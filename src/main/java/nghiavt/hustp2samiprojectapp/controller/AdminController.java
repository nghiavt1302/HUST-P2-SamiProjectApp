package nghiavt.hustp2samiprojectapp.controller;

import nghiavt.hustp2samiprojectapp.constant.AssignStatusEnum;
import nghiavt.hustp2samiprojectapp.constant.CommitteeRoleEnum;
import nghiavt.hustp2samiprojectapp.model.entity.Assignment;
import nghiavt.hustp2samiprojectapp.model.entity.Committee;
import nghiavt.hustp2samiprojectapp.model.entity.TeacherInComm;
import nghiavt.hustp2samiprojectapp.repository.AssignmentRepository;
import nghiavt.hustp2samiprojectapp.repository.CommitteeRepository;
import nghiavt.hustp2samiprojectapp.repository.TeacherInCommRepository;
import nghiavt.hustp2samiprojectapp.service.impl.AssignmentServiceImpl;
import nghiavt.hustp2samiprojectapp.service.impl.ParametersServiceImpl;
import nghiavt.hustp2samiprojectapp.service.impl.TimelineServiceImpl;
import nghiavt.hustp2samiprojectapp.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class AdminController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ParametersServiceImpl parametersService;
    @Autowired
    AssignmentServiceImpl assignmentService;
    @Autowired
    TimelineServiceImpl timelineService;
    @Autowired
    CommitteeRepository committeeRepository;
    @Autowired
    TeacherInCommRepository teacherInCommRepository;
    @Autowired
    AssignmentRepository assignmentRepository;

    // Đổi role của một tài khoản nào đó
    @CrossOrigin
    @GetMapping("/admin/change-role")
    public String changeRole(@RequestParam(name = "email") String email,
                             @RequestParam(name = "role") String role){
        return userService.changeUserRole(email, role);
    }

    // Thiết lập trọng số hướng dẫn mặc định theo bộ môn
    @CrossOrigin
    @GetMapping("/admin/change-param")
    public String changeParam(@RequestParam(name = "term") String term,
                              @RequestParam(name = "bmtcb") float bmtcb,
                              @RequestParam(name = "bmtt") float bmtt,
                              @RequestParam(name = "bmtud") float bmtud){
        return parametersService.changeParam(term, bmtcb, bmtt, bmtud);
    }

    // Chỉnh sửa phân công GVHD
    @CrossOrigin
    @GetMapping("/admin/change-assign-instruct")
    public String changeAssignInstruct(@RequestParam(name = "assignId") String assignId,
                              @RequestParam(name = "instructorId") String id){
        return assignmentService.changeInstructor(assignId,id);
    }

    // Thêm mốc thời gian
    @CrossOrigin
    @GetMapping("/admin/add-event")
    public String addEvent(@RequestParam(name = "event") String event,
                                       @RequestParam(name = "expire") Timestamp expire){
        return timelineService.addEvent(event, expire);
    }

    // Xác nhận phân công GVHD
    @CrossOrigin
    @GetMapping("/admin/accept")
    public String acceptAssignment(@RequestParam (name = "id") String asgId,
                                   @RequestParam (name = "status")AssignStatusEnum status){
        return assignmentService.acceptAssign(asgId, status);
    }

    // Lập hội đồng
    @CrossOrigin
    @GetMapping("/admin/createCommittee")
    public String createCommittee(@RequestParam (name = "commId") String commId,
                                   @RequestParam (name = "term") String term,
                                   @RequestParam (name = "number") Integer number,
                                  @RequestParam (name = "orient") String orient){
        committeeRepository.save(new Committee(commId, term, number, orient));
        return "Created!";
    }

    // Add giảng viên vào hội đồng
    @CrossOrigin
    @GetMapping("/admin/addTeacherToComm")
    public String addTeacherToComm(@RequestParam (name = "commId") String commId,
                                  @RequestParam (name = "teacherId") String teacherId,
                                  @RequestParam (name = "role") CommitteeRoleEnum role){
        TeacherInComm add = new TeacherInComm();
        add.setTeacherId(teacherId);
        add.setCommId(commId);
        add.setRole(role);
        teacherInCommRepository.save(add);
        return "Added!";
    }

    // Phân công Hội đồng
    @CrossOrigin
    @GetMapping("/admin/addCommToAssignment")
    public String addCommToAssignment(@RequestParam (name = "commId") String commId,
                                   @RequestParam (name = "assignId") String assignId){
        List<Assignment> a = assignmentRepository.findByAssignId(assignId);
        if (!a.isEmpty()){
            Assignment add = a.get(0);
            add.setCommId(commId);
            assignmentRepository.save(add);
        }
        return "Added!";
    }
}

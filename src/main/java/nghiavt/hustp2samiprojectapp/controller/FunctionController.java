package nghiavt.hustp2samiprojectapp.controller;

import nghiavt.hustp2samiprojectapp.constant.AssignStatusEnum;
import nghiavt.hustp2samiprojectapp.constant.DepartmentEnum;
import nghiavt.hustp2samiprojectapp.constant.OrientEnum;
import nghiavt.hustp2samiprojectapp.model.dataObject.*;
import nghiavt.hustp2samiprojectapp.model.entity.*;
import nghiavt.hustp2samiprojectapp.repository.*;
import nghiavt.hustp2samiprojectapp.repository.dataObjectRepository.TeacherListForApplyingRepo;
import nghiavt.hustp2samiprojectapp.repository.dataObjectRepository.TeachersNameListRepo;
import nghiavt.hustp2samiprojectapp.service.impl.AssignmentServiceImpl;
import nghiavt.hustp2samiprojectapp.service.impl.ParametersServiceImpl;
import nghiavt.hustp2samiprojectapp.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
public class FunctionController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ParametersServiceImpl parametersService;
    @Autowired
    TeachersNameListRepo teachersNameListRepo;
    @Autowired
    TeacherListForApplyingRepo teacherListForApplyingRepo;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    AssignmentServiceImpl assignmentService;
    @Autowired
    AssignmentRepository assignmentRepository;
    @Autowired
    ProjectRepository projectRepository;

    // API lấy thông tin sinh viên hiện tại
    @CrossOrigin
    @GetMapping("/api/getCurStudentInfo")
    public Student getCurStudentInfo(Authentication authentication){
        String email = userService.getCurUsername(authentication);
        List<Student> students = studentRepository.findByEmail(email);
        return students.get(0);
    }

    // API Lấy email của user hiện tại
    @CrossOrigin
    @GetMapping(value = "/username")
    @ResponseBody
    public String currentUserName(Authentication authentication) {
        return userService.getCurUsername(authentication);
    }

    // API Lấy bộ Mã cán bộ + Tên giảng viên
    @CrossOrigin
    @GetMapping("/api/getTeachersName")
    public List<TeachersNameList> getTeachersName(){
        return teachersNameListRepo.findAll();
    }

    // API lấy thông tin toàn bộ giảng viên để hiển thị khi đăng kí NV
    @CrossOrigin
    @GetMapping("/teacherList")
    public List<TeacherListForApplying> getAll(){
        return teacherListForApplyingRepo.findAll();
    }

    // API lấy thông tin học kỳ hiện tại
    @CrossOrigin
    @GetMapping("/termNow")
    public String getCurrentTerm(){
        return parametersService.getCurTerm();
    }

    // API lấy thông tin GV hiện tại
    @CrossOrigin
    @GetMapping("/api/getCurTeacherInfo")
    public Teacher getCurTeacherInfo(Authentication authentication){
        String email = userService.getCurUsername(authentication);
        List<Teacher> teachers = teacherRepository.findByEmail(email);
        return teachers.get(0);
    }

    @CrossOrigin
    @GetMapping("/api/getApplicationList")
    public List<ApplicationList> getApplicationList(){
        List<ApplicationList> list = new ArrayList<>();
        List<Application> apps = applicationRepository.findAll();
        for (Application app : apps) {
            ApplicationList add = new ApplicationList();
            add.setAppId(app.getAppId());
            add.setStudentId(app.getStudentId());
            add.setOrient1(app.getOrient1());
            add.setOrient2(app.getOrient2());
            add.setOpt1(app.getOpt1());
            add.setOpt2(app.getOpt2());
            add.setOpt3(app.getOpt3());
            add.setCpa(studentRepository.findByStudentId(app.getStudentId()).get(0).getCpa());
            list.add(add);
        }
        Collections.sort(list, Comparator.comparing(ApplicationList::getCpa));
        ApplicationList middle = list.get(list.size()/2);
        List<ApplicationList> left = new ArrayList<>();
        List<ApplicationList> right = new ArrayList<>();
        for (ApplicationList a : list) {
            if (a.getCpa() < middle.getCpa()){
                left.add(a);
            }else {
                right.add(a);
            }
        }
        Collections.shuffle(left);
        Collections.shuffle(right);

        List<ApplicationList> result = new ArrayList<>();
        while (!left.isEmpty() && !right.isEmpty()){
            try {
                result.add(left.remove(0));
                result.add(right.remove(0));
            }catch (Exception e){
            }
        }
        return result;
    }

    // Phân công GVHD tự động
    @CrossOrigin
    @GetMapping("/api/autoAssign")
    public List<PlainAssignResult> autoAssign() {
        List<ApplicationList> input = getApplicationList();
        int m = input.size();
        List<Teacher> teachers = teacherRepository.findAll();
//        Collections.shuffle(teachers);
        float sigmaPi = 0;
        for (Teacher t : teachers) {
            sigmaPi += t.getParam();
        }
        float digits = m / sigmaPi;
        for (Teacher t : teachers) {
            t.setQuota((int) Math.ceil(digits * t.getParam()));
        }

        for (ApplicationList app : input) {
            List<Teacher> wishList = new ArrayList<>();
            try {
                wishList.add(teacherRepository.findByTeacherId(app.getOpt1()).get(0));
                wishList.add(teacherRepository.findByTeacherId(app.getOpt2()).get(0));
                wishList.add(teacherRepository.findByTeacherId(app.getOpt3()).get(0));
            } catch (Exception e) {}
            for (Teacher t : wishList) {
                boolean duplicate = assignmentService.checkDuplicate(app.getStudentId(), t.getTeacherId());
                if ((t.getAssigned() < t.getQuota()) && !duplicate) {
                    Assignment add = new Assignment();
                    add.setAssignId(app.getAppId());
                    add.setAppId(app.getAppId());
                    add.setInstructorId(t.getTeacherId());
                    add.setStatus(AssignStatusEnum.ASSIGNED);
                    add.setStudentId(app.getStudentId());
                    assignmentRepository.save(add);
                    t.setAssigned(t.getAssigned() + 1);
                    break;
                }
                Assignment add = new Assignment();
                add.setAssignId(app.getAppId());
                add.setAppId(app.getAppId());
                add.setStatus(AssignStatusEnum.EXCEPTED);
                add.setStudentId(app.getStudentId());
                assignmentRepository.save(add);
            }
        }

        List<Assignment> except = assignmentRepository.findByStatus(AssignStatusEnum.EXCEPTED);
        List<Teacher> remain = new ArrayList<>();
        for (Teacher t : teachers) {
            if (t.getAssigned() < t.getQuota()) {
                remain.add(t);
            }
        }
        Collections.sort(remain, Comparator.comparing(Teacher::getAssigned));
        for (Assignment asg : except) {
            boolean assigned = false;
            for (Teacher t : remain) {
                if (t.getAssigned() < t.getQuota()) {
                    OrientEnum orient1 = OrientEnum.valueOf(applicationRepository.findByAppId(asg.getAppId()).get(0).getOrient1());
                    if (t.getDepartment() == DepartmentEnum.BMTUD && (orient1 == OrientEnum.PPTU || orient1 == OrientEnum.TTKH)) {
                        boolean duplicate = assignmentService.checkDuplicate(asg.getStudentId(), t.getTeacherId());
                        if (!duplicate) {
                            Assignment add = new Assignment();
                            add.setAssignId(asg.getAppId());
                            add.setAppId(asg.getAppId());
                            add.setInstructorId(t.getTeacherId());
                            add.setStatus(AssignStatusEnum.ASSIGNED);
                            add.setStudentId(asg.getStudentId());
                            assignmentRepository.save(add);
                            t.setAssigned(t.getAssigned() + 1);
                            assigned = true;
                            break;
                        }
                    } else if (t.getDepartment() == DepartmentEnum.BMTT && orient1 == OrientEnum.TH) {
                        boolean duplicate = assignmentService.checkDuplicate(asg.getStudentId(), t.getTeacherId());
                        if (!duplicate) {
                            Assignment add = new Assignment();
                            add.setAssignId(asg.getAppId());
                            add.setAppId(asg.getAppId());
                            add.setInstructorId(t.getTeacherId());
                            add.setStatus(AssignStatusEnum.ASSIGNED);
                            add.setStudentId(asg.getStudentId());
                            assignmentRepository.save(add);
                            t.setAssigned(t.getAssigned() + 1);
                            assigned = true;
                            break;
                        }
                    } else if (t.getDepartment() == DepartmentEnum.BMTCB && orient1 == OrientEnum.PPNN) {
                        boolean duplicate = assignmentService.checkDuplicate(asg.getStudentId(), t.getTeacherId());
                        if (!duplicate) {
                            Assignment add = new Assignment();
                            add.setAssignId(asg.getAppId());
                            add.setAppId(asg.getAppId());
                            add.setInstructorId(t.getTeacherId());
                            add.setStatus(AssignStatusEnum.ASSIGNED);
                            add.setStudentId(asg.getStudentId());
                            assignmentRepository.save(add);
                            t.setAssigned(t.getAssigned() + 1);
                            assigned = true;
                            break;
                        }
                    }
                }
            }
            if (!assigned) {
                for (Teacher t : remain) {
                    if (t.getAssigned() < t.getQuota()) {
                        OrientEnum orient2 = OrientEnum.valueOf(applicationRepository.findByAppId(asg.getAppId()).get(0).getOrient2());
                        if (t.getDepartment() == DepartmentEnum.BMTUD && (orient2 == OrientEnum.PPTU || orient2 == OrientEnum.TTKH)) {
                            boolean duplicate = assignmentService.checkDuplicate(asg.getStudentId(), t.getTeacherId());
                            if (!duplicate) {
                                Assignment add = new Assignment();
                                add.setAssignId(asg.getAppId());
                                add.setAppId(asg.getAppId());
                                add.setInstructorId(t.getTeacherId());
                                add.setStatus(AssignStatusEnum.ASSIGNED);
                                add.setStudentId(asg.getStudentId());
                                assignmentRepository.save(add);
                                t.setAssigned(t.getAssigned() + 1);
//                                assigned = true;
                                break;
                            }
                        } else if (t.getDepartment() == DepartmentEnum.BMTT && orient2 == OrientEnum.TH) {
                            boolean duplicate = assignmentService.checkDuplicate(asg.getStudentId(), t.getTeacherId());
                            if (!duplicate) {
                                Assignment add = new Assignment();
                                add.setAssignId(asg.getAppId());
                                add.setAppId(asg.getAppId());
                                add.setInstructorId(t.getTeacherId());
                                add.setStatus(AssignStatusEnum.ASSIGNED);
                                add.setStudentId(asg.getStudentId());
                                assignmentRepository.save(add);
                                t.setAssigned(t.getAssigned() + 1);
//                                assigned = true;
                                break;
                            }
                        } else if (t.getDepartment() == DepartmentEnum.BMTCB && orient2 == OrientEnum.PPNN) {
                            boolean duplicate = assignmentService.checkDuplicate(asg.getStudentId(), t.getTeacherId());
                            if (!duplicate) {
                                Assignment add = new Assignment();
                                add.setAssignId(asg.getAppId());
                                add.setAppId(asg.getAppId());
                                add.setInstructorId(t.getTeacherId());
                                add.setStatus(AssignStatusEnum.ASSIGNED);
                                add.setStudentId(asg.getStudentId());
                                assignmentRepository.save(add);
                                t.setAssigned(t.getAssigned() + 1);
//                                assigned = true;
                                break;
                            }
                        }
                    }
                }
            }
//            else if (!assigned) {
//                Assignment add = new Assignment();
//                add.setAssignId(asg.getAppId());
//                add.setAppId(asg.getAppId());
//                add.setStatus(AssignStatusEnum.EXCEPTED);
//                add.setStudentId(asg.getStudentId());
//                assignmentRepository.save(add);
//            }
        }
        List<Assignment> result = assignmentRepository.findAll();
        List<PlainAssignResult> output = new ArrayList<>();
        for (Assignment a : result) {
            PlainAssignResult add = new PlainAssignResult();
            try {
                add.setAssignId(a.getAssignId());
                add.setStudentId(a.getStudentId());
                add.setStudentName(studentRepository.findByStudentId(a.getStudentId()).get(0).getFullName());
                add.setInstructorName(teacherRepository.findByTeacherId(a.getInstructorId()).get(0).getFullName());
                add.setStatus(a.getStatus());

            } catch (IndexOutOfBoundsException e) {
                add.setInstructorName(null);
            }
            output.add(add);
        }
        return output;
    }

    @CrossOrigin
    @GetMapping("/api/getPlainApplication")
    public List<PlainApplicationList> getPlainAppList(){
        List<PlainApplicationList> result = new ArrayList<>();
        List<Application> apps = applicationRepository.findAll();
        for (Application a : apps) {
            PlainApplicationList add = new PlainApplicationList();
            try {
                add.setAppId(a.getAppId());
                add.setStudentId(a.getStudentId());
                add.setStudentName(studentRepository.findByStudentId(a.getStudentId()).get(0).getFullName());
                add.setCpa(studentRepository.findByStudentId(a.getStudentId()).get(0).getCpa());
                add.setOrient1(a.getOrient1());
                add.setOrient2(a.getOrient2());
                add.setOpt1(teacherRepository.findByTeacherId(a.getOpt1()).get(0).getFullName());
                add.setOpt2(teacherRepository.findByTeacherId(a.getOpt2()).get(0).getFullName());
                add.setOpt3(teacherRepository.findByTeacherId(a.getOpt3()).get(0).getFullName());
            }
            catch (Exception e){}
            result.add(add);
        }
        return result;
    }

    // Lấy tất cả thông tin về các đồ án
    @CrossOrigin
    @GetMapping("/api/findAllProject")
    public List<Project> findAllProject(){
        return projectRepository.findAll();
    }

    // Tìm đồ án theo keywords
    @CrossOrigin
    @GetMapping("/api/findProjectByKeywords")
    public List<Project> findProjectByKeywords(@RequestParam (name = "key") String key){
        return projectRepository.findAllByKeywordsContains(key);
    }

    @CrossOrigin
    @GetMapping("authPricipal")
    public String authPrin(Authentication authentication){
        return authentication.getPrincipal().toString();
    }

}

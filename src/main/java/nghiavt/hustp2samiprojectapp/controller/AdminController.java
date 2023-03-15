package nghiavt.hustp2samiprojectapp.controller;

import nghiavt.hustp2samiprojectapp.model.entity.Student;
import nghiavt.hustp2samiprojectapp.service.impl.AssignmentServiceImpl;
import nghiavt.hustp2samiprojectapp.service.impl.ParametersServiceImpl;
import nghiavt.hustp2samiprojectapp.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ParametersServiceImpl parametersService;
    @Autowired
    AssignmentServiceImpl assignmentService;

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



}

package nghiavt.hustp2samiprojectapp.controller;


import nghiavt.hustp2samiprojectapp.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeacherController {
    @Autowired
    TeacherServiceImpl teacherService;
    @CrossOrigin
    @GetMapping("/gv-homeinfo/update")
    public String updateTeacherInfo(@RequestParam(name = "expertise") String expertise,
                                    @RequestParam(name = "phone") String phone,
                                    Authentication authentication) {
        return teacherService.updateTeacherInfo(expertise, phone, authentication);
    }
}

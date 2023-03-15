package nghiavt.hustp2samiprojectapp.controller;

import nghiavt.hustp2samiprojectapp.constant.ProjectTypeEnum;
import nghiavt.hustp2samiprojectapp.model.entity.Application;
import nghiavt.hustp2samiprojectapp.model.entity.Student;
import nghiavt.hustp2samiprojectapp.repository.ApplicationRepository;
import nghiavt.hustp2samiprojectapp.repository.StudentRepository;
import nghiavt.hustp2samiprojectapp.repository.TeacherRepository;
import nghiavt.hustp2samiprojectapp.repository.dataObjectRepository.ParametersRepo;
import nghiavt.hustp2samiprojectapp.repository.dataObjectRepository.TeacherListForApplyingRepo;
import nghiavt.hustp2samiprojectapp.repository.dataObjectRepository.TeachersNameListRepo;
import nghiavt.hustp2samiprojectapp.service.impl.StudentServiceImpl;
import nghiavt.hustp2samiprojectapp.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class StudentController {
    @Autowired
    StudentServiceImpl studentService;

    //API gửi đăng kí nguyện vọng
    @CrossOrigin
    @GetMapping("/sv-apply/send")
    public String sendApplication(@RequestParam(name = "prjType") String prjType,
                                  @RequestParam(name = "orient1") String orient1,
                                  @RequestParam(name = "orient2") String orient2,
                                  @RequestParam(name = "opt1") String opt1,
                                  @RequestParam(name = "opt2") String opt2,
                                  @RequestParam(name = "opt3") String opt3,
                                  Authentication authentication){
        return studentService.sendApplication(prjType, orient1, orient2, opt1, opt2, opt3, authentication);
    }

    // API cập nhật thông tin cá nhân SV
    @CrossOrigin
    @GetMapping("/sv-homeinfo/update")
    public String updateStudentInfo(@RequestParam(name = "cpa") String cpa,
                                    @RequestParam(name = "phone") String phone,
                                    Authentication authentication) {
        return studentService.updateStudentInfo(cpa, phone, authentication);
    }
}

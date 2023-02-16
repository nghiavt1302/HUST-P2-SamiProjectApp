package nghiavt.hustp2samiprojectapp.controller;

import nghiavt.hustp2samiprojectapp.constant.ProjectTypeEnum;
import nghiavt.hustp2samiprojectapp.model.dataObject.TeacherListForApplying;
import nghiavt.hustp2samiprojectapp.model.dataObject.TeachersNameList;
import nghiavt.hustp2samiprojectapp.model.entity.Application;
import nghiavt.hustp2samiprojectapp.model.entity.Student;
import nghiavt.hustp2samiprojectapp.model.entity.Teacher;
import nghiavt.hustp2samiprojectapp.repository.ApplicationRepository;
import nghiavt.hustp2samiprojectapp.repository.StudentRepository;
import nghiavt.hustp2samiprojectapp.repository.TeacherRepository;
import nghiavt.hustp2samiprojectapp.repository.dataObjectRepository.ParametersRepo;
import nghiavt.hustp2samiprojectapp.repository.dataObjectRepository.TeacherListForApplyingRepo;
import nghiavt.hustp2samiprojectapp.repository.dataObjectRepository.TeachersNameListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class TestingRestController {
    @Autowired
    TeacherListForApplyingRepo teacherListForApplyingRepo;
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    TeachersNameListRepo teachersNameListRepo;
    @Autowired
    ParametersRepo parametersRepo;
    @CrossOrigin
    @GetMapping("/teacherList")
    public List<TeacherListForApplying> getAll(){
        return teacherListForApplyingRepo.findAll();
    }

    //Get current user email
    @CrossOrigin
    @GetMapping(value = "/username")
    @ResponseBody
    public String currentUserName(Authentication authentication) {
        if (authentication != null)
            return authentication.getName();
        else
            return "";
    }

    @CrossOrigin
    @GetMapping("/termNow")
    public String getCurrentTerm(){
        return parametersRepo.findByCurrent(true).get(0).getTerm();
    }

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
        Application app = new Application();
        String username = currentUserName(authentication);
        List<Student> students = studentRepository.findByEmail(username);
        String stuId = String.valueOf(students.get(0).getStudentId());
        app.setStudentId(Integer.valueOf(stuId));
        String term = getCurrentTerm();
        String appId = term.substring(2)+"."+stuId.substring(2)+"."+prjType;
        List<Application> isExist = applicationRepository.findByAppId(appId);
        if (isExist.isEmpty()){
            app.setAppId(appId);
            app.setTerm(term);
            app.setProjectType(ProjectTypeEnum.valueOf(prjType));
            app.setOrient1(orient1);
            app.setOrient2(orient2);
            app.setOpt1(opt1);
            app.setOpt2(opt2);
            app.setOpt3(opt3);
            Timestamp now = new Timestamp(System.currentTimeMillis());
            app.setSubmitTime(now);
            applicationRepository.save(app);
            return "Đăng kí thành công! " + app;
        }
        return "Bạn đã đăng kí loại đồ án này rồi!";
    }

    // API cập nhật thông tin cá nhân SV
    @CrossOrigin
    @GetMapping("/sv-homeinfo/update")
    public String updateStudentInfo(@RequestParam(name = "cpa") String cpa,
                                    @RequestParam(name = "phone") String phone,
                                    Authentication authentication) {
        String email = currentUserName(authentication);
        List<Student> students = studentRepository.findByEmail(email);
        Student update = students.get(0);
        if (!cpa.isEmpty()){
            update.setCpa(Float.parseFloat(cpa));
        }
        if (!phone.isEmpty()){
            update.setPhone(phone);
        }
        studentRepository.save(update);
        return "Sucess!";
    }

    @CrossOrigin
    @GetMapping("/gv-homeinfo/update")
    public String updateTeacherInfo(@RequestParam(name = "expertise") String expertise,
                                    @RequestParam(name = "phone") String phone,
                                    Authentication authentication) {
        String email = currentUserName(authentication);
        List<Teacher> teachers = teacherRepository.findByEmail(email);
        Teacher update = teachers.get(0);
        if (!expertise.isEmpty()){
            update.setExpertise(expertise);
        }
        if (!phone.isEmpty()){
            update.setPhone(phone);
        }
        teacherRepository.save(update);
        return "Sucess!";
    }


    @CrossOrigin
    @GetMapping("/api/getCurStudentInfo")
    public Student getCurStudentInfo(Authentication authentication){
        String email = currentUserName(authentication);
        List<Student> students = studentRepository.findByEmail(email);
        return students.get(0);
    }

    @CrossOrigin
    @GetMapping("/api/getCurTeacherInfo")
    public Teacher getCurTeacherInfo(Authentication authentication){
        String email = currentUserName(authentication);
        List<Teacher> teachers = teacherRepository.findByEmail(email);
        return teachers.get(0);
    }


    @CrossOrigin
    @GetMapping("/api/getTeachersName")
    public List<TeachersNameList> getTeachersName(){
        return teachersNameListRepo.findAll();
    }

    @CrossOrigin
    @GetMapping("authPricipal")
    public String authPrin(Authentication authentication){
        return authentication.getPrincipal().toString();
    }
}

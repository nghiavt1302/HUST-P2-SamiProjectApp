package nghiavt.hustp2samiprojectapp.service.impl;

import nghiavt.hustp2samiprojectapp.constant.AssignStatusEnum;
import nghiavt.hustp2samiprojectapp.constant.ProjectTypeEnum;
import nghiavt.hustp2samiprojectapp.model.entity.Application;
import nghiavt.hustp2samiprojectapp.model.entity.Assignment;
import nghiavt.hustp2samiprojectapp.model.entity.Student;
import nghiavt.hustp2samiprojectapp.repository.ApplicationRepository;
import nghiavt.hustp2samiprojectapp.repository.AssignmentRepository;
import nghiavt.hustp2samiprojectapp.repository.StudentRepository;
import nghiavt.hustp2samiprojectapp.repository.dataObjectRepository.ParametersRepo;
import nghiavt.hustp2samiprojectapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    ParametersServiceImpl parametersService;
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    AssignmentRepository assignmentRepository;

    // Gửi đăng ký nguyện vọng
    @Override
    public String sendApplication(String prjType, String orient1, String orient2, String opt1, String opt2, String opt3, Authentication authentication) {
        Application app = new Application();
        String username = userService.getCurUsername(authentication);
        List<Student> students = studentRepository.findByEmail(username);
        String stuId = String.valueOf(students.get(0).getStudentId());
        app.setStudentId(Integer.valueOf(stuId));
        String term = parametersService.getCurTerm();
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

            Assignment assignment = new Assignment();
            assignment.setAssignId(appId);
            assignment.setStudentId(Integer.valueOf(stuId));
            assignment.setTerm(term);
            assignment.setStatus(AssignStatusEnum.UNASSIGNED);
            assignment.setPrjType(ProjectTypeEnum.valueOf(prjType));
            assignment.setAppId(appId);
            assignmentRepository.save(assignment);
            return "Đăng kí thành công! ";
        }
        return "Bạn đã đăng kí loại đồ án này rồi!";
    }

    @Override
    public String updateStudentInfo(String cpa, String phone, Authentication authentication) {
        String email = userService.getCurUsername(authentication);
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
}

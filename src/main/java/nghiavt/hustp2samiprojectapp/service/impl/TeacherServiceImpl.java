package nghiavt.hustp2samiprojectapp.service.impl;

import nghiavt.hustp2samiprojectapp.model.entity.Teacher;
import nghiavt.hustp2samiprojectapp.repository.TeacherRepository;
import nghiavt.hustp2samiprojectapp.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    TeacherRepository teacherRepository;
    @Override
    public String updateTeacherInfo(String expertise, String phone, Authentication authentication) {
        String email = userService.getCurUsername(authentication);
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
}

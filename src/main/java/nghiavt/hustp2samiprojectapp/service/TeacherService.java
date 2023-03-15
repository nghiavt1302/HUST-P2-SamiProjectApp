package nghiavt.hustp2samiprojectapp.service;

import org.springframework.security.core.Authentication;

public interface TeacherService {
    String updateTeacherInfo(String expertise, String phone, Authentication authentication);
}

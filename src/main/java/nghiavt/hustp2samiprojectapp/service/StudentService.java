package nghiavt.hustp2samiprojectapp.service;

import org.springframework.security.core.Authentication;

public interface StudentService {
    String sendApplication(String prjType,
                           String orient1,
                           String orient2,
                           String opt1,
                           String opt2,
                           String opt3,
                           Authentication authentication);
    String updateStudentInfo(String cpa, String phone, Authentication authentication);
}

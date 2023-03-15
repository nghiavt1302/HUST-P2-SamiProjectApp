package nghiavt.hustp2samiprojectapp.service;

import org.springframework.security.core.Authentication;

public interface UserService {
    String getCurUsername(Authentication authentication);
    String changeUserRole(String email, String role);
}

package nghiavt.hustp2samiprojectapp.service.impl;

import nghiavt.hustp2samiprojectapp.constant.RoleEnum;
import nghiavt.hustp2samiprojectapp.model.entity.MyUser;
import nghiavt.hustp2samiprojectapp.repository.UserRepository;
import nghiavt.hustp2samiprojectapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public String getCurUsername(Authentication authentication) {
        if (authentication != null)
            return authentication.getName();
        else
            return "";
    }

    @Override
    public String changeUserRole(String email, String role) {
        List<MyUser> users = userRepository.findByEmail(email);
        MyUser user = new MyUser();
        user.setUserId(users.get(0).getUserId());
        user.setPassword(users.get(0).getPassword());
        user.setEmail(users.get(0).getEmail());
        user.setRole(RoleEnum.valueOf(role));
        user.setStatus(users.get(0).getStatus());
        userRepository.save(user);
        return "User " + users.get(0).getEmail() + " changed role: " + role;
    }
}

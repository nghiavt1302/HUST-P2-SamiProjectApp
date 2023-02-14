package nghiavt.hustp2samiprojectapp.config;

import nghiavt.hustp2samiprojectapp.model.entity.MyUser;
import nghiavt.hustp2samiprojectapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CustomUserDetails implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userName, password = null;
        List<GrantedAuthority> authorities = null;
        List<MyUser> myUser = userRepository.findByEmail(username);
        if (myUser.size() == 0) {
            throw new UsernameNotFoundException("User not found: " + username);
        }else {
            userName = myUser.get(0).getEmail();
            password = myUser.get(0).getPassword();
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(myUser.get(0).getRole().toString()));
        }
        return new User(userName, password, authorities);
    }
}

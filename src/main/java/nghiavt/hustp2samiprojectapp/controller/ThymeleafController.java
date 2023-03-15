package nghiavt.hustp2samiprojectapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafController {
    @CrossOrigin
    @GetMapping("/sv-apply")
    public String studentApply(){
        return "sv-apply";
    }

    @CrossOrigin
    @GetMapping("/sv-homeinfo")
    public String studentInfo(){
        return "sv-homeinfo";
    }

    @CrossOrigin
    @GetMapping("/gv-homeinfo")
    public String teacherInfo(){
        return "gv-homeinfo";
    }
}

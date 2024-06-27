package ma.emsi.javaproject.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {
    @GetMapping(path = "/")
    public String homePage()
    {
        return "home";
    }
}

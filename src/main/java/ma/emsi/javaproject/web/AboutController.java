package ma.emsi.javaproject.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {
    @GetMapping(path = "/about")
    public String aboutPage()
    {
        return "about";
    }
}

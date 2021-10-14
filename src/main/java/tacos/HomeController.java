package tacos;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/hello")
    public String home(@RequestParam(name="http_name",required=true) String name, Model model){
        model.addAttribute("name",name);
        return "home";
    }

    @GetMapping("/Index")
    public String index(@RequestParam(name="http_name",required=true) String name, Model model){
        return "index";
    }
}

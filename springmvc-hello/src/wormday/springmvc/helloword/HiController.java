package wormday.springmvc.helloword;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hi")
public class HiController {
    @RequestMapping("/say")
    public String say(){
        return "say";
    }
}

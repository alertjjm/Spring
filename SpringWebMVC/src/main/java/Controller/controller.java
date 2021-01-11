package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by alertjjm on 2021-01-10.
 */
@Controller
public class controller {
    @RequestMapping(value = "/")
    public String test(){
        return "index";
    }
}

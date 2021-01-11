package Controller.notice;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * Created by alertjjm on 2021-01-10.
 */
public class ListController implements Controller {
    public ModelAndView handleRequest(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView mv=new ModelAndView("notice/list");
        return mv;
    }
}

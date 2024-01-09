package dev.lapisteam.webapp.ui.wethand;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UIWethand {

    @RequestMapping("wethand")
    public ModelAndView renderMain(){
        return new ModelAndView("wethand/index");
    }

}

package dev.lapisteam.webapp.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UIMain {

    @RequestMapping("")
    public ModelAndView renderIndex(Model model){
        return new ModelAndView("index");
    }

    @RequestMapping("loader")
    public ModelAndView renderLoader(){
        return new ModelAndView("loader");
    }

}

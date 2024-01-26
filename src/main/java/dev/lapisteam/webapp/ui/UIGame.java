package dev.lapisteam.webapp.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("page")
public class UIGame {

    @GetMapping("")
    public ModelAndView renderIndex(@PathVariable String path, Model model){

        return new ModelAndView("page/gameIndex");
    }

}

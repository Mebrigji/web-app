package dev.lapisteam.webapp.ui.asterius;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

@Controller
public class UIAsterius {

    @RequestMapping("")
    public ModelAndView renderIndex(Model model){
        Random random = new Random();
        int i = random.nextInt(0, 2);
        System.out.println(i);
        model.addAttribute("logged", i == 1);
        return new ModelAndView("asterius/index");
    }


}

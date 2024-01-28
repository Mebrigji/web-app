package dev.lapisteam.webapp.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("page/server")
public class UIGame {

    @GetMapping("index")
    public ModelAndView renderIndex(@RequestParam String id, Model model){

        model.addAttribute("isLogged", true);
        model.addAttribute("nickName", "Mebrigji");
        model.addAttribute("players", 153);
        model.addAttribute("serverId", id);

        return new ModelAndView("page/gameIndex");
    }

}

package dev.lapisteam.webapp.ui.panel;

import dev.lapisteam.webapp.objects.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

@Controller
public class UIPanel {

    @RequestMapping("panel")
    public ModelAndView renderPanel(Model model){
        Account account = new Account("Mebrigji", "https://mc-heads.net/avatar/Mebrigji", "dev/admin");
        model.addAttribute("account", account);
        Random random = new Random();
        int i = random.nextInt(2);
        System.out.println(i);
        model.addAttribute("bell", i == 1 ? "fas fa-bell" : "fas fa-bell-slash");
        return new ModelAndView("panel/index");
    }

}

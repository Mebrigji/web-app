package dev.lapisteam.webapp.ui.panel;

import dev.lapisteam.webapp.events.list.AuthEvent;
import dev.lapisteam.webapp.objects.Account;
import dev.lapisteam.webapp.objects.itemshop.Offer;
import dev.lapisteam.webapp.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Random;

@Controller
public class UIPanel {

    @RequestMapping("panel")
    public ModelAndView renderPanel(HttpServletRequest servletRequest, Model model){
        AuthEvent authEvent = new AuthEvent("panel", servletRequest, model);
        authEvent.call();


        if(authEvent.isCancelled()) return authEvent.getModelAndView();

        model.addAttribute("account", authEvent.getAccount());

        Random random = new Random();
        int i = random.nextInt(2);
        model.addAttribute("bell", i == 1 ? "fas fa-bell" : "fas fa-bell-slash");
        return new ModelAndView("panel/index");
    }

    @RequestMapping("user")
    public ModelAndView renderUser(HttpServletRequest servletRequest, Model model){
        AuthEvent authEvent = new AuthEvent("user", servletRequest, model);
        authEvent.call();

        if(authEvent.isCancelled()) return authEvent.getModelAndView();
        AuthService authService = AuthService.getInstance();

        model.addAttribute("account", authService.getCache().get(servletRequest.getSession().getId()));

        Random random = new Random();
        int i = random.nextInt(2);
        model.addAttribute("bell", i == 1 ? "fas fa-bell" : "fas fa-bell-slash");
        return new ModelAndView("panel/user");
    }

    @RequestMapping("itemshop")
    public ModelAndView renderItemShop(HttpServletRequest servletRequest, Model model, @RequestParam String serverId){
        AuthEvent authEvent = new AuthEvent("itemshop", servletRequest, model);
        authEvent.call();

        if(authEvent.isCancelled()) return authEvent.getModelAndView();

        model.addAttribute("account", authEvent.getAccount());

        Random random = new Random();
        int i = random.nextInt(2);

        model.addAttribute("bell", i == 1 ? "fas fa-bell" : "fas fa-bell-slash");
        model.addAttribute("serverId", serverId.equals("0") ? "sᴜʀᴠɪᴠᴀʟ + ᴅᴢɪᴀᴌᴋɪ" : "sᴋʏʙʟᴏᴄᴋ");

        Offer offer = new Offer("Voucher na 5000$");
        offer.setCanSell(false);
        offer.setExtraTitle(new HashMap<>(){{
            put("ᴘʀᴢᴇᴅᴍɪᴏᴛ ᴘᴏsᴘᴏʟɪᴛʏ", "margin: 0 0 1.25rem 0; color: #626262");
        }});
        offer.setImg("/assets/panel/itemshop/paper.png");
        offer.addLine("Doładowanie 5000$", "margin: 1rem 0 0 0");
        model.addAttribute("offer", offer);
        return new ModelAndView("panel/itemshop");
    }

    @RequestMapping("sign-out")
    public ModelAndView signOut(HttpServletRequest servletRequest){
        AuthService.getInstance().getCache().remove(servletRequest.getSession().getId());
        return new ModelAndView(new RedirectView("login"));
    }

    @RequestMapping("login")
    public ModelAndView renderLogin(HttpServletRequest servletRequest, Model model){
        AuthEvent authEvent = new AuthEvent("login", servletRequest, model);
        authEvent.call();
        if(authEvent.isCancelled()) return authEvent.getModelAndView();
        return new ModelAndView("panel/login");
    }

    @RequestMapping(path = "auth", method = RequestMethod.POST)
    public ModelAndView renderLogin(HttpServletRequest servletRequest, Model model, @RequestParam String nickName, @RequestParam String password){
        AuthEvent authEvent = new AuthEvent("login", servletRequest, model);
        authEvent.call();

        if(authEvent.isCancelled()) return authEvent.getModelAndView();

        Account account = new Account(nickName, "https://mc-heads.net/avatar/" + nickName, "admin");

        AuthService.getInstance().getCache().remove(servletRequest.getSession().getId());
        AuthService.getInstance().getCache().put(servletRequest.getSession().getId(), account);

        model.addAttribute("account", account);

        Random random = new Random();
        int i = random.nextInt(2);
        model.addAttribute("bell", i == 1 ? "fas fa-bell" : "fas fa-bell-slash");

        return new ModelAndView(new RedirectView("panel"));
    }
}

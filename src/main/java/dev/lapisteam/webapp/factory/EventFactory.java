package dev.lapisteam.webapp.factory;

import dev.lapisteam.webapp.events.EventBuilder;
import dev.lapisteam.webapp.events.list.AuthEvent;
import dev.lapisteam.webapp.objects.Account;
import dev.lapisteam.webapp.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class EventFactory {

    public void buildAuthEvent(){
        new EventBuilder<>(AuthEvent.class, authEvent -> {
            String mapping = authEvent.getRequestMapping();
            HttpServletRequest request = authEvent.getHttpServletRequest();
            AuthService authService = AuthService.getInstance();

            if(mapping.equals("login")){
                Account account = null;
                if(request.getSession().getLastAccessedTime() + TimeUnit.HOURS.toMillis(6) > System.currentTimeMillis() && (account = authService.getCache().get(request.getSession().getId())) != null){
                    authEvent.setCancelled(true);
                    authEvent.setAccount(account);
                    authEvent.getModel().addAttribute("account", authEvent.getAccount());

                    Random random = new Random();
                    int i = random.nextInt(2);
                    authEvent.getModel().addAttribute("bell", i == 1 ? "fas fa-bell" : "fas fa-bell-slash");

                    authEvent.setModelAndView(new ModelAndView("panel/index"));
                }
            } else {
                if(request.getSession().getLastAccessedTime() + TimeUnit.HOURS.toMillis(6) < System.currentTimeMillis()){
                    authEvent.setModelAndView(new ModelAndView("panel/session"));
                    authEvent.setCancelled(true);
                } else {
                    Account account = authService.getCache().get(request.getSession().getId());
                    if(account == null) {
                        authEvent.setCancelled(true);
                        authEvent.setModelAndView(new ModelAndView(new RedirectView("login")));
                    } else {
                        authEvent.setAccount(account);
                    }
                }
            }

        });
    }

}

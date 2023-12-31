package dev.lapisteam.webapp.ui;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UIError implements ErrorController {

    @RequestMapping("error")
    public ModelAndView render404(HttpServletRequest httpServletRequest, Model model){
        int code = (int) httpServletRequest.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String message = (String) httpServletRequest.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        if(message.isEmpty()) {
            if (code == 404) {
                message = "Page not found";
            } else {
                message = "Unexpected page error.";
            }
        }
        model.addAttribute("code", code);
        model.addAttribute("message", message);
        return new ModelAndView("error/unexpected");
    }

}

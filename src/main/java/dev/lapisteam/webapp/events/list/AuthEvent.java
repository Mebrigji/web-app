package dev.lapisteam.webapp.events.list;

import dev.lapisteam.webapp.events.Event;
import dev.lapisteam.webapp.events.stereotype.EventHandler;
import dev.lapisteam.webapp.objects.Account;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class AuthEvent implements Event<AuthEvent> {

    @EventHandler
    private static final Set<Consumer<AuthEvent>> events = new HashSet<>();

    private final String requestMapping;
    private final HttpServletRequest httpServletRequest;
    private final Model model;

    @Setter private ModelAndView modelAndView;
    @Setter private boolean cancelled;
    @Setter Account account;


    public AuthEvent(String requestMapping, HttpServletRequest httpServletRequest, Model model) {
        this.requestMapping = requestMapping;
        this.httpServletRequest = httpServletRequest;
        this.model = model;
    }

    public Model getModel() {
        return model;
    }

    public String getRequestMapping() {
        return requestMapping;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public ModelAndView getModelAndView() {
        return modelAndView;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public AuthEvent getEvent() {
        return this;
    }

    @Override
    public Set<Consumer<AuthEvent>> getEventList() {
        return events;
    }

    public Account getAccount() {
        return account;
    }

}

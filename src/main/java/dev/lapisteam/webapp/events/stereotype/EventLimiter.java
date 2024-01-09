package dev.lapisteam.webapp.events.stereotype;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventLimiter {

    String limitMessage() default "Too many events registered.";

    int size();

}

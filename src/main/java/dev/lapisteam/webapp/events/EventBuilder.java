package dev.lapisteam.webapp.events;

import dev.lapisteam.webapp.events.stereotype.EventHandler;
import dev.lapisteam.webapp.events.stereotype.EventLimiter;
import dev.lapisteam.webapp.helper.ReflectionHelper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

public class EventBuilder<T> {

    private final Class<T> clazz;

    protected Set<Consumer<T>> consumers = null;
    private Field field;

    public EventBuilder(Class<T> clazz, Consumer<T> consumer) {
        this.clazz = clazz;
        Arrays.stream(clazz.getDeclaredFields()).filter(ReflectionHelper::isStatic).filter(field -> field.isAnnotationPresent(EventHandler.class)).forEach(field -> this.field = field);
        try {
            consumers = (Set<Consumer<T>>) field.get(null);
            if(consumer != null) add(consumer);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public EventBuilder(Class<T> clazz){
        this(clazz, null);
    }


    public void add(Consumer<T> consumer){
        EventLimiter eventLimiter = Objects.requireNonNullElseGet(field.getAnnotation(EventLimiter.class), () -> new EventLimiter(){
            @Override
            public Class<? extends Annotation> annotationType() {
                return EventLimiter.class;
            }
            @Override
            public String limitMessage() {
                return "";
            }
            @Override
            public int size() {
                return -1;
            }
        });
        if(eventLimiter.size() == -1){
            consumers.add(consumer);
        } else if(eventLimiter.size() > consumers.size()){
            consumers.add(consumer);
        }
    }

    public void remove(Consumer<T> consumer){
        consumers.remove(consumer);
    }

    public void call(T event){
        consumers.forEach(consumer -> consumer.accept(event));
    }
}

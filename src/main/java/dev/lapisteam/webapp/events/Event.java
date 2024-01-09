package dev.lapisteam.webapp.events;

import java.util.Set;
import java.util.function.Consumer;

public interface Event<T> {

    T getEvent();

    Set<Consumer<T>> getEventList();

    default void call(){
        getEventList().forEach(consumer -> consumer.accept(getEvent()));
    }

}

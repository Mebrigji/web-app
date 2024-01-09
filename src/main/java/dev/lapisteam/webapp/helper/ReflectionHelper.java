package dev.lapisteam.webapp.helper;

import lombok.SneakyThrows;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Objects;

public class ReflectionHelper {


    public static Field getField(Class<?> clazz, String fieldName){
        for (Field declaredField : clazz.getDeclaredFields()) {
            declaredField.setAccessible(true);
            if(declaredField.getName().equals(fieldName)) return declaredField;
        }
        return null;
    }

    public static void setFieldValue(Field field, Object target, Object value){
        Objects.requireNonNull(field, "Field");
        try {
            field.set(target, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static Method getMethod(Class<?> clazz, String methodName) {
        for (Method declaredMethod : clazz.getDeclaredMethods()) {
            declaredMethod.setAccessible(true);
            if(declaredMethod.getName().equals(methodName)) return declaredMethod;
        }
        return null;
    }

    public static Method getMethod(Class<?> clazz, String methodName, Class<?>... args){
        for (Method declaredMethod : clazz.getDeclaredMethods()) {
            declaredMethod.setAccessible(true);
            if(declaredMethod.getName().equals(methodName) && Arrays.equals(declaredMethod.getParameterTypes(), args))
                return declaredMethod;
        }
        return null;
    }

    public static Object invoke(Field field, Object object){
        try {
            return field.get(object);
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    public static Object invoke(Method method, Object object, Object... args){
        try {
            return method.invoke(object, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            return null;
        }
    }

    @SneakyThrows
    public static <T> T newInstance(Class<T> clazz){
        return clazz.newInstance();
    }

    @SneakyThrows
    public static <T> T newInstance(Constructor<T> constructor, Object... args){
        return constructor.newInstance(args);
    }

    public static boolean isStatic(Method method){
        method.setAccessible(true);
        return Modifier.isStatic(method.getModifiers());
    }

    public static boolean isStatic(Field field) {
        field.setAccessible(true);
        return Modifier.isStatic(field.getModifiers());
    }

    public static <T> Constructor<?> getConstructor(Class<T> clazz, Class<?>... params) {
        for (Constructor<?> constructor : clazz.getConstructors()) {
            constructor.setAccessible(true);
            if(Arrays.equals(constructor.getParameterTypes(), params))
                return constructor;
        }
        return null;
    }
}

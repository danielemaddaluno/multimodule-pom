package io.multimodule.data.domain.utils;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;

/**
 * Created by madx on 29/11/2016.
 */
public abstract class ReflectionUtils {
    public static <T> T getValueFromObject(Object object, String fieldName, Class<T> returnClazz){
        return getValueFromObject(object, object.getClass(), fieldName, returnClazz);
    }

    private static <T> T getValueFromObject(Object object, Class<?> declaredFieldClass, String fieldName, Class<T> returnClazz){
        try {
            Field field = declaredFieldClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            Object value = field.get(object);
            return returnClazz.cast(value);
        } catch (NoSuchFieldException e) {
            Class<?> superClass = declaredFieldClass.getSuperclass();
            if(superClass != null){
                return getValueFromObject(object, superClass, fieldName, returnClazz);
            }
        } catch (IllegalAccessException e) {
        }
        return null;
    }

    public static void setValueOnObject(Object object, String fieldName, Object value){
        setValueOnObject(object, object.getClass(), fieldName, value);
    }

    private static void setValueOnObject(Object object, Class<?> declaredFieldClass, String fieldName, Object value){
        try {
            Field field = declaredFieldClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, value);
        } catch (NoSuchFieldException e) {
            Class<?> superClass = declaredFieldClass.getSuperclass();
            if(superClass != null){
                setValueOnObject(object, superClass, fieldName, value);
                return;
            }
        } catch (IllegalAccessException e) {
        }
    }
    
    public static List<Field> getAllFieldsList(final Class<?> cls) {
        return FieldUtils.getAllFieldsList(cls);
    }
}

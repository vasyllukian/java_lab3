package org.fpm.di.example;

import org.fpm.di.Container;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class DummyContainer implements Container {
    private final DummyBinder b;
    public DummyContainer(DummyBinder binder){
        this.b = binder;
    }
    @Override
    public <T> T getComponent(Class<T> clazz) {
        if (b.instances.containsKey(clazz)) {
            return (T) b.instances.get(clazz);
        }
        if (b.implementations.containsKey(clazz)) {
            return getComponent((Class <T>) b.implementations.get(clazz));
        }
        try {
            T ex = null;
            for(Constructor<?> constructor: clazz.getConstructors()){
                if(constructor.isAnnotationPresent(Inject.class)){
                    Object[] parameters = new Object[constructor.getParameterCount()];
                    for(int i = 0; i < constructor.getParameters().length; i++){
                        parameters[i] = getComponent(constructor.getParameters()[i].getType());
                    }
                    ex= (T) constructor.newInstance(parameters);
                }
            }
            if (ex == null) {
                ex = clazz.newInstance();
            }
            if(clazz.isAnnotationPresent(Singleton.class)){
                b.bind(clazz,ex);
            }
            return ex;
        } catch(InvocationTargetException | InstantiationException | IllegalAccessException e){
            throw new RuntimeException(e);
        }
    }
}

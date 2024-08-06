package reflection.metamodel.beanmanager;

import reflection.metamodel.annotation.Inject;
import reflection.metamodel.annotation.Provides;
import reflection.metamodel.provider.H2ConnectionProvider;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class BeanManager {

    private Map<Class<?>, Supplier<?>> registry = new HashMap<>();

    private BeanManager() {
        List<Class<?>> providers = List.of(H2ConnectionProvider.class);

        for (var clzz : providers) {
            Method[] methods = clzz.getDeclaredMethods();
            for (var method : methods) {
                Provides provides = method.getAnnotation(Provides.class);
                if (provides != null) {
                    Class<?> returnType = method.getReturnType();
                    Supplier<?> supplier = () -> {
                        try {
                            if (!Modifier.isStatic(method.getModifiers())) {
                                Object object = clzz.getConstructor().newInstance();
                                return method.invoke(object);
                            } else {
                                return method.invoke(null);
                            }
                        } catch (Exception exception) {
                            throw new RuntimeException(exception);
                        }

                    };
                    registry.put(returnType, supplier);
                }
            }
        }
    }

    public static BeanManager getInstance() {
        return new BeanManager();
    }


    public <T> T getInstance(Class<T> clzz) {
        try {
            T t = clzz.getConstructor().newInstance();
            Field[] fields = clzz.getDeclaredFields();
            for (Field field : fields) {
                Inject inject = field.getAnnotation(Inject.class);
                if (inject != null) {
                    field.setAccessible(true);
                    Class<?> injectedFieldType = field.getType();
                    Object valueToInject = registry.get(injectedFieldType).get();
                    field.set(t, valueToInject);
                }
            }
            return t;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}

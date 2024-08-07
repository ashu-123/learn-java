package reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.function.Predicate;

public class LearnReflection {

    public static void main(String[] args) throws Exception{
        Class<?> personClass = Class.forName("reflection.model.Person");
        System.out.println(personClass);

        Field[] fields = personClass.getFields();
        System.out.println("Fields:-");
        System.out.println(Arrays.toString(fields));

        Field[] declaredFields = personClass.getDeclaredFields();
        System.out.println("Declared Fields:-");
        System.out.println(Arrays.toString(declaredFields));

        Method[] methods = personClass.getMethods();
        System.out.println("Methods:-");
        for (Method method : methods) {
            System.out.println(method);
        }

        Method[] declaredMethods = personClass.getDeclaredMethods();
        System.out.println("Declared Methods:-");
        for(Method method : declaredMethods) {
            System.out.println(method);
        }

        System.out.println("Non-Static Methods:-");

        Arrays.stream(declaredMethods)
                .filter(Predicate.not(method -> Modifier.isStatic(method.getModifiers())))
                .forEach(System.out::println);
    }
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class C {

    public static void main(String[] args) {
        System.out.println(getInfo());
//        List<T> list = new ArrayList<>();
//        A a = new A();
//        list.add(a);
//        B b = new B(1, "aSHU", 56);
//        list.add(b);
//        System.out.println(a.getId() + a.getName());
    }
    private static  String getInfo() {
        A b = new B(1, "aSHU", 56);
        return b.sayHello();
    }


}

import sealed_classes.FirstSealed;
import sealed_classes.SealedClass2;
import sealed_classes.SealedClass1;

public class Base {
    public static void main(String[] args) {

    }

    private static void greet(FirstSealed message) {
        switch (message) {
            case SealedClass1 sealedClass1 -> System.out.println("Sealed class 1");
            case SealedClass2 sealedClass2 -> System.out.println("Sealed class 2");
            case default -> System.out.println("default");
        }
    }
}

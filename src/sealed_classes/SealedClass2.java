package sealed_classes;

public record SealedClass2(int s, int t)  implements FirstSealed {

    private static final String TR = "DD";

    public String finale() {
        return "re";
    }

    @Override
    public String countChars() { return "Sealed class 2";}
}

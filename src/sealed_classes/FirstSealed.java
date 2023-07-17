package sealed_classes;

public sealed interface FirstSealed permits SealedClass1, SealedClass2 {

    public String countChars();
}


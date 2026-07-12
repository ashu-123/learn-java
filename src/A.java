public class A {

    private int id;
    private String name = "Pawan";

    A() {

    }

    A(int id, String name) {
        this.id=id;
        this.name=name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public String sayHello() {
        return "Hello";
    }

    public static void main(String[] args) {
        for(;;) System.out.println("jello");
    }


}

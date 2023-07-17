package records;

public record First(int number) {

    public void printNumber() {
        System.out.println(this.number);
    }
}

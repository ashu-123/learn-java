public class B extends A{

    private int phone;

    B(int id, String name, int phone) {
        super(id, name);
        this.phone=phone;
    }

    public int getPhone() {
        return phone;
    }
    @Override
    public String sayHello() {
        return super.sayHello()+" Ashutosh";
    }
}

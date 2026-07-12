package model;

public class Animal {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sound() {
        System.out.println("Animal makes sound");
    }
}

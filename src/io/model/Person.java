package io.model;

import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Person implements Serializable {

    transient String name;

    transient int age;

    Person() { }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws Exception {
        DataOutputStream dataOutputStream = new DataOutputStream(objectOutputStream);
        dataOutputStream.writeUTF(name + "::" + age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

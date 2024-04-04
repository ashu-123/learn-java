package io.model;

import java.io.*;

public class Person implements Serializable{

    String name;

    int age;

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

//    private void writeObject(ObjectOutputStream objectOutputStream) throws Exception {
//        DataOutputStream dataOutputStream = new DataOutputStream(objectOutputStream);
//        dataOutputStream.writeUTF(name + "::" + age);
//    }
//
//    private void readObject(ObjectInputStream objectInputStream) throws Exception {
//        DataInputStream dataInputStream = new DataInputStream(objectInputStream);
//        var data = dataInputStream.readUTF();
//        String[] fields = data.split("::");
//        this.name = fields[0];
//        this.age = Integer.parseInt(fields[1]);
//    }

    private Object writeReplace() throws ObjectStreamException {
        return new PersonProxy(name + ":-:" + age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

package io.model;

import java.io.*;

public class Student implements Externalizable {

    private static final long serialVersionUID = -1028138726556639756L;

    String name;

    int age;

    public Student() { }

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, int age) {
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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        String data = name + "::" + age;
        System.out.println(data);
        out.write(data.getBytes());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        byte[] buffer = new byte[1024];
        int read = in.read(buffer);
        String content = new String(buffer, 0, read);
        this.name = content.split("::")[0];
        this.age = Integer.parseInt(content.split("::")[1]);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

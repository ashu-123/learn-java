package io.model;

import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

public class PersonProxy implements Serializable {

    String name;

    private Object readResolve() throws ObjectStreamException {
        String[] fields = this.name.split(":-:");
        var name = fields[0];
        var age = Integer.parseInt(fields[1]);
        return new Person(name, age);
    }

    PersonProxy() { }

    PersonProxy(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PersonProxy{" +
                "name='" + name + '\'' +
                '}';
    }
}

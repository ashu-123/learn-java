package io.model;

import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class PersonProxy implements Serializable {

    String name;

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

package utils;

import model.Person;

import java.util.List;

public class PersonUtils {

    public static List<Person> getPersons() {
        return List.of(new Person("Ashutosh", 24),
                new Person("Anurag", 30),
                new Person("Naveen", 62),
                new Person("Mamta", 62),
                new Person("H.S Shukla", 94));
    }
}

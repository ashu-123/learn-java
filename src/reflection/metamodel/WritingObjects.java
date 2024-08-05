package reflection.metamodel;

import reflection.metamodel.model.Person;
import reflection.metamodel.orm.EntityManager;

import java.sql.SQLException;

public class WritingObjects {

    public static void main(String[] args) throws SQLException, IllegalAccessException {
        EntityManager<Person> entityManager = EntityManager.of(Person.class);

        Person ashu = new Person("Ashu", 27);
        Person ashutosh = new Person("Ashutosh", 27);
        Person anurag = new Person("Anurag", 36);
        Person mamta = new Person("Mamta", 57);

        System.out.println(ashu);
        System.out.println(ashutosh);
        System.out.println(anurag);
        System.out.println(mamta);

        System.out.println("Writing to the database:");

        entityManager.persist(ashu);
        entityManager.persist(ashutosh);
        entityManager.persist(anurag);
        entityManager.persist(mamta);

        System.out.println(ashu);
        System.out.println(ashutosh);
        System.out.println(anurag);
        System.out.println(mamta);

    }
}

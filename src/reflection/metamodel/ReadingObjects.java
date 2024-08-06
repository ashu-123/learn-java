package reflection.metamodel;

import reflection.metamodel.model.Person;
import reflection.metamodel.orm.EntityManager;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class ReadingObjects {

    public static void main(String[] args) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        EntityManager<Person> entityManager = EntityManager.of(Person.class);

        Person ashu = entityManager.find(Person.class, 1L);
        Person ashutosh = entityManager.find(Person.class, 2L);
        Person anurag = entityManager.find(Person.class, 3L);
        Person mamta = entityManager.find(Person.class, 4L);

        System.out.println(ashu);
        System.out.println(ashutosh);
        System.out.println(anurag);
        System.out.println(mamta);
    }
}

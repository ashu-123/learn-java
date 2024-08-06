package reflection.metamodel;

import reflection.metamodel.beanmanager.BeanManager;
import reflection.metamodel.model.Person;
import reflection.metamodel.orm.EntityManager;
import reflection.metamodel.orm.ManagedEntityManager;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class ReadingObjects {

    public static void main(String[] args) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        BeanManager beanManager = BeanManager.getInstance();

        EntityManager<Person> entityManager = beanManager.getInstance(ManagedEntityManager.class);

        Person ashu = entityManager.find(Person.class, 5L);
        Person ashutosh = entityManager.find(Person.class, 2L);
        Person anurag = entityManager.find(Person.class, 3L);
        Person mamta = entityManager.find(Person.class, 4L);

        System.out.println(ashu);
        System.out.println(ashutosh);
        System.out.println(anurag);
        System.out.println(mamta);
    }
}

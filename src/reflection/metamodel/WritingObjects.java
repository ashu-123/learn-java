package reflection.metamodel;

import reflection.metamodel.beanmanager.BeanManager;
import reflection.metamodel.model.Person;
import reflection.metamodel.orm.EntityManager;
import reflection.metamodel.orm.ManagedEntityManager;

import java.sql.SQLException;

public class WritingObjects {

    public static void main(String[] args) throws SQLException, IllegalAccessException {

        BeanManager beanManager = BeanManager.getInstance();

        EntityManager<Person> entityManager = beanManager.getInstance(ManagedEntityManager.class);

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

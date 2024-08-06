package reflection.metamodel.orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2EntityManager<T> extends AbstractEntityManager<T> {

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:h2:~/Downloads\\ashu\\ashu\\learning\\learn-java-18\\db-files\\db-learning",
                "sa",
                "");
    }
}

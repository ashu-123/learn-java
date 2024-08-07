package reflection.metamodel.provider;

import reflection.metamodel.annotation.Provides;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2ConnectionProvider {

    @Provides
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:h2:~/Downloads\\ashu\\ashu\\learning\\learn-java-18\\db-files\\db-learning",
                "sa",
                "");
    }
}

package reflection.metamodel.orm;

import reflection.metamodel.util.ColumnField;
import reflection.metamodel.util.MetaModel;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.concurrent.atomic.AtomicLong;

public class EntityManagerImpl<T> implements EntityManager<T>{

    private AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public void persist(T t) throws SQLException, IllegalAccessException {
        MetaModel metaModel = MetaModel.of(t.getClass());
        String sql = metaModel.buildInsertRequest();
        PreparedStatement statement = prepareStatementWith(sql).andParameters(t);
        statement.executeUpdate();
    }

    @Override
    public T find(Class<T> clzz, Object primaryKey) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        MetaModel metaModel = MetaModel.of(clzz);
        String sql = metaModel.buildSelectRequest();
        PreparedStatement preparedStatement = prepareStatementWith(sql).andPrimaryKey(primaryKey);
        ResultSet resultSet = preparedStatement.executeQuery();
        return buildInstanceFrom(clzz, resultSet);
    }

    private PreparedStatementWrapper prepareStatementWith(String sql) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:h2:~/Downloads\\ashu\\ashu\\learning\\learn-java-18\\db-files\\db-learning",
                "sa",
                "");

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        return new PreparedStatementWrapper(preparedStatement);
    }

    private T buildInstanceFrom(Class<?> clzz, ResultSet resultSet) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {

        MetaModel metaModel = MetaModel.of(clzz);
        T t = (T) clzz.getConstructor().newInstance();
        Field primaryKeyField = metaModel.getPrimaryKey().getField();
        String primaryKeyColumnName = metaModel.getPrimaryKey().getName();
        Class<?> primaryKeyType = primaryKeyField.getType();

        resultSet.next();

        if(primaryKeyType == long.class) {
            long primaryKey = resultSet.getInt(primaryKeyColumnName);
            primaryKeyField.setAccessible(true);
            primaryKeyField.set(t, primaryKey);
        }

        for(ColumnField columnField : metaModel.getColumns()) {
            Field field = columnField.getField();
            Class<?> fieldType = columnField.getType();
            field.setAccessible(true);
            String columnName = columnField.getName();

            if(fieldType == int.class) {
                int val = resultSet.getInt(columnName);
                field.set(t, val);
            }
            else if(fieldType == String.class) {
                String val = resultSet.getString(columnName);
                field.set(t, val);
            }
        }

        return t;
    }

    private class PreparedStatementWrapper {

        private PreparedStatement preparedStatement;

        public PreparedStatementWrapper(PreparedStatement preparedStatement) {
            this.preparedStatement = preparedStatement;
        }

        public PreparedStatement andParameters(T t) throws SQLException, IllegalAccessException {
            MetaModel metaModel = MetaModel.of(t.getClass());
            Class<?> primaryKeyType = metaModel.getPrimaryKey().getType();
            if(primaryKeyType == long.class) {
                long id = idGenerator.incrementAndGet();
                preparedStatement.setLong(1, id);
                Field field = metaModel.getPrimaryKey().getField();
                field.setAccessible(true);;
                field.set(t, id);
            }

            for(int columnIndex=0;columnIndex<metaModel.getColumns().size();columnIndex++) {
                ColumnField columnField = metaModel.getColumns().get(columnIndex);
                Class<?> columnFieldType = columnField.getType();
                Field field = columnField.getField();
                field.setAccessible(true);
                var val = field.get(t);
                if(columnFieldType == int.class) {
                    preparedStatement.setInt(columnIndex+2, (int) val);
                }
                else if(columnFieldType == String.class) {
                    preparedStatement.setString(columnIndex+2, (String) val);
                }
            }
            return preparedStatement;
        }

        public PreparedStatement andPrimaryKey(Object primaryKey) throws SQLException{
            if (primaryKey.getClass() == Long.class) {
                preparedStatement.setLong(1, (Long) primaryKey);
            }
            return preparedStatement;
        }
    }
}

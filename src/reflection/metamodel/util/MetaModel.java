package reflection.metamodel.util;

import reflection.metamodel.annotation.Column;
import reflection.metamodel.annotation.PrimaryKey;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MetaModel {

    private Class<?> clzz;

    public MetaModel(Class<?> clzz) {
        this.clzz = clzz;
    }

    public static MetaModel of(Class<?> clzz) {
        return new MetaModel(clzz);
    }

    public PrimaryKeyField getPrimaryKey() {

        Field[] fields = clzz.getDeclaredFields();
        for (Field field : fields) {
            PrimaryKey primaryKey = field.getAnnotation(PrimaryKey.class);
            if (primaryKey != null) {
                return new PrimaryKeyField(field);
            }
        }

        throw new IllegalArgumentException("No primary key found in class");
    }

    public List<ColumnField> getColumns() {

        return Arrays.stream(clzz.getDeclaredFields())
                .filter(field -> field.getAnnotation(Column.class) != null)
                .map(ColumnField::new)
                .collect(Collectors.toList());

    }

    public String buildInsertRequest() {

        var primaryKeyColumnName = getPrimaryKey().getName();
        var columnNames = new java.util.ArrayList<>(getColumns().stream().map(ColumnField::getName).toList());
        columnNames.add(0, primaryKeyColumnName);
        String columnElement = String.join(",", columnNames);

        int numberOfColumns = columnNames.size();
        String questionMarkElement = IntStream.range(0, numberOfColumns)
                .mapToObj(index -> "?")
                .collect(Collectors.joining(", "));

        return "insert into " +
                this.clzz.getSimpleName() +
                " (" + columnElement +
                ") values (" +
                questionMarkElement +
                ")";
    }
}

package reflection.metamodel.util;

import reflection.metamodel.annotation.Column;
import reflection.metamodel.annotation.PrimaryKey;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MetaModel<T> {

    private Class<T> clzz;

    public MetaModel(Class<T> clzz) {
        this.clzz = clzz;
    }

    public static <T> MetaModel<T> of(Class<T> clzz) {
        return new MetaModel<>(clzz);
    }

    public PrimaryKeyField getPrimaryKey() {

        Field[] fields = clzz.getDeclaredFields();
        for (Field field : fields) {
            PrimaryKey primaryKey = field.getAnnotation(PrimaryKey.class);
            if (primaryKey!=null) {
                return new PrimaryKeyField(field);
            }
        }

        throw new IllegalArgumentException("No primary key found in class");
    }

    public List<ColumnField> getColumns() {

        return Arrays.stream(clzz.getDeclaredFields())
                .filter(field -> field.getAnnotation(Column.class)!=null)
                .map(ColumnField::new)
                .collect(Collectors.toList());

    }
}

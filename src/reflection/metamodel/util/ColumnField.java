package reflection.metamodel.util;

import reflection.metamodel.annotation.Column;

import java.lang.reflect.Field;

public class ColumnField {

    private Field field;

    public ColumnField(Field field) {
        this.field = field;
    }

    public String getName() { return this.field.getAnnotation(Column.class).name(); }

    public Class<?> getType() { return  this.field.getType(); }

    public Field getField() { return this.field; }
}

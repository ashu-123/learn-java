package reflection.metamodel.util;

import reflection.metamodel.annotation.PrimaryKey;

import java.lang.reflect.Field;

public class PrimaryKeyField {

    private Field field;

    public PrimaryKeyField(Field field) {
        this.field = field;
    }

    public String getName() { return this.field.getAnnotation(PrimaryKey.class).name(); }

    public Class<?> getType() { return  this.field.getType(); }

    public Field getField() { return this.field; }
}

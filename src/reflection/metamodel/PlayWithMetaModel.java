package reflection.metamodel;

import reflection.metamodel.model.Person;
import reflection.metamodel.util.ColumnField;
import reflection.metamodel.util.MetaModel;
import reflection.metamodel.util.PrimaryKeyField;

import java.util.List;

public class PlayWithMetaModel {

    public static void main(String[] args) {

        MetaModel<Person> metaModel = MetaModel.of(Person.class);

        PrimaryKeyField primaryKeyField = metaModel.getPrimaryKey();
        List<ColumnField> columnFields = metaModel.getColumns();

        System.out.println("Primary Key name:- " + primaryKeyField.getName() +
                ", type:- " + primaryKeyField.getType().getSimpleName());

        columnFields.forEach(columnField -> System.out.println("Column field key name:- " + columnField.getName() +
                ", type:- " + columnField.getType().getSimpleName()));
    }
}

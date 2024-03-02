package garbage_collector.reference_classes;

import java.util.Date;
import java.util.WeakHashMap;

public class WeakReferencing {

    public static void main(String[] args) {
        WeakHashMap<People, PersonMetaData> weakHashMap = new WeakHashMap<People, PersonMetaData>();
        People kevin = new People();
        weakHashMap.put(kevin, new PersonMetaData());

        PersonMetaData p = weakHashMap.get(kevin);

        System.out.println(p);

        kevin = null;
        System.gc();

        if (weakHashMap.containsValue(p)) {
            System.out.println("Still contains key");
        } else {
            System.out.println("Key gone");
        }
    }
}

final class Person {

}

class PersonMetaData {
    Date date;

    PersonMetaData() {
        date = new Date();
    }

    @Override
    public String toString() {
        return "PersonMetaData {" +
                "date=" + date +
                '}';
    }
}
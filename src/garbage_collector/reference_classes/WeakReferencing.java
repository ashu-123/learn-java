package garbage_collector.reference_classes;

import java.util.Date;
import java.util.WeakHashMap;

public class WeakReferencing {

    public static void main(String[] args) {
        WeakHashMap<Student, StudentMetaData> weakHashMap = new WeakHashMap<Student, StudentMetaData>();
        Student kevin = new Student();
        weakHashMap.put(kevin, new StudentMetaData());

        StudentMetaData p = weakHashMap.get(kevin);

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

final class Student {

}

class StudentMetaData {
    Date date;

    StudentMetaData() {
        date = new Date();
    }

    @Override
    public String toString() {
        return "StudentMetaData {" +
                "date=" + date +
                '}';
    }
}
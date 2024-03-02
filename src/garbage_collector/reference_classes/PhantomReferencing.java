package garbage_collector.reference_classes;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;

public class PhantomReferencing {

    public static void main(String[] args) {
        ReferenceQueue<People> queue = new ReferenceQueue<People>();
        ArrayList<FinalizePerson> list = new ArrayList<FinalizePerson>();
        ArrayList<People> people = new ArrayList<People>();

        for (int i = 0; i< 10; i++){
            People p = new People();
            people.add(p);
            list.add(new FinalizePerson(p, queue));
        }

        people = null;
        System.gc();

        for (PhantomReference<People> reference : list) {
            System.out.println(reference.isEnqueued());
        }

        Reference<? extends People> referenceFromQueue;
        while ((referenceFromQueue = queue.poll()) != null) {
            ((FinalizePerson) referenceFromQueue).cleanup();
        }

    }
}

class FinalizePerson extends PhantomReference<People>{

    public FinalizePerson(People referent, ReferenceQueue<? super People> q) {
        super(referent, q);
    }

    public void cleanup() {
        System.out.println("person is finalizing resources");
    }

}

class Person {
}
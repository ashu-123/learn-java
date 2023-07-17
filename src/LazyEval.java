import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class LazyEval {

    public static void main(String[] args) {
        var numbers = List.of(1,2,3,4,5,6,7,8,9,10);
        int[] arr = {2};

        int i = 4;
        var multiplyByI = numbers.stream().map(number -> number * i);

        var firstEven = LongStream.range(1,20).sequential()
                .filter(e -> e % 2 == 0)
//                .peek(e -> System.out.println("element = " + e + "thread = " + Thread.currentThread().getName()))
                .map(e -> e * arr[0]);
        arr[0] = 20;
        System.out.println(firstEven.findFirst());
    }
}

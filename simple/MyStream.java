import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MyStream {
    public static void main(String[] args) {
        /*
        given an array  [4,2,1,3,1,2,4,0,4]
        resort, base on frequent,bigger first, if same, on value, littler first,
        result is [4,4,4,1,1,2,2,0,3]
         */
        int[] arr = new int[]{4,2,1,3,1,2,4,0,4};
        System.out.println(Arrays.toString(arr));
        List<Integer> collect = Arrays.stream(arr).boxed()
                    .collect(Collectors.groupingBy(a->a, Collectors.counting()))
                    .entrySet().stream()
                    .sorted((e1,e2)-> {
                        if(e1.getValue().equals(e2.getValue())) {
                             return Integer.compare(e1.getKey(), e2.getKey()) ;
                        }
                        return Long.compare(e2.getValue(), e1.getValue()) ;
                    })
                    .flatMap(e-> Collections.nCopies(e.getValue().intValue(), e.getKey().intValue()).stream())
                    .collect(Collectors.toList());
        System.out.println(collect);
    }
}

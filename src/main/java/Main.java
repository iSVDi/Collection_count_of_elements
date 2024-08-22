import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


interface Filter<T> {
    T apply(T o);
}

class StringFilter implements Filter<String> {
    @Override
    public String apply(String o) {
        return o.toUpperCase();
    }
}

class NumberFilter implements Filter<Integer> {
    @Override
    public Integer apply(Integer o) {
        return -o;
    }
}

public class Main {

    public static void main(String[] args) {
        ArrayList<String> stringArrayList = new ArrayList<>(Arrays.asList("hello", "world", "cinema"));
        ArrayList<Integer> integerArrayList = new ArrayList<>(Arrays.asList(1, 2, 4, 5));

        var filteredStrings = filter(stringArrayList, new StringFilter());
        var filteredInts = filter(integerArrayList, new NumberFilter());

        System.out.println(filteredStrings);
        System.out.println(filteredInts);

    }

    static <T> List<T> filter(List<T> a, Filter<T> filterImp) {
        return a.stream().map(filterImp::apply).toList();
    }


}







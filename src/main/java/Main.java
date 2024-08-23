import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        List<String> stringList = Arrays.asList("a", "a", "a", "asd", "asd", "q", "w", "r");
        List<Integer> intList = Arrays.asList(1,1,1,2,2,34,56,78,89,0,0,0,0,0,0,0,0);
        Map stringListMappedCount =  countOfElements(stringList);
        Map intListMappedCount =  countOfElements(intList);

        System.out.println(stringListMappedCount);
        System.out.println(intListMappedCount);
    }


    static Map countOfElements(List list) {
        Map<Object, Integer> res = new HashMap<>();
        list.forEach(t -> {
            if (res.containsKey(t))
                res.replace(t, res.get(t) + 1);
            else
                res.put(t, 1);
        });
        return res;
    }
}
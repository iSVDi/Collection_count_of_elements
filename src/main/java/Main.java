import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * Создайте коллекцию студентов, где каждый студент содержит информацию о предметах, которые он изучает, и его оценках по этим предметам.
 * Используйте Parallel Stream для обработки данных и создания Map, где ключ - предмет, а значение - средняя оценка по всем студентам.
 * Выведите результат: общую Map с средними оценками по всем предметам.
 */

class Student {
    private String name;
    private Map<String, Integer> grades;

    public Student(String name, Map<String, Integer> grades) {
        this.name = name;
        this.grades = grades;
    }

    public Map<String, Integer> getGrades() {
        return grades;
    }
}


public class Main {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Student1", Map.of("Math", 90, "Physics", 85)),
                new Student("Student2", Map.of("Math", 95, "Physics", 88)),
                new Student("Student3", Map.of("Math", 88, "Chemistry", 92)),
                new Student("Student4", Map.of("Physics", 78, "Chemistry", 85))
        );

        Map<String, ArrayList<Integer>> subjectScoresMap = new HashMap<>();

        students.parallelStream()
                .flatMap(e -> e.getGrades().entrySet().stream())
                .forEach(e -> {
                    if (!subjectScoresMap.containsKey(e.getKey())) {
                        subjectScoresMap.put(e.getKey(), new ArrayList<>());
                    }
                    ArrayList<Integer> current = subjectScoresMap.get(e.getKey());
                    current.add(e.getValue());
                });

        List<Map.Entry<String, BigDecimal>> res = subjectScoresMap.entrySet().parallelStream().flatMap(e -> {
            BigDecimal scoreSum = BigDecimal.valueOf(e.getValue().parallelStream().reduce(0, Integer::sum));
            BigDecimal scoreCount = BigDecimal.valueOf(e.getValue().size());

            BigDecimal middleScore = scoreSum.divide(scoreCount, RoundingMode.UP);
            Map<String, BigDecimal> map = new HashMap<>();
            map.put(e.getKey(), middleScore);
            return map.entrySet().stream();
        }).toList();
        System.out.println(res);
    }

}




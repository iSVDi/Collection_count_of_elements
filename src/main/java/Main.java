import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

/**
 * Предположим, у нас есть список заказов, и каждый заказ представляет собой продукт и его стоимость.
 * Задача состоит в использовании Stream API и коллекторов для решения следующих задач:
 * <p>
 * Создайте список заказов с разными продуктами и их стоимостями.
 */

class Order {
    private String product;
    private double cost;

    public Order(String product, double cost) {
        this.product = product;
        this.cost = cost;
    }

    public String getProduct() {
        return product;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return product + " " + cost;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );


        // * Группируйте заказы по продуктам.
        var ordersByProducts = orders.stream().collect(groupingBy(Order::getProduct));
        System.out.println(ordersByProducts);

        //  * Для каждого продукта найдите общую стоимость всех заказов.
        Map<String, Double> sumByProduct = ordersByProducts
                .entrySet()
                .stream()
                .collect(toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream().map(Order::getCost).reduce((double) 0, Double::sum)
                ));
        System.out.println(sumByProduct);

       //  * Отсортируйте продукты по убыванию общей стоимости.
        var sortedByCost = orders.stream().sorted( (a, b) -> {
            if (a.getCost() > b.getCost())
                    return -1;
            return 1;
        }).toList();

        System.out.println(sortedByCost);

        /*
         * Выберите три самых дорогих продукта.
         * Выведите результат: список трех самых дорогих продуктов и их общая стоимость.
         */

        var topExpensive = sortedByCost.subList(0, 3);
        var topExpensiveSum = topExpensive.stream()
                .map(Order::getCost)
                .reduce(0.0, Double::sum);

        System.out.println(topExpensive);
        System.out.println(topExpensiveSum);
    }


}




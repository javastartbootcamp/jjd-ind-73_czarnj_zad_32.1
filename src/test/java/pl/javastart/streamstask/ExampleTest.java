package pl.javastart.streamstask;

import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ExampleTest {

    private static List<User> users;
    private static List<Expense> expenses;
    private StreamsTask streamsTask = new StreamsTask();

    @BeforeClass
    public static void setup() {
        users = new ArrayList<>();

        users.add(new User(1L, "Alicja", 20));
        users.add(new User(2L, "Dominik", 15));
        users.add(new User(3L, "Patrycja", 25));
        users.add(new User(4L, "Marcin", 30));
        users.add(new User(5L, "Tomek", 18));
        users.add(new User(6L, "Damian", 26));

        expenses = new ArrayList<>();
        expenses.add(new Expense(1L, "Buty", new BigDecimal("149.99"), ExpenseType.WEAR));
        expenses.add(new Expense(1L, "Sałatka", new BigDecimal("14.99"), ExpenseType.FOOD));
        expenses.add(new Expense(2L, "Bluza", new BigDecimal("100"), ExpenseType.WEAR));
        expenses.add(new Expense(2L, "Skarpetki", new BigDecimal("39"), ExpenseType.WEAR));
        expenses.add(new Expense(2L, "Pizza", new BigDecimal("25"), ExpenseType.FOOD));
    }

    @Test
    public void shouldContainTwoElements() {
        Collection<User> women = streamsTask.findWomen(users);
        assertThat(women).hasSize(2);
    }

    @Test
    public void shouldCalculateProperAverage() {
        Double average = streamsTask.averageMenAge(users);
        assertThat(average).isEqualTo(22.25);
    }

    @Test
    public void shouldReturnMapWithTwoKeys() {
        Map<Long, List<Expense>> groupedExpenses = streamsTask.groupExpensesByUserId(users, expenses);
        assertThat(groupedExpenses.keySet()).hasSize(2);
        assertThat(groupedExpenses.get(1L)).hasSize(2);
        assertThat(groupedExpenses.get(2L)).hasSize(3);
    }

    @Test
    public void shouldReturnMapWithTwoUserKeys() {
        Map<User, List<Expense>> groupedExpenses = streamsTask.groupExpensesByUser(users, expenses);
        assertThat(groupedExpenses.keySet()).hasSize(2);
        assertThat(groupedExpenses.get(users.get(0))).hasSize(2);
        assertThat(groupedExpenses.get(users.get(1))).hasSize(3);
    }

}

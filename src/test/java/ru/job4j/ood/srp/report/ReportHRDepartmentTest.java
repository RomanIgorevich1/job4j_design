package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

public class ReportHRDepartmentTest {

    @Test
    public void whenGenerateSort() {
        MemStore store = new MemStore();
        Calendar hired = new GregorianCalendar(2000, Calendar.FEBRUARY, 1);
        Calendar fired = new GregorianCalendar(2015, Calendar.APRIL, 1);
        Employee worker = new Employee("Ivan", hired, fired, 1000);
        Employee worker2 = new Employee("Roman", hired, fired, 1500);
        Employee worker3 = new Employee("Igor", hired, fired, 2000);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        store.add(worker2);
        store.add(worker3);
        Report engine = new ReportHRDepartment(store, parser);
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        List<Employee> employees = new ArrayList<>(store.findBy(em -> true));
        employees.sort(Comparator.comparingDouble(Employee::getSalary).reversed());
        employees.forEach(employee -> text.append(employee.getName()).append(" ")
                .append(employee.getSalary()).append(" ")
                .append(System.lineSeparator()));
        assertThat(engine.generate(m -> true)).isEqualTo(text.toString());
    }
}
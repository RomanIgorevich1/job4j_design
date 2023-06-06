package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.*;

class ReportDevelopmentDepartmentTest {

    @Test
    public void whenGenerateCSV() {
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
        Report engine = new ReportDevelopmentDepartment(store, parser);
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        store.findBy(em -> true)
                .forEach(employee -> text.append(employee.getName()).append(";")
                        .append(parser.parse(employee.getHired())).append(";")
                        .append(parser.parse(employee.getFired())).append(";")
                        .append(employee.getSalary()).append(" ")
                        .append(System.lineSeparator()));
        assertThat(engine.generate(m -> true)).isEqualTo(text.toString());
    }
}
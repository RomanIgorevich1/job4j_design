package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ReportHRDepartment implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;

    public ReportHRDepartment(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        List<Employee> listEmployee = new ArrayList<>(store.findBy(filter));
        listEmployee.sort(Comparator.comparingDouble(Employee::getSalary).reversed());
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : listEmployee) {
            text.append(employee.getName()).append(" ")
                    .append(employee.getSalary()).append(" ")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}

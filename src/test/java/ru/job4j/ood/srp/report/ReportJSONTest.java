package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import javax.xml.bind.JAXBException;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

public class ReportJSONTest {

    @Test
    public void whenJSONGenerated() throws JAXBException {
        String line = System.lineSeparator();
        String tab = "\t";
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("John Doe", now, now, 5000.0);
        Employee worker2 = new Employee("Jane Smith", now, now, 6000.0);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        store.add(worker2);
        Report engine = new ReportJSON(store, parser);
        String expect = "[\n"
                + "  {\n"
                + "    \"name\": " + "\"John Doe\",\n"
                + "    \"hired\": " + "\"" + parser.parse(worker.getHired()) + "\",\n"
                + "    \"fired\": " + "\"" + parser.parse(worker.getFired()) + "\",\n"
                + "    \"salary\": " + worker.getSalary() + "\n"
                + "  },\n"
                + "  {\n"
                + "    \"name\": " + "\"Jane Smith\",\n"
                + "    \"hired\": " + "\"" + parser.parse(worker2.getHired()) + "\",\n"
                + "    \"fired\": " + "\"" + parser.parse(worker2.getFired()) + "\",\n"
                + "    \"salary\": " + worker2.getSalary() + "\n"
                + "  }\n" + "]";
        assertThat(engine.generate(em -> true)).isEqualTo(expect);
    }
}
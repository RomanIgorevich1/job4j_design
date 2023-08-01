package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import javax.xml.bind.JAXBException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;
public class ReportXMLTest {
    @Test
    public void whenJSONGenerated() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("John Doe", now, now, 5000.0);
        Employee worker2 = new Employee("Jane Smith", now, now, 6000.0);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        String date = format.format(now.getTime());
        store.add(worker);
        store.add(worker2);
        Report engine = new ReportXML(store);
        String expect =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<employee>\n"
                        + "    <employee>\n"
                        + "        <name>" + worker.getName() + "</name>\n"
                        + "        <hired>" + date + "</hired>\n"
                        + "        <fired>" + date + "</fired>\n"
                        + "        <salary>" + worker.getSalary() + "</salary>\n"
                        + "    </employee>\n"
                        + "    <employee>\n"
                        + "        <name>" + worker2.getName() + "</name>\n"
                        + "        <hired>" + date + "</hired>\n"
                        + "        <fired>" + date + "</fired>\n"
                        + "        <salary>" + worker2.getSalary() + "</salary>\n"
                        + "    </employee>\n"
                        + "</employee>\n";
        assertThat(engine.generate(em -> true)).isEqualTo(expect);

    }
}
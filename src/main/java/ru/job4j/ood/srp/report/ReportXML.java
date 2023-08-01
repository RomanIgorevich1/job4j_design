package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.function.Predicate;

public class ReportXML implements Report {
    private final Store store;
    private Marshaller marshaller;

    public ReportXML(Store store) throws JAXBException {
        this.store = store;
        JAXBContext context = JAXBContext.newInstance(Employee.class, EmployeeXml.class);
        marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    }

    @Override
    public String generate(Predicate<Employee> filter) throws JAXBException {
        String result = "";
        try (StringWriter writer = new StringWriter()) {
             marshaller.marshal(new EmployeeXml(store.findBy(filter)), writer);
             result = writer.getBuffer().toString();
        } catch (IOException | JAXBException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    @XmlRootElement(name = "employee")
    public static class EmployeeXml {
        private List<Employee> employee;

        public EmployeeXml() {

        }

        public EmployeeXml(List<Employee> employee) {
            this.employee = employee;
        }

        public List<Employee> getEmployee() {
            return employee;
        }

        public void setEmployee(List<Employee> employee) {
            this.employee = employee;
        }
    }
}

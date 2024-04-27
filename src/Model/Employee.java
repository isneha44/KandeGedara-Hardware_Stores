package Model;

import java.util.Date;

public class Employee {
    private String em_id;
    private String em_name;
    private String em_address;
    private String designation;
    private Date dob;
    private double salary;

    public Employee() {
    }

    public Employee(String em_id, String em_name, String em_address, String designation, Date dob, double salary) {
        this.em_id = em_id;
        this.em_name = em_name;
        this.em_address = em_address;
        this.designation = designation;
        this.dob = dob;
        this.salary = salary;
    }

    public String getEm_id() {
        return em_id;
    }

    public void setEm_id(String em_id) {
        this.em_id = em_id;
    }

    public String getEm_name() {
        return em_name;
    }

    public void setEm_name(String em_name) {
        this.em_name = em_name;
    }

    public String getEm_address() {
        return em_address;
    }

    public void setEm_address(String em_address) {
        this.em_address = em_address;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "em_id='" + em_id + '\'' +
                ", em_name='" + em_name + '\'' +
                ", em_address='" + em_address + '\'' +
                ", designation='" + designation + '\'' +
                ", dob=" + dob +
                ", salary=" + salary +
                '}';
    }
}

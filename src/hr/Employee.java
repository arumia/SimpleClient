/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr;

import java.sql.*;


/**
 *
 * @author thdtm
 */
import java.sql.Timestamp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class Employee {

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Timestamp getHireDate() {
        return hireDate;
    }

    public void setHireDate(Timestamp hireDate) {
        this.hireDate = hireDate;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Float getCommisionPct() {
        return commisionPct;
    }

    public void setCommisionPct(Float commisionPct) {
        this.commisionPct = commisionPct;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    private Integer employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Timestamp hireDate;
    private String jobId;
    private Float salary;
    private Float commisionPct;
    private Integer managerId;
    private Integer departmentId;
    
    public ObservableList<Employee> getAll(Connection conn){
        ObservableList<Employee> listEmployee = FXCollections.observableArrayList();
        
        String sql = "SELECT EMPLOYEE_ID,FIRST_NAME,LAST_NAME,EMAIL,PHONE_NUMBER,HIRE_DATE,JOB_ID,SALARY,"
                + "COMMISSION_PCT,MANAGER_ID,DEPARTMENT_ID FROM EMPLOYEES";
        
        Statement stmt;
        ResultSet rs;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Employee employee = new Employee();
                employee.employeeId = rs.getInt(1);
                employee.firstName = rs.getString(2);
                employee.lastName = rs.getString(3);
                employee.email = rs.getString(4);
                employee.phoneNumber = rs.getString(5);
                employee.hireDate = rs.getTimestamp(6);
                employee.jobId = rs.getString(7);
                employee.salary = rs.getFloat(8);
                employee.commisionPct = rs.getFloat(9);
                employee.managerId = rs.getInt(10);
                employee.departmentId = rs.getInt(11);
                
                System.out.println("EmployeeId: " + employee.getEmployeeId().toString() + " Employee Last Name: " 
                        + employee.getLastName());
                listEmployee.add(employee);
            }
        } catch (SQLException exc) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errots with data access");
            alert.setContentText("Details: " + exc.getMessage());
            alert.showAndWait();
        }
              return listEmployee;
    }

}



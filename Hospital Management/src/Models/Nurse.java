/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Admin
 */
public class Nurse extends Person {
    // Các thông tin của lớp nurse
    protected String department;
    protected String shift;
    protected double salary;
    protected int count = 0;

    public Nurse() {
    }

    public Nurse(String id, String name, int age, String gender,
            String address, String phone, String department, String shift, double salary, int count) {
        super(id, name, age, gender, address, phone);
        this.department = department;
        this.shift = shift;
        this.salary = salary;
        this.count = count;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    // Bộ đếm số lần nurse được chỉ định
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    // Tăng bộ đếm nếu nurse được chỉ định
    public void incNumOfCount() {
        count++; // Tăng count thêm 1
        setCount(count); // Set lại count
    }
    
    @Override
    public String toString() {
        String str = String.format("%s|%s|%d|%s|%s|%s|%s|%s|%f|%d", this.id, this.name,
                this.age, this.gender, this.address, this.phone, this.department, this.shift, this.salary, this.count);
        return str;
    }
    
    
}

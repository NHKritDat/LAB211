/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Businesses;

import Control.Menu;
import Models.Nurse;
import Tools.InputHandle;
import java.util.HashMap;

/**
 *
 * @author Admin
 */
public class NurseList extends HashMap<String, Nurse> {

    public NurseList() {
    }
    
    // Tạo thêm nurse
    public void createNurse() {
        while (true) {
            // Nhập Id theo regex
            String nId = InputHandle.getString("Enter nurse ID: ", "Invalid ID!", "^N\\d*$"); // Regex id: N001 - N1 - N00100
            // Kiểm tra ID đã tồn tại hay chưa, tồn tại thì kết thúc, chưa thì tiếp tục
            if (findByID(nId) != null) {
                System.out.println("Nurse ID already exists!");
            } else {
                String nName = InputHandle.getString("Enter nurse's name: ", "Invalid name!"); // Kiểm tra tên không được bỏ trống
                int nAge = InputHandle.getPositiveInt("Enter nurse's age: ", "Invalid age!"); // Kiểm tra số tuổi dương
                String nGender = InputHandle.getString("Enter nurse's gender: ", "Invalid gender!", "^[Mm][Aa][Ll][Ee]$|^[Ff][Ee][Mm][Aa][Ll][Ee]$"); // Regex male/female
                String nAddress = InputHandle.getString("Enter nurse's address: ", "Invalid address!"); // Kiểm tra địa chỉ không được để trống
                String nPhone = InputHandle.getString("Enter nurse's phone number: ", "Invalid phone number!", "^0[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]$"); // Regex kiểm tra số điện thoại hợp lệ
                String nDepartment = InputHandle.getString("Enter nurse's department: ", "Invalid department!", 3, 50); // Giới hạn ký tự của department
                String nShift = InputHandle.getString("Enter nurse's shift: ", "Invalid shift!", "^[Dd][Aa][Yy]$|^[Nn][Ii][Gg][Hh][Tt]$"); // Regex day/night
                double nSalary = InputHandle.getDouble("Enter nurse's salary: ", "Invalid salary!"); // Kiểm tra số thực dương
                this.put(nId, new Nurse(nId, nName, nAge, nGender, nAddress, nPhone, nDepartment, nShift, nSalary, 0)); // Set các giá trị theo key staffID
            }
            // Hỏi có tiếp tục thêm nurse không?
            if (!Menu.getYesNo("Keep adding new nurses?")) {
                return;
            }
        }
    }
    
    //Tìm nurse theo tên
    public void findNurse() {
        String nName = InputHandle.getString("Enter nurse's name: ", "Invalid name!"); // Nhập tên nurse cần tìm
        boolean check = true; // boolean kiểm tra có nurse không
        for (Entry<String, Nurse> entry : this.entrySet()) { // Gọi ra từng entry tồn tại đang tồn tại
            String name = entry.getValue().getName(); // Lấy value name từ key
            if (name.equals(nName) || name.contains(nName)){ // Nếu chứa 1 phần hoặc trùng với tên nurse nhập ở trên, in ra thông tin nurse đó
                System.out.println(entry.getValue().toString());
                check = false; // Có nurse set check = false
            }
        }
        if (check) { // Nếu không có nurse (check = true) in ra message
            System.out.println("Nurse does not exist!");
        }
    }
    
    // Cập nhật nurse
    public void updateNurse() {
        String nId = InputHandle.getString("Enter nurse ID: ", "Invalid ID!", "^N\\d*$"); // Nhập id nurse cần cập nhật
        if (findByID(nId) == null) { // Tìm thông tin nurse tồn tại hay không
            System.out.println("The nurse does not exist!");
        } else {
            System.out.println("Nurse's info: " + findByID(nId).toString()); // In ra thông tin của nurse cần cập nhật
            System.out.println("Update the nurse: ");
            // Nhập data không điều kiện
            String nName = InputHandle.getString("Enter nurse's name: ");
            int nAge = InputHandle.getInt("Enter nurse's age: ");
            String nGender = InputHandle.getString("Enter nurse's gender: ");
            String nAddress = InputHandle.getString("Enter nurse's address: ");
            String nPhone = InputHandle.getString("Enter nurse's phone number: ");
            String nDepartment = InputHandle.getString("Enter nurse's department: ");
            String nShift = InputHandle.getString("Enter nurse's shift: ");
            double nSalary = InputHandle.getDouble("Enter nurse's salary: ");
            // Kiểm tra valid data mới
            try {
                // Kiểm tra điều kiện của từng thông tin được nhập
                if (nName.trim().isEmpty() || nAge <= 0 || !nGender.matches("^[Mm][Aa][Ll][Ee]$|^[Ff][Ee][Mm][Aa][Ll][Ee]$") 
                        || nAddress.trim().isEmpty() || !nPhone.matches("^0[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]$")
                        || !nDepartment.matches("^\\w.{2,47}$") || !nShift.matches("^[Dd][Aa][Yy]$|^[Nn][Ii][Gg][Hh][Tt]$") || nSalary < 0) {
                    throw new Exception();
                }
                // Set value mới
                findByID(nId).setName(nName); findByID(nId).setAge(nAge); findByID(nId).setGender(nGender); findByID(nId).setAddress(nAddress);
                findByID(nId).setPhone(nPhone); findByID(nId).setDepartment(nDepartment); findByID(nId).setShift(nShift); findByID(nId).setSalary(nSalary);
                System.out.println("Update success!");
            } catch (Exception e) { // Ném thông báo không thành công
                System.out.println("Update failure!");
            }
        }
    }
    
    // Xóa nurse
    public void deleteNurse() {
        String nId= InputHandle.getString("Enter nurse ID: ", "Invalid ID!", "^N\\d*$"); // Nhập id nurse cần xóa
        if (findByID(nId) == null) { // Kiểm tra nurse có tồn tại không
            System.out.println("The nurse does not exist!");
        } else {
            for (Entry<String, Nurse> entry : this.entrySet()) { // Gọi ra từng entry đang tồn tại
                if (entry.getValue().getId().equals(nId)) { // Kiểm tra id của entry được gọi trùng với id cần tìm
                    if (entry.getValue().getCount() != 0) { // Nếu bộ đếm của nurse khác 0 (đang có task), xóa thất bại
                        System.out.println("Delete failure!");
                    } else {
                        if (Menu.getYesNo("Continue to delete this nurse?")) { // Xác nhận xóa nurse    
                            this.remove(nId);
                            System.out.println("Delete success!");
                            return;
                        } else {
                            System.out.println("Delete failure!");
                            return;
                        }
                    }
                }
            }
        }
    }
    
    // Tìm nurse theo ID
    public Nurse findByID(String nurseId) {
        return this.get(nurseId);
    }
}

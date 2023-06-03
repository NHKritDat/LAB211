/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Businesses;

import Control.Menu;
import Models.Nurse;
import Models.Patient;
import Tools.FileHandle;
import java.util.ArrayList;
import java.util.Map.Entry;

/**
 *
 * @author Admin
 */
public class Hospital {
    
    // Tạo đường dẫn lưu file
    private final String nursePath = "\\src\\files\\nurses.dat";
    private final String patientPath = "\\src\\files\\patients.dat";
    
    NurseList nurseList = new NurseList();
    PatientList patientList = new PatientList();
    
    public Hospital() {
    }
    
    // Tạo thêm nurse
    public void createNurse() {
        nurseList.createNurse();
    }
    
     //Tìm nurse theo tên
    public void findNurse() {
        nurseList.findNurse();
    }
    
    // Cập nhật nurse
    public void updateNurse() {
        nurseList.updateNurse();
    }
    
    // Xóa nurse
    public void deleteNurse() {
        nurseList.deleteNurse();
    }
    
    // Tạo thêm patient
    public void addPatient() {
        patientList.addPatient(nurseList);
    }
    
    // Xuất ra màn hình danh sách patient
    public void displayPatient() {
        patientList.displayPatient();
    }
    
    // Sort danh sách bệnh nhân
    public void sortPatient() {
        patientList.sortPatient();
    }
    
    // Save data
    public void saveData() {
        ArrayList<String> dta = new ArrayList<>(); // tạo ArrayList chứa data
        for (Entry<String, Nurse> entry : nurseList.entrySet()) { // Gọi từng entry thêm value vào data
            dta.add(entry.getValue().toString());
        }
        FileHandle.writeToFile(nursePath, dta); // Đưa data vào file
        dta.clear(); // xóa data đang có trong ArrayList
        // Lặp lại với patient
        for (Entry<String, Patient> entry : patientList.entrySet()) {
            dta.add(entry.getValue().toString());
        }
        FileHandle.writeToFile(patientPath, dta);
        System.out.println("Data saved successfully!");
    }
    
    // Load data
    public void loadData() {
        // Xóa sạch các data đang có
        nurseList.clear();
        patientList.clear();
        ArrayList<String> dta = new ArrayList<>(); // ArrayList chứa data mới
        // Đọc tất cả và thêm vào dta
        dta.addAll(FileHandle.readFromFile(nursePath));
        dta.addAll(FileHandle.readFromFile(patientPath));
        for (String item : dta) { // Gọi từng item
            String lineSplit[] = item.trim().split("\\|"); // Phân ra theo từng "|"
            if (lineSplit[0].matches("^N\\d*$")) { // Nếu trùng với mã staffID, set vào nurseList
                nurseList.put(lineSplit[0],
                        new Nurse(lineSplit[0], lineSplit[1],
                                Integer.parseInt(lineSplit[2]),
                                lineSplit[3], lineSplit[4],
                                lineSplit[5], lineSplit[6],
                                lineSplit[7], Double.parseDouble(lineSplit[8]), Integer.parseInt(lineSplit[9])));
            } else if (lineSplit[0].matches("^P\\d*$")) { // Nếu trùng với patient ID, set vào patientList
                String[] nal = lineSplit[9].split(":"); // Phân ra theo từng ":"
                NurseList nl = new NurseList();
                for (String n : nal) { // Gọi và thêm các giá trị nurseAssigned
                    nl.put(n, nurseList.findByID(n));
                }
                patientList.put(lineSplit[0],
                        new Patient(lineSplit[0], lineSplit[1],
                                Integer.parseInt(lineSplit[2]),
                                lineSplit[3], lineSplit[4],
                                lineSplit[5], lineSplit[6],
                                lineSplit[7], lineSplit[8], nl));
            }
        }
        System.out.println("Load data success!");
    }
    
    // Quit
    public void quitProgram() {
        if (Menu.getYesNo("DO YOU WANT TO EXIT PROGRAM?")) {
            saveData();
            System.exit(0);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Businesses;

import Control.Menu;
import Models.Patient;
import Tools.InputHandle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Admin
 */
public class PatientList extends HashMap<String, Patient> {

    public PatientList() {
    }

    // Tạo thêm patient
    public void addPatient(NurseList nurseList) {
        if (nurseList.isEmpty()) { // Kiểm tra nurselist rỗng
            System.out.println("Do not have any nurse to take care of new patient!");
        } else {
            while (true) {
                // Nhập ID theo regex
                String nId = InputHandle.getString("Enter patient ID: ", "Invalid ID!", "^P\\d*$"); // Regex id: P001 - P1 - P0011000
                // Kiểm tra ID đã tồn tại hay chưa, tồn tại thì kết thúc, chưa thì tiếp tục
                if (findByID(nId) != null) {
                    System.out.println("Patient ID already exists!");
                } else {
                    String nName = InputHandle.getString("Enter patient's name: ", "Invalid name!"); // Kiểm tra tên không được bỏ trống
                    int nAge = InputHandle.getPositiveInt("Enter patient's age: ", "Invalid age!"); // Kiểm tra số tuổi dương
                    String nGender = InputHandle.getString("Enter patient's gender: ", "Invalid gender!", "^[Mm][Aa][Ll][Ee]$|^[Ff][Ee][Mm][Aa][Ll][Ee]$"); // Regex male/female
                    String nAddress = InputHandle.getString("Enter patient's address: ", "Invalid address!"); // Kiểm tra địa chỉ không được để trống
                    String nPhone = InputHandle.getString("Enter patient's phone number: ", "Invalid phone number!", "^0[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]$"); // Regex kiểm tra số điện thoại hợp lệ
                    String nDiagnosis = InputHandle.getString("Enter patient's diagnosis: ", "Invalid diagnosis!"); // Kiểm tra chuẩn đoán không được bỏ trống
                    String nAdmissionDate = InputHandle.getDate("Enter patient admission date: ", "Invalid date!", "dd/mm/yyyy"); // Kiểm tra ngày nhập
                    String nDischargeDate = InputHandle.getDate("Enter patient discharge date: ", "Invalid date!", "dd/mm/yyyy"); // Kiểm tra ngày ra
                    int count = 2; // Đếm số nưrse
                    NurseList nNurseAssigned = new NurseList();
                    while (count > 0) {
                        System.out.print((3 - count) + ". "); // Nurse số...
                        String nNurseID = InputHandle.getString("Enter assigned nurse ID: ", "Invalid ID!", "^N\\d*$"); // Nhập id nurse
                        if (nurseList.containsKey(nNurseID) && !nNurseAssigned.containsKey(nNurseID)) { // Kiểm tra nurse tồn tại trong nurseList và không tồn tại trong nurseAssigned
                            if (nurseList.get(nNurseID).getCount() < 2) { // Nếu bộ đếm nurse trong nurseList không nhiều hơn 2
                                nNurseAssigned.put(nNurseID, nurseList.get(nNurseID)); // Thêm vào nurseAssigned theo khóa staffID
                                count--; // Bộ đếm nurse giảm
                                nurseList.get(nNurseID).incNumOfCount(); // Bộ đếm nurse trong nurseList +1
                            } else {
                                System.out.println("Nurses have been assigned maximum!");
                                return;
                            }
                        } else {
                            System.out.println("Nurse does not exist or is assigned!");
                            return;
                        }
                    }
                    this.put(nId, new Patient(nId, nName, nAge, nGender, nAddress, nPhone, nDiagnosis, nAdmissionDate, nDischargeDate, nNurseAssigned)); // Set value theo key Patient ID
                }
                // Hỏi có tiếp tục thêm nurse không?
                if (!Menu.getYesNo("Keep adding new patients?")) {
                    return;
                }
            }
        }
    }

    // Xuất ra màn hình danh sách patient
    public void displayPatient() {
        String startDate = InputHandle.getDate("Enter start date: ", "Invalid date!", "dd/mm/yyyy"); // Nhập ngày đầu
        String endDate = InputHandle.getDate("Enter end date: ", "Invalid date!", "dd/mm/yyyy"); // Nhập ngày cuối
        System.out.println("LIST OF PATIENTS");
        System.out.println("Start date: " + startDate);
        System.out.println("End date: " + endDate);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("| No. | Patient Id | Admission Date | Full name        | Phone     | Diagnosis |");
        System.out.println("--------------------------------------------------------------------------------");
        int count = 1; // số đếm No.
        for (Entry<String, Patient> entry : this.entrySet()) { // Gọi từng entry của patient
            if (InputHandle.toDate(entry.getValue().getAdmissionDate(), "dd/MM/yyyy").compareTo(InputHandle.toDate(startDate, "dd/MM/yyyy")) >= 0
                    && InputHandle.toDate(entry.getValue().getAdmissionDate(), "dd/MM/yyyy").compareTo(InputHandle.toDate(endDate, "dd/MM/yyyy")) <= 0) { // Chuyển định dạnh date từ String sang Date và so sánh date
                String str = String.format("|%5d", count);
                System.out.print(str);
                String info = String.format("|%12s|%16s|%18s|%11s|%11s|", entry.getValue().getId(),
                        entry.getValue().getAdmissionDate(), entry.getValue().getName(),
                        entry.getValue().getPhone(), entry.getValue().getDiagnosis());
                System.out.println(info); // In ra thông tin của patient phù hợp
                count++; // tăng số đếm
            }
        }
        System.out.println("--------------------------------------------------------------------------------");
    }

    // Sort patient
    public void sortPatient() {
        System.out.println("LIST OF PATIENTS");
        Comparator<Patient> comparator; // Tạo sort
        boolean sel1 = Menu.getSort("Name", "ID"); // Menu chọn loại sort
        boolean sel2 = Menu.getSort("ASC", "DESC");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("| No. | Patient Id | Admission Date | Full name        | Phone     | Diagnosis |");
        if (sel1) {
            if (sel2) {
                comparator = Comparator.comparing(Patient::getName); // Sort theo name tăng dần
            } else {
                comparator = Comparator.comparing(Patient::getName); 
                comparator = comparator.reversed(); // Sort theo name giảm dần
            }
        } else {
            if (sel2) {
                comparator = Comparator.comparing(Patient::getId); // Sort theo ID tăng dần
            } else {
                comparator = Comparator.comparing(Patient::getId);
                comparator = comparator.reversed(); // Sort theo ID giảm dần
            }
        }
        List<Patient> sortPatients = new ArrayList<>(this.values()); // List chứa các patient
        sortPatients.sort(comparator); // Sort patient theo comparator
        // Xuất thông tin sau khi sort
        int count = 1;
        for (Patient patient : sortPatients) {
            String str = String.format("|%5d", count);
            System.out.print(str);
            String info = String.format("|%12s|%16s|%18s|%11s|%11s|", patient.getId(),
                    patient.getAdmissionDate(), patient.getName(), patient.getPhone(),
                    patient.getDiagnosis());
            System.out.println(info);
            count++;
        }
        System.out.println("--------------------------------------------------------------------------------");
    }

    // Tìm patient theo ID
    private Patient findByID(String patientId) {
        return this.get(patientId);
    }
}

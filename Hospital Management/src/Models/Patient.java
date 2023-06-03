/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Businesses.NurseList;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Admin
 */
public class Patient extends Person {
    // Các thông tin của lớp patient
    protected String diagnosis;
    protected String admissionDate;
    protected String dischargeDate;
    protected NurseList nurseAssigned;

    public Patient() {
    }

    public Patient(String id, String name, int age, String gender,
            String address, String phone,
            String diagnosis, String admissionDate, String dischargeDate, NurseList nurseAssigned) {
        super(id, name, age, gender, address, phone);
        this.diagnosis = diagnosis;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.nurseAssigned = nurseAssigned;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(String dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public NurseList getNurseAssigned() {
        return nurseAssigned;
    }

    public void setNurseAssigned(NurseList nurseAssigned) {
        this.nurseAssigned = nurseAssigned;
    }
    
    private String toStringNurseAssigned() { // Đưa về String của NurseListAssigned
        String ret = "";
        for (Entry<String, Nurse> entry : nurseAssigned.entrySet()) {
            ret += String.format("%s:", entry.getKey());
        }
        return ret.substring(0, ret.length() - 1);
    }

    @Override
    public String toString() {
        return String.format("%s|%s|%d|%s|%s|%s|%s|%s|%s|%s", id, name, age,
                gender, address, phone, diagnosis, admissionDate, dischargeDate, toStringNurseAssigned());
    }
}

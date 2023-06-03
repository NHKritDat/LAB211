/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Businesses.Hospital;

/**
 *
 * @author Admin
 */
public class Program {
    
    public static void main(String[] args) {
        Menu menu = new Menu("-----------------------------HOSPITAL MANAGEMENT-----------------------------");
        menu.addOption("CREAT NURSE");
        menu.addOption("FIND NURSE");
        menu.addOption("UPDATE NURSE");
        menu.addOption("DELETE NURSE");
        menu.addOption("ADD PATIENT");
        menu.addOption("DISPLAY PATIENTS");
        menu.addOption("SORT PATIENT LIST");
        menu.addOption("SAVE DATA");
        menu.addOption("LOAD DATA");
        menu.addOption("QUIT");
        Hospital h = new Hospital();
        int choice;
        while (true) {
            menu.printMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    h.createNurse();
                    break;
                case 2:
                    h.findNurse();
                    break;
                case 3:
                    h.updateNurse();
                    break;
                case 4:
                    h.deleteNurse();
                    break;
                case 5:
                    h.addPatient();
                    break;
                case 6:
                    h.displayPatient();
                    break;
                case 7:
                    h.sortPatient();
                    break;
                case 8:
                    h.saveData();
                    break;
                case 9:
                    h.loadData();
                    break;
                case 10:
                    h.quitProgram();
                    break;
            }
        }
    }
}

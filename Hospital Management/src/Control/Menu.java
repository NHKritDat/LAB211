/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Menu {
    
    private final String title; // Tilte
    private final ArrayList<String> options = new ArrayList<>(); // ArrayList options
    private final Scanner sc = new Scanner(System.in);

    public Menu(String title) { // Lấy title
        this.title = title;
    }
    
    public void addOption(String newOption) { // Thêm option
        options.add(newOption);
    }
    
    public void printMenu() { // In ra bảng Menu
        System.out.println(title);
        for (String s : options) {
            if (options.indexOf(s) < 9) {
                System.out.println(" " + (options.indexOf(s) + 1) + "." + s);
            } else {
                System.out.println("10." + s);
            }
        }
    }
    
    public int getChoice() { // Nhập lựa chọn
        while (true) {
            try {
                System.out.print("Choose an option: ");
                int choice = Integer.parseInt(sc.nextLine());
                if (choice < 1 || choice > options.size()) {
                    throw new Exception();
                }
                return choice;
            } catch (NumberFormatException e) {
                System.out.println("Option must be an integer number!");
            } catch (Exception e) {
                System.out.println("Optioin must be in [1, " + options.size() + "]");
            }
        }
    }
    
    // Lựa chọn Yes/No
    public static boolean getYesNo(String title) {
        Menu sub = new Menu(title); 
        sub.addOption("Yes");
        sub.addOption("No");
        sub.printMenu();
        return sub.getChoice() == 1;
    }
    
    // Lựa chọn type Sort
    public static boolean getSort(String op1, String op2) {
        Menu title = new Menu("What function do you want to perform?");
        title.addOption(op1);
        title.addOption(op2);
        title.printMenu();
        return title.getChoice() == 1;
    }
}

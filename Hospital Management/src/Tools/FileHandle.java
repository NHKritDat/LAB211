/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class FileHandle {
    
    private final static String SYSPATH = new File("").getAbsolutePath(); // Tạo SYSPATH
    
    private static String initPath(String path) { // Tạo path
        return SYSPATH + path;
    }
    
    public static boolean writeToFile(String filePath, ArrayList<String> dta) {
        String _path = initPath(filePath); // Ghi nhận path
        File file = new File(_path); // Thêm path vào File
        try {
            file.createNewFile(); // Tạo file
            BufferedWriter output = new BufferedWriter(new FileWriter(file)); // tạo output Buffer
            for (String line : dta) {  // Gọi từng dta
                output.write(line); // Viết vào file
                output.newLine(); // Dòng mới
            }
            output.close(); // Kết thúc Buffer
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    
    public static ArrayList<String> readFromFile(String path) {
        String _path = initPath(path); // Ghi nhận path
        File file = new File(_path); // Thêm path vào file
        ArrayList<String> dta = new ArrayList<>(); // dta chứa thông tin
        try {
            BufferedReader input = new BufferedReader(new FileReader(file)); // Tạo input Buffer
            String line; // Tạo line
            while ((line = input.readLine()) != null) { // line != null, thêm vào dta
                dta.add(line.trim());
            }
            input.close(); // Kết thúc Buffer
        } catch (IOException e) {
        }
        return dta;
    }
}

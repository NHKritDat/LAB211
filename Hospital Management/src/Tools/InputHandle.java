/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Admin
 */
public class InputHandle {
    
    public static Scanner sc = new Scanner(System.in);
    
    // Nhập chuỗi theo regex
    public static String getString(String inputMsg, String errorMsg, String regex) {
        while (true) {
            try {
                System.out.print(inputMsg);
                String str = sc.nextLine(); // Nhập thông tin
                if (!str.matches(regex)) { // Kiểm tra trùng với regex không
                    throw new Exception();
                } else {
                    return str;
                }
            } catch (Exception e) {
                System.out.println(errorMsg); // Ném thông báo lỗi và thực hiện lại
            }
        }
    }
    
    // Nhập chuỗi không để trống
    public static String getString(String inputMsg, String errorMsg) {
        while (true) {
            try {
                System.out.print(inputMsg);
                String str = sc.nextLine(); // Nhập thông tin
                if (str.trim().isEmpty()) { // Kiểm tra chuỗi có rỗng hay chỉ bao gồm dấu cách
                    throw new Exception();
                }
                return str;
            } catch (Exception e) {
                System.out.println(errorMsg); // Ném thông báo lỗi và thực hiện lại
            }
        }
    }
    
    // Nhập số nguyên dương
    public static int getPositiveInt(String inputMsg, String errorMsg) {
        while (true) {
            try {
                System.out.print(inputMsg);
                int num = Integer.parseInt(sc.nextLine()); // Nhập thông tin
                if (num <= 0) { // Kiểm tra số dương hay không
                    throw new NumberFormatException();
                }
                return num;
            } catch (NumberFormatException e) { // Ném thông báo lỗi và thực hiện lại
                System.out.println(errorMsg);
            } catch (Exception e) {
                System.out.println("Must be greater than 0!");
            }
        }
    }
    
    // Nhập chuỗi giới hạn ký tự
    public static String getString(String inputMsg, String errorMsg, int min, int max) {
        while (true) {
            try {
                System.out.print(inputMsg);
                String str = sc.nextLine(); // Nhập thông tin
                if (str.length() < min || str.length() > max) { // Kiểm tra giới hạn ký tự
                    throw new Exception();
                }
                return str;
            } catch (Exception e) { // Ném thông báo lỗi và thực hiện lại
                System.out.println(errorMsg);
            }
        }
    }
    
    // Nhập số thực không âm
    public static double getDouble(String inputMsg, String errorMsg) {
        while (true) {
            try {
                System.out.print(inputMsg);
                double num = Double.parseDouble(sc.nextLine()); // Nhập thông tin
                if (num < 0) { // Kiểm tra số thực không âm
                    throw new NumberFormatException();
                }
                return num;
            } catch (NumberFormatException e) { // Ném thông báo lỗi và thực hiện lại
                System.out.println(errorMsg);
            } catch (Exception e) {
                System.out.println("Not less than 0!");
            }
        }
    }
    
    // Nhập chuỗi không điều kiện
    public static String getString(String inputMsg) {
        System.out.print(inputMsg);
        return sc.nextLine(); // Nhập thông tin
    }
    
    // Nhập số nguyên không điều kiện
    public static int getInt(String inputMsg) {
        System.out.print(inputMsg);
        int num = Integer.parseInt(sc.nextLine());
        return num;
    }
    
    // Nhập số thực không điều kiện
    public static double getDouble(String inputMsg) {
        System.out.print(inputMsg);
        double num = Double.parseDouble(sc.nextLine());
        return num;
    }
    
    // Nhập Date
    public static String getDate(String inputMsg, String errorMsg, String dateFormat) {
        while (true) {
            try {
                System.out.print(inputMsg);
                String str = sc.nextLine(); // Nhập date
                if (!checkValidDate(str, dateFormat)) { // Kiểm tra date đúng format
                    throw new Exception();
                }
                return str;
            } catch (Exception e) { // Ném thông báo lỗi và thực hiện lại
                System.out.println(errorMsg);
                System.out.println(dateFormat);
            }
        }
    }
    
    // Kiểm tra valid Date
    private static boolean checkValidDate(String date, String dateFormat) {
        try {
            if (!isValidDateFormat(date, dateFormat)) { // Kiểm tra format của Date
                throw new IllegalArgumentException("Invalid date format!");
            }
            int day , month, year;
            String[] dateParts = date.split("[- / .]"); // Phân date được nhập vào theo - / .
            if (dateFormat.equals("dd/mm/yyyy")) {
                day = Integer.parseInt(dateParts[0]); // date là mảng String[0]
                month = Integer.parseInt(dateParts[1]); // month là mảng String[1]
            } else { // Ngược lại
                day = Integer.parseInt(dateParts[1]);
                month = Integer.parseInt(dateParts[0]);
            }
            year = Integer.parseInt(dateParts[2]);
            if (month < 1 || month > 12) { // Kiểm tra valid month
                throw new IllegalArgumentException("Invalid month value!");
            }
            switch (month) { // Kiểm tra valid day trong month
                case 4:
                case 6:
                case 9:
                case 11:
                    if (day > 30) {
                        throw new IllegalArgumentException("Invalid date value!");
                    } else {
                        return true;
                    }
                case 2:
                    if ((year % 4 == 0 && year % 100 != 0 || year % 400 == 0)) { // Năm nhuận
                        if (day > 29) {
                            throw new IllegalArgumentException("Invalid date value!");
                        } else {
                            return true;
                        }
                    } else { // Năm không nhuận
                        if (day > 28) {
                            throw new IllegalArgumentException("Invalid date value!");
                        } else {
                            return true;
                        }
                    }
                default:
                    if (day > 31) {
                        throw new IllegalArgumentException("Invalid date value!");
                    } else {
                        return true;            
                    }
            }
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    
    private static final String DDMMYYYY_REGEX = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$";
    private static final String MMDDYYYY_REGEX = "^(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\\d\\d$";
    
    // Format date
    private static boolean isValidDateFormat(String date, String dateFormat) {
        String regex = (dateFormat.equals("dd/mm/yyyy")) ? DDMMYYYY_REGEX : MMDDYYYY_REGEX; // Regex được nhập theo format nào?
        Pattern pattern = Pattern.compile(regex); // Thêm regex vào pattern
        Matcher matcher = pattern.matcher(date); // Xét những thành phần match với regex
        return matcher.matches(); // Trả về nếu trùng tất cả với regex
    }
    
    // Chuyển String sang Date
    public static Date toDate(String date, String format) {
        Date ret = new Date();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format); // Set format
            ret = sdf.parse(date); // Chuyển về theo format
        } catch (ParseException e) {
        }
        return ret;
    }
}

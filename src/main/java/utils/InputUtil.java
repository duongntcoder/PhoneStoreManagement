package util;

import java.util.Scanner;

public class InputUtil {

    private static Scanner scanner = new Scanner(System.in);

    // ===============================
    // Nhập số nguyên
    // ===============================
    public static int inputInt(String message){

        while(true){

            try{

                System.out.print(message);

                int value = Integer.parseInt(scanner.nextLine());

                if(value < 0){

                    System.out.println("❌ Giá trị phải lớn hơn hoặc bằng 0!");

                    continue;

                }

                return value;

            }catch(NumberFormatException e){

                System.out.println("❌ Vui lòng nhập số nguyên!");

            }

        }

    }

    // ===============================
    // Nhập số thực
    // ===============================
    public static double inputDouble(String message){

        while(true){

            try{

                System.out.print(message);

                double value = Double.parseDouble(scanner.nextLine());

                if(value <= 0){

                    System.out.println("❌ Giá phải lớn hơn 0!");

                    continue;

                }

                return value;

            }catch(NumberFormatException e){

                System.out.println("❌ Vui lòng nhập số!");

            }

        }

    }

    // ===============================
    // Nhập chuỗi
    // ===============================
    public static String inputString(String message){

        while(true){

            System.out.print(message);

            String value = scanner.nextLine();

            if(!value.trim().isEmpty()){

                return value;

            }

            System.out.println("❌ Không được để trống!");

        }

    }

    // ===============================
    // Xác nhận Y/N
    // ===============================
    public static boolean confirm(String message){

        while(true){

            System.out.print(message + " (Y/N): ");

            String choose = scanner.nextLine();

            if(choose.equalsIgnoreCase("Y")){

                return true;

            }

            if(choose.equalsIgnoreCase("N")){

                return false;

            }

            System.out.println("❌ Chỉ được nhập Y hoặc N!");

        }

    }
    public static String inputEmail(String message){

        while(true){

            System.out.print(message);

            String email = scanner.nextLine().trim();

            if(email.isEmpty()){

                System.out.println("❌ Không được để trống!");

                continue;

            }

            if(!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")){

                System.out.println("❌ Email không đúng định dạng!");

                continue;

            }

            return email;

        }

    }
    public static String inputPassword(String message){

        while(true){

            System.out.print(message);

            String password = scanner.nextLine();

            if(password.isBlank()){

                System.out.println("❌ Không được để trống!");

                continue;

            }

            if(password.length() < 6){

                System.out.println("❌ Mật khẩu phải từ 6 ký tự!");

                continue;

            }

            return password;

        }

    }


}
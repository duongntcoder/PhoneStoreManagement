package menu;

import java.util.Scanner;
import entity.Admin;
import service.AdminService;
import util.InputUtil;
public class LoginMenu {

    private Scanner scanner =
            new Scanner(System.in);

    public void start(){

        while(true){

            System.out.println();
            System.out.println("==================================================");
            System.out.println("           PHONE STORE MANAGEMENT");
            System.out.println("==================================================");
            System.out.println("1. Đăng nhập");
            System.out.println("2. Đăng ký Admin");
            System.out.println("0. Thoát");
            System.out.println("==================================================");
            System.out.print("Chọn: ");

            int choice =
                    Integer.parseInt(scanner.nextLine());

            switch(choice){

                case 1:

                    login();

                    break;

                case 2:

                    register();

                    break;

                case 3:

                    System.exit(0);

            }

        }

    }
    private AdminService adminService =
            new AdminService();
    public void register(){

        Admin admin =
                new Admin();

        admin.setFullName(
                InputUtil.inputString("Họ tên: "));

        admin.setEmail(InputUtil.inputEmail("Email: "));

        admin.setPasswordHash(InputUtil.inputPassword("Mật khẩu: "));

        admin.setRole("ADMIN");
        if(adminService.existsEmail(admin.getEmail())){

            System.out.println("❌ Email đã tồn tại!");

            return;

        }
        boolean result =
                adminService.register(admin);

        if(result){

            System.out.println("Đăng ký thành công!");

        }else{

            System.out.println("Đăng ký thất bại!");

        }

    }
    public void login() {

        while (true) {

            String email =
                    InputUtil.inputEmail("Email: ");

            String password =
                    InputUtil.inputPassword("Mật khẩu: ");

            Admin admin =
                    adminService.login(email, password);

            if (admin != null) {

                System.out.println("\nĐăng nhập thành công!");

                MainMenu mainMenu = new MainMenu();

                mainMenu.start();

                return;

            } else {

                System.out.println("\n❌ Sai email hoặc mật khẩu!");
                System.out.println("Vui lòng nhập lại.\n");

            }

        }

    }

}
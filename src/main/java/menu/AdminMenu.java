package menu;

import java.util.Scanner;

public class AdminMenu {

    private Scanner scanner = new Scanner(System.in);

    public void start() {

        while (true) {

            System.out.println("\n========== PHONE STORE MANAGEMENT ==========");
            System.out.println("1. Quản lý sản phẩm");
            System.out.println("2. Quản lý khách hàng");
            System.out.println("3. Quản lý hóa đơn");
            System.out.println("4. Thống kê doanh thu");
            System.out.println("5. Đăng xuất");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {

                case 1:
                    // ProductMenu.start();
                    break;

                case 2:
                    // CustomerMenu.start();
                    break;

                case 3:
                    // InvoiceMenu.start();
                    break;

                case 4:
                    System.out.println("Chức năng thống kê");
                    break;

                case 5:
                    System.out.println("Đăng xuất...");
                    return; // quay lại LoginMenu

                case 0:
                    System.exit(0);

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }

        }

    }

}
package menu;

import util.InputUtil;

public class MainMenu {

    private ProductMenu productMenu = new ProductMenu();
    private CustomerMenu customerMenu = new CustomerMenu();
    private InvoiceMenu invoiceMenu = new InvoiceMenu();

    public void start() {

        while (true) {

            System.out.println();
            System.out.println("==================================================");
            System.out.println("                 MENU QUẢN LÝ");
            System.out.println("==================================================");
            System.out.println("1. Quản lý sản phẩm");
            System.out.println("2. Quản lý khách hàng");
            System.out.println("3. Quản lý hóa đơn");
            System.out.println("4. Thống kê doanh thu");
            System.out.println("5. Đăng xuất");
            System.out.println("0. Thoát");
            System.out.println("==================================================");
            System.out.print("Chọn: ");


            int choice = InputUtil.inputInt("Chọn: ");

            switch (choice) {

                case 1:

                    productMenu.start();

                    break;

                case 2:

                    customerMenu.start();

                    break;

                case 3:

                    invoiceMenu.start();

                    break;

                case 4:

                    invoiceMenu.revenueStatistic();

                    break;

                case 5:

                    System.out.println("\nĐăng xuất thành công!");
                    return;

                case 0:

                    System.out.println("\nTạm biệt!");
                    System.exit(0);

                default:

                    System.out.println("\nLựa chọn không hợp lệ!");

            }

        }

    }

}
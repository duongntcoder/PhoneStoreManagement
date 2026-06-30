package menu;

import entity.Customer;
import service.CustomerService;
import util.InputUtil;
import java.util.List;
import java.util.Scanner;

public class CustomerMenu {

    private CustomerService customerService = new CustomerService();

    private Scanner scanner = new Scanner(System.in);

    public void showAllCustomers() {

        List<Customer> customers =
                customerService.getAllCustomers();

        System.out.println("==========================================================================");

        System.out.printf("%-5s %-20s %-15s %-25s %-20s%n",
                "ID",
                "Tên",
                "SĐT",
                "Email",
                "Địa chỉ");

        System.out.println("==========================================================================");

        for (Customer customer : customers) {

            System.out.println(customer);

        }

    }

    public void addCustomer() {

        Customer customer = new Customer();

        System.out.print("Tên: ");
        customer.setFullName(scanner.nextLine());

        System.out.print("SĐT: ");
        customer.setPhone(scanner.nextLine());

        System.out.print("Email: ");
        customer.setEmail(scanner.nextLine());

        System.out.print("Địa chỉ: ");
        customer.setAddress(scanner.nextLine());
        if(customerService.existsEmail(customer.getEmail())){

            System.out.println("❌ Email đã tồn tại!");

            return;

        }
        boolean result =
                customerService.insertCustomer(customer);

        if (result) {

            System.out.println("Thêm thành công.");

        } else {

            System.out.println("Thêm thất bại.");

        }

    }
    public void updateCustomer(){

        Customer customer = new Customer();

        customer.setCustomerId(
                InputUtil.inputInt("Nhập ID: "));

        customer.setFullName(
                InputUtil.inputString("Tên mới: "));

        customer.setPhone(
                InputUtil.inputString("SĐT mới: "));

        customer.setEmail(
                InputUtil.inputEmail("Email mới: "));

        customer.setAddress(
                InputUtil.inputString("Địa chỉ mới: "));

        if(customerService.existsEmail(
                customer.getEmail(),
                customer.getCustomerId())){

            System.out.println("❌ Email đã tồn tại!");

            return;

        }

        boolean result =
                customerService.updateCustomer(customer);

        if(result){

            System.out.println("✅ Cập nhật thành công.");

        }else{

            System.out.println("❌ Không tìm thấy khách hàng.");

        }

    }
    public void deleteCustomer() {

        System.out.print("Nhập ID khách hàng cần xóa: ");

        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Bạn có chắc muốn xóa? (Y/N): ");

        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("Y")) {

            boolean result = customerService.deleteCustomer(id);

            if (result) {
                System.out.println("Xóa khách hàng thành công!");
            } else {
                System.out.println("Không tìm thấy khách hàng!");
            }

        } else {

            System.out.println("Đã hủy thao tác.");

        }

    }
    public void searchCustomer() {

        System.out.println("\n===== TÌM KIẾM KHÁCH HÀNG =====");
        System.out.println("1. Theo tên");
        System.out.println("2. Theo số điện thoại");
        System.out.println("3. Theo Email");
        System.out.print("Chọn: ");

        int choice = Integer.parseInt(scanner.nextLine());

        List<Customer> customers = null;

        switch (choice) {

            case 1:

                System.out.print("Nhập tên: ");

                customers = customerService.searchByName(scanner.nextLine());

                break;

            case 2:

                System.out.print("Nhập số điện thoại: ");

                customers = customerService.searchByPhone(scanner.nextLine());

                break;

            case 3:

                System.out.print("Nhập Email: ");

                customers = customerService.searchByEmail(scanner.nextLine());

                break;

            default:

                System.out.println("Lựa chọn không hợp lệ!");

                return;
        }

        if (customers.isEmpty()) {

            System.out.println("Không tìm thấy khách hàng.");

            return;
        }

        System.out.println("==========================================================================");
        System.out.printf("%-5s %-20s %-15s %-25s %-20s%n",
                "ID", "Tên", "SĐT", "Email", "Địa chỉ");
        System.out.println("==========================================================================");

        for (Customer customer : customers) {
            System.out.println(customer);
        }

    }
    public void start() {

        while (true) {

            System.out.println();
            System.out.println("==================================================");
            System.out.println("            QUẢN LÝ KHÁCH HÀNG");
            System.out.println("==================================================");
            System.out.println("1. Xem danh sách");
            System.out.println("2. Thêm khách hàng");
            System.out.println("3. Cập nhật khách hàng");
            System.out.println("4. Xóa khách hàng");
            System.out.println("5. Tìm kiếm khách hàng");
            System.out.println("0. Quay lại");
            System.out.println("==================================================");
            System.out.print("Chọn: ");

            System.out.print("Chọn: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {

                case 1:
                    showAllCustomers();
                    break;

                case 2:
                    addCustomer();
                    break;

                case 3:
                    updateCustomer();
                    break;

                case 4:
                    deleteCustomer();
                    break;

                case 5:
                    searchCustomer();
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}
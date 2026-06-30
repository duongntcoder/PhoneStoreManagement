package menu;

import entity.Customer;
import entity.Invoice;
import entity.InvoiceDetail;
import entity.Product;
import java.sql.Date;
import service.CustomerService;
import service.InvoiceService;
import service.ProductService;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.List;
import java.util.Scanner;
import utils.FormatUtil;
public class InvoiceMenu {

    private InvoiceService invoiceService = new InvoiceService();
    private ProductService productService = new ProductService();
    private CustomerService customerService = new CustomerService();
    private Scanner scanner = new Scanner(System.in);
    public void showAllInvoices() {

        List<Invoice> invoices =
                invoiceService.getAllInvoices();

        System.out.println("========================================================");

        System.out.printf("%-5s %-10s %-25s %-12s%n",
                "ID",
                "Customer",
                "Ngày",
                "Tổng tiền");

        System.out.println("========================================================");

        for (Invoice invoice : invoices) {

            System.out.println(invoice);

        }

    }
    public void createInvoice() {

        System.out.println("===== TẠO HÓA ĐƠN =====");

        System.out.print("Nhập ID khách hàng: ");

        int customerId =
                Integer.parseInt(scanner.nextLine());

        Customer customer =
                customerService.findById(customerId);

        if(customer == null){

            System.out.println("Khách hàng không tồn tại!");

            return;

        }

        System.out.print("Nhập ID sản phẩm: ");

        int productId =
                Integer.parseInt(scanner.nextLine());

        Product product =
                productService.findById(productId);

        if(product == null){

            System.out.println("Sản phẩm không tồn tại!");

            return;

        }

        System.out.println("Tên: " + product.getName());
        System.out.println("Giá: " + product.getPrice());
        System.out.println("Kho: " + product.getStock());

        System.out.print("Nhập số lượng: ");

        int quantity =
                Integer.parseInt(scanner.nextLine());

        if(quantity > product.getStock()){

            System.out.println("Không đủ hàng!");

            return;

        }

        double total =
                quantity * product.getPrice();

        Invoice invoice = new Invoice();

        invoice.setCustomerId(customerId);
        invoice.setTotalAmount(total);

        int invoiceId =
                invoiceService.createInvoice(invoice);

        InvoiceDetail detail = new InvoiceDetail();

        detail.setInvoiceId(invoiceId);
        detail.setProductId(productId);
        detail.setQuantity(quantity);
        detail.setUnitPrice(product.getPrice());
        detail.setSubTotal(total);

        invoiceService.addInvoiceDetail(detail);

        productService.updateStock(productId, quantity);

        System.out.println("=======================");
        System.out.println("TẠO HÓA ĐƠN THÀNH CÔNG");
        System.out.println("Tổng tiền: "
                + FormatUtil.money(total));
    }
    public void revenueStatistic(){

        System.out.println("\n===== THỐNG KÊ DOANH THU =====");
        System.out.println("1. Theo ngày");
        System.out.println("2. Theo tháng");
        System.out.println("3. Theo năm");
        System.out.print("Chọn: ");

        int choice = Integer.parseInt(scanner.nextLine());
        NumberFormat format =
                NumberFormat.getInstance(new Locale("vi", "VN"));
        switch(choice){

            case 1:

                System.out.print("Nhập ngày (yyyy-mm-dd): ");

                Date date = Date.valueOf(scanner.nextLine());

                System.out.println("Doanh thu: "
                        + FormatUtil.money(invoiceService.revenueByDate(date)));

                break;

            case 2:

                System.out.print("Nhập tháng: ");

                int month = Integer.parseInt(scanner.nextLine());

                System.out.println("Doanh thu: "
                        + FormatUtil.money(invoiceService.revenueByMonth(month)));

                break;

            case 3:

                System.out.print("Nhập năm: ");

                int year = Integer.parseInt(scanner.nextLine());

                System.out.println("Doanh thu: "
                        + FormatUtil.money(invoiceService.revenueByYear(year)));

                break;

            default:

                System.out.println("Lựa chọn không hợp lệ!");

        }

    }
    public void start() {

        while (true) {

            System.out.println();
            System.out.println("==================================================");
            System.out.println("              QUẢN LÝ HÓA ĐƠN");
            System.out.println("==================================================");
            System.out.println("1. Xem danh sách hóa đơn");
            System.out.println("2. Tạo hóa đơn");
            System.out.println("3. Tìm kiếm hóa đơn");
            System.out.println("4. Thống kê doanh thu");
            System.out.println("0. Quay lại");
            System.out.println("==================================================");
            System.out.print("Chọn: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {

                case 1:
                    showAllInvoices();
                    break;

                case 2:
                    createInvoice();
                    break;

                case 3:
                    searchInvoice();
                    break;

                case 4:
                    revenueStatistic();
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }

        }

    }
    public void searchInvoice() {

        System.out.println("\n===== TÌM KIẾM HÓA ĐƠN =====");
        System.out.println("1. Theo tên khách hàng");
        System.out.println("2. Theo ngày");
        System.out.println("0. Quay lại");

        int choice = Integer.parseInt(scanner.nextLine());

        List<Invoice> invoices;

        switch (choice) {

            case 1:

                System.out.print("Nhập tên khách hàng: ");
                String keyword = scanner.nextLine();

                invoices = invoiceService.searchByCustomer(keyword);

                break;

            case 2:

                System.out.print("Nhập ngày (yyyy-mm-dd): ");
                Date date = Date.valueOf(scanner.nextLine());

                invoices = invoiceService.searchByDate(date);

                break;

            case 0:
                return;

            default:
                System.out.println("Lựa chọn không hợp lệ!");
                return;
        }

        if (invoices.isEmpty()) {

            System.out.println("Không tìm thấy hóa đơn.");
            return;

        }

        System.out.println("==================================================================");
        System.out.printf("%-5s %-20s %-20s %-15s%n",
                "ID",
                "Khách hàng",
                "Ngày",
                "Tổng tiền");
        System.out.println("==================================================================");

        invoices.forEach(System.out::println);

    }


}

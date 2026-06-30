package service;

import dao.InvoiceDAO;
import entity.Invoice;
import entity.InvoiceDetail;
import java.sql.Date;

import java.util.List;

public class InvoiceService {

    private InvoiceDAO invoiceDAO = new InvoiceDAO();

    // Hiển thị danh sách hóa đơn
    public List<Invoice> searchByCustomer(String keyword){

        return invoiceDAO.getAllInvoices()
                .stream()
                .filter(invoice ->
                        invoice.getCustomerName()
                                .toLowerCase()
                                .contains(keyword.toLowerCase()))
                .toList();

    }

    // Tạo hóa đơn
    public int createInvoice(Invoice invoice) {
        return invoiceDAO.createInvoice(invoice);
    }

    // Thêm chi tiết hóa đơn
    public boolean addInvoiceDetail(InvoiceDetail detail) {
        return invoiceDAO.addInvoiceDetail(detail);
    }
    public double revenueByDate(Date date){

        return invoiceDAO.getInvoicesByDate(date)
                .stream()
                .mapToDouble(Invoice::getTotalAmount)
                .sum();

    }
    public double revenueByMonth(int month){

        return invoiceDAO.getInvoicesByMonth(month)
                .stream()
                .mapToDouble(Invoice::getTotalAmount)
                .sum();

    }
    public double revenueByYear(int year){

        return invoiceDAO.getInvoicesByYear(year)
                .stream()
                .mapToDouble(Invoice::getTotalAmount)
                .sum();

    }
    public List<Invoice> searchByDate(Date date){

        return invoiceDAO.getAllInvoices()
                .stream()
                .filter(invoice ->
                        invoice.getInvoiceDate()
                                .toLocalDateTime()
                                .toLocalDate()
                                .equals(date.toLocalDate()))
                .toList();

    }
    public List<Invoice> getAllInvoices() {

        return invoiceDAO.getAllInvoices();

    }


}
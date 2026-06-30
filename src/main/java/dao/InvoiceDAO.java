package dao;

import config.JDBCUtil;
import entity.Invoice;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import entity.InvoiceDetail;
public class InvoiceDAO {
    public List<Invoice> getAllInvoices() {

        List<Invoice> invoices = new ArrayList<>();

        String sql = """
    SELECT i.invoice_id,
           i.customer_id,
           c.customer_name,
           i.invoice_date,
           i.total_amount
    FROM invoice i
    JOIN customer c
        ON i.customer_id = c.customer_id
    ORDER BY i.invoice_id
    """;

        try {

            Connection connection = JDBCUtil.getConnection();

            PreparedStatement ps =
                    connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Invoice invoice = new Invoice();

                invoice.setInvoiceId(
                        rs.getInt("invoice_id"));

                invoice.setCustomerId(
                        rs.getInt("customer_id"));
                invoice.setCustomerName(
                        rs.getString("customer_name"));

                invoice.setInvoiceDate(
                        rs.getTimestamp("invoice_date"));

                invoice.setTotalAmount(
                        rs.getDouble("total_amount"));

                invoices.add(invoice);

            }

            connection.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return invoices;

    }
    public int createInvoice(Invoice invoice) {

        String sql = """
            INSERT INTO invoice(customer_id, total_amount)
            VALUES (?, ?)
            RETURNING invoice_id
            """;

        try {

            Connection connection = JDBCUtil.getConnection();

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, invoice.getCustomerId());
            ps.setDouble(2, invoice.getTotalAmount());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                int invoiceId = rs.getInt("invoice_id");

                connection.close();

                return invoiceId;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }
    public boolean addInvoiceDetail(InvoiceDetail detail) {

        String sql = """
            INSERT INTO invoice_detail
            (invoice_id, product_id, quantity, unit_price, sub_total)
            VALUES (?, ?, ?, ?, ?)
            """;

        try {

            Connection connection = JDBCUtil.getConnection();

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, detail.getInvoiceId());
            ps.setInt(2, detail.getProductId());
            ps.setInt(3, detail.getQuantity());
            ps.setDouble(4, detail.getUnitPrice());
            ps.setDouble(5, detail.getSubTotal());

            int result = ps.executeUpdate();

            connection.close();

            return result > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    public List<Invoice> getInvoicesByDate(Date date){

        List<Invoice> invoices = new ArrayList<>();

        String sql = "SELECT * FROM invoice WHERE DATE(invoice_date)=?";

        try{

            Connection connection = JDBCUtil.getConnection();

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setDate(1,date);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                Invoice invoice = new Invoice();

                invoice.setInvoiceId(rs.getInt("invoice_id"));
                invoice.setCustomerId(rs.getInt("customer_id"));
                invoice.setInvoiceDate(rs.getTimestamp("invoice_date"));
                invoice.setTotalAmount(rs.getDouble("total_amount"));

                invoices.add(invoice);

            }

            connection.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return invoices;
    }
    public List<Invoice> getInvoicesByMonth(int month){

        List<Invoice> invoices = new ArrayList<>();

        String sql =
                "SELECT * FROM invoice WHERE EXTRACT(MONTH FROM invoice_date)=?";

        try{

            Connection connection = JDBCUtil.getConnection();

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1,month);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                Invoice invoice = new Invoice();

                invoice.setInvoiceId(rs.getInt("invoice_id"));
                invoice.setCustomerId(rs.getInt("customer_id"));
                invoice.setInvoiceDate(rs.getTimestamp("invoice_date"));
                invoice.setTotalAmount(rs.getDouble("total_amount"));

                invoices.add(invoice);

            }

            connection.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return invoices;

    }
    public List<Invoice> getInvoicesByYear(int year){

        List<Invoice> invoices = new ArrayList<>();

        String sql =
                "SELECT * FROM invoice WHERE EXTRACT(YEAR FROM invoice_date)=?";

        try{

            Connection connection = JDBCUtil.getConnection();

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1,year);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                Invoice invoice = new Invoice();

                invoice.setInvoiceId(rs.getInt("invoice_id"));
                invoice.setCustomerId(rs.getInt("customer_id"));
                invoice.setInvoiceDate(rs.getTimestamp("invoice_date"));
                invoice.setTotalAmount(rs.getDouble("total_amount"));

                invoices.add(invoice);

            }

            connection.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return invoices;

    }
    public List<Invoice> searchByCustomer(String keyword) {

        List<Invoice> invoices = new ArrayList<>();

        String sql = """
        SELECT i.invoice_id,
               i.customer_id,
               c.customer_name,
               i.invoice_date,
               i.total_amount
        FROM invoice i
        JOIN customer c
        ON i.customer_id = c.customer_id
        WHERE LOWER(c.customer_name)
        LIKE LOWER(?)
        ORDER BY i.invoice_id
        """;

        try {

            Connection connection = JDBCUtil.getConnection();

            PreparedStatement ps =
                    connection.prepareStatement(sql);

            ps.setString(1, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Invoice invoice = new Invoice();

                invoice.setInvoiceId(rs.getInt("invoice_id"));
                invoice.setCustomerId(rs.getInt("customer_id"));
                invoice.setCustomerName(rs.getString("customer_name"));
                invoice.setInvoiceDate(rs.getTimestamp("invoice_date"));
                invoice.setTotalAmount(rs.getDouble("total_amount"));

                invoices.add(invoice);

            }

            connection.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return invoices;
    }
    public List<Invoice> searchByDate(Date date) {

        List<Invoice> invoices = new ArrayList<>();

        String sql = """
        SELECT i.invoice_id,
               i.customer_id,
               c.customer_name,
               i.invoice_date,
               i.total_amount
        FROM invoice i
        JOIN customer c
        ON i.customer_id = c.customer_id
        WHERE DATE(i.invoice_date)=?
        ORDER BY i.invoice_id
        """;

        try {

            Connection connection = JDBCUtil.getConnection();

            PreparedStatement ps =
                    connection.prepareStatement(sql);

            ps.setDate(1, date);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Invoice invoice = new Invoice();

                invoice.setInvoiceId(rs.getInt("invoice_id"));
                invoice.setCustomerId(rs.getInt("customer_id"));
                invoice.setCustomerName(rs.getString("customer_name"));
                invoice.setInvoiceDate(rs.getTimestamp("invoice_date"));
                invoice.setTotalAmount(rs.getDouble("total_amount"));

                invoices.add(invoice);

            }

            connection.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return invoices;
    }
}
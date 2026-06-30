package entity;
import utils.FormatUtil;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
public class Invoice {

    private int invoiceId;
    private int customerId;
    private String customerName;
    private Timestamp invoiceDate;
    private double totalAmount;

    public Invoice() {
    }

    public Invoice(int invoiceId, int customerId, Timestamp invoiceDate, double totalAmount) {
        this.invoiceId = invoiceId;
        this.customerId = customerId;
        this.invoiceDate = invoiceDate;
        this.totalAmount = totalAmount;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Timestamp getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Timestamp invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {

        String date = invoiceDate.toLocalDateTime()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        return String.format(
                "%-5d %-20s %-20s %-20s",
                invoiceId,
                customerName,
                date,
                FormatUtil.money(totalAmount)
        );

    }
}
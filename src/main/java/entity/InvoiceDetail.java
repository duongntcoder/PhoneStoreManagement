package entity;

public class InvoiceDetail {

    private int detailId;
    private int invoiceId;
    private int productId;
    private int quantity;
    private double unitPrice;
    private double subTotal;

    public InvoiceDetail() {
    }

    public InvoiceDetail(int detailId, int invoiceId, int productId,
                         int quantity, double unitPrice, double subTotal) {

        this.detailId = detailId;
        this.invoiceId = invoiceId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subTotal = subTotal;
    }

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    @Override
    public String toString() {
        return String.format(
                "%-5d %-5d %-5d %-10.2f %-10.2f",
                productId,
                quantity,
                invoiceId,
                unitPrice,
                subTotal
        );
    }
}
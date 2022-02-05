package model;

import java.util.Date;

public class Buy {
  
    private int id,installments /*quantidade de parcelas */;
    private ProductSupplier id_supplier;
    private String paymentMethod, date /*data_emissao compra*/;
    private double totalValue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductSupplier getId_supplier() {
        return id_supplier;
    }

    public void setId_supplier(ProductSupplier id_supplier) {
        this.id_supplier = id_supplier;
    }

    public int getInstallments() {
        return installments;
    }

    public void setInstallments(int installments) {
        this.installments = installments;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }
 
    
    
}

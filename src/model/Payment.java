package model;

/**
 *
 * @author daniel
 */
public class Payment {
        
    private int id_payment,
                id_acc_payment,
                id_cashier_payment;
    
    private String date_payment;
    
    private float total_payment_amount;

    public int getId_payment() {
        return id_payment;
    }

    public void setId_payment(int id_payment) {
        this.id_payment = id_payment;
    }

    public int getId_acc_payment() {
        return id_acc_payment;
    }

    public void setId_acc_payment(int id_acc_payment) {
        this.id_acc_payment = id_acc_payment;
    }

    public int getId_cashier_payment() {
        return id_cashier_payment;
    }

    public void setId_cashier_payment(int id_cashier_payment) {
        this.id_cashier_payment = id_cashier_payment;
    }

    public String getDate_payment() {
        return date_payment;
    }

    public void setDate_payment(String date_payment) {
        this.date_payment = date_payment;
    }
    
    public float getTotal_payment_amount() {
        return total_payment_amount;
    }

    public void setTotal_payment_amount(float total_payment_amount) {
        this.total_payment_amount = total_payment_amount;
    }
    
    
    
    
}

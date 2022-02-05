package model;

/**
 *
 * @author daniel
 */
public class Receivement {
    
  /*   Database info
   *   id_recebimento 
   *   id_conta_receber 
   *   id_caixa_recebimento 
   *   data_recebimento 
   *   forma_recebimento
   *   valor_total_recebimento 
   */
    
    private int id_receivement, //id do recebimento.
                id_acc_receivement, //conta que vai retirar o dinheiro.
                id_cashier_receivement; //id do caixa que vai receber o pagamento.
    
    private String date_receivement;//forma de pagamento do recebimento
    
    private float total_amount_receivable;//valor total do recebimento.

    public int getId_receivement() {
        return id_receivement;
    }

    public void setId_receivement(int id_receivement) {
        this.id_receivement = id_receivement;
    }

    public int getId_acc_receivement() {
        return id_acc_receivement;
    }

    public void setId_acc_receivement(int id_acc_receivement) {
        this.id_acc_receivement = id_acc_receivement;
    }

    public int getId_cashier_receivement() {
        return id_cashier_receivement;
    }

    public void setId_cashier_receivement(int id_cashier_receivement) {
        this.id_cashier_receivement = id_cashier_receivement;
    }

    public String getDate_receivement() {
        return date_receivement;
    }

    public void setDate_receivement(String date_receivement) {
        this.date_receivement = date_receivement;
    }

    public float getTotal_amount_receivable() {
        return total_amount_receivable;
    }

    public void setTotal_amount_receivable(float total_amount_receivable) {
        this.total_amount_receivable = total_amount_receivable;
    }
    
    
    
    
}

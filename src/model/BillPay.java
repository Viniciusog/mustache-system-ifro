/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author daniel
 */
public class BillPay {
    /**
     * 
     *  id_conta_pagar int not null auto_increment primary key,
     *  id_compra_conta_pagar int, 
     *  id_funcionario_conta_pagar int,
     *  descricao_conta_pagar varchar(200) not null,
     *  valor_pago_conta_pagar decimal(10,2) not null,
     *  valor_total_conta_pagar decimal(10,2) not null,
     *  data_emissao_conta_pagar date not null,
     *  quitada_conta_pagar boolean not null,
     *  tipo_conta_pagar varchar(100) not null,
     */

private int id_bill_pay, id_buy_bill_pay,id_employee_buy_bill_pay;
private String description_bill, date_bill, type_bill_pay;
private double value_paidOut_bill_pay, total_amount_bill_pay;
private boolean settled_bill_pay;

    public int getId_bill_pay() {
        return id_bill_pay;
    }

    public void setId_bill_pay(int id_bill_pay) {
        this.id_bill_pay = id_bill_pay;
    }

    public int getId_buy_bill_pay() {
        return id_buy_bill_pay;
    }

    public void setId_buy_bill_pay(int id_buy_bill_pay) {
        this.id_buy_bill_pay = id_buy_bill_pay;
    }

    public int getId_employee_buy_bill_pay() {
        return id_employee_buy_bill_pay;
    }

    public void setId_employee_buy_bill_pay(int id_employee_buy_bill_pay) {
        this.id_employee_buy_bill_pay = id_employee_buy_bill_pay;
    }

    public String getDescription_bill() {
        return description_bill;
    }

    public void setDescription_bill(String description_bill) {
        this.description_bill = description_bill;
    }

    public String getDate_bill() {
        return date_bill;
    }

    public void setDate_bill(String date_bill) {
        this.date_bill = date_bill;
    }

    public String getType_bill_pay() {
        return type_bill_pay;
    }

    public void setType_bill_pay(String type_bill_pay) {
        this.type_bill_pay = type_bill_pay;
    }

    public double getValue_paidOut_bill_pay() {
        return value_paidOut_bill_pay;
    }

    public void setValue_paidOut_bill_pay(double value_paidOut_bill_pay) {
        this.value_paidOut_bill_pay = value_paidOut_bill_pay;
    }

    public double getTotal_amount_bill_pay() {
        return total_amount_bill_pay;
    }

    public void setTotal_amount_bill_pay(double total_amount_bill_pay) {
        this.total_amount_bill_pay = total_amount_bill_pay;
    }

    public boolean isSettled_bill_pay() {
        return settled_bill_pay;
    }

    public void setSettled_bill_pay(boolean settled_bill_pay) {
        this.settled_bill_pay = settled_bill_pay;
    }


}

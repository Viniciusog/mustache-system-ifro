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
public class BillReceive {

    /**
     *
     * id_conta_receber int not null auto_increment primary key,
     * id_cliente_conta_receber int, id_venda_conta_receber int,
     * data_emissao_conta_receber date not null, data_vencimento_conta_receber
     * date not null, descricao_conta_receber varchar(200) not null,
     * tipo_conta_receber varchar(100) not null, valor_conta_receber decimal
     * (10,2) not null, valor_restante_conta_receber decimal (10,2) not null,
     * quitada_conta_receber boolean not null,
     */
    private int id_bill_receive, id_client_bill_receive, id_sale_bill_receive;
    private String date_emission_bill_receive, due_date_bill_receive, description_bill_receive, type_bill_receive;
    private Double value_bill;
    private boolean settled_bill_receive;
    private Double valueReceived;

    public Double getValueReceived() {
        return valueReceived;
    }

    public void setValueReceived(Double valueReceived) {
        this.valueReceived = valueReceived;
    }

    public int getId_bill_receive() {
        return id_bill_receive;
    }

    public void setId_bill_receive(int id_bill_receive) {
        this.id_bill_receive = id_bill_receive;
    }

    public int getId_client_bill_receive() {
        return id_client_bill_receive;
    }

    public boolean isSettled_bill_receive() {
        return settled_bill_receive;
    }

    public void setSettled_bill_receive(boolean settled_bill_receive) {
        this.settled_bill_receive = settled_bill_receive;
    }

    public void setId_client_bill_receive(int id_client_bill_receive) {
        this.id_client_bill_receive = id_client_bill_receive;
    }

    public int getId_sale_bill_receive() {
        return id_sale_bill_receive;
    }

    public void setId_sale_bill_receive(int id_sale_bill_receive) {
        this.id_sale_bill_receive = id_sale_bill_receive;
    }

    public String getDate_emission_bill_receive() {
        return date_emission_bill_receive;
    }

    public void setDate_emission_bill_receive(String date_emission_bill_receive) {
        this.date_emission_bill_receive = date_emission_bill_receive;
    }

    public String getDue_date_bill_receive() {
        return due_date_bill_receive;
    }

    public void setDue_date_bill_receive(String due_date_bill_receive) {
        this.due_date_bill_receive = due_date_bill_receive;
    }

    public String getDescription_bill_receive() {
        return description_bill_receive;
    }

    public void setDescription_bill_receive(String description_bill_receive) {
        this.description_bill_receive = description_bill_receive;
    }

    public String getType_bill_receive() {
        return type_bill_receive;
    }

    public void setType_bill_receive(String type_bill_receive) {
        this.type_bill_receive = type_bill_receive;
    }

    public Double getValueBill() {
        return value_bill;
    }

    public void setValueBill(Double value_bill) {
        this.value_bill = value_bill;
    }
}

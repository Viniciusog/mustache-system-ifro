/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author vinij
 */
public class BillReceiveAndSale {

    private Sale sale;
    private BillReceive billReceive;

    public BillReceiveAndSale() {

    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public BillReceive getBillReceive() {
        return billReceive;
    }

    public void setBillReceive(BillReceive billReceive) {
        this.billReceive = billReceive;
    }
}
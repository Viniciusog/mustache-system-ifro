package model;

public class Role {
    
 /* Database info
    id_cargo 
    nome_cargo
    perm_cad_prod_cargo
    perm_cad_func_cargo
    perm_cad_usuario_cargo
    perm_controlar_caixa_cargo
    perm_cad_serv_cargo 
    perm_cad_cliente_cargo
    perm_cad_forn_cargo
    perm_realizar_venda_cargo
    perm_realizar_compra_cargo
    */

    private int id;
    private String name;
    private boolean payBill, receiveBill, addBrand, buy, depositCashier, openCashier, withdrawCashier, addReceivement, addPerson, addSupplier, addProd, receive, addRole, sell, addServ, useReport;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPayBill() {
        return payBill;
    }

    public void setPayBill(boolean payBill) {
        this.payBill = payBill;
    }

    public boolean isReceiveBill() {
        return receiveBill;
    }

    public void setReceiveBill(boolean receiveBill) {
        this.receiveBill = receiveBill;
    }

    public boolean isAddBrand() {
        return addBrand;
    }

    public void setAddBrand(boolean addBrand) {
        this.addBrand = addBrand;
    }

    public boolean isBuy() {
        return buy;
    }

    public void setBuy(boolean buy) {
        this.buy = buy;
    }

    public boolean isDepositCashier() {
        return depositCashier;
    }

    public void setDepositCashier(boolean depositCashier) {
        this.depositCashier = depositCashier;
    }

    public boolean isOpenCashier() {
        return openCashier;
    }

    public void setOpenCashier(boolean openCashier) {
        this.openCashier = openCashier;
    }

    public boolean isWithdrawCashier() {
        return withdrawCashier;
    }

    public void setWithdrawCashier(boolean withdrawCashier) {
        this.withdrawCashier = withdrawCashier;
    }

    public boolean isAddReceivement() {
        return addReceivement;
    }

    public void setAddReceivement(boolean addReceivement) {
        this.addReceivement = addReceivement;
    }

    public boolean isAddPerson() {
        return addPerson;
    }

    public void setAddPerson(boolean addPerson) {
        this.addPerson = addPerson;
    }

    public boolean isAddSupplier() {
        return addSupplier;
    }

    public void setAddSupplier(boolean addSupplier) {
        this.addSupplier = addSupplier;
    }

    public boolean isAddProd() {
        return addProd;
    }

    public void setAddProd(boolean addProd) {
        this.addProd = addProd;
    }

    public boolean isReceive() {
        return receive;
    }

    public void setReceive(boolean receive) {
        this.receive = receive;
    }

    public boolean isAddRole() {
        return addRole;
    }

    public void setAddRole(boolean addRole) {
        this.addRole = addRole;
    }

    public boolean isSell() {
        return sell;
    }

    public void setSell(boolean sell) {
        this.sell = sell;
    }

    public boolean isAddServ() {
        return addServ;
    }

    public void setAddServ(boolean addServ) {
        this.addServ = addServ;
    }

    public void isPayBill(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isUseReport() {
        return useReport;
    }

    public void setUseReport(boolean useReport) {
        this.useReport = useReport;
    }
}
    
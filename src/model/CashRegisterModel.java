package model;

public class CashRegisterModel {
    /*
    id_caixa int not null auto_increment primary key,
    valor_inicial_caixa decimal(20,2) not null,
    valor_final_caixa decimal(20,2) not null,
    data_caixa date not null
     */
    private int id;
    private float initialValue;
    private float endValue;
    private String openDateTime;
    private String closeDateTime;
    private boolean status;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(float initialValue) {
        this.initialValue = initialValue;
    }

    public float getEndValue() {
        return endValue;
    }

    public void setEndValue(float endValue) {
        this.endValue = endValue;
    }
    
    public String getOpenDateTime() {
        return openDateTime;
    }

    public void setOpenDateTime(String openDateTime) {
        this.openDateTime = openDateTime;
    }

    public String getCloseDateTime() {
        return closeDateTime;
    }

    public void setCloseDateTime(String closeDateTime) {
        this.closeDateTime = closeDateTime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

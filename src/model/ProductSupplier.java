package model;

public class ProductSupplier {

    /*
    id_fornecedor int not null auto_increment primary key,
    razao_social_fornecedor varchar(200) not null,
    nome_fantasia_fornecedor varchar(200) not null,
    telefone varchar(30) not null,
    cnpj varchar(30) not null,
    email varchar(100) not null
     */
    private int id;
    private String corporateName;
    private String name;
    private String phoneNumber;
    private String cnpj;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorporateName() {
        return corporateName;
    }

    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override 
    public String toString(){
       return this.getName();
   }
}

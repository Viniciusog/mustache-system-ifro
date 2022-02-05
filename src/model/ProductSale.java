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
public class ProductSale {
    
    Product product;
    int qtdProductSale;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product prod) {
        this.product = prod;
    }

    public int getQtdProductSale() {
        return qtdProductSale;
    }

    public void setQtdProductSale(int qtdProductSale) {
        this.qtdProductSale = qtdProductSale;
    }
    
    
}

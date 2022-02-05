/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dao.BillReceiveDAO;
import dao.BuyDAO;
import dao.CashRegisterDAO;
import dao.PersonDAO;
import dao.ProductDAO;
import dao.ProductSupplierDAO;
import dao.SaleDAO;
import dao.ServiceDAO;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.BillReceive;
import model.BillReceiveAndSale;
import model.Buy;
import model.CashRegisterModel;
import model.Person;
import model.Product;
import model.ProductSupplier;
import model.Sale;
import model.Service;

/**
 *
 * @author vinij
 */
public class Report {

    public static void personReport(String namePerson) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd,MM,yyyy");
            String data = sdf.format(new Date());
            int horas = Calendar.getInstance().getTime().getHours();
            int minutos = Calendar.getInstance().getTime().getMinutes();
            int segundos = Calendar.getInstance().getTime().getSeconds();

            String filename = "RelatorioPessoas-" + data + "-" + horas + "-" + minutos + "-" + segundos;
            String path = "c://mustachesystem/" + filename + ".html";
            File f = new File(path);
            f.createNewFile();

            PersonDAO pdao = new PersonDAO();
            List<Person> listaPessoas = pdao.getListPerson(namePerson);

            SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
            String data2 = sdf2.format(new Date());
            //O true não deixa recriar o arquivo, apenas acrescenta os dados em baixo dos que já tinham
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(path /*,true*/))) {
                bw.write("<!DOCTYPE html>"
                        + "<html>"
                        + "<head>"
                        + "    <meta charset='UTF-8'>"
                        + "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>"
                        + "    <title>Início</title>"
                        + "    <link rel='stylesheet' href='index-style.css'>"
                         + "<style>"
                        + "@import url('https://fonts.googleapis.com/css2?family=Work+Sans&display=swap');"
                        + " html{"
                        + " background-color: #232323;"
                        + "}"
                        + "h2{ "
                        + "color: #ffffff;"
                        + "font-family: 'Work Sans', sans-serif;"
                        + "}"
                        + "h4{"
                        + "color: #ffffff;"
                        + " font-family: 'Work Sans', sans-serif;"
                        + "}"
                        + "table{"
                        + " border-collapse: collapse;"
                        + " font-family: 'Work Sans', sans-serif;"
                        + " margin: 0;"
                        + " margin-left: auto;"
                        + " margin-right: auto;"
                        + " font-size: 15px;"
                        + " border-radius: 5px 5px 0 0;"
                        + " min-width: 400px;"
                        + " overflow: hidden;"
                        + "}"
                        + " table thead tr{"
                        + " background-color: #9933ff;"
                        + " color: white;"
                        + " text-align: center;"
                        + "}"
                        + " tbody{"
                        + " border-bottom: 2px #9933ff;"
                        + " font-family: 'Work Sans', sans-serif;"
                        + "}"
                        + " table th{"
                        + " padding: 12px 15px;"
                        + "}"
                        + " table td{"
                        + " padding: 12px 60px;"
                        + " border-bottom: 1px solid #9933ff;"
                        + " text-align: center;"
                        + " color: #ffffff"
                        + "}"            
                        + "</style>"
                        + "</head>"
                        + "<body>"
                        + "<h2> Relatório de Pessoas </h2>"
                        + "<br>"
                        + "<h4> Data: " + data2 + "</h4>"
                        + "<br>"        
                        + "<table style='width:100%'>"
                        + "<thead>"
                        + " <tr>"
                        + "    <th>Nome</th>"
                        + "    <th>Telefone</th>"
                        + "    <th>Sexo</th>"
                        + "    <th>Cliente</th>"
                        + "    <th>Funcionario</th>"
                        + "</tr>"
                        + "</thead>"
                        + "<tbody>");
                        
                for (Person p : listaPessoas) {
                    bw.write("<tr>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + p.getName() + "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + p.getPhoneNumber() + "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + p.getSex().getName() + "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + p.getIsClient() + "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + p.getIsEmployee() + "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("</tr>");
                }
                bw.write("</tbody>");
                bw.write("</body>");
                bw.write("</html>");
                File htmlFile = new File(path);
                Desktop.getDesktop().browse(htmlFile.toURI());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            Alert.showErrorMessageDialog("Erro ao emitir relatório de pessoas! \n" + ex.getMessage());
        }
    }
    
    public static void stockReport(String productName) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd,MM,yyyy");
            String data = sdf.format(new Date());
            int horas = Calendar.getInstance().getTime().getHours();
            int minutos = Calendar.getInstance().getTime().getMinutes();
            int segundos = Calendar.getInstance().getTime().getSeconds();

            String filename = "RelatorioEstoque-" + data + "-" + horas + "-" + minutos + "-" + segundos;
            String path = "c://mustachesystem/" + filename + ".html";
            File f = new File(path);
            f.createNewFile();         

            SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
            String data2 = sdf2.format(new Date());
            //O true não deixa recriar o arquivo, apenas acrescenta os dados em baixo dos que já tinham
           
            ProductDAO pdao = new ProductDAO();
            List<Product> products = pdao.getListProduct(productName);
             try (BufferedWriter bw = new BufferedWriter(new FileWriter(path /*,true*/))) {
                bw.write("<!DOCTYPE html>"
                        + "<html>"
                        + "<head>"
                        + "    <meta charset='UTF-8'>"
                        + "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>"
                        + "    <title>Início</title>"
                        + "    <link rel='stylesheet' href='index-style.css'>"
                         + "<style>"
                        + "@import url('https://fonts.googleapis.com/css2?family=Work+Sans&display=swap');"
                        + " html{"
                        + " background-color: #232323;"
                        + "}"
                        + "h2{ "
                        + "color: #ffffff;"
                        + "font-family: 'Work Sans', sans-serif;"
                        + "}"
                        + "h4{"
                        + "color: #ffffff;"
                        + " font-family: 'Work Sans', sans-serif;"
                        + "}"
                        + "table{"
                        + " border-collapse: collapse;"
                        + " font-family: 'Work Sans', sans-serif;"
                        + " margin: 0;"
                        + " margin-left: auto;"
                        + " margin-right: auto;"
                        + " font-size: 15px;"
                        + " border-radius: 5px 5px 0 0;"
                        + " min-width: 400px;"
                        + " overflow: hidden;"
                        + "}"
                        + " table thead tr{"
                        + " background-color: #9933ff;"
                        + " color: white;"
                        + " text-align: center;"
                        + "}"
                        + " tbody{"
                        + " border-bottom: 2px #9933ff;"
                        + " font-family: 'Work Sans', sans-serif;"
                        + "}"
                        + " table th{"
                        + " padding: 12px 15px;"
                        + "}"
                        + " table td{"
                        + " padding: 12px 60px;"
                        + " border-bottom: 1px solid #9933ff;"
                        + " text-align: center;"
                        + " color: #ffffff"
                        + "}"         
                        + "</style>"
                        + "</head>"
                        + "<body>"
                        + "<h2> Relatório de Produtos </h2>"
                        + "<br>"
                        + "<h4> Data: " + data2 + "</h4>"
                        + "<br>"        
                        + "<table style='width:100%'>"
                        + "<thead>"
                        + "  <tr>"
                        + "    <th>Código</th>"
                        + "    <th>Nome</th>"
                        + "    <th>Marca</th>"
                        + "    <th>Estoque</th>"
                        + "    <th>Preço</th>"
                        + "</tr>"
                        + "</thead>"
                        + "<tbody>"
                );
                for (Product p : products) {
                    bw.write("<tr>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + p.getId()+ "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + p.getName() + "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + p.getBrandName() + "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + p.getStock() + "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + p.getPrice() + "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("</tr>");
                }
                bw.write("</tbody>");
                bw.write("</body>");
                bw.write("</html>");
                File htmlFile = new File(path);
                Desktop.getDesktop().browse(htmlFile.toURI());
            } catch (IOException e) {
               Alert.showErrorMessageDialog("Erro ao gerar estoque de produtos: " + e.getMessage());
            }
        } catch(Exception e) {
            Alert.showErrorMessageDialog("Erro ao gerar estoque de produtos! \n" + e.getMessage());
        }
    }

    public static void salesReport(int lastSales) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd,MM,yyyy");
            String data = sdf.format(new Date());
            int horas = Calendar.getInstance().getTime().getHours();
            int minutos = Calendar.getInstance().getTime().getMinutes();
            int segundos = Calendar.getInstance().getTime().getSeconds();

            String filename = "RelatorioVendas-" + data + "-" + horas + "-" + minutos + "-" + segundos;
            String path = "c://mustachesystem/" + filename + ".html";
            File f = new File(path);
            f.createNewFile();

            List<BillReceiveAndSale> billsAndSales = SaleDAO.getListSales(lastSales);
            SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
            String data2 = sdf2.format(new Date());
            //O true não deixa recriar o arquivo, apenas acrescenta os dados em baixo dos que já tinham
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(path /*,true*/))) {
                bw.write("<!DOCTYPE html>"
                        + "<html>"
                        + "<head>"
                        + "    <meta charset='UTF-8'>"
                        + "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>"
                        + "    <title>Início</title>"
                        + "</head>"
                        + "<style>"
                        + "@import url('https://fonts.googleapis.com/css2?family=Work+Sans&display=swap');"
                        + " html{"
                        + " background-color: #232323;"
                        + "}"
                        + "h2{ "
                        + "color: #ffffff;"
                        + "font-family: 'Work Sans', sans-serif;"
                        + "}"
                        + "h4{"
                        + "color: #ffffff;"
                        + " font-family: 'Work Sans', sans-serif;"
                        + "}"
                        + "table{"
                        + " border-collapse: collapse;"
                        + " font-family: 'Work Sans', sans-serif;"
                        + " margin: 0;"
                        + " margin-left: auto;"
                        + " margin-right: auto;"
                        + " font-size: 15px;"
                        + " border-radius: 5px 5px 0 0;"
                        + " min-width: 400px;"
                        + " overflow: hidden;"
                        + "}"
                        + " table thead tr{"
                        + " background-color: #9933ff;"
                        + " color: white;"
                        + " text-align: center;"
                        + "}"
                        + " tbody{"
                        + " border-bottom: 2px #9933ff;"
                        + " font-family: 'Work Sans', sans-serif;"
                        + "}"
                        + " table th{"
                        + " padding: 12px 15px;"
                        + "}"
                        + " table td{"
                        + " padding: 12px 60px;"
                        + " border-bottom: 1px solid #9933ff;"
                        + " text-align: center;"
                        + " color: #ffffff"
                        + "}"
                        + "</style>"
                        + "<body>"
                        + "<h2> Relatório de Venda </h2>"
                        + "<br>"
                        + "<h4> Data: " + data2 + "</h4>"
                        + "<br>"
                        + "<table style='width:100%'>"
                        + "<thead>"
                        + "  <tr>"
                        + "    <th>Código</th>"
                        + "    <th>Data</th>"
                        + "    <th>Tipo</th>"
                        + "    <th>Valor</th>"
                        + "    <th>Cliente</th>"
                        + "    <th>CPF Cliente</th>"
                        + "    <th>Data Vencimento</th>"
                        + "    <th>Valor Recebido</th>"
                        + "    <th>Venda quitada?</th>"
                        + "    <th>Descrição</th>"
                        + "</tr>"
                        + "</thead>"
                        + "<tbody>"
                );
                for (BillReceiveAndSale s : billsAndSales) {
                    bw.write("<tr>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + s.getSale().getId() + "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + s.getSale().getDate() + "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + s.getSale().getPaymentKind() + "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + s.getSale().getValue() + "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + s.getSale().getClient().getPerson().getName() + "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + s.getSale().getClient().getPerson().getCpf() + "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + s.getBillReceive().getDue_date_bill_receive() + "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + s.getBillReceive().getValueReceived() + "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + s.getBillReceive().isSettled_bill_receive() + "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + s.getBillReceive().getDescription_bill_receive() + "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("</tr>");
                }
                bw.write("</tbody>");
                bw.write("</body>");
                bw.write("</html>");
                File htmlFile = new File(path);
                Desktop.getDesktop().browse(htmlFile.toURI());
                
            } catch (Exception e) {
                Alert.showErrorMessageDialog("Erro ao emitir relatório de venda! \n" + e.getMessage());
            }

        } catch (Exception ex) {
            Alert.showErrorMessageDialog("Erro ao emitir relatório de venda! \n" + ex.getMessage());
        }

    }
    
    public static void cashierReport() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd,MM,yyyy");
            String data = sdf.format(new Date());
            int horas = Calendar.getInstance().getTime().getHours();
            int minutos = Calendar.getInstance().getTime().getMinutes();
            int segundos = Calendar.getInstance().getTime().getSeconds();

            String filename = "RelatorioCaixa-" + data + "-" + horas + "-" + minutos + "-" + segundos;
            String path = "c://mustachesystem/" + filename + ".html";
            File f = new File(path);
            f.createNewFile();
            
            CashRegisterDAO crDAO = new CashRegisterDAO();
            List<CashRegisterModel> roleCashRegister = crDAO.getListCash("");
            SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
            String data2 = sdf2.format(new Date());
            //O true não deixa recriar o arquivo, apenas acrescenta os dados em baixo dos que já tinham
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(path /*,true*/))) {
                bw.write("<!DOCTYPE html>"
                        + "<html>"
                        + "<head>"
                        + "    <meta charset='UTF-8'>"
                        + "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>"
                        + "    <title>Início</title>"
                        + "</head>"
                        + "<style>"
                        + "@import url('https://fonts.googleapis.com/css2?family=Work+Sans&display=swap');"
                        + " html{"
                        + " background-color: #232323;"
                        + "}"
                        + "h2{ "
                        + "color: #ffffff;"
                        + "font-family: 'Work Sans', sans-serif;"
                        + "}"
                        + "h4{"
                        + "color: #ffffff;"
                        + " font-family: 'Work Sans', sans-serif;"
                        + "}"
                        + "table{"
                        + " border-collapse: collapse;"
                        + " font-family: 'Work Sans', sans-serif;"
                        + " margin: 0;"
                        + " margin-left: auto;"
                        + " margin-right: auto;"
                        + " font-size: 15px;"
                        + " border-radius: 5px 5px 0 0;"
                        + " min-width: 400px;"
                        + " overflow: hidden;"
                        + "}"
                        + " table thead tr{"
                        + " background-color: #9933ff;"
                        + " color: white;"
                        + " text-align: center;"
                        + "}"
                        + " tbody{"
                        + " border-bottom: 2px #9933ff;"
                        + " font-family: 'Work Sans', sans-serif;"
                        + "}"
                        + " table th{"
                        + " padding: 12px 15px;"
                        + "}"
                        + " table td{"
                        + " padding: 12px 60px;"
                        + " border-bottom: 1px solid #9933ff;"
                        + " text-align: center;"
                        + " color: #ffffff"
                        + "}"
                        + "</style>"
                        + "<body>"
                        + "<h2> Relatório de Caixa </h2>"
                        + "<br>"
                        + "<h4> Data: " + data2 + "</h4>"
                        + "<br>"
                        + "<table style='width:100%'>"
                        + "<thead>"
                        + "  <tr>"
                        + "    <th>Código do Caixa</th>"
                        + "    <th>Valor Inicial</th>"
                        + "    <th>Valor Final</th>"
                        + "    <th>Data de Abertura</th>"
                        + "    <th>Data de Fechamento</th>"
                        + "</tr>"
                        + "</thead>"
                        + "<tbody>"
                );
                for (CashRegisterModel r :roleCashRegister) {
                    bw.write("<tr>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + r.getId() + "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + r.getInitialValue() + "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + r.getEndValue() + "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + r.getOpenDateTime() + "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + r.getCloseDateTime() + "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("</tr>");
                }
                bw.write("</tbody>");
                bw.write("</body>");
                bw.write("</html>");
                File htmlFile = new File(path);
                Desktop.getDesktop().browse(htmlFile.toURI());
                
            } catch (Exception e) {
                Alert.showErrorMessageDialog("Erro ao emitir relatório de caixa! \n" + e.getMessage());
            }

        } catch (Exception ex) {
            Alert.showErrorMessageDialog("Erro ao emitir relatório de caixa! \n" + ex.getMessage());
        }

    }
    
        public static void buyReport() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd,MM,yyyy");
            String data = sdf.format(new Date());
            int horas = Calendar.getInstance().getTime().getHours();
            int minutos = Calendar.getInstance().getTime().getMinutes();
            int segundos = Calendar.getInstance().getTime().getSeconds();

            String filename = "RelatorioCompra-" + data + "-" + horas + "-" + minutos + "-" + segundos;
            String path = "c://mustachesystem/" + filename + ".html";
            File f = new File(path);
            f.createNewFile();
            
            BuyDAO bDAO = new BuyDAO();
            List<Buy> buyList = bDAO.getListBuy("");
            SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
            String data2 = sdf2.format(new Date());
            //O true não deixa recriar o arquivo, apenas acrescenta os dados em baixo dos que já tinham
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(path /*,true*/))) {
                bw.write("<!DOCTYPE html>"
                        + "<html>"
                        + "<head>"
                        + "    <meta charset='UTF-8'>"
                        + "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>"
                        + "    <title>Início</title>"
                        + "</head>"
                        + "<style>"
                        + "@import url('https://fonts.googleapis.com/css2?family=Work+Sans&display=swap');"
                        + " html{"
                        + " background-color: #232323;"
                        + "}"
                        + "h2{ "
                        + "color: #ffffff;"
                        + "font-family: 'Work Sans', sans-serif;"
                        + "}"
                        + "h4{"
                        + "color: #ffffff;"
                        + " font-family: 'Work Sans', sans-serif;"
                        + "}"
                        + "table{"
                        + " border-collapse: collapse;"
                        + " font-family: 'Work Sans', sans-serif;"
                        + " margin: 0;"
                        + " margin-left: auto;"
                        + " margin-right: auto;"
                        + " font-size: 15px;"
                        + " border-radius: 5px 5px 0 0;"
                        + " min-width: 400px;"
                        + " overflow: hidden;"
                        + "}"
                        + " table thead tr{"
                        + " background-color: #9933ff;"
                        + " color: white;"
                        + " text-align: center;"
                        + "}"
                        + " tbody{"
                        + " border-bottom: 2px #9933ff;"
                        + " font-family: 'Work Sans', sans-serif;"
                        + "}"
                        + " table th{"
                        + " padding: 12px 15px;"
                        + "}"
                        + " table td{"
                        + " padding: 12px 60px;"
                        + " border-bottom: 1px solid #9933ff;"
                        + " text-align: center;"
                        + " color: #ffffff"
                        + "}"
                        + "</style>"
                        + "<body>"
                        + "<h2> Relatório de Compras </h2>"
                        + "<br>"
                        + "<h4> Data: " + data2 + "</h4>"
                        + "<br>"
                        + "<table style='width:100%'>"
                        + "<thead>"
                        + "  <tr>"
                        + "    <th>Código da Compra</th>"
                        + "    <th>Nome do Fornecedor</th>"
                        + "    <th>Forma de Pagamento</th>"
                        + "    <th>Valor da Compra</th>"
                        + "    <th>Data de Emissão</th>"
                        + "    <th>Quantidade de Parcelas</th>"
                        + "</tr>"
                        + "</thead>"
                        + "<tbody>"
                );
                for (Buy b : buyList) {
                    bw.write("<tr>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + b.getId() + "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + b.getId_supplier().getName() + "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + b.getPaymentMethod() + "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + b.getTotalValue()+ "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + b.getDate()+ "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + b.getInstallments()+ "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("</tr>");
                }
                bw.write("</tbody>");
                bw.write("</body>");
                bw.write("</html>");
                File htmlFile = new File(path);
                Desktop.getDesktop().browse(htmlFile.toURI());
                
            } catch (Exception e) {
                Alert.showErrorMessageDialog("Erro ao emitir relatório de compra! \n" + e.getMessage());
            }

        } catch (Exception ex) {
            Alert.showErrorMessageDialog("Erro ao emitir relatório de compra! \n" + ex.getMessage());
        }

    }
        
    public static void serviceReport() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd,MM,yyyy");
            String data = sdf.format(new Date());
            int horas = Calendar.getInstance().getTime().getHours();
            int minutos = Calendar.getInstance().getTime().getMinutes();
            int segundos = Calendar.getInstance().getTime().getSeconds();

            String filename = "RelatorioServicos-" + data + "-" + horas + "-" + minutos + "-" + segundos;
            String path = "c://mustachesystem/" + filename + ".html";
            File f = new File(path);
            f.createNewFile();
            
            ServiceDAO sDAO = new ServiceDAO();
            List<Service> serviceList = sDAO.getListService("");
            SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
            String data2 = sdf2.format(new Date());
            //O true não deixa recriar o arquivo, apenas acrescenta os dados em baixo dos que já tinham
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(path /*,true*/))) {
                bw.write("<!DOCTYPE html>"
                        + "<html>"
                        + "<head>"
                        + "    <meta charset='UTF-8'>"
                        + "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>"
                        + "    <title>Início</title>"
                        + "</head>"
                        + "<style>"
                        + "@import url('https://fonts.googleapis.com/css2?family=Work+Sans&display=swap');"
                        + " html{"
                        + " background-color: #232323;"
                        + "}"
                        + "h2{ "
                        + "color: #ffffff;"
                        + "font-family: 'Work Sans', sans-serif;"
                        + "}"
                        + "h4{"
                        + "color: #ffffff;"
                        + " font-family: 'Work Sans', sans-serif;"
                        + "}"
                        + "table{"
                        + " border-collapse: collapse;"
                        + " font-family: 'Work Sans', sans-serif;"
                        + " margin: 0;"
                        + " margin-left: auto;"
                        + " margin-right: auto;"
                        + " font-size: 15px;"
                        + " border-radius: 5px 5px 0 0;"
                        + " min-width: 400px;"
                        + " overflow: hidden;"
                        + "}"
                        + " table thead tr{"
                        + " background-color: #9933ff;"
                        + " color: white;"
                        + " text-align: center;"
                        + "}"
                        + " tbody{"
                        + " border-bottom: 2px #9933ff;"
                        + " font-family: 'Work Sans', sans-serif;"
                        + "}"
                        + " table th{"
                        + " padding: 12px 15px;"
                        + "}"
                        + " table td{"
                        + " padding: 12px 60px;"
                        + " border-bottom: 1px solid #9933ff;"
                        + " text-align: center;"
                        + " color: #ffffff"
                        + "}"
                        + "</style>"
                        + "<body>"
                        + "<h2> Relatório de Serviço </h2>"
                        + "<br>"
                        + "<h4> Data: " + data2 + "</h4>"
                        + "<br>"
                        + "<table style='width:100%'>"
                        + "<thead>"
                        + "  <tr>"
                        + "    <th>Código do Serviço</th>"
                        + "    <th>Nome</th>"
                        + "    <th>Descrição</th>"
                        + "    <th>Duração</th>"
                        + "    <th>Preço do Serviço</th>"
                        + "</tr>"
                        + "</thead>"
                        + "<tbody>"
                );
                for (Service s : serviceList) {
                    bw.write("<tr>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + s.getIdService()+ "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + s.getName() + "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + s.getDescription() + "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + s.getDurationService() + "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + s.getPrice()+ "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("</tr>");
                }
                bw.write("</tbody>");
                bw.write("</body>");
                bw.write("</html>");
                File htmlFile = new File(path);
                Desktop.getDesktop().browse(htmlFile.toURI());
                
            } catch (Exception e) {
                Alert.showErrorMessageDialog("Erro ao emitir relatório de serviço! \n" + e.getMessage());
            }

        } catch (Exception ex) {
            Alert.showErrorMessageDialog("Erro ao emitir relatório de serviço! \n" + ex.getMessage());
        }

    }

    public static void supplierReport() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd,MM,yyyy");
            String data = sdf.format(new Date());
            int horas = Calendar.getInstance().getTime().getHours();
            int minutos = Calendar.getInstance().getTime().getMinutes();
            int segundos = Calendar.getInstance().getTime().getSeconds();

            String filename = "RelatorioFornecedores-" + data + "-" + horas + "-" + minutos + "-" + segundos;
            String path = "c://mustachesystem/" + filename + ".html";
            File f = new File(path);
            f.createNewFile();
            
            ProductSupplierDAO psDAO = new ProductSupplierDAO();
            List<ProductSupplier> psList = psDAO.getListProductSuppliers("");
            SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
            String data2 = sdf2.format(new Date());
            //O true não deixa recriar o arquivo, apenas acrescenta os dados em baixo dos que já tinham
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(path /*,true*/))) {
                bw.write("<!DOCTYPE html>"
                        + "<html>"
                        + "<head>"
                        + "    <meta charset='UTF-8'>"
                        + "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>"
                        + "    <title>Início</title>"
                        + "</head>"
                        + "<style>"
                        + "@import url('https://fonts.googleapis.com/css2?family=Work+Sans&display=swap');"
                        + " html{"
                        + " background-color: #232323;"
                        + "}"
                        + "h2{ "
                        + "color: #ffffff;"
                        + "font-family: 'Work Sans', sans-serif;"
                        + "}"
                        + "h4{"
                        + "color: #ffffff;"
                        + " font-family: 'Work Sans', sans-serif;"
                        + "}"
                        + "table{"
                        + " border-collapse: collapse;"
                        + " font-family: 'Work Sans', sans-serif;"
                        + " margin: 0;"
                        + " margin-left: auto;"
                        + " margin-right: auto;"
                        + " font-size: 15px;"
                        + " border-radius: 5px 5px 0 0;"
                        + " min-width: 400px;"
                        + " overflow: hidden;"
                        + "}"
                        + " table thead tr{"
                        + " background-color: #9933ff;"
                        + " color: white;"
                        + " text-align: center;"
                        + "}"
                        + " tbody{"
                        + " border-bottom: 2px #9933ff;"
                        + " font-family: 'Work Sans', sans-serif;"
                        + "}"
                        + " table th{"
                        + " padding: 12px 15px;"
                        + "}"
                        + " table td{"
                        + " padding: 12px 60px;"
                        + " border-bottom: 1px solid #9933ff;"
                        + " text-align: center;"
                        + " color: #ffffff"
                        + "}"
                        + "</style>"
                        + "<body>"
                        + "<h2> Relatório de Compras </h2>"
                        + "<br>"
                        + "<h4> Data: " + data2 + "</h4>"
                        + "<br>"
                        + "<table style='width:100%'>"
                        + "<thead>"
                        + "  <tr>"
                        + "    <th>Código do Fornecedor</th>"
                        + "    <th>Razão Social</th>"
                        + "    <th>Nome Fantasia</th>"
                        + "    <th>Telefone</th>"
                        + "    <th>CNPJ</th>"
                        + "    <th>E-mail</th>"
                        + "</tr>"
                        + "</thead>"
                        + "<tbody>"
                );
                for (ProductSupplier ps : psList) {
                    bw.write("<tr>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + ps.getId() + "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + ps.getName() + "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + ps.getCorporateName() + "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + ps.getPhoneNumber()+ "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + ps.getCnpj()+ "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("<td>" + ps.getEmail()+ "</td>");
                    bw.newLine(); //Quebra de linha
                    bw.write("</tr>");
                }
                bw.write("</tbody>");
                bw.write("</body>");
                bw.write("</html>");
                File htmlFile = new File(path);
                Desktop.getDesktop().browse(htmlFile.toURI());
                
            } catch (Exception e) {
                Alert.showErrorMessageDialog("Erro ao emitir relatório de fornecedores! \n" + e.getMessage());
            }

        } catch (Exception ex) {
            Alert.showErrorMessageDialog("Erro ao emitir relatório de fornecedor! \n" + ex.getMessage());
        }

    }
          
    public static void main(String[] args) throws IOException {

        personReport("");
        salesReport(20);
        stockReport("");
    }
}

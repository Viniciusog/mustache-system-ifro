package view;

import dao.ServiceDAO;
import java.awt.Color;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;
import model.Service;

/**
 *
 * @author lucas
 */
public class ServiceRegisterView extends javax.swing.JDialog {

    private JSpinner jSpinner1;

    /**
     * Creates new form ServiceRegisterView
     */
    public ServiceRegisterView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        buildTable("");
        update = false;
        lblID.setVisible(false);
        editId.setVisible(false);
        btnUpdate.setVisible(false);
    }

    public JPanel getPainel() {
        return this.painelService;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelService = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        Date date = new Date ();
        SpinnerDateModel sp = new SpinnerDateModel (date, null, null, Calendar.HOUR_OF_DAY);
        jSpinner1 = new javax.swing.JSpinner (sp);
        JSpinner.DateEditor de = new JSpinner.DateEditor(jSpinner1, "hh:mm:ss");
        jSpinner1.setEditor(de);
        jPanel2 = new javax.swing.JPanel();
        spinnerTime = new javax.swing.JSpinner();
        editName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        editId = new javax.swing.JTextField();
        lblID = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        edDrescription = new javax.swing.JTextArea();
        editPrice = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableServices = new javax.swing.JTable();
        EditBt = new javax.swing.JButton();
        DeleteBt = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        editSearch = new javax.swing.JTextField();
        btnNew = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastrar Serviços");
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(51, 51, 51));
        setResizable(false);

        painelService.setBackground(new java.awt.Color(90, 90, 90));

        jTabbedPane2.setBackground(new java.awt.Color(90, 90, 90));
        jTabbedPane2.setForeground(new java.awt.Color(30, 30, 30));
        jTabbedPane2.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jTabbedPane2.setName(""); // NOI18N

        jPanel2.setBackground(new java.awt.Color(90, 90, 90));

        spinnerTime.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        spinnerTime.setToolTipText("Duração em minutos");
        spinnerTime.setBorder(null);

        editName.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        editName.setBorder(null);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("NOME");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("DESCRIÇÃO");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("PREÇO");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CADASTRAR SERVIÇO");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("DURAÇÃO");

        editId.setEditable(false);
        editId.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        editId.setBorder(null);

        lblID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblID.setForeground(new java.awt.Color(255, 255, 255));
        lblID.setText("ID");

        btnSave.setBackground(new java.awt.Color(99, 154, 103));
        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("Salvar");
        btnSave.setBorder(null);
        btnSave.setBorderPainted(false);
        btnSave.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSave.setFocusPainted(false);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnCancel.setBackground(new java.awt.Color(200, 76, 88));
        btnCancel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setText("Cancelar");
        btnCancel.setBorder(null);
        btnCancel.setBorderPainted(false);
        btnCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCancel.setFocusPainted(false);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(87, 126, 244));
        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Atualizar");
        btnUpdate.setBorder(null);
        btnUpdate.setBorderPainted(false);
        btnUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnUpdate.setFocusPainted(false);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        edDrescription.setColumns(20);
        edDrescription.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        edDrescription.setRows(5);
        jScrollPane2.setViewportView(edDrescription);

        editPrice.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        editPrice.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                editPriceFocusLost(evt);
            }
        });
        editPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editPriceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(editName, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(editPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(spinnerTime, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(230, 230, 230)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, Short.MAX_VALUE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblID, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editId, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(60, 60, 60))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(235, 235, 235)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addComponent(lblID)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editId, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(editName, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spinnerTime, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2))
                .addGap(196, 196, 196))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(134, 134, 134))
        );

        jTabbedPane2.addTab("CADASTRAR SERVIÇO", jPanel2);

        jPanel3.setBackground(new java.awt.Color(90, 90, 90));

        tableServices.setForeground(new java.awt.Color(51, 51, 51));
        tableServices.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome do Serviço", "Duração", "Preço", "Descrição"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableServices.setRowHeight(25);
        jScrollPane1.setViewportView(tableServices);

        EditBt.setBackground(new java.awt.Color(87, 126, 244));
        EditBt.setForeground(new java.awt.Color(255, 255, 255));
        EditBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/baseline_edit_white_18dp.png"))); // NOI18N
        EditBt.setBorderPainted(false);
        EditBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditBtActionPerformed(evt);
            }
        });

        DeleteBt.setBackground(new java.awt.Color(200, 76, 88));
        DeleteBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/baseline_delete_white_18dp.png"))); // NOI18N
        DeleteBt.setBorderPainted(false);
        DeleteBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteBtActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/baseline_search_white_18dp.png"))); // NOI18N

        editSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editSearchActionPerformed(evt);
            }
        });
        editSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                editSearchKeyTyped(evt);
            }
        });

        btnNew.setBackground(new java.awt.Color(99, 154, 103));
        btnNew.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNew.setForeground(new java.awt.Color(99, 154, 103));
        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/baseline_add_white_18dp.png"))); // NOI18N
        btnNew.setBorderPainted(false);
        btnNew.setFocusable(false);
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("LISTA DE SERVIÇOS");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(EditBt, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(DeleteBt, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                .addComponent(editSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(88, 88, 88))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(257, 257, 257)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(EditBt, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DeleteBt, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(editSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("LISTAR SERVIÇOS", jPanel3);

        javax.swing.GroupLayout painelServiceLayout = new javax.swing.GroupLayout(painelService);
        painelService.setLayout(painelServiceLayout);
        painelServiceLayout.setHorizontalGroup(
            painelServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelServiceLayout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 741, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        painelServiceLayout.setVerticalGroup(
            painelServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jTabbedPane2.getAccessibleContext().setAccessibleName("Lista de serviços ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(painelService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DeleteBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteBtActionPerformed
        int selectedRow = tableServices.getSelectedRow();
        if (selectedRow >= 0) {
            Service service = new Service();
            service.setIdService(Integer.parseInt(tableServices.getValueAt(selectedRow, 0).toString()));
            ServiceDAO.delete(service);
            buildTable("");
            jTabbedPane2.setSelectedIndex(1);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um item da tabela!");
        }
    }//GEN-LAST:event_DeleteBtActionPerformed

    private void EditBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditBtActionPerformed
        int selectedRow = tableServices.getSelectedRow();
        if (selectedRow >= 0) {
            editId.setText(tableServices.getValueAt(selectedRow, 0).toString());
            editName.setText(tableServices.getValueAt(selectedRow, 1).toString());
            spinnerTime.setValue(tableServices.getValueAt(selectedRow, 2));
            editPrice.setText(tableServices.getValueAt(selectedRow, 3).toString());
            edDrescription.setText(tableServices.getValueAt(selectedRow, 4).toString());
            lblID.setVisible(true);
            editId.setVisible(true);
            btnUpdate.setVisible(true);
            btnSave.setVisible(false);
            //MAKE UPDATE ATRIBUTTE TRUE
            update = true;
            jTabbedPane2.setSelectedIndex(0);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um item da tabela!");
        }

    }//GEN-LAST:event_EditBtActionPerformed

    private void editSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editSearchActionPerformed

    }//GEN-LAST:event_editSearchActionPerformed

    private void editSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_editSearchKeyTyped
        buildTable(editSearch.getText());
    }//GEN-LAST:event_editSearchKeyTyped

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        jTabbedPane2.setSelectedIndex(0);
        editName.setFocusable(true);
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        saveService();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        resetValuesTextFieldAndSpinner();
    }//GEN-LAST:event_btnCancelActionPerformed


    private void btnCancelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseExited

        btnCancel.setBackground(new Color(216, 83, 76));
    }//GEN-LAST:event_btnCancelMouseExited

    private void btnCancelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseEntered
        // TODO add your handling code here:
        btnCancel.setBackground(new Color(188, 77, 71));
    }//GEN-LAST:event_btnCancelMouseEntered

    private void btnSaveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseEntered
        // TODO add your handling code here:
        btnSave.setBackground(new Color(80, 156, 81));
    }//GEN-LAST:event_btnSaveMouseEntered


    
    private void btnSaveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseExited

// TODO add your handling code here:
            btnSave.setBackground(new Color(88, 185, 90));

    }//GEN-LAST:event_btnSaveMouseExited

    private void btnSaveMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMousePressed
        // TODO add your handling code here:
        saveService();
    }//GEN-LAST:event_btnSaveMousePressed

    private void btnSaveMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSaveMouseReleased

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (!verifyValuesIfItsNotEmpty()) {
            Service service = new Service();
            service.setIdService(Integer.parseInt(editId.getText()));
            service.setDescription(edDrescription.getText());
            service.setDurationService((int) spinnerTime.getValue());
            service.setName(editName.getText());
            service.setPrice(Float.parseFloat(editPrice.getText()));
            ServiceDAO.update(service);
            resetValuesTextFieldAndSpinner();
            buildTable("");
            jTabbedPane2.setSelectedIndex(1);
        } else {
            alert("Existe um campo vazio!");
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void editPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editPriceActionPerformed

    private void editPriceFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_editPriceFocusLost
        // TODO add your handling code here:
        if (editPrice.getText() != null) {
            String sv = editPrice.getText();
            String vsf = sv.replace("R$", "").replace(" ", "").replace(".", "").replace(",", ".");
            Double value = new Double(vsf);
            NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            String formattedValue = nf.format(value);
            editPrice.setText(formattedValue);
        }
    }//GEN-LAST:event_editPriceFocusLost

    private void resetValuesTextFieldAndSpinner() {
        edDrescription.setText("");
        editName.setText("");
        editPrice.setText("");
        spinnerTime.setValue(0);
        editName.setFocusable(true);
    }

    private void saveService() {
        if (!verifyValuesIfItsNotEmpty()) {
            Service service = new Service();
            service.setDescription(edDrescription.getText());
            service.setDurationService((int) spinnerTime.getValue());
            service.setName(editName.getText());
            service.setPrice(Float.parseFloat(editPrice.getText().substring(3).replace(".","").replace(",",".")));
            ServiceDAO.insert(service);
            resetValuesTextFieldAndSpinner();
            buildTable("");
            jTabbedPane2.setSelectedIndex(1);
        } else {
            alert("Existe um campo vazio!");
        }
    }

    private boolean update = false;

    private boolean verifyValuesIfItsNotEmpty() {
        boolean haveEmptyValue = false;

        if (edDrescription.getText().isEmpty()) {
            haveEmptyValue = true;
        }
        if (editName.getText().isEmpty()) {
            haveEmptyValue = true;
        }
        if (editPrice.getText().isEmpty()) {
            haveEmptyValue = true;
        }
        int spinnerValue = (int) spinnerTime.getValue();
        if (spinnerValue <= 0) {
            haveEmptyValue = true;
        }
        return haveEmptyValue;
    }

    private void alert(String msg) {
        JOptionPane.showMessageDialog(rootPane, msg, null, JOptionPane.WARNING_MESSAGE);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ServiceRegisterView dialog = new ServiceRegisterView(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    public void buildTable(String name) {
        ServiceDAO serviceDao = new ServiceDAO();
        List<Service> list = serviceDao.getListService(name);
        DefaultTableModel tableModel = (DefaultTableModel) tableServices.getModel();
        tableModel.setRowCount(0);

        for (Service service : list) {
            tableModel.addRow(new Object[]{
                service.getIdService(),
                service.getName(),
                service.getDurationService(),
                service.getPrice(),
                service.getDescription()
            });
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DeleteBt;
    private javax.swing.JButton EditBt;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JTextArea edDrescription;
    private javax.swing.JTextField editId;
    private javax.swing.JTextField editName;
    private javax.swing.JFormattedTextField editPrice;
    private javax.swing.JTextField editSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel lblID;
    private javax.swing.JPanel painelService;
    private javax.swing.JSpinner spinnerTime;
    private javax.swing.JTable tableServices;
    // End of variables declaration//GEN-END:variables
}
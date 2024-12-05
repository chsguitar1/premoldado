/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TelaPedCompra.java
 *
 * Created on 28/02/2011, 23:49:35
 */
package telaMovimenacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import persistencia.Itenscotprod;
import persistencia.Itenspedcompra;
import persistencia.Pedcompra;
import Banco.Conexao;
import premoldados.TelaSistema;
import Telacadastros.TelaFornecedor;
import Telacadastros.TelaMateriaPrima;
import Telaconsulta.TelaConsMov;
import ajuda.PlayMovie;
import componentes.AcaTabela;

/**
 *
 * @author cristiano
 */
public class TelaPedCompra extends TelaMovimenta implements InternalFrameListener, KeyListener, MouseListener, ActionListener {

    public AcaTabela jTabela = new AcaTabela();
    TelaConsultaPed consultaPed;
    TelaMateriaPrima telamateria;
    TelaFornecedor telafornecedor;
    TelaConsMov telaconsultamat;
    TelaConsMov telaconsultafor;
    Pedcompra pedcompraper = new Pedcompra();
    Itenspedcompra itenspedcompraper = new Itenspedcompra();
    Itenscotprod itenscotPer = new Itenscotprod();
    public Vector colu = new Vector();

    /** Creates new form TelaPedCompra */
    public TelaPedCompra() {
        initComponents();
        campos.add(acaTextfieldCodItens);
        campos.add(acaBotoesAddItes);
     
        campos.add(acaBotoesDelItens);
        campos.add(acaBotoesDelSelIten);
        campos.add(acaTextfieldDes);
      
        campos.add(acaMonetarioSubtot);
        campos.add(acaMonetarioTotal);
        campos.add(acaMonetarioUnit);
        campos.add(acaDataPed);
        campos.add(acaDataPrevPed);
        campos.add(acaTextfieldCodigoPed);
       
        campos.add(acaTextfieldQtde);
        //campos.add(acaComboFornecedor);
        jTextAreaObs.setEnabled(false);
        habilitaCampos(false);
        acaLabelID.setText("Cotação");
        acaLabelCodigo.setVisible(false);
        acaLabelNome.setVisible(false);
        acaTextfieldCodigo.setVisible(false);
        acaTextfieldNome.setVisible(false);
        acaComboTipo.buscaResult("select distinct cotcod,cotcod from cotfornecedor where codstatus = " + FECHADO + "");
       
        jTabela.montaTabela(new String[]{"Codigo", "Descrição", "Quantidade", "Valor Unitario", "Subtotal"}, new int[]{70, 200, 100, 100, 100});
        acaTextfieldQtde.addKeyListener(this);
        acaMonetarioUnit.addKeyListener(this);
        jTabela.addMouseListener(this);
        acaLabelFornecedor.setVisible(false);
    }

    @Override
    protected void incluir() {
        super.incluir();

        jTextAreaObs.setEnabled(true);
    }

    @Override
    protected void alterar() {
        super.alterar();
        
        acaComboFornecedor.setEnabled(false);
        acaComboTipo.setEnabled(false);
       
       
    }

    @Override
    protected void consultar() {
        if (consultaPed == null) {
            consultaPed = new TelaConsultaPed(this, 2);
            consultaPed.tabela.montaTabela(new String[]{"Codigo", "Cliente", "Data", "Valor"}, new int[]{90, 200, 90, 120});
            consultaPed.setCAMPOCODIGO("pecomcod");
            consultaPed.setCAMPODATA("pecomdata");
            consultaPed.setCAMPONOME("fonome");
            consultaPed.setCAMPOVLR("pedcomtotal");
            consultaPed.setTABELA("pedcompra");
            consultaPed.setTABELA2("fornecedor");
            consultaPed.setCHAVETABELA("forcod");
            consultaPed.setCAMPOSTATUS(("pedcomstatus"));
            consultaPed.acaCombosStatus.comboFiltro(new int[]{FECHADO, ABERTO, CANCELADO}, new String[]{"Fechado", "Aberto", "Cancelado"});

            consultaPed.setVisible(true);
            consultaPed.addInternalFrameListener(this);

            TelaSistema.jdp.add(consultaPed);


        }
        TelaSistema.jdp.moveToFront(consultaPed);
        tipoOperacao = PESQUISANDO;
    }

    @Override
    protected void cancelar() {
        super.cancelar();
        jTextAreaObs.setEnabled(false);
        jTabela.limparPesquisa();
        acaLabelFornecedor.setVisible(false);
        acaComboFornecedor.setVisible(true);
    }

    @Override
    public void obterCampos() {
        if (tipoOperacao == ALTERANDO || tipoOperacao == EXCLUINDO) {
            pedcompraper.setPecomcod(Integer.parseInt(acaTextfieldCodigoPed.getText()));
        }
        pedcompraper.setForcod(acaComboFornecedor.getValor());
        pedcompraper.setPecomdata(acaDataPed.getValor());
        pedcompraper.setPedcomstatus(acaCombostatus.getValor());
        pedcompraper.setPedcomtotal(Double.parseDouble(acaMonetarioTotal.getValor()));
        pedcompraper.setPedobs(jTextAreaObs.getText());
        pedcompraper.setPeddatafim(acaDataPrevPed.getValor());

    }
protected void confirmar(){
super.confirmar();
    if(tipoOperacao == ALTERANDO){


}

}
    @Override
    public void preencheCampos() {
    }

    @Override
    protected boolean incluirBD() {
        obterCampos();
        if (pedcompraper.verificaDuplicidade()) {
            ResultSet rs = null;
            try {
                rs = pedcompraper.getInsereRs();
                rs.next();
                itenspedcompraper.setPecomcod(rs.getInt(1));
                for (int x = 0; x < jTabela.getRowCount(); x++) {
                    itenspedcompraper.setMatcod(Integer.parseInt(jTabela.getValueAt(x, 0).toString()));
                    itenspedcompraper.setItepedqtde(Float.parseFloat(jTabela.getValueAt(x, 2).toString()));
                    itenspedcompraper.setItepedunit(Double.parseDouble(jTabela.getValueAt(x, 3).toString()));
                    itenspedcompraper.setItepedsubtotal(Double.parseDouble(jTabela.getValueAt(x, 4).toString()));


                    Conexao.executaSql(itenspedcompraper.getInsereSql());
                }
                Conexao.executaSql("update cotfornecedor set codstatus = " + PEDIDO + " where cotcod = " + acaComboTipo.getValor() + " and forcod = " + acaComboFornecedor.getValor() + " ");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            jTabela.limparPesquisa();
            jTextAreaObs.setText("");
            jTextAreaObs.setEnabled(false);


            return true;
        }
        return false;

    }
  @Override
    protected void ajuda() {
        PlayMovie videos = new PlayMovie("pedidocompra.wmv");


    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        acaLabel1 = new componentes.AcaLabel();
        acaTextfieldCodigoPed = new componentes.AcaTextfield(false, "codigo", 1);
        acaLabel2 = new componentes.AcaLabel();
        acaLabel3 = new componentes.AcaLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaObs = new javax.swing.JTextArea();
        acaLabel12 = new componentes.AcaLabel();
        acaDataPed = new componentes.AcaData(true,"Data do pedido");
        acaDataPrevPed = new componentes.AcaData(true,"Data Prevista");
        acaComboFornecedor = new componentes.AcaCombo(true, "Importar cotação");
        acaLabel10 = new componentes.AcaLabel();
        acaLabelFornecedor = new componentes.AcaLabel();
        jPanel2 = new javax.swing.JPanel();
        acaLabel5 = new componentes.AcaLabel();
        acaLabel6 = new componentes.AcaLabel();
        acaLabel7 = new componentes.AcaLabel();
        acaLabel8 = new componentes.AcaLabel();
        acaTextfieldCodItens = new componentes.AcaTextfield();
        acaTextfieldDes = new componentes.AcaTextfield();
        acaTextfieldQtde = new componentes.AcaTextfield(false, "", 1);
        acaBotoesAddItes = new componentes.AcaBotoes(false,"","");
        acaBotoesDelItens = new componentes.AcaBotoes(false,null,null);
        jScrollPane1 = new javax.swing.JScrollPane(jTabela);
        acaBotoesDelSelIten = new componentes.AcaBotoes(false,"","Apagar item selecionado");
        acaMonetarioUnit = new componentes.AcaMonetario(false,"Valor unitario",1);
        acaLabel4 = new componentes.AcaLabel();
        acaLabel9 = new componentes.AcaLabel();
        acaMonetarioSubtot = new componentes.AcaMonetario(false,"subtotal",1);

        setSize(new java.awt.Dimension(1169, 665));
        setTitle("Pedido de Compra");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        acaLabel1.setText("Codigo");

        acaLabel2.setText("Data do Pedido");

        acaLabel3.setText("Observações");

        jTextAreaObs.setColumns(20);
        jTextAreaObs.setRows(5);
        jScrollPane2.setViewportView(jTextAreaObs);

        acaLabel12.setText("Data de Validade");

        acaComboFornecedor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                acaComboFornecedorItemStateChanged(evt);
            }
        });

        acaLabel10.setText("Fornecedor :");

        acaLabelFornecedor.setText("acaLabel11");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acaLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaTextfieldCodigoPed, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acaDataPed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acaLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaDataPrevPed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acaLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(acaLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(acaComboFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(acaLabelFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acaLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaComboFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabelFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acaLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(acaTextfieldCodigoPed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(acaDataPed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(acaDataPrevPed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Itens do pedido"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        acaLabel5.setText("Codigo");
        jPanel2.add(acaLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        acaLabel6.setText("Descrição");
        jPanel2.add(acaLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, -1, -1));

        acaLabel7.setText("Quantidade");
        jPanel2.add(acaLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 30, -1, -1));
        jPanel2.add(acaLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1041, 69, -1, -1));

        acaTextfieldCodItens.setEditable(false);
        acaTextfieldCodItens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaTextfieldCodItensActionPerformed(evt);
            }
        });
        jPanel2.add(acaTextfieldCodItens, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        acaTextfieldDes.setEditable(false);
        jPanel2.add(acaTextfieldDes, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 366, -1));
        jPanel2.add(acaTextfieldQtde, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, 76, -1));

        acaBotoesAddItes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/edit_add1.png"))); // NOI18N
        acaBotoesAddItes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaBotoesAddItesActionPerformed(evt);
            }
        });
        jPanel2.add(acaBotoesAddItes, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 50, -1, 30));

        acaBotoesDelItens.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/x.png"))); // NOI18N
        acaBotoesDelItens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaBotoesDelItensActionPerformed(evt);
            }
        });
        jPanel2.add(acaBotoesDelItens, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 50, -1, 30));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabela de Itens do Pedido"));
        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 550, 151));

        acaBotoesDelSelIten.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/edit_remove.png"))); // NOI18N
        acaBotoesDelSelIten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaBotoesDelSelItenActionPerformed(evt);
            }
        });
        jPanel2.add(acaBotoesDelSelIten, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 130, -1, 30));

        acaMonetarioUnit.setText("acaMonetario1");
        jPanel2.add(acaMonetarioUnit, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 50, 113, -1));

        acaLabel4.setText("Valor Unitario");
        jPanel2.add(acaLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 30, -1, -1));

        acaLabel9.setText("Subtotal");
        jPanel2.add(acaLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 30, -1, -1));

        acaMonetarioSubtot.setText("acaMonetario2");
        jPanel2.add(acaMonetarioSubtot, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 50, 113, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getPainel());
        getPainel().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 969, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void acaTextfieldCodItensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaTextfieldCodItensActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_acaTextfieldCodItensActionPerformed

    private void acaBotoesAddItesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaBotoesAddItesActionPerformed
        if (acaTextfieldQtde.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe a quantidade do Materia");

            return;
        } else {
            addItem();

            // acaMonetarioTotal.setValor(Double.parseDouble(calculaTotal(acaMonetarioVlrUnit.getValor().toString())));
            acaTextfieldCodItens.setText("");
            acaTextfieldDes.setText("");
            acaTextfieldQtde.setText("");
            acaMonetarioUnit.setValor(0.00);
            acaMonetarioSubtot.setValor(0.00);

        
            calculaTotal();

        }
}//GEN-LAST:event_acaBotoesAddItesActionPerformed

    private void acaBotoesDelItensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaBotoesDelItensActionPerformed
        acaTextfieldCodItens.setText("");
        acaTextfieldDes.setText("");
        acaMonetarioUnit.setValor(0.00);
        acaTextfieldQtde.setText("");
   
        acaMonetarioSubtot.setValor(0.00);
        if (!acaMonetarioSubtot.getValor().equals("")) {
            calculaTotal();

        }

}//GEN-LAST:event_acaBotoesDelItensActionPerformed

    private void acaBotoesDelSelItenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaBotoesDelSelItenActionPerformed
        if (tipoOperacao == ALTERANDO) {
//            itePedVenda.setVencod(Integer.parseInt(acaTextfieldCodItens.getText()));
//            itePedVenda.setProdcod(Integer.parseInt(dtm.getValueAt(jtTabela.getSelectedRow(), 0).toString()));
//            Conexao.executaSql(itePedVenda.getExcluiSql());
            jTabela.dtm.removeRow(jTabela.getSelectedRow());

        } else {
            jTabela.dtm.removeRow(jTabela.getSelectedRow());
            //acaMonetarioTotal.setValor(Double.parseDouble(calculaTotal(acaMonetarioUnit.getValor().toString())));

        }
}//GEN-LAST:event_acaBotoesDelSelItenActionPerformed

    private void acaComboFornecedorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_acaComboFornecedorItemStateChanged
        if (acaComboFornecedor.getSelectedIndex() >= 0) {
            if (acaComboFornecedor.isPopupVisible()) {

                jTabela.montaTabela("SELECT I1.MATCOD,M1.MATNOME,I1.ITENDCOTQTDE,I1.ITENSCOTUNT,I1.ITENSCOTSUBTOTAL "
                        + "FROM ITENSCODPROD I1, MATERIAPRIMA M1 "
                        + "WHERE I1.MATCOD = M1.MATCOD and I1.cotcod = " + acaComboTipo.getValor() + " and I1.forcod = " + acaComboFornecedor.getValor() + "");
                calculaTotal();
            }

        }
    }//GEN-LAST:event_acaComboFornecedorItemStateChanged

    public void addItem() {

        Object[] item = {acaTextfieldCodItens.getText(), acaTextfieldDes.getText().toUpperCase(), acaTextfieldQtde.getText(), acaMonetarioUnit.getText(), acaMonetarioSubtot.getText()};
        jTabela.PrencheTabela(item);

    }

//    public void calculaTotal() {
//        acaMonetarioTotal.setValor(0.00);
//        double soma = 0.00;
//        double valor = 0;
//        for (int x = 0; x < jTabela.dtm.getRowCount(); x++) {
//
//            if (!acaMonetarioTotal.getText().equals("")) {
//                soma = soma + Double.parseDouble(jTabela.getValueAt(x, 4).toString().replace(",", "."));
//                acaMonetarioTotal.setValor(soma);
//            }
//        }
//
//    }
//    @Override
//    public void dadosConsultaTipo(int codigo) {
//
//        String sql = null;
//        ResultSet rs = null;
//        if (tipoOperacao == PESQUISANDO || tipoOperacao == INCLUINDO) {
//            sql = "select cotcodxf,fonome  from cotfornecedor where cotcod = " + codigo + " and codstatus != 0";
//            try {
//                rs = Conexao.executaQuery(sql);
//
//                rs.next();
//                if (tipoOperacao == PESQUISANDO) {
//                    acaComboTipo.setValor(0);
//                }
//                acaTextfieldCodigo.setText(rs.getString(1));
//                acaTextfieldNome.setText(rs.getString(2));
//
//            } catch (Exception e) {
//            }
//        } else {
//            sql = "SELECT C1.CLINOME,C1.CLIENDERECO,C1.CLIBAIRRO,C1.CLICEP,c1.CLINUMERO,C1.CLISTATUS,E1.ESTNOME, C2.CIDNOME, C1.CLICOD, p2.pedvenvlrtotal FROM "
//                    + "CLIENTE C1,ESTADO E1,CIDADE C2,PEDVENDA P2 WHERE E1.ESTCOD = C2.ESTCOD AND C2.CIDCOD = C1.CIDCOD AND  P2.CLICOD = C1.CLICOD AND P2.PEDVENCOD = " + codigo + "";
//            try {
//                rs = Conexao.executaQuery(sql);
//
//                rs.next();
//                if (rs.getInt(6) == 1) {
//
//                    acaTextfieldNome.setText(rs.getString(1));
//
//
//                    if (tipoOperacao != INCLUINDO) {
//                        acaComboTipo.setValor(rs.getInt(9));
//
//                        acaMonetarioTotal.setValor(rs.getDouble(10));
//                    } else {
//                    }
//                    acaTextfieldCodigo.setText(String.valueOf(acaComboTipo.getValor()));
//
//                } else {
//                    JOptionPane.showMessageDialog(null, "Foi encontrado um problema no cadastro do cliente \n verivique o status");
//                    return;
//                }
//            } catch (Exception e) {
//            }
//        }
//        System.out.println(sql);
//            acaComboCotacao.buscaResult("select cotcod,cotcod from cotfornecedor  where forcod = " + acaComboTipo.getValor() + " and codstatus != "+FECHADO+"");
//    }
    @Override
    public void dadosConsultaTipo(int codigo) {
        if (tipoOperacao == INCLUINDO) {
            acaComboFornecedor.buscaResult("select forcod,fonome from cotfornecedor where cotcod = " + codigo + "and codstatus = 2 ");
        }
        if (tipoOperacao == PESQUISANDO) {
            itenspedcompraper.setPecomcod(codigo);
            jTabela.montaTabela(itenspedcompraper.getConsultaSqlCod());
            acaTextfieldCodigoPed.setText(String.valueOf(codigo));
            ResultSet rs = null;
            try {
                pedcompraper.setPecomcod(codigo);
                rs = Conexao.executaQuery(pedcompraper.getConsultaTodos());
                rs.next();
                acaTextfieldCodigoPed.setText(rs.getString(1));
                acaDataPed.SetValor(rs.getDate(3));
                acaMonetarioTotal.setValor(rs.getDouble(4));
                acaDataPrevPed.SetValor(rs.getDate(6));
                acaCombostatus.setValor(rs.getInt(5));
                jTextAreaObs.setText(rs.getString(7));
         
                acaLabelFornecedor.setText(rs.getString(8));
                acaComboFornecedor.setVisible(false);
                acaLabelFornecedor.setVisible(true);
               


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void consultarTipo() {
        if (telaconsultafor == null) {
            telaconsultafor = new TelaConsMov("Consulta Fornecedor ", this, new int[]{1, 2, 4}, new String[]{"Codigo", "Nome", "CPF/CNPJ"}, "fornecedor", "forcod", "fonome", "forcpf", "forcnpj");
            colu.add("CODIGO");
            colu.add("NOME");
            telaconsultafor.setColu(colu);
            telaconsultafor.addInternalFrameListener(this);
            TelaSistema.jdp.add(telaconsultafor);
            telaconsultafor.setVisible(true);
            tipoOperacao = PESQUISANDO;
        }
        TelaSistema.jdp.moveToFront(telaconsultafor);
    }

    public void calculaTotal() {
        acaMonetarioTotal.setValor(0.00);
        double soma = 0.00;
        double valor = 0;
        for (int x = 0; x < jTabela.dtm.getRowCount(); x++) {

            if (!acaMonetarioTotal.getText().equals("")) {
                soma = soma + Double.parseDouble(jTabela.getValueAt(x, 4).toString().replace(",", "."));
                acaMonetarioTotal.setValor(soma);
            }
        }

    }

    public void calculaUnitario() {
        int quantidade = Integer.parseInt(acaTextfieldQtde.getText());
        double valUnitario = Double.parseDouble(acaMonetarioUnit.getValor());
        double valTotal = ((int) ((quantidade * valUnitario) * 100.0)) / 100.0;
        acaMonetarioSubtot.setValor(valTotal);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private componentes.AcaBotoes acaBotoesAddItes;
    private componentes.AcaBotoes acaBotoesDelItens;
    private componentes.AcaBotoes acaBotoesDelSelIten;
    public componentes.AcaCombo acaComboFornecedor;
    protected componentes.AcaData acaDataPed;
    protected componentes.AcaData acaDataPrevPed;
    private componentes.AcaLabel acaLabel1;
    private componentes.AcaLabel acaLabel10;
    private componentes.AcaLabel acaLabel12;
    private componentes.AcaLabel acaLabel2;
    public componentes.AcaLabel acaLabel3;
    private componentes.AcaLabel acaLabel4;
    private componentes.AcaLabel acaLabel5;
    private componentes.AcaLabel acaLabel6;
    private componentes.AcaLabel acaLabel7;
    private componentes.AcaLabel acaLabel8;
    private componentes.AcaLabel acaLabel9;
    public componentes.AcaLabel acaLabelFornecedor;
    private componentes.AcaMonetario acaMonetarioSubtot;
    private componentes.AcaMonetario acaMonetarioUnit;
    protected componentes.AcaTextfield acaTextfieldCodItens;
    protected componentes.AcaTextfield acaTextfieldCodigoPed;
    protected componentes.AcaTextfield acaTextfieldDes;
    private componentes.AcaTextfield acaTextfieldQtde;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTextArea jTextAreaObs;
    // End of variables declaration//GEN-END:variables

    public void internalFrameOpened(InternalFrameEvent e) {
    }

    public void internalFrameClosing(InternalFrameEvent e) {
    }

    public void internalFrameClosed(InternalFrameEvent e) {
        if (e.getSource() == telaconsultamat) {
            telaconsultamat = null;
        }
        if (e.getSource() == telamateria) {
            telamateria = null;
        }
        if (e.getSource() == consultaPed) {
            consultaPed = null;
        }
    }

    public void internalFrameIconified(InternalFrameEvent e) {
    }

    public void internalFrameDeiconified(InternalFrameEvent e) {
    }

    public void internalFrameActivated(InternalFrameEvent e) {
    }

    public void internalFrameDeactivated(InternalFrameEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        calculaUnitario();
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            acaTextfieldCodItens.setText((String) jTabela.dtm.getValueAt(jTabela.getSelectedRow(), 0));
            acaTextfieldDes.setText((String) jTabela.dtm.getValueAt(jTabela.getSelectedRow(), 1));
            acaTextfieldQtde.setText((String) jTabela.dtm.getValueAt(jTabela.getSelectedRow(), 2));
            acaMonetarioUnit.setValor((Double.parseDouble(jTabela.getValueAt(jTabela.getSelectedRow(), 3).toString().replace(",", "."))));
            acaMonetarioSubtot.setValor(Double.parseDouble(jTabela.getValueAt(jTabela.getSelectedRow(), 4).toString().replace(",", ".")));

//            String teste = dtm.getValueAt(jtTabela.getSelectedRow(), 4).toString().replace(",", "");
//            acaMonetarioSubTot.setValor(Double.parseDouble(teste));
            jTabela.dtm.removeRow(jTabela.getSelectedRow());


            //acaMonetarioTotal.setValor(Double.parseDouble(calculaTotal(acaMonetarioVlrUnit.getValor().toString())));
        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void actionPerformed(ActionEvent e) {
      
   
    }
}

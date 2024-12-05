/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TelaCompra.java
 *
 * Created on 24/02/2011, 23:30:28
 */
package telaMovimenacao;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import persistencia.Compra;
import persistencia.Contapagar;
import persistencia.Itenscompra;
import Banco.Conexao;
import ajuda.PlayMovie;
import componentes.AcaTabela;

/**
 *
 * @author cristiano
 */
public class TelaCompra extends TelaMovimenta implements MouseListener, KeyListener {

    public AcaTabela jTabela = new AcaTabela();
    Compra compraPer = new Compra();
    Itenscompra itensCompraPer = new Itenscompra();
    Contapagar contaPagPer = new Contapagar();
    private static final Calendar DATA_EMISSAO = Calendar.getInstance();

    /** Creates new form TelaCompra */
    public TelaCompra() {
        initComponents();
        acaComboTipo.buscaResult("select pecomcod,pecomcod from pedcompra where pedcomstatus = " + ABERTO + "");
        acaCombostatus.comboFiltro(new int[]{ABERTO, FECHADO}, new String[]{"Aberta", "Finalizada"});
        acaLabelID.setText("Importar Pedido");
        acaLabelCodigo.setText("Codigo do Pedido");
        acaDataCompra.setEnabled(false);
        campos.add(acaComboPar);
        campos.add(acaTextfieldDes);
        campos.add(acaMonetarioSubtot);
        campos.add(acaMonetarioTotal);
        campos.add(acaMonetarioUnit);
        campos.add(acaTextfieldQtde);
        campos.add(acaTextfieldDes);
        acaDataCompra.setDate(new Date());
        jTabela.montaTabela(new String[]{"Codigo", "Descrição", "Quantidade", "Valor Unitario", "Subtotal"}, new int[]{70, 200, 100, 100, 100});
        habilitaCampos(false);
        jTabela.addMouseListener(this);
        acaTextfieldQtde.addKeyListener(this);
        acaMonetarioUnit.addKeyListener(this);
        acaBotoesAlterar.setVisible(false);
        acaBotoesExcluir.setVisible(false);
        acaBotoesConsultar.setVisible(false);
    }

    @Override
    public void obterCampos() {
        compraPer.setPecomcod(Integer.parseInt(acaTextfieldCodigo.getText()));
        compraPer.setComstatus(acaCombostatus.getValor());
        compraPer.setCompar(acaComboPar.getValor());
        compraPer.setComtotal(Double.parseDouble(acaMonetarioTotal.getValor()));
        DATA_EMISSAO.setTime(acaDataCompra.getDate());
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
        String data = df.format(DATA_EMISSAO.getTime()).toString();
        compraPer.setComdata(data.replace("/", "."));
    }

    @Override
    protected void cancelar() {
        super.cancelar();

        jTabela.limparPesquisa();
        acaDataCompra.setEnabled(false);

    }

    @Override
    protected void incluir() {
        super.incluir();
        acaDataCompra.setEnabled(true);

    }

    @Override
    protected boolean incluirBD() {
        obterCampos();

        ResultSet rs = null;
         rs = compraPer.getInsereRs();
        try {
           
            rs.next();
            itensCompraPer.setComcod(rs.getInt(1));
            for (int x = 0; x < jTabela.getRowCount(); x++) {
                itensCompraPer.setMatcod(Integer.parseInt(jTabela.getValueAt(x, 0).toString()));
                itensCompraPer.setItecomqtd(Float.parseFloat(jTabela.getValueAt(x, 2).toString()));
                itensCompraPer.setItecomunit(Double.parseDouble(jTabela.getValueAt(x, 3).toString().replace(",", ".")));
                itensCompraPer.setItecomsubtot(Double.parseDouble(jTabela.getValueAt(x, 4).toString().replace(",", ".")));
                Conexao.executaSql(itensCompraPer.getInsereSql());
                Conexao.executaSql("EXECUTE PROCEDURE ATUALIZAPEDCOMPRA(" + acaComboTipo.getValor() + ", " + itensCompraPer.getMatcod() + ", " + itensCompraPer.getItecomsubtot() + "," + Double.parseDouble(acaMonetarioTotal.getValor()) + "," + itensCompraPer.getItecomunit() + "," + itensCompraPer.getItecomqtd() + ")");
            }
              if (acaComboPar.getValor() == 0) {
                ResultSet rs1 = null;
                rs1 = Conexao.executaQuery("select max(comcod)from compra");
                try {
                    rs1.next();
                    Conexao.executaSql("EXECUTE PROCEDURE COMPRAAVISTA(" + rs1.getInt(1) + ", '" +compraPer.getComdata()+ "', '" + acaTextfieldNome.getTexto() + "'," + acaMonetarioTotal.getValor() + ")");
                } catch (SQLException ex) {
                    Logger.getLogger(TelaVenda.class.getName()).log(Level.SEVERE, null, ex);
                }


            } else {

                ResultSet rs2 = null;
                rs2 = Conexao.executaQuery("select max(comcod)from compra");
                try {
                    DATA_EMISSAO.setTime(acaDataCompra.getDate());
                    rs2.next();
                     contaPagPer.setComcod(rs2.getInt(1));
                    for (int x = 0; x < acaComboPar.getValor(); x++) {
                        DATA_EMISSAO.add(Calendar.MONTH, 1);
                        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
                        String data = df.format(DATA_EMISSAO.getTime()).toString();

                        contaPagPer.setConpagdata(data.replace("/", "."));
                        contaPagPer.setConpagnum("Compra a Prazo ");
                        contaPagPer.setConpagstatus(ABERTO);
                        contaPagPer.setConpagvlr(geraParcelas());
                        Conexao.executaSql(contaPagPer.getInsereSql());

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(TelaVenda.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //Conexao.executaSql("update cotfornecedor set codstatus = " + PEDIDO + " where cotcod = " + acaComboTipo.getValor() + " and forcod = " + acaComboFornecedor.getValor() + " ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jTabela.limparPesquisa();


        return true;



    }

    @Override
    public void dadosConsultaTipo(int codigo) {
        String sql = "";
        ResultSet rs = null;
        if (tipoOperacao == INCLUINDO || tipoOperacao == PESQUISANDO) {
            sql = "select PEDCOMPRA.PECOMCOD, FORNECEDOR.FONOME, pedcompra.pedcomtotal "
                    + "from PEDCOMPRA,FORNECEDOR "
                    + "where PEDCOMPRA.FORCOD = FORNECEDOR.FORCOD and PEDCOMPRA.PECOMCOD = " + codigo + "";
        }
        try {
            rs = Conexao.executaQuery(sql);
            rs.next();
            acaTextfieldCodigo.setInteiro(rs.getInt(1));
            acaTextfieldNome.setText(rs.getString(2));
            acaMonetarioTotal.setValor(rs.getDouble(3));
        } catch (Exception e) {
        }
        jTabela.montaTabela("SELECT M1.MATCOD,M1.MATNOME,I1.ITEPEDQTDE,I1.ITEPEDUNIT,I1.ITEPEDSUBTOTAL "
                + "FROM MATERIAPRIMA M1, ITENSPEDCOMPRA I1 "
                + "WHERE M1.MATCOD = I1.MATCOD   and I1.pecomcod = " + Integer.parseInt(acaTextfieldCodigo.getText()) + "");
    }
  @Override
    protected void ajuda() {
        PlayMovie videos = new PlayMovie("compra.wmv");


    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        acaLabel1 = new componentes.AcaLabel();
        acaLabel2 = new componentes.AcaLabel();
        acaComboPar = new componentes.AcaCombo();
        acaComboPar.comboFiltro(new int []{0,1,2,3,4,5}, new String[]{"a vista","1x","2x","3x","4x","5x","6"});
        jPanel2 = new javax.swing.JPanel();
        acaLabel5 = new componentes.AcaLabel();
        acaLabel6 = new componentes.AcaLabel();
        acaLabel7 = new componentes.AcaLabel();
        acaLabel8 = new componentes.AcaLabel();
        acaTextfieldCodItens = new componentes.AcaTextfield();
        acaTextfieldDes = new componentes.AcaTextfield();
        acaTextfieldQtde = new componentes.AcaTextfield(false, "", 1);
        jScrollPane1 = new javax.swing.JScrollPane(jTabela);
        acaMonetarioUnit = new componentes.AcaMonetario(false,"Valor unitario",1);
        acaLabel4 = new componentes.AcaLabel();
        acaLabel9 = new componentes.AcaLabel();
        acaMonetarioSubtot = new componentes.AcaMonetario(false,"subtotal",1);
        acaBotoesAddItes = new componentes.AcaBotoes(false,"","");
        acaBotoesDelItens = new componentes.AcaBotoes(false,null,null);
        acaDataCompra = new com.toedter.calendar.JDateChooser();

        setPreferredSize(new java.awt.Dimension(1169, 665));
        setSize(new java.awt.Dimension(1169, 665));
        setTitle("Compra");

        acaLabel1.setText("Data");

        acaLabel2.setText("Formas de Pagamento");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Itens do pedido"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        acaLabel5.setText("Codigo");
        jPanel2.add(acaLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        acaLabel6.setText("Descrição");
        jPanel2.add(acaLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, -1, -1));

        acaLabel7.setText("Quantidade");
        jPanel2.add(acaLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 40, -1, -1));
        jPanel2.add(acaLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1041, 69, -1, -1));

        acaTextfieldCodItens.setEditable(false);
        acaTextfieldCodItens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaTextfieldCodItensActionPerformed(evt);
            }
        });
        jPanel2.add(acaTextfieldCodItens, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        acaTextfieldDes.setEditable(false);
        jPanel2.add(acaTextfieldDes, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 366, -1));
        jPanel2.add(acaTextfieldQtde, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 60, 76, -1));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabela de Itens do Pedido"));
        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 550, 200));

        acaMonetarioUnit.setEditable(false);
        acaMonetarioUnit.setText("acaMonetario1");
        jPanel2.add(acaMonetarioUnit, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 60, 113, -1));

        acaLabel4.setText("Valor Unitario");
        jPanel2.add(acaLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 40, -1, -1));

        acaLabel9.setText("Subtotal");
        jPanel2.add(acaLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 40, -1, -1));

        acaMonetarioSubtot.setEditable(false);
        acaMonetarioSubtot.setText("acaMonetario2");
        jPanel2.add(acaMonetarioSubtot, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 60, 113, -1));

        acaBotoesAddItes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/edit_add1.png"))); // NOI18N
        acaBotoesAddItes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaBotoesAddItesActionPerformed(evt);
            }
        });
        jPanel2.add(acaBotoesAddItes, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 60, -1, 30));

        acaBotoesDelItens.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/x.png"))); // NOI18N
        acaBotoesDelItens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaBotoesDelItensActionPerformed(evt);
            }
        });
        jPanel2.add(acaBotoesDelItens, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 60, -1, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getPainel());
        getPainel().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acaLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(acaDataCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acaComboPar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 969, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acaLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(acaDataCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaComboPar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                .addContainerGap())
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

    public void addItem() {

        Object[] item = {acaTextfieldCodItens.getText(), acaTextfieldDes.getText().toUpperCase(), acaTextfieldQtde.getText(), acaMonetarioUnit.getText(), acaMonetarioSubtot.getText()};
        jTabela.PrencheTabela(item);

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

    public void obterValoresPedido() {
        for (int x = 0; x < jTabela.dtm.getRowCount(); x++) {
        }

    }
    private double geraParcelas() {
        double valorTotal = Double.parseDouble(acaMonetarioTotal.getValor().replace(",", "."));
        double qtdParcelas = valorTotal / acaComboPar.getValor();
        System.out.println(qtdParcelas);
        return qtdParcelas;

    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private componentes.AcaBotoes acaBotoesAddItes;
    private componentes.AcaBotoes acaBotoesDelItens;
    private componentes.AcaCombo acaComboPar;
    private com.toedter.calendar.JDateChooser acaDataCompra;
    private componentes.AcaLabel acaLabel1;
    private componentes.AcaLabel acaLabel2;
    private componentes.AcaLabel acaLabel4;
    private componentes.AcaLabel acaLabel5;
    private componentes.AcaLabel acaLabel6;
    private componentes.AcaLabel acaLabel7;
    private componentes.AcaLabel acaLabel8;
    private componentes.AcaLabel acaLabel9;
    private componentes.AcaMonetario acaMonetarioSubtot;
    private componentes.AcaMonetario acaMonetarioUnit;
    protected componentes.AcaTextfield acaTextfieldCodItens;
    protected componentes.AcaTextfield acaTextfieldDes;
    private componentes.AcaTextfield acaTextfieldQtde;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

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

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        calculaUnitario();

    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TelaCaixa.java
 *
 * Created on 03/08/2011, 21:41:30
 */
package telaMovimenacao;

import IntefaceAca.AcaComponente;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import persistencia.Movcaixa;
import Banco.Conexao;

/**
 *
 * @author cristiano
 */
public class TelaCaixa extends javax.swing.JInternalFrame {

    public final int ENTRADA = 1;
    public final int SAIDA = 2;
    public Vector<AcaComponente> campos = new Vector();
    protected final int PADRAO = 0;
    protected final int PESQUISANDO = 1;
    protected final int INCLUINDO = 2;
    protected final int ALTERANDO = 3;
    protected final int EXCLUINDO = 4;
    protected final int NOVOITEM = 5;
    protected int tipoOperacao = PADRAO;
    Movcaixa movPer = new Movcaixa();

    /** Creates new form TelaCaixa */
    public TelaCaixa() {
        initComponents();
        posicaoTela();
        campos.add(acaTextfieldCodigo);
        campos.add(acaTextfieldDescr);
        campos.add(acaMonetarioVlr);
        campos.add(acaComboTipoMov);
        acaTabelaMov.montaTabela(new String[]{"Codigo", "Descrição", "Tipo", "Valor",}, new int[]{70, 250, 50, 100});
        tabela();

        habilitaCampos(false);
    }

    private void obtercampos() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        formatter.format(new Date());
        movPer.setMovdata(formatter.format(new Date()));
        movPer.setMovdescr(acaTextfieldDescr.getTexto());
        movPer.setMovtipo(acaComboTipoMov.getValor());
        movPer.setMovvlr(Double.parseDouble(acaMonetarioVlr.getValor()));
        movPer.setMovstatus(2);


    }

    public void tabela() {
        ResultSet rs = null;

        try {
            rs = Conexao.executaQuery("SELECT C1.CXSALDOTOT, C1.CXSTATUS, c1.cxcod FROM CAIXA C1");
            rs.next();
            acaLabelSaldo.setText(rs.getString(1));
            movPer.setCxcod(rs.getInt(3));
            if (rs.getInt(2) == 1) {
                acaLabelStatus.setText("ABERTO");
            } else {
                acaLabelStatus.setText("FECHADO");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TelaCaixa.class.getName()).log(Level.SEVERE, null, ex);
        }

        acaTabelaMov.montaTabela("SELECT p.MOVCOD, p.MOVDESCR, p.MOVTIPO, p.MOVVLR FROM SP_MOVCAIXA p");

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
        acaTextfieldCodigo = new componentes.AcaTextfield();
        acaBotoesMovi = new componentes.AcaBotoes();
        acaLabel2 = new componentes.AcaLabel();
        acaTextfieldDescr = new componentes.AcaTextfield(true, "Descrição", 1);
        acaLabel3 = new componentes.AcaLabel();
        acaComboTipoMov = new componentes.AcaCombo(true,"Tipo");
        acaComboTipoMov.comboFiltro(new int[]{0,ENTRADA,SAIDA}, new String[]{"Selecionar","Entrada","Saida"});
        acaLabel4 = new componentes.AcaLabel();
        acaMonetarioVlr = new componentes.AcaMonetario(true, "Valor", 1);
        acaBotoesIncluir = new componentes.AcaBotoes();
        acaBotoesCancelar = new componentes.AcaBotoes();
        jScrollPane3 = new javax.swing.JScrollPane();
        acaTabelaMov = new componentes.AcaTabela();
        acaLabel5 = new componentes.AcaLabel();
        acaLabelStatus = new componentes.AcaLabel();
        acaLabel6 = new componentes.AcaLabel();
        acaLabelSaldo = new componentes.AcaLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Movimento de Caixa");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        acaLabel1.setText("Codigo");

        acaTextfieldCodigo.setEditable(false);

        acaBotoesMovi.setText("Movimentar");
        acaBotoesMovi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaBotoesMoviActionPerformed(evt);
            }
        });

        acaLabel2.setText("Historico");

        acaLabel3.setText("Tipo ");

        acaLabel4.setText("Valor");

        acaMonetarioVlr.setText("acaMonetario1");

        acaBotoesIncluir.setText("Confirmar");
        acaBotoesIncluir.setEnabled(false);
        acaBotoesIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaBotoesIncluirActionPerformed(evt);
            }
        });

        acaBotoesCancelar.setText("Cancelar");
        acaBotoesCancelar.setEnabled(false);

        acaTabelaMov.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        acaTabelaMov.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(acaTabelaMov);

        acaLabel5.setText("Status ");

        acaLabelStatus.setText("acaLabel6");
        acaLabelStatus.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N

        acaLabel6.setText("Saldo:");

        acaLabelSaldo.setText("acaLabel7");
        acaLabelSaldo.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(acaLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(acaTextfieldDescr, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(acaLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 166, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(acaBotoesIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(acaBotoesCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(acaLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(acaTextfieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(123, 123, 123)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(acaLabelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 272, Short.MAX_VALUE)
                                        .addComponent(acaBotoesMovi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(acaLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(acaComboTipoMov, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                        .addGap(412, 412, 412))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(acaLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 322, Short.MAX_VALUE)
                        .addComponent(acaLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(acaLabelSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(179, 179, 179))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(acaMonetarioVlr, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(499, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {acaBotoesCancelar, acaBotoesIncluir, acaBotoesMovi});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acaLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(acaBotoesMovi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(acaTextfieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(acaLabelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(acaBotoesIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(acaBotoesCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(acaLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(acaTextfieldDescr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(acaLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(acaComboTipoMov, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acaLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabelSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(acaMonetarioVlr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void acaBotoesMoviActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaBotoesMoviActionPerformed
        acaBotoesIncluir.setEnabled(true);
        acaBotoesCancelar.setEnabled(true);
        habilitaCampos(true);
        tipoOperacao = INCLUINDO;

    }//GEN-LAST:event_acaBotoesMoviActionPerformed

    private void acaBotoesIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaBotoesIncluirActionPerformed
        confirmar();
    }//GEN-LAST:event_acaBotoesIncluirActionPerformed
    protected void habilitaCampos(boolean status) {

        for (int i = 0; i < campos.size(); i++) {

            campos.get(i).habilitar(status);
        }

    }

    protected void limparCampos() {
        for (int i = 0; i < campos.size(); i++) {
            campos.get(i).limpar();

        }

    }

    protected void confirmar() {

        if (!validarCampos()) {
            return;
        }
        if ((tipoOperacao == INCLUINDO) && !incluirBD()) {
            JOptionPane.showMessageDialog(null, validarCampos());
            return;
        } else if ((tipoOperacao == ALTERANDO) && !alterarBD()) {
            return;
        } else if ((tipoOperacao == EXCLUINDO) && !excluirBD()) {
            campos.removeAllElements();
            return;
        }
        if (tipoOperacao == INCLUINDO) {
            JOptionPane.showMessageDialog(null, "DADOS GRAVADOS COM SUCESSO");
        }
        if (tipoOperacao == ALTERANDO) {
            JOptionPane.showMessageDialog(null, "DADOS ALTERADOS COM SUCESSO");
        }
        tipoOperacao = PADRAO;
        limparCampos();
        tabela();
        habilitaCampos(false);
    }

    protected boolean validarCampos() {
        String errosObrigatorios = "";
        String errosValidacao = "";
        String mensagemErro = "";
        for (int contador = 0; contador < campos.size(); contador++) {
            if ((campos.get(contador).eObrigatorio()) && (campos.get(contador).eVazio())) {
                errosObrigatorios = errosObrigatorios + campos.get(contador).getDica() + "\n";
            }
            if ((!campos.get(contador).eVazio()) && (!campos.get(contador).eValido())) {
                errosValidacao = errosValidacao + campos.get(contador).getDica() + "\n";
            }
        }
        if (!errosObrigatorios.isEmpty()) {
            mensagemErro = "Campos obrigatorios não preenchidos: \n" + errosObrigatorios;
        }
        if (!errosValidacao.isEmpty()) {
            mensagemErro = mensagemErro + "\n\nCampos Invalidos: \n" + errosValidacao;
        }
        if (!mensagemErro.isEmpty()) {
            JOptionPane.showMessageDialog(null, mensagemErro);
            return false;
        } else {
            return true;
        }

    }

    public void incluir() {
    }

    public void posicaoTela() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 200);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private componentes.AcaBotoes acaBotoesCancelar;
    private componentes.AcaBotoes acaBotoesIncluir;
    private componentes.AcaBotoes acaBotoesMovi;
    private componentes.AcaCombo acaComboTipoMov;
    private componentes.AcaLabel acaLabel1;
    private componentes.AcaLabel acaLabel2;
    private componentes.AcaLabel acaLabel3;
    private componentes.AcaLabel acaLabel4;
    private componentes.AcaLabel acaLabel5;
    private componentes.AcaLabel acaLabel6;
    private componentes.AcaLabel acaLabelSaldo;
    private componentes.AcaLabel acaLabelStatus;
    private componentes.AcaMonetario acaMonetarioVlr;
    private componentes.AcaTabela acaTabelaMov;
    private componentes.AcaTextfield acaTextfieldCodigo;
    private componentes.AcaTextfield acaTextfieldDescr;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables

    private boolean incluirBD() {
        obtercampos();
        Conexao.executaSql(movPer.getInsereSql());

        return true;
    }

    private boolean alterarBD() {
        return true;
    }

    private boolean excluirBD() {
        return true;
    }
}
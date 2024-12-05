/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TelaCadastro.java
 *
 * Created on 04/05/2010, 23:17:41
 */
package Telacadastros;

import IntefaceAca.AcaComponente;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.Vector;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author chs
 */
public class TelaCadastro extends javax.swing.JInternalFrame {

    protected final int PADRAO = 0;
    protected final int PESQUISANDO = 1;
    protected final int INCLUINDO = 2;
    protected final int ALTERANDO = 3;
    protected final int EXCLUINDO = 4;
    protected final int NOVOITEM = 5;
    protected int tipoOperacao = PADRAO;
    public Vector<AcaComponente> campos = new Vector();
    public Vector tipos = new Vector();

    /** Creates new form TelaCadastro */
    public TelaCadastro() {

        initComponents();
        habilitaCampos(false);
        posicaoTela();
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F2"), "fireDoFindNext");
        getRootPane().getActionMap().put("fireDoFindNext", new AbstractAction("fireDoFindNext") {
            // The next two lines should be in one line

            public void actionPerformed(ActionEvent evt) {
                consultar();
            }
        });


    }

    protected void incluir() {
        tipoOperacao = INCLUINDO;
        habilitaCampos(true);
        //  limparCampos();
        habilitarBotoes(false);
    }

    protected void alterar() {
        tipoOperacao = ALTERANDO;
        habilitarBotoes(false);
        habilitaCampos(true);
    }

    protected void excluir() {
        /*Object[] botoes = {"Sim", "Não"};
        int opcao = JOptionPane.showOptionDialog(null, " Deseja Excluir o cadastro ",
        "Sair", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes, botoes[0]);
        if (opcao == JOptionPane.YES_OPTION) {
        habilitarBotoes(true);
        habilitaCampos(false);
        requestFocus();
        }
        if (opcao == JOptionPane.NO_OPTION) {
        habilitaCampos(true);

        }*/
        tipoOperacao = EXCLUINDO;

        habilitarBotoes(false);
    }

    protected void consultar() {
    }

    protected void cancelar() {

        Object[] botoes = {"Sim", "Não"};
        int opcao = JOptionPane.showOptionDialog(null, " Deseja cancelar a operação ",
                "Sair", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes, botoes[0]);
        if (opcao == JOptionPane.YES_OPTION) {
            habilitarBotoes(true);
            limparCampos();
            habilitaCampos(false);


        }
        if (opcao == JOptionPane.NO_OPTION) {
            habilitaCampos(true);

        }
        tipoOperacao = PADRAO;
        habilitarBotoes(true);
        limparCampos();
        habilitaCampos(false);

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
        habilitarBotoes(true);
        habilitaCampos(false);
    }

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

    protected boolean incluirBD() {

        return true;
    }

    protected boolean alterarBD() {
        return true;
    }

    protected boolean excluirBD() {
        return true;
    }

    protected void habilitarBotoes(boolean status) {

        jBIncluir.setEnabled(status);
        jBAlterar.setEnabled(status);
        jBExcluir.setEnabled(status);
        jBConsultar.setEnabled(status);
        jBConfirmar.setEnabled(!status);
        jBCancelar.setEnabled(!status);
    }

    public void habilitaBotoesConsulta(boolean status) {
        jBIncluir.setEnabled(!status);
        jBAlterar.setEnabled(status);
        jBExcluir.setEnabled(status);
        jBConsultar.setEnabled(status);
        jBConfirmar.setEnabled(!status);
        jBCancelar.setEnabled(status);
    }

    public void consultarDados(int codigo) {
    }

    public void consultaItens(int codigo) {
    }

    /*public Vector insereResultado(ResultSet rs) {

    Vector comp_result = new Vector();
    try {
    ResultSetMetaData resultado = rs.getMetaData();
    for (int x = 1; x <= resultado.getColumnCount(); x++) {
    switch (resultado.getColumnType(x)) {
    case Types.VARCHAR:

    comp_result.addElement(rs.getString(x));
    break;
    case Types.INTEGER:
    comp_result.addElement(new Long(rs.getLong(x)));

    break;
    }
    }


    } catch (Exception e) {
    }


    return comp_result;

    }*/
    public void posicaoTela() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 200);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelCentro = new javax.swing.JPanel();
        jPanelBotoes = new javax.swing.JPanel();
        jBConfirmar = new javax.swing.JButton();
        jBAlterar = new javax.swing.JButton();
        jBConsultar = new javax.swing.JButton();
        jBCancelar = new javax.swing.JButton();
        jBIncluir = new javax.swing.JButton();
        jBExcluir = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setAutoscrolls(true);
        setPreferredSize(new java.awt.Dimension(612, 320));

        javax.swing.GroupLayout painelCentroLayout = new javax.swing.GroupLayout(painelCentro);
        painelCentro.setLayout(painelCentroLayout);
        painelCentroLayout.setHorizontalGroup(
            painelCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 489, Short.MAX_VALUE)
        );
        painelCentroLayout.setVerticalGroup(
            painelCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 16, Short.MAX_VALUE)
        );

        jPanelBotoes.setBorder(javax.swing.BorderFactory.createTitledBorder("Opções"));
        jPanelBotoes.setPreferredSize(new java.awt.Dimension(537, 87));
        jPanelBotoes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBConfirmar.setFont(new java.awt.Font("Tahoma", 0, 10));
        jBConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/ok.png"))); // NOI18N
        jBConfirmar.setText("Confirmar");
        jBConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBConfirmarActionPerformed(evt);
            }
        });
        jPanelBotoes.add(jBConfirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, -1, 40));

        jBAlterar.setFont(new java.awt.Font("Tahoma", 0, 10));
        jBAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/reload_page.png"))); // NOI18N
        jBAlterar.setText("Alterar");
        jBAlterar.setPreferredSize(new java.awt.Dimension(90, 36));
        jBAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAlterarActionPerformed(evt);
            }
        });
        jPanelBotoes.add(jBAlterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 91, 40));

        jBConsultar.setFont(new java.awt.Font("Tahoma", 0, 10));
        jBConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/viewmag.png"))); // NOI18N
        jBConsultar.setText("Consultar(F2)");
        jBConsultar.setPreferredSize(new java.awt.Dimension(90, 36));
        jBConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBConsultarActionPerformed(evt);
            }
        });
        jPanelBotoes.add(jBConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, 126, 40));

        jBCancelar.setFont(new java.awt.Font("Tahoma", 0, 10));
        jBCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/button_cancel.png"))); // NOI18N
        jBCancelar.setText("Cancelar");
        jBCancelar.setPreferredSize(new java.awt.Dimension(90, 36));
        jBCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCancelarActionPerformed(evt);
            }
        });
        jPanelBotoes.add(jBCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 30, 121, 40));

        jBIncluir.setFont(new java.awt.Font("Tahoma", 0, 10));
        jBIncluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/edit_add1.png"))); // NOI18N
        jBIncluir.setText("Incluir");
        jBIncluir.setPreferredSize(new java.awt.Dimension(90, 36));
        jBIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBIncluirActionPerformed(evt);
            }
        });
        jPanelBotoes.add(jBIncluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, 40));

        jBExcluir.setFont(new java.awt.Font("Tahoma", 0, 10));
        jBExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cnrdelete-all1.png"))); // NOI18N
        jBExcluir.setText("Excluir");
        jBExcluir.setPreferredSize(new java.awt.Dimension(90, 36));
        jBExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBExcluirActionPerformed(evt);
            }
        });
        jPanelBotoes.add(jBExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 30, 91, 40));

        jButton1.setText("Ajuda(F1)");
        jButton1.setToolTipText("Clique Para Ajuda ou Tecle F!");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelCentro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(painelCentro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(181, 181, 181))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBIncluirActionPerformed
        incluir();
    }//GEN-LAST:event_jBIncluirActionPerformed

    private void jBAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAlterarActionPerformed
        alterar();
    }//GEN-LAST:event_jBAlterarActionPerformed

    private void jBConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBConsultarActionPerformed
        consultar();
    }//GEN-LAST:event_jBConsultarActionPerformed

    private void jBConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBConfirmarActionPerformed
        confirmar();
    }//GEN-LAST:event_jBConfirmarActionPerformed

    private void jBCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCancelarActionPerformed
        cancelar();
    }//GEN-LAST:event_jBCancelarActionPerformed

    private void jBExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBExcluirActionPerformed
        excluir();
    }//GEN-LAST:event_jBExcluirActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ajuda();
    }//GEN-LAST:event_jButton1ActionPerformed
    public Container getPainelCentral() {
        return painelCentro;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton jBAlterar;
    protected javax.swing.JButton jBCancelar;
    protected javax.swing.JButton jBConfirmar;
    protected javax.swing.JButton jBConsultar;
    protected javax.swing.JButton jBExcluir;
    protected javax.swing.JButton jBIncluir;
    private javax.swing.JButton jButton1;
    protected javax.swing.JPanel jPanelBotoes;
    public javax.swing.JPanel painelCentro;
    // End of variables declaration//GEN-END:variables

    protected void ajuda() {

    }
}

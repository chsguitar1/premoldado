/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TelaRecPag.java
 *
 * Created on 23/07/2011, 16:54:56
 */
package telaMovimenacao;

import IntefaceAca.AcaComponente;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author cristiano
 */
public class TelaRecPag extends javax.swing.JInternalFrame {

    protected final int PADRAO = 0;
    protected final int PESQUISANDO = 1;
    protected final int INCLUINDO = 2;
    protected final int ALTERANDO = 3;
    protected final int EXCLUINDO = 4;
    protected final int NOVOITEM = 5;
    public final int FECHADO = 2;
    public final int ABERTO = 1;
    public final int CANCELADO = 0;
    public final int PARCIAL = 3;
    public final int PEDIDO = 4;
    public int tipoOperacao = PADRAO;
    public Vector<AcaComponente> campos = new Vector();
    public Vector tipos = new Vector();

    /** Creates new form TelaRecPag */
    public TelaRecPag() {
        initComponents();
        posicaoTela();
    }

    public Container getPainel() {
        return jPainelDados;
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
        tipoOperacao = INCLUINDO;
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


        jBConsultar.setEnabled(status);
        jBConfirmar.setEnabled(!status);

    }

    public void habilitaBotoesConsulta(boolean status) {


        jBConsultar.setEnabled(status);
        jBConfirmar.setEnabled(!status);

    }

    public void consultarDados(int codigo) {
    }

    public void posicaoTela() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 200);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        telaConta1 = new telaMovimenacao.TelaConta();
        jPanel1 = new javax.swing.JPanel();
        acaLabel1 = new componentes.AcaLabel();
        acaTextfieldCodigo = new componentes.AcaTextfield();
        acaLabel2 = new componentes.AcaLabel();
        acaDataConta = new componentes.AcaData(true,"Data de Vencimento");
        acaLabel3 = new componentes.AcaLabel();
        acaLabel4 = new componentes.AcaLabel();
        acaTextfieldDescr = new componentes.AcaTextfield(true, "Descrição", 1);
        acaMonetarioVlr = new componentes.AcaMonetario(true, "Valor", 1);
        acaLabel5 = new componentes.AcaLabel();
        acaComboStatus = new componentes.AcaCombo();
        acaComboStatus.comboFiltro(new int[]{ABERTO,FECHADO,PARCIAL}, new String[]{"Em Aberto","Quitado","Pagamento Pacial"});
        jPanelBotoes = new javax.swing.JPanel();
        jBConfirmar = new javax.swing.JButton();
        jBConsultar = new javax.swing.JButton();
        jBCancelar = new javax.swing.JButton();
        jPainelDados = new javax.swing.JPanel();
        jButtonAjuda = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        telaConta1.setTitle("Contas a Receber");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        acaLabel1.setText("Codigo");

        acaTextfieldCodigo.setEditable(false);

        acaLabel2.setText("Vencimento");

        acaLabel3.setText("Descrição");

        acaLabel4.setText("Valor");

        acaMonetarioVlr.setText("acaMonetario1");

        acaLabel5.setText("Status");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(acaLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acaDataConta, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaTextfieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaTextfieldDescr, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                            .addComponent(acaMonetarioVlr, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addComponent(acaComboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acaLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaTextfieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acaLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaDataConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acaLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaTextfieldDescr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acaLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaMonetarioVlr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acaLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaComboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout telaConta1Layout = new javax.swing.GroupLayout(telaConta1.getPainel());
        telaConta1.getPainel().setLayout(telaConta1Layout);
        telaConta1Layout.setHorizontalGroup(
            telaConta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(telaConta1Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        telaConta1Layout.setVerticalGroup(
            telaConta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(telaConta1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        jPanelBotoes.setBorder(javax.swing.BorderFactory.createTitledBorder("Opções"));
        jPanelBotoes.setPreferredSize(new java.awt.Dimension(537, 87));

        jBConfirmar.setFont(new java.awt.Font("Tahoma", 0, 10));
        jBConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/ok.png"))); // NOI18N
        jBConfirmar.setText("Confirmar");
        jBConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBConfirmarActionPerformed(evt);
            }
        });

        jBConsultar.setFont(new java.awt.Font("Tahoma", 0, 10));
        jBConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/viewmag.png"))); // NOI18N
        jBConsultar.setText("Consultar");
        jBConsultar.setPreferredSize(new java.awt.Dimension(90, 36));
        jBConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBConsultarActionPerformed(evt);
            }
        });

        jBCancelar.setFont(new java.awt.Font("Tahoma", 0, 10));
        jBCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/button_cancel.png"))); // NOI18N
        jBCancelar.setText("Cancelar");
        jBCancelar.setPreferredSize(new java.awt.Dimension(90, 36));
        jBCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBotoesLayout = new javax.swing.GroupLayout(jPanelBotoes);
        jPanelBotoes.setLayout(jPanelBotoesLayout);
        jPanelBotoesLayout.setHorizontalGroup(
            jPanelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotoesLayout.createSequentialGroup()
                .addGroup(jPanelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBotoesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBConsultar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                            .addComponent(jBConfirmar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBotoesLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jBCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanelBotoesLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jBConfirmar, jBConsultar});

        jPanelBotoesLayout.setVerticalGroup(
            jPanelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotoesLayout.createSequentialGroup()
                .addComponent(jBConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jBConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPainelDados.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        javax.swing.GroupLayout jPainelDadosLayout = new javax.swing.GroupLayout(jPainelDados);
        jPainelDados.setLayout(jPainelDadosLayout);
        jPainelDadosLayout.setHorizontalGroup(
            jPainelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 382, Short.MAX_VALUE)
        );
        jPainelDadosLayout.setVerticalGroup(
            jPainelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );

        jButtonAjuda.setText("Ajuda");
        jButtonAjuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAjudaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPainelDados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonAjuda)
                        .addContainerGap(551, Short.MAX_VALUE))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 311, Short.MAX_VALUE)
                    .addComponent(telaConta1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 311, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButtonAjuda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPainelDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(137, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 173, Short.MAX_VALUE)
                    .addComponent(telaConta1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 173, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBConfirmarActionPerformed
        confirmar();
}//GEN-LAST:event_jBConfirmarActionPerformed

    private void jBConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBConsultarActionPerformed
        consultar();
}//GEN-LAST:event_jBConsultarActionPerformed

    private void jBCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCancelarActionPerformed
        cancelar();
}//GEN-LAST:event_jBCancelarActionPerformed

    private void jButtonAjudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAjudaActionPerformed
        ajuda();
            // TODO add your handling code here:
    }//GEN-LAST:event_jButtonAjudaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public componentes.AcaCombo acaComboStatus;
    public componentes.AcaData acaDataConta;
    private componentes.AcaLabel acaLabel1;
    private componentes.AcaLabel acaLabel2;
    private componentes.AcaLabel acaLabel3;
    private componentes.AcaLabel acaLabel4;
    private componentes.AcaLabel acaLabel5;
    public componentes.AcaMonetario acaMonetarioVlr;
    public componentes.AcaTextfield acaTextfieldCodigo;
    public componentes.AcaTextfield acaTextfieldDescr;
    protected javax.swing.JButton jBCancelar;
    protected javax.swing.JButton jBConfirmar;
    protected javax.swing.JButton jBConsultar;
    public javax.swing.JButton jButtonAjuda;
    public javax.swing.JPanel jPainelDados;
    public javax.swing.JPanel jPanel1;
    protected javax.swing.JPanel jPanelBotoes;
    private telaMovimenacao.TelaConta telaConta1;
    // End of variables declaration//GEN-END:variables

protected void ajuda() {

    }
}

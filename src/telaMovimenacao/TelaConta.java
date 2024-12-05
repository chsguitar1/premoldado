/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TelaConta.java
 *
 * Created on 21/05/2011, 23:30:59
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
public class TelaConta extends javax.swing.JInternalFrame {

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

    /** Creates new form TelaConta */
    public TelaConta() {
        initComponents();
        habilitaCampos(false);
        posicaoTela();
    }

    public Container getPainel() {
        return jPanelDados;
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

        jPanelBotoes = new javax.swing.JPanel();
        jBConfirmar = new javax.swing.JButton();
        jBAlterar = new javax.swing.JButton();
        jBConsultar = new javax.swing.JButton();
        jBCancelar = new javax.swing.JButton();
        jBIncluir = new javax.swing.JButton();
        jBExcluir = new javax.swing.JButton();
        jPanelDados = new javax.swing.JPanel();
        jButtonAjuda = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

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

        jBAlterar.setFont(new java.awt.Font("Tahoma", 0, 10));
        jBAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/reload_page.png"))); // NOI18N
        jBAlterar.setText("Alterar");
        jBAlterar.setPreferredSize(new java.awt.Dimension(90, 36));
        jBAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAlterarActionPerformed(evt);
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

        jBIncluir.setFont(new java.awt.Font("Tahoma", 0, 10));
        jBIncluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/edit_add1.png"))); // NOI18N
        jBIncluir.setText("Incluir");
        jBIncluir.setPreferredSize(new java.awt.Dimension(90, 36));
        jBIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBIncluirActionPerformed(evt);
            }
        });

        jBExcluir.setFont(new java.awt.Font("Tahoma", 0, 10));
        jBExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cnrdelete-all1.png"))); // NOI18N
        jBExcluir.setText("Excluir");
        jBExcluir.setPreferredSize(new java.awt.Dimension(90, 36));
        jBExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBotoesLayout = new javax.swing.GroupLayout(jPanelBotoes);
        jPanelBotoes.setLayout(jPanelBotoesLayout);
        jPanelBotoesLayout.setHorizontalGroup(
            jPanelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jBIncluir, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
            .addComponent(jBAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
            .addComponent(jBConsultar, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
            .addComponent(jBConfirmar, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
            .addComponent(jBCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
            .addComponent(jBExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
        );
        jPanelBotoesLayout.setVerticalGroup(
            jPanelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotoesLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jBIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jBAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanelDados.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        javax.swing.GroupLayout jPanelDadosLayout = new javax.swing.GroupLayout(jPanelDados);
        jPanelDados.setLayout(jPanelDadosLayout);
        jPanelDadosLayout.setHorizontalGroup(
            jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 371, Short.MAX_VALUE)
        );
        jPanelDadosLayout.setVerticalGroup(
            jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 304, Short.MAX_VALUE)
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanelDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButtonAjuda)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButtonAjuda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBConfirmarActionPerformed
        confirmar();
}//GEN-LAST:event_jBConfirmarActionPerformed

    private void jBAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAlterarActionPerformed
        alterar();
}//GEN-LAST:event_jBAlterarActionPerformed

    private void jBConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBConsultarActionPerformed
        consultar();
}//GEN-LAST:event_jBConsultarActionPerformed

    private void jBCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCancelarActionPerformed
        cancelar();
}//GEN-LAST:event_jBCancelarActionPerformed

    private void jBIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBIncluirActionPerformed
        incluir();
}//GEN-LAST:event_jBIncluirActionPerformed

    private void jBExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBExcluirActionPerformed
        excluir();
}//GEN-LAST:event_jBExcluirActionPerformed

    private void jButtonAjudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAjudaActionPerformed
       ajuda();
    }//GEN-LAST:event_jButtonAjudaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton jBAlterar;
    protected javax.swing.JButton jBCancelar;
    protected javax.swing.JButton jBConfirmar;
    protected javax.swing.JButton jBConsultar;
    protected javax.swing.JButton jBExcluir;
    protected javax.swing.JButton jBIncluir;
    public javax.swing.JButton jButtonAjuda;
    protected javax.swing.JPanel jPanelBotoes;
    public javax.swing.JPanel jPanelDados;
    // End of variables declaration//GEN-END:variables

   protected  void ajuda() {
       
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TelaMovimenta.java
 *
 * Created on 29/10/2010, 20:13:42
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
 * @author chs
 */
public class TelaMovimenta extends javax.swing.JInternalFrame {

    protected final int PADRAO = 0;
    protected final int PESQUISANDO = 1;
    protected final int INCLUINDO = 2;
    protected final int ALTERANDO = 3;
    protected final int EXCLUINDO = 4;
    protected final int NOVOPESSOA = 8;
    protected final int NOVOITEM = 5;
    public final int FECHADO = 2;
    public final int ABERTO = 1;
    public final int CANCELADO = 0;
    public final int AUTOMATICO = 3;
    public final int PEDIDO = 4;
    public int tipoOperacao = PADRAO;
    public Vector<AcaComponente> campos = new Vector();
    public Vector selecao = new Vector();

    /** Creates new form TelaMovimenta */
    public TelaMovimenta() {
        initComponents();
        campos.add(acaComboTipo);
        campos.add(acaBotoesConsTipo);
        campos.add(acaBotoesNovo);

        campos.add(acaTextfieldNome);

        campos.add(acaTextfieldCodigo);
        campos.add(acaCombostatus);
        campos.add(acaMonetarioTotal);
        habilitaCampos(false);
        habilitarBotoes(false);
        posicaoTela();
    }

    public Container getPainel() {
        return jPanelDados;
    }

    protected void incluir() {
        tipoOperacao = INCLUINDO;
        habilitarBotoes(true);
        acaBotoesAlterar.setEnabled(false);
        acaBotoesExcluir.setEnabled(false);
        habilitaCampos(true);
    }

    protected void alterar() {
        tipoOperacao = ALTERANDO;
        habilitaCampos(true);
        habilitarBotoesConsulta(true);
    }

    protected void consultarTipo() {
    }

    protected void consultarItens() {
    }

    protected void consultar() {
        tipoOperacao = PESQUISANDO;
        habilitarBotoes(true);
    }

    protected void excluir() {
        tipoOperacao = EXCLUINDO;
    }

    protected void cancelar() {
        Object[] botoes = {"Sim", "Não"};
        int opcao = JOptionPane.showOptionDialog(null, " Deseja cancelar a operação ",
                "Sair", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes, botoes[0]);
        if (opcao == JOptionPane.YES_OPTION) {
            habilitarBotoes(false);

            habilitaCampos(false);
            requestFocus();
        }
        if (opcao == JOptionPane.NO_OPTION) {
            habilitaCampos(true);

        }
        tipoOperacao = PADRAO;
        habilitarBotoes(false);
        limparCampos();
        habilitaCampos(false);
    }

    protected void confirmar() {

        if (!validarCampos()) {
            return;
        }
        if ((tipoOperacao == INCLUINDO) && !incluirBD()) {


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
        habilitarBotoes(false);
        habilitaCampos(false);
        acaCombostatus.setSelectedIndex(1);
    }

    protected void novoItem() {
    }

    protected void obterCampos() {
    }

    protected void preencheCampos() {
    }

    protected void novoTipo() {
    }

    public void dadosConsultaTipo(int codigo) {
    }

    public void dadosConsultaItens(int codigo) {
    }

    public void dadosConsulta(int codigo) {
    }

    public void dadosConsulta(int codigo, String pessoa) {
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

        jPanel1 = new javax.swing.JPanel();
        acaLabelNome = new componentes.AcaLabel();
        acaTextfieldNome = new componentes.AcaTextfield(false,"campo de nome",1);
        jPanel5 = new javax.swing.JPanel();
        acaLabelID = new componentes.AcaLabel();
        acaComboTipo = new componentes.AcaCombo(true,"Selecione o "+acaLabelNome+"");
        acaBotoesConsTipo = new componentes.AcaBotoes(false,"","Consultar outros resgistros");
        acaBotoesNovo = new componentes.AcaBotoes(false, "", "Novo Registro");
        acaLabel1 = new componentes.AcaLabel();
        acaCombostatus = new componentes.AcaCombo(false, "Status do pedido");
        acaCombostatus.comboFiltro(new int[]{FECHADO,ABERTO,CANCELADO}, new String[]{"Fechado","Aberto","Cancelado"});
        acaCombostatus.setSelectedIndex(1);
        acaLabeLAtividade = new componentes.AcaLabel();
        acaLabelTot = new componentes.AcaLabel();
        acaMonetarioTotal = new componentes.AcaMonetario(false, "valor Total", 1);
        acaLabel9 = new componentes.AcaLabel();
        acaLabelCodigo = new componentes.AcaLabel();
        acaTextfieldCodigo = new componentes.AcaTextfield(false, "codigo itenssss", 1);
        jPanelDados = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        acaBotoesConsultar = new componentes.AcaBotoes();
        acaBotoesIncuir = new componentes.AcaBotoes();
        acaBotoesAlterar = new componentes.AcaBotoes();
        acaBotoesExcluir = new componentes.AcaBotoes();
        acaBotoesConfirmar = new componentes.AcaBotoes();
        acaBotoesCancelar = new componentes.AcaBotoes();
        jButtonAjuda = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        acaLabelNome.setText("Nome");

        acaTextfieldNome.setEditable(false);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        acaLabelID.setText("acaLabel1");

        acaComboTipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                acaComboTipoItemStateChanged(evt);
            }
        });
        acaComboTipo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                acaComboTipoFocusGained(evt);
            }
        });

        acaBotoesConsTipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/search.png"))); // NOI18N
        acaBotoesConsTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaBotoesConsTipoActionPerformed(evt);
            }
        });

        acaBotoesNovo.setText("Novo");
        acaBotoesNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaBotoesNovoActionPerformed(evt);
            }
        });

        acaLabel1.setText("Status");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(acaComboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(acaBotoesConsTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(acaBotoesNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(acaLabelID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(acaCombostatus, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(acaLabeLAtividade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(acaLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acaLabelID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabeLAtividade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(acaBotoesNovo, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                        .addComponent(acaCombostatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(acaComboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaBotoesConsTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        acaLabelTot.setText("TOTAL");

        acaMonetarioTotal.setEditable(false);
        acaMonetarioTotal.setText("acaMonetario1");

        acaLabel9.setText("R$");
        acaLabel9.setFont(new java.awt.Font("Arial Black", 0, 18));

        acaLabelCodigo.setText("Codigo");

        acaTextfieldCodigo.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(acaLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acaLabelTot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaMonetarioTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(acaLabelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(acaLabelNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(acaTextfieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(acaTextfieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(acaLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(acaLabelTot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(acaMonetarioTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acaLabelNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acaTextfieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaTextfieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanelDados.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        javax.swing.GroupLayout jPanelDadosLayout = new javax.swing.GroupLayout(jPanelDados);
        jPanelDados.setLayout(jPanelDadosLayout);
        jPanelDadosLayout.setHorizontalGroup(
            jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 606, Short.MAX_VALUE)
        );
        jPanelDadosLayout.setVerticalGroup(
            jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 329, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        acaBotoesConsultar.setText("Consultar");
        acaBotoesConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaBotoesConsultarActionPerformed(evt);
            }
        });

        acaBotoesIncuir.setText("Incluir");
        acaBotoesIncuir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaBotoesIncuirActionPerformed(evt);
            }
        });

        acaBotoesAlterar.setText("Alterar");
        acaBotoesAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaBotoesAlterarActionPerformed(evt);
            }
        });

        acaBotoesExcluir.setText("Excluir");
        acaBotoesExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaBotoesExcluirActionPerformed(evt);
            }
        });

        acaBotoesConfirmar.setText("Confirmar");
        acaBotoesConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaBotoesConfirmarActionPerformed(evt);
            }
        });

        acaBotoesCancelar.setText("Cancelar");
        acaBotoesCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaBotoesCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(acaBotoesConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(acaBotoesIncuir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(acaBotoesAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(acaBotoesExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(acaBotoesConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(acaBotoesCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(acaBotoesConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(acaBotoesIncuir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(acaBotoesAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(acaBotoesExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(acaBotoesConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(acaBotoesCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanelDados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonAjuda))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonAjuda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelDados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void acaBotoesAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaBotoesAlterarActionPerformed
        alterar();

    }//GEN-LAST:event_acaBotoesAlterarActionPerformed

    private void acaBotoesConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaBotoesConsultarActionPerformed
        consultar();
    }//GEN-LAST:event_acaBotoesConsultarActionPerformed

    private void acaBotoesIncuirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaBotoesIncuirActionPerformed
        incluir();

    }//GEN-LAST:event_acaBotoesIncuirActionPerformed

    private void acaBotoesExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaBotoesExcluirActionPerformed
        excluir();
    }//GEN-LAST:event_acaBotoesExcluirActionPerformed

    private void acaBotoesConsTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaBotoesConsTipoActionPerformed
        consultarTipo();
    }//GEN-LAST:event_acaBotoesConsTipoActionPerformed

    private void acaBotoesNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaBotoesNovoActionPerformed
        novoTipo();
    }//GEN-LAST:event_acaBotoesNovoActionPerformed

    private void acaBotoesConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaBotoesConfirmarActionPerformed
        confirmar();
    }//GEN-LAST:event_acaBotoesConfirmarActionPerformed

    private void acaBotoesCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaBotoesCancelarActionPerformed
        cancelar();
    }//GEN-LAST:event_acaBotoesCancelarActionPerformed

    private void acaComboTipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_acaComboTipoItemStateChanged
        if (acaComboTipo.getSelectedIndex() >= 0) {
            if (acaComboTipo.isPopupVisible()) {
                dadosConsultaTipo(acaComboTipo.getValor());
            }
        }
    }//GEN-LAST:event_acaComboTipoItemStateChanged

    private void acaComboTipoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_acaComboTipoFocusGained
        if (tipoOperacao == NOVOPESSOA) {
            acaComboTipo.removeAllItems();
            acaComboTipo.buscaResult("select clicod,clinome from cliente where clistatus !=0");
        }
    }//GEN-LAST:event_acaComboTipoFocusGained

    private void jButtonAjudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAjudaActionPerformed
      ajuda();
    }//GEN-LAST:event_jButtonAjudaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected componentes.AcaBotoes acaBotoesAlterar;
    protected componentes.AcaBotoes acaBotoesCancelar;
    protected componentes.AcaBotoes acaBotoesConfirmar;
    protected componentes.AcaBotoes acaBotoesConsTipo;
    protected componentes.AcaBotoes acaBotoesConsultar;
    protected componentes.AcaBotoes acaBotoesExcluir;
    protected componentes.AcaBotoes acaBotoesIncuir;
    public componentes.AcaBotoes acaBotoesNovo;
    protected componentes.AcaCombo acaComboTipo;
    protected componentes.AcaCombo acaCombostatus;
    protected componentes.AcaLabel acaLabeLAtividade;
    private componentes.AcaLabel acaLabel1;
    public componentes.AcaLabel acaLabel9;
    public componentes.AcaLabel acaLabelCodigo;
    protected componentes.AcaLabel acaLabelID;
    public componentes.AcaLabel acaLabelNome;
    protected componentes.AcaLabel acaLabelTot;
    protected componentes.AcaMonetario acaMonetarioTotal;
    protected componentes.AcaTextfield acaTextfieldCodigo;
    protected componentes.AcaTextfield acaTextfieldNome;
    public javax.swing.JButton jButtonAjuda;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    public javax.swing.JPanel jPanelDados;
    // End of variables declaration//GEN-END:variables

    public void habilitarBotoes(boolean status) {
        acaBotoesIncuir.setEnabled(!status);
        acaBotoesConsultar.setEnabled(!status);
        acaBotoesAlterar.setEnabled(status);
        acaBotoesCancelar.setEnabled(status);
        acaBotoesConfirmar.setEnabled(status);
        acaBotoesExcluir.setEnabled(status);
    }

    public void habilitarBotoesConsulta(boolean status) {
        acaBotoesIncuir.setEnabled(!status);
        acaBotoesConsultar.setEnabled(!status);
        acaBotoesAlterar.setEnabled(status);
        acaBotoesCancelar.setEnabled(status);
        acaBotoesConfirmar.setEnabled(status);
        acaBotoesExcluir.setEnabled(status);
    }

    protected void ajuda() {
       
    }
}

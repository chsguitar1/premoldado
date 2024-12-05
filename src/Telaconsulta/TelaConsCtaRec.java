/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TelaConsCtaRec.java
 *
 * Created on 23/07/2011, 11:01:34
 */
package Telaconsulta;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import componentes.AcaTabela;
import telaMovimenacao.TelaConta;
import telaMovimenacao.TelaContaReceber;
import telaMovimenacao.TelaRecPag;

/**
 *
 * @author cristiano
 */
public class TelaConsCtaRec extends javax.swing.JInternalFrame implements MouseListener {

    private TelaConta telaConta;
    private TelaRecPag telarecpag;
    public Vector<JCheckBox> filtro = new Vector<JCheckBox>();
    public AcaTabela tabela = new AcaTabela();
    public int[] tamanhos;
    public int tipo = 0;
    public int opcao = -1;

    /** Creates new form TelaConsCtaRec */
    public TelaConsCtaRec(TelaConta telaConta) {
        this.telaConta = telaConta;
        initComponents();
        tabela.addMouseListener(this);
        filtro.add(jCheckBoxCodigo);
        filtro.add(jCheckBoxVencimento);
        filtro.add(jCheckBoxCliente);
        filtro.add(jCheckBoxStatus);
        filtro.add(jCheckBoxTodos);
        jCheckBoxTodos.setSelected(true);
        acaLabeTipo.setText("Todos");
        posicaoTela();
        tabela.addMouseListener(this);

    }

    public TelaConsCtaRec(TelaRecPag telarecpag, int tipo) {
        this.telarecpag = telarecpag;
        this.tipo = tipo;
        initComponents();
        tabela.addMouseListener(this);
        filtro.add(jCheckBoxCodigo);
        filtro.add(jCheckBoxVencimento);
        filtro.add(jCheckBoxCliente);
        filtro.add(jCheckBoxStatus);
        filtro.add(jCheckBoxTodos);
        jCheckBoxTodos.setSelected(true);
        acaLabeTipo.setText("Todos");
        posicaoTela();
        tabela.addMouseListener(this);

    }

    public void consultarDados(int codigo) {
    }

    public void opcaoConsulta() {
        if (jCheckBoxTodos.isSelected()) {
            opcao = 0;

        }
        if (jCheckBoxCodigo.isSelected()) {
            opcao = 1;
        }
        if (jCheckBoxCliente.isSelected()) {
            opcao = 2;
        }
        if (jCheckBoxVencimento.isSelected()) {
            opcao = 3;
        }
        if (jCheckBoxStatus.isSelected()) {
            opcao = 4;
        }
        switch (opcao) {
            case 0: {

                tabela.montaTabela("SELECT a.CONRECCOD,a.CONRECNUM,a.CONRECDATA,a.CONRECVLR FROM CONTARECEBER a where a.conrecstatus != 2 order by a.conrecdata desc");


                break;
            }
            case 1: {
                tabela.montaTabela("SELECT a.CONRECCOD,a.CONRECNUM,a.CONRECDATA,a.CONRECVLR FROM CONTARECEBER a where  a.conrecstatus != 2 AND a.CONRECCOD  = " + Integer.parseInt(acaTextfieldConsulta.getText()) + "  order by a.conrecdata desc ");
                break;
            }
            case 2: {
                tabela.montaTabela("SELECT C1.CONRECCOD, C1.CONRECNUM,C1.CONRECDATA,C1.CONRECVLR"
                        + " FROM CONTARECEBER C1,VENDA V1,PEDVENDA P1,CLIENTE C2 "
                        + " WHERE C1.CONRECSTATUS !=2 AND  C1.VENCOD = V1.VENCOD AND V1.PEDVENCOD = P1.PEDVENCOD AND P1.CLICOD = C2.CLICOD and c2.clinome  like '%" + acaTextfieldConsulta.getTexto() + "%' ");
                break;
            }
            case 3: {
                tabela.montaTabela("SELECT a.CONRECCOD,a.CONRECNUM,a.CONRECDATA,a.CONRECVLR FROM CONTARECEBER a where  a.conrecstatus != 2 AND a.conrecdata <= '" + acaDataCon.getValor() + "'order by a.conrecdata desc");
                break;
            }
            case 4: {
                tabela.montaTabela("SELECT a.CONRECCOD,a.CONRECNUM,a.CONRECDATA,a.CONRECVLR FROM CONTARECEBER a where  a.conrecstatus != 2 AND a.conrecstatus = " + acaCombosStatus.getValor() + " order by a.conrecdata desc");
                break;
            }
        }


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
        jPanel5 = new javax.swing.JPanel();
        acaLabeTipo = new componentes.AcaLabel();
        acaTextfieldConsulta = new componentes.AcaTextfield();
        acaBotoesConsulta1 = new componentes.AcaBotoes();
        acaLabel4 = new componentes.AcaLabel();
        acaDataCon = new componentes.AcaData();
        jPanel2 = new javax.swing.JPanel();
        jCheckBoxCodigo = new javax.swing.JCheckBox();
        jCheckBoxCliente = new javax.swing.JCheckBox();
        jCheckBoxVencimento = new javax.swing.JCheckBox();
        jCheckBoxStatus = new javax.swing.JCheckBox();
        jCheckBoxTodos = new javax.swing.JCheckBox();
        acaCombosStatus = new componentes.AcaCombo();
        jScrollPane1 = new javax.swing.JScrollPane(tabela);
        acaBotoesLimpar = new componentes.AcaBotoes();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Consulta Consta a Receber");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        acaLabeTipo.setText("acaLabel1");

        acaTextfieldConsulta.setEnabled(false);

        acaBotoesConsulta1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/viewmag.png"))); // NOI18N
        acaBotoesConsulta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaBotoesConsulta1ActionPerformed(evt);
            }
        });

        acaLabel4.setText("Data");

        acaDataCon.setEnabled(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(acaTextfieldConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabeTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acaLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaDataCon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                        .addComponent(acaBotoesConsulta1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(acaBotoesConsulta1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(acaLabeTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(acaTextfieldConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(acaLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addComponent(acaDataCon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Opçoes de consulta"));

        jCheckBoxCodigo.setText("Codigo");
        jCheckBoxCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxCodigoActionPerformed(evt);
            }
        });

        jCheckBoxCliente.setText("Cliente");
        jCheckBoxCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxClienteActionPerformed(evt);
            }
        });

        jCheckBoxVencimento.setText("Vencimento");
        jCheckBoxVencimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxVencimentoActionPerformed(evt);
            }
        });

        jCheckBoxStatus.setText("Status");
        jCheckBoxStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxStatusActionPerformed(evt);
            }
        });

        jCheckBoxTodos.setText("Todas");
        jCheckBoxTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxTodosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBoxCodigo)
                            .addComponent(jCheckBoxCliente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jCheckBoxStatus)
                            .addComponent(jCheckBoxTodos))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(acaCombosStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jCheckBoxVencimento)
                        .addContainerGap(105, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxCodigo)
                    .addComponent(jCheckBoxStatus)
                    .addComponent(acaCombosStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxCliente)
                    .addComponent(jCheckBoxTodos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBoxVencimento)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        acaBotoesLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/x.png"))); // NOI18N
        acaBotoesLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaBotoesLimparActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(acaBotoesLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaBotoesLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBoxCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxCodigoActionPerformed
        if (jCheckBoxCodigo.isSelected()) {
            checkAcao(jCheckBoxCodigo);
            acaLabeTipo.setText("Codigo");
            acaTextfieldConsulta.setEnabled(true);
        }
}//GEN-LAST:event_jCheckBoxCodigoActionPerformed

    private void jCheckBoxClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxClienteActionPerformed
        if (jCheckBoxCliente.isSelected()) {
            checkAcao(jCheckBoxCliente);
            acaLabeTipo.setText("Cliente");
            acaTextfieldConsulta.setEnabled(true);
            acaDataCon.setEnabled(false);

        }
}//GEN-LAST:event_jCheckBoxClienteActionPerformed

    private void jCheckBoxVencimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxVencimentoActionPerformed
        if (jCheckBoxVencimento.isSelected()) {
            checkAcao(jCheckBoxVencimento);
            acaDataCon.setEnabled(true);
            acaTextfieldConsulta.setEnabled(false);
            acaLabeTipo.setText("");
        }
}//GEN-LAST:event_jCheckBoxVencimentoActionPerformed

    private void jCheckBoxStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxStatusActionPerformed
        if (jCheckBoxStatus.isSelected()) {
            checkAcao(jCheckBoxStatus);
            acaLabeTipo.setText("");
            acaTextfieldConsulta.setEnabled(false);
            acaDataCon.setEnabled(false);
            acaCombosStatus.setVisible(true);
        }
}//GEN-LAST:event_jCheckBoxStatusActionPerformed

    private void jCheckBoxTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxTodosActionPerformed
        if (jCheckBoxTodos.isSelected()) {
            checkAcao(jCheckBoxTodos);
            acaLabeTipo.setText("Todos");
            acaTextfieldConsulta.setEnabled(false);
            acaDataCon.setEnabled(false);
        }
}//GEN-LAST:event_jCheckBoxTodosActionPerformed

    private void acaBotoesConsulta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaBotoesConsulta1ActionPerformed
        opcaoConsulta();
}//GEN-LAST:event_acaBotoesConsulta1ActionPerformed

    private void acaBotoesLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaBotoesLimparActionPerformed
        tabela.limparPesquisa();
        jCheckBoxCodigo.isSelected();
}//GEN-LAST:event_acaBotoesLimparActionPerformed
    public void checkAcao(JComponent comp) {
        for (int x = 0; x < filtro.size(); x++) {
            if (filtro.get(x).equals(comp)) {
                filtro.get(x).setSelected(true);
            } else {
                filtro.get(x).setSelected(false);
            }
        }
    }

    public void posicaoTela() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 200);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private componentes.AcaBotoes acaBotoesConsulta1;
    private componentes.AcaBotoes acaBotoesLimpar;
    public componentes.AcaCombo acaCombosStatus;
    private componentes.AcaData acaDataCon;
    private componentes.AcaLabel acaLabeTipo;
    private componentes.AcaLabel acaLabel4;
    private componentes.AcaTextfield acaTextfieldConsulta;
    private javax.swing.JCheckBox jCheckBoxCliente;
    private javax.swing.JCheckBox jCheckBoxCodigo;
    private javax.swing.JCheckBox jCheckBoxStatus;
    private javax.swing.JCheckBox jCheckBoxTodos;
    private javax.swing.JCheckBox jCheckBoxVencimento;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            String pegacod = (String) tabela.getValueAt(tabela.getSelectedRow(), 0);
            if (tipo == 0) {
                telaConta.consultarDados(Integer.parseInt(pegacod));
            } else {
                telarecpag.consultarDados(Integer.parseInt(pegacod));
            }

            dispose();
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
}
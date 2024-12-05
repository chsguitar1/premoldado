/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TelaConsultaPedVenda.java
 *
 * Created on 01/11/2010, 17:46:28
 */
package telaMovimenacao;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import org.jdesktop.swingx.JXGlassBox;
import org.jdesktop.swingx.JXHyperlink;
import org.jdesktop.swingx.JXMonthView;
import componentes.AcaTabela;
import telaMovimenacao.TelaMovimenta;

/**
 *
 * @author chs
 */
public class TelaConsultaPed extends javax.swing.JInternalFrame implements MouseListener {

    private TelaMovimenta telaMovimenta;
    private TelaCotacao telacota;
    public Vector<JCheckBox> filtro = new Vector<JCheckBox>();
    public int opcao = -1;
    public AcaTabela tabela = new AcaTabela();
    public int[] tamanhos;
    public int tipo;
    public String CAMPOCODIGO;
    public String CAMPONOME;
    public String CAMPODATA;
    public String CAMPOVLR;
    public String TABELA;
    public String TABELA2;
    public String CHAVETABELA;
    public String CAMPOSTATUS;
    JXGlassBox glassBox = new JXGlassBox();
    JXMonthView monthView = new JXMonthView();
    JXHyperlink hCalendario = new JXHyperlink();

    public void setCAMPOCODIGO(String CAMPOCODIGO) {
        this.CAMPOCODIGO = CAMPOCODIGO;
    }

    public void setCAMPODATA(String CAMPODATA) {
        this.CAMPODATA = CAMPODATA;
    }

    public void setCAMPONOME(String CAMPONOME) {
        this.CAMPONOME = CAMPONOME;
    }

    public void setCAMPOVLR(String CAMPOVLR) {
        this.CAMPOVLR = CAMPOVLR;
    }

    public void setCHAVETABELA(String CHAVETABELA) {
        this.CHAVETABELA = CHAVETABELA;
    }

    public void setCAMPOSTATUS(String CAMPOSTATUS) {
        this.CAMPOSTATUS = CAMPOSTATUS;
    }

    public void setTABELA(String TABELA) {
        this.TABELA = TABELA;
    }

    public void setTABELA2(String TABELA2) {
        this.TABELA2 = TABELA2;
    }

    /** Creates new form TelaConsultaPedVenda */
    public TelaConsultaPed() {

        initComponents();
        tabela.addMouseListener(this);
        filtro.add(jCheckBoxCodigo);
        filtro.add(jCheckBoxData);
        filtro.add(jCheckBoxNome);
        filtro.add(jCheckBoxStatus);
        filtro.add(jCheckBoxTodos);
        jCheckBoxTodos.setSelected(true);
        acaLabeTipo.setText("Todos");
        posicaoTela();

    }

    public TelaConsultaPed(TelaMovimenta telaMovimenta, int tipo) {
        this.telaMovimenta = telaMovimenta;
        this.tipo = tipo;
        initComponents();

        tabela.addMouseListener(this);
        filtro.add(jCheckBoxCodigo);
        filtro.add(jCheckBoxData);
        filtro.add(jCheckBoxNome);
        filtro.add(jCheckBoxStatus);
        filtro.add(jCheckBoxTodos);
        jCheckBoxTodos.setSelected(true);
        acaLabeTipo.setText("Todos");
        posicaoTela();
        acaCombosStatus.setVisible(false);


    }
      public TelaConsultaPed(TelaCotacao telaMovimenta, int tipo) {
        this.telacota = telaMovimenta;
        this.tipo = tipo;
        initComponents();

        tabela.addMouseListener(this);
        filtro.add(jCheckBoxCodigo);
        filtro.add(jCheckBoxData);
        filtro.add(jCheckBoxNome);
        filtro.add(jCheckBoxStatus);
        filtro.add(jCheckBoxTodos);
        jCheckBoxTodos.setSelected(true);
        acaLabeTipo.setText("Todos");
        posicaoTela();
        acaCombosStatus.setVisible(false);


    }

    public void opcaoConsulta() {
        if (jCheckBoxTodos.isSelected()) {
            opcao = 0;

        }
        if (jCheckBoxCodigo.isSelected()) {
            opcao = 1;
        }
        if (jCheckBoxNome.isSelected()) {
            opcao = 2;
        }
        if (jCheckBoxData.isSelected()) {
            opcao = 3;
        }
        if (jCheckBoxStatus.isSelected()) {
            opcao = 4;
        }
        switch (opcao) {
            case 0: {

                tabela.montaTabela("SELECT  P2." + CAMPOCODIGO + ", C1." + CAMPONOME + ", P2." + CAMPODATA + ",P2." + CAMPOVLR + " FROM " + TABELA + " P2, " + TABELA2 + " C1 WHERE C1." + CHAVETABELA + " = P2." + CHAVETABELA + " and P2."+CAMPOSTATUS+" != "+telaMovimenta.FECHADO+"   order by p2." + CAMPOCODIGO + " desc");


                break;
            }
            case 1: {
                tabela.montaTabela("SELECT P2." + CAMPOCODIGO + ", C1." + CAMPONOME + ", P2." + CAMPODATA + ",P2." + CAMPOVLR + " FROM " + TABELA + " P2, " + TABELA2 + " C1 WHERE C1." + CHAVETABELA + " = P2." + CHAVETABELA + " and p2." + CAMPOCODIGO + " = " + Integer.parseInt(acaTextfieldConsulta.getText()) + " ");
                break;
            }
            case 2: {
                tabela.montaTabela("SELECT P2." + CAMPOCODIGO + ", C1." + CAMPONOME + ", P2." + CAMPODATA + ",P2." + CAMPOVLR + " FROM " + TABELA + " P2, " + TABELA2 + " C1 WHERE C1." + CHAVETABELA + " = P2." + CHAVETABELA + " and c1." + CAMPONOME + " like '%" + acaTextfieldConsulta.getTexto() + "%' ");
                break;
            }
            case 3: {
                tabela.montaTabela("SELECT P2." + CAMPOCODIGO + ", C1." + CAMPONOME + ", P2." + CAMPODATA + ",P2." + CAMPOVLR + " FROM " + TABELA + " P2, " + TABELA2 + " C1 WHERE C1." + CHAVETABELA + " = P2." + CHAVETABELA + " and p2." + CAMPODATA + " = '" + acaDataCon.getValor() + "'");
                break;
            }
            case 4: {
                tabela.montaTabela("SELECT P2." + CAMPOCODIGO + ", C1." + CAMPONOME + ", P2." + CAMPODATA + ",P2." + CAMPOVLR + " FROM " + TABELA + " P2, " + TABELA2 + " C1 WHERE C1." + CHAVETABELA + " = P2." + CHAVETABELA + " and p2." + CAMPOSTATUS + " = " + acaCombosStatus.getValor() + "");
                break;
            }
        }


    }

    public void checkAcao(JComponent comp) {
        for (int x = 0; x < filtro.size(); x++) {
            if (filtro.get(x).equals(comp)) {
                filtro.get(x).setSelected(true);
            } else {
                filtro.get(x).setSelected(false);
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
        jCheckBoxCodigo = new javax.swing.JCheckBox();
        jCheckBoxNome = new javax.swing.JCheckBox();
        jCheckBoxData = new javax.swing.JCheckBox();
        jCheckBoxStatus = new javax.swing.JCheckBox();
        jCheckBoxTodos = new javax.swing.JCheckBox();
        acaCombosStatus = new componentes.AcaCombo();
        jPanel3 = new javax.swing.JPanel();
        jScrollPaneRes = new javax.swing.JScrollPane(tabela);
        acaBotoesLimpar = new componentes.AcaBotoes();
        jPanel4 = new javax.swing.JPanel();
        acaLabeTipo = new componentes.AcaLabel();
        acaTextfieldConsulta = new componentes.AcaTextfield();
        acaBotoesConsulta = new componentes.AcaBotoes();
        acaLabel3 = new componentes.AcaLabel();
        acaDataCon = new componentes.AcaData();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Opçoes de consulta"));

        jCheckBoxCodigo.setText("Codigo");
        jCheckBoxCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxCodigoActionPerformed(evt);
            }
        });

        jCheckBoxNome.setText("Nome");
        jCheckBoxNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxNomeActionPerformed(evt);
            }
        });

        jCheckBoxData.setText("Data");
        jCheckBoxData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxDataActionPerformed(evt);
            }
        });

        jCheckBoxStatus.setText("Status");
        jCheckBoxStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxStatusActionPerformed(evt);
            }
        });

        jCheckBoxTodos.setText("Todos");
        jCheckBoxTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxTodosActionPerformed(evt);
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBoxCodigo)
                            .addComponent(jCheckBoxNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jCheckBoxStatus)
                            .addComponent(jCheckBoxTodos))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(acaCombosStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(155, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCheckBoxData)
                        .addContainerGap(282, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxCodigo)
                    .addComponent(jCheckBoxStatus)
                    .addComponent(acaCombosStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxNome)
                    .addComponent(jCheckBoxTodos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBoxData)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Resultados da Pesquisa"));

        acaBotoesLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/x.png"))); // NOI18N
        acaBotoesLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaBotoesLimparActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneRes, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(acaBotoesLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPaneRes, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(acaBotoesLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        acaLabeTipo.setText("acaLabel1");

        acaTextfieldConsulta.setEnabled(false);

        acaBotoesConsulta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/viewmag.png"))); // NOI18N
        acaBotoesConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaBotoesConsultaActionPerformed(evt);
            }
        });

        acaLabel3.setText("Data");

        acaDataCon.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(acaTextfieldConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabeTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acaLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaDataCon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                        .addComponent(acaBotoesConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(acaBotoesConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(acaLabeTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(acaTextfieldConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(acaLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(acaDataCon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
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

    private void jCheckBoxTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxTodosActionPerformed
        if (jCheckBoxTodos.isSelected()) {
            checkAcao(jCheckBoxTodos);
            acaLabeTipo.setText("Todos");
            acaTextfieldConsulta.setEnabled(false);
            acaDataCon.setEnabled(false);
        }
    }//GEN-LAST:event_jCheckBoxTodosActionPerformed

    private void jCheckBoxNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxNomeActionPerformed
        if (jCheckBoxNome.isSelected()) {
            checkAcao(jCheckBoxNome);
            acaLabeTipo.setText("Nome");
            acaTextfieldConsulta.setEnabled(true);
        }
    }//GEN-LAST:event_jCheckBoxNomeActionPerformed

    private void acaBotoesConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaBotoesConsultaActionPerformed
        opcaoConsulta();
}//GEN-LAST:event_acaBotoesConsultaActionPerformed

    private void jCheckBoxDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxDataActionPerformed
        if (jCheckBoxData.isSelected()) {
            checkAcao(jCheckBoxData);
            acaDataCon.setEnabled(true);
            acaTextfieldConsulta.setEnabled(false);
            acaLabeTipo.setText("");
        }
    }//GEN-LAST:event_jCheckBoxDataActionPerformed

    private void jCheckBoxStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxStatusActionPerformed
        if (jCheckBoxStatus.isSelected()) {
            checkAcao(jCheckBoxStatus);
            acaLabeTipo.setText("Pedido Aberto");
            acaTextfieldConsulta.setEnabled(false);
            acaDataCon.setEnabled(false);
            acaCombosStatus.setVisible(true);
        }
    }//GEN-LAST:event_jCheckBoxStatusActionPerformed

    private void acaBotoesLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaBotoesLimparActionPerformed
        tabela.limparPesquisa();
        jCheckBoxCodigo.isSelected();
    }//GEN-LAST:event_acaBotoesLimparActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private componentes.AcaBotoes acaBotoesConsulta;
    private componentes.AcaBotoes acaBotoesLimpar;
    public componentes.AcaCombo acaCombosStatus;
    private componentes.AcaData acaDataCon;
    private componentes.AcaLabel acaLabeTipo;
    private componentes.AcaLabel acaLabel3;
    private componentes.AcaTextfield acaTextfieldConsulta;
    private javax.swing.JCheckBox jCheckBoxCodigo;
    private javax.swing.JCheckBox jCheckBoxData;
    private javax.swing.JCheckBox jCheckBoxNome;
    private javax.swing.JCheckBox jCheckBoxStatus;
    private javax.swing.JCheckBox jCheckBoxTodos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPaneRes;
    // End of variables declaration//GEN-END:variables

    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            String pegacod = (String) tabela.getValueAt(tabela.getSelectedRow(), 0);
            telaMovimenta.dadosConsulta(Integer.parseInt(pegacod));
            telaMovimenta.dadosConsultaTipo(Integer.parseInt(pegacod));
            telaMovimenta.dadosConsulta(Integer.parseInt(pegacod), tabela.getValueAt(tabela.getSelectedRow(), 1).toString());
            telaMovimenta.habilitarBotoesConsulta(true);
            //JOptionPane.showMessageDialog(null, pegacod);
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

    public void posicaoTela() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 200);
    }
}

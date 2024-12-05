/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TelaMateriaPrima.java
 *
 * Created on 23/07/2010, 22:32:29
 */
package Telacadastros;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import persistencia.Materiaprima;
import Banco.Conexao;
import premoldados.TelaSistema;
import Telaconsulta.TelaConsulta;
import ajuda.PlayMovie;

/**
 *
 * @author chs
 */
public class TelaMateriaPrima extends TelaCadastro implements FocusListener, InternalFrameListener {

    public Materiaprima materiaPer = new Materiaprima();
    TelaConsulta consulta;
    TelaCustos custos;
    TelaUnidades unidades;
    public Vector colu = new Vector();

    /** Creates new form BeanForm */
    public TelaMateriaPrima() {
        initComponents();
        campos.add(acaTextfieldCodigo);
        campos.add(acaTextfieldNome);
        campos.add(acaTextfieldQtde);
        campos.add(acaMonetarioTotal);
        campos.add(acaComboStatus);
        campos.add(acaComboUnidade);
        if (tipoOperacao == INCLUINDO || tipoOperacao == PADRAO) {
            acaComboStatus.setSelectedIndex(1);
        } else {
            acaComboStatus.setSelectedIndex(-1);

        }
        campos.add(acaComboCusto);
        campos.add(acaMonetarioCompra);
        campos.add(acaBotoesCusto);
        campos.add(acaBotoesUni);
      
        habilitaCampos(false);
    }

    public void obterCampos() {
        if (tipoOperacao == ALTERANDO || tipoOperacao == EXCLUINDO) {
            materiaPer.setMatcod(Integer.parseInt(acaTextfieldCodigo.getText()));
        }
        materiaPer.setMatnome(acaTextfieldNome.getTexto());
        materiaPer.setMatstatus(acaComboStatus.getValor());
        materiaPer.setMatqtde(Float.parseFloat(acaTextfieldQtde.getText()));
        materiaPer.setMatvalor(Double.parseDouble(acaMonetarioTotal.getValor()));
        materiaPer.setUnicod(acaComboUnidade.getValor());
        materiaPer.setCustocod(acaComboCusto.getValor());
       
    }

    public void preencheCampos() {
        acaTextfieldCodigo.setText(String.valueOf(materiaPer.getMatcod()));
        acaTextfieldNome.setText(materiaPer.getMatnome());
        acaTextfieldQtde.setText(String.valueOf(materiaPer.getMatqtde()));
        acaMonetarioTotal.setValor(materiaPer.getMatvalor());
        if (tipoOperacao == ALTERANDO) {
            acaMonetarioCompra.setValor(materiaPer.getMatvalor());
        }
        acaComboCusto.setValor(materiaPer.getCustocod());
        acaComboStatus.setValor(materiaPer.getMatstatus());
        acaComboUnidade.setValor(materiaPer.getUnicod());
        acaLabelTipo.setText(acaComboUnidade.getSelectedItem().toString());


    }

    @Override
    protected void incluir() {
        super.incluir();
        campos.removeElement(acaTextfieldCodigo);

    }

    @Override
    protected void cancelar() {
        super.cancelar();
        campos.add(acaTextfieldCodigo);
    }

    @Override
    protected void consultar() {

        if (consulta == null) {
            consulta = new TelaConsulta("Consulta Materia Prima", this, new int[]{1, 2}, new String[]{"codigo", "nome"}, "materiaprima", "matcod", "matnome", null);
            colu.add("CODIGO");
            colu.add("NOME");
            consulta.setColu(colu);
            consulta.addInternalFrameListener(this);
            TelaSistema.jdp.add(consulta);
            consulta.setVisible(true);
            tipoOperacao = PESQUISANDO;
        }
        TelaSistema.jdp.moveToFront(consulta);
    }

    @Override
    protected void confirmar() {
        super.confirmar();
        campos.add(acaTextfieldCodigo);
        acaComboStatus.setSelectedIndex(1);
        acaComboCusto.setSelectedIndex(0);
        acaComboUnidade.setSelectedIndex(0);
    }

    @Override
    protected boolean incluirBD() {
        obterCampos();
        if (materiaPer.verificaDuplicidade()) {
            Conexao.executaSql(materiaPer.getInsereSql());
            return true;
        }
        return false;
    }

    @Override
    protected boolean alterarBD() {
        obterCampos();
        if (materiaPer.verificaDuplicidadeAtualizar()) {
            Conexao.executaSql(materiaPer.getAtualizaSql());
            return true;
        }
        return false;
    }

    @Override
    public void consultarDados(int codigo) {
        materiaPer.setMatcod(codigo);
        ResultSet rs = null;
        try {
            rs = Conexao.executaQuery(materiaPer.getConsultaTodos());
            rs.next();
            materiaPer.setMatcod(rs.getInt(1));
            materiaPer.setUnicod(rs.getInt(2));
            materiaPer.setCustocod(rs.getInt(3));
            materiaPer.setMatnome(rs.getString(4));
            materiaPer.setMatstatus(rs.getInt(5));
            materiaPer.setMatqtde(rs.getFloat(6));
            materiaPer.setMatvalor(rs.getDouble(7));
           
            preencheCampos();
            habilitaCampos(false);
            habilitaBotoesConsulta(true);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
    protected boolean excluirBD() {
        obterCampos();
        if ((Conexao.executaSqlExcluir(materiaPer.getExcluiSql()) == Conexao.DEPENDENCIA) || (Conexao.executaSqlExcluir(materiaPer.getExcluiSql()) == Conexao.OUTROERRO)) {
            habilitaCampos(false);
            habilitaBotoesConsulta(true);
            return false;
        } else {
            return true;
        }

    }
  @Override
    protected void ajuda() {
        PlayMovie videos = new PlayMovie("materia.wmv");


    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelDados = new javax.swing.JPanel();
        acaLabelCodigo = new componentes.AcaLabel();
        acaTextfieldCodigo = new componentes.AcaTextfield(false,"codigo",1);
        acaLabelNome = new componentes.AcaLabel();
        acaTextfieldNome = new componentes.AcaTextfield(true,"Nome",1);
        acaLabelValor = new componentes.AcaLabel();
        acaLabelStatus = new componentes.AcaLabel();
        acaComboStatus = new componentes.AcaCombo();
        acaComboStatus.comboFiltro(new int[]{0,1,2}, new String[]{"Inativo","Ativo","Negativo"});
        acaLabelQtde = new componentes.AcaLabel();
        acaTextfieldQtde = new componentes.AcaTextfield(true,"Quantidade",1);
        acaLabelUni = new componentes.AcaLabel();
        acaComboUnidade = new componentes.AcaCombo();
        acaComboUnidade.buscaResult("select unicod,unitipo from unidademat");
        acaLabelCusto = new componentes.AcaLabel();
        acaComboCusto = new componentes.AcaCombo();
        acaComboCusto.buscaResult("select custocod, custotipo from custos");
        acaMonetarioTotal = new componentes.AcaMonetario(true,"Valor total",1);
        acaLabelVlrCompra = new componentes.AcaLabel();
        acaMonetarioCompra = new componentes.AcaMonetario();
        acaLabelTipo = new componentes.AcaLabel();
        acaBotoesUni = new componentes.AcaBotoes(false, "Novo", "Nova Unidade de Medida");
        acaBotoesCusto = new componentes.AcaBotoes(false,"Novo","Novo Custo");

        setPreferredSize(new java.awt.Dimension(800, 320));
        setSize(new java.awt.Dimension(800, 320));
        setTitle("Cadastro de Materias Primas");

        jPanelDados.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelDados.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        acaLabelCodigo.setText("Codigo");
        jPanelDados.add(acaLabelCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 22, -1, -1));

        acaTextfieldCodigo.setEditable(false);
        jPanelDados.add(acaTextfieldCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 17, 77, -1));

        acaLabelNome.setText("Nome");
        jPanelDados.add(acaLabelNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));
        jPanelDados.add(acaTextfieldNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 255, -1));

        acaLabelValor.setText("Valor total");
        jPanelDados.add(acaLabelValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 140, -1, -1));

        acaLabelStatus.setText("Status");
        jPanelDados.add(acaLabelStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 22, -1, -1));

        jPanelDados.add(acaComboStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(213, 19, -1, -1));

        acaLabelQtde.setText("Quantidade em:");
        jPanelDados.add(acaLabelQtde, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 140, 99, -1));
        jPanelDados.add(acaTextfieldQtde, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 130, 43, -1));

        acaLabelUni.setText("Unidade de Medida");
        jPanelDados.add(acaLabelUni, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        acaComboUnidade.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                acaComboUnidadeItemStateChanged(evt);
            }
        });
        acaComboUnidade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                acaComboUnidadeFocusGained(evt);
            }
        });
        jPanelDados.add(acaComboUnidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 100, -1));

        acaLabelCusto.setText("Custo");
        jPanelDados.add(acaLabelCusto, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 60, -1, -1));

        acaComboCusto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                acaComboCustoItemStateChanged(evt);
            }
        });
        acaComboCusto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaComboCustoActionPerformed(evt);
            }
        });
        acaComboCusto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                acaComboCustoFocusGained(evt);
            }
        });
        jPanelDados.add(acaComboCusto, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 130, -1));

        acaMonetarioTotal.setEditable(false);
        jPanelDados.add(acaMonetarioTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 130, 85, -1));

        acaLabelVlrCompra.setText("Valor de Compra");
        jPanelDados.add(acaLabelVlrCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));
        jPanelDados.add(acaMonetarioCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 87, -1));
        jPanelDados.add(acaLabelTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(317, 105, -1, -1));

        acaBotoesUni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaBotoesUniActionPerformed(evt);
            }
        });
        jPanelDados.add(acaBotoesUni, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, -1, 21));

        acaBotoesCusto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaBotoesCustoActionPerformed(evt);
            }
        });
        jPanelDados.add(acaBotoesCusto, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 50, -1, 21));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getPainelCentral());
        getPainelCentral().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelDados, javax.swing.GroupLayout.PREFERRED_SIZE, 705, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelDados, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void acaComboUnidadeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_acaComboUnidadeItemStateChanged
        if (acaComboUnidade.getSelectedIndex() >= 0) {
            if (acaComboUnidade.isPopupVisible()) {
                acaLabelTipo.setText((String) acaComboUnidade.getSelectedItem());

            }
        }
    }//GEN-LAST:event_acaComboUnidadeItemStateChanged

    private void acaComboCustoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_acaComboCustoItemStateChanged
        if (acaComboCusto.getSelectedIndex() >= 0) {
            if (acaComboCusto.isPopupVisible()) {

                calculaCusto();
            }

        }
    }//GEN-LAST:event_acaComboCustoItemStateChanged

    private void acaBotoesUniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaBotoesUniActionPerformed
        if (unidades == null) {
            unidades = new TelaUnidades();
            unidades.addInternalFrameListener(this);
            TelaSistema.jdp.add(unidades);
            unidades.setVisible(true);

        }
        TelaSistema.jdp.moveToFront(unidades);
        tipoOperacao = NOVOITEM;
    }//GEN-LAST:event_acaBotoesUniActionPerformed

    private void acaBotoesCustoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaBotoesCustoActionPerformed
        if (custos == null) {
            custos = new TelaCustos();
            custos.addInternalFrameListener(this);
            TelaSistema.jdp.add(custos);
            custos.setVisible(true);

        }
        TelaSistema.jdp.moveToFront(custos);
        tipoOperacao = NOVOITEM;
    }//GEN-LAST:event_acaBotoesCustoActionPerformed

    private void acaComboUnidadeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_acaComboUnidadeFocusGained
        if (tipoOperacao == NOVOITEM) {
            acaComboUnidade.buscaResult("select unicod,unitipo from unidademat");

        }
    }//GEN-LAST:event_acaComboUnidadeFocusGained

    private void acaComboCustoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_acaComboCustoFocusGained
        if (tipoOperacao == NOVOITEM) {
            acaComboCusto.buscaResult("select custocod, custotipo from custos");
        }
    }//GEN-LAST:event_acaComboCustoFocusGained

    private void acaComboCustoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaComboCustoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_acaComboCustoActionPerformed
    public void calculaCusto() {
        try {
            ResultSet rs = Conexao.executaQuery("select custoperce from custos where custocod = " + acaComboCusto.getValor() + "");
            rs.next();

            double custo = rs.getDouble(1) / 100;
            double vlrUnitario = 0;
            if (tipoOperacao == ALTERANDO) {
                vlrUnitario = Double.parseDouble(acaMonetarioTotal.getValor());

                acaMonetarioTotal.setText("");
            } else {
                vlrUnitario = Double.parseDouble(acaMonetarioCompra.getValor());
            }
            double vlrTotal = ((double) ((custo * vlrUnitario))) + vlrUnitario;
            acaMonetarioTotal.setValor(vlrTotal);
        } catch (SQLException ex) {
            Logger.getLogger(TelaMateriaPrima.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private componentes.AcaBotoes acaBotoesCusto;
    private componentes.AcaBotoes acaBotoesUni;
    private componentes.AcaCombo acaComboCusto;
    private componentes.AcaCombo acaComboStatus;
    private componentes.AcaCombo acaComboUnidade;
    private componentes.AcaLabel acaLabelCodigo;
    private componentes.AcaLabel acaLabelCusto;
    private componentes.AcaLabel acaLabelNome;
    private componentes.AcaLabel acaLabelQtde;
    private componentes.AcaLabel acaLabelStatus;
    private componentes.AcaLabel acaLabelTipo;
    private componentes.AcaLabel acaLabelUni;
    private componentes.AcaLabel acaLabelValor;
    private componentes.AcaLabel acaLabelVlrCompra;
    private componentes.AcaMonetario acaMonetarioCompra;
    private componentes.AcaMonetario acaMonetarioTotal;
    private componentes.AcaTextfield acaTextfieldCodigo;
    private componentes.AcaTextfield acaTextfieldNome;
    private componentes.AcaTextfield acaTextfieldQtde;
    private javax.swing.JPanel jPanelDados;
    // End of variables declaration//GEN-END:variables

    public void focusGained(FocusEvent e) {
        if (e.getSource() == acaTextfieldQtde) {
        }
    }

    public void focusLost(FocusEvent e) {
    }

    public void internalFrameOpened(InternalFrameEvent e) {
    }

    public void internalFrameClosing(InternalFrameEvent e) {
    }

    public void internalFrameClosed(InternalFrameEvent e) {
        if (e.getSource() == consulta) {
            consulta = null;
        }
        if (e.getSource() == custos) {
            custos = null;
        }
        if (e.getSource() == unidades) {
            unidades = null;
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
}

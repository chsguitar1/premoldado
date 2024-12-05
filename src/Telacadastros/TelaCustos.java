/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TelaCustos.java
 *
 * Created on 27/09/2010, 21:18:30
 */
package Telacadastros;

import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import persistencia.Custos;
import Banco.Conexao;
import premoldados.TelaSistema;
import Telaconsulta.TelaConsulta;
import ajuda.PlayMovie;

/**
 *
 * @author chs
 */
public class TelaCustos extends TelaCadastro implements InternalFrameListener {

    public Custos custosPer = new Custos();
    TelaConsulta consulta;
    public Vector colu = new Vector();

    /** Creates new form BeanForm */
    public TelaCustos() {
        initComponents();
        campos.add(acaTextfieldPercen);
        campos.add(acaTextfieldTipo);
        campos.add(acaTextfieldCodigo);
        habilitaCampos(false);
    }

    public void obterCampos() {
        if (tipoOperacao == ALTERANDO || tipoOperacao == EXCLUINDO) {
            custosPer.setCustocod(Integer.parseInt(acaTextfieldCodigo.getText()));
        }
        custosPer.setCustotipo(acaTextfieldTipo.getTexto());
        custosPer.setCustoperce(Float.parseFloat(acaTextfieldPercen.getText()));
    }

    public void preencheCampos() {
        acaTextfieldCodigo.setText(String.valueOf(custosPer.getCustocod()));
        acaTextfieldTipo.setText(custosPer.getCustotipo());
        acaTextfieldPercen.setText(String.valueOf(custosPer.getCustoperce()));
    }

    @Override
    protected void incluir() {
        super.incluir();
        campos.removeElement(acaTextfieldCodigo);
    }

    @Override
    protected void consultar() {
        if (consulta == null) {
            consulta = new TelaConsulta("Consulta Custos", this, new int[]{1, 2}, new String[]{"Codigo", "Nome"}, "custos", "custocod", "custotipo", null);
            colu.addElement("CODIGO");
            colu.add("NOME");
            consulta.setColu(colu);
            //consulta.setTodosB(true);
            consulta.addInternalFrameListener(this);
            tipoOperacao = PESQUISANDO;
            TelaSistema.jdp.add(consulta);
            consulta.setVisible(true);
        }
        TelaSistema.jdp.moveToFront(consulta);
    }

    @Override
    protected boolean incluirBD() {
        obterCampos();
        if (custosPer.verificaDuplicidade()) {
            Conexao.executaSql(custosPer.getInsereSql());
            return true;
        }
        return false;
    }

    @Override
    protected boolean alterarBD() {
        obterCampos();
        if (custosPer.verificaDuplicidadeAtualiza()) {
            Conexao.executaSql(custosPer.getAtualizaSql());
            return true;
        }
        return false;
    }

    @Override
    public void consultarDados(int codigo) {
        try {
            custosPer.setCustocod(codigo);

            ResultSet rs = Conexao.executaQuery(custosPer.getConsultaTodos());
            rs.next();
            custosPer.setCustocod(rs.getInt(1));
            custosPer.setCustotipo(rs.getString(2));
            custosPer.setCustoperce(rs.getFloat(3));
            preencheCampos();
            habilitaCampos(false);
            habilitaBotoesConsulta(true);
        } catch (Exception e) {
        }
    }

    @Override
    protected boolean excluirBD() {
        obterCampos();
        if ((Conexao.executaSqlExcluir(custosPer.getExcluiSql()) == Conexao.DEPENDENCIA) || (Conexao.executaSqlExcluir(custosPer.getExcluiSql()) == Conexao.OUTROERRO)) {
            habilitaCampos(false);
            habilitaBotoesConsulta(true);
            return false;
        } else {
            return true;
        }

    }
  @Override
    protected void ajuda() {
        PlayMovie videos = new PlayMovie("custos.wmv");


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
        acaLabelCodgio = new componentes.AcaLabel();
        acaTextfieldCodigo = new componentes.AcaTextfield();
        acaLabelNome = new componentes.AcaLabel();
        acaTextfieldTipo = new componentes.AcaTextfield(true,"Tipo Percentual",1);
        acaLabel3 = new componentes.AcaLabel();
        acaTextfieldPercen = new componentes.AcaTextfield(true,"Percentual",1);

        setPreferredSize(new java.awt.Dimension(800, 300));
        setSize(new java.awt.Dimension(800, 300));
        setTitle("Cadastro de Custos");

        acaLabelCodgio.setText("Codigo");

        acaLabelNome.setText("Tipo Percentual");

        acaLabel3.setText("Percentual");

        acaTextfieldPercen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaTextfieldPercenActionPerformed(evt);
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
                        .addComponent(acaLabelCodgio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(acaTextfieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(acaLabelNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(acaTextfieldTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(108, 108, 108)
                .addComponent(acaLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(acaTextfieldPercen, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acaLabelCodgio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaTextfieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acaLabelNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaTextfieldPercen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaTextfieldTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getPainelCentral());
        getPainelCentral().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void acaTextfieldPercenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaTextfieldPercenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_acaTextfieldPercenActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private componentes.AcaLabel acaLabel3;
    private componentes.AcaLabel acaLabelCodgio;
    private componentes.AcaLabel acaLabelNome;
    private componentes.AcaTextfield acaTextfieldCodigo;
    private componentes.AcaTextfield acaTextfieldPercen;
    private componentes.AcaTextfield acaTextfieldTipo;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    public void internalFrameOpened(InternalFrameEvent e) {
    }

    public void internalFrameClosing(InternalFrameEvent e) {
    }

    public void internalFrameClosed(InternalFrameEvent e) {
        if (e.getSource() == consulta) {
            consulta = null;
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
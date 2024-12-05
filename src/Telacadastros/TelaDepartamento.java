/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TelaDepartamento.java
 *
 * Created on 01/09/2010, 22:18:39
 */
package Telacadastros;

import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import persistencia.Departamentos;
import Banco.Conexao;
import premoldados.TelaSistema;
import Telaconsulta.TelaConsulta;

/**
 *
 * @author chs
 */
public class TelaDepartamento extends TelaCadastro implements InternalFrameListener {

    public Departamentos departamentoPer = new Departamentos();
    public Vector colu = new Vector();
    TelaConsulta consulta;

    /** Creates new form BeanForm */
    public TelaDepartamento() {
        initComponents();
        campos.add(acaTextfieldNome);
        campos.add(acaTextfieldCodigo);
        campos.add(acaComboStatus);
        habilitaCampos(false);
    }

    public void obterCampos() {
        if (tipoOperacao == ALTERANDO) {
            departamentoPer.setDepcod(Integer.parseInt(acaTextfieldCodigo.getText()));
        }
        departamentoPer.setDepnome(acaTextfieldNome.getTexto());
        departamentoPer.setDepstatus(acaComboStatus.getValor());
        if (tipoOperacao == EXCLUINDO) {
            departamentoPer.setDepcod(Integer.parseInt(acaTextfieldCodigo.getText()));
        }
    }

    public void preencheCampos() {
        if (tipoOperacao == PESQUISANDO) {
            campos.add(acaTextfieldCodigo);
            acaTextfieldCodigo.setText(String.valueOf(departamentoPer.getDepcod()));
        }
        acaTextfieldNome.setText(departamentoPer.getDepnome());
        acaComboStatus.setValor(departamentoPer.getDepstatus());

    }

    @Override
    protected boolean incluirBD() {
        obterCampos();
        if (departamentoPer.verificaDuplicidade()) {
            Conexao.executaSql(departamentoPer.getInsereSql());
            return true;
        }
        return false;
    }

    @Override
    protected boolean alterarBD() {
        departamentoPer.setDepcod(Integer.parseInt(acaTextfieldCodigo.getText()));
        obterCampos();
        if (departamentoPer.verificaDuplicidadeAtualizar()) {
            Conexao.executaSql(departamentoPer.getAtualizaSql());
            return true;
        }
        return false;

    }

    @Override
    public boolean excluirBD() {
        obterCampos();
        if ((Conexao.executaSqlExcluir(departamentoPer.getExcluiSql()) == Conexao.DEPENDENCIA) || (Conexao.executaSqlExcluir(departamentoPer.getExcluiSql()) == Conexao.OUTROERRO)) {
            habilitaCampos(false);
            habilitaBotoesConsulta(true);
            return false;
        } else {
            return true;
        }
    }

    @Override
    protected void consultar() {
        if (consulta == null) {
            consulta = new TelaConsulta("Consulta Departamentos", this, new int[]{1, 2}, new String[]{"Codigo", "Nome"}, "departamentos", "Depcod", "Depnome", null);
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
        //  jXGlassBoxConsultar.setVisible(true);
        //acaComboTipo.setSelectedIndex(-1);

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
    public void consultarDados(int codigo) {

        try {
            departamentoPer.setDepcod(codigo);
            JOptionPane.showMessageDialog(null, departamentoPer.getConsultaTodos());
            ResultSet rs = Conexao.executaQuery(departamentoPer.getConsultaTodos());
            rs.next();
            departamentoPer.setDepcod(rs.getInt(1));
            departamentoPer.setDepnome(rs.getString(2));
            departamentoPer.setDepstatus(rs.getInt(3));
            preencheCampos();
            habilitaCampos(false);
            habilitaBotoesConsulta(true);

        } catch (Exception e) {
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

        jPanelDados = new javax.swing.JPanel();
        acaLabelCodigo = new componentes.AcaLabel();
        acaTextfieldNome = new componentes.AcaTextfield(true,"Nome",1);
        acaTextfieldCodigo = new componentes.AcaTextfield();
        acaLabelNome = new componentes.AcaLabel();
        acaComboStatus = new componentes.AcaCombo();
        acaComboStatus.comboFiltro(new int[]{0,1},new String[]{"Inativo","Ativo"});
        acaLabel1 = new componentes.AcaLabel();

        setTitle("Cadastro de Departamentos");

        acaLabelCodigo.setText("Codigo");

        acaTextfieldCodigo.setEditable(false);

        acaLabelNome.setText("Nome");

        acaLabel1.setText("Status");

        javax.swing.GroupLayout jPanelDadosLayout = new javax.swing.GroupLayout(jPanelDados);
        jPanelDados.setLayout(jPanelDadosLayout);
        jPanelDadosLayout.setHorizontalGroup(
            jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acaTextfieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaLabelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 243, Short.MAX_VALUE)
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acaLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaComboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(acaLabelNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaTextfieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanelDadosLayout.setVerticalGroup(
            jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(acaLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(acaComboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(acaLabelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(acaTextfieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(acaLabelNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(acaTextfieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getPainelCentral());
        getPainelCentral().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(91, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private componentes.AcaCombo acaComboStatus;
    private componentes.AcaLabel acaLabel1;
    private componentes.AcaLabel acaLabelCodigo;
    private componentes.AcaLabel acaLabelNome;
    private componentes.AcaTextfield acaTextfieldCodigo;
    private componentes.AcaTextfield acaTextfieldNome;
    private javax.swing.JPanel jPanelDados;
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
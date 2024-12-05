/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TelaColaborador.java
 *
 * Created on 01/09/2010, 22:30:03
 */
package Telacadastros;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import persistencia.Colaboradores;
import Banco.Conexao;
import premoldados.TelaSistema;
import Telaconsulta.TelaConsulta;

/**
 *
 * @author chs
 */
public class TelaColaborador extends TelaCadastro implements InternalFrameListener {

    public Colaboradores colaboPer = new Colaboradores();
    public Vector colu = new Vector();
    TelaConsulta consulta;
    TelaFuncao telafuncao;
    TelaDepartamento teladepartamento;

    /** Creates new form BeanForm */
    public TelaColaborador() {
        initComponents();
        campos.add(acaTextfieldNome);
        campos.add(acaComboDep);
        campos.add(acaComboFunc);
        campos.add(acaTextfieldCodigo);
        campos.add(acaComboStatus);
        campos.add(acaBotoesNDep);
        campos.add(acaBotoesNFunc);
        habilitaCampos(false);
    }

    public void obterCampos() {
        if (tipoOperacao == ALTERANDO) {
            colaboPer.setDepcod(Integer.parseInt(acaTextfieldCodigo.getText()));
        }
        colaboPer.setColnome(acaTextfieldNome.getTexto());
        colaboPer.setColstatus(acaComboStatus.getValor());
        colaboPer.setDepcod(acaComboDep.getValor());
        colaboPer.setFunccod(acaComboFunc.getValor());
        if (tipoOperacao == EXCLUINDO) {
            colaboPer.setDepcod(Integer.parseInt(acaTextfieldCodigo.getText()));
        }
    }

    public void preencheCampos() {
        if (tipoOperacao == PESQUISANDO) {
            campos.add(acaTextfieldCodigo);
            acaTextfieldCodigo.setText(String.valueOf(colaboPer.getDepcod()));
        }
        acaTextfieldNome.setText(colaboPer.getColnome());
        acaComboStatus.setValor(colaboPer.getColstatus());
        acaComboDep.setValor(colaboPer.getDepcod());
        acaComboFunc.setValor(colaboPer.getFunccod());

    }

    @Override
    protected boolean incluirBD() {
        obterCampos();
        if (colaboPer.verificaDuplicidade()) {
            Conexao.executaSql(colaboPer.getInsereSql());
            return true;
        }
        return false;
    }

    @Override
    protected boolean alterarBD() {
        colaboPer.setDepcod(Integer.parseInt(acaTextfieldCodigo.getText()));
        obterCampos();
        if (colaboPer.verificaDuplicidadeAtualizar()) {
            Conexao.executaSql(colaboPer.getAtualizaSql());
            return true;
        }
        return false;

    }

    @Override
    public boolean excluirBD() {
        obterCampos();
        if ((Conexao.executaSqlExcluir(colaboPer.getExcluiSql()) == Conexao.DEPENDENCIA) || (Conexao.executaSqlExcluir(colaboPer.getExcluiSql()) == Conexao.OUTROERRO)) {
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
            consulta = new TelaConsulta("Consulta Colaboradores", this, new int[]{1, 2,}, new String[]{"Codigo", "Nome",}, "colaboradores", "colcod", "Colnome", null);
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
    protected void confirmar() {
        super.confirmar();
        campos.add(acaTextfieldCodigo);
        habilitaCampos(false);

    }

    @Override
    public void consultarDados(int codigo) {

        try {
            colaboPer.setColcod(codigo);
            JOptionPane.showMessageDialog(null, colaboPer.getConsultaTodos());
            ResultSet rs = Conexao.executaQuery(colaboPer.getConsultaTodos());
            rs.next();
            colaboPer.setColcod(rs.getInt(1));
            colaboPer.setDepcod(rs.getInt(2));
            colaboPer.setFunccod(rs.getInt(3));
            colaboPer.setColnome(rs.getString(4));
            colaboPer.setColstatus(rs.getInt(5));
            preencheCampos();
            habilitaCampos(false);
            habilitaBotoesConsulta(true);

        } catch (SQLException e) {
            e.printStackTrace();
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
        acaLabelFunc = new componentes.AcaLabel();
        acaLabelDep = new componentes.AcaLabel();
        acaTextfieldCodigo = new componentes.AcaTextfield();
        acaLabelCodigo = new componentes.AcaLabel();
        acaTextfieldNome = new componentes.AcaTextfield(true,"Nome",1);
        acaLabelNome = new componentes.AcaLabel();
        acaComboFunc = new componentes.AcaCombo(true,"Função");
        acaComboFunc.buscaResult("select funccod,funcnome from funcoes");
        acaComboDep = new componentes.AcaCombo(true,"Departamento");
        acaComboDep.buscaResult("select depcod,depnome from departamentos");
        acaLabel1 = new componentes.AcaLabel();
        acaComboStatus = new componentes.AcaCombo();
        acaComboStatus.comboFiltro(new int[]{0,1}, new String[]{"Inativo","Ativo"});
        acaBotoesNDep = new componentes.AcaBotoes(false,"Novo","Novo Departamento");
        acaBotoesNFunc = new componentes.AcaBotoes(false,"Novo","Nova Função");

        setPreferredSize(new java.awt.Dimension(720, 320));
        setSize(new java.awt.Dimension(720, 320));
        setTitle("Cadastro de Colaboradores");

        acaLabelFunc.setText("Função");

        acaLabelDep.setText("Departamento");

        acaTextfieldCodigo.setEditable(false);

        acaLabelCodigo.setText("Codigo");

        acaTextfieldNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaTextfieldNomeActionPerformed(evt);
            }
        });

        acaLabelNome.setText("Nome");

        acaComboFunc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                acaComboFuncFocusGained(evt);
            }
        });

        acaComboDep.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                acaComboDepFocusGained(evt);
            }
        });

        acaLabel1.setText("Status");

        acaBotoesNDep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaBotoesNDepActionPerformed(evt);
            }
        });

        acaBotoesNFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaBotoesNFuncActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(acaLabelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaTextfieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabelNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaTextfieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(acaComboDep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(acaBotoesNDep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(acaLabelDep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(acaLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaComboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabelFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(acaComboFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(acaBotoesNFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(101, 101, 101))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acaLabelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(acaTextfieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(acaLabelNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(acaTextfieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(acaComboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(acaLabelDep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(acaComboDep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaBotoesNDep, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(acaLabelFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(acaComboFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaBotoesNFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getPainelCentral());
        getPainelCentral().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void acaTextfieldNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaTextfieldNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_acaTextfieldNomeActionPerformed

    private void acaBotoesNDepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaBotoesNDepActionPerformed
        if (teladepartamento == null) {
            teladepartamento = new TelaDepartamento();
            teladepartamento.addInternalFrameListener(this);
            TelaSistema.jdp.add(teladepartamento);
            teladepartamento.setVisible(true);
        }
        TelaSistema.jdp.moveToFront(teladepartamento);
        tipoOperacao = NOVOITEM;
    }//GEN-LAST:event_acaBotoesNDepActionPerformed

    private void acaBotoesNFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaBotoesNFuncActionPerformed
        if (telafuncao == null) {
            telafuncao = new TelaFuncao();
            telafuncao.addInternalFrameListener(this);
            TelaSistema.jdp.add(telafuncao);
            telafuncao.setVisible(true);
        }
        TelaSistema.jdp.moveToFront(teladepartamento);
        tipoOperacao = NOVOITEM;
    }//GEN-LAST:event_acaBotoesNFuncActionPerformed

    private void acaComboDepFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_acaComboDepFocusGained
        if (tipoOperacao == NOVOITEM) {
            acaComboDep.buscaResult("select depcod,depnome from departamentos");

        }
    }//GEN-LAST:event_acaComboDepFocusGained

    private void acaComboFuncFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_acaComboFuncFocusGained
       if(tipoOperacao == NOVOITEM){
       acaComboFunc.buscaResult("select funccod,funcnome from funcoes");
       }
    }//GEN-LAST:event_acaComboFuncFocusGained

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private componentes.AcaBotoes acaBotoesNDep;
    private componentes.AcaBotoes acaBotoesNFunc;
    private componentes.AcaCombo acaComboDep;
    private componentes.AcaCombo acaComboFunc;
    private componentes.AcaCombo acaComboStatus;
    private componentes.AcaLabel acaLabel1;
    private componentes.AcaLabel acaLabelCodigo;
    private componentes.AcaLabel acaLabelDep;
    private componentes.AcaLabel acaLabelFunc;
    private componentes.AcaLabel acaLabelNome;
    private componentes.AcaTextfield acaTextfieldCodigo;
    private componentes.AcaTextfield acaTextfieldNome;
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
        if (e.getSource() == telafuncao) {
            telafuncao = null;
        }
        if (e.getSource() == teladepartamento) {
            teladepartamento = null;
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

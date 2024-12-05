/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TelaVeiculos.java
 *
 * Created on 03/09/2010, 19:02:05
 */
package Telacadastros;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import persistencia.Veiculos;
import Banco.Conexao;
import premoldados.TelaSistema;
import Telaconsulta.TelaConsulta;

/**
 *
 * @author chs
 */
public class TelaVeiculos extends TelaCadastro implements InternalFrameListener {

    TelaCidade telacidade;
    public Veiculos veiculoPer = new Veiculos();
    public Vector colu = new Vector();
    TelaConsulta consulta;

    /** Creates new form BeanForm */
    public TelaVeiculos() {
        initComponents();
        acaComboEstado.setSelectedIndex(-1);
        campos.add(acaTextfieldCodigo);
        campos.add(acaTextfieldModelo);
        campos.add(acaTextfieldAno);
        campos.add(acaTextfieldCor);
        campos.add(acaComboCidade);
        campos.add(acaComboEstado);
        campos.add(acaTextfieldPlaca);
        campos.add(acaComboStatus);
        campos.add(acaBotoesNCidade);
        campos.add(acaTextfieldKm);
        habilitaCampos(false);
    }

    public void obterCampos() {
        if (tipoOperacao == ALTERANDO || tipoOperacao == EXCLUINDO) {
            veiculoPer.setVeicod(Integer.parseInt(acaTextfieldCodigo.getText()));
        }
        veiculoPer.setCidcod(acaComboCidade.getValor());
        veiculoPer.setVeiano(acaTextfieldAno.getTexto());
        veiculoPer.setVeicor(acaTextfieldCor.getTexto());
        veiculoPer.setVeikilometro(acaTextfieldKm.getText());
        veiculoPer.setVeistatus(acaComboStatus.getValor());
        veiculoPer.setVeiplaca(acaTextfieldPlaca.getTexto());
        veiculoPer.setVeimodelo(acaTextfieldModelo.getTexto());
    }

    public void preencheCampos() {
        acaTextfieldCodigo.setText(String.valueOf(veiculoPer.getVeicod()));
        acaTextfieldAno.setText(veiculoPer.getVeiano());
        acaTextfieldCor.setText(veiculoPer.getVeicor());
        acaTextfieldKm.setText(veiculoPer.getVeikilometro());
        acaTextfieldModelo.setText(veiculoPer.getVeimodelo());
        acaTextfieldPlaca.setText(veiculoPer.getVeiplaca());
        acaComboStatus.setValor(veiculoPer.getVeistatus());
        try {
            ResultSet rs = Conexao.executaQuery("execute procedure comboestador(" + veiculoPer.getCidcod() + ")");
            rs.next();
            int codigoEstado = rs.getInt(1);
            acaComboEstado.buscaResult("select estcod  ,estnome from estado");
            acaComboEstado.setValor(codigoEstado);
        } catch (SQLException a) {
            a.printStackTrace();
        }
        acaComboCidade.buscaResult("select cidcod,cidnome from cidade");
        acaComboCidade.setValor(veiculoPer.getCidcod());
    }

    @Override
    protected boolean alterarBD() {
        veiculoPer.setVeicod(Integer.parseInt(acaTextfieldAno.getText()));
        obterCampos();
        if (veiculoPer.verificaDuplicidadeAtualizar()) {
            Conexao.executaSql(veiculoPer.getAtualizaSql());
            return true;
        }
        return false;
    }

    @Override
    public void incluir() {
        super.incluir();
        campos.removeElement(acaTextfieldCodigo);

    }

    @Override
    public void cancelar() {
        super.cancelar();
        campos.add(acaTextfieldCodigo);
        habilitaCampos(false);
    }

    @Override
    public void confirmar() {
        super.confirmar();
        campos.add(acaTextfieldCodigo);
        habilitaCampos(false);
    }

    @Override
    protected void consultar() {
        if (consulta == null) {
            consulta = new TelaConsulta("Consulta Veiculos", this, new int[]{1, 2,}, new String[]{"Codigo", "Placa",}, "veiculos", "veicod", "veiplaca", null);
            colu.addElement("CODIGO");
            colu.add("PLACA");

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
        if (veiculoPer.verificaDuplicidade()) {
            Conexao.executaSql(veiculoPer.getInsereSql());
            return true;
        }
        return false;
    }

    @Override
    protected boolean excluirBD() {
        obterCampos();
        if (Conexao.executaSqlExcluir(veiculoPer.getExcluiSql()) == Conexao.OUTROERRO) {
            return false;
        }
        if (Conexao.executaSqlExcluir(veiculoPer.getExcluiSql()) == Conexao.DEPENDENCIA) {
            Object[] botoes = {"Sim", "Não"};
            int opcao = JOptionPane.showOptionDialog(null, "Não foi possivel excluir o cadasro\n deseja INATIVAR o mesmo ",
                    "Sair", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes, botoes[0]);
            if (opcao == JOptionPane.YES_OPTION) {
                Conexao.executaSql(veiculoPer.getProcedureDel());
                limparCampos();
                habilitarBotoes(true);
                habilitaCampos(false);
                return true;

            }
            if (opcao == JOptionPane.NO_OPTION) {

                habilitarBotoes(true);
                return false;
            }

        }
        Conexao.executaSql(veiculoPer.getExcluiSql());
        limparCampos();
        habilitarBotoes(true);
        habilitaCampos(false);

        return true;
    }

    @Override
    public void consultarDados(int codigo) {

        try {
            veiculoPer.setVeicod(codigo);
            ResultSet rs = Conexao.executaQuery(veiculoPer.getConsultaTodos());
            rs.next();
            veiculoPer.setVeicod(rs.getInt(1));
            veiculoPer.setCidcod(rs.getInt(2));
            veiculoPer.setVeimodelo(rs.getString(3));
            veiculoPer.setVeicor(rs.getString(4));
            veiculoPer.setVeiano(rs.getString(5));
            veiculoPer.setVeiplaca(rs.getString(6));
            veiculoPer.setVeikilometro(rs.getString(7));
            veiculoPer.setVeistatus(rs.getInt(8));
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
        acaComboCidade = new componentes.AcaCombo();
        acaLabelModelo = new componentes.AcaLabel();
        acaLabelCidade = new componentes.AcaLabel();
        acaComboEstado = new componentes.AcaCombo();
        acaComboEstado.buscaResult("select estcod,estnome from estado");
        acaTextfieldCodigo = new componentes.AcaTextfield();
        acaLabelCodigo = new componentes.AcaLabel();
        acaTextfieldModelo = new componentes.AcaTextfield(true,"Modelo",1);
        acaLabelAno = new componentes.AcaLabel();
        acaTextfieldPlaca = new componentes.AcaTextfield(true,"Placa",1);
        acaLabelEstado = new componentes.AcaLabel();
        acaTextfieldAno = new componentes.AcaTextfield(true,"Ano",1);
        acaLabelPlaca = new componentes.AcaLabel();
        acaLabelCor = new componentes.AcaLabel();
        acaTextfieldCor = new componentes.AcaTextfield(true,"Cor",1);
        acaLabelStatus = new componentes.AcaLabel();
        acaComboStatus = new componentes.AcaCombo();
        acaComboStatus.comboFiltro(new int[]{-1,0,1}, new String[]{"Selecionar","Inativo","Ativo"});
        acaBotoesNCidade = new componentes.AcaBotoes(false,"Novo","Nova Cidade");
        acaLabel1 = new componentes.AcaLabel();
        acaTextfieldKm = new componentes.AcaTextfield(false,null,1);

        setPreferredSize(new java.awt.Dimension(850, 320));
        setSize(new java.awt.Dimension(850, 320));
        setTitle("Cadastro de Veiculos");

        acaComboCidade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                acaComboCidadeFocusGained(evt);
            }
        });

        acaLabelModelo.setText("Modelo");

        acaLabelCidade.setText("Cidade");

        acaComboEstado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                acaComboEstadoItemStateChanged(evt);
            }
        });

        acaTextfieldCodigo.setEditable(false);

        acaLabelCodigo.setText("Codigo");

        acaLabelAno.setText("Ano");

        acaLabelEstado.setText("Estado");

        acaLabelPlaca.setText("Placa");

        acaLabelCor.setText("Cor");

        acaLabelStatus.setText("Status");

        acaBotoesNCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaBotoesNCidadeActionPerformed(evt);
            }
        });

        acaLabel1.setText("Kilometragem");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(acaTextfieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acaLabelModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaTextfieldModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaLabelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(acaLabelEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(acaComboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(72, 72, 72)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(acaComboCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(acaBotoesNCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(acaLabelCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acaLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(acaTextfieldAno, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(acaLabelAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(acaLabelPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(acaTextfieldPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(acaComboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(acaLabelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(2, 2, 2)
                                                .addComponent(acaTextfieldCor, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
                                            .addComponent(acaLabelCor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(acaTextfieldKm, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(acaLabelPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(acaTextfieldPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(acaLabelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(acaLabelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaComboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(acaTextfieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(acaLabelAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(acaTextfieldAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(acaLabelModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(acaTextfieldModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(acaLabelCor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(acaTextfieldCor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(acaLabelEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaLabelCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(acaComboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaComboCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaBotoesNCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(acaLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(acaTextfieldKm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getPainelCentral());
        getPainelCentral().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void acaComboEstadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_acaComboEstadoItemStateChanged
        if (acaComboEstado.getSelectedIndex() >= 0) {
            if (acaComboEstado.isPopupVisible()) {
                acaComboCidade.buscaResult("select cidcod,cidnome from cidade where estcod = " + acaComboEstado.getValor() + "");
            }
        }
    }//GEN-LAST:event_acaComboEstadoItemStateChanged

    private void acaBotoesNCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaBotoesNCidadeActionPerformed
        if (telacidade == null) {
            telacidade = new TelaCidade();
            telacidade.addInternalFrameListener(this);
            TelaSistema.jdp.add(telacidade);
            telacidade.setVisible(true);
            //telaestado.setSize(800, 300);
        }
        TelaSistema.jdp.moveToFront(telacidade);
        tipoOperacao = NOVOITEM;
    }//GEN-LAST:event_acaBotoesNCidadeActionPerformed

    private void acaComboCidadeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_acaComboCidadeFocusGained
        if (tipoOperacao == NOVOITEM) {
            acaComboCidade.buscaResult("select cidcod,cidnome from cidade");
        }
    }//GEN-LAST:event_acaComboCidadeFocusGained
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private componentes.AcaBotoes acaBotoesNCidade;
    private componentes.AcaCombo acaComboCidade;
    private componentes.AcaCombo acaComboEstado;
    private componentes.AcaCombo acaComboStatus;
    private componentes.AcaLabel acaLabel1;
    private componentes.AcaLabel acaLabelAno;
    private componentes.AcaLabel acaLabelCidade;
    private componentes.AcaLabel acaLabelCodigo;
    private componentes.AcaLabel acaLabelCor;
    private componentes.AcaLabel acaLabelEstado;
    private componentes.AcaLabel acaLabelModelo;
    private componentes.AcaLabel acaLabelPlaca;
    private componentes.AcaLabel acaLabelStatus;
    private componentes.AcaTextfield acaTextfieldAno;
    private componentes.AcaTextfield acaTextfieldCodigo;
    private componentes.AcaTextfield acaTextfieldCor;
    private componentes.AcaTextfield acaTextfieldKm;
    private componentes.AcaTextfield acaTextfieldModelo;
    private componentes.AcaTextfield acaTextfieldPlaca;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    public void internalFrameOpened(InternalFrameEvent e) {
    }

    public void internalFrameClosing(InternalFrameEvent e) {
    }

    public void internalFrameClosed(InternalFrameEvent e) {
        if (e.getSource() == telacidade) {
            telacidade = null;
        }
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

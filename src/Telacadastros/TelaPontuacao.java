/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TelaPontuacao.java
 *
 * Created on 15/07/2010, 20:57:41
 */
package Telacadastros;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import modelos.renderAlinha;
import persistencia.Itenspontuacao;
import persistencia.Pontuacao;
import Banco.Conexao;
import premoldados.TelaSistema;
import Telaconsulta.TelaConsulta;
import ajuda.PlayMovie;
import componentes.AcaTabela;

/**
 *
 * @author chs
 */
public class TelaPontuacao extends TelaCadastro implements MouseListener, InternalFrameListener {

    protected AcaTabela tabela = new AcaTabela();
    public Pontuacao pontuacaoper = new Pontuacao();
    public Itenspontuacao itensponper = new Itenspontuacao();
    public Vector colu = new Vector();
    public Vector itens = new Vector();
    TelaConsulta consulta;
    private Vector CodItens;
    public String pegacodigo = "";
    private JTable jtTabela = new JTable() {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private DefaultTableModel dtm;
    private renderAlinha renderer = new renderAlinha();

    /** Creates new form BeanForm */
    public TelaPontuacao() {

        jtTabela.addMouseListener(this);
        montaTabela(new String[]{"Codigo", "Nome"});
        initComponents();
//        String[] column = {"Nome"};
//        dtm = (DefaultTableModel) jtTabela.getModel();
//        for (int i = 0; i < column.length; i++) {
//            dtm.addColumn(column[i]);
//        }
//
//        jtTabela.getColumn(jtTabela.getColumnName(0)).setPreferredWidth(50);

//        jtTabela.getColumn(jtTabela.getColumnName(2)).setPreferredWidth(90);
//        jtTabela.getColumn(jtTabela.getColumnName(3)).setPreferredWidth(90);
//        jtTabela.getColumn(jtTabela.getColumnName(4)).setPreferredWidth(90);
//        jtTabela.getColumn(jtTabela.getColumnName(5)).setPreferredWidth(100);
//        jtTabela.setModel(dtm);
//        TableColumnModel modeloColuna = jtTabela.getColumnModel();
//
//        jtTabela.getTableHeader().setReorderingAllowed(false);
        /*jtTabela.getColumnModel().getColumn(0).setMaxWidth(300);
        jtTabela.getColumnModel().getColumn(1).setMaxWidth(60);
        //jtTabela.getColumnModel().getColumn(0).setPreferredWidth(10);
        jtTabela.getColumnModel().getColumn(0).setResizable(false);
        jtTabela.getColumnModel().getColumn(1).setPreferredWidth(0);

        jtTabela.getColumnModel().getColumn(1).setResizable(false);*/
        /*DefaultTableCellRenderer renderer1 = new DefaultTableCellRenderer();
        //Definindo o alinhamento das células do modelo como Centralizado
        renderer1.setHorizontalAlignment(SwingConstants.CENTER);*/

        campos.add(acaTextfieldCodigo);
        campos.add(acaTextfieldItensN);
        campos.add(acaTextfieldNome);
        habilitaCampos(false);

    }

    public void montaTabela(String[] colunas) {

        dtm = (DefaultTableModel) jtTabela.getModel();
        for (int i = 0; i < colunas.length; i++) {
            dtm.addColumn(colunas[i]);
        }

        jtTabela.getColumn(jtTabela.getColumnName(0)).setPreferredWidth(50);

//        jtTabela.getColumn(jtTabela.getColumnName(2)).setPreferredWidth(90);
//        jtTabela.getColumn(jtTabela.getColumnName(3)).setPreferredWidth(90);
//        jtTabela.getColumn(jtTabela.getColumnName(4)).setPreferredWidth(90);
//        jtTabela.getColumn(jtTabela.getColumnName(5)).setPreferredWidth(100);
        jtTabela.setModel(dtm);
        TableColumnModel modeloColuna = jtTabela.getColumnModel();

        jtTabela.getTableHeader().setReorderingAllowed(false);
        /*jtTabela.getColumnModel().getColumn(0).setMaxWidth(300);
        jtTabela.getColumnModel().getColumn(1).setMaxWidth(60);
        //jtTabela.getColumnModel().getColumn(0).setPreferredWidth(10);
        jtTabela.getColumnModel().getColumn(0).setResizable(false);
        jtTabela.getColumnModel().getColumn(1).setPreferredWidth(0);

        jtTabela.getColumnModel().getColumn(1).setResizable(false);*/
        /*DefaultTableCellRenderer renderer1 = new DefaultTableCellRenderer();
        //Definindo o alinhamento das células do modelo como Centralizado
        renderer1.setHorizontalAlignment(SwingConstants.CENTER);*/



    }

    public void montaTabela(String textoSql) {

        try {
            while (dtm.getRowCount() > 0) {
                dtm.removeRow(0);
            }
            ResultSet rs1 = Conexao.executaQuery(textoSql);
            ResultSetMetaData rsmd1 = rs1.getMetaData();
            Vector vetor;
            while (rs1.next()) {
                vetor = new Vector();
                for (int i = 0; i < rsmd1.getColumnCount(); i++) {
                    vetor.add(rs1.getString(i + 1));
//            


                }
                dtm.addRow(vetor);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível efetuar a pesquisa");
            e.printStackTrace();
        }

        TableColumnModel modeloColuna = jtTabela.getColumnModel();
        jtTabela.getTableHeader().setReorderingAllowed(false);
        jtTabela.getColumnModel().getColumn(0).setMaxWidth(70);
        jtTabela.getColumnModel().getColumn(0).setPreferredWidth(95);
        jtTabela.getColumnModel().getColumn(0).setResizable(false);
        jtTabela.getColumnModel().getColumn(1).setMaxWidth(200);
        jtTabela.getColumnModel().getColumn(1).setPreferredWidth(200);

        jtTabela.getColumnModel().getColumn(1).setResizable(false);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelPon = new javax.swing.JPanel();
        acaLabel1 = new componentes.AcaLabel();
        acaTextfieldCodigo = new componentes.AcaTextfield();
        acaLabel2 = new componentes.AcaLabel();
        acaTextfieldNome = new componentes.AcaTextfield();
        jPanelItensPon = new javax.swing.JPanel();
        acaLabelNomeItens = new componentes.AcaLabel();
        acaTextfieldItensN = new componentes.AcaTextfield();
        jScrollPaneTabela = new javax.swing.JScrollPane(jtTabela);
        acaBotoesAddIt = new componentes.AcaBotoes(false,"","Adiciona Novo Item");
        acaBotoesDelIt = new componentes.AcaBotoes(false,"","Remove Itens");
        acaBotoesDelItens = new componentes.AcaBotoes(false, "", "Exlcuir Itens");

        setPreferredSize(new java.awt.Dimension(850, 490));
        setSize(new java.awt.Dimension(850, 490));
        setTitle("Cadastro de Pontuação");

        jPanelPon.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados da Pontuação"));

        acaLabel1.setText("Codigo");

        acaTextfieldCodigo.setEditable(false);

        acaLabel2.setText("Nome da Pontuação");

        javax.swing.GroupLayout jPanelPonLayout = new javax.swing.GroupLayout(jPanelPon);
        jPanelPon.setLayout(jPanelPonLayout);
        jPanelPonLayout.setHorizontalGroup(
            jPanelPonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(acaLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(acaTextfieldCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(77, 77, 77)
                .addComponent(acaLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(acaTextfieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanelPonLayout.setVerticalGroup(
            jPanelPonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPonLayout.createSequentialGroup()
                .addGroup(jPanelPonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acaLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaTextfieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaTextfieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanelItensPon.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Itens da Pontuação", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        acaLabelNomeItens.setText("Nome  dos Itens ");

        acaBotoesAddIt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/edit_add1.png"))); // NOI18N
        acaBotoesAddIt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaBotoesAddItActionPerformed(evt);
            }
        });

        acaBotoesDelIt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/x.png"))); // NOI18N
        acaBotoesDelIt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaBotoesDelItActionPerformed(evt);
            }
        });

        acaBotoesDelItens.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/edit_remove.png"))); // NOI18N
        acaBotoesDelItens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaBotoesDelItensActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelItensPonLayout = new javax.swing.GroupLayout(jPanelItensPon);
        jPanelItensPon.setLayout(jPanelItensPonLayout);
        jPanelItensPonLayout.setHorizontalGroup(
            jPanelItensPonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelItensPonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelItensPonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelItensPonLayout.createSequentialGroup()
                        .addComponent(jScrollPaneTabela, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(acaBotoesDelItens, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelItensPonLayout.createSequentialGroup()
                        .addComponent(acaLabelNomeItens, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(acaTextfieldItensN, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(acaBotoesAddIt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(acaBotoesDelIt, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(179, Short.MAX_VALUE))
        );
        jPanelItensPonLayout.setVerticalGroup(
            jPanelItensPonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelItensPonLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanelItensPonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(acaLabelNomeItens, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelItensPonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(acaBotoesDelIt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(acaBotoesAddIt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(acaTextfieldItensN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelItensPonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneTabela, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelItensPonLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(acaBotoesDelItens, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getPainelCentral());
        getPainelCentral().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelItensPon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelPon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(799, 799, 799))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelPon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelItensPon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void acaBotoesAddItActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaBotoesAddItActionPerformed
        if (acaTextfieldItensN.getText().equals("")) {
            return;
        }
        addItem();
        acaTextfieldItensN.setText("");


    }//GEN-LAST:event_acaBotoesAddItActionPerformed

    private void acaBotoesDelItActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaBotoesDelItActionPerformed

        limparPesquisa();

    }//GEN-LAST:event_acaBotoesDelItActionPerformed

    private void acaBotoesDelItensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaBotoesDelItensActionPerformed
        dtm.removeRow(jtTabela.getSelectedRow());
    }//GEN-LAST:event_acaBotoesDelItensActionPerformed
    public void obterCampos() {
        if (tipoOperacao == ALTERANDO) {
            pontuacaoper.setPoncod(Integer.parseInt(acaTextfieldCodigo.getText()));
        }
        pontuacaoper.setPonnome(acaTextfieldNome.getText().toUpperCase());
        if (tipoOperacao == EXCLUINDO) {
            pontuacaoper.setPoncod(Integer.parseInt(acaTextfieldCodigo.getText()));
        }
    }

    public void preencheCampos() {
        if (tipoOperacao == PESQUISANDO) {
            acaTextfieldCodigo.setText(String.valueOf(pontuacaoper.getPoncod()));
        }
        acaTextfieldNome.setText(pontuacaoper.getPonnome());

    }

    public void PrencheTabela(Object[] item) {
        dtm = (DefaultTableModel) jtTabela.getModel();
        dtm.addRow(item);

        jtTabela.setModel(dtm);
    }

    public void PreencheTabela(Vector vetor) {
        dtm.addRow(vetor);
    }

    public void addItem() {

        Object[] item = {pegacodigo, acaTextfieldItensN.getText().toUpperCase()};
        PrencheTabela(item);
        //acaTextfieldTotal.setText(String.valueOf(calculaTotal()));
    }

//    public double calculaTotal() {
//        double soma = 0;
//        for (int i = 0; i < jtTabela.getRowCount(); i++) {
//            String valor = String.valueOf(jtTabela.getValueAt(i, 1));
//            soma = soma + Double.parseDouble(valor);
//        }
//        return soma;
//    }
    @Override
    protected void incluir() {
        super.incluir();

    }

    @Override
    protected void alterar() {
        super.alterar();
        jtTabela.setEnabled(true);
    }

    @Override
    protected void cancelar() {
        super.cancelar();
        limparPesquisa();
    }

    @Override
    protected void confirmar() {
        if (dtm.getRowCount() > 0) {
            super.confirmar();
            limparPesquisa();
        } else {
            JOptionPane.showMessageDialog(null, "Itens da Pontuação são Obrigatorios");
            return;
        }

    }

    @Override
    protected void consultar() {
        if (consulta == null) {
            consulta = new TelaConsulta("Consulta Pontuação", this, new int[]{1, 2}, new String[]{"Codigo", "Nome"}, "pontuacao", "poncod", "ponnome", null);
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
        if (pontuacaoper.verificaDuplicidade()) {
            ResultSet rs = null;
            try {
                rs = pontuacaoper.getInsereRs();
                rs.next();
                itensponper.setPoncod(rs.getInt(1));

                for (int x = 0; x < jtTabela.getRowCount(); x++) {

                    itensponper.setItenponnome((String) jtTabela.getValueAt(x, 1).toString().toUpperCase());
                    Conexao.executaSql(itensponper.getInsereSql());
                }

            } catch (Exception e) {
            }
            return true;
        }

        return false;
    }

    @Override
    protected boolean alterarBD() {
        obterCampos();
        Conexao.executaSql(pontuacaoper.getAtualizaSql());


        for (int x = 0; x < jtTabela.getRowCount(); x++) {

            itensponper.setIteponcod(Integer.parseInt(jtTabela.getValueAt(x, 0).toString()));
            itensponper.setItenponnome((String) jtTabela.getValueAt(x, 1).toString().toUpperCase());
            Conexao.executaSql(itensponper.getAtualizaSql());
        }




//        for (int x = 0; x < jtTabela.getRowCount(); x++) {
//            itensponper.setItenponnome((String) jtTabela.getValueAt(x, 0).toString().toUpperCase());
//            itensponper.setIteponcod(Integer.parseInt(CodItens.get(x)));
//
//        }
        return true;

    }
  @Override
    protected void ajuda() {
        PlayMovie videos = new PlayMovie("pontuacao.wmv");


    }
    @Override
    public void consultarDados(int codigo) {
        try {
            pontuacaoper.setPoncod(codigo);
            //   JOptionPane.showMessageDialog(null, "chegou aqui consultadados");
            ResultSet rs = Conexao.executaQuery(pontuacaoper.getConsultaTodos());
            rs.next();
            pontuacaoper.setPoncod(rs.getInt(1));
            itensponper.setPoncod(rs.getInt(1));
            pontuacaoper.setPonnome(rs.getString(2));




        } catch (SQLException e) {
            e.printStackTrace();
        }
        preencheCampos();
        montaTabela(itensponper.getConsultaSqlString());
        jtTabela.setEnabled(false);
        habilitaCampos(false);
        habilitaBotoesConsulta(true);

    }

    @Override
    protected boolean excluirBD() {
        obterCampos();
        if ((Conexao.executaSqlExcluir(pontuacaoper.getExcluiSql()) == Conexao.DEPENDENCIA) || (Conexao.executaSqlExcluir(pontuacaoper.getExcluiSql()) == Conexao.OUTROERRO)) {
            habilitaCampos(false);
            habilitaBotoesConsulta(true);
            return false;
        } else {
            return true;
        }
    }

    public void limparPesquisa() {
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }


    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private componentes.AcaBotoes acaBotoesAddIt;
    private componentes.AcaBotoes acaBotoesDelIt;
    private componentes.AcaBotoes acaBotoesDelItens;
    private componentes.AcaLabel acaLabel1;
    private componentes.AcaLabel acaLabel2;
    private componentes.AcaLabel acaLabelNomeItens;
    private componentes.AcaTextfield acaTextfieldCodigo;
    private componentes.AcaTextfield acaTextfieldItensN;
    private componentes.AcaTextfield acaTextfieldNome;
    private javax.swing.JPanel jPanelItensPon;
    private javax.swing.JPanel jPanelPon;
    private javax.swing.JScrollPane jScrollPaneTabela;
    // End of variables declaration//GEN-END:variables

    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            String peganome = (String) dtm.getValueAt(jtTabela.getSelectedRow(), 1);
            if (tipoOperacao == ALTERANDO || tipoOperacao == PESQUISANDO) {
                pegacodigo = (String) dtm.getValueAt(jtTabela.getSelectedRow(), 0);

            }
            acaTextfieldItensN.setText(peganome);

            dtm.removeRow(jtTabela.getSelectedRow());
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

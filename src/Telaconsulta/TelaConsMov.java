/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TelaConsMov.java
 *
 * Created on 02/11/2010, 10:24:23
 */
package Telaconsulta;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.renderAlinha;
import Banco.Conexao;
import telaMovimenacao.TelaCotacao;
import telaMovimenacao.TelaMovimenta;

/**
/**
 *
 * @author chs
 */
public class TelaConsMov extends javax.swing.JInternalFrame implements MouseListener {

    private String tabela;
    private int[] tipo;
    private String[] nome;
    private String codigo;
    private String campoNome;
    private String campoAle;
    private String campo1;
    private String campo2;
    private boolean itens;
    private TelaMovimenta telacadastro;
    private TelaCotacao telaCota;
    //  private String[][] filtros = null;
    public Vector filtros = new Vector();
    public String todos = "TODOS";
    public String buscaTodos = "*";
    int opcao;
    public boolean todosB;
    public Vector colu = new Vector();
    private JTable jtTabela = new JTable() {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private DefaultTableModel dtm = (DefaultTableModel) jtTabela.getModel();
    private renderAlinha renderer = new renderAlinha();

    public boolean isTodosB() {
        return todosB;
    }

    public void setTodosB(boolean todosB) {
        this.todosB = todosB;
    }

    public void setColu(Vector colu) {
        this.colu = colu;
    }

    /** Creates new form TelaConsulta */
    public TelaConsMov(String titulo, TelaMovimenta telaCadastro, int[] tipo, String[] nome, String tabela, String Campocod, String campoNome, String campoAle) {
        setTitle(titulo);
        this.telacadastro = telaCadastro;
        this.tabela = tabela;
        this.tipo = tipo;
        this.nome = nome;
        this.codigo = Campocod;
        this.campoNome = campoNome;
        this.campoAle = campoAle;
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(dtm);
        jtTabela.setRowSorter(sorter);
        jtTabela.addMouseListener(this);
        initComponents();

        montaFiltros();

        posicaoTela();
    }

    public TelaConsMov(String titulo, TelaMovimenta telaCadastro, int[] tipo, String[] nome, String tabela, String Campocod, String campoNome, String campo1, String campo2) {
        setTitle(titulo);
        this.telacadastro = telaCadastro;
        this.tabela = tabela;
        this.tipo = tipo;
        this.nome = nome;
        this.codigo = Campocod;
        this.campoNome = campoNome;
        this.campo1 = campo1;
        this.campo2 = campo2;
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(dtm);
        jtTabela.setRowSorter(sorter);
        jtTabela.addMouseListener(this);
        initComponents();

        montaFiltros();

        posicaoTela();
    }

    public TelaConsMov(String titulo, TelaMovimenta telaCadastro, int[] tipo, String[] nome, String tabela, String Campocod, String campoNome, String campo1, boolean itens) {
        setTitle(titulo);
        this.telacadastro = telaCadastro;
        this.tabela = tabela;
        this.tipo = tipo;
        this.nome = nome;
        this.codigo = Campocod;
        this.campoNome = campoNome;
        this.campo1 = campo1;
        this.itens = itens;

        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(dtm);
        jtTabela.setRowSorter(sorter);
        jtTabela.addMouseListener(this);
        initComponents();

        montaFiltros();

        posicaoTela();
    }

    public TelaConsMov(String titulo, TelaCotacao telaCota, int[] tipo, String[] nome, String tabela, String Campocod, String campoNome, String campo1, boolean itens) {
        setTitle(titulo);
        this.telaCota = telaCota;
        this.tabela = tabela;
        this.tipo = tipo;
        this.nome = nome;
        this.codigo = Campocod;
        this.campoNome = campoNome;
        this.campo1 = campo1;
        this.itens = itens;

        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(dtm);
        jtTabela.setRowSorter(sorter);
        jtTabela.addMouseListener(this);
        initComponents();

        montaFiltros();

        posicaoTela();
    }

    public TelaConsMov(String titulo, TelaCotacao telaCota, int[] tipo, String[] nome, String tabela, String Campocod, String campoNome, String campo1, String campo2) {
        setTitle(titulo);
        this.telaCota = telaCota;
        this.tabela = tabela;
        this.tipo = tipo;
        this.nome = nome;
        this.codigo = Campocod;
        this.campoNome = campoNome;
        this.campo1 = campo1;
        this.itens = itens;

        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(dtm);
        jtTabela.setRowSorter(sorter);
        jtTabela.addMouseListener(this);
        initComponents();

        montaFiltros();

        posicaoTela();
    }

    public void montaFiltros() {
        acaComboBusca.comboFiltro(tipo, nome);
        acaComboBusca.setSelectedIndex(-1);

    }

    public void buscar() {
        String sql;
        JOptionPane.showMessageDialog(null, opcao);
        if (dtm.getColumnCount() <= 0) {
            preencherColunas();
        }
        switch (opcao) {
            case 1:

                sql = "select " + codigo + ", " + campoNome + " from " + tabela + " where " + codigo + " = " + Integer.parseInt(acaTextfieldBusca.getText()) + " ";
                JOptionPane.showMessageDialog(null, sql);
                // preencherColunas();
                efetuarPesquisa(sql);
                break;
            case 2:
                sql = "select " + codigo + "," + campoNome + " from " + tabela + " where " + campoNome + "  like '%" + acaTextfieldBusca.getText().toUpperCase() + "%' ";
                JOptionPane.showMessageDialog(null, sql);
                //preencherColunas();
                efetuarPesquisa(sql);
                break;
            case 3:
                sql = "select " + codigo + "," + campoNome + " from " + tabela + " where " + campoAle + "  like '%" + acaTextfieldBusca.getText().toUpperCase() + "%' ";
                //preencherColunas();
                efetuarPesquisa(sql);
                break;
            case 4:
                sql = "select " + codigo + "," + campoNome + " from " + tabela + " where " + campo1 + " = '" + acaTextfieldBusca.getText() + "' or  " + campo2 + " = '" + acaTextfieldBusca.getText() + "' ";
            case 5:
                sql = "select " + codigo + "," + campoNome + " from " + tabela + " where " + campo1 + " = " + acaComboBusca.getValor() + " and  " + campo2 + " = '" + acaTextfieldBusca.getText().toUpperCase() + "' ";
                efetuarPesquisa(sql);
                break;
            default:
                sql = "select " + codigo + "," + campoNome + " from " + tabela + "";
                //preencherColunas();
                efetuarPesquisa(sql);
        }
    }

    public void preencherColunas() {
        if (dtm.getColumnCount() == 0) {
            for (int i = 0; i < colu.size(); i++) {
               // dtm.addColumn(colu.get(i));
                dtm.addColumn(colu.get(i));
            }
        }
    }

    public void limparPesquisa() {
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
        colu.removeAllElements();
        acaComboBusca.setSelectedIndex(-1);
        acaTextfieldBusca.setText("");

    }

    public void efetuarPesquisa(String textoSql) {

        try {
            while (dtm.getRowCount() > 0) {
                dtm.removeRow(0);
            }
            ResultSet rs = Conexao.executaQuery(textoSql);
            ResultSetMetaData rsmd = rs.getMetaData();
            Vector vetor;
            while (rs.next()) {
                vetor = new Vector();
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    vetor.add(rs.getString(i + 1));


                }
                dtm.addRow(vetor);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível efetuar a pesquisa");
        }

        TableColumnModel modeloColuna = jtTabela.getColumnModel();
        jtTabela.getTableHeader().setReorderingAllowed(false);
        jtTabela.getColumnModel().getColumn(0).setMaxWidth(70);
        jtTabela.getColumnModel().getColumn(0).setPreferredWidth(10);
        jtTabela.getColumnModel().getColumn(0).setResizable(false);
        jtTabela.getColumnModel().getColumn(1).setMaxWidth(180);
        jtTabela.getColumnModel().getColumn(1).setPreferredWidth(95);

        jtTabela.getColumnModel().getColumn(1).setResizable(false);
        /*DefaultTableCellRenderer renderer1 = new DefaultTableCellRenderer();
        renderer1.setHorizontalAlignment(SwingConstants.CENTER);*/
        /*jPanelResultados.add(jsp);
        jsp.setPreferredSize(new Dimension(500, 300));*/

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    // End of variables declaration
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            String pegacod = (String) dtm.getValueAt(jtTabela.getSelectedRow(), 0);
            if (itens == true) {
                telacadastro.dadosConsultaItens(Integer.parseInt(pegacod));
            } else {

                if (telaCota != null) {
                    Object[] item = {(String) dtm.getValueAt(jtTabela.getSelectedRow(), 0), (String) dtm.getValueAt(jtTabela.getSelectedRow(), 1)};
                    telaCota.addItemTabelaFor(item, Integer.parseInt(pegacod));

                } else {
                    telacadastro.dadosConsultaTipo(Integer.parseInt(pegacod));
                }
            }
            colu.removeAllElements();
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelCentral = new javax.swing.JPanel();
        jPanelFiltros = new javax.swing.JPanel();
        acaComboBusca = new componentes.AcaCombo();
        acaLabelTipo = new componentes.AcaLabel();
        acaTextfieldBusca = new componentes.AcaTextfield();
        jButtonProcurar = new javax.swing.JButton();
        jButtonLimpar = new javax.swing.JButton();
        jScrollPaneResult = new javax.swing.JScrollPane(jtTabela);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanelCentral.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanelFiltros.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtrar por:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N

        acaComboBusca.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                acaComboBuscaItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanelFiltrosLayout = new javax.swing.GroupLayout(jPanelFiltros);
        jPanelFiltros.setLayout(jPanelFiltrosLayout);
        jPanelFiltrosLayout.setHorizontalGroup(
            jPanelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFiltrosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(acaComboBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(172, Short.MAX_VALUE))
        );
        jPanelFiltrosLayout.setVerticalGroup(
            jPanelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFiltrosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(acaComboBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButtonProcurar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/search.png"))); // NOI18N
        jButtonProcurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProcurarActionPerformed(evt);
            }
        });

        jButtonLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/x.png"))); // NOI18N
        jButtonLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimparActionPerformed(evt);
            }
        });

        jScrollPaneResult.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resultados da Consulta", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N

        javax.swing.GroupLayout jPanelCentralLayout = new javax.swing.GroupLayout(jPanelCentral);
        jPanelCentral.setLayout(jPanelCentralLayout);
        jPanelCentralLayout.setHorizontalGroup(
            jPanelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCentralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneResult, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabelTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelCentralLayout.createSequentialGroup()
                        .addGroup(jPanelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanelFiltros, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(acaTextfieldBusca, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jButtonProcurar)
                        .addGap(32, 32, 32)
                        .addComponent(jButtonLimpar)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanelCentralLayout.setVerticalGroup(
            jPanelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCentralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(acaLabelTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(acaTextfieldBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonProcurar))
                    .addComponent(jButtonLimpar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jScrollPaneResult, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 488, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanelCentral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 352, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelCentral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void acaComboBuscaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_acaComboBuscaItemStateChanged
        if (acaComboBusca.getSelectedIndex() >= 0) {
            if (acaComboBusca.isPopupVisible()) {
                opcao = acaComboBusca.getValor();
                acaLabelTipo.setText((String) acaComboBusca.getSelectedItem());
            }
        }
}//GEN-LAST:event_acaComboBuscaItemStateChanged

    private void jButtonProcurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProcurarActionPerformed
        buscar();
}//GEN-LAST:event_jButtonProcurarActionPerformed

    private void jButtonLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimparActionPerformed
        limparPesquisa();
        acaComboBusca.setSelectedIndex(-1);
        acaTextfieldBusca.setText("");
        acaLabelTipo.setText("");
    }//GEN-LAST:event_jButtonLimparActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private componentes.AcaCombo acaComboBusca;
    private componentes.AcaLabel acaLabelTipo;
    private componentes.AcaTextfield acaTextfieldBusca;
    private javax.swing.JButton jButtonLimpar;
    private javax.swing.JButton jButtonProcurar;
    private javax.swing.JPanel jPanelCentral;
    private javax.swing.JPanel jPanelFiltros;
    private javax.swing.JScrollPane jScrollPaneResult;
    // End of variables declaration//GEN-END:variables
}

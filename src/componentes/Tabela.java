/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;

import java.awt.Component;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import modelos.ModeloTabela;
import Banco.Conexao;

/**
 *
 * @author cristiano
 */
public class Tabela extends JTable {

    public ModeloTabela dtm;
    public Vector dados;

    public Tabela() {
    }

    public void tabelaSeleciona(Vector colunas, int[] tamanhos, String sql) {

        dtm.setColunas(colunas);
        dtm = new ModeloTabela(sql);


        for (int z = 0; z < this.getColumnCount(); z++) {
            this.getColumnModel().getColumn(z).setResizable(false);
            this.getColumnModel().getColumn(z).setMaxWidth(tamanhos[z]);
            this.getColumnModel().getColumn(z).setPreferredWidth(tamanhos[z]);
        }
        this.setDefaultRenderer(Boolean.class, new PintaCelula());
        this.setModel(dtm);


    }

    public Object[][] montaTabela(String textoSql) {
        Object[][] obj = null;
        try {


            ResultSet rs1 = Conexao.executaQuery(textoSql);
            ResultSetMetaData rsmd1 = rs1.getMetaData();

            while (rs1.next()) {
                dados = new Vector();
                for (int i = 0; i < rsmd1.getColumnCount(); i++) {
                    dados.add(rs1.getString(i + 1));

                }
                System.out.println(dados);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível efetuar a pesquisa");
            e.printStackTrace();
        }

        obj = new Object[dados.size()][];
      

        return obj;
    }

    class Modelo extends AbstractTableModel {

        private String[] colunas = {"Nome", "Devedor"};
        private Object[][] conteudo;
//  private Object[][] conteudo = {
//     {"Osmar J. Silva", new Boolean(true)},
//     {"Fernando Santos", new Boolean(false)}
//  };
//        private Object[][] conteudo = montaTabela1("select tipomatfor from tipomateria");

        // criar e preencher o vetor...
        public int getColumnCount() {
            return colunas.length;
        }

        public int getRowCount() {
            return conteudo.length;
        }

        public String getColumnName(int col) {
            return colunas[col];
        }

        public Object getValueAt(int row, int col) {
            return conteudo[row][col];
        }

        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        public boolean isCellEditable(int row, int col) {
            return true;
        }

        public void setValueAt(Object value, int row, int col) {
            conteudo[row][col] = value;
            fireTableCellUpdated(row, col);
        }
    }

    class PintaCelula implements TableCellRenderer {

        private JCheckBox jcbox = new JCheckBox();

        public Component getTableCellRendererComponent(JTable tabela, Object valor, boolean selecionado, boolean temFoco, int linha, int coluna) {
            jcbox.setSelected(selecionado);
            //p.setBackground((Color) valor);
            return jcbox;
        }
    }
}

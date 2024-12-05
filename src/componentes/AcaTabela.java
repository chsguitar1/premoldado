/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import modelos.ModeloTabela;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.events.MouseEvent;
import org.w3c.dom.views.AbstractView;
import Banco.Conexao;

/**
 *
 * @author chs
 */
public class AcaTabela extends JTable implements MouseListener {

    public DefaultTableModel dtm;
    public Vector colu = new Vector();
    public String[] colunas;
    public Object[][] celulas;

    public Object[][] getCelulas() {
        return celulas;
    }

    public void setCelulas(Object[][] celulas) {
        this.celulas = celulas;
    }

    public Vector getColu() {
        return colu;
    }

    public void setColu(Vector colu) {
        this.colu = colu;
    }

    public AcaTabela() {
    }

    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public void montaTabela(String[] colunas, int[] tamanhos) {

        dtm = (DefaultTableModel) this.getModel();
        this.setModel(dtm);
        TableColumnModel modeloColuna = this.getColumnModel();
        for (int i = 0; i < colunas.length; i++) {
            dtm.addColumn(colunas[i]);

            this.getTableHeader().setReorderingAllowed(false);


        }
        for (int z = 0; z < this.getColumnCount(); z++) {
            this.getColumnModel().getColumn(z).setResizable(false);
            this.getColumnModel().getColumn(z).setMaxWidth(tamanhos[z]);
            this.getColumnModel().getColumn(z).setPreferredWidth(tamanhos[z]);
        }
        DefaultTableCellRenderer renderer1 = new DefaultTableCellRenderer();
        renderer1.setHorizontalAlignment(SwingConstants.CENTER);

    }

    public void montaTabelaCheckBox(String Sql,Vector colunas) {
      Modelo dtm = new Modelo();
      this.setModel(dtm);
      
       

    }

    public void montaTabela(String textoSql) {
     //   System.out.println(textoSql);
        try {
            while (dtm.getRowCount() > 0) {
                this.dtm.removeRow(0);
            }
            ResultSet rs1 = Conexao.executaQuery(textoSql);
            ResultSetMetaData rsmd1 = rs1.getMetaData();
            Vector vetor;
            while (rs1.next()) {
                vetor = new Vector();
                for (int i = 0; i < rsmd1.getColumnCount(); i++) {
                    vetor.add(rs1.getString(i + 1));

                }

                this.dtm.addRow(vetor);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível efetuar a pesquisa");
            e.printStackTrace();
        }

    }

    public Object[][] montaTabela1(String textoSql) {
        Object[][] dados = null;
        try {

            ResultSet rs1 = Conexao.executaQuery(textoSql);
            ResultSetMetaData rsmd1 = rs1.getMetaData();
            while (rs1.next()) {
                dados = new Object[this.getRowCount()][2];
                for (int i = 0; i < rsmd1.getColumnCount(); i++) {
                    dados[i][0] = new Boolean(false);
                    dados[i][1] = rs1.getString(i + 1);

                }


            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível efetuar a pesquisa");
            e.printStackTrace();
        }

        return dados;
    }

    public boolean validaItens(int codigo) {
        //JOptionPane.showMessageDialog(null, codigo);
        for (int x = 0; x < dtm.getRowCount(); x++) {
            if (Integer.parseInt((String) dtm.getValueAt(x, 0)) == codigo) {
                return true;
            }

        }
        return false;

    }

    public void PrencheTabela(Object[] item) {
        dtm.addRow(item);

        this.setModel(dtm);
    }

    public void limparPesquisa() {
        while (dtm.getRowCount() > 0) {
            this.dtm.removeRow(0);
        }
    }

    class Modelo extends AbstractTableModel {

        private String[] colunas = {"Nome", "Devedor"};
//  private Object[][] conteudo = {
//     {"Osmar J. Silva", new Boolean(true)},
//     {"Fernando Santos", new Boolean(false)}
//  };
        private Object[][] conteudo = montaTabela1("select fonome from fornecedor");

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

        @Override
        public void setValueAt(Object value, int row, int col) {
            conteudo[row][col] = value;
            fireTableCellUpdated(row, col);
        }
    }

    public void mouseClicked(java.awt.event.MouseEvent e) {
    }

    public void mousePressed(java.awt.event.MouseEvent e) {
    }

    public void mouseReleased(java.awt.event.MouseEvent e) {
    }

    public void mouseEntered(java.awt.event.MouseEvent e) {
    }

    public void mouseExited(java.awt.event.MouseEvent e) {
    }
}

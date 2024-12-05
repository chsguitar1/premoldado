/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import Banco.Conexao;

/**
 *
 * @author chs
 */
public class ConsultaBD implements MouseListener {

    public Vector colu = new Vector();
    private JTable jtTabela = new JTable() {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private DefaultTableModel dtm = (DefaultTableModel) jtTabela.getModel();
    private renderAlinha renderer = new renderAlinha();

    public ConsultaBD() {
        colu.add("CODIGO");
        colu.add("NOME");
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(dtm);
        jtTabela.setRowSorter(sorter);
        jtTabela.addMouseListener(this);
    }

    public void preencherColunas() {
        if (dtm.getColumnCount() == 0) {
            for (int i = 0; i < colu.size(); i++) {
                dtm.addColumn(colu.get(i).toString());
            }
        }
    }

    public JTable efetuarPesquisa(String textoSql) {
        JOptionPane.showMessageDialog(null, textoSql);
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
        jtTabela.getColumnModel().getColumn(0).setMaxWidth(60);
        //jtTabela.getColumnModel().getColumn(0).setPreferredWidth(10);
        jtTabela.getColumnModel().getColumn(0).setResizable(false);
        jtTabela.getColumnModel().getColumn(1).setPreferredWidth(0);
        jtTabela.getColumnModel().getColumn(1).setResizable(false);
        DefaultTableCellRenderer renderer1 = new DefaultTableCellRenderer();
        renderer1.setHorizontalAlignment(SwingConstants.CENTER);
        /*jPanelResultados.add(jsp);
        jsp.setPreferredSize(new Dimension(500, 300));*/
        return jtTabela;
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}

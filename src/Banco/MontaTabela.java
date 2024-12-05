/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Banco;

import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import modelos.ModeloTabela;

/**
 *
 * @author chs
 */
public class MontaTabela {

    Conexao conecta = new Conexao();
    public static JTable jtTabela = new JTable();
    public static JScrollPane jsscrol = new JScrollPane(jtTabela);
    public static String query;
    public static ResultSet rs;

    public static String getQuery() {
        return query;
    }

    public static void setQuery(String query) {
        MontaTabela.query = query;

    }

    public static void MontaTabela1() {

        rs = consultar(query);
        TableModel modelo = new ModeloTabela(query);
        jtTabela.setModel(modelo);
        TableColumnModel modeloColuna = jtTabela.getColumnModel();

        jtTabela.getTableHeader().setReorderingAllowed(false);

        jtTabela.getColumnModel().getColumn(0).setPreferredWidth(50);
        jtTabela.getColumnModel().getColumn(0).setResizable(false);
        jtTabela.getColumnModel().getColumn(1).setPreferredWidth(100);
        jtTabela.getColumnModel().getColumn(1).setResizable(false);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        //Definindo o alinhamento das células do modelo como Centralizado
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        //setando a interface modificada nas células da coluna 0
        jtTabela.getColumnModel().getColumn(0).setCellRenderer(renderer);
    }

    public static ResultSet consultar(String sql) {
        ResultSet resultado = null;
        Conexao.getConexao();
        try {

            Statement consulta = Conexao.conexao.createStatement();
            resultado = consulta.executeQuery(sql);
          
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }
}



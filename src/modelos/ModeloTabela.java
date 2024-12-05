/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.awt.Component;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import Banco.Conexao;

/**
 *
 * @author chs
 */
public class ModeloTabela extends AbstractTableModel {

    public static ResultSet rs;
    public static ResultSetMetaData rsmd;
    public static ArrayList cache = null;

    public static Vector getColunas() {
        return colunas;
    }

    public static void setColunas(Vector colunas) {
        ModeloTabela.colunas = colunas;
    }
    public static Vector colunas;
 
    public Vector vetor;

    

    public static int c = -1;

    public ModeloTabela(String sql) {
        
        rs = Conexao.executaQuery(sql);
        try {
            rsmd = rs.getMetaData();
            cache = new ArrayList();
            //os dados da consulta  sÃ£o armazenados no cache.

            while (rs.next()) {
                Object[] row = new Object[rsmd.getColumnCount()];
                for (int j = 0; j < row.length; j++) {
                   
                    row[j] = rs.getObject(j + 1);
                 


                }
                cache.add(row);

            }

        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }//fim bloco construtor
//   public  ModeloTabela(String textoSql) {
//        try {
//
//            ResultSet rs1 = Conexao.executaQuery(textoSql);
//            ResultSetMetaData rsmd1 = rs1.getMetaData();
//
//            while (rs1.next()) {
//                vetor = new Vector();
//                for (int i = 0; i < rsmd1.getColumnCount(); i++) {
//                    vetor.add(rs1.getString(i + 1));
//
//                }
//
//System.out.println(colunas);
//System.out.println(vetor);
//
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Não foi possível efetuar a pesquisa");
//            e.printStackTrace();
//        }
//
//    }

    public int getRowCount() {
        return cache.size();
    }

    public int getColumnCount() {
        try {
          return rsmd.getColumnCount()+1;

        } catch (Exception e) {
            System.out.println("Error " + e);

            return 0;
        }

    }

//    @Override
//    public String getColumnName(int c) {
//    try {
//    return rsmd.getColumnName(c + 1);
//    } catch (SQLException e) {
//    System.out.println("Error " + e);
//
//    return "";
//    }
//    }
    @Override
    public String getColumnName(int c) {
        try {
            System.out.println(colunas.get(c));
            return (String) colunas.get(c);


            //return rsmd.getColumnName(c + 1);
        } catch (ExceptionInInitializerError e) {
            System.out.println("Error " + e);
            return "";
        }
    }

    @Override
    public Class getColumnClass(int c) {

         Class klass = String.class; // para todas as outras colunas use String
         // primeira coluna deve ser marcável
         if (c == 3) klass = Boolean.class;
         return klass;


        // return getValueAt(0, c).getClass();
    }

    public Object getValueAt(int r, int d) {
      
        if (r < cache.size()) {
         return ((Object[]) cache.get(r))[d];
         //   return ((Object[]) vetor.get(r))[d];
        } else {
            return null;
        }
    }
     
}

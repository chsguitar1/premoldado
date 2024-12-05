/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author chs
 */
public class ModeloTabelaMov extends AbstractTableModel {

    public static Vector rs;
    public static ArrayList cache;
    public static String[] colunas;
    public static int c = -1;

    public ModeloTabelaMov() {
    }

    public ModeloTabelaMov(Vector vetor) {
        rs = vetor;
        for (int cont = 0; cont < rs.size(); cont++) {
            Object[] linha = new Object[rs.size()];
            for (int j = 0; j < linha.length; j++) {
                linha[j] = rs.get(j + 1);
            }
            cache.add(linha);
        }
    }

    @Override
    public String getColumnName(int c) {
        try {
            System.out.println(colunas[c]);

            return colunas[c].toString();


            //return rsmd.getColumnName(c + 1);
        } catch (ExceptionInInitializerError e) {
            System.out.println("Error " + e);
            return "";
        }
    }

    public Object getValueAt(int r, int d) {
        if (r < cache.size()) {
            return ((Object[]) cache.get(r))[d];
        } else {
            return null;
        }
    }

    public int getRowCount() {
        return cache.size();
    }

    public int getColumnCount() {
        if (c == -1) {
            c = rs.size();

            return c;
        } else {
            return 0;
        }
    }
     public static String[] getColunas() {
        return colunas;
    }

    public static void setColunas(String[] colunas) {
        ModeloTabelaMov.colunas = colunas;
    }
}




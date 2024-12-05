/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Banco;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author cristiano
 */
public class ValidaDados {

    public static boolean nulos;
    public static boolean vazio;
    public static boolean invalido;
    public static String[] invalidos = {"/", "\\", ":", "*", "\"", "<", ">", "//", ";"};
    public static boolean existe;
    public static boolean existeBd;
    public static String query;

    public static void setQuery(String query) {
        ValidaDados.query = query;
    }

    public static void verificaNulllo(String texto, String campo) {
        if (texto.equals("")) {
            JOptionPane.showMessageDialog(null, "Campo " + campo + " requerido");
            vazio = true;
        }
    }

    public static void verificaInvalido(String texto, String campo) {
        for (int cont = 0; cont < invalidos.length; cont++) {
            if (texto.trim().equals(invalidos[cont])) {
                invalido = true;
                JOptionPane.showMessageDialog(null, "Campo " + campo + " invalido");
                System.out.println(vazio);
            }
        }

    }

    public static void verificaCampos(String texto, String campo) {
        if (texto.equals("")) {
            JOptionPane.showMessageDialog(null, "Campo " + campo + " requerido");
            vazio = true;
            System.out.println(vazio);
        }

        for (int cont = 0; cont < invalidos.length; cont++) {
            if (texto.trim().equals(invalidos[cont])) {
                invalido = true;
                JOptionPane.showMessageDialog(null, "Campo " + campo + " invalido");
                System.out.println(invalido);
            }
        }

    }


    /*public static void verificaBanco(Vector Nomes, Vector campos, String query) {
    Conexao.getConexao();
    Statement stmt;
    ResultSet resultado = null;
    try {
    stmt = Conexao.conexao.createStatement();
    resultado = stmt.executeQuery(query);
    } catch (SQLException e) {
    e.printStackTrace();
    }
    try {
    while (resultado.next()) {
    for (int cont = 0; cont < Nomes.size(); cont++) {
    if (Nomes.get(cont).equals(resultado.getString(1))) {
    existeBd = true;
    JOptionPane.showMessageDialog(null, "Campo " + campos + " invalido");

    }

    }
    }


    } catch (SQLException e) {
    }
    }*/
    public static void verificaBanco(String texto, String campo) {
        Conexao.getConexao();
        Statement stmt;
        ResultSet resultado = null;
        ResultSetMetaData rs = null;
        try {
            System.out.println(query);
            stmt = Conexao.conexao.createStatement();
            resultado = stmt.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (resultado.next()) {
                System.out.println(resultado.getString(1));
                if (texto.toUpperCase().trim().equals(resultado.getString(1))) {

                    existe = true;
                    JOptionPane.showMessageDialog(null, "Ja existe o " + campo + " cadastrado");

                }
            }
        } catch (SQLException e) {
        }

        if (texto.equals("")) {
            JOptionPane.showMessageDialog(null, "Campo " + campo + " requerido");
            vazio = true;
            System.out.println(vazio);
        }
        for (int cont = 0; cont < invalidos.length; cont++) {
            if (texto.trim().equals(invalidos[cont])) {
                invalido = true;
                JOptionPane.showMessageDialog(null, "Campo " + campo + " invalido");
                System.out.println(invalido);
            }
        }

    }

    public static boolean isNumeric(String str, Class<? extends Number> clazz) {
        try {
            if (clazz.equals(Byte.class)) {
                Byte.parseByte(str);
            } else if (clazz.equals(Double.class)) {
                Double.parseDouble(str);
            } else if (clazz.equals(Float.class)) {
                Float.parseFloat(str);
            } else if (clazz.equals(Integer.class)) {
                Integer.parseInt(str);
            } else if (clazz.equals(Long.class)) {
                Long.parseLong(str);
            } else if (clazz.equals(Short.class)) {
                Short.parseShort(str);
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "nao 'e numero");
            return false;
        }
        JOptionPane.showMessageDialog(null, "nao 'e numero");
        return true;

    }
}

/*# (Character.toString(str.charAt( i )).equals("/")) ||
#                    (Character.toString(str.charAt( i )).equals("\\")) ||
#                    (Character.toString(str.charAt( i )).equals(":")) ||
#                    (Character.toString(str.charAt( i )).equals("*")) ||
#                    (Character.toString(str.charAt( i )).equals("\"")) ||
#                    (Character.toString(str.charAt( i )).equals("<")) ||
#                    (Character.toString(str.charAt( i )).equals(">")))*/




/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author chs
 */
public class Conexao {

    public static Connection conexao;
    public static Statement stmt;
    public static final int LIVRE = 0;
    public static final int DEPENDENCIA = 1;
    public static final int OUTROERRO = 2;
    private static Connection mysqlConnection = null;
    private static   Statement mysqlStatement = null;

    public static Connection getConexao() {
        if (conexao != null) {
            return conexao;
        } else {
            try {
                Class.forName("org.firebirdsql.jdbc.FBDriver");
                //WINDOS

                conexao = DriverManager.getConnection("jdbc:firebirdsql://localhost:3050/F:\\ACADB.GDB", "SYSDBA", "masterkey");
                //SERVIDOR LINUX NOTEBOOK
                // conexao = DriverManager.getConnection("jdbc:firebirdsql://192.168.2.2:3050//home/cristiano/bases/ACADB.GDB", "SYSDBA", "masterkey");
            } catch (SQLException e) {
                e.printStackTrace();
                //Tratamento de erro
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            return conexao;
        }

    }

//    public static Statement getStatement(){
//        try {
//            mysqlConnection = DriverManager.getConnection(mysqlUrl, mysqlUser, mysqlPassword);
//            mysqlStatement = mysqlConnection.createStatement();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return  mysqlStatement;
//
//    }

    public static void executaSql(String sql) {
//        JOptionPane.showMessageDialog(null, sql);
        System.out.println(sql);
        getConexao();
        try {
            stmt = conexao.createStatement();
            stmt.execute(sql);
            stmt.close();


        } catch (SQLException ex) {
            ex.getErrorCode();
        }

    }

    public static int executaSqlExcluir(String sql) {
//        JOptionPane.showMessageDialog(null, sql);
        System.out.println(sql);
        try {
            stmt = getConexao().createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            if ((e.getErrorCode() == 335544466)) {
                //   JOptionPane.showMessageDialog(null, "Nao foi possivel excluir \n  extiste uma movimentaçao para o registro selecionado");
                return DEPENDENCIA;
            } else {

                JOptionPane.showMessageDialog(null, "Não foi possível efetuar atualização no banco de dados.");
                return OUTROERRO;
            }
        }
        return LIVRE;
    }

    public static void fechaConexao(int fecha) {
        if (fecha == 1) {
            try {
                conexao.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "impossiel ferchar ");
            }
        }


    }

    public static ResultSet executaQuery(String sql) {
//        JOptionPane.showMessageDialog(null, sql);
        System.out.println(sql);
        ResultSet rs = null;
        try {
            stmt = getConexao().createStatement();
            rs = stmt.executeQuery(sql);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível efetuar consulta no banco de dados" + "\n" + sql);
        }
        return rs;
    }
}

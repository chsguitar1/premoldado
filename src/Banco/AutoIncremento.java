/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Banco;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author chs
 */
public class AutoIncremento {

    public static int valor;
    public static int incremento = 1;

    public static void autoValor(String autovalor) {
        String sql = "select gen_id(" + autovalor + "," + incremento + ")from rdb$database";
        System.out.println(sql);
        Conexao.getConexao();
        try {
            Statement stmt = Conexao.conexao.createStatement();
            ResultSet resultado = stmt.executeQuery(sql);

            try {
                while(resultado.next()){
                 valor = resultado.getInt(1);

                }
            } catch (Exception e) {
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(valor) ;

    }
}

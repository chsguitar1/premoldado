/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JList;
import Banco.Conexao;

/**
 *
 * @author cristiano
 */
public class AcaListaMult extends JFrame {

    private JList direita;
    private JList esquerda;
    private AcaBotoes copiar;
    public  Vector listadados = new Vector();
    String[] textos;


    public AcaListaMult(String sql) {
    ResultSet rs = Conexao.executaQuery(sql);
        try {
            while (rs.next()) {
               listadados.addElement(rs.getString(1));
               textos= (String[]) listadados.get(1);
            }
//            direita.setListData(listadados);
            System.out.println(textos);
        } catch (SQLException ex) {
            Logger.getLogger(AcaListaMult.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

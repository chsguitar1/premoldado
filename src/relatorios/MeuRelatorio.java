/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorios;

/**
 *
 * @author cristiano
 */
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.*;
import Banco.Conexao;
import javax.swing.JOptionPane;

/**
 * Ponto de entrada do projeto.
 *
 * @author David Buzatto
 */
public class MeuRelatorio {

    Connection conecta = Conexao.getConexao();
    JRResultSetDataSource jrRS;

    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        new MeuRelatorio().abrirRelatorioClientes("RelCidades.jasper","Cidades");
//    }
    public MeuRelatorio(String arquivo, String titulo, Map parametros) {
        relatorioConexao(arquivo, titulo, parametros);


    }

    public MeuRelatorio(String arquivo, String titulo, Map parametros, String query) {
        relatorioDatasource(arquivo, titulo, parametros, query);


    }

    public void relatorioConexao(String relatorio, String titulo, Map parametros) {

       
        InputStream inputStream = getClass().getResourceAsStream(relatorio);

        // mapa de parâmetros do relatório (ainda vamos aprender a usar)
        // Map parametros = new HashMap();
        try {
            ReportUtilis.openReport(titulo, inputStream, parametros, conecta);
        } catch (JRException ex) {
            Logger.getLogger(MeuRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public void relatorioDatasource(String relatorio, String titulo, Map parametros, String query) {

               InputStream inputStream = getClass().getResourceAsStream(relatorio);
               String ende = getClass().getResourceAsStream(relatorio).toString();
               JOptionPane.showMessageDialog(null, ende);
        ResultSet rs = Conexao.executaQuery(query);
        jrRS = new JRResultSetDataSource(rs);
        // mapa de parâmetros do relatório (ainda vamos aprender a usar)
        // Map parametros = new HashMap();
        try {
            ReportUtilis.openReport(titulo, inputStream, parametros, jrRS);
        } catch (JRException ex) {
            Logger.getLogger(MeuRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
}

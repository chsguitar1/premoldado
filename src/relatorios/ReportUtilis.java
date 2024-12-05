/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorios;

/**
 *
 * @author cristiano
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.swing.JRViewer;
import Banco.Conexao;
import premoldados.TelaSistema;

/**
 * Classe com métodos utilitários para executar e abrir relatórios.
 *
 * @author David Buzatto
 */
public class ReportUtilis {

    TelaSistema telasistema;

    /**
     * Abre um relatório usando uma conexão como datasource.
     *
     * @param titulo Título usado na janela do relatório.
     * @param inputStream InputStream que contém o relatório.
     * @param parametros Parâmetros utilizados pelo relatório.
     * @param conexao Conexão utilizada para a execução da query.
     * @throws JRException Caso ocorra algum problema na execução do relatório
     */
    public static void openReport(
            String titulo,
            InputStream inputStream,
            Map parametros,
            Connection conexao) throws JRException {

        /*
         * Cria um JasperPrint, que é a versão preenchida do relatório,
         * usando uma conexão.
         */

        JasperPrint print = JasperFillManager.fillReport(inputStream, parametros, conexao);

        // abre o JasperPrint em um JFrame
        viewReportFrame(titulo, print);

    }

    /**
     * Abre um relatório usando um datasource genérico.
     *
     * @param titulo Título usado na janela do relatório.
     * @param inputStream InputStream que contém o relatório.
     * @param parametros Parâmetros utilizados pelo relatório.
     * @param dataSource Datasource a ser utilizado pelo relatório.
     * @throws JRException Caso ocorra algum problema na execução do relatório
     */
    public static void openReport(
            String titulo,
            InputStream inputStream,
            Map parametros,
            JRDataSource dataSource) throws JRException {

        /*
         * Cria um JasperPrint, que é a versão preenchida do relatório,
         * usando um datasource genérico.
         */
        JasperPrint print = JasperFillManager.fillReport(
                inputStream, parametros, dataSource);

        // abre o JasperPrint em um JFrame
        viewReportFrame(titulo, print);

    }

    /**
     * Cria um JFrame para exibir o relatório representado pelo JasperPrint.
     *
     * @param titulo Título do JFrame.
     * @param print JasperPrint do relatório.
     */
//    private static void viewReportFrame( String titulo, JasperPrint print ) {
//
//        /*
//         * Cria um JRViewer para exibir o relatório.
//         * Um JRViewer é uma JPanel.
//         */
//        JRViewer viewer = new JRViewer( print );
//
//        // cria o JFrame
//        JFrame frameRelatorio = new JFrame( titulo );
//
//        // adiciona o JRViewer no JFrame
//        frameRelatorio.add( viewer, BorderLayout.CENTER );
//
//        // configura o tamanho padrão do JFrame
//        frameRelatorio.setSize( 800, 500 );
//
//        // maximiza o JFrame para ocupar a tela toda.
//       // frameRelatorio.setExtendedState( JFrame.MAXIMIZED_BOTH );
//
//        // configura a operação padrão quando o JFrame for fechado.
//        frameRelatorio.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
//
//        // exibe o JFrame
//        frameRelatorio.setVisible( true );
//
//    }
    private static void viewReportFrame(String titulo, JasperPrint print) {

        /*
         * Cria um JRViewer para exibir o relatório.
         * Um JRViewer é uma JPanel.
         */
        JRViewer viewer = new JRViewer(print);

        // cria o JFrame
        JInternalFrame frameRelatorio = new JInternalFrame(titulo);

        // adiciona o JRViewer no JFrame
        frameRelatorio.add(viewer, BorderLayout.CENTER);

        // configura o tamanho padrão do JFrame
        frameRelatorio.setSize(900, 700);

        // maximiza o JFrame para ocupar a tela toda.
        // frameRelatorio.s( JFrame.MAXIMIZED_BOTH );

        // configura a operação padrão quando o JFrame for fechado.
        frameRelatorio.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // exibe o JFrame
        frameRelatorio.setVisible(true);
        frameRelatorio.setClosable(true);
        frameRelatorio.setIconifiable(true);
        frameRelatorio.setResizable(true);
        frameRelatorio.setMaximizable(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frameRelatorio.getSize();
        frameRelatorio.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 200);
        TelaSistema.jdp.add(frameRelatorio);
        TelaSistema.jdp.moveToFront(frameRelatorio);

    }
    
}

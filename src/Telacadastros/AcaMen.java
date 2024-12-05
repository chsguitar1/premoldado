/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Telacadastros;

import javax.swing.JOptionPane;
import Banco.Conexao;
import Telaconsulta.TelaConsCtaRec;

/**
 *
 * @author cristiano
 */
public class AcaMen extends JOptionPane {

    private TelaCadastro tela;
    int codigo;
    private final String campostatus;

    public AcaMen(TelaCadastro tela, int codigo, String tabela, String campocod, String campostatus) {

        this.tela = tela;
        this.codigo = codigo;
        this.campostatus = campostatus;
        Object[] botoes = {"Sim", "Não"};
        int opcao = JOptionPane.showOptionDialog(null, " Nao foi possivel excluir \n  extiste uma movimentaçao para o registro selecionado \n Deseja Inativa o registro ",
                "Sair", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes, botoes[0]);
        if (opcao == JOptionPane.YES_OPTION) {
            Conexao.executaSql("EXECUTE PROCEDURE INATIVASTATUS("+codigo+",'" + tabela + "','" + campocod + "','" + campostatus + "')");
            tela.limparCampos();
            tela.habilitarBotoes(true);
            tela.habilitaCampos(false);
            requestFocus();
        }
        if (opcao == JOptionPane.NO_OPTION) {
            tela.habilitaCampos(true);

        }


    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/**
 *
 * @author chs
 */
public class MenuAnimado extends AbstractAction {

    private static final String acao = "go_home";
    // Tool tip text.
    public MenuAnimado() {
    }
    //  private static final Icon icone;

    public MenuAnimado(String nome, String descricao) {
        super();
        putValue(NAME, nome);
        putValue(SHORT_DESCRIPTION, descricao);
        putValue(ACTION_COMMAND_KEY, acao);
        // putValue(SMALL_ICON, icone);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(acao)) {
            JOptionPane.showMessageDialog(null, "teste de link");
        }
    }
}

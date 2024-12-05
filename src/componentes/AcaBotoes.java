/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;

import IntefaceAca.AcaComponente;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

/**
 *
 * @author chs
 */
public class AcaBotoes extends JButton implements MouseListener, AcaComponente {

    protected String dica;
    public boolean obrigatorio;

    public AcaBotoes() {
    }

    public AcaBotoes(boolean obrigatorio,String titulo, String dica) {
        super(titulo);
        this.dica = dica;
        this.obrigatorio = obrigatorio;
        this.setToolTipText(dica);
        addMouseListener(this);
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void limpar() {
    }

    public void habilitar(boolean status) {
        setEnabled(status);
    }

    public void editar(boolean status) {
    }

    public boolean eObrigatorio() {
        return obrigatorio;
    }

    public boolean eVazio() {
        return true;
    }

    public boolean eValido() {
        return true;
    }

   

    public void alterarCampos(boolean status) {
    }

    public String getDica() {
      return dica;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package componentes;

import javax.swing.JLabel;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

/**
 *
 * @author chs
 */
public class AcaLabel extends JLabel {
public AcaLabel(){
    setFont(new java.awt.Font("Arial", 0, 12));


}
 public String getValor() {
        String valor = this.getText();
        valor = valor.replace(".", "");
        valor = valor.replace(",", ".");
        return valor;
    }


}

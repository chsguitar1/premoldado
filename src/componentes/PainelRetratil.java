/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;

import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.border.DropShadowBorder;

/**
 *
 * @author chs
 */
public class PainelRetratil extends JXTaskPane {

    public PainelRetratil() {
        this.setTitle("Filtro");
        this.setCollapsed(true);
     
        this.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/viewmag.png")));

    }
}

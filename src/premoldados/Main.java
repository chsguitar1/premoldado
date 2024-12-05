 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package premoldados;

import java.awt.AWTKeyStroke;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import javax.swing.UIManager;

/**
 *
 * @author chs
 */
public class Main {

    public String usuario = "admin";
    public String senha = "admin";
    public static  boolean autorizado = true;

    public void setAutorizado(boolean autorizado) {
        this.autorizado = autorizado;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Long2 telalogin = new Long2();
        telalogin.setVisible(true);
        


    }
}

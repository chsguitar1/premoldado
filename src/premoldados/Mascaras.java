/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package premoldados;

import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author chs
 */
public class Mascaras {
 public static DefaultFormatterFactory setFormatoData()
    {
        MaskFormatter comFoco = null;
        try
        {
            comFoco = new MaskFormatter("##.###-###");
        }
        catch (Exception pe) { }
        DefaultFormatterFactory factory = new DefaultFormatterFactory(comFoco, comFoco);
        return factory;
    }
}

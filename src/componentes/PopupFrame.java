/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.plaf.basic.BasicComboBoxUI.ItemHandler;

/**
 *
 * @author chs
 */
public class PopupFrame {

    private JRadioButtonMenuItem[] items;
    private JPopupMenu popupMenu;
    public String[] colors = {"Remover", "alterar"};

    public PopupFrame() {

        ItemHandler handler = new ItemHandler();
        items = new JRadioButtonMenuItem[colors.length];
        popupMenu = new JPopupMenu();
        for (int count = 0; count < items.length; count++) {
            items[count] = new JRadioButtonMenuItem(colors[count]);
            popupMenu.add(items[count]);
            items[count].addActionListener(handler);
        }
        popupMenu.setBackground(Color.WHITE);
        popupMenu.setSize(300, 200);
        popupMenu.setVisible(true);

        popupMenu.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent event) {
                checkForTriggerEvent(event);
            }

            @Override
            public void mouseReleased(MouseEvent event) {
                checkForTriggerEvent(event);
            }

            public void checkForTriggerEvent(MouseEvent event) {
                if (event.isPopupTrigger()) {
                    popupMenu.show(event.getComponent(), 400, 600);
                }
            }
        });

    }

    private class ItemHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < items.length; i++) {
                if (e.getSource() == items[i]) {
                JOptionPane.showMessageDialog(null, items[i]);
                }

            }
        }
    }
}

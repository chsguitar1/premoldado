/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelos;

import java.awt.Component;
import java.text.NumberFormat;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author chs
 */
public class renderAlinha extends DefaultTableCellRenderer  {
  private NumberFormat formatoDinheiro = NumberFormat.getCurrencyInstance();

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        return comp;
    }

    @Override
    protected void setValue(Object value) {
        if (null != value) {
                super.setValue(formatoDinheiro.format(Double.valueOf(value.toString())));
                setHorizontalAlignment(RIGHT);
            }
    }
}

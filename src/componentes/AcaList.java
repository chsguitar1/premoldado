/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;

import IntefaceAca.AcaComponente;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import org.omg.CORBA.INTERNAL;
import Banco.Conexao;
import Telacadastros.TelaCadastro;
import telaMovimenacao.TelaMovimenta;

/**
 *
 * @author cristiano
 */
public class AcaList extends JList implements AcaComponente, KeyListener, MouseListener, ActionListener {

    public Object[] arrayObjetos;
    public Vector codigo = new Vector();
    boolean obrigatorio;
    String dica;

    public AcaList() {
//       
    }

    public AcaList(String sql, boolean obrigatorio, String dica) {
        System.out.println(sql);
        this.obrigatorio = obrigatorio;
        this.dica = dica;
        try {
            ResultSet rs = Conexao.executaQuery(sql);
            ArrayList list = new ArrayList();

            while (rs.next()) {
                list.add(new JCheckBox(rs.getString(2)));
                codigo.add(rs.getInt(1));

            }

            arrayObjetos = list.toArray();

            System.out.println(arrayObjetos);



        } catch (Exception ex) {
            ex.printStackTrace();
        }
        this.setCellRenderer(new CheckBoxCellRenderer());
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setListData(arrayObjetos);
        addMouseListener(this);
        addKeyListener(this);


    }

    public void montaLista(String sql) {
        try {
            ResultSet rs = Conexao.executaQuery(sql);
            ArrayList list = new ArrayList();

            while (rs.next()) {
                list.add(new JCheckBox(rs.getString(2)));
                codigo.add(rs.getString(1));

            }

            arrayObjetos = list.toArray();

            System.out.println(arrayObjetos);



        } catch (Exception ex) {
            ex.printStackTrace();
        }
        this.setCellRenderer(new CheckBoxCellRenderer());
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setListData(arrayObjetos);
        addMouseListener(this);
        addKeyListener(this);

    }

    public void getValor(TelaCadastro tela) {


        for (int i = 0; i < this.getModel().getSize(); i++) {
            JCheckBox checkbox =
                    (JCheckBox) this.getModel().getElementAt(i);
            if (checkbox.isSelected()) {

                tela.tipos.add(codigo.get(i));
            }
        }

    }

    public void getValor(TelaMovimenta tela) {

        for (int i = 0; i < this.getModel().getSize(); i++) {
            JCheckBox checkbox =
                    (JCheckBox) this.getModel().getElementAt(i);
            if (checkbox.isSelected()) {

                tela.selecao.add(codigo.get(i));
            }
        }


    }

    public int getValor() {
        for (int i = 0; i < this.getModel().getSize(); i++) {
            JCheckBox checkbox =
                    (JCheckBox) this.getModel().getElementAt(i);
            
                return Integer.parseInt(codigo.get(i).toString());
        }

        return -1;
    }

    public String getSelecionado() {
        return this.getSelectedValue().toString();
    }

    public void setValor() {
        for (int i = 0; i < this.getModel().getSize(); i++) {
            JCheckBox checkbox =
                    (JCheckBox) this.getModel().getElementAt(i);
            checkbox.isSelected();


        }
    }

    public void limpar() {
        for (int i = 0; i < this.getModel().getSize(); i++) {
            JCheckBox checkbox = (JCheckBox) this.getModel().getElementAt(i);
            //  checkbox.setSelected(false);
            this.removeAll();
            codigo.removeAllElements();
//                itens += "Item com índice " + codigo.get(i)
//                        + " está marcado\n";


        }
    }

    public void habilitar(boolean status) {
        this.setEnabled(status);
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

    public String getDica() {
        return dica;
    }

    public void alterarCampos(boolean status) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            int index = this.getSelectedIndex();
            if (index != -1) {
                JCheckBox checkbox =
                        (JCheckBox) this.getModel().getElementAt(index);
                checkbox.setSelected(!checkbox.isSelected());
                repaint();
            }
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        int index = this.locationToIndex(e.getPoint());
        if (index != -1) {
            JCheckBox checkbox =
                    (JCheckBox) this.getModel().getElementAt(index);
            checkbox.setSelected(!checkbox.isSelected());
            repaint();
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void actionPerformed(ActionEvent e) {
    }
}

class CheckBoxCellRenderer implements ListCellRenderer {

    Border noFocusBorder = new EmptyBorder(1, 1, 1, 1);

    public Component getListCellRendererComponent(
            JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {
        JCheckBox checkbox = (JCheckBox) value;
        checkbox.setBackground(isSelected
                ? list.getSelectionBackground() : list.getBackground());
        checkbox.setForeground(isSelected
                ? list.getSelectionForeground() : list.getForeground());

        checkbox.setEnabled(list.isEnabled());
        checkbox.setFont(list.getFont());
        checkbox.setFocusPainted(false);

        checkbox.setBorderPainted(true);
        checkbox.setBorder(isSelected ? UIManager.getBorder(
                "List.focusCellHighlightBorder") : noFocusBorder);

        return checkbox;
    }
}

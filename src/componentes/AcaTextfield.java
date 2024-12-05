/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;

import IntefaceAca.AcaComponente;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.Dimension2D;
import javax.swing.JTextField;

/**
 *
 * @author chs
 */
public class AcaTextfield extends JTextField implements AcaComponente, FocusListener {

    public int altura = 30;
    private boolean ogribatorio;
    private String dica;
    public int alterar;
    public int getInteiro;

    public AcaTextfield() {

        this.setFont(new java.awt.Font("Arial", 0, 12));
        setPreferredSize(new Dimension(60, 25));
        this.setText("");
        this.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
    }

    public AcaTextfield(boolean obrigatorio, String dica, int alterar) {
        this.ogribatorio = obrigatorio;
        this.dica = dica;
        this.alterar = alterar;
        addFocusListener(this);
        if (eObrigatorio()) {
//            setBackground(Color.GRAY);
        }
    }

    public String getTexto() {
        return this.getText().toUpperCase();
    }

    public void limpar() {
        this.setText("");
    }

    public void habilitar(boolean status) {
        this.setEnabled(status);
    }

    public void editar(boolean status) {
        this.setEditable(status);
    }

    public boolean eObrigatorio() {
        if (ogribatorio == false) {
            return false;
        }
        return true;
    }

    public boolean eVazio() {
        return getText().trim().isEmpty();
    }

    public boolean eValido() {
        return true;
    }

    public String getDica() {
        return dica;
    }

    public void focusGained(FocusEvent e) {
        setBackground(Color.YELLOW);
    }

    public void focusLost(FocusEvent e) {
        if (eObrigatorio()) {
            setBackground(Color.YELLOW);

        } else {
            setBackground(Color.WHITE);

        }
    }

    public int getInteiro() {
        return Integer.parseInt(this.getText());
    }

    public void setInteiro(int valor) {
        setText(String.valueOf(valor));

    }

    public void alterarCampos(boolean status) {
        if (alterar == 0) {
            setEnabled(status);
        }
    }
}

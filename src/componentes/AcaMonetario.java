/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;

import IntefaceAca.AcaComponente;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author chs
 */
public class AcaMonetario extends AcaFormatado implements AcaComponente, CaretListener {

    private boolean obrigatorio;
    private String dica;
    private int alterar;

    public AcaMonetario() {
        this.setFont(new java.awt.Font("Arial", 0, 12));
        setPreferredSize(new Dimension(60, 25));
      
        this.setDocument(new MonetarioDocument());
        setHorizontalAlignment(RIGHT);
        addCaretListener(this);
    }

    public AcaMonetario(boolean obrigatorio, String dica, int alterar) {
        this.ogribatorio = obrigatorio;
        this.dica = dica;
        this.alterar = alterar;
        this.setDocument(new MonetarioDocument());
        setHorizontalAlignment(RIGHT);
        addCaretListener(this);
        if (eObrigatorio()) {
            // setBackground(Color.GRAY);
        }
    }

    public void caretUpdate(CaretEvent e) {
        int posfinal = getText().length();
        if (e.getDot() != posfinal) {
            getCaret().setDot(posfinal);
        }
    }

    @Override
    public boolean eObrigatorio() {
    
      
        if (ogribatorio == false) {
            return false;
        }
        return true;
    }

    @Override
    public boolean eVazio() {
         return getText().trim().isEmpty();

    }

    @Override
    public boolean eValido() {
        return true;
    }

    @Override
    public String getDica() {
        return dica;
    }

    public String getValor() {
        String valor = this.getText();
        valor = valor.replace(".", "");
        valor = valor.replace(",", ".");
        return valor;
    }

    public void setValor(double val) {
        int x = (int) (val * 100);
        val = x / 100.0;
        String valor = "" + val;
        while (valor.indexOf(".") != (valor.length() - 3)) {
            valor = valor + "0";
        }
        valor = valor.replace(".", "");
        this.setText(valor);
    }

    public void limpaCampo() {
        setText("");
    }

    public void habilitaCampo(boolean status) {
        setEnabled(status);
    }
}

class MonetarioDocument extends PlainDocument {

    @Override
    public void remove(int offs, int len) throws BadLocationException {
        super.remove(offs, len);
        insertString(offs, "", null);
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        String texto = getText(0, getLength());
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
//            JOptionPane.showMessageDialog(null, c);
            if (!Character.isDigit(c)) {
//                 JOptionPane.showMessageDialog(null, c);
                return;
            }
        }

        super.remove(0, getLength());
        texto = texto.replace(".", "").replace(",", "");
        StringBuffer s = new StringBuffer(texto + str);

        while (s.length() > 0 && s.charAt(0) == '0') {
            s.deleteCharAt(0);
        }

        while (s.length() < 3) {
            s.insert(0, "0");
        }

        if (s.length() > 5) {
            String inteiro = s.toString().substring(0, s.toString().length() - 2);
            int milhar = 0;
            texto = "";
            for (int contador = (inteiro.length() - 1); contador >= 0; contador--) {
                milhar++;
                if ((milhar == 3) && (contador > 0)) {
                    texto = "." + inteiro.charAt(contador) + texto;
                    milhar = 0;
                } else {
                    texto = inteiro.charAt(contador) + texto;
                }
            }
            texto = texto + s.toString().substring(s.toString().length() - 2);
        } else {
            texto = s.toString();
        }
        texto = texto.substring(0, texto.length() - 2) + "," + texto.substring(texto.length() - 2, texto.length());
        super.insertString(0, texto, a);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;

import IntefaceAca.AcaComponente;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author chs
 */
public class AcaFormatado extends JFormattedTextField implements AcaComponente {

    protected boolean ogribatorio;
    protected String dica;
    protected MaskFormatter mf = new MaskFormatter();
    protected String[] mascaras;
    int alterar = -1;

    public AcaFormatado() {
        this.setFont(new java.awt.Font("Tahoma", 0, 14));
        setText("");
    }

    public AcaFormatado(boolean obrigatorio, String dica, int alterar) {
        this.ogribatorio = obrigatorio;
        this.dica = dica;
        this.alterar = alterar;
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

    public void alterarCampos(boolean status) {
         if(alterar == 0){
           setEnabled(status);
       }
    }
}

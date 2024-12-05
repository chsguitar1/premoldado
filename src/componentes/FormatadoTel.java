/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;

import java.text.ParseException;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author chs
 */
public class FormatadoTel extends AcaFormatado {

    private MaskFormatter ftmTel;

    public FormatadoTel() {
        this.setFont(new java.awt.Font("Tahoma", 0, 14));
        setText("");
    }

    public FormatadoTel(boolean obrigatorio, String dica, int alterar) {
        super(obrigatorio, dica, alterar);
        this.dica = dica;

        try {
            ftmTel = new MaskFormatter("(##)####-####");
            ftmTel.setValidCharacters("0123456789");
            ftmTel.install(this);
        } catch (ParseException e) {
        }
    }

    public String getTexto() {
        String tel = this.getText();
        tel = tel.replace("(", "");
        tel = tel.replace(")", "");
        tel = tel.replace("-", "");

      return tel;
    }

    @Override
    public void limpar() {
        this.setText("");
    }

    @Override
    public void habilitar(boolean status) {
        this.setEnabled(status);
    }

    @Override
    public void editar(boolean status) {
        this.setEditable(status);
    }

    @Override
    public boolean eObrigatorio() {
        return true;
    }

    @Override
    public boolean eVazio() {
        String texto = getText().replace('(', '\0').replace(')', '\0').replace('-', '\0').trim();
        if (texto.length() > 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean eValido() {
        if (getText().equals("(00)0000-0000")) {
            return false;
        }
        return true;
    }

    @Override
    public String getDica() {
        return dica;
    }

    @Override
    public void alterarCampos(boolean status) {
        if (alterar == 0) {
            setEnabled(status);
        }
    }
}

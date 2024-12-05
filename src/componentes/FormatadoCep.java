/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;

import java.awt.Dimension;
import java.text.ParseException;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author chs
 */
public class FormatadoCep extends AcaFormatado {

    private MaskFormatter forCep;
    private boolean obrigatorio;

    public FormatadoCep() {
        this.setFont(new java.awt.Font("Arial", 0, 12));
        setPreferredSize(new Dimension(60, 25));
        this.setText("");
    }

    public FormatadoCep(boolean obrigatorio, String dica, int alterar) {
        super(obrigatorio, dica, alterar);
        this.dica = dica;
        this.ogribatorio = obrigatorio;

        try {
            forCep = new MaskFormatter("#####-###");
            forCep.setValidCharacters("0123456789");
            forCep.install(this);
        } catch (ParseException e) {
        }
    }

    public String getTexto() {
        String campo = this.getText();
        campo = campo.replace("-", "");

        return campo;
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
        String texto = getText().replace("-", " ");
        if (texto.trim().length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean eValido() {
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


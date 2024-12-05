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
public class FormatadoRgIe extends AcaFormatado {

    private String tipo;
    private MaskFormatter mascaraRG;
    private MaskFormatter mascaraIe;

    public FormatadoRgIe() {
        this.setFont(new java.awt.Font("Tahoma", 0, 14));
        setText("");
    }

    public FormatadoRgIe(String tipo, boolean obrigatorio, String dica, int alterar) {
        super(obrigatorio, dica, alterar);
        this.ogribatorio = obrigatorio;
        this.tipo = tipo;
        this.dica = dica;

        inicializaMascara();

    }

    public void inicializaMascara() {
        try {
            mascaraRG = new MaskFormatter("#.###.###-#");
            mascaraIe = new MaskFormatter("###.#####-##");
            if (tipo.equals("RG")) {
                mascaraRG.install(this);
            } else {
                mascaraIe.install(this);
            }
        } catch (ParseException ex) {
        }
    }

    public void setFormatoRG() {
        mascaraIe.uninstall();
        mascaraRG.install(this);
        tipo = "RG";
    }

    public void setFormatoIE() {
        mascaraRG.uninstall();
        mascaraIe.install(this);
        tipo = "IE";
    }

    public void setTexto(String texto) {
    String campo = texto;
    campo = campo.replace("-", "");
    campo = campo.replace(".", "");
    this.setText(campo);
    }
    public String getTexto() {
        String texto = this.getText();
        texto = texto.replace(".", "");
        texto = texto.replace("-", "");
        return texto;
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
        String cpfcnpj = this.getText();
        cpfcnpj = cpfcnpj.replace("-", "");
        cpfcnpj = cpfcnpj.replace(".", "");
        cpfcnpj = cpfcnpj.replace("/", "");
        if (cpfcnpj.length() == 0) {
            return true;
        }
        return false;
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;

import Util.ValidaCnpj;
import Util.ValidaCpf;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author chs
 */
public class FormatadoCpfCnpj extends AcaFormatado {

    private MaskFormatter mascaraCPF;
    private MaskFormatter mascaraCNPJ;
    private String tipo;

    public FormatadoCpfCnpj() {
        this.setFont(new java.awt.Font("Tahoma", 0, 14));
        setText("");
    }

    public FormatadoCpfCnpj(String tipo, boolean obrigatorio, String dica, int alterar) {
        super(obrigatorio, dica, alterar);
        this.ogribatorio = obrigatorio;
        this.tipo = tipo;
        this.dica = dica;

        inicializaMascara();

    }

    public void inicializaMascara() {
        try {
            mascaraCPF = new MaskFormatter("###.###.###-##");
            mascaraCNPJ = new MaskFormatter("##.###.###/####-##");
            if (tipo.equals("CPF")) {
                mascaraCPF.install(this);
            } else {
                mascaraCNPJ.install(this);
            }
        } catch (ParseException ex) {
        }
    }

    public void setFormatoCPF() {
        mascaraCNPJ.uninstall();
        mascaraCPF.install(this);
        tipo = "CPF";
    }

    public void setFormatoCNPJ() {
        mascaraCPF.uninstall();
        mascaraCNPJ.install(this);
        tipo = "CNPJ";
    }

    public String getTexto() {
        String campo = this.getText();
        campo = campo.replace("-", "");
        campo = campo.replace(".", "");
        campo = campo.replace("/", "");
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

    public boolean validaCPF() {
        if (ValidaCpf.validaCpf(getText()) == false) {
            setBackground(Color.red);
            JOptionPane.showMessageDialog(null, "CPF Inválido!");
            return false;

        }
        return true;

    }

    public boolean validaCNPJ() {
        if (ValidaCnpj.validaCnpj(getText()) == false) {
            setBackground(Color.red);
            JOptionPane.showMessageDialog(null, "CNPJ Inválido!");
            return false;
        }
        return true;
    }

    public void setValor(String valor) {
        String cnpcnpj = valor;
        cnpcnpj = cnpcnpj.replace("-", "");
        cnpcnpj = cnpcnpj.replace(".", "");
        cnpcnpj = cnpcnpj.replace("/", "");
        this.setText(cnpcnpj);
    }
}

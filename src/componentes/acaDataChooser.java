/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;

import IntefaceAca.AcaComponente;
import com.toedter.calendar.JDateChooser;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author cristiano
 */
public class acaDataChooser extends JDateChooser implements AcaComponente {

    public boolean obrigatorio;
    public String dica;
    private static final Calendar DATA_VALOR = Calendar.getInstance();

    public acaDataChooser() {
    }

    public acaDataChooser(String dica, boolean obrigatorio) {
        this.obrigatorio = obrigatorio;
        this.dica = dica;


    }

    public void limpar() {
        this.setDate(null);
    }

    public void habilitar(boolean status) {
        this.setEnabled(status);
    }

    public void editar(boolean status) {
    }

    public boolean eObrigatorio() {
        if (obrigatorio == false) {
            return false;
        }
        return true;
    }

    public boolean eVazio() {
        return getDate().toString().isEmpty();
    }

    public boolean eValido() {
        return true;
    }

    public String getDica() {
        return dica;
    }

    public String getValor() {
        String data = "";
        DATA_VALOR.setTime(this.getDate());
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
        return data = df.format(DATA_VALOR.getTime()).toString().replace("/", ".");

    }
     public void SetValor(Date data) {
       
        this.setDate(data);
    }

    public void alterarCampos(boolean status) {
        return;
    }
}

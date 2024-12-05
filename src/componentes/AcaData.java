/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;

import IntefaceAca.AcaComponente;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.JOptionPane;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author chs
 */
public class AcaData extends JXDatePicker implements AcaComponente {

    protected boolean obrigatorio;
    protected String dica;

    public AcaData() {
    }

    public AcaData(boolean obrigatorio, String dica) {
        this.obrigatorio = obrigatorio;
        this.dica = dica;


    }

    public String getValor() {

        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");

        return formato.format(this.getDate());


    }

    public void SetValor(Date data) {
       
        this.setDate(data);
    }

    public void SetValor(String data) {
        System.out.println(data);
        data = data.replace("-", "/");
        System.out.println(data);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
 System.out.println(sdf);
        try {
            Date date = sdf.parse(data);

            this.setDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(AcaData.class.getName()).log(Level.SEVERE, null, ex);
        }

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

//
    public boolean eVazio() {
        if (obrigatorio) {
            if (this.getDate() == null) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean eValido() {
        return true;
    }

    public String getDica() {
        return dica;
    }

    public void alterarCampos(boolean status) {
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Telaconsulta;

/**
 *
 * @author cristiano
 */
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

public class Teste {

    public static void main(String[] args) {
        GregorianCalendar gc = new GregorianCalendar();
        int numPar = Integer.parseInt(JOptionPane.showInputDialog("Quantidade de parcelas"));
        Date diaAtual = new Date();
        for (int e = 0; e < numPar; e++) {
            gc.setTime(diaAtual);
            
            gc.roll(GregorianCalendar.MONTH, e);

            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
             
            Date d = gc.getTime();
            if(d.equals(GregorianCalendar.DECEMBER)){
                gc.roll(GregorianCalendar.YEAR,1);

            }
            if (d.equals(diaAtual)) {
                System.out.print("Entrada ");
            } else {
                System.out.print("Parcela " + e + ": ");
            }

            System.out.println(df.format(d));

        }
    }
}

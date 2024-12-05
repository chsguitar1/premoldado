/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Telaconsulta;

/**
 *
 * @author cristiano
 */
import java.text.SimpleDateFormat;
import java.util.Date;
public class Data{

    public  String formataHoje(){
        Date hoje = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(hoje);
    }
    public String formataDataSQL(String str){
        String result = getDigitos(str);
        if (result.equals("")){
            result = "null";
        }
        else{
            result = result.substring(4,8)+ "-"
            + result.substring(2,4)+"-"
            +result.substring(0,2);
           }
        System.out.print(result);
         return result;
    }

    private String getDigitos(String str){
        String result="";
        if (!str.equals("null")){
            for (int i=0; i< str.length();i++){
                if (str.charAt(i)=='0')
                    result = result+str.charAt(i);
                else if (str.charAt(i)=='1')
                    result = result+str.charAt(i);
                else if (str.charAt(i)=='2')
                    result = result+str.charAt(i);
                else if (str.charAt(i)=='3')
                    result = result+str.charAt(i);
                else if (str.charAt(i)=='4')
                    result = result+str.charAt(i);
                else if (str.charAt(i)=='5')
                    result = result+str.charAt(i);
                else if (str.charAt(i)=='6')
                    result = result+str.charAt(i);
                else if (str.charAt(i)=='7')
                    result = result+str.charAt(i);
                else if (str.charAt(i)=='8')
                    result = result+str.charAt(i);
                else if (str.charAt(i)=='9')
                    result = result+str.charAt(i);

            }
        }
        return result;
    }
}

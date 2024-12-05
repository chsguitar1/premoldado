/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import IntefaceAca.OperaBanco;
import java.util.Vector;

/**
 *
 * @author cristiano
 */
public class PontFornecedor implements OperaBanco {

    public int getCodigoItenPon() {
        return codigoItenPon;
    }

    public void setCodigoItenPon(int codigoItenPon) {
        this.codigoItenPon = codigoItenPon;
    }

    public float getPesoUnitario() {
        return pesoUnitario;
    }

    public void setPesoUnitario(float pesoUnitario) {
        this.pesoUnitario = pesoUnitario;
    }

    public int getCodigoClassifica() {
        return codigoClassifica;
    }

    public void setCodigoClassifica(int codigoClassifica) {
        this.codigoClassifica = codigoClassifica;
    }

    public int getCodigoPontFor() {
        return codigoPontFor;
    }

    public void setCodigoPontFor(int codigoPontFor) {
        this.codigoPontFor = codigoPontFor;
    }
    public int codigoPontFor;
    public int codigoClassifica;
    public float pesoUnitario;
    public int codigoItenPon;

    public String getInsereSql() {
    return "insert into itensponxforn(iteponcod,forxcod,pesounitario)values("+codigoItenPon+","+codigoClassifica+","+pesoUnitario+")";
    }

    public String getAtualizaSql() {
        return " update itensponxforn set pesounitario = "+pesoUnitario+" where forxcod = "+codigoClassifica+" and  iteponcod = "+codigoItenPon+" ";
    }

    public String getExcluiSql() {
        return "delete from itensponxforn where forxcod = "+codigoClassifica+" ";
    }

    public String getConsultaTodos() {
        return "SELECT I2.ITEPONCOD,I2.ITENPONNOME,I1.PESOUNITARIO FROM ITENSPONTUACAO I2,ITENSPONXFORN I1, FORNXPONTUACAO F1 WHERE I1.FORXCOD = F1.FORXCOD AND I1.ITEPONCOD = I2.ITEPONCOD and I1.forxcod = "+codigoClassifica+"";
    }

    public String getConsultaSqlCod() {
        return "";
    }

    public String getConsultaSqlString() {


        return "";
    }
}

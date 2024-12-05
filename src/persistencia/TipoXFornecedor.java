/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import IntefaceAca.OperaBanco;

/**
 *
 * @author cristiano
 */
public class TipoXFornecedor implements OperaBanco {

    public int codtipoxfor;
    public int tipomatcod;
    public int forcod;

    public int getCodtipoxfor() {
        return codtipoxfor;
    }

    public void setCodtipoxfor(int codtipoxfor) {
        this.codtipoxfor = codtipoxfor;
    }

    public int getForcod() {
        return forcod;
    }

    public void setForcod(int forcod) {
        this.forcod = forcod;
    }

    public int getTipomatcod() {
        return tipomatcod;
    }

    public void setTipomatcod(int tipomatcod) {
        this.tipomatcod = tipomatcod;
    }

    public String getInsereSql() {
     return "insert into tipoxfornecedor(tipomatcod,forcod)values("+tipomatcod+","+forcod+") ";
    }

    public String getAtualizaSql() {
       return "";
    }

    public String getExcluiSql() {
      return "";
    }

    public String getConsultaTodos() {
        return "";
    }

    public String getConsultaSqlCod() {
      return "";
    }

    public String getConsultaSqlString() {
       return "";
    }

}

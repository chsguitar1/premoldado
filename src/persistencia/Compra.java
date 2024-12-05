package persistencia;

import IntefaceAca.OperaBanco;
import java.sql.ResultSet;
import Banco.Conexao;

public class Compra implements OperaBanco {

    public int comcod;
    public int pecomcod;
    public int compar;
    public int comstatus;
    public String comdata;
    public double comtotal;

    public int getComcod() {
        return comcod;
    }

    public void setComcod(int comcod) {
        this.comcod = comcod;
    }

    public String getComdata() {
        return comdata;
    }

    public void setComdata(String comdata) {
        this.comdata = comdata;
    }

    public int getCompar() {
        return compar;
    }

    public void setCompar(int compar) {
        this.compar = compar;
    }

    public int getComstatus() {
        return comstatus;
    }

    public void setComstatus(int comstatus) {
        this.comstatus = comstatus;
    }

    public double getComtotal() {
        return comtotal;
    }

    public void setComtotal(double comtotal) {
        this.comtotal = comtotal;
    }

    public int getPecomcod() {
        return pecomcod;
    }

    public void setPecomcod(int pecomcod) {
        this.pecomcod = pecomcod;
    }

    public String getInsereSql() {
      return "";
    }
 public ResultSet getInsereRs() {
        String sql = "insert into compra(pecomcod,comdata ,comtotal,compar,comstatus)values(" + pecomcod + ",'" + comdata + "'," + comtotal + "," +compar+ ","+comstatus+")returning comcod";
        System.out.println(sql);
        return Conexao.executaQuery(sql);

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

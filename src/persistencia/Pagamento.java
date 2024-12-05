package persistencia;

import IntefaceAca.OperaBanco;
import java.sql.ResultSet;
import Banco.Conexao;

public class Pagamento implements OperaBanco {

    public int pagcod;
    public int conpagcod;
    public int pagstatus;
    public String pagdata;
    public double pagvlr;
    public String pagnome;
    public String pagcond;


    public int getConpagcod() {
        return conpagcod;
    }

    public void setConpagcod(int conpagcod) {
        this.conpagcod = conpagcod;
    }

    public int getPagcod() {
        return pagcod;
    }

    public void setPagcod(int pagcod) {
        this.pagcod = pagcod;
    }

    public String getPagcond() {
        return pagcond;
    }

    public void setPagcond(String pagcond) {
        this.pagcond = pagcond;
    }

    public String getPagdata() {
        return pagdata;
    }

    public void setPagdata(String pagdata) {
        this.pagdata = pagdata;
    }

    public String getPagnome() {
        return pagnome;
    }

    public void setPagnome(String pagnome) {
        this.pagnome = pagnome;
    }

    public int getPagstatus() {
        return pagstatus;
    }

    public void setPagstatus(int pagstatus) {
        this.pagstatus = pagstatus;
    }

    public double getPagvlr() {
        return pagvlr;
    }

    public void setPagvlr(double pagvlr) {
        this.pagvlr = pagvlr;
    }


    public String getInsereSql() {
      return "";
    }
public ResultSet getInsereRs() {
        String sql = "insert into pagamento(conpagcod,pagdata,pagvlr,pagnome,pagcond,pagstatus)values("+conpagcod+",'"+pagdata+"',"+pagvlr+",'"+pagnome+"','"+pagcond+"',"+pagstatus+")returning pagcod";
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

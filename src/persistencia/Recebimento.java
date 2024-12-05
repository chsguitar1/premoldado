package persistencia;

import IntefaceAca.OperaBanco;
import java.sql.ResultSet;
import Banco.Conexao;

public class Recebimento implements OperaBanco{

    public int reccod;
    public int conreccod;
    public int recstatus;
    public String recdata;
    public double recvlr;
    public String recnome;
    public String recond;

    public int getConreccod() {
        return conreccod;
    }

    public void setConreccod(int conreccod) {
        this.conreccod = conreccod;
    }

    public int getReccod() {
        return reccod;
    }

    public void setReccod(int reccod) {
        this.reccod = reccod;
    }

    public String getRecdata() {
        return recdata;
    }

    public void setRecdata(String recdata) {
        this.recdata = recdata;
    }

    public String getRecnome() {
        return recnome;
    }

    public void setRecnome(String recnome) {
        this.recnome = recnome;
    }

    public String getRecond() {
        return recond;
    }

    public void setRecond(String recond) {
        this.recond = recond;
    }

    public int getRecstatus() {
        return recstatus;
    }

    public void setRecstatus(int recstatus) {
        this.recstatus = recstatus;
    }

    public double getRecvlr() {
        return recvlr;
    }

    public void setRecvlr(double recvlr) {
        this.recvlr = recvlr;
    }

    public String getInsereSql() {
      return "insert into recebimento(conreccod,recdata,recvlr,recnome,recond,recstatus)values("+conreccod+",'"+recdata+"',"+recvlr+",'"+recnome+"','"+recond+"',"+recstatus+")";
    }
    public ResultSet getInsereRs() {
        String sql = "insert into recebimento(conreccod,recdata,recvlr,recnome,recond,recstatus)values(" + conreccod + ",'" + recdata + "'," + recvlr + ",'" + recnome + "','" + recond + "'," + recstatus + ")returning reccod";
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

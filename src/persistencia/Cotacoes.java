package persistencia;

import IntefaceAca.OperaBanco;
import java.sql.ResultSet;
import Banco.Conexao;

public class Cotacoes implements OperaBanco {

    public int cotcod;
    public int cotstatus;
    public String  cotdata;
    public String cotdatafin;
    public double cotvlrtotal;
    public int tipomatcod;

    public int getTipomatcod() {
        return tipomatcod;
    }

    public void setTipomatcod(int tipomatcod) {
        this.tipomatcod = tipomatcod;
    }


    public double getCotvlrtotal() {
        return cotvlrtotal;
    }

    public void setCotvlrtotal(double cotvlrtotal) {
        this.cotvlrtotal = cotvlrtotal;
    }

    public int getCotcod() {
        return cotcod;
    }

    public void setCotcod(int cotcod) {
        this.cotcod = cotcod;
    }

    public String getCotdata() {
        return cotdata;
    }

    public void setCotdata(String cotdata) {
        this.cotdata = cotdata;
    }

    public String getCotdatafin() {
        return cotdatafin;
    }

    public void setCotdatafin(String cotdatafin) {
        this.cotdatafin = cotdatafin;
    }

    public int getCotstatus() {
        return cotstatus;
    }
     public ResultSet getInsereRs() {
        String sql = "insert into cotacoes(cotdata,cotdatafin,cotstatus,tipomatcod)values('"+cotdata+"','"+cotdatafin+"',"+cotstatus+","+tipomatcod+")returning cotcod";
       System.out.println(sql);
        return Conexao.executaQuery(sql);

    }

    public void setCotstatus(int cotstatus) {
        this.cotstatus = cotstatus;
    }

    public String getInsereSql() {
        return "";
    }

    public String getAtualizaSql() {
        return "update cotacoes set cotdata= '"+cotdata+"',cotdatafin = '"+cotdatafin+"',tipomatcod = "+tipomatcod+",cotvlrtotal = "+cotvlrtotal+" where cotcod = "+cotcod+"";
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
    public boolean verificaDuplicidade(){
        return true;

    }

}

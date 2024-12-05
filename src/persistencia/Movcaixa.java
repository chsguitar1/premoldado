package persistencia;

import IntefaceAca.OperaBanco;

public class Movcaixa implements OperaBanco{

    public int movcod;
    public int pagcod;
    public int reccod;
    public int movtipo;
    public int movstatus;
    public String movdata;
    public double movvlr;
    public String movdescr;
    public int cxcod;

    public int getCxcod() {
        return cxcod;
    }

    public void setCxcod(int cxcod) {
        this.cxcod = cxcod;
    }

    public int getMovcod() {
        return movcod;
    }

    public void setMovcod(int movcod) {
        this.movcod = movcod;
    }

    public String getMovdata() {
        return movdata;
    }

    public void setMovdata(String movdata) {
        this.movdata = movdata;
    }

    public String getMovdescr() {
        return movdescr;
    }

    public void setMovdescr(String movdescr) {
        this.movdescr = movdescr;
    }

    public int getMovstatus() {
        return movstatus;
    }

    public void setMovstatus(int movstatus) {
        this.movstatus = movstatus;
    }

    public int getMovtipo() {
        return movtipo;
    }

    public void setMovtipo(int movtipo) {
        this.movtipo = movtipo;
    }

    public double getMovvlr() {
        return movvlr;
    }

    public void setMovvlr(double movvlr) {
        this.movvlr = movvlr;
    }

    public int getPagcod() {
        return pagcod;
    }

    public void setPagcod(int pagcod) {
        this.pagcod = pagcod;
    }

    public int getReccod() {
        return reccod;
    }

    public void setReccod(int reccod) {
        this.reccod = reccod;
    }

    public String getInsereSql() {
      return "insert into movcaixa(pagcod,reccod,movdata,movdescr,movtipo,movvlr,movstatus,cxcod)values("+pagcod+","+reccod+",'"+movdata+"','"+movdescr+"',"+movtipo+","+movvlr+","+movstatus+","+cxcod+")";
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

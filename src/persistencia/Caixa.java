package persistencia;

import IntefaceAca.OperaBanco;

public class Caixa implements OperaBanco {

    public int cxstatus;
    public int cxcod;
    public int movcod;
    public String cxdata;
    public double cxsaldotot;
    public double cxvlr;
    public double cxsaldoini;

    public int getCxcod() {
        return cxcod;
    }

    public void setCxcod(int cxcod) {
        this.cxcod = cxcod;
    }

    public String getCxdata() {
        return cxdata;
    }

    public void setCxdata(String cxdata) {
        this.cxdata = cxdata;
    }

    public double getCxsaldoini() {
        return cxsaldoini;
    }

    public void setCxsaldoini(double cxsaldoini) {
        this.cxsaldoini = cxsaldoini;
    }

    public double getCxsaldotot() {
        return cxsaldotot;
    }

    public void setCxsaldotot(double cxsaldotot) {
        this.cxsaldotot = cxsaldotot;
    }

    public int getCxstatus() {
        return cxstatus;
    }

    public void setCxstatus(int cxstatus) {
        this.cxstatus = cxstatus;
    }

    public double getCxvlr() {
        return cxvlr;
    }

    public void setCxvlr(double cxvlr) {
        this.cxvlr = cxvlr;
    }

    public int getMovcod() {
        return movcod;
    }

    public void setMovcod(int movcod) {
        this.movcod = movcod;
    }
    

    public String getInsereSql() {
     return "INSERT INTO CAIXA (CXDATA, CXSALDOINI, cxsaldotot,CXSTATUS) VALUES ('"+cxdata+"',"+cxsaldoini+","+cxsaldoini+","+cxstatus+")";
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

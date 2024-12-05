package persistencia;

import IntefaceAca.OperaBanco;

public class Itensordprod implements OperaBanco {

    public int iteordcod;
    public int odcod;
    public int compocod;
    public int iteordqtd;

    public int getCompocod() {
        return compocod;
    }

    public void setCompocod(int compocod) {
        this.compocod = compocod;
    }

    public int getIteordcod() {
        return iteordcod;
    }

    public void setIteordcod(int iteordcod) {
        this.iteordcod = iteordcod;
    }

    public int getIteordqtd() {
        return iteordqtd;
    }

    public void setIteordqtd(int iteordqtd) {
        this.iteordqtd = iteordqtd;
    }

    public int getOdcod() {
        return odcod;
    }

    public void setOdcod(int odcod) {
        this.odcod = odcod;
    }

    public String getInsereSql() {
      return "INSERT INTO ITENSORDPROD (ODCOD, COMPOCOD, ITEORDQTD) VALUES ("+odcod+","+compocod+","+iteordqtd+")";
    }

    public String getAtualizaSql() {
      return "";
    }

    public String getExcluiSql() {
      return "";
    }

    public String getConsultaTodos() {
      return "select * from Itensordprod where odcod = "+odcod+"  ";
    }

    public String getConsultaSqlCod() {
      return "";
    }

    public String getConsultaSqlString() {
      return "";
    }
}

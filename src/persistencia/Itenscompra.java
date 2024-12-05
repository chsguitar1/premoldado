package persistencia;

import IntefaceAca.OperaBanco;

public class Itenscompra implements OperaBanco{

    public int getComcod() {
        return comcod;
    }

    public void setComcod(int comcod) {
        this.comcod = comcod;
    }

    public int getItecomcod() {
        return itecomcod;
    }

    public void setItecomcod(int itecomcod) {
        this.itecomcod = itecomcod;
    }

    public float getItecomqtd() {
        return itecomqtd;
    }

    public void setItecomqtd(float itecomqtd) {
        this.itecomqtd = itecomqtd;
    }

    public double getItecomunit() {
        return itecomunit;
    }

    public void setItecomunit(double itecomunit) {
        this.itecomunit = itecomunit;
    }

    public int getMatcod() {
        return matcod;
    }

    public void setMatcod(int matcod) {
        this.matcod = matcod;
    }

    public int itecomcod;
    public int comcod;
    public int matcod;
    public float itecomqtd;
    public double itecomunit;
    public double itecomsubtot;

    public double getItecomsubtot() {
        return itecomsubtot;
    }

    public void setItecomsubtot(double itecomsubtot) {
        this.itecomsubtot = itecomsubtot;
    }

    public String getInsereSql() {
      return "insert into itenscompra(matcod,comcod,itecomqtd,itecomunit,itecomsubtot)values ("+matcod+","+comcod+","+itecomqtd+","+itecomunit+","+itecomsubtot+")";
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

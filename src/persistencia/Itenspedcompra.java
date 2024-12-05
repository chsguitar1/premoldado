package persistencia;

import IntefaceAca.OperaBanco;

public class Itenspedcompra implements OperaBanco {

    public int itepedcod;
    public int pecomcod;
    public int matcod;
    public float itepedqtde;
    public double itepedunit;
    public double itepedsubtotal;

    public int getItepedcod() {
        return itepedcod;
    }

    public void setItepedcod(int itepedcod) {
        this.itepedcod = itepedcod;
    }

    public float getItepedqtde() {
        return itepedqtde;
    }

    public void setItepedqtde(float itepedqtde) {
        this.itepedqtde = itepedqtde;
    }

    public double getItepedsubtotal() {
        return itepedsubtotal;
    }

    public void setItepedsubtotal(double itepedsubtotal) {
        this.itepedsubtotal = itepedsubtotal;
    }

    public double getItepedunit() {
        return itepedunit;
    }

    public void setItepedunit(double itepedunit) {
        this.itepedunit = itepedunit;
    }

    public int getMatcod() {
        return matcod;
    }

    public void setMatcod(int matcod) {
        this.matcod = matcod;
    }

    public int getPecomcod() {
        return pecomcod;
    }

    public void setPecomcod(int pecomcod) {
        this.pecomcod = pecomcod;
    }
    

    public String getInsereSql() {
          return "insert into itenspedcompra(itepedqtde,matcod,pecomcod,itepedunit,itepedsubtotal) values("+itepedqtde+","+matcod+","+pecomcod+","+itepedunit+","+itepedsubtotal+")";
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
          return "SELECT M1.MATCOD,M1.MATNOME,I1.ITEPEDQTDE,I1.ITEPEDUNIT,I1.ITEPEDSUBTOTAL"
                 + " FROM MATERIAPRIMA M1,ITENSPEDCOMPRA I1 WHERE M1.MATCOD = I1.MATCOD and I1.pecomcod = "+pecomcod+"";
    }

    public String getConsultaSqlString() {
         return "";
    }
}

package persistencia;

import IntefaceAca.OperaBanco;

public class Itenspedvenda implements OperaBanco{

    

    public double getItenvalorunit() {
        return itenvalorunit;
    }

    public void setItenvalorunit(double itenvalorunit) {
        this.itenvalorunit = itenvalorunit;
    }

    public int getItenvencod() {
        return itenvencod;
    }

    public void setItenvencod(int itenvencod) {
        this.itenvencod = itenvencod;
    }

    public int getProdcod() {
        return prodcod;
    }

    public void setProdcod(int prodcod) {
        this.prodcod = prodcod;
    }

    public int getVencod() {
        return vencod;
    }

    public void setVencod(int vencod) {
        this.vencod = vencod;
    }


    public float getItenqtdeprod() {
        return itenqtdeprod;
    }

    public void setItenqtdeprod(float itenqtdeprod) {
        this.itenqtdeprod = itenqtdeprod;
    }
    public int itenvencod;
    public int prodcod;
    public int vencod;
    public float itenqtdeprod;
    public double itenvlrunita;

    public double getItenvlrunita() {
        return itenvlrunita;
    }

    public void setItenvlrunita(double itenvlrunita) {
        this.itenvlrunita = itenvlrunita;
    }
   
    public double itenvalorunit;

    public String getInsereSql() {
       return "insert into Itenspedvenda(vencod,prodcod,itenqtdeprod,itenvalorunit,itenvlrunita)values("+vencod+","+prodcod+","+itenqtdeprod+","+itenvalorunit+","+itenvlrunita+")";
    }

    public String getAtualizaSql() {
       return "EXECUTE PROCEDURE ALTERAITEMPEDVENDA("+vencod+", "+prodcod+", "+itenvalorunit+", "+itenvlrunita+", "+itenqtdeprod+")";
    }

    public String getExcluiSql() {
       return "delete from Itenspedvenda where pedvencod = "+vencod+" and  prodcod = "+prodcod+"";
    }

    public String getConsultaTodos() {
       return "";
    }

    public String getConsultaSqlCod() {
       return "SELECT P1.PRODCOD,P1.PRODNOME,I1.ITENQTDEPROD,I1.ITENVLRUNITA,I1.ITENVALORUNIT FROM PRODUTO P1,ITENSPEDVENDA I1 WHERE P1.PRODCOD = I1.PRODCOD and I1.VENCOD = "+vencod+"";
    }

    public String getConsultaSqlString() {
       return "";
    }
}

package persistencia;

import IntefaceAca.OperaBanco;

public class Itensproduto implements OperaBanco {

    public int getCompocod() {
        return compocod;
    }

    public void setCompocod(int compocod) {
        this.compocod = compocod;
    }

    public float getItenqtdeprod() {
        return itenqtdeprod;
    }

    public void setItenqtdeprod(float itenqtdeprod) {
        this.itenqtdeprod = itenqtdeprod;
    }

    public double getItenvalorunit() {
        return itenvalorunit;
    }

    public void setItenvalorunit(double itenvalorunit) {
        this.itenvalorunit = itenvalorunit;
    }

    public float getIteprodcod() {
        return iteprodcod;
    }

    public void setIteprodcod(float iteprodcod) {
        this.iteprodcod = iteprodcod;
    }

    public int getProdcod() {
        return prodcod;
    }

    public void setProdcod(int prodcod) {
        this.prodcod = prodcod;
    }

    public int compocod;
    public int prodcod;
    public float iteprodcod;
    public float itenqtdeprod;
    public double itenvalorunit;
    public double itevlrUnita;

    public double getItevlrUnita() {
        return itevlrUnita;
    }

    public void setItevlrUnita(double itevlrUnita) {
        this.itevlrUnita = itevlrUnita;
    }

    public String getInsereSql() {
     return "insert into itensproduto(compocod,prodcod,itenqtdeprod,itenvalorunit,iteprodvlrunita)values("+compocod+","+prodcod+","+itenqtdeprod+","+itenvalorunit+","+itevlrUnita+")";
    }

    public String getAtualizaSql() {
     return "EXECUTE PROCEDURE ALTERAITEMCOMPO("+prodcod+","+compocod+","+itenvalorunit+","+itenqtdeprod+","+itevlrUnita+")";
    }

    public String getExcluiSql() {
     return "delete from itemcomposicao where compocod = "+compocod+" and prodcod = "+prodcod+"";
    }

    public String getConsultaTodos() {
     return "";
    }

    public String getConsultaSqlCod() {
     return "";
    }

    public String getConsultaSqlString() {
    return "SELECT I1.compocod, M1.componome, I1.itenqtdeprod, I1.iteprodvlrunita,I1.itenvalorunit FROM itensproduto I1, composicao M1 WHERE I1.compocod = M1.compocod AND i1.prodcod = "+prodcod+"";
    }
}

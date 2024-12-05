package persistencia;

import IntefaceAca.OperaBanco;

public class Itemcomposicao implements OperaBanco {

    public int getCompocod() {
        return compocod;
    }

    public void setCompocod(int compocod) {
        this.compocod = compocod;
    }

    public int getItcompocod() {
        return itcompocod;
    }

    public void setItcompocod(int itcompocod) {
        this.itcompocod = itcompocod;
    }

    public float getItcompoqtde() {
        return itcompoqtde;
    }

    public void setItcompoqtde(float itcompoqtde) {
        this.itcompoqtde = itcompoqtde;
    }

    public double getItcompovalor() {
        return itcompovalor;
    }

    public void setItcompovalor(double itcompovalor) {
        this.itcompovalor = itcompovalor;
    }

    public int getMatcod() {
        return matcod;
    }

    public void setMatcod(int matcod) {
        this.matcod = matcod;
    }

    public int itcompocod;
    public int compocod;
    public int matcod;
    public float itcompoqtde;
    public double itcompovalor;
    public double itvlrUnit;

    public double getItvlrUnit() {
        return itvlrUnit;
    }

    public void setItvlrUnit(double itvlrUnit) {
        this.itvlrUnit = itvlrUnit;
    }

    public String getInsereSql() {
     return "insert into itemcomposicao(compocod,matcod,itcompoqtde,itcompovalor,itvlrunit)values("+compocod+","+matcod+","+itcompoqtde+","+itcompovalor+","+itvlrUnit+") ";
    }

    public String getAtualizaSql() {

        return "EXECUTE PROCEDURE INSEREITEMCOMPO("+compocod+", "+matcod+", "+itcompovalor+", "+itcompoqtde+")";
    }

    public String getExcluiSql() {
     return "delete from itemcomposicao where matcod = "+matcod+" and compocod = "+compocod+"";
    }

    public String getConsultaTodos() {
     return "";
    }

    public String getConsultaSqlCod() {
     return "select * from itemcomposicao where compocod = "+compocod+"";
    }

    public String getConsultaSqlString() {
     return "SELECT I1.MATCOD, M1.MATNOME, I1.ITCOMPOQTDE, I1.itvlrUnit,I1.ITCOMPOVALOR FROM ITEMCOMPOSICAO I1, MATERIAPRIMA M1 WHERE I1.MATCOD = M1.MATCOD AND i1.COMPOCOD = "+compocod+" ";
    }
}

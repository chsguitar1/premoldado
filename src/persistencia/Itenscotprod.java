package persistencia;

import IntefaceAca.OperaBanco;
import java.sql.ResultSet;
import Banco.Conexao;

public class Itenscotprod implements OperaBanco {

    public int itenscotcod;
    public int matcod;
    public float itendcotqtde;
    public double itenscotunt;
    public int cotcod;
    public double  itenscotsubtotal;
    public int forcod;
    public String fonome;

    public String getFonome() {
        return fonome;
    }

    public void setFonome(String fonome) {
        this.fonome = fonome;
    }

    public int getForcod() {
        return forcod;
    }

    public void setForcod(int forcod) {
        this.forcod = forcod;
    }

    public double getItenscotsubtotal() {
        return itenscotsubtotal;
    }

    public void setItenscotsubtotal(double itenscotsubtotal) {
        this.itenscotsubtotal = itenscotsubtotal;
    }

    public int getCotcod() {
        return cotcod;
    }

    public void setCotcod(int cotcod) {
        this.cotcod = cotcod;
    }

    public float getItendcotqtde() {
        return itendcotqtde;
    }

    public void setItendcotqtde(float itendcotqtde) {
        this.itendcotqtde = itendcotqtde;
    }

    public int getItenscotcod() {
        return itenscotcod;
    }

    public void setItenscotcod(int itenscotcod) {
        this.itenscotcod = itenscotcod;
    }

    public double getItenscotunt() {
        return itenscotunt;
    }

    public void setItenscotunt(double itenscotunt) {
        this.itenscotunt = itenscotunt;
    }

    public int getMatcod() {
        return matcod;
    }

    public void setMatcod(int matcod) {
        this.matcod = matcod;
    }

    public String getInsereSql() {
        return "insert into itenscodprod(matcod,itendcotqtde,cotcod,forcod,itenscotunt,itenscotsubtotal)values(" + matcod + "," + itendcotqtde + "," + cotcod + ","+forcod+","+itenscotunt+","+itenscotsubtotal+")";
    }

    public String getAtualizaSql() {
        return "EXECUTE PROCEDURE INSEREITEMCOTACOES("+cotcod+", "+matcod+", "+itenscotunt+", "+itendcotqtde+","+itenscotsubtotal+","+forcod+")";
    }

    public String getExcluiSql() {
        return "delete from itenscodprod where cotcod =  " + cotcod + " and matcod = " + matcod + "";
    }

    public String getConsultaTodos() {
        return("SELECT M1.MATCOD,M1.MATNOME,I1.ITENDCOTQTDE,I1.ITENSCOTUNT,I1.ITENSCOTSUBTOTAL FROM MATERIAPRIMA M1,ITENSCODPROD I1,COTFORNECEDOR C2 WHERE M1.MATCOD = I1.MATCOD and I1.FORCOD = C2.FORCOD and c2.COTCOD = I1.COTCOD and c2.fonome = '"+fonome+"' and c2.cotcod = "+cotcod+"");
//        return "SELECT distinct I1.MATCOD, M1.MATNOME, I1.ITENDCOTQTDE, I1.ITENSCOTUNT,I1.ITENSCOTSUBTOTAL FROM ITENSCODPROD I1, MATERIAPRIMA M1, COTACOES C1, cotfornecedor c2 "
//                + "WHERE M1.MATCOD = I1.MATCOD AND C1.COTCOD = I1.COTCOD and c1.cotcod = " + cotcod + " and c2.fonome= '"+fonome+"'  ";
    }

    public String getConsultaSqlCod() {
        return "";
    }

    public String getConsultaSqlString() {
        return "";
    }

    public ResultSet getInsereRs() {
        String sql = "insert into itenscodprod(matcod,itendcotqtde,cotcod)values(" + matcod + "," + itendcotqtde + "," + cotcod + ")returning itenscotcod ";
        System.out.println(sql);
        return Conexao.executaQuery(sql);

    }
}

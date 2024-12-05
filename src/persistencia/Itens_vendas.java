package persistencia;

import IntefaceAca.OperaBanco;

public class Itens_vendas implements OperaBanco {

    public int getItevencond() {
        return itevencond;
    }

    public void setItevencond(int itevencond) {
        this.itevencond = itevencond;
    }

    public float getItevenqtde() {
        return itevenqtde;
    }

    public void setItevenqtde(float itevenqtde) {
        this.itevenqtde = itevenqtde;
    }

    public double getItevenunit() {
        return itevenunit;
    }

    public void setItevenunit(double itevenunit) {
        this.itevenunit = itevenunit;
    }

    public int getVencod() {
        return vencod;
    }

    public void setVencod(int vencod) {
        this.vencod = vencod;
    }

    public int itevencond;
    public int vencod;
    public float itevenqtde;
    public double itevenunit;
    public double itevensubtotal;

    public double getItevensubtotal() {
        return itevensubtotal;
    }

    public void setItevensubtotal(double itevensubtotal) {
        this.itevensubtotal = itevensubtotal;
    }

    public String getInsereSql() {
      return "insert into itens_venda(vencod,itevenqtde,itevenunit,itevensubtotal)values("+vencod+","+itevenqtde+","+itevenunit+","+itevensubtotal+")";
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

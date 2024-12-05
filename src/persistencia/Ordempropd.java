package persistencia;

import IntefaceAca.OperaBanco;
import java.sql.ResultSet;
import Banco.Conexao;

public class Ordempropd implements OperaBanco  {

    public int ordstatus;
    public int odcod;
    public String orddatafim;
    public String orddataini;

    public int getOdcod() {
        return odcod;
    }

    public void setOdcod(int odcod) {
        this.odcod = odcod;
    }

    public String getOrddatafim() {
        return orddatafim;
    }

    public void setOrddatafim(String orddatafim) {
        this.orddatafim = orddatafim;
    }

    public String getOrddataini() {
        return orddataini;
    }

    public void setOrddataini(String orddataini) {
        this.orddataini = orddataini;
    }

    public int getOrdstatus() {
        return ordstatus;
    }

    public void setOrdstatus(int ordstatus) {
        this.ordstatus = ordstatus;
    }

    public String getInsereSql() {
      return "";
    }

    public String getAtualizaSql() {
      return "";
    }

    public String getExcluiSql() {
      return "";
    }

    public String getConsultaTodos() {
      return "select * from ordempropd where odcod = "+odcod+"";
    }

    public String getConsultaSqlCod() {
      return "";
    }

    public String getConsultaSqlString() {
      return "";
    }
     public ResultSet getInsereRs() {
        String sql = "insert into  ORDEMPROPD(orddataini,ordstatus)values('"+orddataini+"',"+ordstatus+")returning odcod";
       System.out.println(sql);
        return Conexao.executaQuery(sql);

    }
     public boolean verificaDuplicidadeAtualiza(){
     return true;
     }
}

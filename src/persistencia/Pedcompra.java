package persistencia;

import IntefaceAca.OperaBanco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import Banco.Conexao;

public class Pedcompra implements OperaBanco {

    public int pecomcod;
    public int forcod;
    public int pedcomstatus;
    public String pecomdata;
    public double pedcomtotal;
    public String pedobs;
    public String peddatafim;
    private Statement stmt;
    private ResultSet rs;

    public int getForcod() {
        return forcod;
    }

    public void setForcod(int forcod) {
        this.forcod = forcod;
    }

    public int getPecomcod() {
        return pecomcod;
    }

    public void setPecomcod(int pecomcod) {
        this.pecomcod = pecomcod;
    }

    public String getPecomdata() {
        return pecomdata;
    }

    public void setPecomdata(String pecomdata) {
        this.pecomdata = pecomdata;
    }

    public int getPedcomstatus() {
        return pedcomstatus;
    }

    public void setPedcomstatus(int pedcomstatus) {
        this.pedcomstatus = pedcomstatus;
    }

    public double getPedcomtotal() {
        return pedcomtotal;
    }

    public void setPedcomtotal(double pedcomtotal) {
        this.pedcomtotal = pedcomtotal;
    }

    public String getPeddatafim() {
        return peddatafim;
    }

    public void setPeddatafim(String peddatafim) {
        this.peddatafim = peddatafim;
    }

    public String getPedobs() {
        return pedobs;
    }

    public void setPedobs(String pedobs) {
        this.pedobs = pedobs;
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
        return "SELECT P1.*,F1.FONOME FROM PEDCOMPRA P1,FORNECEDOR F1 WHERE P1.FORCOD = F1.FORCOD and p1.pecomcod = "+pecomcod+"";
    }

    public String getConsultaSqlCod() {
        return "";
    }

    public String getConsultaSqlString() {
        return "";
    }

    public ResultSet getInsereRs() {
        String sql = "insert into pedcompra(forcod,pecomdata ,peddatafim,pedcomtotal,pedcompobs,pedcomstatus)values(" + forcod + ",'" + pecomdata + "','" + peddatafim + "'," + pedcomtotal + ",'" + pedobs + "'," + pedcomstatus + ")returning pecomcod";
        System.out.println(sql);
        return Conexao.executaQuery(sql);

    }

    public boolean verificaDuplicidade() {
        try {
            int count = 0;
            stmt = Conexao.getConexao().createStatement();
            rs = stmt.executeQuery("select count(forcod) from pedcompra where forcod = " + forcod + " and  pecomdata = '" + pecomdata + "' and pedcomstatus = " + pedcomstatus + " and peddatafim = '" + peddatafim + "'");

            while (rs.next()) {
                count = rs.getInt(1);
            }

            if (count > 0) {
                JOptionPane.showMessageDialog(null, "Existe Pedido  com o mesmos valores cadastradados");
                return false;
            } else {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro de Conexão com o Banco de Dados.");
        }
        return false;
    }
}

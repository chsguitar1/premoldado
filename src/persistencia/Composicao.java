package persistencia;

import IntefaceAca.OperaBanco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import Banco.Conexao;

public class Composicao implements OperaBanco{
    private Statement stmt;
    private ResultSet rs;

    public int getCompocod() {
        return compocod;
    }

    public void setCompocod(int compocod) {
        this.compocod = compocod;
    }

    public String getComponome() {
        return componome;
    }

    public void setComponome(String componome) {
        this.componome = componome;
    }

    public int getCompostatus() {
        return compostatus;
    }

    public void setCompostatus(int compostatus) {
        this.compostatus = compostatus;
    }

    public double getCompovlrtotal() {
        return compovlrtotal;
    }

    public void setCompovlrtotal(double compovlrtotal) {
        this.compovlrtotal = compovlrtotal;
    }

    public int compocod;
    public int compostatus;
    public double compovlrtotal;
    public String componome;

    public String getInsereSql() {
       return "";
    }

    public String getAtualizaSql() {
       return "update composicao set compostatus ="+compostatus+" ,compovlrtotal = "+compovlrtotal+",componome = '"+componome+"' where compocod = "+compocod+" ";
    }

    public String getExcluiSql() {
       return " delete from composicao where compocod = "+compocod+"";
    }

    public String getConsultaTodos() {
       return "select * from composicao where compocod = "+compocod+"";
    }

    public String getConsultaSqlCod() {
       return "";
    }

    public String getConsultaSqlString() {
       return "";
    }
      public ResultSet getInsereRs() {
        String sql = "insert into composicao(compostatus,compovlrtotal,componome)values("+compostatus+","+compovlrtotal+",'"+componome+"')returning compocod";
       System.out.println(sql);
        return Conexao.executaQuery(sql);

    }
       public boolean verificaDuplicidade() {
        try {
            int count = 0;
            stmt = Conexao.getConexao().createStatement();
            rs = stmt.executeQuery("select count (componome) from composicao where componome = '" + componome + "'");

            while (rs.next()) {
                count = rs.getInt(1);
            }

            if (count > 0) {
                JOptionPane.showMessageDialog(null, "Existe uma composição com o mesmo nome cadastrada");
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
       public boolean verificaDuplicidadeAtualiza() {
        try {
            int count = 0;
            stmt = Conexao.getConexao().createStatement();
            rs = stmt.executeQuery("select count (componome) from composicao where componome = '" + componome + "' and  compocod != "+compocod+"");

            while (rs.next()) {
                count = rs.getInt(1);
            }

            if (count > 0) {
                JOptionPane.showMessageDialog(null, "Existe uma composiçao com o mesmo nome cadastrada");
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

package persistencia;

import IntefaceAca.OperaBanco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import Banco.Conexao;

public class Produto implements OperaBanco {
    private Statement stmt;
    private ResultSet rs;

    public int getProdcod() {
        return prodcod;
    }

    public void setProdcod(int prodcod) {
        this.prodcod = prodcod;
    }

    public String getProdnome() {
        return prodnome;
    }

    public void setProdnome(String prodnome) {
        this.prodnome = prodnome;
    }

    public int getProdstatus() {
        return prodstatus;
    }

    public void setProdstatus(int prodstatus) {
        this.prodstatus = prodstatus;
    }

    public double getProdvalor() {
        return prodvalor;
    }

    public void setProdvalor(double prodvalor) {
        this.prodvalor = prodvalor;
    }

    public int prodcod;
    public int prodstatus;
    public double prodvalor;
    public String prodnome;

    public String getInsereSql() {
       return "";
    }

    public String getAtualizaSql() {
       return "update produto set prodstatus = "+prodstatus+", prodvalor = "+prodvalor+",prodnome = '"+prodnome+"' where prodcod = "+prodcod+" ";
    }

    public String getExcluiSql() {
       return "delete from produto where prodcod = "+prodcod+"";
    }

    public String getConsultaTodos() {
       return "select * from produto where prodcod = "+prodcod+"";
    }

    public String getConsultaSqlCod() {
       return "";
    }

    public String getConsultaSqlString() {
       return "";
    }
     public ResultSet getInsereRs() {
        String sql = "insert into produto(prodstatus, prodvalor,prodnome)values("+prodstatus+","+prodvalor+",'"+prodnome+"')returning prodcod";
       System.out.println(sql);
        return Conexao.executaQuery(sql);

    }
       public boolean verificaDuplicidade() {
        try {
            int count = 0;
            stmt = Conexao.getConexao().createStatement();
            rs = stmt.executeQuery("select count (prodnome) from produto where prodnome = '" + prodnome + "'");

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
            rs = stmt.executeQuery("select count (prodnome) from produto where prodnome = '" + prodnome + "' and  prodcod != "+prodcod+"");

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

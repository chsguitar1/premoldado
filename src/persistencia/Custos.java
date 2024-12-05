package persistencia;

import IntefaceAca.OperaBanco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import Banco.Conexao;

public class Custos implements OperaBanco {
    private Statement stmt;
    private ResultSet rs;

    public int getCustocod() {
        return custocod;
    }

    public void setCustocod(int custocod) {
        this.custocod = custocod;
    }

    public float getCustoperce() {
        return custoperce;
    }

    public void setCustoperce(float custoperce) {
        this.custoperce = custoperce;
    }

    public String getCustotipo() {
        return custotipo;
    }

    public void setCustotipo(String custotipo) {
        this.custotipo = custotipo;
    }

    public int custocod;
    public float custoperce;
    public String custotipo;

    public String getInsereSql() {
     return "insert into custos(custotipo,custoperce)values('"+custotipo+"',"+custoperce+")";
    }

    public String getAtualizaSql() {
       return "update custos set custotipo = '"+custotipo+"', custoperce = "+custoperce+" where custocod = "+custocod+"";
    }

    public String getExcluiSql() {
        return "delete from custos where custocod = "+custocod+"";
    }

    public String getConsultaTodos() {
       return "select * from custos where custocod = "+custocod+" ";
    }

    public String getConsultaSqlCod() {
     return "";
    }

    public String getConsultaSqlString() {
       return "";
    }
     public boolean verificaDuplicidade() {
        try {
            int count = 0;
            stmt = Conexao.getConexao().createStatement();
            rs = stmt.executeQuery("select count (custotipo) from custos where custotipo = '" +custotipo+ "'");

            while (rs.next()) {
               count = rs.getInt(1);
            }

            if (count > 0) {
                JOptionPane.showMessageDialog(null, "Cadastro de custo existente");
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
            rs = stmt.executeQuery("select count (custotipo) from custos where custotipo = '" +custotipo+ "'and custoperce = "+custoperce+"");

            while (rs.next()) {
               count = rs.getInt(1);
            }

            if (count > 0) {
                JOptionPane.showMessageDialog(null, "Cadastro de custo existente");
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

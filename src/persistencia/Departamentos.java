package persistencia;

import IntefaceAca.OperaBanco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import Banco.Conexao;

public class Departamentos implements OperaBanco{
    private Statement stmt;
    private ResultSet rs;

    public int getDepcod() {
        return depcod;
    }

    public void setDepcod(int depcod) {
        this.depcod = depcod;
    }

    public String getDepnome() {
        return depnome;
    }

    public void setDepnome(String depnome) {
        this.depnome = depnome;
    }

    public int getDepstatus() {
        return depstatus;
    }

    public void setDepstatus(int depstatus) {
        this.depstatus = depstatus;
    }

    public int depcod;
    public int depstatus;
    public String depnome;

       public String getInsereSql() {
        return "insert into departamentos(depnome,depstatus) values('"+ depnome +"',"+depstatus+")";

    }

    public String getAtualizaSql() {
       return "update departamentos set  depnome = '" + depnome + "', depstatus = " + depstatus + " WHERE depcod = " + depcod;
    }

    public String getExcluiSql() {
        return "delete from departamentos where depcod = "+depcod;
    }

    public String getConsultaTodos() {
       return "select * from departamentos where depcod = "+depcod+"";
    }

    public String getConsultaSqlCod() {
       return "select depcod,depnome from departamentos where depcod ="+depcod+"";

    }

    public String getConsultaSqlString() {
     return "";
    }
  public boolean verificaDuplicidade() {
        try {
            int count = 0;
            stmt = Conexao.getConexao().createStatement();
            rs = stmt.executeQuery("select count (depnome) from departamentos where depnome = '" + depnome + "' and depstatus = " + depstatus + "");

            while (rs.next()) {
               count = rs.getInt(1);
            }

            if (count > 0) {
                JOptionPane.showMessageDialog(null, "Cdadastro de departamentos já existe");
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
          public boolean verificaDuplicidadeAtualizar() {
        try {
            int count = 0;
            stmt = Conexao.getConexao().createStatement();
            rs = stmt.executeQuery("select count (depnome) from departamentos where depnome = '" + depnome + "' and depstatus = " + depstatus + " and depcod != "+depcod+"");

            while (rs.next()) {
               count = rs.getInt(1);
            }

            if (count > 0) {
                JOptionPane.showMessageDialog(null, "Cadastro de departamentos ja existe");
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

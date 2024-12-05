package persistencia;

import IntefaceAca.OperaBanco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import Banco.Conexao;

public class Colaboradores implements OperaBanco{
    private Statement stmt;
    private ResultSet rs;

    public int getColcod() {
        return colcod;
    }

    public void setColcod(int colcod) {
        this.colcod = colcod;
    }

    public String getColnome() {
        return colnome;
    }

    public void setColnome(String colnome) {
        this.colnome = colnome;
    }

    public int getColstatus() {
        return colstatus;
    }

    public void setColstatus(int colstatus) {
        this.colstatus = colstatus;
    }

    public int getDepcod() {
        return depcod;
    }

    public void setDepcod(int depcod) {
        this.depcod = depcod;
    }

    public int getFunccod() {
        return funccod;
    }

    public void setFunccod(int funccod) {
        this.funccod = funccod;
    }

    public int colcod;
    public int depcod;
    public int funccod;
    public int colstatus;
    public String colnome;

     public String getInsereSql() {
        return "insert into colaboradores(colnome,colstatus,funccod,depcod) values('"+ colnome +"',"+colstatus+","+funccod+","+depcod+")";

    }

    public String getAtualizaSql() {
       return "update colaboradores set  colnome = '" + colnome + "', colstatus = " + colstatus + ",funccod = "+funccod+",depcod  WHERE depcod = " + depcod;
    }

    public String getExcluiSql() {
        return "delete from colaboradores where colcod = "+colcod;
    }

    public String getConsultaTodos() {
       return "select * from colaboradores where colcod = "+colcod+"";
    }

    public String getConsultaSqlCod() {
       return "select depcod,colnome from colaboradores where colcod ="+colcod+"";

    }

    public String getConsultaSqlString() {
     return "";
    }
  public boolean verificaDuplicidade() {
        try {
            int count = 0;
            stmt = Conexao.getConexao().createStatement();
            rs = stmt.executeQuery("select count (colnome) from colaboradores where colnome = '" + colnome + "' and depcod = " + depcod + "");

            while (rs.next()) {
               count = rs.getInt(1);
            }

            if (count > 0) {
                JOptionPane.showMessageDialog(null, "Cdadastro de colaboradores já existe");
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
            rs = stmt.executeQuery("select count (colnome) from colaboradores where colnome = '" + colnome + "' and depcod = " + depcod+ " and colcod != "+colcod+"");

            while (rs.next()) {
               count = rs.getInt(1);
            }

            if (count > 0) {
                JOptionPane.showMessageDialog(null, "Cadastro de colaboradores ja existe");
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

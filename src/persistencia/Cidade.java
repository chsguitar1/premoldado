package persistencia;

import IntefaceAca.OperaBanco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import Banco.Conexao;

public class Cidade implements OperaBanco {
private Statement stmt;
private ResultSet rs;
    public int getCidcod() {
        return cidcod;
    }

    public void setCidcod(int cidcod) {
        this.cidcod = cidcod;
    }

    public String getCidnome() {
        return cidnome;
    }

    public void setCidnome(String cidnome) {
        this.cidnome = cidnome;
    }

    public int getEstcod() {
        return estcod;
    }

    public void setEstcod(int estcod) {
        this.estcod = estcod;
    }

    public int cidcod;
    public int estcod;
    public String cidnome;
    public int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public String getInsereSql() {
    return "insert into cidade(cidnome,estcod,status)values('"+cidnome+"',"+estcod+","+status+")";
    }

    public String getAtualizaSql() {
      return "update cidade set cidnome = '"+cidnome+"',estcod = "+estcod+", status = "+status+" where cidcod = "+cidcod+"";
    }

    public String getExcluiSql() {
     return "delete from cidade where cidcod = "+cidcod+"";
    }

    public String getConsultaTodos() {
      return "select * from cidade where cidcod = "+cidcod+"";
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
            rs = stmt.executeQuery("select count (cidnome) from cidade where cidnome = '" + cidnome + "' and estcod = " + estcod + "");

            while (rs.next()) {
               count = rs.getInt(1);
            }

            if (count > 0) {
                JOptionPane.showMessageDialog(null, "Cdadastro de Cidade já existe");
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
            rs = stmt.executeQuery("select count (cidnome) from CIDADE where cidnome = '" + cidnome + "' and estcod = " + estcod + " and cidcod != cidcod");

            while (rs.next()) {
               count = rs.getInt(1);
            }

            if (count > 0) {
                JOptionPane.showMessageDialog(null, "Cadastro de cidade ja existe");
                return false;
            } else {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro de Banco de Dados.");
        }
        return false;
    }
}

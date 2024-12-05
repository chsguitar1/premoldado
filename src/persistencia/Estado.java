package persistencia;

import IntefaceAca.OperaBanco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import Banco.Conexao;

public class Estado implements OperaBanco {

    private Statement stmt;
    private ResultSet rs;

    public int getEstcod() {
        return estcod;
    }

    public void setEstcod(int estcod) {
        this.estcod = estcod;
    }

    public String getEstnome() {
        return estnome;
    }

    public void setEstnome(String estnome) {
        this.estnome = estnome;
    }

    public String getEstsigla() {
        return estsigla;
    }

    public void setEstsigla(String estsigla) {
        this.estsigla = estsigla;
    }
    public int estcod;
    public String estsigla;
    public String estnome;

    public String getInsereSql() {
        return "insert into estado(estnome,estsigla) values('"+ estnome +"','"+estsigla+"')";

    }

    public String getAtualizaSql() {
       return "update estado set  estnome = '" + estnome + "', estsigla = '" + estsigla + "' WHERE estcod = " + estcod;
    }

    public String getExcluiSql() {
        return "delete from estado where estcod = "+estcod;
    }

    public String getConsultaTodos() {
       return "select * from estado where estcod = "+estcod+"";
    }

    public String getConsultaSqlCod() {
       return "select estcod,estnome from estado where estcod ="+estcod+"";
       
    }

    public String getConsultaSqlString() {
     return "";
    }

   
     public boolean verificaDuplicidade() {
        try {
            int count = 0;
            stmt = Conexao.getConexao().createStatement();
            rs = stmt.executeQuery("select count (estnome) from ESTADO where estnome = '" + estnome + "' and estsigla = '" + estsigla + "'");

            while (rs.next()) {
               count = rs.getInt(1);
            }

            if (count > 0) {
                JOptionPane.showMessageDialog(null, "Cdadastro de estado já existe");
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
            rs = stmt.executeQuery("select count (estnome) from ESTADO where estnome = '" + estnome + "' and estsigla = '" + estsigla + "' and estcod != "+estcod+"");

            while (rs.next()) {
               count = rs.getInt(1);
            }

            if (count > 0) {
                JOptionPane.showMessageDialog(null, "Cadastro de estado ja existe");
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

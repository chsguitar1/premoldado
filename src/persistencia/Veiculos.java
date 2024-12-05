package persistencia;

import IntefaceAca.OperaBanco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import Banco.Conexao;

public class Veiculos implements OperaBanco {
    private Statement stmt;
    private ResultSet rs;

    public int getCidcod() {
        return cidcod;
    }

    public void setCidcod(int cidcod) {
        this.cidcod = cidcod;
    }

    public String getVeiano() {
        return veiano;
    }

    public void setVeiano(String veiano) {
        this.veiano = veiano;
    }

    public int getVeicod() {
        return veicod;
    }

    public void setVeicod(int veicod) {
        this.veicod = veicod;
    }

    public String getVeicor() {
        return veicor;
    }

    public void setVeicor(String veicor) {
        this.veicor = veicor;
    }

    public String getVeikilometro() {
        return veikilometro;
    }

    public void setVeikilometro(String veikilometro) {
        this.veikilometro = veikilometro;
    }

    public String getVeimodelo() {
        return veimodelo;
    }

    public void setVeimodelo(String veimodelo) {
        this.veimodelo = veimodelo;
    }

    public String getVeiplaca() {
        return veiplaca;
    }

    public void setVeiplaca(String veiplaca) {
        this.veiplaca = veiplaca;
    }

    public int getVeistatus() {
        return veistatus;
    }

    public void setVeistatus(int veistatus) {
        this.veistatus = veistatus;
    }

    public int cidcod;
    public int veistatus;
    public int veicod;
    public String veimodelo;
    public String veicor;
    public String veiano;
    public String veiplaca;
    public String veikilometro;

    public String getInsereSql() {
      return "insert into veiculos(veimodelo,veicor,veiano,veiplaca,veikilometro,veistatus,cidcod)values('"+veimodelo+"','"+veicor+"','"+veiano+"','"+veiplaca+"','"+veikilometro+"',"+veistatus+","+cidcod+")";
    }

    public String getAtualizaSql() {
      return "update veiculos set veimodelo = '"+veimodelo+"',veicor = '"+veicor+"',veiano = '"+veiano+"' ,veiplaca = '"+veiplaca+"',veikilometro = '"+veikilometro+"',veistatus = "+veistatus+",cidcod = "+cidcod+" where veicod = "+veicod+"";
    }

    public String getExcluiSql() {
      return "delete from veiculos where veicod = "+veicod+"";
    }

    public String getConsultaTodos() {
      return "select * from veiculos where veicod = "+veicod+"";
    }

    public String getConsultaSqlCod() {
      return "";
    }

    public String getConsultaSqlString() {
      return "";
    }
    public String getProcedureDel(){
        return "execute procedure inativastatus("+veicod+",'veiculos','veicod','veistatus')";

    }
     public boolean verificaDuplicidade() {
        try {
            int count = 0;
            stmt = Conexao.getConexao().createStatement();
            rs = stmt.executeQuery("select count (veimodelo) from veiculos where veimodelo = '" + veimodelo + "' and veiplaca =' " + veiplaca + "'");

            while (rs.next()) {
               count = rs.getInt(1);
            }

            if (count > 0) {
                JOptionPane.showMessageDialog(null, "Cdadastro de Veiculos já existe");
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
            rs = stmt.executeQuery("select count (veimodelo) from veiculos where veimodelo = '" + veimodelo + "' and veiplaca = '" + veiplaca + "' and cidcod != "+cidcod+"");

            while (rs.next()) {
               count = rs.getInt(1);
            }

            if (count > 0) {
                JOptionPane.showMessageDialog(null, "Cadastro de veiculo ja existe");
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

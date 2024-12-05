package persistencia;

import IntefaceAca.OperaBanco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import Banco.Conexao;

public class Itenspontuacao implements OperaBanco {

    private Statement stmt;
    private ResultSet rs;

    public String getItenponnome() {
        return itenponnome;
    }

    public void setItenponnome(String itenponnome) {
        this.itenponnome = itenponnome;
    }

    public int getIteponcod() {
        return iteponcod;
    }

    public void setIteponcod(int iteponcod) {
        this.iteponcod = iteponcod;
    }

    public int getPoncod() {
        return poncod;
    }

    public void setPoncod(int poncod) {
        this.poncod = poncod;
    }
    public int iteponcod;
    public int poncod;
    public String itenponnome;

    public String getInsereSql() {
        return "insert into itenspontuacao(poncod,itenponnome)values(" + poncod + ",'" + itenponnome + "') ";
    }

    public String getAtualizaSql() {
        return "update itenspontuacao set itenponnome = '"+itenponnome+"' where iteponcod = "+iteponcod+"";
    }

    public String getExcluiSql() {
        return "";
    }

    public String getConsultaTodos() {
        return "";
    }

    public String getConsultaSqlCod() {
        return "select iteponcod from itenspontuacao where poncod = "+poncod+"";
    }

    public String getConsultaSqlString() {
        return "select iteponcod,itenponnome from itenspontuacao where poncod = "+poncod+"";
    }

    public boolean verificaDuplicidade() {
        try {
            int count = 0;
            stmt = Conexao.getConexao().createStatement();
            rs = stmt.executeQuery("select count (itenponnome) from itenspontuacao where itenponnome = '" + itenponnome + "'");

            while (rs.next()) {
                count = rs.getInt(1);
                System.out.println();rs.getInt(count);
            }

            if (count > 0) {
                JOptionPane.showMessageDialog(null, "Existe um item pontuaçao com o mesmo nome cadastrada");
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

package persistencia;

import IntefaceAca.OperaBanco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import Banco.Conexao;

public class Pontuacao implements OperaBanco {

    private Statement stmt;
    private ResultSet rs;

    public int getPoncod() {
        return poncod;
    }

    public void setPoncod(int poncod) {
        this.poncod = poncod;
    }

    public String getPonnome() {
        return ponnome;
    }

    public void setPonnome(String ponnome) {
        this.ponnome = ponnome;
    }
    public int poncod;
    public String ponnome;

    public String getInsereSql() {
        return "";
    }

    public String getAtualizaSql() {
        return "update pontuacao set ponnome = '"+ponnome+"' where poncod = "+poncod+"";
    }

    public String getExcluiSql() {
        return "delete from pontuacao where poncod = "+poncod+"";
    }

    public String getConsultaTodos() {
        return "select * from pontuacao where poncod = "+poncod+"";
    }

    public String getConsultaSqlCod() {
        return "";
    }

    public String getConsultaSqlString() {
        return "";
    }

    public ResultSet getInsereRs() {
        String sql = "insert into pontuacao(ponnome)values('" + ponnome + "')returning poncod";
        return Conexao.executaQuery(sql);

    }
public ResultSet getAtualizaRs(){
    String sql = "update pontuacao set ponnome = '"+ponnome+"' where poncod = "+poncod+"";
    return  Conexao.executaQuery(sql);
}
    public boolean verificaDuplicidade() {
        try {
            int count = 0;
            stmt = Conexao.getConexao().createStatement();
            rs = stmt.executeQuery("select count (ponnome) from pontuacao where ponnome = '" + ponnome + "'");

            while (rs.next()) {
                count = rs.getInt(1);
            }

            if (count > 0) {
                JOptionPane.showMessageDialog(null, "Existe uma pontuaçao com o mesmo nome cadastrada");
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

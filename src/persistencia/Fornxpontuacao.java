package persistencia;

import IntefaceAca.OperaBanco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import Banco.Conexao;

public class Fornxpontuacao implements OperaBanco {

    private Statement stmt;
    private ResultSet rs;

    public int getForcod() {
        return forcod;
    }

    public void setForcod(int forcod) {
        this.forcod = forcod;
    }

    public int getForxcod() {
        return forxcod;
    }

    public void setForxcod(int forxcod) {
        this.forxcod = forxcod;
    }

    public float getForxpeso() {
        return forxpeso;
    }

    public void setForxpeso(float forxpeso) {
        this.forxpeso = forxpeso;
    }

    public int getPoncod() {
        return poncod;
    }

    public void setPoncod(int poncod) {
        this.poncod = poncod;
    }
    public int forxcod;
    public int forcod;
    public int poncod;
    public float forxpeso;

    public String getInsereSql() {
        return "";
    }

    public String getAtualizaSql() {
        return "update fornxpontuacao set forxpeso = "+forxpeso+" where forxcod = "+forxcod+" and forcod = "+forcod+" and poncod = "+poncod+" ";
    }

    public String getExcluiSql() {
        return "delete from fornxpontuacao where forxcod = "+forxcod+" ";
    }

    public String getConsultaTodos() {
        return "select * from fornxpontuacao where forcod = "+forcod+"";
    }

    public String getConsultaSqlCod() {
        return "";
    }

    public String getConsultaSqlString() {
        return "";
    }

    public ResultSet getInsereRs() {
        String sql = "insert into fornxpontuacao(forcod,poncod,forxpeso)values(" + forcod + "," + poncod + "," + forxpeso + ")returning forxcod";
        return Conexao.executaQuery(sql);

    }

    public boolean verificaDuplicidade() {
        try {
            int count = 0;
            stmt = Conexao.getConexao().createStatement();
            rs = stmt.executeQuery("select count (forcod) from fornxpontuacao where forcod = " + forcod + " and poncod = " + poncod + "");

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
        public boolean verificaDuplicidadeAtualiza() {
        try {
            int count = 0;
            stmt = Conexao.getConexao().createStatement();
            rs = stmt.executeQuery("select count (forcod) from fornxpontuacao where forcod = " + forcod + " and poncod = " + poncod + " and forxcod != forxcod");

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

package persistencia;

import IntefaceAca.OperaBanco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import Banco.Conexao;

public class Unidademat implements OperaBanco {
    private Statement stmt;
    private ResultSet rs;

    public int getUnicod() {
        return unicod;
    }

    public void setUnicod(int unicod) {
        this.unicod = unicod;
    }

    public String getUnitipo() {
        return unitipo;
    }

    public void setUnitipo(String unitipo) {
        this.unitipo = unitipo;
    }

    public int unicod;
    public String unitipo;

    public String getInsereSql() {
     return"insert into unidademat(unitipo)values('"+unitipo+"')";
    }

    public String getAtualizaSql() {
      return"update unidademat set unitipo = '"+unitipo+"' where unicod = "+unicod+"";
    }

    public String getExcluiSql() {
        return"delete from unidademat where unicod = "+unicod+"";
    }

    public String getConsultaTodos() {
      return"select * from unidademat where unicod = "+unicod+"";
    }

    public String getConsultaSqlCod() {
       return"";
    }

    public String getConsultaSqlString() {
         return"";
    }
       public boolean verificaDuplicidade() {
        try {
            int count = 0;
            stmt = Conexao.getConexao().createStatement();
            rs = stmt.executeQuery("select count(unitipo) from unidademat where unitipo = '" + unitipo + "' ");

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

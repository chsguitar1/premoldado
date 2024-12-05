package persistencia;

import IntefaceAca.OperaBanco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import Banco.Conexao;

public class Tipomateria implements  OperaBanco {
    private Statement stmt;
    private ResultSet rs;

    public int getTipomatcod() {
        return tipomatcod;
    }

    public void setTipomatcod(int tipomatcod) {
        this.tipomatcod = tipomatcod;
    }

    public String getTipomatfor() {
        return tipomatfor;
    }

    public void setTipomatfor(String tipomatfor) {
        this.tipomatfor = tipomatfor;
    }

    public int tipomatcod;
    public String tipomatfor;

       public String getInsereSql() {
        return "insert into tipomateria(tipomatfor) values('"+ tipomatfor +"')";

    }

    public String getAtualizaSql() {
       return "update tipomateria set  tipomatfor = '" + tipomatfor + "'  WHERE tipomatcod = " + tipomatcod;
    }

    public String getExcluiSql() {
        return "delete from tipomateria where tipomatcod = "+tipomatcod;
    }

    public String getConsultaTodos() {
       return "select * from tipomateria where tipomatcod = "+tipomatcod+"";
    }

    public String getConsultaSqlCod() {
       return "select tipomatcod,tipomatfor from tipomateria where tipomatcod ="+tipomatcod+"";

    }

    public String getConsultaSqlString() {
     return "";
    }

    public boolean verificaDuplicidade() {
        try {
            int count = 0;
            stmt = Conexao.getConexao().createStatement();
            rs = stmt.executeQuery("select count (tipomatfor) from tipomateria where tipomatfor = '" +tipomatfor+ "' ");

            while (rs.next()) {
               count = rs.getInt(1);
            }

            if (count > 0) {
                JOptionPane.showMessageDialog(null, "Cdadastro de Tipo de Material  já existe");
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
            rs = stmt.executeQuery("select count (tipomatfor) from tipomateria where tipomatfor = '" + tipomatfor + "' and tipomatcod != "+tipomatcod+"");

            while (rs.next()) {
               count = rs.getInt(1);
            }

            if (count > 0) {
                JOptionPane.showMessageDialog(null, "Cadastro de tipomateria ja existe");
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

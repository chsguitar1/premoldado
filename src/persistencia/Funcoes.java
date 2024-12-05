package persistencia;

import IntefaceAca.OperaBanco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import Banco.Conexao;

public class Funcoes implements OperaBanco {
    private Statement stmt;
    private ResultSet rs;

    public int getFunccod() {
        return funccod;
    }

    public void setFunccod(int funccod) {
        this.funccod = funccod;
    }

    public String getFuncnome() {
        return funcnome;
    }

    public void setFuncnome(String funcnome) {
        this.funcnome = funcnome;
    }

    public int getFuncstatus() {
        return funcstatus;
    }

    public void setFuncstatus(int funcstatus) {
        this.funcstatus = funcstatus;
    }

    public int funccod;
    public int funcstatus;
    public String funcnome;

     public String getInsereSql() {
        return "insert into funcoes(funcnome,funcstatus) values('"+ funcnome +"',"+funcstatus+")";

    }

    public String getAtualizaSql() {
       return "update funcoes set  funcnome = '" + funcnome + "', funcstatus = " + funcstatus + " WHERE funccod = " + funccod;
    }

    public String getExcluiSql() {
        return "delete from funcoes where funccod = "+funccod;
    }

    public String getConsultaTodos() {
       return "select * from funcoes where funccod = "+funccod+"";
    }

    public String getConsultaSqlCod() {
       return "select funccod,funcnome from funcoes where funccod ="+funccod+"";

    }

    public String getConsultaSqlString() {
     return "";
    }
  public boolean verificaDuplicidade() {
        try {
            int count = 0;
            stmt = Conexao.getConexao().createStatement();
            rs = stmt.executeQuery("select count (funcnome) from funcoes where funcnome = '" + funcnome + "' and funcstatus = " + funcstatus + "");

            while (rs.next()) {
               count = rs.getInt(1);
            }

            if (count > 0) {
                JOptionPane.showMessageDialog(null, "Cdadastro de funcoes já existe");
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
            rs = stmt.executeQuery("select count (funcnome) from funcoes where funcnome = '" + funcnome + "' and funcstatus = " + funcstatus + " and funccod != "+funccod+"");

            while (rs.next()) {
               count = rs.getInt(1);
            }

            if (count > 0) {
                JOptionPane.showMessageDialog(null, "Cadastro de funcoes ja existe");
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

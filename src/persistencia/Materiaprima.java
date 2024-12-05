package persistencia;

import IntefaceAca.OperaBanco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import Banco.Conexao;

public class Materiaprima implements OperaBanco {
    private Statement stmt;
    private ResultSet rs;

    public int getCustocod() {
        return custocod;
    }

    public void setCustocod(int custocod) {
        this.custocod = custocod;
    }

    public int getMatcod() {
        return matcod;
    }

    public void setMatcod(int matcod) {
        this.matcod = matcod;
    }

    public String getMatnome() {
        return matnome;
    }

    public void setMatnome(String matnome) {
        this.matnome = matnome;
    }

    public float getMatqtde() {
        return matqtde;
    }

    public void setMatqtde(float matqtde) {
        this.matqtde = matqtde;
    }

    public int getMatstatus() {
        return matstatus;
    }

    public void setMatstatus(int matstatus) {
        this.matstatus = matstatus;
    }

    public double getMatvalor() {
        return matvalor;
    }

    public void setMatvalor(double matvalor) {
        this.matvalor = matvalor;
    }

    public int getUnicod() {
        return unicod;
    }

    public void setUnicod(int unicod) {
        this.unicod = unicod;
    }

   

    public int matcod;
    public int unicod;
    public int custocod;
    public int matstatus;
    public float matqtde;
    public double matvalor;
    public String matnome;
   

    public String getInsereSql() {
        return "insert into materiaprima(unicod,custocod,matstatus,matqtde,matvalor,matnome)values("+unicod+","+custocod+","+matstatus+","+matqtde+","+matvalor+",'"+matnome+"')";
    }

    public String getAtualizaSql() {
        return "update materiaprima set unicod ="+unicod+" ,custocod ="+custocod+" ,matstatus = "+matstatus+",matqtde = "+matqtde+",matvalor = "+matvalor+",matnome ='"+matnome+"'  where matcod = "+matcod+"";
    }

    public String getExcluiSql() {
        return "delete from materiaprima where matcod = "+matcod+"";
    }

    public String getConsultaTodos() {
        return "select * from materiaprima where matcod = "+matcod+"";
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
            rs = stmt.executeQuery("select count (matnome) from materiaprima where matnome = '" + matnome + "' and unicod = " + unicod + "");

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
            rs = stmt.executeQuery("select count (matnome) from materiaprima where matnome = '" + matnome + "' and unicod = '" + unicod + "' and matcod != "+matcod+"");

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

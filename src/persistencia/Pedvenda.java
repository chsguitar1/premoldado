package persistencia;

import IntefaceAca.OperaBanco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import Banco.Conexao;

public class Pedvenda implements OperaBanco {
    private Statement stmt;
    private ResultSet rs;

    public int getClicod() {
        return clicod;
    }

    public void setClicod(int clicod) {
        this.clicod = clicod;
    }

    public int getPedvencod() {
        return pedvencod;
    }

    public void setPedvencod(int pedvencod) {
        this.pedvencod = pedvencod;
    }

    public String getPedvendata() {
        return pedvendata;
    }

    public void setPedvendata(String pedvendata) {
        this.pedvendata = pedvendata;
    }

    public String getPedvendatafinal() {
        return pedvendatafinal;
    }

    public void setPedvendatafinal(String pedvendatafinal) {
        this.pedvendatafinal = pedvendatafinal;
    }

    public String getPedvenobs() {
        return pedvenobs;
    }

    public void setPedvenobs(String pedvenobs) {
        this.pedvenobs = pedvenobs;
    }

    public int getPedvenparcelas() {
        return pedvenparcelas;
    }

    public void setPedvenparcelas(int pedvenparcelas) {
        this.pedvenparcelas = pedvenparcelas;
    }

    public int getPedvenstatus() {
        return pedvenstatus;
    }

    public void setPedvenstatus(int pedvenstatus) {
        this.pedvenstatus = pedvenstatus;
    }

    public double getPedvenvlrtotal() {
        return pedvenvlrtotal;
    }

    public void setPedvenvlrtotal(double pedvenvlrtotal) {
        this.pedvenvlrtotal = pedvenvlrtotal;
    }

    public int pedvencod;
    public int clicod;
    public int pedvenparcelas;
    public int pedvenstatus;
    public String pedvendata;
    public String pedvendatafinal;
    public double pedvenvlrtotal;
    public String pedvenobs;

    public String getInsereSql() {
       return "";
    }

    public String getAtualizaSql() {
       return "update pedvenda set clicod = "+clicod+",pedvendata = '"+pedvendata+"' ,pedvendatafinal = '"+pedvendatafinal+"',pedvenvlrtotal = "+pedvenvlrtotal+",pedvenobs = '"+pedvenobs+"',pedvenstatus = "+pedvenstatus+" where pedvencod = "+pedvencod+"  ";
    }
public ResultSet getInsereRs() {
        String sql = "insert into pedvenda(clicod,pedvendata ,pedvendatafinal,pedvenvlrtotal,pedvenobs,pedvenstatus)values("+clicod+",'"+pedvendata+"','"+pedvendatafinal+"',"+pedvenvlrtotal+",'"+pedvenobs+"',"+pedvenstatus+")returning pedvencod";
       System.out.println(sql);
        return Conexao.executaQuery(sql);

    }
    public String getExcluiSql() {
       return "delete from pedvenda where pedvencod = "+pedvencod+"";
    }

    public String getConsultaTodos() {
       return "";
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
            rs = stmt.executeQuery("select count(pedvenvlrtotal) from pedvenda where pedvenvlrtotal = '" + pedvenvlrtotal + "'and pedvenstatus = "+pedvenstatus+"");

            while (rs.next()) {
                count = rs.getInt(1);
            }

            if (count > 0) {
                JOptionPane.showMessageDialog(null, "Existe uma composição com o mesmo nome cadastrada");
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
            rs = stmt.executeQuery("select count(pedvenvlrtotal) from pedvenda where pedvenvlrtotal = '" + pedvenvlrtotal + "'and pedvenstatus = "+pedvenstatus+" and pedvencod !="+pedvencod+"");

            while (rs.next()) {
                count = rs.getInt(1);
            }

            if (count > 0) {
                JOptionPane.showMessageDialog(null, "Existe uma pedidoeeee cadastrado");
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

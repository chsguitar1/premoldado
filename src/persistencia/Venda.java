package persistencia;

import IntefaceAca.OperaBanco;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Banco.Conexao;

public class Venda implements OperaBanco {

    ResultSet rs;

    public int getPedvencod() {
        return pedvencod;
    }

    public void setPedvencod(int pedvencod) {
        this.pedvencod = pedvencod;
    }

    public int getVencod() {
        return vencod;
    }

    public void setVencod(int vencod) {
        this.vencod = vencod;
    }

    public String getVendata() {
        return vendata;
    }

    public void setVendata(String vendata) {
        this.vendata = vendata;
    }

    public int getVenparcelas() {
        return venparcelas;
    }

    public void setVenparcelas(int venparcelas) {
        this.venparcelas = venparcelas;
    }

    public int getVenstatus() {
        return venstatus;
    }

    public void setVenstatus(int venstatus) {
        this.venstatus = venstatus;
    }

    public double getVentotal() {
        return ventotal;
    }

    public void setVentotal(double ventotal) {
        this.ventotal = ventotal;
    }
    public int vencod;
    public int pedvencod;
    public int venparcelas;
    public int venstatus;
    public String vendata;
    public double ventotal;

    public String getInsereSql() {
        return "insert into venda(pedvencod,venparcelas ,venstatus,vendata,ventotal)values(" + pedvencod + "," +venparcelas+",'" + venstatus+ "','" + vendata+ "'," + ventotal + ")";
    }

    public ResultSet getInsereRs() {
        String sql = "insert into venda(pedvencod,venparcelas ,venstatus,vendata,ventotal)values(" + pedvencod + "," +venparcelas+"," + venstatus+ ",'" + vendata+ "'," + ventotal + ")returning vencod , pedvencod";
        System.out.println(sql);
        return Conexao.executaQuery(sql);

    }

    public String getAtualizaSql() {
        return "";
    }

    public String getExcluiSql() {
        return "";
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

            rs = Conexao.executaQuery("select count(vencod) from venda where vencod = '" + vencod + "'and venstatus = " + venstatus + "");

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
}

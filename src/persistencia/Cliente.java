package persistencia;

import IntefaceAca.OperaBanco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import Banco.Conexao;

public class Cliente implements OperaBanco {

    private Statement stmt;
    private ResultSet rs;

    public int getCidcod() {
        return cidcod;
    }

    public void setCidcod(int cidcod) {
        this.cidcod = cidcod;
    }

    public String getClibairro() {
        return clibairro;
    }

    public void setClibairro(String clibairro) {
        this.clibairro = clibairro;
    }

    public String getClicel() {
        return clicel;
    }

    public void setClicel(String clicel) {
        this.clicel = clicel;
    }

    public String getClicep() {
        return clicep;
    }

    public void setClicep(String clicep) {
        this.clicep = clicep;
    }

    public String getClicnpj() {
        return clicnpj;
    }

    public void setClicnpj(String clicnpj) {
        this.clicnpj = clicnpj;
    }

    public int getClicod() {
        return clicod;
    }

    public void setClicod(int clicod) {
        this.clicod = clicod;
    }

    public String getClicompl() {
        return clicompl;
    }

    public void setClicompl(String clicompl) {
        this.clicompl = clicompl;
    }

    public String getClicontato() {
        return clicontato;
    }

    public void setClicontato(String clicontato) {
        this.clicontato = clicontato;
    }

    public String getClicpf() {
        return clicpf;
    }

    public void setClicpf(String clicpf) {
        this.clicpf = clicpf;
    }

    public String getClidata() {
        return clidata;
    }

    public void setClidata(String clidata) {
        this.clidata = clidata;
    }

    public String getCliemail() {
        return cliemail;
    }

    public void setCliemail(String cliemail) {
        this.cliemail = cliemail;
    }

    public String getCliendereco() {
        return cliendereco;
    }

    public void setCliendereco(String cliendereco) {
        this.cliendereco = cliendereco;
    }

    public String getClifax() {
        return clifax;
    }

    public void setClifax(String clifax) {
        this.clifax = clifax;
    }

    public String getCliie() {
        return cliie;
    }

    public void setCliie(String cliie) {
        this.cliie = cliie;
    }

    public String getClinome() {
        return clinome;
    }

    public void setClinome(String clinome) {
        this.clinome = clinome;
    }

    public String getClinumero() {
        return clinumero;
    }

    public void setClinumero(String clinumero) {
        this.clinumero = clinumero;
    }

    public String getClioe() {
        return clioe;
    }

    public void setClioe(String clioe) {
        this.clioe = clioe;
    }

    public String getClirg() {
        return clirg;
    }

    public void setClirg(String clirg) {
        this.clirg = clirg;
    }

    public int getClistatus() {
        return clistatus;
    }

    public void setClistatus(int clistatus) {
        this.clistatus = clistatus;
    }

    public String getClitel() {
        return clitel;
    }

    public void setClitel(String clitel) {
        this.clitel = clitel;
    }
    public int clicod;
    public int cidcod;
    public int clistatus;
    public String clidata;
    public String clinome;
    public String cliendereco;
    public String clinumero;
    public String clibairro;
    public String clicompl;
    public String clicep;
    public String clitel;
    public String clicel;
    public String clifax;
    public String cliemail;
    public String clicontato;
    public String clicpf;
    public String clirg;
    public String clicnpj;
    public String cliie;
    public String clioe;
    public int clitipo;

    public int getClitipo() {
        return clitipo;
    }

    public void setClitipo(int clitipo) {
        this.clitipo = clitipo;
    }

    public String getInsereSql() {
        return "insert into cliente(cidcod,clistatus,clidata,clinome,cliendereco,clinumero,clibairro,"
                + "clicompl,clicep,clitel,clicel,clifax,cliemail,clicontato,clicpf,clirg,clicnpj,cliie,clioe,clitipo)values("
                + "" + cidcod + "," + clistatus + ",'" + clidata + "','" + clinome + "','" + cliendereco + "','" + clinumero + "',"
                + "'" + clibairro + "','" + clicompl + "','" + clicep + "','" + clitel + "','" + clicel + "','" + clifax + "',"
                + "'" + cliemail + "','" + clicontato + "','" + clicpf + "','" + clirg + "','" + clicnpj + "','" + cliie + "','" + clioe + "'," + clitipo + ")";
    }

    public String getAtualizaSql() {
        return "update cliente set cidcod = "+cidcod+",clistatus = "+clistatus+",clinome = '"+clinome+"',cliendereco = '"+cliendereco+"',clinumero ='"+clinumero+"',clibairro = '"+clibairro+"',"
                + "clicompl = '"+clicompl+"',clicep = '"+clicep+"',clitel = '"+clitel+"',clicel = '"+clicel+"',clifax = '"+clifax+"',cliemail = '"+cliemail+"',clicontato = '"+clicontato+"',clicpf = '"+clicpf+"'," +
                "clirg = '"+clirg+"',clicnpj = '"+clicnpj+"',cliie = '"+cliie+"',clioe = '"+clioe+"',clitipo = "+clitipo+" where clicod = "+clicod+"";
    }

    public String getExcluiSql() {
        return "delete from cliente where clicod = "+clicod+"";
    }

    public String getConsultaTodos() {
        return "select * from cliente where clicod = " + clicod + "";
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

            if (clitipo == 1) {
                stmt = Conexao.getConexao().createStatement();
                rs = stmt.executeQuery("execute procedure duplicidade_clientef('" + clicpf + "','" + clirg + "')");
            } else {
                stmt = Conexao.getConexao().createStatement();
                rs = stmt.executeQuery("execute procedure duplicidade_clientej('" + clicnpj + "')");
            }
            while (rs.next()) {
                count = rs.getInt(1);
//                JOptionPane.showMessageDialog(null, count);
            }
            if (count > 0) {
                JOptionPane.showMessageDialog(null, "Existe um Cadastro para o Cliente "  + clinome + "");
                return false;
            } else {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro de Banco de Dados.");
        }
        return false;
    }

    public boolean verificaDuplicidadeAtualizar() {
        try {
            int count = 0;
            if (clitipo == 1) {
                stmt = Conexao.getConexao().createStatement();
                rs = stmt.executeQuery("select count (clicpf) from cliente where clicpf = '" + clicpf + "' and clicod != " + clicod + "");
            } else {
                stmt = Conexao.getConexao().createStatement();
                rs = stmt.executeQuery("select count (clicnpj) from cliente where clicnpj = '" + clicnpj + "' and clicod != " + clicod + "");
            }

            while (rs.next()) {
                count = rs.getInt(1);
            }
            if (count > 0) {
                JOptionPane.showMessageDialog(null, "Cliente já Cadastrado.");
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

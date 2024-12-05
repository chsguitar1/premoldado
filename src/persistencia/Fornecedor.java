package persistencia;

import IntefaceAca.OperaBanco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import Banco.Conexao;

public class Fornecedor implements OperaBanco {

    private Statement stmt;
    private ResultSet rs;

    public int getCidcod() {
        return cidcod;
    }

    public void setCidcod(int cidcod) {
        this.cidcod = cidcod;
    }

    public String getFonome() {
        return fonome;
    }

    public void setFonome(String fonome) {
        this.fonome = fonome;
    }

    public String getForbairro() {
        return forbairro;
    }

    public void setForbairro(String forbairro) {
        this.forbairro = forbairro;
    }

    public String getForcel() {
        return forcel;
    }

    public void setForcel(String forcel) {
        this.forcel = forcel;
    }

    public String getForcep() {
        return forcep;
    }

    public void setForcep(String forcep) {
        this.forcep = forcep;
    }

    public String getForcnpj() {
        return forcnpj;
    }

    public void setForcnpj(String forcnpj) {
        this.forcnpj = forcnpj;
    }

    public int getForcod() {
        return forcod;
    }

    public void setForcod(int forcod) {
        this.forcod = forcod;
    }

    public String getForcompl() {
        return forcompl;
    }

    public void setForcompl(String forcompl) {
        this.forcompl = forcompl;
    }

    public String getForcpf() {
        return forcpf;
    }

    public void setForcpf(String forcpf) {
        this.forcpf = forcpf;
    }

    public String getFordata() {
        return fordata;
    }

    public void setFordata(String fordata) {
        this.fordata = fordata;
    }

    public String getForemail() {
        return foremail;
    }

    public void setForemail(String foremail) {
        this.foremail = foremail;
    }

    public String getForendereco() {
        return forendereco;
    }

    public void setForendereco(String forendereco) {
        this.forendereco = forendereco;
    }

    public String getForfax() {
        return forfax;
    }

    public void setForfax(String forfax) {
        this.forfax = forfax;
    }

    public String getForie() {
        return forie;
    }

    public void setForie(String forie) {
        this.forie = forie;
    }

    public String getFornumero() {
        return fornumero;
    }

    public void setFornumero(String fornumero) {
        this.fornumero = fornumero;
    }

    public String getForoe() {
        return foroe;
    }

    public void setForoe(String foroe) {
        this.foroe = foroe;
    }

    public String getForrg() {
        return forrg;
    }

    public void setForrg(String forrg) {
        this.forrg = forrg;
    }

    public int getForstatus() {
        return forstatus;
    }

    public void setForstatus(int forstatus) {
        this.forstatus = forstatus;
    }

    public String getFortel() {
        return fortel;
    }

    public void setFortel(String fortel) {
        this.fortel = fortel;
    }

    public int getFortipo() {
        return fortipo;
    }

    public void setFortipo(int fortipo) {
        this.fortipo = fortipo;
    }
    public int forcod;
    public int cidcod;
    public int forstatus;
    public String fordata;
    public String fonome;
    public String forendereco;
    public String fornumero;
    public String forbairro;
    public String forcompl;
    public String forcep;
    public String fortel;
    public String forcel;
    public String forfax;
    public String foremail;
    public String forcpf;
    public String forrg;
    public String forcnpj;
    public String forie;
    public String foroe;
    public String forcontato;

    public String getForcontato() {
        return forcontato;
    }

    public void setForcontato(String forcontato) {
        this.forcontato = forcontato;
    }
    public int fortipo;

    public String getInsereSql() {
        return "insert into fornecedor(cidcod,fonome,forendereco,fornumero,forbairro,forcompl,forcep,fortel,forcel,"
                + "forfax,foremail,fordata,forcpf,forrg , forcnpj,forie,foroe,tipofor,forcontato,forstatus)values"
                + "(" + cidcod + ",'" + fonome + "','" + forendereco + "','" + fornumero + "',"
                + "'" + forbairro + "','" + forcompl + "','" + forcep + "','" + fortel + "','" + forcel + "','" + forfax + "','" + foremail + "','" + fordata + "',"
                + "'" + forcpf + "','" + forrg + "','" + forcnpj + "','" + forie + "','" + foroe + "'," + fortipo + ",'" + forcontato + "'," + forstatus + ")retuning forcod";
    }

    public String getAtualizaSql() {
        return "update fornecedor set cidcod = " + cidcod + ",fonome = '" + fonome + "',forendereco = '" + forendereco + "',fornumero = '" + fornumero + "',forbairro = '" + forbairro + "' ,"
                + "forcompl = '" + forcompl + "' ,forcep = '" + forcep + "' ,fortel = '" + fortel + "' ,forcel = '" + forcel + "',forfax = '" + forfax + "' ,foremail = '" + foremail + "',fordata = '" + fordata + "' ,forcpf = '" + forcpf + "',forrg = '" + forrg + "' , forcnpj = '" + forcnpj + "',forie = '" + forie + "' ,"
                + "foroe = '" + foroe + "',tipofor = " + fortipo + ",forcontato = '" + forcontato + "',forstatus = " + forstatus + " where forcod = " + forcod + " ";
    }

    public String getExcluiSql() {
        return "";
    }
  public ResultSet getInsereRs() {
        String sql = "insert into fornecedor(cidcod,fonome,forendereco,fornumero,forbairro,forcompl,forcep,fortel,forcel,"
                + "forfax,foremail,fordata,forcpf,forrg , forcnpj,forie,foroe,tipofor,forcontato,forstatus)values"
                + "(" + cidcod + ",'" + fonome + "','" + forendereco + "','" + fornumero + "',"
                + "'" + forbairro + "','" + forcompl + "','" + forcep + "','" + fortel + "','" + forcel + "','" + forfax + "','" + foremail + "','" + fordata + "',"
                + "'" + forcpf + "','" + forrg + "','" + forcnpj + "','" + forie + "','" + foroe + "'," + fortipo + ",'" + forcontato + "'," + forstatus + ")returning forcod";
        System.out.println(sql);
        return Conexao.executaQuery(sql);

    }
    public String getConsultaTodos() {
        return "select * from fornecedor where forcod = " + forcod + " ";
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

            if (fortipo == 1) {
                stmt = Conexao.getConexao().createStatement();
                rs = stmt.executeQuery("select count (forcpf) from fornecedor where forcpf = '" + forcpf + "'");
            } else {
                stmt = Conexao.getConexao().createStatement();
                rs = stmt.executeQuery("select count (forcnpj)from fornecedor  where forcnpj = '" + forcnpj + "'");
            }
            while (rs.next()) {
                count = rs.getInt(1);
                JOptionPane.showMessageDialog(null, count);
            }
            if (count > 0) {
                JOptionPane.showMessageDialog(null, "Existe um Cadastro para o Fornecedor " + fonome + "");
                return false;
            } else {
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de Banco de Dados.");
        }
        return false;
    }

    public boolean verificaDuplicidadeAtualizar() {
        try {
            int count = 0;
            if (fortipo == 1) {
                stmt = Conexao.getConexao().createStatement();
                rs = stmt.executeQuery("select count (forcpf) from fornecedor where forcpf = '" + forcpf + "' and forcod != " + forcod + "");
            } else {
                stmt = Conexao.getConexao().createStatement();
                rs = stmt.executeQuery("select count (forcnpj) from fornecedor where forcnpj = '" + forcnpj + "' and forcod != " + forcod + "");
            }

            while (rs.next()) {
                count = rs.getInt(1);
            }
            if (count > 0) {
                JOptionPane.showMessageDialog(null, "Fornecedor  Cadastrado.");
                return false;
            } else {
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de Conexão com o Banco de Dados.");
        }
        return false;
    }

    public boolean verificaTipos() {
        try {
            int count = 0;

            stmt = Conexao.getConexao().createStatement();
            rs = stmt.executeQuery("select count (forcod) from  where tipoxfornecedor forcod = '" + forcod + "' ");
            while (rs.next()) {
                count = rs.getInt(1);
            }
            if (count > 0) {

                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de Conexão com o Banco de Dados.");
        }
        return false;
    }
}

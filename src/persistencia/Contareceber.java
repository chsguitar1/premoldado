package persistencia;

import IntefaceAca.OperaBanco;

public class Contareceber implements OperaBanco {

    public int conreccod;
    public int conrecstatus;
    public String conrecdata;
    public double conrecvlr;
    public String conrecnum;
    public int vencod;

    public int getVencod() {
        return vencod;
    }

    public void setVencod(int vencod) {
        this.vencod = vencod;
    }

    public int getConreccod() {
        return conreccod;
    }

    public void setConreccod(int conreccod) {
        this.conreccod = conreccod;
    }

    public String getConrecdata() {
        return conrecdata;
    }

    public void setConrecdata(String conrecdata) {
        this.conrecdata = conrecdata;
    }

    public String getConrecnum() {
        return conrecnum;
    }

    public void setConrecnum(String conrecnum) {
        this.conrecnum = conrecnum;
    }

    public int getConrecstatus() {
        return conrecstatus;
    }

    public void setConrecstatus(int conrecstatus) {
        this.conrecstatus = conrecstatus;
    }

    public double getConrecvlr() {
        return conrecvlr;
    }

    public void setConrecvlr(double conrecvlr) {
        this.conrecvlr = conrecvlr;
    }

    public String getInsereSql() {
        return "insert into contareceber(conrecdata,conrecvlr,conrecnum, conrecstatus,vencod)values('" + conrecdata + "'," + conrecvlr + ",'" + conrecnum + "'," + conrecstatus + "," + vencod + ")";
    }

    public String getInsereSqlAvulsa() {
        return "insert into contareceber(conrecdata,conrecvlr,conrecnum, conrecstatus,vencod)values('" + conrecdata + "'," + conrecvlr + ",'" + conrecnum + "'," + conrecstatus + "," + null + ")";
    }

    public String getAtualizaSql() {
        return "update contareceber set conrecdata = '" + conrecdata + "',conrecvlr = " + conrecvlr + " ,conrecnum = '" + conrecnum + "' , conrecstatus = " + conrecstatus + " where conreccod = " + conreccod + " ";
    }

    public String getExcluiSql() {
        return "delete from contareceber where conreccod = " + conreccod + " ";
    }

    public String getConsultaTodos() {
        return "";
    }

    public String getConsultaSqlCod() {
        return "select * from contareceber where conreccod = " + conreccod + " ";
    }

    public String getConsultaSqlString() {
        return "";
    }
}

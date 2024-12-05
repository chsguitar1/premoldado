package persistencia;

import IntefaceAca.OperaBanco;

public class Contapagar implements OperaBanco {

    public int conpagcod;
    public int comcod;
    public int conpagstatus;
    public String conpagdata;
    public double conpagvlr;
    public String conpagnum;

    public int getComcod() {
        return comcod;
    }

    public void setComcod(int comcod) {
        this.comcod = comcod;
    }

    public int getConpagcod() {
        return conpagcod;
    }

    public void setConpagcod(int conpagcod) {
        this.conpagcod = conpagcod;
    }

    public String getConpagdata() {
        return conpagdata;
    }

    public void setConpagdata(String conpagdata) {
        this.conpagdata = conpagdata;
    }

    public String getConpagnum() {
        return conpagnum;
    }

    public void setConpagnum(String conpagnum) {
        this.conpagnum = conpagnum;
    }

    public int getConpagstatus() {
        return conpagstatus;
    }

    public void setConpagstatus(int conpagstatus) {
        this.conpagstatus = conpagstatus;
    }

    public double getConpagvlr() {
        return conpagvlr;
    }

    public void setConpagvlr(double conpagvlr) {
        this.conpagvlr = conpagvlr;
    }

    public String getInsereSql() {
        return "insert into contapagar(conpagdata,conpagvlr,conpagnum,conpagstatus,comcod)values('" + conpagdata + "'," + conpagvlr + ",'" + conpagnum + "'," + conpagstatus + ","+comcod+")";
    }
    public String getInsereSqlAvulsa() {
        return "insert into contapagar(conpagdata,conpagvlr,conpagnum,conpagstatus,comcod)values('" + conpagdata + "'," + conpagvlr + ",'" + conpagnum + "'," + conpagstatus + ","+null+")";
    }

    public String getAtualizaSql() {
        return "update contapagar set conpagdata = '" + conpagdata + "',conpagvlr = " + conpagvlr + ",conpagnum = '" + conpagnum + "' ,conpagstatus = " + conpagstatus + " where conpagcod = " + conpagcod + "";
    }

    public String getExcluiSql() {
        return " delete from contapagar where conpagcod = " + conpagcod + " ";
    }

    public String getConsultaTodos() {
        return " ";
    }

    public String getConsultaSqlCod() {
        return "select * from contapagar where conpagcod = " + conpagcod + "";
    }

    public String getConsultaSqlString() {
        return "";
    }
}

package persistencia;

import IntefaceAca.OperaBanco;

public class Cotfornecedor implements OperaBanco {

    public int cotcodxf;
    public int forcod;
    public int cotcod;
    public int codstatus;
    public String fonome;
    public double cotvlrxf;

    public double getCotvlrxf() {
        return cotvlrxf;
    }

    public void setCotvlrxf(double cotvlrxf) {
        this.cotvlrxf = cotvlrxf;
    }

    public String getFonome() {
        return fonome;
    }

    public void setFonome(String fonome) {
        this.fonome = fonome;
    }

    public int getCodstatus() {
        return codstatus;
    }

    public void setCodstatus(int codstatus) {
        this.codstatus = codstatus;
    }

    public int getCotcod() {
        return cotcod;
    }

    public void setCotcod(int cotcod) {
        this.cotcod = cotcod;
    }

    public int getCotcodxf() {
        return cotcodxf;
    }

    public void setCotcodxf(int cotcodxf) {
        this.cotcodxf = cotcodxf;
    }

    public int getForcod() {
        return forcod;
    }

    public void setForcod(int forcod) {
        this.forcod = forcod;
    }

    public String getInsereSql() {
        return "insert into cotfornecedor(forcod, cotcod, codstatus,fonome)values(" + forcod + "," + cotcod + "," + codstatus + ",'" + fonome + "')";
    }

    public String getAtualizaSql() {
        return "update cotfornecedor set forcod = " + forcod + ", cotcod = " + cotcod + ",codstatus = " + codstatus + ", fonome = '" + fonome + "',cotvlrxf = " + cotcodxf + "  where cotcod = " + cotcod + "";
    }

    public String getAtualizaCotacao() {
        return "update cotfornecedor set codstatus = " + codstatus + ",cotvlrxf = " + cotvlrxf + "  where cotcod = " + cotcod + " and forcod = " + forcod + "";

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
}

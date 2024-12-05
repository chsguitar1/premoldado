/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

/**
 *
 * @author chs
 */
public class Consulta {
int codigo;
String letra;
String cod;
String campo;
String tabela;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getTabela() {
        return tabela;
    }

    public void setTabela(String tabela) {
        this.tabela = tabela;
    }

    public String sqlConCodigo(){
    String sql = "select "+ cod +" , "+ campo +" from "+ tabela +" where "+ cod +" = "+ codigo +" ";
    return sql;
    }
    public String sqlConString(){
    String sql =  "select "+cod+", "+campo+" from "+tabela+" where "+campo+" like %" +letra+ "% ";
    return sql;
    }
}

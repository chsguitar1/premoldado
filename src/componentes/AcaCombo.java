/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;

import IntefaceAca.AcaComponente;
import Banco.Conexao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author chs
 */
public class AcaCombo extends JComboBox implements AcaComponente {

    //  public static Connection conexao1;
    public String querycombo;
    public Vector codigo = new Vector();
    public String dica;
    public boolean obrigatorio;

    public String getQuerycombo() {
        return querycombo;
    }

    public void setQuerycombo(String querycombo) {
        this.querycombo = querycombo;
    }

    public Vector getCodigo() {
        return codigo;
    }

    public void setDica(String dica) {
        this.dica = dica;
    }

    public int getValor() {
        return (Integer) codigo.get(getSelectedIndex());


    }

    public void setValor(int valor) {
        for (int contador = 0; contador < codigo.size(); contador++) {
            if ((Integer) codigo.get(contador) == valor) {
                setSelectedIndex(contador);
                break;
            }
        }

    }

    public AcaCombo() {

        this.setFont(new java.awt.Font("Tahoma", 0, 12));
    }

    public AcaCombo(boolean obrigatorio, String dica) {
        this.obrigatorio = obrigatorio;
        this.dica = dica;
        this.setFont(new java.awt.Font("Tahoma", 0, 12));
    }

    public void buscaResult(String sql) {

//  JOptionPane.showMessageDialog(null, sql);
 System.out.println(sql);

        Conexao.getConexao();
        try {
            Statement stmt = Conexao.conexao.createStatement();

            ResultSet resultado = stmt.executeQuery(sql);

            displayResultSet(resultado);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "nao encontrou");

        }


    }

    public void displayResultSet(ResultSet rs) {



        try {

            codigo.removeAllElements();
            this.removeAllItems();
            codigo.addElement(0);
            this.addItem("Selecionar...");
            while (rs.next()) {

                codigo.addElement(rs.getInt(1));

                this.addItem(rs.getString(2));

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "NAO ACHEI DADOS DO COMBO");
        }
    }

    public void limpar() {
        setSelectedIndex(-1);
    }

    public void habilitar(boolean status) {
        setEnabled(status);
    }

    public void editar(boolean status) {
        setEditable(status);
    }

    public boolean eObrigatorio() {
        if (obrigatorio == false && this.getSelectedIndex() == 0) {
        return false;
        }
        return true;
            }


    public boolean eVazio() {
        if (this.getSelectedIndex() != -1) {
            return false;
        }
        return true;
    }

    public boolean eValido() {
        return true;
    }

    public String getDica() {
        return dica;
    }

    public void alterarCampos(boolean status) {
    }

    public void comboFiltro(int[] tipo, String[] nome) {
        codigo.removeAllElements();
        this.removeAllItems();
        for (int x = 0; x < tipo.length; x++) {
            codigo.addElement(tipo[x]);
            this.addItem(nome[x]);
        }

    }
}



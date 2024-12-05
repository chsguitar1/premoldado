/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package IntefaceAca;

/**
 *
 * @author chs
 */
public interface AcaComponente {
public void limpar();
public void habilitar(boolean  status);
public void editar(boolean status);
public boolean eObrigatorio();
public boolean eVazio();
public boolean  eValido();
public String getDica();
public void alterarCampos(boolean status);

 

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package IntefaceAca;

import java.sql.ResultSet;

/**
 *
 * @author chs
 */
public interface OperaBanco {
public String getInsereSql();
public String getAtualizaSql();
public String getExcluiSql();
public String getConsultaTodos();
public String getConsultaSqlCod();
public String getConsultaSqlString();


}

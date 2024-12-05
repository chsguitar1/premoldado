/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TelaVenda.java
 *
 * Created on 03/11/2010, 12:06:09
 */
package telaMovimenacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import persistencia.Contareceber;
import persistencia.Itens_vendas;
import persistencia.Itensordprod;
import persistencia.Ordempropd;
import persistencia.Venda;
import Banco.Conexao;
import Telaconsulta.Data;
import ajuda.PlayMovie;
import componentes.AcaTabela;

/**
 *
 * @author chs
 */
public class TelaVenda extends TelaMovimenta implements ActionListener {

    Venda vendaPer = new Venda();
    Itens_vendas itensVenPer = new Itens_vendas();
    Contareceber contarecper = new Contareceber();
    Ordempropd ordemPer = new Ordempropd();
    Itensordprod itensOrdPer = new Itensordprod();
    AcaTabela jtTabela = new AcaTabela();
    Data datateste = new Data();
    private static final Calendar DATA_EMISSAO = Calendar.getInstance();

    /** Creates new form BeanForm */
    public TelaVenda() {
        initComponents();
        jtTabela.montaTabela(new String[]{"Codigo", "Descrição", "Quantidade", "Valor Unitario", "Subtotal"}, new int[]{50, 200, 120, 100, 100});
        acaComboTipo.buscaResult("select pedvencod,pedvencod from pedvenda where pedvenstatus = " + ABERTO + "");
        acaCombostatus.comboFiltro(new int[]{ABERTO, FECHADO}, new String[]{"Aberta", "Finalizada"});
        acaLabelID.setText("Importar Pedido");
        acaLabelCodigo.setText("Codigo do Pedido");
        acaDataPed.setDate(new Date());
        campos.add(acaComboParcelas);
        //      campos.add(acaDataPed);
        acaBotoesAlterar.setVisible(false);
        acaBotoesConsultar.setVisible(false);
        acaBotoesExcluir.setVisible(false);
        habilitaCampos(false);

    }

    @Override
    protected void obterCampos() {
        vendaPer.setPedvencod(acaComboTipo.getValor());
        vendaPer.setVenstatus(acaCombostatus.getValor());
        vendaPer.setVenparcelas(acaComboParcelas.getValor());
        vendaPer.setVentotal(Double.parseDouble(acaMonetarioTotal.getValor()));
        DATA_EMISSAO.setTime(acaDataPed.getDate());
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
        String data = df.format(DATA_EMISSAO.getTime()).toString();
        vendaPer.setVendata(data.replace("/", "."));

    }

    @Override
    protected boolean incluirBD() {
        obterCampos();
        if (vendaPer.verificaDuplicidade()) {
            Conexao.executaSql(vendaPer.getInsereSql());
            Conexao.executaSql("update pedvenda set pedvenstatus = " + FECHADO + " where pedvencod = " + acaComboTipo.getValor() + "");

            if (acaComboParcelas.getValor() == 0) {
                ResultSet rs = null;
                rs = Conexao.executaQuery("select max(vencod)from venda");
                try {
                    rs.next();
                    Conexao.executaSql("EXECUTE PROCEDURE VENDAAVISTA(" + rs.getInt(1) + ", '" + vendaPer.getVendata() + "', '" + acaTextfieldNome.getTexto() + "'," + acaMonetarioTotal.getValor() + ")");
                } catch (SQLException ex) {
                    Logger.getLogger(TelaVenda.class.getName()).log(Level.SEVERE, null, ex);
                }


            } else {

                ResultSet rs = null;
                rs = Conexao.executaQuery("select max(vencod)from venda");
                try {
                    DATA_EMISSAO.setTime(acaDataPed.getDate());
                    rs.next();
                    contarecper.setVencod(rs.getInt(1));
                    for (int x = 0; x < acaComboParcelas.getValor(); x++) {
                        DATA_EMISSAO.add(Calendar.MONTH, 1);
                        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
                        String data = df.format(DATA_EMISSAO.getTime()).toString();

                        contarecper.setConrecdata(data.replace("/", "."));
                        contarecper.setConrecnum("Venda a Prazo ");
                        contarecper.setConrecstatus(ABERTO);
                        contarecper.setConrecvlr(geraParcelas());
                        Conexao.executaSql(contarecper.getInsereSql());


                    }
                } catch (SQLException ex) {
                    Logger.getLogger(TelaVenda.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            geraOrdem();

            return true;

        }
        return false;
    }

    @Override
    public void dadosConsultaTipo(int codigo) {
        String sql;
        ResultSet rs = null;
        if (tipoOperacao == INCLUINDO || tipoOperacao == PESQUISANDO) {
            sql = "SELECT C1.CLINOME,C1.CLIENDERECO,C1.CLIBAIRRO,C1.CLICEP,c1.CLINUMERO,C1.CLISTATUS,E1.ESTNOME, C2.CIDNOME, C1.CLICOD,p2.pedvenvlrtotal FROM "
                    + "CLIENTE C1,ESTADO E1,CIDADE C2,PEDVENDA P2 WHERE E1.ESTCOD = C2.ESTCOD AND C2.CIDCOD = C1.CIDCOD AND  P2.CLICOD = C1.CLICOD AND P2.PEDVENCOD = " + codigo + "";
        } else {
            sql = "SELECT C1.CLINOME,C1.CLIENDERECO,C1.CLIBAIRRO,C1.CLICEP,c1.CLINUMERO,C1.CLISTATUS,E1.ESTNOME, C2.CIDNOME, C1.CLICOD FROM "
                    + "CLIENTE C1,ESTADO E1,CIDADE C2,PEDVENDA P2 WHERE E1.ESTCOD = C2.ESTCOD AND C2.CIDCOD = C1.CIDCOD AND  P2.CLICOD = C1.CLICOD AND P2.PEDVENCOD = " + codigo + "";

        }
        System.out.println(sql);
        try {
            rs = Conexao.executaQuery(sql);

            rs.next();
            if (rs.getInt(6) == 1) {

                acaTextfieldNome.setText(rs.getString(1));
                ;
                if (tipoOperacao != INCLUINDO) {
                    acaComboTipo.setValor(rs.getInt(9));

                } else {
                }
                acaTextfieldCodigo.setText(String.valueOf(acaComboTipo.getValor()));
                acaMonetarioTotal.setValor(rs.getDouble(10));
            } else {
                JOptionPane.showMessageDialog(null, "Foi encontrado um problema no cadastro do cliente \n verivique o status");
                return;
            }
        } catch (Exception e) {
        }
    }

    public void geraOrdem() {
       // JOptionPane.showMessageDialog(null, "chegou agui");
        ordemPer.setOrddataini(vendaPer.getVendata());
        ordemPer.setOrdstatus(ABERTO);
        ResultSet ordem = null;
        ordem = ordemPer.getInsereRs();
        try {
            ordem.next();
            Conexao.executaSql("EXECUTE PROCEDURE GERAORDEMVENDA(" + contarecper.getVencod() + "," + ordem.getInt(1) +" )");

        } catch (Exception e) {
        }


    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanel1 = new javax.swing.JPanel();
        acaLabel2 = new componentes.AcaLabel();
        acaLabel3 = new componentes.AcaLabel();
        acaComboParcelas = new componentes.AcaCombo(true,"Parcelas");
        acaComboParcelas.comboFiltro(new int[]{0,1,2,3,4,5,6}, new String[]{"À vista","1","2","3","4","5","6"});
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaGContaReceber = new componentes.AcaTabela();
        acaDataPed = new com.toedter.calendar.JDateChooser();

        setPreferredSize(new java.awt.Dimension(870, 540));
        setSize(new java.awt.Dimension(870, 540));
        setTitle("Venda");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        acaLabel2.setText("Data da Venda");

        acaLabel3.setText("Formas de Pagamento");

        tabelaGContaReceber.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabelaGContaReceber);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acaLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaDataPed, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(acaComboParcelas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(acaLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acaLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(acaDataPed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaComboParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getPainel());
        getPainel().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private componentes.AcaCombo acaComboParcelas;
    private com.toedter.calendar.JDateChooser acaDataPed;
    private componentes.AcaLabel acaLabel2;
    private componentes.AcaLabel acaLabel3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private componentes.AcaTabela tabelaGContaReceber;
    // End of variables declaration//GEN-END:variables

    private boolean validaItens(int valor) {
        return true;
    }

    private double geraParcelas() {
        double valorTotal = Double.parseDouble(acaMonetarioTotal.getValor().replace(",", "."));
        double qtdParcelas = valorTotal / acaComboParcelas.getValor();
        System.out.println(qtdParcelas);
        return qtdParcelas;

    }
  @Override
    protected void ajuda() {
        PlayMovie videos = new PlayMovie("vendas.wmv");


    }
    public void actionPerformed(ActionEvent e) {
    }
}

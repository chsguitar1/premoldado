/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TelaRecebimento.java
 *
 * Created on 23/07/2011, 17:04:15
 */
package telaMovimenacao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import persistencia.Contapagar;
import persistencia.Movcaixa;
import persistencia.Pagamento;
import Banco.Conexao;
import premoldados.TelaSistema;
import Telaconsulta.TelaConsCtaPag;
import ajuda.PlayMovie;

/**
 *
 * @author cristiano
 */
public class TelaPagamento extends TelaRecPag implements InternalFrameListener {

 Contapagar contaPagarPer = new Contapagar();
 Pagamento pagaPer = new Pagamento();
    Movcaixa movCxPer = new Movcaixa();
    TelaConsCtaPag telaconsulta;

    /** Creates new form TelaRecebimento */
    public TelaPagamento() {
        initComponents();
        campos.add(acaTextfieldCodigo);
        campos.add(acaDatarecebimento);
        campos.add(acaMonetarioVlr);
        campos.add(acaComboCondicao);
        campos.add(acaTextfieldDescr);
        campos.add(acaDataVencimento);
        acaDataVencimento.setEditable(false);
        habilitaCampos(false);
    }

    public void obterCampos() {
        pagaPer.setConpagcod(Integer.parseInt(acaTextfieldCodigo.getText()));
        pagaPer.setPagdata(acaDatarecebimento.getValor());
        pagaPer.setPagnome(acaTextfieldDescr.getTexto());
        pagaPer.setPagcond(acaComboCondicao.getSelectedItem().toString());
        pagaPer.setPagstatus(FECHADO);
        pagaPer.setPagvlr(Double.parseDouble(acaMonetarioVlr.getValor()));
        movCxPer.setMovdata(acaDatarecebimento.getValor());
        movCxPer.setMovdescr(acaTextfieldDescr.getTexto());
        movCxPer.setMovstatus(FECHADO);
        movCxPer.setMovtipo(2);
        movCxPer.setMovvlr(Double.parseDouble(acaMonetarioVlr.getValor()));

    }

    @Override
    protected void consultar() {
        super.consultar();
        if (telaconsulta == null) {
            telaconsulta = new TelaConsCtaPag(this, 1);
            telaconsulta.addInternalFrameListener(this);
            telaconsulta.setVisible(true);
            TelaSistema.jdp.add(telaconsulta);
            telaconsulta.acaCombosStatus.comboFiltro(new int[]{ABERTO, FECHADO, PARCIAL}, new String[]{"Em Aberto", "Quitado", "Pagamento Parcial"});
            telaconsulta.tabela.montaTabela(new String[]{"Codigo", "Descrição", "Data", "Valor"}, new int[]{90, 200, 90, 120});
        }
        TelaSistema.jdp.moveToFront(telaconsulta);
    }

    @Override
    public void consultarDados(int codigo) {

        contaPagarPer.setConpagcod(codigo);
        ResultSet rs = null;
        try {
            rs = Conexao.executaQuery(contaPagarPer.getConsultaSqlCod());
            rs.next();
            acaTextfieldCodigo.setText(rs.getString(1));
            acaDataVencimento.SetValor(rs.getDate(2));
            acaMonetarioVlr.setValor(rs.getDouble(3));
            acaTextfieldDescr.setText(rs.getString(4));
            habilitaCampos(true);

        } catch (Exception e) {
        }

    }

    @Override
    protected boolean incluirBD() {
        obterCampos();
        ResultSet rs = null;
        ResultSet rscaixa = null;
        if (verStatusCaixa()) {
            rs = pagaPer.getInsereRs();
            try {
                rs.next();
                movCxPer.setPagcod(rs.getInt(1));
            } catch (SQLException ex) {
                Logger.getLogger(TelaPagamento.class.getName()).log(Level.SEVERE, null, ex);
            }
            rscaixa = Conexao.executaQuery("select cxcod from caixa");
            try {
                rscaixa.next();
                movCxPer.setCxcod(rscaixa.getInt(1));
            } catch (SQLException ex) {
                Logger.getLogger(TelaPagamento.class.getName()).log(Level.SEVERE, null, ex);
            }
            Conexao.executaSql("update contapagar set conpagstatus = " + FECHADO + " where conpagcod = " + Integer.parseInt(acaTextfieldCodigo.getText()) + "");
            Conexao.executaSql(movCxPer.getInsereSql());

            limparCampos();
            habilitaCampos(false);
            return true;
        } else {
            return false;
        }

    }

    public boolean verStatusCaixa() {
        ResultSet rs = null;

        try {
            rs = Conexao.executaQuery("select cxstatus from caixa  ");
            rs.next();

            if (rs.getInt(1) != 1) {
                JOptionPane.showMessageDialog(null, "O caixa encontra-se fechado");
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
        }

        return false;


    }
  @Override
    protected void ajuda() {
        PlayMovie videos = new PlayMovie("pagamento.wmv");


    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        acaLabel6 = new componentes.AcaLabel();
        acaTextfieldDescr = new componentes.AcaTextfield();
        acaLabel3 = new componentes.AcaLabel();
        acaLabel2 = new componentes.AcaLabel();
        acaComboCondicao = new componentes.AcaCombo();
        acaComboCondicao.comboFiltro(new int[]{1,2}, new String[]{"Dinheiro","Cheque"});
        acaLabel5 = new componentes.AcaLabel();
        acaLabel4 = new componentes.AcaLabel();
        acaLabel1 = new componentes.AcaLabel();
        acaTextfieldCodigo = new componentes.AcaTextfield();
        acaDatarecebimento = new componentes.AcaData();
        acaMonetario1 = new componentes.AcaMonetario();
        acaDataVencimento = new componentes.AcaData();
        acaCombosStatus = new componentes.AcaCombo();
        acaLabel7 = new componentes.AcaLabel();

        setTitle("Pagamento");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        acaLabel6.setText("Vencimento  ");

        acaLabel3.setText("Data ");

        acaLabel2.setText("Descrição");

        acaLabel5.setText("Valor");

        acaLabel4.setText("Condições");

        acaLabel1.setText("Codigo ");

        acaTextfieldCodigo.setEditable(false);

        acaMonetario1.setText("acaMonetario1");

        acaLabel7.setText("statutis");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(acaLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(acaMonetario1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(acaLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(acaDatarecebimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(acaLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(acaLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acaComboCondicao, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                            .addComponent(acaCombosStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(151, 151, 151))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acaLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acaTextfieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaTextfieldDescr, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaDataVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acaLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaTextfieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acaLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaTextfieldDescr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acaLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaDataVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acaLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaComboCondicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaDatarecebimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acaMonetario1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaCombosStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private componentes.AcaCombo acaComboCondicao;
    private componentes.AcaCombo acaCombosStatus;
    private componentes.AcaData acaDataVencimento;
    private componentes.AcaData acaDatarecebimento;
    private componentes.AcaLabel acaLabel1;
    private componentes.AcaLabel acaLabel2;
    private componentes.AcaLabel acaLabel3;
    private componentes.AcaLabel acaLabel4;
    private componentes.AcaLabel acaLabel5;
    private componentes.AcaLabel acaLabel6;
    private componentes.AcaLabel acaLabel7;
    private componentes.AcaMonetario acaMonetario1;
    private componentes.AcaTextfield acaTextfieldCodigo;
    private componentes.AcaTextfield acaTextfieldDescr;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    public void internalFrameOpened(InternalFrameEvent e) {
    }

    public void internalFrameClosing(InternalFrameEvent e) {
    }

    public void internalFrameClosed(InternalFrameEvent e) {
        if (e.getSource() == telaconsulta) {
            telaconsulta = null;
        }
    }

    public void internalFrameIconified(InternalFrameEvent e) {
    }

    public void internalFrameDeiconified(InternalFrameEvent e) {
    }

    public void internalFrameActivated(InternalFrameEvent e) {
    }

    public void internalFrameDeactivated(InternalFrameEvent e) {
    }
}

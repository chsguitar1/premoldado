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
import persistencia.Contareceber;
import persistencia.Movcaixa;
import persistencia.Recebimento;
import Banco.Conexao;
import premoldados.TelaSistema;
import Telaconsulta.TelaConsCtaRec;
import ajuda.PlayMovie;

/**
 *
 * @author cristiano
 */
public class TelaRecebimento extends TelaRecPag implements InternalFrameListener {

    Contareceber contaRecePer = new Contareceber();
    Recebimento recebePer = new Recebimento();
    Movcaixa movCxPer = new Movcaixa();
    TelaConsCtaRec telaconsulta;

    /** Creates new form TelaRecebimento */
    public TelaRecebimento() {
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
        recebePer.setConreccod(acaTextfieldCodigo.getInteiro());
        recebePer.setRecdata(acaDatarecebimento.getValor());
        recebePer.setRecnome(acaTextfieldDescr.getTexto());
        recebePer.setRecond(acaComboCondicao.getSelectedItem().toString());
        recebePer.setRecstatus(FECHADO);
        recebePer.setRecvlr(Double.parseDouble(acaMonetarioVlr.getValor()));
        movCxPer.setMovdata(acaDatarecebimento.getValor());
        movCxPer.setMovdescr(acaTextfieldDescr.getTexto());
        movCxPer.setMovstatus(FECHADO);
        movCxPer.setMovtipo(1);
        movCxPer.setMovvlr(Double.parseDouble(acaMonetarioVlr.getValor()));

    }

    @Override
    protected void consultar() {
        super.consultar();
        if (telaconsulta == null) {
            telaconsulta = new TelaConsCtaRec(this, 1);
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

        contaRecePer.setConreccod(codigo);
        ResultSet rs = null;
        try {
            rs = Conexao.executaQuery(contaRecePer.getConsultaSqlCod());
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
            rs = recebePer.getInsereRs();
            try {
                rs.next();
                movCxPer.setReccod(rs.getInt(1));
            } catch (SQLException ex) {
                Logger.getLogger(TelaRecebimento.class.getName()).log(Level.SEVERE, null, ex);
            }
            rscaixa = Conexao.executaQuery("select cxcod from caixa");
            try {
                rscaixa.next();
                movCxPer.setCxcod(rscaixa.getInt(1));
            } catch (SQLException ex) {
                Logger.getLogger(TelaRecebimento.class.getName()).log(Level.SEVERE, null, ex);
            }
            Conexao.executaSql("update contareceber set conrecstatus = " + FECHADO + " where conreccod = " + Integer.parseInt(acaTextfieldCodigo.getText()) + "");
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
        PlayMovie videos = new PlayMovie("recebimento.wmv");


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
        acaDatarecebimento = new componentes.AcaData();
        acaLabel4 = new componentes.AcaLabel();
        acaDataVencimento = new componentes.AcaData();
        acaMonetarioVlr = new componentes.AcaMonetario();
        acaLabel1 = new componentes.AcaLabel();
        acaTextfieldCodigo = new componentes.AcaTextfield();

        setSize(new java.awt.Dimension(800, 373));
        setTitle("Recebimento");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        acaLabel6.setText("Vencimento  ");

        acaLabel3.setText("Data ");

        acaLabel2.setText("Descrição");

        acaLabel5.setText("Valor");

        acaLabel4.setText("Condições");

        acaMonetarioVlr.setText("acaMonetario1");

        acaLabel1.setText("Codigo ");

        acaTextfieldCodigo.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acaLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acaTextfieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaDataVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaTextfieldDescr, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acaLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acaMonetarioVlr, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(acaDatarecebimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(acaLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(acaComboCondicao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(141, 141, 141)))
                .addContainerGap())
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
                    .addComponent(acaDataVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acaLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaDatarecebimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaComboCondicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acaMonetarioVlr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getPainel());
        getPainel().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    private componentes.AcaData acaDataVencimento;
    private componentes.AcaData acaDatarecebimento;
    private componentes.AcaLabel acaLabel1;
    private componentes.AcaLabel acaLabel2;
    private componentes.AcaLabel acaLabel3;
    private componentes.AcaLabel acaLabel4;
    private componentes.AcaLabel acaLabel5;
    private componentes.AcaLabel acaLabel6;
    private componentes.AcaMonetario acaMonetarioVlr;
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

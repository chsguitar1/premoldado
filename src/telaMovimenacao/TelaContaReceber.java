/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TelaContaReceber.java
 *
 * Created on 23/07/2011, 11:38:50
 */
package telaMovimenacao;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import persistencia.Contareceber;
import Banco.Conexao;
import premoldados.TelaSistema;
import Telaconsulta.TelaConsCtaRec;
import ajuda.PlayMovie;

/**
 *
 * @author cristiano
 */
public class TelaContaReceber extends TelaConta implements InternalFrameListener {

    Contareceber contaRecePer = new Contareceber();
    TelaConsCtaRec telaconsulta;

    /** Creates new form TelaContaReceber */
    public TelaContaReceber() {
        initComponents();
        campos.add(acaTextfieldCodigo);
        campos.add(acaTextfieldDescr);
        campos.add(acaMonetarioVlr);
        campos.add(acaComboStatus);
        campos.add(acaDataConta);
        habilitaCampos(false);


    }

    private void obterCampos() {
        contaRecePer.setConrecdata(acaDataConta.getValor());
        contaRecePer.setConrecnum(acaTextfieldDescr.getTexto());
        if (tipoOperacao == ALTERANDO || tipoOperacao == PESQUISANDO) {
            contaRecePer.setConreccod(acaTextfieldCodigo.getInteiro());
        }
        contaRecePer.setConrecstatus(acaComboStatus.getValor());
        contaRecePer.setConrecvlr(Double.parseDouble(acaMonetarioVlr.getValor()));
    }

    @Override
    protected boolean incluirBD() {
        obterCampos();
        Conexao.executaSql(contaRecePer.getInsereSqlAvulsa());
        limparCampos();
        habilitaCampos(false);
        return true;
    }

    @Override
    protected void consultar() {
        if (telaconsulta == null) {
            telaconsulta = new TelaConsCtaRec(this);
            telaconsulta.addInternalFrameListener(this);
            telaconsulta.setVisible(true);
            TelaSistema.jdp.add(telaconsulta);
            telaconsulta.acaCombosStatus.comboFiltro(new int[]{ABERTO, FECHADO, PARCIAL}, new String[]{"Em Aberto", "Quitado", "Pagamento Parcial"});
            telaconsulta.tabela.montaTabela(new String[]{"Codigo", "Descrição", "Data", "Valor"}, new int[]{90, 200, 90, 120});
        }
        TelaSistema.jdp.moveToFront(telaconsulta);
    }

    public void preencheCampos() {

        acaTextfieldCodigo.setText(String.valueOf(contaRecePer.getConreccod()));
        acaTextfieldDescr.setText(contaRecePer.getConrecnum());
        // acaDataConta.SetValor(contaRecePer.getConrecdata());
        acaMonetarioVlr.setValor(contaRecePer.getConrecvlr());
        acaComboStatus.setValor(contaRecePer.getConrecstatus());

    }

    @Override
    public void consultarDados(int codigo) {
        ResultSet rs = null;

        try {
            contaRecePer.setConreccod(codigo);
            rs = Conexao.executaQuery(contaRecePer.getConsultaSqlCod());
            rs.next();

            contaRecePer.setConreccod(rs.getInt(1));
            acaDataConta.SetValor(rs.getDate(2));
            //contaRecePer.setConrecdata(rs.getString(2));
            contaRecePer.setConrecvlr(rs.getDouble(3));
            contaRecePer.setConrecnum(rs.getString(4));
            contaRecePer.setConrecstatus(rs.getInt(5));



        } catch (SQLException ex) {
        }
        preencheCampos();

    }

    @Override
    protected boolean alterarBD() {
        obterCampos();
        Conexao.executaSql(contaRecePer.getAtualizaSql());
        limparCampos();
        habilitaCampos(false);
        return true;
    }

    @Override
    protected boolean excluirBD() {
        obterCampos();
        ;
        if (Conexao.executaSqlExcluir(contaRecePer.getExcluiSql()) == Conexao.DEPENDENCIA) {
            Object[] botoes = {"Sim", "Não"};
            int opcao = JOptionPane.showOptionDialog(null, " Nao foi possivel excluir \n  extiste uma movimentaçao para o registro selecionado \n Deseja Inativa o registro ",
                    "Sair", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes, botoes[0]);
            if (opcao == JOptionPane.YES_OPTION) {
                Conexao.executaSql("EXECUTE PROCEDURE INATIVASTATUS(" + contaRecePer.getConreccod() + ",'contareceber','conreccod','conrecstatus')");
                requestFocus();
            }
            limparCampos();
            return true;
        } else {
            Conexao.executaSqlExcluir(contaRecePer.getExcluiSql());
            limparCampos();

        }

        return true;
    }
  @Override
    protected void ajuda() {
        PlayMovie videos = new PlayMovie("contareceber.wmv");


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
        acaLabel1 = new componentes.AcaLabel();
        acaTextfieldCodigo = new componentes.AcaTextfield();
        acaLabel2 = new componentes.AcaLabel();
        acaDataConta = new componentes.AcaData(true,"Data de Vencimento");
        acaLabel3 = new componentes.AcaLabel();
        acaLabel4 = new componentes.AcaLabel();
        acaTextfieldDescr = new componentes.AcaTextfield(true, "Descrição", 1);
        acaMonetarioVlr = new componentes.AcaMonetario(true, "Valor", 1);
        acaLabel5 = new componentes.AcaLabel();
        acaComboStatus = new componentes.AcaCombo();
        acaComboStatus.comboFiltro(new int[]{ABERTO,FECHADO,PARCIAL}, new String[]{"Em Aberto","Quitado","Pagamento Pacial"});

        setTitle("Contas a Receber");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        acaLabel1.setText("Codigo");

        acaTextfieldCodigo.setEditable(false);

        acaLabel2.setText("Vencimento");

        acaLabel3.setText("Descrição");

        acaLabel4.setText("Valor");

        acaMonetarioVlr.setText("acaMonetario1");

        acaLabel5.setText("Status");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(acaLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acaDataConta, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaTextfieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaTextfieldDescr, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                            .addComponent(acaMonetarioVlr, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addComponent(acaComboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acaLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaTextfieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acaLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaDataConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acaLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaTextfieldDescr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acaLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaMonetarioVlr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acaLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaComboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getPainel());
        getPainel().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public componentes.AcaCombo acaComboStatus;
    public componentes.AcaData acaDataConta;
    private componentes.AcaLabel acaLabel1;
    private componentes.AcaLabel acaLabel2;
    private componentes.AcaLabel acaLabel3;
    private componentes.AcaLabel acaLabel4;
    private componentes.AcaLabel acaLabel5;
    public componentes.AcaMonetario acaMonetarioVlr;
    public componentes.AcaTextfield acaTextfieldCodigo;
    public componentes.AcaTextfield acaTextfieldDescr;
    public javax.swing.JPanel jPanel1;
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ConsultaCotacao.java
 *
 * Created on 19/04/2011, 23:58:49
 */
package telaMovimenacao;

import IntefaceAca.AcaComponente;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
import persistencia.Cotacoes;
import persistencia.Cotfornecedor;
import persistencia.Itenscotprod;
import Banco.Conexao;
import componentes.AcaTabela;

/**
 *
 * @author cristiano
 */
public class ConsultaCotacao extends javax.swing.JInternalFrame implements MouseListener, KeyListener {

    Cotacoes cotacoePer = new Cotacoes();
    Cotfornecedor cotforPer = new Cotfornecedor();
    Itenscotprod itenscotaper = new Itenscotprod();
    TelaCotacao telacota = new TelaCotacao();
    public AcaTabela jTabela = new AcaTabela();
    public Vector<AcaComponente> camposPainel1 = new Vector();
    public Vector<AcaComponente> camposPainel2 = new Vector();
    public int codigocot;
    public int codigofor;
    public final int FECHADO = 2;
    protected final int ABERTO = 1;
    public final int CANCELADO = 0;
    protected final int AUTOMATICO = 3;
    public final int EXCLUINDO = 0;
    public final int ALTERANDO = 1;
    public final int PADRAO = -1;
    public int tipooeracao = PADRAO;

    /** Creates new form ConsultaCotacao */
    public ConsultaCotacao() {
        initComponents();
    }

    public ConsultaCotacao(int codigocot, int codigofor) {
        this.codigocot = codigocot;
        this.codigofor = codigofor;
        initComponents();
        camposPainel1.add(acaTextfieldCodigo);
        camposPainel1.add(acaDataFin);
        camposPainel1.add(acaDataIni);
        camposPainel1.add(acaComboStatus);
        camposPainel1.add(acaComboTipoItens);
        camposPainel1.add(acaTextfieldCodFor);
        camposPainel1.add(acaTextfieldFornecedor);
        //  camposPainel2.add(acaComboProd);
        camposPainel2.add(acaTextfieldCodItens);
        camposPainel2.add(acaTextfieldQtde);
        camposPainel2.add(acaMonetarioSubtot);
        camposPainel2.add(acaMonetarioUnit);
        camposPainel2.add(acaTextfieldDes);
        camposPainel2.add(acaBotoesAddItes);
        camposPainel2.add(acaBotoesDelItens);
        camposPainel2.add(acaBotoesDelSelIten);
//        camposPainel2.add(acaBotoesNovoProd);
//        camposPainel2.add(acaBotoesConProd);
        camposPainel2.add(acaMonetarioTotal);
        acaTextfieldQtde.addKeyListener(this);
        acaMonetarioUnit.addKeyListener(this);
        acaMonetarioTotal.addKeyListener(this);
        acaComboStatus.comboFiltro(new int[]{ABERTO, FECHADO, CANCELADO, AUTOMATICO}, new String[]{"Aberta", "Encerrada", "Cancelada", "Automatica"});
        jTabela.addMouseListener(this);

        setaCamposPer();
        habilitaCampos(false, camposPainel1);
        habilitaCampos(false, camposPainel2);
        acaMonetarioSubtot.setValor(0.00);
        acaMonetarioUnit.setValor(0.00);

        posicaoTela();

    }

    protected void habilitaCampos(boolean status, Vector<AcaComponente> vetor) {

        for (int i = 0; i < vetor.size(); i++) {

            vetor.get(i).habilitar(status);
        }


    }

    protected void limparCampos(Vector<AcaComponente> campos) {
        for (int i = 0; i < campos.size(); i++) {
            campos.get(i).limpar();

        }

    }

    public void setaCamposPer() {
        try {
            ResultSet rs = Conexao.executaQuery("SELECT C1.COTCOD, C1.COTDATA,C1.COTDATAFIN,C1.TIPOMATCOD,C2.FORCOD,C2.FONOME , C2.CODSTATUS, C2.cotvlrxf "
                    + " FROM COTACOES C1,COTFORNECEDOR C2 "
                    + "WHERE C1.COTCOD = C2.COTCOD and c1.cotcod = " + codigocot + " and c2.forcod = " + codigofor + "");
            rs.next();
            cotacoePer.setCotcod(rs.getInt(1));
            acaDataIni.SetValor(rs.getDate(2));
            acaDataFin.SetValor(rs.getDate(3));
            cotacoePer.setTipomatcod(rs.getInt(4));
            cotforPer.setCotcod(rs.getInt(1));
            cotforPer.setForcod(rs.getInt(5));
            cotforPer.setFonome(rs.getString(6));
            cotforPer.setCodstatus(rs.getInt(7));
            cotforPer.setCotvlrxf(rs.getDouble(8));
            preecheCampos();
            jTabela.montaTabela(new String[]{"Codigo", "Descrição", "Quantidade", "Valor Unitario", "Subtotal"}, new int[]{70, 200, 100, 100, 100});
            jTabela.montaTabela("SELECT I1.MATCOD,M1.MATNOME,I1.ITENDCOTQTDE,I1.ITENSCOTUNT,I1.ITENSCOTSUBTOTAL "
                    + "FROM ITENSCODPROD I1, MATERIAPRIMA M1 "
                    + "WHERE I1.MATCOD = M1.MATCOD and I1.cotcod = " + codigocot + " and I1.forcod = " + codigofor + "");
            acaComboTipoItens.setEnabled(false);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void preecheCampos() {
        acaTextfieldCodigo.setText(String.valueOf(cotacoePer.getCotcod()));
        acaComboStatus.setValor(cotforPer.getCodstatus());
        acaComboTipoItens.setValor(cotacoePer.getTipomatcod());
        acaTextfieldCodFor.setText(String.valueOf(cotforPer.getForcod()));
        acaTextfieldFornecedor.setText(String.valueOf(cotforPer.getFonome()));
        // acaComboProd.buscaResult("select matcod, matnome from materiaprima where tipomatcod = " + cotacoePer.getTipomatcod() + "");
        acaMonetarioTotal.setValor(cotforPer.getCotvlrxf());
    }

    public void calculaUnitario() {
        int quantidade = Integer.parseInt(acaTextfieldQtde.getText());
        double valUnitario = Double.parseDouble(acaMonetarioUnit.getValor());
        double valTotal = ((int) ((quantidade * valUnitario) * 100.0)) / 100.0;
        acaMonetarioSubtot.setValor(valTotal);
    }

    public void calculaTotal() {

        double soma = 0.00;
        double valor = 0;
        for (int x = 0; x < jTabela.dtm.getRowCount(); x++) {
            if (!acaMonetarioTotal.getText().equals("")) {
                soma = soma + Double.parseDouble(jTabela.getValueAt(x, 4).toString().replace(",", "."));
                acaMonetarioTotal.setValor(soma);
            }
        }

    }

    public void addItem(AcaTabela tabela) {

        Object[] item = {acaTextfieldCodItens.getText(), acaTextfieldDes.getText().toUpperCase(), acaTextfieldQtde.getText(), acaMonetarioUnit.getValor(), acaMonetarioSubtot.getValor()};
        tabela.PrencheTabela(item);

    }

    public void alterandoBd() {
        cotacoePer.setCotcod(acaTextfieldCodigo.getInteiro());
        cotacoePer.setCotdata(acaDataIni.getValor().replace("/", "."));
        cotacoePer.setCotdatafin(acaDataFin.getValor().replace("/", "."));
        cotacoePer.setTipomatcod(acaComboTipoItens.getValor());
        cotacoePer.setCotvlrtotal(Double.parseDouble(acaMonetarioTotal.getValor()));
        cotacoePer.setCotstatus(acaComboStatus.getValor());
        cotforPer.setCodstatus(acaComboStatus.getValor());
        cotforPer.setCotvlrxf(Double.parseDouble(acaMonetarioTotal.getValor()));
        cotforPer.setCotcod(acaTextfieldCodigo.getInteiro());
        cotforPer.setForcod(acaTextfieldCodFor.getInteiro());
        Conexao.executaSql(cotacoePer.getAtualizaSql());
        itenscotaper.setCotcod(acaTextfieldCodigo.getInteiro());
        itenscotaper.setFonome(acaTextfieldFornecedor.getTexto());
        itenscotaper.setForcod(acaTextfieldCodFor.getInteiro());
        for (int x = 0; x < jTabela.dtm.getRowCount(); x++) {

            itenscotaper.setItendcotqtde(Float.parseFloat(jTabela.getValueAt(x, 2).toString().replace(",", ".")));
            itenscotaper.setItenscotunt(Double.parseDouble(jTabela.getValueAt(x, 3).toString().replace(",", ".")));
            itenscotaper.setItenscotsubtotal(Double.parseDouble(jTabela.getValueAt(x, 4).toString()));
            itenscotaper.setMatcod(Integer.parseInt(jTabela.getValueAt(x, 0).toString()));
            Conexao.executaSql(itenscotaper.getAtualizaSql());
        }
        System.out.println(cotforPer.getAtualizaCotacao());
        Conexao.executaSql(cotforPer.getAtualizaCotacao());
        limparCampos(camposPainel1);
        limparCampos(camposPainel2);
        habilitaCampos(false, camposPainel1);
        habilitaCampos(false, camposPainel2);
        jTabela.limparPesquisa();
        // telacota.jTabelaConsulta.limparPesquisa();
        telacota.consultar();
        dispose();

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        acaLabel1 = new componentes.AcaLabel();
        acaTextfieldCodigo = new componentes.AcaTextfield();
        acaLabel2 = new componentes.AcaLabel();
        acaDataIni = new componentes.AcaData();
        acaLabel3 = new componentes.AcaLabel();
        acaDataFin = new componentes.AcaData();
        acaLabel4 = new componentes.AcaLabel();
        acaComboStatus = new componentes.AcaCombo();
        acaLabel5 = new componentes.AcaLabel();
        acaComboTipoItens = new componentes.AcaCombo();
        acaComboTipoItens.buscaResult("select tipomatcod, tipomatfor from tipomateria");
        acaLabel11 = new componentes.AcaLabel();
        acaTextfieldCodFor = new componentes.AcaTextfield();
        acaLabel12 = new componentes.AcaLabel();
        acaTextfieldFornecedor = new componentes.AcaTextfield();
        jPanel2 = new javax.swing.JPanel();
        acaLabel6 = new componentes.AcaLabel();
        acaLabel7 = new componentes.AcaLabel();
        acaLabel8 = new componentes.AcaLabel();
        acaTextfieldCodItens = new componentes.AcaTextfield(false,"codigo itens",1);
        acaTextfieldDes = new componentes.AcaTextfield(false,"descricao itens",1);
        acaTextfieldQtde = new componentes.AcaTextfield(false, "quantidade", 1);
        acaBotoesAddItes = new componentes.AcaBotoes(false,"","");
        acaBotoesDelItens = new componentes.AcaBotoes(false,null,null);
        jScrollPane2 = new javax.swing.JScrollPane(jTabela);
        acaBotoesDelSelIten = new componentes.AcaBotoes(false,"","Apagar item selecionado");
        acaMonetarioUnit = new componentes.AcaMonetario(false,"Valor unitario",1);
        acaLabel9 = new componentes.AcaLabel();
        acaLabel10 = new componentes.AcaLabel();
        acaMonetarioSubtot = new componentes.AcaMonetario(false,"subtotal",1);
        acaLabel13 = new componentes.AcaLabel();
        acaMonetarioTotal = new componentes.AcaMonetario(true,"Valor Total",1);
        acaBotoesExcluir = new componentes.AcaBotoes();
        acaBotoesAlterar = new componentes.AcaBotoes();
        acaBotoesCancelar = new componentes.AcaBotoes();
        acaBotoesConfirmar = new componentes.AcaBotoes();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Alterar Cotaçao");

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        acaLabel1.setText("Codigo da Cotação");

        acaTextfieldCodigo.setEditable(false);

        acaLabel2.setText("Data Inicial");

        acaDataIni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaDataIniActionPerformed(evt);
            }
        });

        acaLabel3.setText("Data Final");

        acaLabel4.setText("Status");

        acaLabel5.setText("Tipo de Itens ");

        acaComboTipoItens.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                acaComboTipoItensItemStateChanged(evt);
            }
        });

        acaLabel11.setText("Codigo do Fornecedor");

        acaLabel12.setText("Nome");

        acaTextfieldFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaTextfieldFornecedorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(acaLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaTextfieldCodFor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acaLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaTextfieldFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(218, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acaTextfieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(acaLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(acaDataIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(acaLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(acaDataFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(acaLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(acaComboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(acaLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE))
                                    .addComponent(acaComboTipoItens, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(acaLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 375, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(182, 182, 182))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(acaLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(acaTextfieldCodFor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(acaLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(acaTextfieldFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(acaLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(acaTextfieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(acaLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(acaDataFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(acaLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(acaComboTipoItens, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(acaLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(acaComboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(acaLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(acaDataIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(112, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Pedido de Cotação", jPanel1);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setEnabled(false);

        acaLabel6.setText("Codigo");

        acaLabel7.setText("Descrição");

        acaLabel8.setText("Quantidade");

        acaTextfieldCodItens.setEditable(false);
        acaTextfieldCodItens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaTextfieldCodItensActionPerformed(evt);
            }
        });

        acaTextfieldDes.setEditable(false);

        acaBotoesAddItes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/edit_add1.png"))); // NOI18N
        acaBotoesAddItes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaBotoesAddItesActionPerformed(evt);
            }
        });

        acaBotoesDelItens.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/x.png"))); // NOI18N
        acaBotoesDelItens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaBotoesDelItensActionPerformed(evt);
            }
        });

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabela de Itens do Pedido"));

        acaBotoesDelSelIten.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/edit_remove.png"))); // NOI18N
        acaBotoesDelSelIten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaBotoesDelSelItenActionPerformed(evt);
            }
        });

        acaMonetarioUnit.setText("acaMonetario1");

        acaLabel9.setText("Valor Unitario");

        acaLabel10.setText("Subtotal");

        acaMonetarioSubtot.setText("acaMonetario2");

        acaLabel13.setText("Valor Total");
        acaLabel13.setFont(new java.awt.Font("Arial", 1, 12));

        acaMonetarioTotal.setText("acaMonetario1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(acaBotoesDelSelIten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(acaLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(acaLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(acaLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(acaTextfieldQtde, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(acaMonetarioUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17)
                                .addComponent(acaMonetarioSubtot, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(acaBotoesAddItes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(acaBotoesDelItens, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(acaLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(acaLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(acaTextfieldCodItens, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(acaTextfieldDes, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acaMonetarioTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(acaLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(acaLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(acaTextfieldCodItens, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(acaTextfieldDes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(acaLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(acaMonetarioTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acaLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acaTextfieldQtde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaMonetarioUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaMonetarioSubtot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaBotoesAddItes, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaBotoesDelItens, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(acaBotoesDelSelIten, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Itens da Cotação", jPanel2);

        acaBotoesExcluir.setText("Excluir");
        acaBotoesExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaBotoesExcluirActionPerformed(evt);
            }
        });

        acaBotoesAlterar.setText("Alterar");
        acaBotoesAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaBotoesAlterarActionPerformed(evt);
            }
        });

        acaBotoesCancelar.setText("Cancelar");
        acaBotoesCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaBotoesCancelarActionPerformed(evt);
            }
        });

        acaBotoesConfirmar.setText("Confirmar");
        acaBotoesConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaBotoesConfirmarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(acaBotoesAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaBotoesExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaBotoesConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaBotoesCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(acaBotoesAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(acaBotoesExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(acaBotoesConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(acaBotoesCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void acaComboTipoItensItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_acaComboTipoItensItemStateChanged
        if (acaComboTipoItens.getSelectedIndex() > 0) {
            if (acaComboTipoItens.isPopupVisible()) {
                //acaComboProd.buscaResult("select matcod, matnome from materiaprima where tipomatcod = " + acaComboTipoItens.getValor() + " ");
            }
        }
}//GEN-LAST:event_acaComboTipoItensItemStateChanged

    private void acaTextfieldCodItensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaTextfieldCodItensActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_acaTextfieldCodItensActionPerformed

    private void acaBotoesAddItesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaBotoesAddItesActionPerformed

        if (acaTextfieldQtde.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe a quantidade do Materia");

            return;
        }
        if (jTabela.getRowCount() == 0) {
            acaMonetarioTotal.setValor(Double.parseDouble(acaMonetarioSubtot.getValor()));
        }
        addItem(jTabela);

        // acaMonetarioTotal.setValor(Double.parseDouble(calculaTotal(acaMonetarioVlrUnit.getValor().toString())));
        acaTextfieldCodItens.setText("");
        acaTextfieldDes.setText("");
        acaTextfieldQtde.setText("");
        acaMonetarioUnit.setValor(0.00);
        acaMonetarioSubtot.setValor(0.00);

//        acaComboProd.setValor(0);
        if (tipooeracao == ALTERANDO) {
            calculaTotal();
        } else {
            if (jTabela.getRowCount() == 1) {
                calculaTotal();
            }
        }


}//GEN-LAST:event_acaBotoesAddItesActionPerformed

    private void acaBotoesDelItensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaBotoesDelItensActionPerformed
        acaTextfieldCodItens.setText("");
        acaTextfieldDes.setText("");
        acaMonetarioUnit.setValor(0.00);
        acaTextfieldQtde.setText("");
        //   acaComboProd.setSelectedIndex(0);
        acaMonetarioSubtot.setValor(0.00);
//        if (!acaMonetarioSubtot.getValor().equals("") && tipoOperacao != INCLUINDO) {
//            // calculaTotal();
//        }
}//GEN-LAST:event_acaBotoesDelItensActionPerformed

    private void acaBotoesDelSelItenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaBotoesDelSelItenActionPerformed
        if (tipooeracao == ALTERANDO) {
            itenscotaper.setCotcod(Integer.parseInt(acaTextfieldCodigo.getText()));
            itenscotaper.setMatcod(Integer.parseInt(jTabela.dtm.getValueAt(jTabela.getSelectedRow(), 0).toString()));
            Conexao.executaSql(itenscotaper.getExcluiSql());
            jTabela.dtm.removeRow(jTabela.getSelectedRow());



        } else {
            jTabela.dtm.removeRow(jTabela.getSelectedRow());
            //acaMonetarioTotal.setValor(Double.parseDouble(calculaTotal(acaMonetarioUnit.getValor().toString())));



        }
}//GEN-LAST:event_acaBotoesDelSelItenActionPerformed

    private void acaBotoesExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaBotoesExcluirActionPerformed
        habilitaCampos(true, camposPainel1);
        habilitaCampos(
                true, camposPainel2);
        tipooeracao = EXCLUINDO;
        acaBotoesAlterar.setEnabled(false);
}//GEN-LAST:event_acaBotoesExcluirActionPerformed

    private void acaBotoesAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaBotoesAlterarActionPerformed
        tipooeracao = ALTERANDO;
        habilitaCampos(
                true, camposPainel1);
        habilitaCampos(
                true, camposPainel2);
        tipooeracao = ALTERANDO;


        if (cotforPer.getCotvlrxf() == 0.00) {
            acaMonetarioTotal.setValor(0.00);


        }
        acaBotoesExcluir.setEnabled(false);

}//GEN-LAST:event_acaBotoesAlterarActionPerformed
    private void acaBotoesCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaBotoesCancelarActionPerformed
        Object[] botoes = {"Sim", "Não"};


        int opcao = JOptionPane.showOptionDialog(null, " Deseja cancelar a operação ",
                "Sair", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes, botoes[0]);


        if (opcao == JOptionPane.YES_OPTION) {
            dispose();




        }
        if (opcao == JOptionPane.NO_OPTION) {
            habilitaCampos(true, camposPainel1);
            habilitaCampos(
                    true, camposPainel2);



        }
        tipooeracao = PADRAO;
}//GEN-LAST:event_acaBotoesCancelarActionPerformed

    private void acaBotoesConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaBotoesConfirmarActionPerformed
        if (tipooeracao == ALTERANDO) {
            alterandoBd();



        }
        if (tipooeracao == EXCLUINDO) {
        }
}//GEN-LAST:event_acaBotoesConfirmarActionPerformed

    private void acaTextfieldFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaTextfieldFornecedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_acaTextfieldFornecedorActionPerformed

    private void acaDataIniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaDataIniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_acaDataIniActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private componentes.AcaBotoes acaBotoesAddItes;
    protected componentes.AcaBotoes acaBotoesAlterar;
    protected componentes.AcaBotoes acaBotoesCancelar;
    protected componentes.AcaBotoes acaBotoesConfirmar;
    private componentes.AcaBotoes acaBotoesDelItens;
    private componentes.AcaBotoes acaBotoesDelSelIten;
    protected componentes.AcaBotoes acaBotoesExcluir;
    private componentes.AcaCombo acaComboStatus;
    private componentes.AcaCombo acaComboTipoItens;
    private componentes.AcaData acaDataFin;
    private componentes.AcaData acaDataIni;
    private componentes.AcaLabel acaLabel1;
    private componentes.AcaLabel acaLabel10;
    private componentes.AcaLabel acaLabel11;
    private componentes.AcaLabel acaLabel12;
    private componentes.AcaLabel acaLabel13;
    private componentes.AcaLabel acaLabel2;
    private componentes.AcaLabel acaLabel3;
    private componentes.AcaLabel acaLabel4;
    private componentes.AcaLabel acaLabel5;
    private componentes.AcaLabel acaLabel6;
    private componentes.AcaLabel acaLabel7;
    private componentes.AcaLabel acaLabel8;
    private componentes.AcaLabel acaLabel9;
    public componentes.AcaMonetario acaMonetarioSubtot;
    public componentes.AcaMonetario acaMonetarioTotal;
    public componentes.AcaMonetario acaMonetarioUnit;
    private componentes.AcaTextfield acaTextfieldCodFor;
    protected componentes.AcaTextfield acaTextfieldCodItens;
    public componentes.AcaTextfield acaTextfieldCodigo;
    protected componentes.AcaTextfield acaTextfieldDes;
    private componentes.AcaTextfield acaTextfieldFornecedor;
    public componentes.AcaTextfield acaTextfieldQtde;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == jTabela) {
            if (e.getClickCount() == 2) {
                acaTextfieldCodItens.setText(String.valueOf(jTabela.dtm.getValueAt(jTabela.getSelectedRow(), 0)));
                acaTextfieldDes.setText(String.valueOf(jTabela.dtm.getValueAt(jTabela.getSelectedRow(), 1)));
                acaTextfieldQtde.setText(String.valueOf(jTabela.dtm.getValueAt(jTabela.getSelectedRow(), 2).toString().replace(".0", "")));
                acaMonetarioUnit.setValor(Double.parseDouble(jTabela.dtm.getValueAt(jTabela.getSelectedRow(), 3).toString()));
                acaMonetarioSubtot.setValor(Double.parseDouble(jTabela.dtm.getValueAt(jTabela.getSelectedRow(), 4).toString()));
                jTabela.dtm.removeRow(jTabela.getSelectedRow());
                acaTextfieldQtde.requestFocus();


            }

        }
    }

    public void posicaoTela() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        setLocation(
                (screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 200);


    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {

        calculaUnitario();


    }
}

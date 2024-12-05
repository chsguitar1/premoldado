/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TelaPedVenda.java
 *
 * Created on 30/10/2010, 11:34:07
 */
package telaMovimenacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import persistencia.Itenspedvenda;
import persistencia.Pedvenda;
import persistencia.Produto;
import Banco.Conexao;
import premoldados.TelaSistema;
import Telacadastros.TelaCliente;
import Telacadastros.TelaMateriaPrima;
import Telacadastros.TelaProduto;
import Telaconsulta.TelaConsMov;
import Telaconsulta.TelaConsulta;
import ajuda.PlayMovie;

/**
 *
 * @author chs
 */
public class TelaPedVenda extends TelaMovimenta implements InternalFrameListener, KeyListener, MouseListener, ActionListener {

    TelaConsMov telaconsultacli;
    TelaConsulta consulta;
    TelaConsultaPed consultaPed;
    TelaCliente telacliente;
    Produto prodPer = new Produto();
    Pedvenda pedVendaPer = new Pedvenda();
    Itenspedvenda itePedVenda = new Itenspedvenda();
    TelaProduto telaproduto;
    TelaVenda telavenda;
    private JTable jtTabela = new JTable() {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private DefaultTableModel dtm;
    public Vector colu = new Vector();
    TelaMateriaPrima telamatprima;

    /** Creates new form BeanForm */
    public TelaPedVenda() {

        acaLabeLAtividade.setText("do Pedido");
        montaTabela(new String[]{"Codigo", "Descrição", "Quantidade", "Unitario", "Subtotal"});
        jtTabela.addMouseListener(this);
        initComponents();
        campos.add(acaTextfieldCodItens);
        campos.add(acaBotoesAddItes);
        campos.add(acaBotoesConProd);
        campos.add(acaBotoesDelItens);
        campos.add(acaBotoesDelSelIten);
        campos.add(acaTextfieldDes);
        campos.add(acaComboProd);
        campos.add(acaMonetarioSubtot);
        campos.add(acaMonetarioUnit);
        campos.add(acaDataPed);
        campos.add(acaDataPrevPed);
        campos.add(acaTextfieldCodigoPed);
        campos.add(acaBotoesNovoProd);
        campos.add(acaTextfieldQtde);

        jTextAreaObs.setEnabled(false);
        habilitaCampos(false);
        acaLabelID.setText("Cliente");
        acaLabelNome.setText("Cliente");
        acaComboTipo.buscaResult("select clicod,clinome from cliente");
        /*SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        formatter.format(new Date());
        //
        acaDataPed.setFormats(formatter.format(new Date()));
        acaDataPrevPed.setFormats(formatter.format(new Date()));*/
        acaTextfieldQtde.addKeyListener(this);
        acaMonetarioUnit.addKeyListener(this);
        acaBotoesNovoProd.addActionListener(this);
        acaBotoesConProd.addActionListener(this);
        acaBotoesExcluir.setVisible(false);
    }

    @Override
    protected void obterCampos() {
        if (tipoOperacao == ALTERANDO || tipoOperacao == EXCLUINDO) {
            pedVendaPer.setPedvencod(Integer.parseInt(acaTextfieldCodigoPed.getText()));
        }
        if (acaComboTipo.getSelectedIndex() == 0) {
            pedVendaPer.setClicod(Integer.parseInt(acaTextfieldCodigo.getText()));
        } else {
            pedVendaPer.setClicod(acaComboTipo.getValor());
        }
        pedVendaPer.setPedvendata(acaDataPed.getValor());
        pedVendaPer.setPedvendatafinal(acaDataPrevPed.getValor());
        pedVendaPer.setPedvenobs(jTextAreaObs.getText().toUpperCase());
        pedVendaPer.setPedvenstatus(acaCombostatus.getValor());
        pedVendaPer.setPedvenvlrtotal(Double.parseDouble(acaMonetarioTotal.getValor()));
    }

    @Override
    protected void preencheCampos() {
    }

    public void montaTabela(String[] colunas) {

        dtm = (DefaultTableModel) jtTabela.getModel();
        for (int i = 0; i < colunas.length; i++) {
            dtm.addColumn(colunas[i]);
        }

        jtTabela.getColumn(jtTabela.getColumnName(0)).setPreferredWidth(50);

//        jtTabela.getColumn(jtTabela.getColumnName(2)).setPreferredWidth(90);
//        jtTabela.getColumn(jtTabela.getColumnName(3)).setPreferredWidth(90);
//        jtTabela.getColumn(jtTabela.getColumnName(4)).setPreferredWidth(90);
//        jtTabela.getColumn(jtTabela.getColumnName(5)).setPreferredWidth(100);
        jtTabela.setModel(dtm);
        TableColumnModel modeloColuna = jtTabela.getColumnModel();

        jtTabela.getTableHeader().setReorderingAllowed(false);
        jtTabela.getColumnModel().getColumn(0).setMaxWidth(80);
        jtTabela.getColumnModel().getColumn(1).setMaxWidth(190);
        jtTabela.getColumnModel().getColumn(0).setPreferredWidth(80);
        jtTabela.getColumnModel().getColumn(1).setPreferredWidth(190);
        jtTabela.getColumnModel().getColumn(2).setPreferredWidth(70);
        jtTabela.getColumnModel().getColumn(2).setMaxWidth(70);
        jtTabela.getColumnModel().getColumn(3).setPreferredWidth(100);
        jtTabela.getColumnModel().getColumn(3).setMaxWidth(100);
        jtTabela.getColumnModel().getColumn(4).setPreferredWidth(100);
        jtTabela.getColumnModel().getColumn(4).setMaxWidth(100);

        jtTabela.getColumnModel().getColumn(0).setResizable(false);


        jtTabela.getColumnModel().getColumn(1).setResizable(false);
        DefaultTableCellRenderer renderer1 = new DefaultTableCellRenderer();
        renderer1.setHorizontalAlignment(SwingConstants.CENTER);

    }

    public void montaTabela(String textoSql) {
        try {
            while (dtm.getRowCount() > 0) {
                dtm.removeRow(0);
            }
            ResultSet rs1 = Conexao.executaQuery(textoSql);
            ResultSetMetaData rsmd1 = rs1.getMetaData();
            Vector vetor;
            while (rs1.next()) {
                vetor = new Vector();
                for (int i = 0; i < rsmd1.getColumnCount(); i++) {
                    vetor.add(rs1.getString(i + 1));

                }

                dtm.addRow(vetor);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível efetuar a pesquisa");
            e.printStackTrace();
        }

        TableColumnModel modeloColuna = jtTabela.getColumnModel();
        jtTabela.getTableHeader().setReorderingAllowed(false);
        jtTabela.getColumnModel().getColumn(0).setMaxWidth(95);
        jtTabela.getColumnModel().getColumn(0).setPreferredWidth(300);
        jtTabela.getColumnModel().getColumn(0).setResizable(false);
        jtTabela.getColumnModel().getColumn(1).setMaxWidth(70);
        jtTabela.getColumnModel().getColumn(1).setPreferredWidth(10);
         jtTabela.getColumnModel().getColumn(2).setPreferredWidth(150);

        jtTabela.getColumnModel().getColumn(1).setResizable(false);

    }

    @Override
    protected void incluir() {
        super.incluir();
        jTextAreaObs.setEnabled(true);

    }

    @Override
    protected void cancelar() {
        super.cancelar();
        limparPesquisa();
        jTextAreaObs.setEnabled(false);

    }

    @Override
    protected void alterar() {
        super.alterar();
        jtTabela.setEnabled(true);

    }

    @Override
    protected void consultar() {
        if (consultaPed == null) {
            consultaPed = new TelaConsultaPed(this, 1);
            consultaPed.tabela.montaTabela(new String[]{"Codigo", "Cliente", "Data", "Valor"}, new int[]{90, 200, 90, 120});
            consultaPed.setCAMPOCODIGO("pedvencod");
            consultaPed.setCAMPODATA("pedvendata");
            consultaPed.setCAMPONOME("clinome");
            consultaPed.setCAMPOVLR("pedvenvlrtotal");
            consultaPed.setTABELA("pedvenda");
            consultaPed.setTABELA2("cliente");
            consultaPed.setCHAVETABELA("clicod");
            consultaPed.setCAMPOSTATUS(("pedvenstatus"));
            consultaPed.acaCombosStatus.comboFiltro(new int[]{FECHADO, ABERTO, CANCELADO}, new String[]{"Fechado", "Aberto", "Cancelado"});

            consultaPed.setVisible(true);
            consultaPed.addInternalFrameListener(this);
            TelaSistema.jdp.add(consultaPed);


        }
        TelaSistema.jdp.moveToFront(consultaPed);
    }

    @Override
    public void dadosConsulta(int codigo) {
        try {
            itePedVenda.setVencod(codigo);
            montaTabela(itePedVenda.getConsultaSqlCod());
            acaTextfieldCodigoPed.setText(String.valueOf(codigo));
            ResultSet rs = null;
            try {
                rs = Conexao.executaQuery("select pedvendata,pedvendatafinal,  pedvenstatus from pedvenda where pedvencod = " + codigo + "");
                rs.next();
                acaDataPed.SetValor(rs.getDate(1));
                acaDataPrevPed.SetValor(rs.getDate(2));
                acaCombostatus.setValor(rs.getInt(3));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



        jtTabela.setEnabled(false);
        habilitaCampos(false);

    }

    @Override
    protected void novoTipo() {
        tipoOperacao = NOVOPESSOA;
        if (telacliente == null) {
            telacliente = new TelaCliente();
            telacliente.addInternalFrameListener(this);
            TelaSistema.jdp.add(telacliente);
            telacliente.setVisible(true);

        }
        TelaSistema.jdp.moveToFront(telacliente);

    }

    @Override
    protected boolean incluirBD() {
        obterCampos();
        if (pedVendaPer.verificaDuplicidade()) {
            ResultSet rs = null;
            try {
                rs = pedVendaPer.getInsereRs();
                rs.next();
                itePedVenda.setVencod(rs.getInt(1));
                for (int x = 0; x < jtTabela.getRowCount(); x++) {

                    itePedVenda.setProdcod(Integer.parseInt(jtTabela.getValueAt(x, 0).toString()));
                    itePedVenda.setItenqtdeprod(Float.parseFloat(jtTabela.getValueAt(x, 2).toString()));
                    itePedVenda.setItenvalorunit(Double.parseDouble(jtTabela.getValueAt(x, 4).toString()));
                    itePedVenda.setItenvlrunita(Double.parseDouble(jtTabela.getValueAt(x, 3).toString()));

                    Conexao.executaSql(itePedVenda.getInsereSql());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            limparPesquisa();
            jTextAreaObs.setText("");
            jTextAreaObs.setEnabled(false);
            Object[] botoes = {"Sim", "Não"};
            int opcao = JOptionPane.showOptionDialog(null, " Deseja finalizar o Pedido de venda ",
                    "Sair", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes, botoes[0]);
            if (opcao == JOptionPane.YES_OPTION) {
                if (telavenda == null) {
                    telavenda = new TelaVenda();
                    telavenda.addInternalFrameListener(this);
                    TelaSistema.jdp.add(telavenda);
                    telavenda.setVisible(true);
                }
                TelaSistema.jdp.moveToFront(telavenda);
            }
            if (opcao == JOptionPane.NO_OPTION) {
                habilitaCampos(true);

            }
            return true;
        }
        return false;
    }
  @Override
    protected void ajuda() {
        PlayMovie videos = new PlayMovie("pedidovenda.wmv");


    }
//    @Override
//    protected boolean alterarBD() {
//        obterCampos();
//        if (pedVendaPer.verificaDuplicidadeAtualiza()) {
//            ResultSet rs = null;
//            try {
//                Conexao.executaSql(pedVendaPer.getAtualizaSql());
//
//                itePedVenda.setProdcod(Integer.parseInt(acaTextfieldCodigo.getText()));
//
//                for (int x = 0; x < dtm.getRowCount(); x++) {
//                    itePedVenda.setProdcod(Integer.parseInt(jtTabela.getValueAt(x, 0).toString()));
//                    itePedVenda.setItenqtdeprod(Float.parseFloat(jtTabela.getValueAt(x, 2).toString()));
//                    itePedVenda.setItenvalorunit(Double.parseDouble(jtTabela.getValueAt(x, 4).toString()));
//                    itePedVenda.setItenvlrunita(Double.parseDouble(jtTabela.getValueAt(x, 3).toString()));
//                    Conexao.executaSql(itePedVenda.getAtualizaSql());
//                }
//                limparPesquisa();
//                habilitarBotoes(true);
//            } catch (Exception e) {
//            }
//            return true;
//
//        }
//        return false;
//    }
    @Override
    protected boolean alterarBD() {
        obterCampos();
        if (pedVendaPer.verificaDuplicidadeAtualiza()) {
            ResultSet rs = null;
            try {
                Conexao.executaSql(pedVendaPer.getAtualizaSql());

                itePedVenda.setProdcod(Integer.parseInt(acaTextfieldCodigo.getText()));

                for (int x = 0; x < dtm.getRowCount(); x++) {
                    itePedVenda.setProdcod(Integer.parseInt(jtTabela.getValueAt(x, 0).toString()));
                    itePedVenda.setItenqtdeprod(Float.parseFloat(jtTabela.getValueAt(x, 2).toString()));
                    itePedVenda.setItenvalorunit(Double.parseDouble(jtTabela.getValueAt(x, 4).toString()));
                    itePedVenda.setItenvlrunita(Double.parseDouble(jtTabela.getValueAt(x, 3).toString()));
                    Conexao.executaSql(itePedVenda.getAtualizaSql());
                }
                limparPesquisa();
                habilitarBotoes(true);
            } catch (Exception e) {
            }
            return true;

        }
        return false;
    }

    @Override
    protected boolean excluirBD() {
        pedVendaPer.setPedvencod(Integer.parseInt(acaTextfieldCodigoPed.getText()));
        int opcao = -1;
        if (Conexao.executaSqlExcluir(pedVendaPer.getExcluiSql()) == Conexao.DEPENDENCIA) {
            opcao = 1;
        } else {
            opcao = 0;
        }
        if (Conexao.executaSqlExcluir(pedVendaPer.getExcluiSql()) == Conexao.OUTROERRO) {
            opcao = 0;
            return false;
        }
        if (opcao == 1) {
            Object[] botoes = {"Sim", "Não"};
            int mensagem = JOptionPane.showOptionDialog(null, "Existe dependencia de dados \n Deseja inativar o cadastro",
                    "Sair", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes, botoes[0]);
            if (mensagem == JOptionPane.YES_OPTION) {

                Conexao.executaSql("execute procedure inativastatus(" + pedVendaPer.getPedvencod() + ",'pedvenda','pedvencod','pedvenstatus') ");
                habilitarBotoes(true);
                habilitaCampos(true);
                limparPesquisa();
                return false;

            }
            if (mensagem == JOptionPane.NO_OPTION) {
                habilitaCampos(true);
                return false;
            }


        }
        return true;

    }

    @Override
    protected void consultarTipo() {
        if (telaconsultacli == null) {
            telaconsultacli = new TelaConsMov("Consulta Clientes ", this, new int[]{1, 2, 4}, new String[]{"Codigo", "Nome", "CPF/CNPJ"}, "cliente", "clicod", "clinome", "clicpf", "clicnpj");
            colu.add("CODIGO");
            colu.add("NOME");

            telaconsultacli.setColu(colu);
            telaconsultacli.addInternalFrameListener(this);
            TelaSistema.jdp.add(telaconsultacli);
            telaconsultacli.setVisible(true);
            tipoOperacao = PESQUISANDO;
        }
        TelaSistema.jdp.moveToFront(telaconsultacli);
    }

    @Override
    public void dadosConsultaTipo(int codigo) {

        String sql = null;
        ResultSet rs = null;
        if (tipoOperacao == PESQUISANDO || tipoOperacao == INCLUINDO) {
            sql = "select clicod,clinome  from cliente where clicod = " + codigo + " and clistatus != 0";
            try {
                rs = Conexao.executaQuery(sql);

                rs.next();
                if (tipoOperacao == PESQUISANDO) {
                    acaComboTipo.setValor(0);
                }
                acaTextfieldCodigo.setText(rs.getString(1));
                acaTextfieldNome.setText(rs.getString(2));

            } catch (Exception e) {
            }
        } else {
            sql = "SELECT C1.CLINOME,C1.CLIENDERECO,C1.CLIBAIRRO,C1.CLICEP,c1.CLINUMERO,C1.CLISTATUS,E1.ESTNOME, C2.CIDNOME, C1.CLICOD, p2.pedvenvlrtotal FROM "
                    + "CLIENTE C1,ESTADO E1,CIDADE C2,PEDVENDA P2 WHERE E1.ESTCOD = C2.ESTCOD AND C2.CIDCOD = C1.CIDCOD AND  P2.CLICOD = C1.CLICOD AND P2.PEDVENCOD = " + codigo + "";
            try {
                rs = Conexao.executaQuery(sql);

                rs.next();
                if (rs.getInt(6) == 1) {

                    acaTextfieldNome.setText(rs.getString(1));


                    if (tipoOperacao != INCLUINDO) {
                        acaComboTipo.setValor(rs.getInt(9));

                        acaMonetarioTotal.setValor(rs.getDouble(10));
                    } else {
                    }
                    acaTextfieldCodigo.setText(String.valueOf(acaComboTipo.getValor()));

                } else {
                    JOptionPane.showMessageDialog(null, "Foi encontrado um problema no cadastro do cliente \n verivique o status");
                    return;
                }
            } catch (Exception e) {
            }
        }
        System.out.println(sql);

        tipoOperacao = INCLUINDO;
    }
//    @Override
//    public void dadosConsultaTipo(int codigo) {
//        String sql;
//        ResultSet rs = null;
//        if (tipoOperacao == INCLUINDO) {
//            sql = "SELECT C1.CLINOME,C1.CLIENDERECO,C1.CLIBAIRRO,C1.CLICEP, C1.CLINUMERO,C1.CLISTATUS,E1.ESTNOME, c2.cidnome "
//                    + "FROM CLIENTE C1, ESTADO E1,CIDADE C2 WHERE C1.CIDCOD = C2.CIDCOD AND C2.ESTCOD = E1.ESTCOD and c1.clicod = " + codigo + "";
//        } else {
//            sql = "SELECT C1.CLINOME,C1.CLIENDERECO,C1.CLIBAIRRO,C1.CLICEP,c1.CLINUMERO,C1.CLISTATUS,E1.ESTNOME, C2.CIDNOME, C1.CLICOD, p2.pedvenvlrtotal FROM "
//                    + "CLIENTE C1,ESTADO E1,CIDADE C2,PEDVENDA P2 WHERE E1.ESTCOD = C2.ESTCOD AND C2.CIDCOD = C1.CIDCOD AND  P2.CLICOD = C1.CLICOD AND P2.PEDVENCOD = " + codigo + "";
//
//        }
//        System.out.println(sql);
//        try {
//            rs = Conexao.executaQuery(sql);
//
//            rs.next();
//            if (rs.getInt(6) == 1) {
//
//                acaTextfieldNome.setText(rs.getString(1));
//
//
//                if (tipoOperacao != INCLUINDO) {
//                    acaComboTipo.setValor(rs.getInt(9));
//
//                    acaMonetarioTotal.setValor(rs.getDouble(10));
//                } else {
//                }
//                acaTextfieldCodigo.setText(String.valueOf(acaComboTipo.getValor()));
//
//            } else {
//                JOptionPane.showMessageDialog(null, "Foi encontrado um problema no cadastro do cliente \n verivique o status");
//                return;
//            }
//        } catch (Exception e) {
//        }
//    }

    @Override
    public void dadosConsultaItens(int codigo) {
        prodPer.setProdcod(codigo);
        ResultSet rs = null;
        try {
            rs = Conexao.executaQuery(prodPer.getConsultaTodos());
            rs.next();
            acaTextfieldCodItens.setText(rs.getString(1));
            acaTextfieldDes.setText(rs.getString(2));
            acaMonetarioUnit.setValor(rs.getDouble(3));

        } catch (Exception e) {
        }



    }

    public boolean validaItens(int codigo) {
        for (int x = 0; x < dtm.getRowCount(); x++) {
            if (Integer.parseInt((String) dtm.getValueAt(x, 0)) == codigo) {
                return true;
            }

        }
        return false;

    }

    public void addItem() {

        Object[] item = {acaTextfieldCodItens.getText(), acaTextfieldDes.getText().toUpperCase(), acaTextfieldQtde.getText(), acaMonetarioUnit.getValor(), acaMonetarioSubtot.getValor()};
        PrencheTabela(item);
        //acaTextfieldTotal.setText(String.valueOf(calculaTotal()));
    }

    public void PrencheTabela(Object[] item) {
        dtm = (DefaultTableModel) jtTabela.getModel();
        dtm.addRow(item);

        jtTabela.setModel(dtm);
    }

    public void limparPesquisa() {
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
    }

    public void calculaTotal() {
        acaMonetarioTotal.setValor(0.00);
        double soma = 0.00;
        double valor = 0;
        for (int x = 0; x < dtm.getRowCount(); x++) {

            if (!acaMonetarioTotal.getText().equals("")) {
                soma = soma + Double.parseDouble(jtTabela.getValueAt(x, 4).toString().replace(",", "."));
                acaMonetarioTotal.setValor(soma);
            }
        }

    }

    public void calculaUnitario() {
        int quantidade = Integer.parseInt(acaTextfieldQtde.getText());
        double valUnitario = Double.parseDouble(acaMonetarioUnit.getValor());
        double valTotal = ((int) ((quantidade * valUnitario) * 100.0)) / 100.0;
        acaMonetarioSubtot.setValor(valTotal);
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
        acaTextfieldCodigoPed = new componentes.AcaTextfield(false, "codigo", 1);
        acaLabel2 = new componentes.AcaLabel();
        acaLabel3 = new componentes.AcaLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaObs = new javax.swing.JTextArea();
        acaLabel12 = new componentes.AcaLabel();
        acaDataPed = new componentes.AcaData(false,"Data do pedido");
        acaDataPrevPed = new componentes.AcaData(false,"Data Prevista");
        jPanel2 = new javax.swing.JPanel();
        acaLabelSel = new componentes.AcaLabel();
        acaComboProd = new componentes.AcaCombo(true, "teste");
        acaComboProd.buscaResult("select prodcod,prodnome from produto where prodstatus = 1");
        acaBotoesConProd = new componentes.AcaBotoes(false, "", "Consultar Itens");
        acaBotoesNovoProd = new componentes.AcaBotoes(false,"","Novo Produto");
        acaLabel5 = new componentes.AcaLabel();
        acaLabel6 = new componentes.AcaLabel();
        acaLabel7 = new componentes.AcaLabel();
        acaLabel8 = new componentes.AcaLabel();
        acaLabel9 = new componentes.AcaLabel();
        acaTextfieldCodItens = new componentes.AcaTextfield();
        acaTextfieldDes = new componentes.AcaTextfield();
        acaTextfieldQtde = new componentes.AcaTextfield(false, "", 1);
        acaMonetarioUnit = new componentes.AcaMonetario();
        acaLabel10 = new componentes.AcaLabel();
        acaLabel11 = new componentes.AcaLabel();
        acaMonetarioSubtot = new componentes.AcaMonetario();
        acaBotoesAddItes = new componentes.AcaBotoes(false,"","");
        acaBotoesDelItens = new componentes.AcaBotoes(false,null,null);
        jScrollPane1 = new javax.swing.JScrollPane(jtTabela);
        acaBotoesDelSelIten = new componentes.AcaBotoes(false,"","Apagar item selecionado");

        setPreferredSize(new java.awt.Dimension(1068, 750));
        setSize(new java.awt.Dimension(1068, 750));
        setTitle("Pedido de Vendas");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        acaLabel1.setText("Codigo");

        acaLabel2.setText("Data do Pedido");

        acaLabel3.setText("Observações");

        jTextAreaObs.setColumns(20);
        jTextAreaObs.setRows(5);
        jScrollPane2.setViewportView(jTextAreaObs);

        acaLabel12.setText("Data Prevista");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(acaLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaTextfieldCodigoPed, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(acaDataPed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(acaLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaDataPrevPed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(acaLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(95, 95, 95))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acaLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(acaTextfieldCodigoPed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(acaDataPed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(acaDataPrevPed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Itens do pedido"));

        acaLabelSel.setText("Selecione o produto");

        acaComboProd.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                acaComboProdItemStateChanged(evt);
            }
        });

        acaBotoesConProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/search.png"))); // NOI18N

        acaBotoesNovoProd.setText("Novo");

        acaLabel5.setText("Codigo");

        acaLabel6.setText("Descrição");

        acaLabel7.setText("Quantidade");

        acaLabel8.setText("Valor Unitário");

        acaLabel9.setText("Subtotal");

        acaTextfieldCodItens.setEditable(false);
        acaTextfieldCodItens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaTextfieldCodItensActionPerformed(evt);
            }
        });

        acaTextfieldDes.setEditable(false);

        acaMonetarioUnit.setEditable(false);
        acaMonetarioUnit.setText("acaMonetario1");

        acaLabel10.setText("R$");
        acaLabel10.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N

        acaLabel11.setText("R$");
        acaLabel11.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N

        acaMonetarioSubtot.setText("acaMonetario2");

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

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabela de Itens do Pedido"));

        acaBotoesDelSelIten.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/edit_remove.png"))); // NOI18N
        acaBotoesDelSelIten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaBotoesDelSelItenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(225, 225, 225)
                        .addComponent(acaBotoesDelSelIten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(acaLabelSel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(acaComboProd, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(acaBotoesConProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(acaBotoesNovoProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acaTextfieldCodItens, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acaTextfieldDes, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(acaTextfieldQtde, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(acaLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(acaLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(acaMonetarioUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(acaLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(acaLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acaLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaMonetarioSubtot, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acaBotoesAddItes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaBotoesDelItens, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(acaComboProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(acaLabelSel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acaBotoesNovoProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaBotoesConProd, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(acaLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(acaTextfieldCodItens, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(acaLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(acaLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(acaLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(5, 5, 5))
                                .addComponent(acaBotoesAddItes, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(acaLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(acaTextfieldQtde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(acaTextfieldDes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(acaLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaMonetarioUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaMonetarioSubtot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaBotoesDelItens, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(acaBotoesDelSelIten, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getPainel());
        getPainel().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(134, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void acaTextfieldCodItensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaTextfieldCodItensActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_acaTextfieldCodItensActionPerformed

    private void acaComboProdItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_acaComboProdItemStateChanged
        if (acaComboProd.getSelectedIndex() >= 0) {
            if (acaComboProd.isPopupVisible()) {
                if (!validaItens(acaComboProd.getValor())) {
                    acaTextfieldCodItens.setText(String.valueOf(acaComboProd.getValor()));
                    acaTextfieldDes.setText(acaComboProd.getSelectedItem().toString());

                    try {
                        ResultSet rs = Conexao.executaQuery("select  prodvalor from produto where prodcod = " + acaComboProd.getValor() + "");
                        rs.next();
                        acaMonetarioUnit.setValor(rs.getDouble(1));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ja existe o produto no pedido");
                    acaComboProd.setSelectedIndex(0);
                    return;
                }
            }

        }
    }//GEN-LAST:event_acaComboProdItemStateChanged

    private void acaBotoesAddItesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaBotoesAddItesActionPerformed
        if (acaTextfieldQtde.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe a quantidade");

            return;
        } else {
            addItem();

            // acaMonetarioTotal.setValor(Double.parseDouble(calculaTotal(acaMonetarioVlrUnit.getValor().toString())));
            acaTextfieldCodItens.setText("");
            acaTextfieldDes.setText("");
            acaTextfieldQtde.setText("");
            acaMonetarioSubtot.setValor(0.00);
            acaMonetarioUnit.setValor(0.00);
            acaComboProd.setValor(0);
            calculaTotal();
        }
    }//GEN-LAST:event_acaBotoesAddItesActionPerformed

    private void acaBotoesDelItensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaBotoesDelItensActionPerformed
        acaTextfieldCodItens.setText("");
        acaTextfieldDes.setText("");
        acaMonetarioUnit.setValor(0.00);
        acaTextfieldQtde.setText("");
        acaComboProd.setSelectedIndex(0);
        acaMonetarioSubtot.setValor(0.00);
        if (!acaMonetarioSubtot.getValor().equals("")) {
            calculaTotal();

        }
    }//GEN-LAST:event_acaBotoesDelItensActionPerformed

    private void acaBotoesDelSelItenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaBotoesDelSelItenActionPerformed
        if (tipoOperacao == ALTERANDO) {
            itePedVenda.setVencod(Integer.parseInt(acaTextfieldCodItens.getText()));
            itePedVenda.setProdcod(Integer.parseInt(dtm.getValueAt(jtTabela.getSelectedRow(), 0).toString()));
            Conexao.executaSql(itePedVenda.getExcluiSql());
            dtm.removeRow(jtTabela.getSelectedRow());
            calculaTotal();
        } else {
            dtm.removeRow(jtTabela.getSelectedRow());
            //acaMonetarioTotal.setValor(Double.parseDouble(calculaTotal(acaMonetarioVlrUnit.getValor().toString())));
            calculaTotal();
        }
    }//GEN-LAST:event_acaBotoesDelSelItenActionPerformed

    public void internalFrameOpened(InternalFrameEvent e) {
    }

    public void internalFrameClosing(InternalFrameEvent e) {
    }

    public void internalFrameClosed(InternalFrameEvent e) {
        if (e.getSource() == telacliente) {
            telacliente = null;
        }
        if (e.getSource() == consultaPed) {
            consultaPed = null;
        }
        if (e.getSource() == telaconsultacli) {
            telaconsultacli = null;
        }
        if (e.getSource() == telaproduto) {
            telamatprima = null;
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private componentes.AcaBotoes acaBotoesAddItes;
    public componentes.AcaBotoes acaBotoesConProd;
    private componentes.AcaBotoes acaBotoesDelItens;
    private componentes.AcaBotoes acaBotoesDelSelIten;
    public componentes.AcaBotoes acaBotoesNovoProd;
    public componentes.AcaCombo acaComboProd;
    protected componentes.AcaData acaDataPed;
    protected componentes.AcaData acaDataPrevPed;
    private componentes.AcaLabel acaLabel1;
    private componentes.AcaLabel acaLabel10;
    private componentes.AcaLabel acaLabel11;
    private componentes.AcaLabel acaLabel12;
    private componentes.AcaLabel acaLabel2;
    public componentes.AcaLabel acaLabel3;
    private componentes.AcaLabel acaLabel5;
    private componentes.AcaLabel acaLabel6;
    private componentes.AcaLabel acaLabel7;
    private componentes.AcaLabel acaLabel8;
    private componentes.AcaLabel acaLabel9;
    public componentes.AcaLabel acaLabelSel;
    private componentes.AcaMonetario acaMonetarioSubtot;
    protected componentes.AcaMonetario acaMonetarioUnit;
    protected componentes.AcaTextfield acaTextfieldCodItens;
    protected componentes.AcaTextfield acaTextfieldCodigoPed;
    protected componentes.AcaTextfield acaTextfieldDes;
    private componentes.AcaTextfield acaTextfieldQtde;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTextArea jTextAreaObs;
    // End of variables declaration//GEN-END:variables

    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            acaTextfieldCodItens.setText((String) dtm.getValueAt(jtTabela.getSelectedRow(), 0));
            acaTextfieldDes.setText((String) dtm.getValueAt(jtTabela.getSelectedRow(), 1));
            acaTextfieldQtde.setText((String) dtm.getValueAt(jtTabela.getSelectedRow(), 2));
            System.out.println(dtm.getValueAt(jtTabela.getSelectedRow(), 3));
            System.out.println(dtm.getValueAt(jtTabela.getSelectedRow(), 4));
            acaMonetarioUnit.setValor((Double.parseDouble(dtm.getValueAt(jtTabela.getSelectedRow(), 3).toString().replace(",", "."))));
            acaMonetarioSubtot.setValor(Double.parseDouble(dtm.getValueAt(jtTabela.getSelectedRow(), 4).toString().replace(",", ".")));
//            String teste = dtm.getValueAt(jtTabela.getSelectedRow(), 4).toString().replace(",", "");
//            acaMonetarioSubTot.setValor(Double.parseDouble(teste));
            dtm.removeRow(jtTabela.getSelectedRow());

            calculaTotal();
            //acaMonetarioTotal.setValor(Double.parseDouble(calculaTotal(acaMonetarioVlrUnit.getValor().toString())));
        }
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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == acaBotoesNovoProd) {
            if (telaproduto == null) {
                telaproduto = new TelaProduto();
                telaproduto.addInternalFrameListener(this);
                TelaSistema.jdp.add(telaproduto);
                telaproduto.setVisible(true);
            }
            TelaSistema.jdp.moveToFront(telaproduto);

        }
        if (e.getSource() == acaBotoesConProd) {
            if (telaconsultacli == null) {
                telaconsultacli = new TelaConsMov("Consulta Produto", this, new int[]{1, 2}, new String[]{"Codigo", "Nome"}, "produto", "prodcod", "prodnome", null, true);
                colu.addElement("CODIGO");
                colu.add("NOME");
                telaconsultacli.setColu(colu);
                //consulta.setTodosB(true);
                telaconsultacli.addInternalFrameListener(this);
                tipoOperacao = PESQUISANDO;
                TelaSistema.jdp.add(telaconsultacli);
                telaconsultacli.setVisible(true);
            }
            TelaSistema.jdp.moveToFront(telaconsultacli);
        }
    }
}

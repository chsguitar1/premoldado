/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TelaFornecedor.java
 *
 * Created on 17/07/2010, 16:56:16
 */
package Telacadastros;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import modelos.ModeloTabela;
//import org.jdesktop.swingx.renderer.DefaultTableRenderer;
import persistencia.Fornecedor;
import persistencia.TipoXFornecedor;
import Banco.Conexao;
import premoldados.TelaSistema;
import Telaconsulta.TelaConsulta;
import ajuda.PlayMovie;
import componentes.AcaListaMult;
import componentes.AcaTabela;
import componentes.Tabela;
import componentes.TelaForxTipo;

/**
 *
 * @author chs
 */
public class TelaFornecedor extends TelaCadastro implements InternalFrameListener {

    public Fornecedor fornecedorper = new Fornecedor();
    public TipoXFornecedor tipoper = new TipoXFornecedor();
    TelaCidade telacidade;
    TelaConsulta consulta;
    TelaForxTipo forxtipo;
    public Vector colu = new Vector();
    public Tabela tab = new Tabela() ;
  
    
    

    /** Creates new form BeanForm */
    public TelaFornecedor() {
        
        initComponents();
        campos.add(acaTextfieldNome);
        campos.add(acaTextfieldCodigo);
        campos.add(acaTextfieldEnd);
        campos.add(acaTextfieldNum);
        campos.add(acaTextfieldBairo);
        campos.add(acaTextfieldCompl);
        campos.add(formatadoTelFor);
        campos.add(formatadoTelCel);
        campos.add(formatadoCepFor);
        campos.add(formatadoCpfCnpjFor);
        campos.add(acaTextfieldNome);
        campos.add(acaComboEstado);
        campos.add(acaComboCidade);
        campos.add(acaComboTipo);
        campos.add(formatadoRgIeFor);
        campos.add(formatadoTelFax);
        campos.add(formatadoCpfCnpjFor);
        campos.add(formatadoRgIeFor);
        campos.add(acaTextfieldEmail);
        campos.add(acaTextfieldContado);
        campos.add(acaComboStatus);
        campos.add(acaTextfieldOe);
        campos.add(acaBotoesNovo);
        campos.add(acaListTipos);
       
     
       
//       acaTabelaTipos.montaTabela(new String[]{"Codigo","Tipo","Selecionar"}, new int[]{50,70,80});
//       acaTabelaTipos.montaTabela("select tipomatcod,tipomatfor from tipomateria");
//      JCheckBox box = new JCheckBox();

        habilitaCampos(false);
    }

    public void tabelaTipos() {
tab.montaTabela("select tipomatfor from tipomateria");
//        colu.add("Codigo");
//        colu.add("Nome");
//        colu.add("Selecionar");
//        tab.tabelaSeleciona(colu,new int[]{50,100,60}, "select tipomatcod,tipomatfor from tipomateria");

     
//        dtm.setColunas(colu);
//        dtm = new ModeloTabela("select tipomatcod,tipomatfor from tipomateria");
//
//    tab.setModel(dtm);

              




    }

    public void obterCampos() {
        if (tipoOperacao == ALTERANDO) {
            fornecedorper.setForcod(Integer.parseInt(acaTextfieldCodigo.getText()));
        }

        fornecedorper.setCidcod(acaComboCidade.getValor());
        fornecedorper.setFortipo(acaComboTipo.getValor());
        fornecedorper.setFonome(acaTextfieldNome.getText().toUpperCase());
        fornecedorper.setForendereco(acaTextfieldEnd.getText().toUpperCase());
        fornecedorper.setFornumero(acaTextfieldNum.getText().toUpperCase());
        fornecedorper.setForbairro(acaTextfieldBairo.getText().toUpperCase());
        fornecedorper.setForcompl(acaTextfieldCompl.getText().toUpperCase());
        fornecedorper.setForcep(formatadoCepFor.getTexto());
        fornecedorper.setFortel(formatadoTelFor.getTexto());
        fornecedorper.setForcel(formatadoTelCel.getTexto());
        fornecedorper.setForemail(acaTextfieldEmail.getText().toUpperCase());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        formatter.format(new Date());
        fornecedorper.setFordata(formatter.format(new Date()).replace("/", "."));
        fornecedorper.setForcontato(acaTextfieldContado.getText().toUpperCase());


        if (acaComboTipo.getValor() == 1) {
            fornecedorper.setForcpf(formatadoCpfCnpjFor.getTexto());
            fornecedorper.setForrg(formatadoRgIeFor.getTexto());
            fornecedorper.setForoe(acaTextfieldOe.getText().toUpperCase());
            fornecedorper.setForcnpj(" ");
            fornecedorper.setForie(" ");
        }
        if (acaComboTipo.getValor() == 2) {
            fornecedorper.setForcnpj(formatadoCpfCnpjFor.getTexto());
            fornecedorper.setForie(formatadoRgIeFor.getTexto());
            fornecedorper.setForcpf(" ");
            fornecedorper.setForrg(" ");
            fornecedorper.setForoe(" ");
        }
        fornecedorper.setCidcod(acaComboCidade.getValor());
        fornecedorper.setForstatus(acaComboStatus.getValor());
        fornecedorper.setForfax(formatadoTelFax.getTexto());
        if (tipoOperacao == EXCLUINDO) {
            fornecedorper.setForcod(Integer.parseInt(acaTextfieldCodigo.getText()));
        }
    }

    public void preencherCampos() {
        setaLabel(fornecedorper.getFortipo());
        acaTextfieldCodigo.setText(String.valueOf(fornecedorper.getForcod()));
        acaTextfieldNome.setText(fornecedorper.getFonome());
        acaTextfieldEnd.setText(fornecedorper.getForendereco());
        acaTextfieldNum.setText(fornecedorper.getFornumero());
        acaTextfieldBairo.setText(fornecedorper.getForbairro());
        acaTextfieldCompl.setText(fornecedorper.getForcompl());
        acaTextfieldContado.setText(fornecedorper.getForcontato());
        acaTextfieldEmail.setText(fornecedorper.getForemail());

        formatadoCepFor.setText(fornecedorper.getForcep());
        formatadoTelFor.setText(fornecedorper.getFortel());
        formatadoTelCel.setText(fornecedorper.getForcel());
        formatadoTelFax.setText(fornecedorper.getForfax());
        acaComboTipo.setValor(fornecedorper.getFortipo());
        if (fornecedorper.getFortipo() == 1) {
            formatadoCpfCnpjFor.setValor(fornecedorper.getForcpf());
            formatadoRgIeFor.setTexto(fornecedorper.getForrg());
            acaTextfieldOe.setText(fornecedorper.getForoe());
        } else {
            formatadoCpfCnpjFor.setValor(fornecedorper.getForcnpj());
            formatadoRgIeFor.setTexto(fornecedorper.getForie());
        }
        acaComboStatus.setValor(fornecedorper.getForstatus());
        try {
            ResultSet rs = Conexao.executaQuery("execute procedure comboestador(" + fornecedorper.getCidcod() + ")");
            rs.next();
            int codigoEstado = rs.getInt(1);
            acaComboEstado.buscaResult("select estcod,estnome from estado");
            acaComboEstado.setValor(codigoEstado);
        } catch (SQLException a) {
        }
        acaComboCidade.buscaResult("select cidcod,cidnome from cidade");
        acaComboCidade.setValor(fornecedorper.getCidcod());

    }

    @Override
    protected void incluir() {
        super.incluir();
        campos.removeElement(acaTextfieldCodigo);
        acaComboStatus.setValor(1);
    }

    @Override
    protected void cancelar() {
        super.cancelar();
        acaComboTipo.setValor(1);
        acaComboCidade.removeAllItems();
        campos.addElement(acaTextfieldCodigo);
        setaLabel(acaComboTipo.getValor());
    }

    @Override
    protected void consultar() {
        if (consulta == null) {
            consulta = new TelaConsulta("Consulta Fornecedor ", this, new int[]{1, 2, 4}, new String[]{"Codigo", "Nome", "CPF/CNPJ"}, "fornecedor", "forcod", "fonome", "forcpf", "forcnpj");
            colu.add("CODIGO");
            colu.add("NOME");

            consulta.setColu(colu);
            consulta.addInternalFrameListener(this);
            TelaSistema.jdp.add(consulta);
            consulta.setVisible(true);
            tipoOperacao = PESQUISANDO;
        }
        TelaSistema.jdp.moveToFront(consulta);
    }
  
    @Override
    protected boolean incluirBD() {
        acaListTipos.getValor(this);
         for(int x = 0 ;x < tipos.size();x++){
                    //System.out.println(tipos.get(x));
                    }

        obterCampos();
       // System.out.println(fornecedorper.fortipo);


        if (fornecedorper.verificaDuplicidade()) {
                ResultSet rs = null;
            if ((acaComboTipo.getValor() == 1) && (formatadoCpfCnpjFor.validaCPF() == true)) {
            
                try {
                    rs = fornecedorper.getInsereRs();
                    rs.next();
                    tipoper.setForcod(rs.getInt(1));
                    for(int x = 0 ;x < tipos.size();x++){
                    tipoper.setTipomatcod(Integer.parseInt(tipos.get(x).toString()));
                    }
                } catch (SQLException ex) {

                }
                return true;
            }
            if ((acaComboTipo.getValor() == 2) && (formatadoCpfCnpjFor.validaCNPJ() == true)) {
               
                try {
                      rs = fornecedorper.getInsereRs();
                    rs.next();
                    tipoper.setForcod(rs.getInt(1));
                   // JOptionPane.showMessageDialog(null, "persistencia"+tipoper.getForcod());
                    for(int x = 0 ;x < tipos.size();x++){
                    tipoper.setTipomatcod(Integer.parseInt(tipos.get(x).toString()));
                    //System.out.println(tipoper.getInsereSql());
                    Conexao.executaSql(tipoper.getInsereSql());
                    }
                } catch (SQLException ex) {

                }
                return true;

            }
        }

        return false;
    }

    @Override
    protected boolean alterarBD() {
        fornecedorper.setForcod(Integer.parseInt(acaTextfieldCodigo.getText()));
        obterCampos();
        if (fornecedorper.verificaDuplicidadeAtualizar()) {
            Conexao.executaSql(fornecedorper.getAtualizaSql());
            return true;
        }
        return false;

    }

    @Override
    public boolean excluirBD() {
        obterCampos();
        if ((Conexao.executaSqlExcluir(fornecedorper.getExcluiSql()) == Conexao.DEPENDENCIA) || (Conexao.executaSqlExcluir(fornecedorper.getExcluiSql()) == Conexao.OUTROERRO)) {
            habilitaCampos(false);
            habilitaBotoesConsulta(true);
            return false;
        } else {
            return true;
        }
    }
  @Override
    protected void ajuda() {
        PlayMovie videos = new PlayMovie("fornecedor.wmv");


    }
    @Override
    public void consultarDados(int codigo) {

        try {
            fornecedorper.setForcod(codigo);
            ResultSet rs = Conexao.executaQuery(fornecedorper.getConsultaTodos());
            rs.next();
            fornecedorper.setForcod(rs.getInt(1));

            fornecedorper.setCidcod(rs.getInt(2));
            fornecedorper.setFonome(rs.getString(3));
            fornecedorper.setForendereco(rs.getString(4));
            fornecedorper.setFornumero(rs.getString(5));
            fornecedorper.setForbairro(rs.getString(6));
            fornecedorper.setForcompl(rs.getString(7));
            fornecedorper.setForcep(rs.getString(8));
            fornecedorper.setFortel(rs.getString(9));
            fornecedorper.setForcel(rs.getString(10));
            fornecedorper.setForfax(rs.getString(11));
            fornecedorper.setForemail(rs.getString(12));
            fornecedorper.setFordata(rs.getString(13));
            fornecedorper.setForcpf(rs.getString(14));
            fornecedorper.setForrg(rs.getString(15));
            fornecedorper.setForcnpj(rs.getString(16));
            fornecedorper.setForie(rs.getString(17));
            fornecedorper.setForoe(rs.getString(18));
            fornecedorper.setFortipo(rs.getInt(19));
            fornecedorper.setForcontato(rs.getString(20));
            fornecedorper.setForstatus(rs.getInt(21));
            preencherCampos();

            habilitaCampos(false);
            habilitaBotoesConsulta(true);


        } catch (SQLException e) {
            e.printStackTrace();
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

        jPanelDados1 = new javax.swing.JPanel();
        acaLabelEnd = new componentes.AcaLabel();
        acaTextfieldEnd = new componentes.AcaTextfield(true,"Endereço",1);
        acaLabelNum = new componentes.AcaLabel();
        acaTextfieldNum = new componentes.AcaTextfield(true,"Numero",1);
        acaLabelBairro = new componentes.AcaLabel();
        acaTextfieldBairo = new componentes.AcaTextfield(true, "Bairro",1);
        acaLabelEstado = new componentes.AcaLabel();
        acaComboEstado = new componentes.AcaCombo();
        acaComboEstado.buscaResult("select estcod,estnome from estado order by estnome asc");
        acaLabelCidade = new componentes.AcaLabel();
        acaComboCidade = new componentes.AcaCombo();
        acaLabelCep = new componentes.AcaLabel();
        acaLabelEmail = new componentes.AcaLabel();
        acaTextfieldEmail = new componentes.AcaTextfield(true,"Email",1);
        acaLabelCompl = new componentes.AcaLabel();
        acaTextfieldCompl = new componentes.AcaTextfield(true,"Complemento",1);
        formatadoCepFor = new componentes.FormatadoCep(true,"Cep",1);
        acaBotoesNovo = new componentes.AcaBotoes(false, "Novo", "Nova Cidade");
        jPanelDados = new javax.swing.JPanel();
        acaLabelCodigo = new componentes.AcaLabel();
        acaTextfieldCodigo = new componentes.AcaTextfield();
        acaLabelTipo = new componentes.AcaLabel();
        acaComboTipo = new componentes.AcaCombo();
        acaComboTipo.comboFiltro(new int[]{1,2}, new String[]{"Fisica","Juridica"});
        acaLabelNome1 = new componentes.AcaLabel();
        acaTextfieldNome = new componentes.AcaTextfield(true,"Nome/Razao Social",1);
        acaLabelId2 = new componentes.AcaLabel();
        acaLabelCel = new componentes.AcaLabel();
        acaLabelId1 = new componentes.AcaLabel();
        acaLabelTel = new componentes.AcaLabel();
        acaLabelContato = new componentes.AcaLabel();
        acaTextfieldContado = new componentes.AcaTextfield(false,"Contato",1);
        acaLabelFax = new componentes.AcaLabel();
        acaLabelOe = new componentes.AcaLabel();
        acaTextfieldOe = new componentes.AcaTextfield(false, "Orgao Expedidor", 1);
        formatadoTelFax = new componentes.FormatadoTel(false,"Fax",1);
        formatadoTelFor = new componentes.FormatadoTel(true,"Telefone",1);
        formatadoTelCel = new componentes.FormatadoTel(false,"celular",1);
        formatadoCpfCnpjFor = new componentes.FormatadoCpfCnpj("CPF",true,"Cpf/Cnpj",1);
        formatadoRgIeFor = new componentes.FormatadoRgIe("RG",false,"Rg/Inscrição Estadual",1);
        acaLabel1 = new componentes.AcaLabel();
        acaComboStatus = new componentes.AcaCombo();
        acaComboStatus.comboFiltro(new int[]{0,1,2},new String[]{"Inativo","Ativo","Desqualificado"});
        acaComboStatus.setSelectedIndex(-1);
        jScrollPane1 = new javax.swing.JScrollPane();
        acaListTipos = new componentes.AcaList("select tipomatcod,tipomatfor from tipomateria",false,"Tipo de Materiais");

        setPreferredSize(new java.awt.Dimension(930, 600));
        setSize(new java.awt.Dimension(930, 600));
        setTitle("Cadastro de Fornecedores");

        jPanelDados1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        acaLabelEnd.setText("Endereço");

        acaLabelNum.setText("Numero");

        acaLabelBairro.setText("Bairro");

        acaLabelEstado.setText("Estado");

        acaComboEstado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                acaComboEstadoItemStateChanged(evt);
            }
        });

        acaLabelCidade.setText("Cidade");

        acaComboCidade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                acaComboCidadeFocusGained(evt);
            }
        });

        acaLabelCep.setText("Cep.");

        acaLabelEmail.setText("Email");

        acaLabelCompl.setText("Compl.");

        acaBotoesNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaBotoesNovoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelDados1Layout = new javax.swing.GroupLayout(jPanelDados1);
        jPanelDados1.setLayout(jPanelDados1Layout);
        jPanelDados1Layout.setHorizontalGroup(
            jPanelDados1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDados1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDados1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(acaLabelEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabelCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabelEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelDados1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(acaTextfieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelDados1Layout.createSequentialGroup()
                        .addComponent(acaComboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(133, 133, 133)
                        .addComponent(acaLabelCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(acaComboCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(acaBotoesNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(formatadoCepFor, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelDados1Layout.createSequentialGroup()
                        .addGap(223, 223, 223)
                        .addComponent(acaLabelCompl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(acaTextfieldCompl, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelDados1Layout.createSequentialGroup()
                        .addComponent(acaTextfieldEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(acaLabelNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(acaTextfieldNum, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(acaLabelBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(acaTextfieldBairo, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanelDados1Layout.setVerticalGroup(
            jPanelDados1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDados1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDados1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acaLabelEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaTextfieldEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabelBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaTextfieldBairo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabelNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaTextfieldNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDados1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDados1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(formatadoCepFor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(acaLabelCompl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(acaTextfieldCompl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(acaLabelCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDados1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(acaLabelEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaComboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelDados1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(acaComboCidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(acaBotoesNovo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(acaLabelCidade, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addGroup(jPanelDados1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acaLabelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaTextfieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanelDados.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelDados.setPreferredSize(new java.awt.Dimension(800, 208));

        acaLabelCodigo.setText("Codigo");

        acaTextfieldCodigo.setEditable(false);

        acaLabelTipo.setText("Tipo");

        acaComboTipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                acaComboTipoItemStateChanged(evt);
            }
        });

        acaLabelNome1.setText("Nome");

        acaTextfieldNome.setMinimumSize(new java.awt.Dimension(280, 21));

        acaLabelId2.setText("Rg");

        acaLabelCel.setText("Celular");

        acaLabelId1.setText("Cpf");

        acaLabelTel.setText("Telefone");

        acaLabelContato.setText("Contato");

        acaLabelFax.setText("Fax");

        acaLabelOe.setText("O. E.");

        acaLabel1.setText("Status");

        acaListTipos.setBorder(javax.swing.BorderFactory.createTitledBorder("Materiais Fornecidos"));
        acaListTipos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane1.setViewportView(acaListTipos);

        javax.swing.GroupLayout jPanelDadosLayout = new javax.swing.GroupLayout(jPanelDados);
        jPanelDados.setLayout(jPanelDadosLayout);
        jPanelDadosLayout.setHorizontalGroup(
            jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDadosLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(acaLabelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabelNome1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabelContato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabelId1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabelId2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(acaTextfieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(acaTextfieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(acaLabelTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(acaComboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(acaLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(acaComboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelDadosLayout.createSequentialGroup()
                                .addComponent(acaTextfieldContado, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(acaLabelTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanelDadosLayout.createSequentialGroup()
                                    .addComponent(formatadoCpfCnpjFor, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(acaLabelFax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(formatadoTelFax, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanelDadosLayout.createSequentialGroup()
                                    .addComponent(formatadoRgIeFor, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(acaLabelOe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(acaTextfieldOe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelDadosLayout.createSequentialGroup()
                                .addComponent(formatadoTelFor, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(acaLabelCel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(formatadoTelCel, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1))))
                .addContainerGap())
        );
        jPanelDadosLayout.setVerticalGroup(
            jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acaLabelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabelTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaComboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaComboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaTextfieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acaTextfieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabelNome1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acaTextfieldContado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabelTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(formatadoTelFor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabelCel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(formatadoTelCel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acaLabelContato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(formatadoCpfCnpjFor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaLabelFax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(formatadoTelFax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaLabelId1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(formatadoRgIeFor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaLabelOe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaTextfieldOe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaLabelId2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getPainelCentral());
        getPainelCentral().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanelDados1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelDados, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE))
                .addGap(41, 41, 41))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelDados, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelDados1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void acaBotoesNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaBotoesNovoActionPerformed
        if (telacidade == null) {
            telacidade = new TelaCidade();
            telacidade.addInternalFrameListener(this);
            TelaSistema.jdp.add(telacidade);
            telacidade.setVisible(true);
            //telaestado.setSize(800, 300);
        }
        TelaSistema.jdp.moveToFront(telacidade);
        tipoOperacao = NOVOITEM;
    }//GEN-LAST:event_acaBotoesNovoActionPerformed

    private void acaComboTipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_acaComboTipoItemStateChanged
        if (acaComboTipo.getSelectedIndex() >= 0) {
            if (acaComboTipo.isPopupVisible()) {
                if (acaComboTipo.getValor() == 2) {
                    /*acaLabelOe.setVisible(true);
                    acaLabelOe.setText("O. E.");
                    acaTextfieldOe.setVisible(true);
                    acaLabelId1.setText("Cpf.");
                    acaLabelId2.setText("Rg.");
                    acaLabelNome1.setText("Nome");*/
                    acaLabelOe.setVisible(false);
                    acaTextfieldOe.setVisible(false);
                    acaLabelId1.setText("Cnpj.");
                    acaLabelId2.setText("Ie..");
                    acaLabelNome1.setText("Razão Social");
                    formatadoCpfCnpjFor.setFormatoCNPJ();
                    formatadoRgIeFor.setFormatoIE();

                }
                if (acaComboTipo.getValor() == 1) {
                    acaLabelOe.setVisible(true);
                    acaTextfieldOe.setVisible(true);
                    acaLabelId1.setText("Cpf");
                    acaLabelId2.setText("Rg");
                    acaLabelNome1.setText("Nome");
                    formatadoCpfCnpjFor.setFormatoCPF();
                    formatadoRgIeFor.setFormatoRG();
                }
            }

        }
    }//GEN-LAST:event_acaComboTipoItemStateChanged

    private void acaComboEstadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_acaComboEstadoItemStateChanged
        if (acaComboEstado.getSelectedIndex() >= 0) {
            if (acaComboEstado.isPopupVisible()) {
                acaComboCidade.buscaResult("select cidcod, cidnome from cidade where estcod = " + acaComboEstado.getValor() + " order by cidnome asc");
            }
        }
    }//GEN-LAST:event_acaComboEstadoItemStateChanged

    private void acaComboCidadeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_acaComboCidadeFocusGained
        if (tipoOperacao == NOVOITEM) {
            acaComboCidade.buscaResult("select cidcod, cidnome from cidade");

        }
    }//GEN-LAST:event_acaComboCidadeFocusGained

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private componentes.AcaBotoes acaBotoesNovo;
    private componentes.AcaCombo acaComboCidade;
    private componentes.AcaCombo acaComboEstado;
    private componentes.AcaCombo acaComboStatus;
    private componentes.AcaCombo acaComboTipo;
    private componentes.AcaLabel acaLabel1;
    private componentes.AcaLabel acaLabelBairro;
    private componentes.AcaLabel acaLabelCel;
    private componentes.AcaLabel acaLabelCep;
    private componentes.AcaLabel acaLabelCidade;
    private componentes.AcaLabel acaLabelCodigo;
    private componentes.AcaLabel acaLabelCompl;
    private componentes.AcaLabel acaLabelContato;
    private componentes.AcaLabel acaLabelEmail;
    private componentes.AcaLabel acaLabelEnd;
    private componentes.AcaLabel acaLabelEstado;
    private componentes.AcaLabel acaLabelFax;
    private componentes.AcaLabel acaLabelId1;
    private componentes.AcaLabel acaLabelId2;
    private componentes.AcaLabel acaLabelNome1;
    private componentes.AcaLabel acaLabelNum;
    private componentes.AcaLabel acaLabelOe;
    private componentes.AcaLabel acaLabelTel;
    private componentes.AcaLabel acaLabelTipo;
    private componentes.AcaList acaListTipos;
    private componentes.AcaTextfield acaTextfieldBairo;
    private componentes.AcaTextfield acaTextfieldCodigo;
    private componentes.AcaTextfield acaTextfieldCompl;
    private componentes.AcaTextfield acaTextfieldContado;
    private componentes.AcaTextfield acaTextfieldEmail;
    private componentes.AcaTextfield acaTextfieldEnd;
    private componentes.AcaTextfield acaTextfieldNome;
    private componentes.AcaTextfield acaTextfieldNum;
    private componentes.AcaTextfield acaTextfieldOe;
    private componentes.FormatadoCep formatadoCepFor;
    private componentes.FormatadoCpfCnpj formatadoCpfCnpjFor;
    private componentes.FormatadoRgIe formatadoRgIeFor;
    private componentes.FormatadoTel formatadoTelCel;
    private componentes.FormatadoTel formatadoTelFax;
    private componentes.FormatadoTel formatadoTelFor;
    private javax.swing.JPanel jPanelDados;
    private javax.swing.JPanel jPanelDados1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    public void setaLabel(int tipo) {
        if (tipo == 2) {
            /*acaLabelOe.setVisible(true);
            acaLabelOe.setText("O. E.");
            acaTextfieldOe.setVisible(true);
            acaLabelId1.setText("Cpf.");
            acaLabelId2.setText("Rg.");
            acaLabelNome1.setText("Nome");*/
            acaLabelOe.setVisible(false);
            acaTextfieldOe.setVisible(false);
            acaLabelId1.setText("Cnpj.");
            acaLabelId2.setText("Ie..");
            acaLabelNome1.setText("Razão Social");
            formatadoCpfCnpjFor.setFormatoCNPJ();
            formatadoRgIeFor.setFormatoIE();

        }
        if (tipo == 1) {
            acaLabelOe.setVisible(true);
            acaTextfieldOe.setVisible(true);
            acaLabelId1.setText("Cpf");
            acaLabelId2.setText("Rg");
            formatadoCpfCnpjFor.setFormatoCPF();
            formatadoRgIeFor.setFormatoRG();
        }

    }

    public void internalFrameOpened(InternalFrameEvent e) {
    }

    public void internalFrameClosing(InternalFrameEvent e) {
    }

    public void internalFrameClosed(InternalFrameEvent e) {
        if (e.getSource() == telacidade) {
            telacidade = null;
        }
        if (e.getSource() == consulta) {
            consulta = null;
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

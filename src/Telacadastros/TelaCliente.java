/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TelaCliente.java
 *
 * Created on 16/07/2010, 21:27:53
 */
package Telacadastros;

import ajuda.PlayMovie;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import persistencia.Cliente;
import Banco.Conexao;
import premoldados.TelaSistema;
import Telaconsulta.TelaConsulta;

/**
 *
 * @author chs
 */
public class TelaCliente extends TelaCadastro implements InternalFrameListener {

    TelaCidade telacidade;
    TelaConsulta consulta;
    public Cliente clienteper = new Cliente();
    public Vector colu = new Vector();
    private String link;
    private String media;

    /** Creates new form BeanForm */
    public TelaCliente() {
        initComponents();
        campos.add(acaTextfieldNome1);
        campos.add(acaTextfieldCodigo);
        campos.add(acaTextfieldEnd);
        campos.add(acaTextfieldNum);
        campos.add(acaTextfieldBairo);
        campos.add(acaTextfieldCompl);
        campos.add(formatadoTel1);
        campos.add(formatadoTelCel);
        campos.add(formatadoCepCli);
        campos.add(formatadoCpfCnpjCli);
        campos.add(acaTextfieldNome1);
        campos.add(acaComboEstado);
        campos.add(acaComboCidade);
        campos.add(acaComboTipo);
        campos.add(formatadoRgIe1);
        campos.add(formatadoTelFax);
        campos.add(formatadoCpfCnpjCli);
        campos.add(formatadoRgIe1);
        campos.add(acaTextfieldEmail);
        campos.add(acaTextfieldContado);
        campos.add(acaComboStatus);
        campos.add(acaTextfieldOe);
        campos.add(acaBotoesNovo);


        habilitaCampos(false);
    }

    public void obterCampos() {
        if (tipoOperacao == ALTERANDO) {
            clienteper.setClicod(Integer.parseInt(acaTextfieldCodigo.getText()));
        }
        clienteper.setCidcod(acaComboCidade.getValor());
        clienteper.setClitipo(acaComboTipo.getValor());
        clienteper.setClinome(acaTextfieldNome1.getText().toUpperCase());
        clienteper.setCliendereco(acaTextfieldEnd.getText().toUpperCase());
        clienteper.setClinumero(acaTextfieldNum.getText().toUpperCase());
        clienteper.setClibairro(acaTextfieldBairo.getText().toUpperCase());
        clienteper.setClicompl(acaTextfieldCompl.getText().toUpperCase());
        clienteper.setClicep(formatadoCepCli.getTexto());
        clienteper.setClitel(formatadoTel1.getTexto());
        clienteper.setClicel(formatadoTelCel.getTexto());
        clienteper.setCliemail(acaTextfieldEmail.getText().toUpperCase());

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        formatter.format(new Date());
        clienteper.setClidata(formatter.format(new Date()));
        clienteper.setClicontato(acaTextfieldContado.getText().toUpperCase());
        if (acaComboTipo.getValor() == 1) {
            clienteper.setClicpf(formatadoCpfCnpjCli.getTexto());
            clienteper.setClirg(formatadoRgIe1.getTexto());
            clienteper.setClioe(acaTextfieldOe.getText().toUpperCase());
            clienteper.setClicnpj(" ");
            clienteper.setCliie(" ");
        }
        if (acaComboTipo.getValor() == 2) {
            clienteper.setClicnpj(formatadoCpfCnpjCli.getTexto());
            clienteper.setCliie(formatadoRgIe1.getTexto());
            clienteper.setClicpf(" ");
            clienteper.setClirg(" ");
            clienteper.setClioe(" ");
        }
        clienteper.setCidcod(acaComboCidade.getValor());
        clienteper.setClistatus(acaComboStatus.getValor());
        clienteper.setClifax(formatadoTelFax.getTexto());
        if (tipoOperacao == EXCLUINDO) {
            clienteper.setClicod(Integer.parseInt(acaTextfieldCodigo.getText()));
        }
    }

    public void preencherCampos() {
        setaLabel(clienteper.getClistatus());
        acaTextfieldCodigo.setText(String.valueOf(clienteper.getClicod()));
        acaTextfieldNome1.setText(clienteper.getClinome());
        acaTextfieldEnd.setText(clienteper.getCliendereco());
        acaTextfieldNum.setText(clienteper.getClinumero());
        acaTextfieldBairo.setText(clienteper.getClibairro());
        acaTextfieldCompl.setText(clienteper.getClicompl());
        acaTextfieldContado.setText(clienteper.getClicontato());
        acaTextfieldEmail.setText(clienteper.getCliemail());

        formatadoCepCli.setText(clienteper.getClicep());
        formatadoTel1.setText(clienteper.getClitel());
        formatadoTelCel.setText(clienteper.getClicel());
        formatadoTelFax.setText(clienteper.getClifax());
        acaComboTipo.setValor(clienteper.getClitipo());
        if (clienteper.getClitipo() == 1) {
            formatadoCpfCnpjCli.setValor(clienteper.getClicpf());
            formatadoRgIe1.setTexto(clienteper.getClirg());
            acaTextfieldOe.setText(clienteper.getClioe());
        } else {
            formatadoCpfCnpjCli.setValor(clienteper.getClicnpj());
            formatadoRgIe1.setTexto(clienteper.getCliie());
        }
        acaComboStatus.setValor(clienteper.getClistatus());
        try {
            ResultSet rs = Conexao.executaQuery("execute procedure comboestador(" + clienteper.getCidcod() + ")");
            rs.next();
            int codigoEstado = rs.getInt(1);
            acaComboEstado.buscaResult("select estcod,estnome from estado");
            acaComboEstado.setValor(codigoEstado);
        } catch (SQLException a) {
        }
        acaComboCidade.buscaResult("select cidcod,cidnome from cidade");
        acaComboCidade.setValor(clienteper.getCidcod());
    }

    @Override
    protected void incluir() {
        super.incluir();
        campos.removeElement(acaTextfieldCodigo);
        acaComboStatus.setValor(1);
    }

    @Override
    protected void cancelar() {
        campos.add(acaTextfieldCodigo);
        super.cancelar();
        acaComboTipo.setValor(1);
        acaComboCidade.removeAllItems();

        setaLabel(acaComboTipo.getValor());
    }

    @Override
    protected void consultar() {
        if (consulta == null) {
            consulta = new TelaConsulta("Consulta Clientes ", this, new int[]{1, 2, 4}, new String[]{"Codigo", "Nome", "CPF/CNPJ"}, "cliente", "clicod", "clinome", "clicpf", "clicnpj");
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
        obterCampos();
//        System.out.println(clienteper.clitipo);

        if (clienteper.verificaDuplicidade()) {
            if ((acaComboTipo.getValor() == 1) && (formatadoCpfCnpjCli.validaCPF() == true)) {
                Conexao.executaSql(clienteper.getInsereSql());
                return true;
            }
            if ((acaComboTipo.getValor() == 2) && (formatadoCpfCnpjCli.validaCNPJ() == true)) {
                Conexao.executaSql(clienteper.getInsereSql());
                return true;

            }

        }
        return false;
    }

    @Override
    protected boolean alterarBD() {
        clienteper.setClicod(Integer.parseInt(acaTextfieldCodigo.getText()));
        obterCampos();
        if (clienteper.verificaDuplicidadeAtualizar()) {
            Conexao.executaSql(clienteper.getAtualizaSql());
            return true;
        }
        return false;

    }

    @Override
    public boolean excluirBD() {
        obterCampos();
        if (Conexao.executaSqlExcluir(clienteper.getExcluiSql()) == Conexao.DEPENDENCIA) {
            AcaMen men = new AcaMen(this, Integer.parseInt(acaTextfieldCodigo.getText()), "cidade", "cidcod", "status");

            return false;
        } else if (Conexao.executaSqlExcluir(clienteper.getExcluiSql()) == Conexao.DEPENDENCIA) {
            limparCampos();
            habilitaCampos(false);
            habilitaBotoesConsulta(true);
            return false;
        }


        return true;


    }

    @Override
    public void consultarDados(int codigo) {

        try {
            clienteper.setClicod(codigo);
            ResultSet rs = Conexao.executaQuery(clienteper.getConsultaTodos());
            rs.next();
            clienteper.setClicod(rs.getInt(1));
            clienteper.setCidcod(rs.getInt(2));
            clienteper.setClinome(rs.getString(3));
            clienteper.setCliendereco(rs.getString(4));
            clienteper.setClinumero(rs.getString(5));
            clienteper.setClibairro(rs.getString(6));
            clienteper.setClicompl(rs.getString(7));
            clienteper.setClicep(rs.getString(8));
            clienteper.setClitel(rs.getString(9));
            clienteper.setClicel(rs.getString(10));
            clienteper.setClifax(rs.getString(11));
            clienteper.setCliemail(rs.getString(12));
            clienteper.setClidata(rs.getString(13));
            clienteper.setClicontato(rs.getString(14));
            clienteper.setClicpf(rs.getString(15));
            clienteper.setClirg(rs.getString(16));
            clienteper.setClicnpj(rs.getString(17));
            clienteper.setCliie(rs.getString(18));
            clienteper.setClioe(rs.getString(19));
            clienteper.setClistatus(rs.getInt(20));
            clienteper.setClitipo(rs.getInt(21));
            preencherCampos();
            habilitaCampos(false);
            habilitaBotoesConsulta(true);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void ajuda() {
        PlayMovie videos = new PlayMovie("clientes.wmv");


    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jPanelDados = new javax.swing.JPanel();
        acaTextfieldCodigo = new componentes.AcaTextfield();
        acaLabelTipo = new componentes.AcaLabel();
        acaComboTipo = new componentes.AcaCombo();
        acaComboTipo.comboFiltro(new int[]{1,2}, new String[]{"Fisica","Juridica"});
        acaLabelNome1 = new componentes.AcaLabel();
        acaTextfieldNome1 = new componentes.AcaTextfield(true, "Nome ou Razão", 1);
        acaTextfieldContado = new componentes.AcaTextfield(false,"contato", 0);
        acaLabelContato = new componentes.AcaLabel();
        if(acaComboTipo.getValor() == 1){
            acaLabelContato.setText("Contato");
        }else{
            acaLabelContato.setText("Nome de Fantasia");
        }
        acaLabelCel = new componentes.AcaLabel();
        acaLabelId2 = new componentes.AcaLabel();
        acaLabelId1 = new componentes.AcaLabel();
        acaLabelTel = new componentes.AcaLabel();
        acaTextfieldOe = new componentes.AcaTextfield(false, "Orgao Expedidor", 1);
        acaLabelOe = new componentes.AcaLabel();
        formatadoCpfCnpjCli = new componentes.FormatadoCpfCnpj("CPF", true, "CPF ou CNPJ", 1);
        formatadoTel1 = new componentes.FormatadoTel(false, "Telefone", 1);
        formatadoTelCel = new componentes.FormatadoTel(false, "Celular", 1);
        formatadoRgIe1 = new componentes.FormatadoRgIe("RG", true, "RG ou IE", 1);
        acaLabelFax = new componentes.AcaLabel();
        formatadoTelFax = new componentes.FormatadoTel(false, "fAX", 1);
        acaComboStatus = new componentes.AcaCombo();
        acaComboStatus.comboFiltro(new int[]{0,1,2},new String[]{"Inativo","Ativo","Inadimplente"});
        acaComboStatus.setSelectedIndex(-1);
        acaLabelStatus = new componentes.AcaLabel();
        acaLabelCodigo = new componentes.AcaLabel();
        jPanelDados1 = new javax.swing.JPanel();
        acaLabelEnd = new componentes.AcaLabel();
        acaTextfieldEnd = new componentes.AcaTextfield(true, "Endereço", 1);
        acaLabelEstado = new componentes.AcaLabel();
        acaComboEstado = new componentes.AcaCombo();
        acaComboEstado.buscaResult("select estcod,estnome from estado order by estnome asc");
        acaComboEstado.setDica("Estado");
        acaLabelCidade = new componentes.AcaLabel();
        acaComboCidade = new componentes.AcaCombo();
        acaLabelCep = new componentes.AcaLabel();
        acaLabelEmail = new componentes.AcaLabel();
        formatadoCepCli = new componentes.FormatadoCep(true, "Cep", 1);
        acaBotoesNovo = new componentes.AcaBotoes(false,"Novo","Cadastra nova Cidade");
        acaTextfieldEmail = new componentes.AcaTextfield(false, "Email", 1);
        acaLabelNum = new componentes.AcaLabel();
        acaTextfieldNum = new componentes.AcaTextfield(true, "Numero", 1);
        acaLabelBairro = new componentes.AcaLabel();
        acaTextfieldBairo = new componentes.AcaTextfield(true, "Bairro", 1);
        acaLabelCompl = new componentes.AcaLabel();
        acaTextfieldCompl = new componentes.AcaTextfield(true, "Complemento", 1);

        jButton2.setText("jButton2");

        setPreferredSize(new java.awt.Dimension(915, 600));
        setSize(new java.awt.Dimension(915, 600));
        setTitle("Cadastro de Clientes");

        jPanelDados.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        acaTextfieldCodigo.setEditable(false);

        acaLabelTipo.setText("Tipo");

        acaComboTipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                acaComboTipoItemStateChanged(evt);
            }
        });

        acaLabelNome1.setText("Nome");

        acaTextfieldNome1.setMinimumSize(new java.awt.Dimension(280, 21));

        acaLabelContato.setText("Contato");

        acaLabelCel.setText("Celular");

        acaLabelId2.setText("Rg");

        acaLabelId1.setText("Cpf");

        acaLabelTel.setText("Telefone");

        acaLabelOe.setText("O. E.");

        acaLabelFax.setText("Fax");

        acaComboStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaComboStatusActionPerformed(evt);
            }
        });

        acaLabelStatus.setText("Status");

        acaLabelCodigo.setText("Codigo");

        javax.swing.GroupLayout jPanelDadosLayout = new javax.swing.GroupLayout(jPanelDados);
        jPanelDados.setLayout(jPanelDadosLayout);
        jPanelDadosLayout.setHorizontalGroup(
            jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acaLabelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaLabelNome1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acaTextfieldNome1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelDadosLayout.createSequentialGroup()
                                .addComponent(acaTextfieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(acaLabelTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(acaComboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64)
                                .addComponent(acaLabelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(acaComboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(264, 264, 264))
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acaLabelContato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelDadosLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(acaLabelId2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(acaLabelId1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acaTextfieldContado, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDadosLayout.createSequentialGroup()
                                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                                        .addComponent(formatadoRgIe1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                                        .addComponent(acaLabelOe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(formatadoCpfCnpjCli, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(acaLabelTel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(acaTextfieldOe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(formatadoTel1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(acaLabelCel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(formatadoTelCel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addComponent(acaLabelFax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(formatadoTelFax, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap())))
        );
        jPanelDadosLayout.setVerticalGroup(
            jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosLayout.createSequentialGroup()
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(acaLabelTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaLabelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaLabelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaComboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaComboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaTextfieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(acaLabelNome1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaTextfieldNome1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acaLabelContato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaTextfieldContado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(formatadoCpfCnpjCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaLabelId1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaLabelTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(formatadoTel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acaLabelCel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(formatadoTelCel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(acaLabelId2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(formatadoRgIe1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(acaLabelOe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(acaTextfieldOe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(acaLabelFax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(formatadoTelFax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanelDados1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelDados1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        acaLabelEnd.setText("Endereço");
        jPanelDados1.add(acaLabelEnd, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 22, -1, -1));
        jPanelDados1.add(acaTextfieldEnd, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 17, 318, -1));

        acaLabelEstado.setText("Estado");
        jPanelDados1.add(acaLabelEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 83, -1, -1));

        acaComboEstado.setEnabled(false);
        acaComboEstado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                acaComboEstadoItemStateChanged(evt);
            }
        });
        jPanelDados1.add(acaComboEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 80, 130, -1));

        acaLabelCidade.setText("Cidade");
        jPanelDados1.add(acaLabelCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 83, -1, -1));

        acaComboCidade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                acaComboCidadeFocusGained(evt);
            }
        });
        jPanelDados1.add(acaComboCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(286, 80, 100, -1));

        acaLabelCep.setText("Cep.");
        jPanelDados1.add(acaLabelCep, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 53, -1, -1));

        acaLabelEmail.setText("Email");
        jPanelDados1.add(acaLabelEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 117, -1, -1));
        jPanelDados1.add(formatadoCepCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 48, 135, -1));

        acaBotoesNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaBotoesNovoActionPerformed(evt);
            }
        });
        jPanelDados1.add(acaBotoesNovo, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 80, -1, 22));
        jPanelDados1.add(acaTextfieldEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 107, 282, -1));

        acaLabelNum.setText("Numero");
        jPanelDados1.add(acaLabelNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(409, 22, -1, -1));
        jPanelDados1.add(acaTextfieldNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(458, 17, 105, -1));

        acaLabelBairro.setText("Bairro");
        jPanelDados1.add(acaLabelBairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(581, 22, -1, -1));
        jPanelDados1.add(acaTextfieldBairo, new org.netbeans.lib.awtextra.AbsoluteConstraints(618, 17, 109, -1));

        acaLabelCompl.setText("Complemento");
        jPanelDados1.add(acaLabelCompl, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 53, -1, -1));
        jPanelDados1.add(acaTextfieldCompl, new org.netbeans.lib.awtextra.AbsoluteConstraints(319, 48, 101, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getPainelCentral());
        getPainelCentral().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelDados1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelDados1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void acaComboStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acaComboStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_acaComboStatusActionPerformed

    private void acaComboEstadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_acaComboEstadoItemStateChanged
        if (acaComboEstado.getSelectedIndex() >= 0) {
            if (acaComboEstado.isPopupVisible()) {
                acaComboCidade.buscaResult("select cidcod, cidnome from cidade where estcod = " + acaComboEstado.getValor() + " order by cidnome asc");
            }
        }
    }//GEN-LAST:event_acaComboEstadoItemStateChanged

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
                    formatadoCpfCnpjCli.setFormatoCNPJ();
                    formatadoRgIe1.setFormatoIE();

                }
                if (acaComboTipo.getValor() == 1) {
                    acaLabelOe.setVisible(true);
                    acaTextfieldOe.setVisible(true);
                    acaLabelId2.setText("Rg");
                    acaLabelId1.setText("Cpf");
                    acaLabelNome1.setText("Nome");
                    formatadoCpfCnpjCli.setFormatoCPF();
                    formatadoRgIe1.setFormatoRG();
                }
            }

        }
    }//GEN-LAST:event_acaComboTipoItemStateChanged

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

    private void acaComboCidadeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_acaComboCidadeFocusGained
        if (tipoOperacao == NOVOITEM) {
            acaComboCidade.buscaResult("select cidcod,cidnome from cidade");
        }
    }//GEN-LAST:event_acaComboCidadeFocusGained
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private componentes.AcaBotoes acaBotoesNovo;
    private componentes.AcaCombo acaComboCidade;
    private componentes.AcaCombo acaComboEstado;
    private componentes.AcaCombo acaComboStatus;
    private componentes.AcaCombo acaComboTipo;
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
    private componentes.AcaLabel acaLabelStatus;
    private componentes.AcaLabel acaLabelTel;
    private componentes.AcaLabel acaLabelTipo;
    private componentes.AcaTextfield acaTextfieldBairo;
    private componentes.AcaTextfield acaTextfieldCodigo;
    private componentes.AcaTextfield acaTextfieldCompl;
    private componentes.AcaTextfield acaTextfieldContado;
    private componentes.AcaTextfield acaTextfieldEmail;
    private componentes.AcaTextfield acaTextfieldEnd;
    private componentes.AcaTextfield acaTextfieldNome1;
    private componentes.AcaTextfield acaTextfieldNum;
    private componentes.AcaTextfield acaTextfieldOe;
    private componentes.FormatadoCep formatadoCepCli;
    private componentes.FormatadoCpfCnpj formatadoCpfCnpjCli;
    private componentes.FormatadoRgIe formatadoRgIe1;
    private componentes.FormatadoTel formatadoTel1;
    private componentes.FormatadoTel formatadoTelCel;
    private componentes.FormatadoTel formatadoTelFax;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanelDados;
    private javax.swing.JPanel jPanelDados1;
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


        }
        if (tipo == 1) {
            acaLabelOe.setVisible(true);
            acaTextfieldOe.setVisible(true);
            acaLabelId2.setText("Rg");

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

package br.edu.ifsul.controle;


import br.edu.ifsul.dao.ClienteDAO;
import br.edu.ifsul.dao.FuncionarioDAO;
import br.edu.ifsul.modelo.Cliente;
import br.edu.ifsul.modelo.Funcionario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 */
@ManagedBean(name = "controleFuncionario")
@ViewScoped
public class ControleFuncionario implements Serializable {

    @EJB
    private FuncionarioDAO<Funcionario> dao;
    private Funcionario objeto;
    @EJB
    private ClienteDAO<Cliente> daoCliente;
    private Cliente cliente;
    private Boolean novoCliente;
    

    public ControleFuncionario() {

    }

    public String listar() {
        return "/privado/funcionario/listar?faces-redirect=true";
    }

//    public void imprimir(Integer id) {
//        try {
//            objeto = dao.getObjectById(id);
//            List<Funcionario> listaFuncionario = new ArrayList<>();
//            listaFuncionario.add(objeto);
//            HashMap parametros = new HashMap();
//            parametros.put("listaEnderecos",objeto.getEnderecos());
//            UtilRelatorios.imprimeRelatorio("relatorioFuncionario", parametros, listaFuncionario);
//        } catch (Exception e) {
//            UtilMensagens.mensagemErro("Erro: " + e.getMessage());
//        }
//        
//    }    

    public void novo() {
        setObjeto(new Funcionario());
    }

    public void salvar() {
        try {
            if (getObjeto().getId() == null) {
                getDao().persist(getObjeto());
            } else {
                getDao().merge(getObjeto());
            }
            UtilMensagens.mensagemInformacao("Objeto persistido com sucesso!");
        } catch (Exception e) {
            UtilMensagens.mensagemErro("Erro ao persistir objeto: " + e.getMessage());
        }
    }

    public void editar(Integer id) {
        try {
            setObjeto(getDao().getObjectById(id));
        } catch (Exception e) {
            UtilMensagens.mensagemErro("Erro ao recuperar objeto: " + e.getMessage());
        }
    }

    public void remover(Integer id) {
        try {
            setObjeto(getDao().getObjectById(id));
            getDao().remove(getObjeto());
            UtilMensagens.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            UtilMensagens.mensagemErro("Erro ao remover objeto: " + e.getMessage());
        }
    }
     
    public void novoCliente() {
        cliente = new Cliente();
        novoCliente = true;
    }

    public void alterarCliente(int index) {
        cliente = objeto.getAtende().get(index);
        novoCliente = false;
    }

    public void salvarCliente() {
        if (novoCliente) {
            objeto.adicionarCliente(cliente);
        }
        UtilMensagens.mensagemInformacao("Operação realizada com sucesso!");
    }

    public void removerCliente(int index) {
        objeto.removerCliente(index);
        UtilMensagens.mensagemInformacao("Cliente removido com sucesso!");
    }
    

    public FuncionarioDAO<Funcionario> getDao() {
        return dao;
    }

    public void setDao(FuncionarioDAO<Funcionario> dao) {
        this.dao = dao;
    }

    public Funcionario getObjeto() {
        return objeto;
    }

    public void setObjeto(Funcionario objeto) {
        this.objeto = objeto;
    }

    public ClienteDAO<Cliente> getDaoCliente() {
        return daoCliente;
    }

    public void setDaoCliente(ClienteDAO<Cliente> daoCliente) {
        this.daoCliente = daoCliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Boolean getNovoCliente() {
        return novoCliente;
    }

    public void setNovoCliente(Boolean novoCliente) {
        this.novoCliente = novoCliente;
    }

   
}

package br.edu.ifsul.controle;


import br.edu.ifsul.dao.ClienteDAO;
import br.edu.ifsul.dao.FilmeDAO;
import br.edu.ifsul.modelo.Cliente;
import br.edu.ifsul.modelo.Filme;
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
@ManagedBean(name = "controleCliente")
@ViewScoped
public class ControleCliente implements Serializable {

    @EJB
    private ClienteDAO<Cliente> dao;
    private Cliente objeto;
    @EJB
    private FilmeDAO<Filme> daoFilme;
    private Filme filme;
    private Boolean novoFilme;
    

    public ControleCliente() {

    }

    public String listar() {
        return "/privado/cliente/listar?faces-redirect=true";
    }

//    public void imprimir(Integer id) {
//        try {
//            objeto = dao.getObjectById(id);
//            List<Cliente> listaCliente = new ArrayList<>();
//            listaCliente.add(objeto);
//            HashMap parametros = new HashMap();
//            parametros.put("listaEnderecos",objeto.getEnderecos());
//            UtilRelatorios.imprimeRelatorio("relatorioCliente", parametros, listaCliente);
//        } catch (Exception e) {
//            UtilMensagens.mensagemErro("Erro: " + e.getMessage());
//        }
//        
//    }    

    public void novo() {
        setObjeto(new Cliente());
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
    
    

    public ClienteDAO<Cliente> getDao() {
        return dao;
    }

    public void setDao(ClienteDAO<Cliente> dao) {
        this.dao = dao;
    }

    public Cliente getObjeto() {
        return objeto;
    }

    public void setObjeto(Cliente objeto) {
        this.objeto = objeto;
    }

    public FilmeDAO<Filme> getDaoFilme() {
        return daoFilme;
    }

    public void setDaoFilme(FilmeDAO<Filme> daoFilme) {
        this.daoFilme = daoFilme;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Boolean getNovoFilme() {
        return novoFilme;
    }

    public void setNovoFilme(Boolean novoFilme) {
        this.novoFilme = novoFilme;
    }

   
}

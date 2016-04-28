package br.edu.ifsul.controle;


import br.edu.ifsul.dao.FilmeDAO;
import br.edu.ifsul.dao.FornecedorDAO;
import br.edu.ifsul.modelo.Filme;
import br.edu.ifsul.modelo.Fornecedor;
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
@ManagedBean(name = "controleFornecedor")
@ViewScoped
public class ControleFornecedor implements Serializable {

    @EJB
    private FornecedorDAO<Fornecedor> dao;
    private Fornecedor objeto;
    @EJB
    private FilmeDAO<Filme> daoFilme;
    private Filme filme;
    

    public ControleFornecedor() {
        

    }

    public String listar() {
        return "/privado/fornecedor/listar?faces-redirect=true";
    }

//    public void imprimir(Integer id) {
//        try {
//            objeto = dao.getObjectById(id);
//            List<Fornecedor> listaFornecedor = new ArrayList<>();
//            listaFornecedor.add(objeto);
//            HashMap parametros = new HashMap();
//            parametros.put("listaEnderecos",objeto.getEnderecos());
//            UtilRelatorios.imprimeRelatorio("relatorioFornecedor", parametros, listaFornecedor);
//        } catch (Exception e) {
//            UtilMensagens.mensagemErro("Erro: " + e.getMessage());
//        }
//        
//    }    

    public void novo() {
        setObjeto(new Fornecedor());
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

    public void adicionarFornecimento(){
        if (filme != null){
            if(!objeto.getFornece().contains(filme)){
                objeto.getFornece().add(filme);
                UtilMensagens.mensagemInformacao("Fornecimento adicionado com sucesso!");
            } else {
                UtilMensagens.mensagemErro("Produto já existe na lista!");
            } 
        }
    }
    
    public void removerFornecimento(int index){
        filme = objeto.getFornece().get(index);
        objeto.getFornece().remove(filme);
        UtilMensagens.mensagemInformacao("Fornecimento removido com sucesso!");
    }
    

    public FornecedorDAO<Fornecedor> getDao() {
        return dao;
    }

    public void setDao(FornecedorDAO<Fornecedor> dao) {
        this.dao = dao;
    }

    public Fornecedor getObjeto() {
        return objeto;
    }

    public void setObjeto(Fornecedor objeto) {
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

  
   
}

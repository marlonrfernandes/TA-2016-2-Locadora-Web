package br.edu.ifsul.controle;


import br.edu.ifsul.dao.AtorDAO;
import br.edu.ifsul.dao.FilmeDAO;
import br.edu.ifsul.dao.GeneroDAO;
import br.edu.ifsul.modelo.Ator;
import br.edu.ifsul.modelo.Filme;
import br.edu.ifsul.modelo.Genero;
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
@ManagedBean(name = "controleFilme")
@ViewScoped
public class ControleFilme implements Serializable {

    @EJB
    private FilmeDAO<Filme> dao;
    private Filme objeto;
    @EJB
    private GeneroDAO<Genero> daoGenero;
    @EJB
    private AtorDAO<Ator> daoAtor;
    private Ator ator;
    

    public ControleFilme() {

    }

    public String listar() {
        return "/privado/filme/listar?faces-redirect=true";
    }

//    public void imprimir(Integer id) {
//        try {
//            objeto = dao.getObjectById(id);
//            List<Filme> listaFilme = new ArrayList<>();
//            listaFilme.add(objeto);
//            HashMap parametros = new HashMap();
//            parametros.put("listaEnderecos",objeto.getEnderecos());
//            UtilRelatorios.imprimeRelatorio("relatorioFilme", parametros, listaFilme);
//        } catch (Exception e) {
//            UtilMensagens.mensagemErro("Erro: " + e.getMessage());
//        }
//        
//    }    

    public void novo() {
        setObjeto(new Filme());
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

    public void adicionarElenco(){
        if (ator != null){
            if(!objeto.getAtuacao().contains(ator)){
                objeto.getAtuacao().add(ator);
                UtilMensagens.mensagemInformacao("Ator adicionado com sucesso!");
            } else {
                UtilMensagens.mensagemErro("Ator já existe na lista!");
            } 
        }
    }
    
    public void removerElenco(int index){
        ator = objeto.getAtuacao().get(index);
        objeto.getAtuacao().remove(ator);
        UtilMensagens.mensagemInformacao("Ator removido com sucesso!");
    }

    public FilmeDAO<Filme> getDao() {
        return dao;
    }

    public void setDao(FilmeDAO<Filme> dao) {
        this.dao = dao;
    }

    public Filme getObjeto() {
        return objeto;
    }

    public void setObjeto(Filme objeto) {
        this.objeto = objeto;
    }

    public GeneroDAO<Genero> getDaoGenero() {
        return daoGenero;
    }

    public void setDaoGenero(GeneroDAO<Genero> daoGenero) {
        this.daoGenero = daoGenero;
    }

    public AtorDAO<Ator> getDaoAtor() {
        return daoAtor;
    }

    public void setDaoAtor(AtorDAO<Ator> daoAtor) {
        this.daoAtor = daoAtor;
    }

    public Ator getAtor() {
        return ator;
    }

    public void setAtor(Ator ator) {
        this.ator = ator;
    }

   
}

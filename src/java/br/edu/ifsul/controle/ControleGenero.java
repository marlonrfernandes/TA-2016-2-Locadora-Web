package br.edu.ifsul.controle;


import br.edu.ifsul.dao.DAOGenerico;
import br.edu.ifsul.dao.GeneroDAO;
import br.edu.ifsul.modelo.Genero;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 */
@ManagedBean(name = "controleGenero")
@ViewScoped
public class ControleGenero implements Serializable {

    @EJB
    private GeneroDAO<Genero> dao;
    private Genero objeto;
    

    public ControleGenero() {

    }
    
    public String listar(){
        return "/privado/genero/listar?faces-redirect=true";
    }
    
    public void novo(){
        setObjeto(new Genero());
    }
    
    public void salvar(){
        try {
            if (getObjeto().getId() == null){
                getDao().persist(getObjeto());
            } else {
                getDao().merge(getObjeto());
            }
            UtilMensagens.mensagemInformacao("Objeto persistido com sucesso!");
        } catch (Exception e){
            UtilMensagens.mensagemErro("Erro ao persistir objeto: "+e.getMessage());
        }
    }
    
    public void editar(Integer id){
        try {
            setObjeto(getDao().getObjectById(id));            
        } catch (Exception e){
            UtilMensagens.mensagemErro("Erro ao recuperar objeto: "+e.getMessage());
        }
    }
    
    public void remover(Integer id){
        try {
            setObjeto(getDao().getObjectById(id));
            getDao().remove(getObjeto());
            UtilMensagens.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e){
            UtilMensagens.mensagemErro("Erro ao remover objeto: "+e.getMessage());
        }
    }

    public GeneroDAO<Genero> getDao() {
        return dao;
    }

    public void setDao(GeneroDAO<Genero> dao) {
        this.dao = dao;
    }

    public Genero getObjeto() {
        return objeto;
    }

    public void setObjeto(Genero objeto) {
        this.objeto = objeto;
    }

    
        
}

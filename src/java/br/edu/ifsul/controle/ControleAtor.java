package br.edu.ifsul.controle;


import br.edu.ifsul.dao.AtorDAO;
import br.edu.ifsul.modelo.Ator;
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
@ManagedBean(name = "controleAtor")
@ViewScoped
public class ControleAtor implements Serializable {

    @EJB
    private AtorDAO<Ator> dao;
    private Ator objeto;
    

    public ControleAtor() {

    }

    public String listar() {
        return "/privado/ator/listar?faces-redirect=true";
    }

//    public void imprimir(Integer id) {
//        try {
//            objeto = dao.getObjectById(id);
//            List<Ator> listaAtor = new ArrayList<>();
//            listaAtor.add(objeto);
//            HashMap parametros = new HashMap();
//            parametros.put("listaEnderecos",objeto.getEnderecos());
//            UtilRelatorios.imprimeRelatorio("relatorioAtor", parametros, listaAtor);
//        } catch (Exception e) {
//            UtilMensagens.mensagemErro("Erro: " + e.getMessage());
//        }
//        
//    }    

    public void novo() {
        setObjeto(new Ator());
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

    

    public AtorDAO<Ator> getDao() {
        return dao;
    }

    public void setDao(AtorDAO<Ator> dao) {
        this.dao = dao;
    }

    public Ator getObjeto() {
        return objeto;
    }

    public void setObjeto(Ator objeto) {
        this.objeto = objeto;
    }

   
}

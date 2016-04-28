package br.edu.ifsul.controle;


import br.edu.ifsul.dao.AluguelDAO;
import br.edu.ifsul.dao.AluguelIdDAO;
import br.edu.ifsul.dao.ClienteDAO;
import br.edu.ifsul.dao.FilmeDAO;
import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.modelo.Aluguel;
import br.edu.ifsul.modelo.AluguelID;
import br.edu.ifsul.modelo.AluguelItem;
import br.edu.ifsul.modelo.Cliente;
import br.edu.ifsul.modelo.Filme;
import br.edu.ifsul.modelo.Pessoa;
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
@ManagedBean(name = "controleAluguel")
@ViewScoped
public class ControleAluguel implements Serializable {

    @EJB
    private AluguelDAO<Aluguel> dao;
    private Aluguel objeto;
    @EJB
    private AluguelIdDAO<AluguelID> daoAluguelID;
    @EJB
    private ClienteDAO<Cliente> daoCliente;
    @EJB
    private FilmeDAO<Filme> daoFilme;
    private AluguelItem item;
    private Boolean novoItem;
    

    public ControleAluguel() {

    }

    public String listar() {
        return "/privado/aluguel/listar?faces-redirect=true";
    }

//    public void imprimir(Integer id) {
//        try {
//            objeto = dao.getObjectById(id);
//            List<Aluguel> listaAluguel = new ArrayList<>();
//            listaAluguel.add(objeto);
//            HashMap parametros = new HashMap();
//            parametros.put("listaEnderecos",objeto.getEnderecos());
//            UtilRelatorios.imprimeRelatorio("relatorioAluguel", parametros, listaAluguel);
//        } catch (Exception e) {
//            UtilMensagens.mensagemErro("Erro: " + e.getMessage());
//        }
//        
//    }    

    public void novo() {
        setObjeto(new Aluguel());
        objeto.setId(new AluguelID());
    }
    
    

    public void salvar() {
        try {
            if (getObjeto().getId().getNumeroNota() == null) {
                getDao().persist(getObjeto());
            } else {
                getDao().merge(getObjeto());
            }
            UtilMensagens.mensagemInformacao("Objeto persistido com sucesso!");
        } catch (Exception e) {
            UtilMensagens.mensagemErro("Erro ao persistir objeto: " + e.getMessage());
        }
    }

    public void editar(AluguelID id) {
        try {
            setObjeto(getDao().getObjectById(id));
        } catch (Exception e) {
            UtilMensagens.mensagemErro("Erro ao recuperar objeto: " + e.getMessage());
        }
    }

    public void remover(AluguelID id) {
        try {
            setObjeto(getDao().getObjectById(id));
            getDao().remove(getObjeto());
            UtilMensagens.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            UtilMensagens.mensagemErro("Erro ao remover objeto: " + e.getMessage());
        }
    }
    
    public void novoItem(){
        item = new AluguelItem();
        novoItem = true;
    }
    
    public void alterarItem(int index){
        item = objeto.getItens().get(index);
        novoItem = false;
    }
    
    public void salvarItem(){
        if(novoItem){
            objeto.adicionarItem(item);
        }else{
            atualizarValorTotalAluguel();
        }
        UtilMensagens.mensagemInformacao("Operação realizada com sucesso!");
    }
    
    public void removerItem(int index){
        objeto.removerItem(index);
        UtilMensagens.mensagemInformacao("Item removido com sucesso!");
    }
    
    public void atualizaValorUnitario(){
        if(item != null){
            if(item.getFilme() != null){
                item.setValorUnitario(item.getFilme().getPreco());
            }
        }
    }
    
    public void calculaValorTotalItem(){
        if(item != null){
            if(item.getValorUnitario()!= null && item.getQuantidade() != null){
                item.setValorTotal(item.getValorUnitario() * item.getQuantidade());
            }
        }
    }
    
    public void atualizarValorTotalAluguel(){
        objeto.setValorTotal(0.0);
        Double total = 0.0;
        for(AluguelItem ai : objeto.getItens()){
            total += ai.getValorTotal();
        }
        objeto.setValorTotal(total);
    }

    

    public AluguelDAO<Aluguel> getDao() {
        return dao;
    }

    public void setDao(AluguelDAO<Aluguel> dao) {
        this.dao = dao;
    }

    public Aluguel getObjeto() {
        return objeto;
    }

    public void setObjeto(Aluguel objeto) {
        this.objeto = objeto;
    }

    public AluguelIdDAO<AluguelID> getDaoAluguelID() {
        return daoAluguelID;
    }

    public void setDaoAluguelID(AluguelIdDAO<AluguelID> daoAluguelID) {
        this.daoAluguelID = daoAluguelID;
    }

    

    public AluguelItem getItem() {
        return item;
    }

    public void setItem(AluguelItem item) {
        this.item = item;
    }

    public Boolean getNovoItem() {
        return novoItem;
    }

    public void setNovoItem(Boolean novoItem) {
        this.novoItem = novoItem;
    }

    public FilmeDAO<Filme> getDaoFilme() {
        return daoFilme;
    }

    public void setDaoFilme(FilmeDAO<Filme> daoFilme) {
        this.daoFilme = daoFilme;
    }

    public ClienteDAO<Cliente> getDaoCliente() {
        return daoCliente;
    }

    public void setDaoCliente(ClienteDAO<Cliente> daoCliente) {
        this.daoCliente = daoCliente;
    }

   
}

package br.edu.ifsul.dao;



import br.edu.ifsul.modelo.Filme;
import br.edu.ifsul.modelo.Fornecedor;
import java.io.Serializable;
import javax.ejb.Stateful;



/**
 *
 * @author Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 */
@Stateful
public class FornecedorDAO<T> extends DAOGenerico<Fornecedor> implements Serializable {
    public FornecedorDAO(){
        super();
        super.setClassePersistente(Fornecedor.class);
    }
    
    @Override
    public Fornecedor getObjectById(Integer id) throws Exception {
        Fornecedor obj = (Fornecedor) super.getEm().find(super.getClassePersistente(), id);
        obj.getFornece().size();
        return obj;
    }
    
}

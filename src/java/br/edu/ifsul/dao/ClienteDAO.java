package br.edu.ifsul.dao;



import br.edu.ifsul.modelo.Cliente;
import java.io.Serializable;
import javax.ejb.Stateful;



/**
 *
 * @author Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 */
@Stateful
public class ClienteDAO<T> extends DAOGenerico<Cliente> implements Serializable {
    public ClienteDAO(){
        super();
        super.setClassePersistente(Cliente.class);
    }
    
    @Override
    public Cliente getObjectById(Integer id) throws Exception {
        Cliente obj = (Cliente) super.getEm().find(super.getClassePersistente(), id);
        obj.getInteresses().size();
        return obj;
    }
    
}

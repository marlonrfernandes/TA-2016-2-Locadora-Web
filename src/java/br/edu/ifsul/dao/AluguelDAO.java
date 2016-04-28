package br.edu.ifsul.dao;


import br.edu.ifsul.modelo.Aluguel;
import br.edu.ifsul.modelo.AluguelID;
import java.io.Serializable;
import javax.ejb.Stateful;



/**
 *
 * @author Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 */
@Stateful
public class AluguelDAO<T> extends DAOGenerico<Aluguel> implements Serializable {
    public AluguelDAO(){
        super();
        super.setClassePersistente(Aluguel.class);
    }
    
    public Aluguel getObjectById(AluguelID id) throws Exception {
        Aluguel obj = (Aluguel) super.getEm().find(super.getClassePersistente(), id);
        obj.getItens().size();
        return obj;
    }
    
}

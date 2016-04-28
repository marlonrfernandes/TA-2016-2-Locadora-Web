package br.edu.ifsul.dao;


import br.edu.ifsul.modelo.AluguelID;
import java.io.Serializable;
import javax.ejb.Stateful;



/**
 *
 * @author Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 */
@Stateful
public class AluguelIdDAO<T> extends DAOGenerico<AluguelID> implements Serializable {
    public AluguelIdDAO(){
        super();
        super.setClassePersistente(AluguelID.class);
    }
    
}

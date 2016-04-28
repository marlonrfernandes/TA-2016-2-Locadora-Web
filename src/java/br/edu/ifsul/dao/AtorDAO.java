package br.edu.ifsul.dao;


import br.edu.ifsul.modelo.Ator;
import java.io.Serializable;
import javax.ejb.Stateful;



/**
 *
 * @author Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 */
@Stateful
public class AtorDAO<T> extends DAOGenerico<Ator> implements Serializable {
    public AtorDAO(){
        super();
        super.setClassePersistente(Ator.class);
    }
    
}

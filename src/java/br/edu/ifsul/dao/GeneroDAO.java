package br.edu.ifsul.dao;


import br.edu.ifsul.modelo.Genero;
import java.io.Serializable;
import javax.ejb.Stateful;



/**
 *
 * @author Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 */
@Stateful
public class GeneroDAO<T> extends DAOGenerico<Genero> implements Serializable {
    public GeneroDAO(){
        super();
        super.setClassePersistente(Genero.class);
    }
    
}

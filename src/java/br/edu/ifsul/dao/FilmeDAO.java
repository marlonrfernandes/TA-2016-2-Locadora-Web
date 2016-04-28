package br.edu.ifsul.dao;


import br.edu.ifsul.modelo.Filme;
import java.io.Serializable;
import javax.ejb.Stateful;



/**
 *
 * @author Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 */
@Stateful
public class FilmeDAO<T> extends DAOGenerico<Filme> implements Serializable {
    public FilmeDAO(){
        super();
        super.setClassePersistente(Filme.class);
    }
    @Override
    public Filme getObjectById(Integer id) throws Exception {
        Filme obj = (Filme) super.getEm().find(super.getClassePersistente(), id);
        obj.getAtuacao().size();
        return obj;
    }    
}

package br.edu.ifsul.dao;



import br.edu.ifsul.modelo.Pessoa;
import java.io.Serializable;
import javax.ejb.Stateful;



/**
 *
 * @author Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 */
@Stateful
public class PessoaDAO<T> extends DAOGenerico<Pessoa> implements Serializable {
    public PessoaDAO(){
        super();
        super.setClassePersistente(Pessoa.class);
    }
    
    
}

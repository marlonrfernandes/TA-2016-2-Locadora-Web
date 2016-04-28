package br.edu.ifsul.dao;



import br.edu.ifsul.modelo.Funcionario;
import java.io.Serializable;
import javax.ejb.Stateful;



/**
 *
 * @author Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 */
@Stateful
public class FuncionarioDAO<T> extends DAOGenerico<Funcionario> implements Serializable {
    public FuncionarioDAO(){
        super();
        super.setClassePersistente(Funcionario.class);
    }
    
    @Override
    public Funcionario getObjectById(Integer id) throws Exception {
        Funcionario obj = (Funcionario) super.getEm().find(super.getClassePersistente(), id);
        obj.getAtende().size();
        return obj;
    }
    
}

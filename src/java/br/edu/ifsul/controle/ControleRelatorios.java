/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;


import br.edu.ifsul.dao.GeneroDAO;
import br.edu.ifsul.modelo.Genero;
import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Marlon
 */
@ManagedBean(name = "controleRelatorios")
@SessionScoped
public class ControleRelatorios implements Serializable{
    @EJB
    private GeneroDAO<Genero> daoGenero;

    public ControleRelatorios() {
    }
    
    public void imprimeRelatorioGeneros(){
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioGenero2", parametros, getDaoGenero().getListaTodos());
    }

    public GeneroDAO<Genero> getDaoGenero() {
        return daoGenero;
    }

    public void setDaoGenero(GeneroDAO<Genero> daoGenero) {
        this.daoGenero = daoGenero;
    }

    
    
    
}

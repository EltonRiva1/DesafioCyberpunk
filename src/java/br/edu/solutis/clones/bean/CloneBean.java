/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.solutis.clones.bean;

import br.edu.solutis.clones.dao.CloneDAO;
import br.edu.solutis.clones.entidade.Clone;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author notle
 */
@ManagedBean
@SessionScoped
public class CloneBean extends CrudBean<Clone, CloneDAO> {

    private CloneDAO cloneDAO;

    @Override
    public CloneDAO getDao() {
        if (cloneDAO == null) {
            cloneDAO = new CloneDAO();
        }
        return cloneDAO;
    }

    @Override
    public Clone criarNovaEntidade() {
        return new Clone();
    }

}

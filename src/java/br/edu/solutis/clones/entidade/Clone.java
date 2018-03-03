/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.solutis.clones.entidade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author notle
 */
public class Clone {

    private Integer id, idade;
    private String nome;
    private Date dataDeCriacao = new Date();
    private List<String> adicionais;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the idade
     */
    public Integer getIdade() {
        return idade;
    }

    /**
     * @param idade the idade to set
     */
    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the dataDeCriacao
     */
    public Date getDataDeCriacao() {
        return dataDeCriacao;
    }

    /**
     * @param dataDeCriacao the dataDeCriacao to set
     */
    public void setDataDeCriacao(Date dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }

    /**
     * @return the adicionais
     */
    public List<String> getAdicionais() {
        return adicionais;
    }

    public String getAdicionaisString() {
        String adic = "";
        for (String adicional : adicionais) {
            adic += adicional + ",";
        }
        adic = adic.substring(0, adic.length() - 1);
        return adic;
    }

    public void setAdicionaisString(String adicional) {
        adicionais = Arrays.asList(adicional.split(","));
    }

    /**
     * @param adicionais the adicionais to set
     */
    public void setAdicionais(List<String> adicionais) {
        this.adicionais = adicionais;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Clone other = (Clone) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}

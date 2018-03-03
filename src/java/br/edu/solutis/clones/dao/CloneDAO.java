/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.solutis.clones.dao;

import br.edu.solutis.clones.entidade.Clone;
import br.edu.solutis.clones.util.FabricaConexao;
import br.edu.solutis.clones.util.exception.ErroSistema;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author notle
 */
public class CloneDAO implements CrudDAO<Clone> {

    @Override
    public void salvar(Clone entidade) throws ErroSistema {
        try {
            if (validarCloneExistente(entidade.getNome(), entidade.getId())) {
                throw new ErroSistema("Clone já existente.");
            }
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
            if (entidade.getId() == null) {
                ps = conexao.prepareStatement("INSERT INTO clone (nome,idade,dataDeCriacao,adicionais) VALUES (?,?,?,?)");
            } else {
                ps = conexao.prepareStatement("update clone set nome=?, idade=?, dataDeCriacao=?, adicionais=?  where id=?");
                ps.setInt(5, entidade.getId());
            }
            ps.setString(1, entidade.getNome());
            ps.setInt(2, entidade.getIdade());
            ps.setDate(3, new Date(entidade.getDataDeCriacao().getTime()));
            ps.setString(4, entidade.getAdicionaisString());
            ps.execute();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao tentar salvar!", ex);
        }
    }

    @Override
    public void deletar(Clone entidade) throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("delete from clone where id = ?");
            ps.setInt(1, entidade.getId());
            ps.execute();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao deletar o clone!", ex);
        }
    }

    public boolean validarCloneExistente(String nome, Integer id) throws ErroSistema {
        try {
            boolean jaExiste = false;
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
            if (id == null) {
                ps = conexao.prepareStatement("select * from clone where nome = ?");
            } else {
                ps = conexao.prepareStatement("select * from clone where nome = ? and id <> ?");
                ps.setInt(2, id);
            }
            ps.setString(1, nome);
            ResultSet resultSet = ps.executeQuery();
            jaExiste = resultSet.next();
            FabricaConexao.fecharConexao();
            return jaExiste;
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao buscar os clones!", ex);
        }
    }

    @Override
    public List<Clone> buscar() throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("select * from clone");
            ResultSet resultSet = ps.executeQuery();
            List<Clone> clones = new ArrayList<>();
            while (resultSet.next()) {
                Clone clone = new Clone();
                clone.setId(resultSet.getInt("id"));
                clone.setNome(resultSet.getString("nome"));
                clone.setIdade(resultSet.getInt("idade"));
                clone.setDataDeCriacao(resultSet.getDate("dataDeCriacao"));
                clone.setAdicionaisString(resultSet.getString("adicionais"));
                clones.add(clone);
            }
            FabricaConexao.fecharConexao();
            return clones;
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao buscar os clones!", ex);
        }
    }
}

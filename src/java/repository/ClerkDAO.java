/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import viewmodel.ClerkModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.FabricaConexao;

/**
 *
 * @author Otavio
 */
public class ClerkDAO {
    
    Connection conexao;
    PreparedStatement ps;

    public void salvar(ClerkModel atendente) throws Exception {
        try {
            conexao = FabricaConexao.getConnection(); // obtem a conexão com o BD
            if (atendente.getAtendenteid() == null) { // se é uma inserção
                ps = conexao.prepareStatement("INSERT INTO atendente(cpf,nome,matricula,cargo) values(?,?,?,?)");
            } else {
                ps = conexao.prepareStatement("UPDATE atendente set cpf=?,nome=?,matricula=?,cargo=? where id=?");
                ps.setString(5, atendente.getAtendenteid().toString());
            }
            ps.setString(1, atendente.getCpfatendete());
            ps.setString(2, atendente.getNomeAtendente());
            ps.setString(3, atendente.getMatriculaAtendente());
            ps.setString(4, atendente.getCargoAtendente());
            

        } catch (SQLException e) {
            throw new Exception("Ops! problema ao conectar com banco de dados", e);
        }
        try {
            ps.execute();
        } catch (SQLException e) {
            throw new Exception("Ops!Houve um erro ao atribuir os dados", e);
        }
    }
    
      public List<ClerkModel> buscar() throws Exception {
        try {
            conexao = FabricaConexao.getConnection(); // obtem a conexão com o BD
            PreparedStatement ps = conexao.prepareStatement("select * from atendente");
            ResultSet resultSet = ps.executeQuery();
            List<ClerkModel> atendentes = new ArrayList<ClerkModel>();
           
                 while (resultSet.next()) { // percorre todos os registros da tabela
                ClerkModel atendente = new ClerkModel();
                atendente.setAtendenteid(resultSet.getInt("id"));
                atendente.setCpfatendete(resultSet.getString("cpf"));
                atendente.setNomeAtendente(resultSet.getString("nome"));
                atendente.setMatriculaAtendente(resultSet.getString("matricula"));
                atendente.setCargoAtendente(resultSet.getString("cargo"));
                atendentes.add(atendente);
            }
            FabricaConexao.closeConnection();
            return atendentes;

        } catch (SQLException e) {
            throw new Exception("Ops! problema ao conectar com banco de dados", e);
        }
    }
      
        public void deletar(ClerkModel atendente) throws Exception {
        conexao = FabricaConexao.getConnection(); // obtem a conexão com o BD
        try {
            ps = conexao.prepareStatement("delete from atendente where id=?");
            ps.setInt(1, atendente.getAtendenteid());
            ps.execute();
        } catch (SQLException e) {
            throw new Exception("Ops! Tivemos um erro ao deletar os dados do cliente,tente novamente!", e);
        }
        FabricaConexao.closeConnection();
    }
    
}

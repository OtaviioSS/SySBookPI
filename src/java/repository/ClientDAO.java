/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import viewmodel.ClientModel;
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
public class ClientDAO {
    
      Connection conexao;
    PreparedStatement ps; // armazena a declaração SQL

    public void salvar(ClientModel cliente) throws Exception {
        try {
            conexao = FabricaConexao.getConnection(); // obtem a conexão com o BD
            if(cliente.getId() == null){ // se é uma inserção
                ps = conexao.prepareStatement("INSERT INTO cliente(cpf,nome,endereco,email,telefone) values(?,?,?,?,?)");
            } else { // se é uma update
                ps = conexao.prepareStatement("UPDATE cliente set cpf=?,nome=?,endereco=?, email=?,telefone=? where id=?");
                ps.setString(6, cliente.getId().toString());
            }
            ps.setString(1, cliente.getCpf());
            ps.setString(2, cliente.getNome());
            ps.setString(3, cliente.getEndereco());
            ps.setString(4, cliente.getEmail());
            ps.setString(5, cliente.getTelefone());
            
        } catch (SQLException e) {
            throw new Exception("Ops! problema ao conectar com banco de dados", e);
        }
        try {
            ps.execute();
        } catch (SQLException e) {
            throw new Exception("Ops!Houve um erro ao atribuir os dados", e);
        }
    }

    public List<ClientModel> buscar() throws Exception {
        try {
            conexao = FabricaConexao.getConnection(); // obtem a conexão com o BD
            PreparedStatement ps = conexao.prepareStatement("select * from cliente");
            ResultSet resultSet = ps.executeQuery();
            List<ClientModel> clientes = new ArrayList<ClientModel>();

            while (resultSet.next()) { // percorre todos os registros da tabela
                ClientModel cliente = new ClientModel();
                cliente.setId(resultSet.getInt("id"));
                cliente.setCpf(resultSet.getString("cpf"));
                cliente.setNome(resultSet.getString("nome"));
                cliente.setEndereco(resultSet.getString("endereco"));
                cliente.setEmail(resultSet.getString("email"));
                cliente.setTelefone(resultSet.getString("telefone"));
                clientes.add(cliente);
            }

            FabricaConexao.closeConnection();
            return clientes;

        } catch (SQLException e) {
            throw new Exception("Ops! problema ao conectar com banco de dados - ", e);
        }
    }

    public void deletar(ClientModel cliente) throws Exception {
        conexao = FabricaConexao.getConnection(); // obtem a conexão com o BD
        try {
            ps = conexao.prepareStatement("delete from cliente where id=?");
            ps.setInt(1, cliente.getId());
            ps.execute();
        } catch (SQLException e) {
            throw new Exception("Ops! Tivemos um erro ao deletar os dados do cliente", e);
        }
        FabricaConexao.closeConnection();
    }
    
}

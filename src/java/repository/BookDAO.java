/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import viewmodel.BookModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.FabricaConexao;

/**
 * CRUD da tabela 'acao'
 */
public class BookDAO {

    Connection conexao;
    PreparedStatement ps; // armazena a declaração SQL

    public void salvar(BookModel livro) throws Exception {
        try {
            conexao = FabricaConexao.getConnection(); // obtem a conexão com o BD
            if (livro.getId() == null) { // se é uma inserção
                ps = conexao.prepareStatement("INSERT INTO livro(name,autor,code) values(?,?,?)");
            } else { // se é uma update
                ps = conexao.prepareStatement("UPDATE livro set name=?,autor=?, code=? where id=?");
                ps.setString(4, livro.getId().toString());
            }
            ps.setString(1, livro.getName());
            ps.setString(2, livro.getAutor());
            ps.setString(3, livro.getCode());
        } catch (SQLException e) {
            throw new Exception("Ops! problema ao conectar com banco de dados", e);
        }
        try {
            ps.execute();
        } catch (SQLException e) {
            throw new Exception("Ops!Houve um erro ao atribuir os dados", e);
        }
    }

    public List<BookModel> buscar() throws Exception {
        try {
            conexao = FabricaConexao.getConnection(); // obtem a conexão com o BD
            PreparedStatement ps = conexao.prepareStatement("select * from livro");
            ResultSet resultSet = ps.executeQuery();
            List<BookModel> livros = new ArrayList<BookModel>();

            while (resultSet.next()) { // percorre todos os registros da tabela
                BookModel livro = new BookModel();
                livro.setId(resultSet.getInt("id"));
                livro.setName(resultSet.getString("name"));
                livro.setCode(resultSet.getString("code"));
                livro.setAutor(resultSet.getString("autor"));
                livros.add(livro);
            }

            FabricaConexao.closeConnection();
            return livros;

        } catch (SQLException e) {
            throw new Exception("Ops! problema ao conectar com banco de dados", e);
        }
    }

    public void deletar(BookModel livro) throws Exception {
        conexao = FabricaConexao.getConnection(); // obtem a conexão com o BD
        try {
            ps = conexao.prepareStatement("delete from livro where id=?");
            ps.setInt(1, livro.getId());
            ps.execute();
        } catch (SQLException e) {
            throw new Exception("Ops! Tivemos um erro ao deletar o livro, tente novamente", e);
        }
        FabricaConexao.closeConnection();
    }

}

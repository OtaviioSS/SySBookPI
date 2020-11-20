package repository;

import viewmodel.UserModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.FabricaConexao;

public class UserDAO {
   
  /* Métodos */
    public boolean login(UserModel usuario) throws Exception {
        Connection conexao = FabricaConexao.getConnection();
        PreparedStatement ps;
        String senhaDigitada = usuario.getPassword();
        try {
            ps = conexao.prepareStatement("SELECT * FROM acoes.usuario where login=?;"); // obtem apena uma única informação
            ps.setString(1, usuario.getLogin());
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next() == false) // verifica se o usuário realmente está no banco de dados
            {
                throw new Exception("Login incorreto! Não existe no banco de dados!");
            }

            usuario.setId(resultSet.getInt("id"));
            usuario.setName(resultSet.getString("name"));
            usuario.setPassword(resultSet.getString("password"));
            usuario.setPhoto(resultSet.getBytes("photo"));

            if (senhaDigitada.equals(usuario.getPassword())) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            throw new Exception("Erro na execução do SQL - busca de usuário", ex);
        }
    }
    

    public UserModel buscar(Integer id) throws Exception {
        Connection conexao = FabricaConexao.getConnection();
        PreparedStatement ps;
        try {
            ps = conexao.prepareStatement("select * from usuario where id=?"); // obtem apena uma única informação
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                UserModel usuario = new UserModel();
                usuario.setId(resultSet.getInt("id"));
                usuario.setName(resultSet.getString("name"));
                usuario.setLogin(resultSet.getString("login"));
                usuario.setPassword(resultSet.getString("password"));
                usuario.setPhoto(resultSet.getBytes("photo"));
                return usuario;
            }
        } catch (SQLException ex) {
            throw new Exception("Erro na execução do SQL - busca de usuário", ex);
        }
        return null;
    }
}

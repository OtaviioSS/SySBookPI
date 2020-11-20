package repository;

import viewmodel.BookModel;
import viewmodel.ServiceModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.FabricaConexao;

public class ServiceDAO {

    Connection conexao;
    PreparedStatement ps;

    public void salvar(ServiceModel servico) throws Exception {
        try {
            conexao = FabricaConexao.getConnection(); // obtem a conexão com o BD
            if (servico.getId() == null) { // se é uma inserção
                ps = conexao.prepareStatement("INSERT INTO servicos(tiposervico,livro,cpfcliente,dataemprestimo,datadevolucao) values(?,?,?,?,?)");
            } else {
                ps = conexao.prepareStatement("UPDATE servicos set tiposervicos=?,livro=?,cpfcliente=?,dataemprestimo=?,datadevolucao=? where id=?");
                ps.setString(6, servico.getId().toString());
            }
            ps.setString(1, servico.getTiposervico());
            ps.setString(2, servico.getLivro());
            ps.setString(3, servico.getCpfcliente());
            ps.setString(4, servico.getDataemprestimo());
            ps.setString(5, servico.getDatadevolucao());

        } catch (SQLException e) {
            throw new Exception("Ops! problema ao conectar com banco de dados", e);
        }
        try {
            ps.execute();
        } catch (SQLException e) {
            throw new Exception("Ops!Houve um erro ao atribuir os dados", e);
        }
    }

    public List<ServiceModel> buscar() throws Exception {
        try {
            conexao = FabricaConexao.getConnection(); // obtem a conexão com o BD
            PreparedStatement ps = conexao.prepareStatement("select * from servicos");
            ResultSet resultSet = ps.executeQuery();
            List<ServiceModel> servicos = new ArrayList<ServiceModel>();
           
                 while (resultSet.next()) { // percorre todos os registros da tabela
                ServiceModel servico = new ServiceModel();
                servico.setId(resultSet.getInt("id"));
                servico.setTiposervico(resultSet.getString("tiposervico"));
                servico.setLivro(resultSet.getString("livro"));
                servico.setCpfcliente(resultSet.getString("cpfcliente"));
                servico.setDataemprestimo(resultSet.getString("dataemprestimo"));
                servico.setDatadevolucao(resultSet.getString("datadevolucao"));
                servicos.add(servico);
            }
            FabricaConexao.closeConnection();
            return servicos;

        } catch (SQLException e) {
            throw new Exception("Ops! problema ao conectar com banco de dados", e);
        }
    }
    
       public void deletar(ServiceModel servico) throws Exception {
        conexao = FabricaConexao.getConnection(); // obtem a conexão com o BD
        try {
            ps = conexao.prepareStatement("delete from servicos where id=?");
            ps.setInt(1, servico.getId());
            ps.execute();
        } catch (SQLException e) {
            throw new Exception("Ops! Tivemos um erro ao remover o servico, tente novamente", e);
        }
        FabricaConexao.closeConnection();
    }
}

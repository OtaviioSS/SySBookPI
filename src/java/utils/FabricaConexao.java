package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Cria conexões com o banco de dados
 */
public class FabricaConexao {

    /* Atributos */
    private static Connection conexao = null; // é a conexão que será fabricada e retornada para o usuario
    private static final String URL_CONNECTION = "jdbc:mysql://localhost/acoes?useTimezone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false"; // local onde está o banco
    private static final String USER = "root"; // nome da conexão
    private static final String PASSWORD = "14789632"; // senha da conexão

    /* Contrutor */
    private FabricaConexao() {
    }

    /* Métodos */
    public static Connection getConnection() throws Exception {
        // verifica se a conexão não foi criada
        if (conexao == null) {
            // tenta criar uma nova conexão
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // nome do driver Mysql
                conexao = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
            } catch (ClassNotFoundException e) {
                throw new Exception("Erro de driver!");
            } catch (SQLException e) {
                e.printStackTrace();
                throw new Exception("Erro de SQL!");
            }
        }
        return conexao;
    }

    public static void closeConnection() throws Exception {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException e) {
                throw new Exception("Erro no fechamento da conexão", e);
            }
            conexao = null;
        }
    }

}

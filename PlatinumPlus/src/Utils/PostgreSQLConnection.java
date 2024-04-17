package Utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLConnection {
    private static PostgreSQLConnection instance; // Instancia de Banco de Dados
    private Connection connection; // Conectar o Banco 

    private final String url = "jdbc:postgresql://isabelle.db.elephantsql.com:5432/klanfdoe";
    private final String username = "klanfdoe";
    private final String password = "bmiNoFORaiJ_E0cJKWYhJFLKq0oSSVCR";

    /*
     * Para a conexão com o Banco no PgAdmin4
     * Port: 5432
     * Host: isabelle.db.elephantsql.com
     * User: klanfdoe
     * Password: bmiNoFORaiJ_E0cJKWYhJFLKq0oSSVCR
     */

    // Cria uma conexão com o Banco de Dados
    private PostgreSQLConnection() {
        try { // Tenta Conectar
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(url, username, password); 
        } catch (SQLException | ClassNotFoundException e) { // Se não conseguir da erro
            System.err.println("Error connecting to database: " + e.getMessage());
        }
    }

    public static synchronized PostgreSQLConnection getInstance() {
        if (instance == null) {
            instance = new PostgreSQLConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Conexão fechada com sucesso.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }
}
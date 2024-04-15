package Controllers;

import java.sql.*;
import Utils.PostgreSQLConnection;
import Models.Usuario;

public class ControleFuncionario {


    public static boolean criaFuncionario(Usuario usuario) {
        Connection connection = PostgreSQLConnection.getInstance().getConnection();
        PreparedStatement state = null;
        if (ControleUsuario.buscaUsuario(connection, usuario.getCpf()) == null) {
            try {
                String query = "INSERT INTO funcionario (cpf) VALUES (?)";
                state = connection.prepareStatement(query);
                state.setString(1, usuario.getCpf());
                state.executeUpdate();
                System.out.println("criado funcionario");
            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                try {
                    if (state != null) {
                        state.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return true;
        } else {
            return false;
        }
    }
    
}

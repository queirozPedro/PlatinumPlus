package Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ControleJogoUsuario {
    public static boolean comprarJogo(Connection connection, String cpf, int id) {
        PreparedStatement state = null;
        if (ControleJogo.buscarJogo(connection, id) != null && ControleUsuario.buscarUsuario(connection, cpf) != null) {
            try {
                String query = "INSET Into JogoUsuario (cpfUsuario, idJogo, QuantObtidaConquistas, temCupom) VALUES (?,?,?,?)";
                state = connection.prepareStatement(query);
                state.setString(1, cpf);
                state.setInt(2, id);
                state.setInt(3, 0);
                state.setInt(4, 1);
                state.executeUpdate();
                
            } catch (SQLException e) {
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

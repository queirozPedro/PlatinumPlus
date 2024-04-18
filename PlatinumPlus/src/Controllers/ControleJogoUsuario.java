package Controllers;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Jogo;
import Models.JogoUsuario;

public class ControleJogoUsuario {

    public static boolean comprarJogo(Connection connection, String cpf, int id) {
        PreparedStatement state = null;

        // Verifica se o jogo já está na biblioteca do usuário
        if (buscaJugoUsuario(connection, cpf, id) == null) {

            // Verifica se o jogo e o usuário existem
            if (ControleJogo.buscarJogo(connection, id) != null && ControleUsuario.buscarUsuario(connection, cpf) != null) {
                
                // Se o jogo ainda não está na biblioteca do usuário, realiza a inserção
                try {

                    String query = "INSERT INTO JogoUsuario (cpfUsuario, idJogo) VALUES (?, ?)";
                    state = connection.prepareStatement(query);
                    state.setString(1, cpf);
                    state.setInt(2, id);
                    // Os demais valores serão setados com valores default no banco
                    state.executeUpdate();

                    return true;

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
            }
        }
        return false;
    }

    public static JogoUsuario buscaJugoUsuario(Connection connection, String cpf, int idJogo) {
        PreparedStatement state = null;
        ResultSet result = null;

        try {
            String query = "SELECT * FROM JogoUsuario WHERE cpfUsuario = ? AND idJogo = ?";
            state = connection.prepareStatement(query);
            state.setString(1, cpf);
            state.setInt(2, idJogo);
            result = state.executeQuery();

            // Se já existe uma entrada para o jogo na biblioteca do usuário, retorna false
            if (result.next()) {
                return new JogoUsuario(result.getString(1), result.getInt(2), result.getInt(3),
                        (result.getInt(4) == 1 ? true : false));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (state != null) {
                    state.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    
    }

    public static ArrayList<Jogo> buscaJugoUsuario(Connection connection, String cpf) {
        PreparedStatement state = null;
        ResultSet result = null;

        try {
            ArrayList<Jogo> jogos = new ArrayList<>(); 
            String query = "SELECT idJogo FROM JogoUsuario WHERE cpfUsuario = ?";
            state = connection.prepareStatement(query);
            state.setString(1, cpf);
            result = state.executeQuery();

            // Se já existe uma entrada para o jogo na biblioteca do usuário, retorna false
            while (result.next()) {
                Jogo jogo = ControleJogo.buscarJogo(connection, result.getInt(1));
                jogos.add(jogo);
            }
            
            return jogos;
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (state != null) {
                    state.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static ArrayList<Jogo> buscaJugoUsuario(Connection connection) {
        PreparedStatement state = null;
        ResultSet result = null;

        try {
            ArrayList<Jogo> jogos = new ArrayList<>(); 
            String query = "SELECT idJogo FROM JogoUsuario WHERE TemCupom = 1";
            state = connection.prepareStatement(query);
            result = state.executeQuery();

            // Se já existe uma entrada para o jogo na biblioteca do usuário, retorna false
            while (result.next()) {
                Jogo jogo = ControleJogo.buscarJogo(connection, result.getInt(1));
                jogos.add(jogo);
            }
            
            return jogos;
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (state != null) {
                    state.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

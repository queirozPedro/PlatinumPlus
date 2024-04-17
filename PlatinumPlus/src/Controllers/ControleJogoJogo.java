package Controllers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.Jogo;

public class ControleJogoJogo {
    
    /**
     * Esse método recebe os atribútos de aluno e cadastra um aluno no banco
     * 
     * @param connection
     * @param nome
     * @param cpf
     * @param email
     * @param senha
     * @param telefone
     */
     
    public static boolean criarConta(Connection connection, String nome, String genero, String descricao, Date dataLancamento, float valor, String desenvolvedora, int quantConquistas, int descontoElegivel) {
        PreparedStatement state = null;

        try {
            // Insere o usuário na tabela Jogo
            String query = "INSERT Into Jogo (nome, genero, descricao, dataLancamento, valor, desenvolvedora, quantConquistas, descontoElegivel) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            state = connection.prepareStatement(query);
            state.setString(1, nome);
            state.setString(2, genero);
            state.setString(3, descricao);
            state.setDate(4, dataLancamento);
            state.setFloat(5, valor);
            state.setString(6, desenvolvedora);
            state.setInt(7, quantConquistas);
            state.setInt(8, descontoElegivel);

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

        return false;
    }


    public static Jogo buscarJogo(Connection connection, int idJogo) {
        PreparedStatement state = null;
        ResultSet result = null;

        try {
            
            String query = "SELECT * From Jogo where idJogo = ?";
            state = connection.prepareStatement(query);
            state.setInt(1, idJogo);
            result = state.executeQuery();

            // Retorna o Jogo
            if (result.next()) {
                return new Jogo(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getDate(5), result.getFloat(6), result.getString(7), result.getInt(8), result.getInt(9));
            }

        } catch (SQLException e) {
            // Trate a exceção ou registre o erro, não apenas imprima a pilha de exceção
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

    public static Jogo buscarJogo(Connection connection, float valor) {
        PreparedStatement state = null;
        ResultSet result = null;

        try {
            
            String query = "SELECT * From Jogo where valor <= ?";
            state = connection.prepareStatement(query);
            state.setFloat(1, valor);
            result = state.executeQuery();

            // Retorna o Jogo
            if (result.next()) {
                return new Jogo(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getDate(5), result.getFloat(6), result.getString(7), result.getInt(8), result.getInt(9));
            }

        } catch (SQLException e) {
            // Trate a exceção ou registre o erro, não apenas imprima a pilha de exceção
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

    public static Jogo buscarJogoNome(Connection connection, String nome) {
        PreparedStatement state = null;
        ResultSet result = null;

        try {
            
            String query = "SELECT * From Jogo where nome = ?";
            state = connection.prepareStatement(query);
            state.setString(1, nome);
            result = state.executeQuery();

            // Retorna o Jogo
            if (result.next()) {
                return new Jogo(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getDate(5), result.getFloat(6), result.getString(7), result.getInt(8), result.getInt(9));
            }

        } catch (SQLException e) {
            // Trate a exceção ou registre o erro, não apenas imprima a pilha de exceção
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

    public static Jogo buscarJogoGenero(Connection connection, String genero) {
        PreparedStatement state = null;
        ResultSet result = null;

        try {
            
            String query = "SELECT * From Jogo where genero = ?";
            state = connection.prepareStatement(query);
            state.setString(1, genero);
            result = state.executeQuery();

            // Retorna o Jogo
            if (result.next()) {
                return new Jogo(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getDate(5), result.getFloat(6), result.getString(7), result.getInt(8), result.getInt(9));
            }

        } catch (SQLException e) {
            // Trate a exceção ou registre o erro, não apenas imprima a pilha de exceção
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
    
    public static Jogo buscarJogoDeselvovedora(Connection connection, String desenvolvedora) {
        PreparedStatement state = null;
        ResultSet result = null;

        try {
            
            String query = "SELECT * From Jogo where desenvolvedora = ?";
            state = connection.prepareStatement(query);
            state.setString(1, desenvolvedora);
            result = state.executeQuery();

            // Retorna o Jogo
            if (result.next()) {
                return new Jogo(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getDate(5), result.getFloat(6), result.getString(7), result.getInt(8), result.getInt(9));
            }

        } catch (SQLException e) {
            // Trate a exceção ou registre o erro, não apenas imprima a pilha de exceção
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

    // // Isso aqui eu faço jajá
    // public void editarJogo(Connection connection, String campo, String valor, Jogo Jogo) {
    //     PreparedStatement state = null;

    //     /*
    //      * Primeiro checa se algum desses dados foi recebido e aplica valores
    //      * locais aos que forem null.
    //      */
    //     try {

    //         // Atualiza nome, senha e email na tabela Jogo na posição do cpf usado.
    //         String query = "UPDATE Jogo SET " + campo + " = ? WHERE cpf = ?";
    //         state = connection.prepareStatement(query);
    //         state.setString(1, valor);
    //         state.setString(2, Jogo.getCpf());
    //         state.executeUpdate();
    //         state.close(); // Checar se precisa ser aqui

    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     } finally {
    //         try {
    //             if (state != null) {
    //                 state.close();
    //             }
    //         } catch (SQLException e) {
    //             e.printStackTrace();
    //         }
    //     }
    // }

    public static boolean excluirJogo(Connection connection, int idJogo) {
        PreparedStatement state = null;

        try {

            String query = "DELETE From Jogo where idJogo = ?";
            state = connection.prepareStatement(query);
            state.setInt(1, idJogo);
            state.executeUpdate();
           
            return true;

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
        return false;
    }
}

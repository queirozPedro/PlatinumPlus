package Controllers;

import java.sql.*;
import Utils.PostgreSQLConnection;
import Models.Funcionario;
import Models.Usuario;

public class ControleFuncionario {


    /**
     * Método que Cria um Funcionario no Banco
     * (Precisa de Revisão)
     * @param usuario
     * @return
     */
    public static boolean criaFuncionario(Usuario usuario) {

        Connection connection = PostgreSQLConnection.getInstance().getConnection();
        PreparedStatement state = null;
        if (ControleUsuario.buscarUsuario(connection, usuario.getCpf()) == null) {
            try {
                String query = "INSERT INTO Funcionario (cpf) VALUES (?)";
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

    /**
     * Metodo para editar um cpf? parece estanho
     * (Precisa de revisão)
     * @param connection
     * @param campo
     * @param valor
     * @param funcionario
     */
    public void editarFuncionario(Connection connection, String campo, String valor, Funcionario funcionario) {
        PreparedStatement state = null;

        /*
         * Primeiro checa se algum desses dados foi recebido e aplica valores
         * locais aos que forem null.
         */
        try {

            // Atualiza nome, senha e email na tabela usuario na posição do cpf usado.
            String query = "UPDATE Funcionario SET "+ campo +" = ? WHERE cpf = ?";
            state = connection.prepareStatement(query);
            state.setString(1, valor);
            state.setString(2, funcionario.getCpf());
            state.executeUpdate();
            state.close(); // Checar se precisa ser aqui

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
    }

    /**
     * Método que remove um funcionário do banco de dados
     * (Precisa de revisão)
     * Possivel erro: O funcionário será excluido, mas e o usuário que ele herda?
     * @param connection
     * @param cpf
     */
    public static void excluirFuncionario(Connection connection, String cpf) {
        PreparedStatement state = null;

        try {
            /*
             * Aqui precisa ser colocada a lógica:
             * Se um usuário possúi jogos ou cupons, eles precisam ser excluidos antes do usuario ser excluido 
            */

            // Remove o funcionário da tabela Funcionario
            String query = "DELETE From Funcioanrio where cpf = ?";
            state = connection.prepareStatement(query);
            state.setString(1, cpf);
            state.executeUpdate();
            System.out.println(" Funcionário Excluido! ");

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
    }

    /**
     * Método que faz a autenticação do funcionário.
     * (Precisa de revisão)
     * Possível erro: a tabela funcionário não possui esses campos. O tipo do retorno também está 
     * errado.
     * @param connection
     * @param email
     * @param senha
     * @return Funcionario
     */
    public static Funcionario loginFuncionario(Connection connection ,String email, String senha) {
        Usuario userAux = ControleUsuario.loginUsuario(connection, email, senha);

        PreparedStatement state = null;
        ResultSet result = null;

        try {
            
            // Seleciona o cpf do funcionário na tabela que tenha os mesmos email e senha
            String query = "SELECT cpf From Funcionario where cpf = ?";
            state = connection.prepareStatement(query);
            state.setString(1, userAux.getCpf());
            result = state.executeQuery();
            
            if (result.next()) {
                return new Funcionario(ControleUsuario.buscarUsuario(connection, result.getString(1)));
            }

        } catch (Exception e) {
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
package Controllers;

import java.sql.*;
import Utils.PostgreSQLConnection;
import Models.Funcionario;
import Models.Usuario;

public class ControleFuncionario {


    public static boolean criaFuncionario(Usuario usuario) {
        Connection connection = PostgreSQLConnection.getInstance().getConnection();
        PreparedStatement state = null;
        if (ControleUsuario.buscaUsuario(connection, usuario.getCpf()) == null) {
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

    public static Funcionario buscaFuncionario(Connection connection, String cpf) {
        PreparedStatement state = null;
        ResultSet result = null;

        try {
            // Seleciona tudo (*) na tabela Usuario onde o cpf foi o igual ao recebido
            String query = "SELECT * From Funcionario where cpf = ?";
            state = connection.prepareStatement(query);
            state.setString(1, cpf);
            result = state.executeQuery();

            // Retorna o usuário
            if (result.next()) {
                return new Funcionario(result.getString(1), result.getString(2), result.getString(3), result.getString(4),
                        result.getString(5));
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

    public static Usuario loginFuncionario(Connection connection ,String email, String senha) {
        PreparedStatement state = null;
        ResultSet result = null;

        try {

            // Seleciona o cpf do funcionário na tabela que tenha os mesmos email e senha
            String query = "SELECT cpf From Funcionario where email = ? AND senha = ?";
            state = connection.prepareStatement(query);
            state.setString(1, email);
            state.setString(2, senha);
            result = state.executeQuery();
            if (result.next()) {
                return buscaFuncionario(connection, result.getString(1));
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

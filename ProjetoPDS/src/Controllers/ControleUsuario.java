package Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.Usuario;

public class ControleUsuario {
    
    /**
     * Esse método recebe os atribútos de aluno e cadastra um aluno no 
     * @param connection
     * @param nome
     * @param cpf
     * @param email
     * @param senha
     * @param telefone
     */
    public static void criarConta(Connection connection, String nome, String cpf, String email, String senha, String telefone) {
        PreparedStatement state = null;

        if (buscaUsuario(connection, cpf) == null) {
            try {

                // Insere o usuário na tabela Usuario
                String query = "INSERT Into usuario (cpf, nome, senha, email, telefone) VALUES (?, ?, ?, ?, ?)";
                state = connection.prepareStatement(query);
                state.setString(1, cpf);
                state.setString(2, nome);
                state.setString(3, senha);
                state.setString(4, email);
                state.setString(5, telefone);
                state.executeUpdate();

                // Insere os telefones dele na tabela Telefone com base em seu cpf
                System.out.println(" Usuário Cadastrado!");

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
        } else {
            System.out.println(" ERRO! Cpf já Cadastrado!");
        }
    }

    public static Usuario buscaUsuario(Connection connection, String cpf) {
        PreparedStatement state = null;
        ResultSet result = null;

        try {
            // Seleciona tudo (*) na tabela Usuario onde o cpf foi o igual ao recebido
            String query = "SELECT * From usuario where cpf = ?";
            state = connection.prepareStatement(query);
            state.setString(1, cpf);
            result = state.executeQuery();

            // Retorna o usuário
            if (result.next()) {
                return new Usuario(result.getString(1), result.getString(2), result.getString(3), result.getString(4),
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

    public void editarUsuario(Connection connection, String campo, String valor, Usuario usuario) {
        PreparedStatement state = null;

        /*
         * Primeiro checa se algum desses dados foi recebido e aplica valores
         * locais aos que forem null.
         */
        try {

            // Atualiza nome, senha e email na tabela usuario na posição do cpf usado.
            String query = "UPDATE Usuario SET "+ campo +" = ? WHERE cpf = ?";
            state = connection.prepareStatement(query);
            state.setString(1, valor);
            state.setString(2, usuario.getCpf());
            state.executeUpdate();
            state.close();

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

    public static void excluirConta(Connection connection, String cpf) {
        PreparedStatement state = null;

        try {
            /*
             * Aqui precisa ser colocada a lógica:
             * Se um usuário possúi jogos ou cupons, eles precisam ser excluidos antes do usuario ser excluido 
            */

            // Remove o usuário da tabela Usuario
            String query = "DELETE From usuario where cpf = ?";
            state = connection.prepareStatement(query);
            state.setString(1, cpf);
            state.executeUpdate();
            System.out.println(" Usuário Excluido! ");

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

    public static Usuario loginUsuario(Connection connection ,String email, String senha) {
        PreparedStatement state = null;
        ResultSet result = null;

        try {

            // Seleciona o cpf do usuario na tabela que tenha os mesmos email e senha
            String query = "SELECT cpf From usuario where email = ? AND senha = ?";
            state = connection.prepareStatement(query);
            state.setString(1, email);
            state.setString(2, senha);
            result = state.executeQuery();
            if (result.next()) {
                return buscaUsuario(connection, result.getString(1));
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

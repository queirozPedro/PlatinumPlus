package Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.Usuario;

public class ControleUsuario {

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
    public static boolean criarConta(Connection connection, String nome, String cpf, String email, String senha, String telefone) {
        PreparedStatement state = null;

        try {
            // Insere o usuário na tabela Usuario
            String query = "INSERT Into Usuario (cpf, nome, senha, email, telefone) VALUES (?, ?, ?, ?, ?)";
            state = connection.prepareStatement(query);
            state.setString(1, cpf);
            state.setString(2, nome);
            state.setString(3, senha);
            state.setString(4, email);
            state.setString(5, telefone);
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

    /**
     * 
     * @param connection
     * @param cpf
     * @return Usuario
     */
    public static Usuario buscarUsuario(Connection connection, String cpf) {
        PreparedStatement state = null;
        ResultSet result = null;

        try {
            // Seleciona tudo (*) na tabela Usuario onde o cpf foi o igual ao recebido
            String query = "SELECT * From Usuario where cpf = ?";
            state = connection.prepareStatement(query);
            state.setString(1, cpf);
            result = state.executeQuery();

            // Retorna o usuário
            if (result.next()) {
                return new Usuario(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5));
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

        try {

            // Atualiza nome, senha e email na tabela usuario na posição do cpf usado.
            String query = "UPDATE Usuario SET " + campo + " = ? WHERE cpf = ?";
            state = connection.prepareStatement(query);
            state.setString(1, valor);
            state.setString(2, usuario.getCpf());
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

    public static void excluirConta(Connection connection, String cpf) {
        PreparedStatement state = null;

        try {
            /*
             * Aqui precisa ser colocada a lógica:
             * Se um usuário possúi jogos ou cupons, eles precisam ser excluidos antes do
             * usuario ser excluido
             */

            // Remove o usuário da tabela Usuario
            String query = "DELETE From Usuario where cpf = ?";
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

    /**
     * @param connection
     * @param email
     * @param senha
     * @return Usuario
     */
    public static Usuario loginUsuario(Connection connection, String email, String senha) {
        PreparedStatement state = null;
        ResultSet result = null;

        try {

            // Seleciona o cpf do usuario na tabela que tenha os mesmos email e senha
            String query = "SELECT * From Usuario where email = ? AND senha = ?";
            state = connection.prepareStatement(query);
            state.setString(1, email);
            state.setString(2, senha);
            result = state.executeQuery();
            
            if (result.next()) {
                return new Usuario(result.getString(2), result.getString(1), result.getString(3), result.getString(4), result.getString(5));
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

    /**
     * Esse método funciona como uma verificação dos dados do usuário.
     * Retorna uma string com os erros encontrados na validação dos dados.
     * 
     * @param nome
     * @param cpf
     * @param email
     * @param senha
     * @param reSenha
     * @param telefone
     * @return String
     */
    public static String validarUsuario(String nome, String cpf, String email, String senha, String reSenha, String telefone) {
        String string = "";
        string += validarNome(nome) != "" ? " " + validarNome(nome) + "\n" : "";
        string += validarCpf(cpf) != "" ? " " + validarCpf(cpf) + "\n" : "";
        string += validarEmail(email) != "" ? " " + validarEmail(email) + "\n" : "";
        string += validarSenha(senha, reSenha) != "" ? " " + validarSenha(senha, reSenha) + "\n" : "";
        string += validarTelefone(telefone) != "" ? " " + validarTelefone(telefone) + "\n" : "";
        return string;
    }

    public static String validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return "O campo nome precisa ser preenchido.";
        }
        return "";
    }

    public static String validarCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            return "O campo cpf precisa ser preenchido.";
        }
        if (!cpf.matches("\\d{11}")) {
            return "O cpf deve possuir 11 digitos";
        }
        return "";
    }

    public static String validarTelefone(String telefone) {
        if (telefone == null || telefone.isEmpty()) {
            return "O campo telefone precisa ser preenchido.";
        }
        if (!telefone.matches("\\d{11}")) {
            return "O telefone deve possuir 11 digitos";
        }
        return "";
    }

    public static String validarEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return "O campo email precisa ser preenchido.";
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            return "Digite um email válido.";
        }
        return "";

    }

    public static String validarSenha(String senha, String reSenha) {
        if (senha == null || senha.trim().isEmpty() || senha.length() < 6) {
            return "A senha deve possuir no mínimo 6 caracteres.";
        } else {
            if (senha.trim().equals(reSenha.trim())) {
                return "";
            } else {
                return "As senhas devem concidir.";
            }
        }
    }
}
package Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Models.Jogo;

public class ControleJogo {

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

    public static boolean criarJogo(Connection connection, String nome, String genero, String descricao, float valor, String desenvolvedora, int quantConquistas, int descontoElegivel) {
        PreparedStatement state = null;

            try {
                String query = "INSERT Into Jogo (nome, genero, descricao, valor, desenvolvedora, quantConquistas, descontoElegivel) VALUES (?, ?, ?, ?, ?, ?, ?)";
                state = connection.prepareStatement(query);
                state.setString(1, nome);
                state.setString(2, genero);
                state.setString(3, descricao);
                state.setFloat(4, valor);
                state.setString(5, desenvolvedora);
                state.setInt(6, quantConquistas);
                state.setInt(7, descontoElegivel);

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
                return new Jogo(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getFloat(5), result.getString(6), result.getInt(7), result.getInt(8));
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

    public static Jogo buscarJogo(Connection connection, float valorMin, float valorMax) {
        PreparedStatement state = null;
        ResultSet result = null;

        try {

            String query = "SELECT * From Jogo where valor >= ? AND valor <= ?";
            state = connection.prepareStatement(query);
            state.setFloat(1, valorMin);
            state.setFloat(2, valorMax);
            result = state.executeQuery();

            // Retorna o Jogo
            if (result.next()) {
                return new Jogo(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getFloat(5), result.getString(6), result.getInt(7), result.getInt(8));
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
                return new Jogo(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getFloat(5), result.getString(6), result.getInt(7), result.getInt(8));
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
                return new Jogo(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getFloat(5), result.getString(6), result.getInt(7), result.getInt(8));
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
                return new Jogo(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getFloat(5), result.getString(6), result.getInt(7), result.getInt(8));
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
    // public void editarJogo(Connection connection, String campo, String valor,
    // Jogo Jogo) {
    // PreparedStatement state = null;

    // /*
    // * Primeiro checa se algum desses dados foi recebido e aplica valores
    // * locais aos que forem null.
    // */
    // try {

    // // Atualiza nome, senha e email na tabela Jogo na posição do cpf usado.
    // String query = "UPDATE Jogo SET " + campo + " = ? WHERE cpf = ?";
    // state = connection.prepareStatement(query);
    // state.setString(1, valor);
    // state.setString(2, Jogo.getCpf());
    // state.executeUpdate();
    // state.close(); // Checar se precisa ser aqui

    // } catch (Exception e) {
    // e.printStackTrace();
    // } finally {
    // try {
    // if (state != null) {
    // state.close();
    // }
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // }
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
    public static String validarJogo(String nome, String genero, String descricao, float valor,
            String desenvolvedora, int quantConquistas, int descontoElegivel) {
        String string = "";
        string += validarNome(nome) != "" ? " " + validarNome(nome) + "\n" : "";
        string += validarGenero(genero) != "" ? " " + validarGenero(genero) + "\n" : "";
        string += validarDescrição(descricao) != "" ? " " + validarDescrição(descricao) + "\n" : "";
        string += validarValor(valor) != "" ? " " + validarValor(valor) + "\n" : "";
        string += validarDesenvolvedora(desenvolvedora) != "" ? " " + validarDesenvolvedora(desenvolvedora) + "\n" : "";
        string += validarQuantConquistas(quantConquistas) != "" ? " " + validarQuantConquistas(quantConquistas) + "\n" : "";
        string += validarDescontoElegivel(descontoElegivel) != "" ? " " + validarDescontoElegivel(descontoElegivel) + "\n" : "";
        return string;
    }

    public static String validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return "O campo nome precisa ser preenchido.";
        }
        return "";
    }    

    public static String validarGenero(String genero) {
        if (genero == null || genero.trim().isEmpty()) {
            return "O campo gênero precisa ser preenchido.";
        }
        return "";
    }    

    public static String validarDescrição(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            return "O campo descrição precisa ser preenchido.";
        }
        return "";
    }

    public static String validarValor(float valor) {
        if (valor < 0) {
            return "O campo valor deve ser nulo ou positivo";
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

    public static String validarDesenvolvedora(String desenvolvedora) {
        if (desenvolvedora == null || desenvolvedora.trim().isEmpty()) {
            return "O campo desenvolvedora precisa ser preenchido.";
        }
        return "";
    }

    public static String validarQuantConquistas(int quantConquistas) {
        if (quantConquistas < 0) {
            return "O campo quantidade de conquistas deve possuir um valor nulo ou positivo.";
        }
        return "";
    }

    public static String validarDescontoElegivel(int descontoElegivel) {
        if (descontoElegivel < 0) {
            return "O campo desconto elegível deve possuir um valor nulo ou positivo.";
        }
        return "";
    }

}

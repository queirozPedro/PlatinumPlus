package Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Jogo;

public class ControleJogo {

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

    public static ArrayList<Jogo> buscarJogo(Connection connection, String string) {
        PreparedStatement state = null;
        ResultSet result = null;

        try {

            ArrayList<Jogo> jogos = new ArrayList<Jogo>();
            String query = "SELECT * FROM Jogo WHERE nome like ? OR genero like ? OR desenvolvedora like ?";
            state = connection.prepareStatement(query);
            state.setString(1, "%" + string + "%");
            state.setString(2, "%" + string + "%");
            state.setString(3, "%" + string + "%");
            result = state.executeQuery();

            // Retorna o Jogo
            while (result.next()) {
                Jogo jogo = new Jogo(
                    result.getInt(1), 
                    result.getString(2), 
                    result.getString(3), 
                    result.getString(4), 
                    result.getFloat(5), 
                    result.getString(6), 
                    result.getInt(7), 
                    result.getInt(8)
                );
                jogos.add(jogo);
            }
            
            return jogos;

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
    
    public static ArrayList<Jogo> buscarJogoGenero(Connection connection, String genero) {
        PreparedStatement state = null;
        ResultSet result = null;

        try {

            ArrayList<Jogo> jogos = new ArrayList<Jogo>();
            String query = "SELECT * FROM Jogo WHERE genero like ? ORDER BY RANDOM() LIMIT 10";
            state = connection.prepareStatement(query);
            state.setString(1, "%" + genero + "%");
            result = state.executeQuery();

            // Retorna o Jogo
            while (result.next()) {
                Jogo jogo = new Jogo(
                    result.getInt(1), 
                    result.getString(2), 
                    result.getString(3), 
                    result.getString(4), 
                    result.getFloat(5), 
                    result.getString(6), 
                    result.getInt(7), 
                    result.getInt(8)
                );
                jogos.add(jogo);
            }
            
            return jogos;

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

    public static ArrayList<Jogo> explorarJogos(Connection connection) {
        PreparedStatement state = null;
        ResultSet result = null;

        try {
            ArrayList<Jogo> jogos = new ArrayList<Jogo>();
            String query = "SELECT * FROM Jogo ORDER BY RANDOM() LIMIT 10";
            state = connection.prepareStatement(query);
            result = state.executeQuery();

            while (result.next()){
                Jogo jogo = new Jogo(
                    result.getInt(1), 
                    result.getString(2), 
                    result.getString(3), 
                    result.getString(4), 
                    result.getFloat(5), 
                    result.getString(6), 
                    result.getInt(7), 
                    result.getInt(8)
                );
                jogos.add(jogo);
            }

            return jogos;

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

    /**
     * Método que exclui jogos pelo id
     * @param connection
     * @param idJogo
     * @return boolean
     */
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

    public static String listarJogosUsuario(ArrayList<Jogo> jogos){
        String string = "";
        for(int i = 0; i < jogos.size(); i++){
            string += jogos.get(i).exibirJogo()+"\n\n";
        }
        return string;

    }
    public static String listarJogosFuncionario(ArrayList<Jogo> jogos){
        String string = "";
        for(int i = 0; i < jogos.size(); i++){
            string += jogos.get(i).toString()+"\n\n";
        }
        return string;
    }
}

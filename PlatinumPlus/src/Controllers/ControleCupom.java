package Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.*;

public class ControleCupom {

    public static void criarCupom(Connection connection, int idCupom, String cpf) {
        PreparedStatement state = null;

        if (buscaCupom(connection, idCupom) == null) {
            try {

                // Insere o usuário na tabela Usuario
                String query = "INSERT Into Cupom (idCupom, cpf, desconto) VALUES (?, ?, ?)";
                state = connection.prepareStatement(query);
                state.setInt(1, idCupom);
                state.setString(2, cpf);
                state.setInt(3, 0);
                state.executeUpdate();

                // Insere os telefones dele na tabela Telefone com base em seu cpf
                System.out.println(" Cupom Cadastrado!");

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
        } else {
            System.out.println(" ERRO! Cupom já Cadastrado!");
        }
    }

    public static Cupom buscaCupom(Connection connection, int idCupom) {
        PreparedStatement state = null;
        ResultSet result = null;

        try {
            // Seleciona tudo (*) na tabela Usuario onde o cpf foi o igual ao recebido
            String query = "SELECT * From Cupom where cpf = ?";
            state = connection.prepareStatement(query);
            state.setInt(1, idCupom);
            result = state.executeQuery();

            // Retorna o usuário
            if (result.next()) {
                return new Cupom(result.getInt(1), result.getString(2), result.getInt(3));
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

    public static int calculaDesconto(Connection connection, JogoUsuario jogoUsuario, Jogo jogo, Cupom cupom){
        PreparedStatement state = null;
        if (buscaCupom(connection, cupom.getIdCupom()) != null && jogoUsuario.getTemCupom() == true) {
            try {
                // Insere o usuário na tabela Usuario
                cupom.setDesconto((jogoUsuario.getQuantObtidaConquistas()/jogo.getQuantConquistas())*jogo.getDescontoElegivel());
                String query = "UPDATE Into Cupom (desconto) VALUES (?)";
                state = connection.prepareStatement(query);
                state.setInt(3, cupom.getDesconto());
                state.executeUpdate();

                query = "UPDATE Into jogoUsuario (desconto) VALUES (?)";
                state = connection.prepareStatement(query);
                state.setInt(4,0);
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
            return cupom.getDesconto();
        } else{
            return 0;
        } 
    }

       
}

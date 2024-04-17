package Views;

import java.io.IOException;
import java.sql.Connection;
import java.util.Scanner;

import Utils.PostgreSQLConnection;

import Controllers.*;
import Models.Funcionario;
import Models.Usuario;

public class ViewMain {

    static Connection connection;

    public static void main(String[] args) throws InterruptedException, IOException {
        boolean sair = false;
        Scanner sc = new Scanner(System.in);
        connection = PostgreSQLConnection.getInstance().getConnection();

        while (!sair) {
            try {
                LimpaTela();
                System.out.println(" ===  Menu Principal  ===");
                System.out.println(" 1 -> Navegar por Jogos");
                System.out.println(" 2 -> Buscar Jogos");
                System.out.println(" 3 -> Iniciar Sessão");
                System.out.println(" 0 -> Sair");
                System.out.print(" >> ");
                switch (Integer.valueOf(Integer.valueOf(sc.nextLine()))) {
                    case 1:
                        menuNavegacao(sc);
                        break;
                    case 2:
                        LimpaTela(); 
                        System.out.println(" < Buscar Jogo >");
                        System.out.print(" >> ");
                        String search = ControleJogo.listarJogosUsuario(ControleJogo.buscarJogo(connection, sc.nextLine()));
                        if(!search.trim().isBlank()){
                            LimpaTela();
                            System.out.println(" < Resultado da Busca >\n");
                            System.out.println(search);
                        }
                        else{
                            System.out.println(colorirTexto("\n Nenhum jogo encontrado.", corVermelho));
                        }
                        System.out.print(" Aperte enter para continuar! ");
                        sc.nextLine();
                         
                        break;
                    case 3:
                        menuIniciarSessao(sc);
                        break;
                    case 0: // Sair
                        sair = true;
                        System.out.println("\n     - Finalizando -\n");
                        return;
                    default:
                        break;
                }
            } catch (NumberFormatException e) {
            }
        }
        sc.close();
    }

    public static String menuNavegacao(Scanner sc) throws InterruptedException, IOException {
        LimpaTela();
        return null;
    }

    public static void menuIniciarSessao(Scanner sc) throws InterruptedException, IOException {
        LimpaTela();
        System.out.println(" ===  Iniciar Sessão  ===");
        System.out.println(" 1 -> Login");
        System.out.println(" 2 -> Registrar-se");
        System.out.println(" 0 -> Sair");
        System.out.print(" >> ");

        try {
            switch (Integer.valueOf(sc.nextLine())) {
                case 1: // Usuário
                    menuLogin(sc);
                    break;
                case 2: // Cliente
                    menuCadastro(sc);
                    break;
                default:
                    break;
            }
        } catch (NumberFormatException e) {
        }
    }

    /**
     * menu responsável por realizar o login do Usuário
     * 
     * @param sc
     * @throws InterruptedException
     * @throws IOException
     */
    public static void menuLogin(Scanner sc) throws InterruptedException, IOException {
        boolean sair = false;
        do {
            try {
                LimpaTela();
                System.out.println(" ===  Menu Login  ===");
                System.out.println(" 1 -> Realizar Login");
                System.out.println(" 0 -> Voltar");
                System.out.print(" >> ");

                switch (Integer.valueOf(sc.nextLine())) {
                    case 1:
                        LimpaTela();
                        System.out.println(" < Login >");
                        System.out.print(" Email: ");
                        String email = sc.nextLine();
                        System.out.print(" Senha: ");
                        String senha = sc.nextLine();

                        if (ControleUsuario.loginUsuario(connection, email, senha) != null) {
                            if (ControleFuncionario.loginFuncionario(connection, email, senha) != null) {
                                menuFuncionario(sc, ControleFuncionario.loginFuncionario(connection, email, senha));
                                return;
                            } else {
                                menuUsuario(sc, ControleUsuario.loginUsuario(connection, email, senha));
                                return;
                            }
                        } else {
                            System.out.println(colorirTexto("\n Usuário não cadastrado!", corVermelho));
                            System.out.print(" Precione Enter para Continuar!");
                            sc.nextLine();
                        }
                        break;

                    case 0:
                        sair = true;
                        break;
                    default:
                        break;
                }
            } catch (NumberFormatException e) {
            }
        } while (!sair);

    }

    /**
     * Método que exibe um menu de Registro de Usuário
     * 
     * @param sc
     * @throws InterruptedException
     * @throws IOException
     */
    public static void menuCadastro(Scanner sc) throws InterruptedException, IOException {
        boolean sair = false;
        do {
            try {
                LimpaTela();
                System.out.println(" ===  Tela de Cadastro  ===");
                System.out.println(" 1 -> Registrar-se");
                System.out.println(" 0 -> Voltar");
                System.out.print(" >> ");
                switch (Integer.valueOf(sc.nextLine())) {
                    case 1:
                        String nome = null, cpf = null, email = null, senha = null, reSenha = null,
                                telefone = null;
                        do {
                            LimpaTela();
                            System.out.println(" < Cadastro >");
                            System.out.print(" Nome: ");
                            nome = sc.nextLine();
                            System.out.print(" Cpf: ");
                            cpf = sc.nextLine().trim().replace("[.-]", "");
                            System.out.print(" Email: ");
                            email = sc.nextLine();
                            System.out.print(" Telefone: ");
                            telefone = sc.nextLine().trim().replace("[.-()]", "");
                            System.out.print(" Senha: ");
                            senha = sc.nextLine();
                            System.out.print(" Confirmar senha: ");
                            reSenha = sc.nextLine();

                            if (ControleUsuario.validarUsuario(nome, cpf, email, senha, reSenha, telefone).trim()
                                    .isBlank()) {
                                if (ControleUsuario.buscarUsuario(connection, cpf) == null) {
                                    System.out.println(" Confirmar operação 1 -> Sim, 2 -> Não");
                                    System.out.print(" >> ");
                                    if (Integer.valueOf(sc.nextLine()) == 1) {
                                        if (ControleUsuario.criarConta(connection, nome, cpf, email, reSenha,
                                                telefone)) {
                                            System.out
                                                    .println(colorirTexto("Usuário Cadastrado com Sucesso!", corVerde));
                                            System.out.print(" Aperte Enter para Continuar!");
                                            sc.nextLine();
                                            return;
                                        } else {
                                            System.out
                                                    .println(colorirTexto("\n Erro ao criar o usuário.", corVermelho));
                                        }
                                    } else {
                                        System.out.print(" Cancelando operação.\n Aperte Enter para Continuar!");
                                        sc.nextLine();
                                        break;
                                    }
                                } else {
                                    System.out.println(colorirTexto("\n Um usuário com esse cpf já está cadastrado.",
                                            corVermelho));
                                }
                            } else {
                                System.out.println("\n" + colorirTexto(
                                        ControleUsuario.validarUsuario(nome, cpf, email, senha, reSenha, telefone),
                                        corVermelho));
                            }
                            System.out.print(" Aperte Enter para Continuar!");
                            sc.nextLine();
                        } while (!sair);
                        sair = false;
                        break;
                    case 0:
                        return;
                }
            } catch (NumberFormatException e) {
            }
        } while (!sair);
    }

    public static void menuUsuario(Scanner sc, Usuario usuario) throws InterruptedException, IOException {
        boolean sair = false;
        do {
            try {
                LimpaTela();
                System.out.println("  === PlatinumPlus ===");
                System.out.println(" 1 -> Navegar por Jogos");
                System.out.println(" 2 -> Pesquisar Jogo");
                System.out.println(" 3 -> Minha Biblioteca");
                System.out.println(" 4 -> Meus Cupons");
                System.out.println(" 5 -> Meu Perfil");
                System.out.println(" 0 -> Sair");
                System.out.print(" >> ");
                switch (Integer.valueOf(sc.nextLine())) {
                    case 1:
                        break;

                    case 0:
                        sair = true;
                        return;
                }

            } catch (NumberFormatException e) {
            }
        } while (!sair);
    }

    public static void menuFuncionario(Scanner sc, Funcionario funcionario) throws InterruptedException, IOException {
        boolean sair = false;
        do {
            try {
                LimpaTela();
                System.out.println("  === Menu de Gerenciamento ===");
                System.out.println(" 1 -> Gerenciar Jogos");
                System.out.println(" 2 -> Gerenciar Funcionários");
                System.out.println(" 3 -> Editar Perfil");
                System.out.println(" 0 -> Sair");
                System.out.print(" >> ");
                switch (Integer.valueOf(sc.nextLine())) {
                    case 1:
                        menuGerenciarJogos(connection, sc);
                        break;
                    case 2:
                        menuGerenciarFuncionarios(connection, sc);
                        break;
                    case 3:
                        menuPerfil(connection, sc, funcionario);    
                    case 0:
                        sair = true;
                        return;
                }

            } catch (NumberFormatException e) {
            }
        } while (!sair);
    }

    public static void menuGerenciarJogos(Connection connection, Scanner sc) throws InterruptedException, IOException {
        boolean sair = false;
        do {
            try {
                LimpaTela();
                System.out.println("  === Gerenciar Jogos ===");
                System.out.println(" 1 -> Adicionar Jogo");
                System.out.println(" 2 -> Remover Jogo");
                System.out.println(" 3 -> Buscar Jogo");
                System.out.println(" 4 -> Editar Jogo");
                System.out.println(" 0 -> Sair");
                System.out.print(" >> ");
                switch (Integer.valueOf(sc.nextLine())) {
                    case 1:
                        do {

                            String nome, genero, descricao, desenvolvedora;
                            float valor;
                            int quantConquistas, descontoElegivel;

                            LimpaTela();
                            System.out.println(" < Cadastrar Jogo>");
                            System.out.print(" Nome: ");
                            nome = sc.nextLine();
                            System.out.print(" Gênero: ");
                            genero = sc.nextLine();
                            System.out.print(" Descrição: ");
                            descricao = sc.nextLine();
                            System.out.print(" Desenvolvedora: ");
                            desenvolvedora = sc.nextLine();
                            System.out.print(" Valor: ");
                            valor = Float.valueOf(sc.nextLine());
                            System.out.print(" Quantidade de Conquistas: ");
                            quantConquistas = Integer.valueOf(sc.nextLine());
                            System.out.print(" Desconto Elegível: ");
                            descontoElegivel = Integer.valueOf(sc.nextLine());

                            if (ControleJogo.validarJogo(nome, genero, descricao, valor, desenvolvedora,
                                quantConquistas, descontoElegivel).trim().isBlank()) {
                                System.out.println(" Cadastrar Jogo? 1 -> Sim, 2 -> Não");
                                System.out.print(" >> ");
                                if (Integer.valueOf(sc.nextLine()) == 1) {
                                    if (ControleJogo.criarJogo(connection, nome, genero, descricao,
                                            valor, desenvolvedora, quantConquistas, descontoElegivel)) {
                                        System.out
                                                .println(colorirTexto("\n Jogo cadastrado com sucesso!", corVerde));
                                        System.out.print(" Aperte Enter para continuar! ");
                                        sc.nextLine();
                                        break;
                                    } else {
                                        System.out.println(" Erro ao criar o Jogo!");
                                        System.out.print(" Aperte Enter para continuar! ");
                                        sc.nextLine();
                                    }
                                } else {
                                    System.out.println(colorirTexto("\n O jogo da existe!", corVermelho));
                                    System.out.print(" Precione Enter para continuar! ");
                                    sc.nextLine();
                                }
                            } else {
                                System.out.println(colorirTexto(ControleJogo.validarJogo(nome, genero, descricao, valor,
                                        desenvolvedora, quantConquistas, descontoElegivel), corVermelho));
                                System.out.print(" Precione Enter para continuar! ");
                                sc.nextLine();
                            }

                        } while (!sair);
                    case 2:
                        LimpaTela();
                        break;
                    case 0:
                        sair = true;
                        return;
                }

            } catch (NumberFormatException e) {
            }
        } while (!sair);
    }

    public static void menuGerenciarFuncionarios(Connection connection, Scanner sc){
        
    }

    public static void menuPerfil(Connection connection, Scanner sc, Usuario usuario){

    }


    // Métodos de personalização do terminal

    /**
     * Método para limpar a tela do terminal
     * 
     * @throws InterruptedException
     * @throws IOException
     */
    public static void LimpaTela() throws InterruptedException, IOException {
        // Isso aqui funciona pra identificar qual SO está sendo usado
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
            new ProcessBuilder("sh", "-c", "clear").inheritIO().start().waitFor();
        }
    }

    public static final String resetCor = "\u001B[0m";
    public static final String corVermelho = "\u001B[31m";
    public static final String corVerde = "\u001B[32m";
    public static final String corAmarelo = "\u001B[33m";
    public static final String corAzul = "\u001B[34m";
    public static final String corRoxo = "\u001B[35m";
    public static final String corCiano = "\u001B[36m";
    public static final String corBranco = "\u001B[37m";

    /**
     * Método que recebe o código de uma cor e uma string e altera a cor dela
     * 
     * @param texto
     * @param cor
     * @return String - Texto colorido
     */
    public static String colorirTexto(String texto, String cor) {
        return cor + texto + resetCor;
    }
}
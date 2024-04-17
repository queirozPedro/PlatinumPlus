package Views;

import java.io.IOException;
import java.sql.Connection;
import java.util.Scanner;

import Utils.PostgreSQLConnection;

// Imports de classes do projeto

import Controllers.*;
import Models.*;

public class ViewMain {

    /*
     * Notas:
     * 
     * -> Na página inicial de sites de lojas de jogos online, os jogos podem ser
     * vizualizados mesmo
     * sem precisar de um login, isso poderia ser implementado aqui.
     * -> O menu de usuário não deveria prender o usuário, ele deveria poder
     * simplesmente navegar
     * entre os jogos. Uma menu expecifico apenas para navegar entre os jogos não
     * seria ruim.
     * -> A classe Connection pode ser global, apenas com sua inicialização na main.
     * Algo também deve
     * ser feito para que o programa possa se reconectar em caso de queda, para que
     * não exista a
     * nescessidade de executar o programa inteiro novamente. Algo também deve ser
     * feito, para que
     * os dados da aplicação não sejam perdidos em caso de queda.
     * -> Deve ser implementada alguma maneira de fazer o usuário não ficar preso ao
     * fruxo do código, algo como um botão de retroceder.
     */

    static Connection connection = PostgreSQLConnection.getInstance().getConnection();

    public static void main(String[] args) throws InterruptedException, IOException {
        boolean sair = false;
        Scanner sc = new Scanner(System.in);

        while (!sair) {
            try {
                switch (Integer.valueOf(MenuPrincipal(sc))) {
                    case 1:
                        MenuNavegacao(sc);
                        break;
                    case 2:
                        MenuBusca(sc);
                        break;
                    case 3:
                        MenuIniciarSessao(sc);
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

    /**
     * Menu principal
     * 
     * @param sc
     * @return void
     * @throws InterruptedException
     * @throws IOException
     */
    public static String MenuPrincipal(Scanner sc) throws InterruptedException, IOException {
        LimpaTela();
        System.out.println(" ===  Menu Principal  ===");
        System.out.println(" 1 -> Navegar por Jogos");
        System.out.println(" 2 -> Buscar Jogos");
        System.out.println(" 3 -> Iniciar Sessão");
        System.out.println(" 0 -> Sair");
        System.out.print(" >> ");
        return sc.nextLine();
    }

    public static String MenuNavegacao(Scanner sc) throws InterruptedException, IOException {
        LimpaTela();
        return null;
    }

    public static String MenuBusca(Scanner sc) throws InterruptedException, IOException {
        LimpaTela();
        return null;
    }

    public static void MenuIniciarSessao(Scanner sc) throws InterruptedException, IOException {
        LimpaTela();
        System.out.println(" ===  Menu Principal  ===");
        System.out.println(" 1 -> Login");
        System.out.println(" 2 -> Registrar-se");
        System.out.println(" 0 -> Sair");
        System.out.print(" >> ");

        try {
            switch (Integer.valueOf(sc.nextLine())) {
                case 1: // Usuário
                    MenuLogin(sc);
                    break;
                case 2: // Cliente
                    MenuCadastro(sc);
                    break;
                default:
                    break;
            }
        } catch (NumberFormatException e) {}
    }

    /**
     * Menu responsável por realizar o login do Usuário
     * 
     * @param sc
     * @throws InterruptedException
     * @throws IOException
     */
    public static void MenuLogin(Scanner sc) throws InterruptedException, IOException {
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

                        if(ControleUsuario.loginUsuario(connection, email ,senha) != null){
                            if(ControleFuncionario.loginFuncionario(connection, email, senha) == null){
                                sc.nextLine();
                                MenuUsuario(sc, ControleUsuario.loginUsuario(connection, email, senha));
                            }
                            else{
                                // O login de Funcionario ta retornando um usuario
                                //MenuFuncionario(sc, ControleFuncionario.loginFuncionario(connection, email, senha));
                            }
                        } else {
                            System.out.println(colorirTexto(" Usuário inexistente.", corVermelho));
                            System.out.println(" Precione Enter para Continuar!");
                            sc.nextLine();
                        }

                        break;
                    case 0:
                        sair = true;
                        break;
                    default:
                        break;
                }
            } catch (NumberFormatException e) {}
        } while (!sair);

    }

    /**
     * Método que exibe um menu de Registro de Usuário
     * 
     * @param sc
     * @throws InterruptedException
     * @throws IOException
     */
    public static void MenuCadastro(Scanner sc) throws InterruptedException, IOException {
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

                            if (ControleUsuario.validarUsuario(nome, cpf, email, senha, reSenha, telefone).trim().isBlank()) {
                                if(ControleUsuario.buscaUsuario(connection, cpf) == null){
                                    System.out.println(" Confirmar operação 1 -> Sim, 2 -> Não");
                                    System.out.print(" >> ");
                                    if(Integer.valueOf(sc.nextLine()) == 1){
                                        if(ControleUsuario.criarConta(connection, nome, cpf, email, reSenha, telefone)){
                                            System.out.println(colorirTexto("Usuário Cadastrado com Sucesso!", corVerde));
                                        } else {
                                            System.out.println(colorirTexto("\n Erro ao criar o usuário.", corVermelho));
                                        }
                                    }
                                    else{
                                        System.out.print(" Cancelando operação.\n Aperte Enter para Continuar!");
                                        sc.nextLine();
                                        break;
                                    }
                                }
                                else{
                                    System.out.println(colorirTexto("\n Um usuário com esse cpf já está cadastrado.", corVermelho));
                                }
                            } else {
                                System.out.println("\n"+ colorirTexto(ControleUsuario.validarUsuario(nome, cpf, email, senha, reSenha, telefone), corVermelho));
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

    public static void MenuFuncionario(Scanner sc, Funcionario funcionario) throws InterruptedException, IOException {
        boolean sair = false;
        do {
            try {
                LimpaTela();
                System.out.println("=== Tela do Funcionário ===");
                System.out.println("1 -> Adicionar Jogo");
                System.out.println("2 -> Excluir Jogo");
                System.out.println("3 -> Listar Jogos");
                System.out.println("4 -> Editar Usuário");
                System.out.println("5 -> Editar Jogo");
                System.out.println("0 -> Sair");
                System.out.print(" >> ");
                switch (Integer.valueOf(sc.nextLine())) {
                    case 1:

                        break;

                    case 2:
                    
                        break;
                    
                    case 3:
                    
                        break;
                    
                    case 4:
                    
                        break;
                    
                    case 5:
                    
                        break;
                    case 0:
                        return;
                }
                
            } catch (NumberFormatException e) {
            }
        } while (!sair);
    }

    public static void MenuUsuario(Scanner sc, Usuario usuario) throws InterruptedException, IOException {
        boolean sair = false;
        do {
            try {
                LimpaTela();
                System.out.println("=== Tela do Usuário ===");
                System.out.println("1 -> Comprar Jogo");
                System.out.println("2 -> Usar Cupom");
                System.out.println("3 -> Listar Jogos");
                System.out.println("4 -> Listar Cupons");
                System.out.println("5 -> Testar Cupom");
                System.out.println("0 -> Sair");
                System.out.print(" >> ");
                switch (Integer.valueOf(sc.nextLine())) {
                    case 1:
                    
                        break;
                    
                    case 2:
                    
                        break;
                    
                    case 3:
                    
                        break;
                    
                    case 4:
                    
                        break;
                    
                    case 5:
                    
                        break;
                    case 0:
                        return;
                }
                
            } catch (NumberFormatException e) {
            }
        } while (!sair);
    }    
    
    // Métodos de personalização do terminal

    /**
     * Método para limpar a tela do terminal
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
    public static final String corRoxo= "\u001B[35m";
    public static final String corCiano = "\u001B[36m";
    public static final String corBranco = "\u001B[37m";

    /**
     * Método que recebe o código de uma cor e uma string e altera a cor dela
     * @param texto
     * @param cor
     * @return String - Texto colorido
     */ 
    public static String colorirTexto(String texto, String cor) {
        return cor + texto + resetCor;
    }

    // implementação
    // String textoColorido = colorirTexto("Este texto é verde!", CorVerde);

}

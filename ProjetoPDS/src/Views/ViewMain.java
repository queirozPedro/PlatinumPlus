package Views;


import java.io.IOException;
import java.sql.Connection;
import java.util.Scanner;

import Utils.PostgreSQLConnection;
import Utils.ValidarDados;

// Imports de classes do projeto

import Controllers.ControleUsuario;
// import Controllers.ControleCupom;
// import Controllers.ControleJogo;
// import Controllers.ControleJogoUsuario;
// import Controllers.ControleFuncionario;
// import Models.Usuario;

public class ViewMain {

    public static void main(String[] args) throws InterruptedException, IOException {
        boolean sair = false;
        Scanner sc = new Scanner(System.in);
        Connection connection = PostgreSQLConnection.getInstance().getConnection();

        while (!sair) {
            try {
                switch (Integer.valueOf(MenuPrincipal(sc))) {
                    case 1:
                        MenuLogin(sc);
                        break;
                    case 2:
                        MenuCadastro(connection, sc);
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
     * @param sc
     * @return void
     * @throws InterruptedException
     * @throws IOException
     */
    public static String MenuPrincipal(Scanner sc) throws InterruptedException, IOException {
        LimpaTela();
        System.out.println(" ===  Menu Principal  ===");
        System.out.println(" 1 -> Login");
        System.out.println(" 2 -> Registrar-se");
        System.out.println(" 0 -> Sair");
        System.out.print(" >> ");
        return sc.nextLine();
    }

    /**
     * Menu responsável por realizar o login do Usuário
     * @param sc
     * @throws InterruptedException
     * @throws IOException
     */
    public static void MenuLogin(Scanner sc) throws InterruptedException, IOException {
        //int out = 3;
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
                        System.out.print("Email: ");
                        //String email = sc.nextLine();
                        System.out.print("Senha: ");
                        //String senha = sc.nextLine();

                        /* 
                         * A lógica dessa parte deve ser implementada 
                         *
                         * Basicamente nesse ponto ele checa se o usuário é um Funcionário 
                         * da Loja ou um Usuário padrão e faz o login.
                        */

                        // if (Usuario.loginUsuario(email, senha) != null) {
                        //     if (Adm.loginAdm(email, senha) != null) {
                        //         menuAdmin(Adm.loginAdm(email, senha), sc);
                        //     } else {
                        //         menuUsuario(Usuario.loginUsuario(email, senha), sc);
                        //     }
                        // } else {
                        //     LimpaTela();
                        //     System.out.println(" Email ou Senha incorretos! ");
                        //     if (--out == 0) {
                        //         System.out.print(" Aguarde alguns minutos e tente novamente! ");
                        //         sc.nextLine();
                        //         return;
                        //     }
                        // }

                        System.out.print("\n Aperte Enter para Continuar! ");
                        sc.nextLine();
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

    public static void MenuCadastro(Connection connection, Scanner sc) throws InterruptedException, IOException {
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
                        String nome = null, cpf = null, email = null, senha = null, senhaConfirma = null,
                                telefone = null;
                        do {
                            LimpaTela();
                            System.out.println(" < Cadastro >");
                            if (!ValidarDados.validarNome(nome)) {
                                System.out.print(" Nome: ");
                                nome = sc.nextLine();
                            } else {
                                System.out.println(" Nome: " + nome);
                                if (!ValidarDados.validarCpf(cpf)) {
                                    System.out.print(" Cpf (11 digitos): ");
                                    cpf = sc.nextLine();
                                } else {
                                    System.out.println(" Cpf: " + cpf);
                                    if (!ValidarDados.validarEmail(email)) {
                                        System.out.print(" Email: ");
                                        email = sc.nextLine();
                                    } else {
                                        System.out.println(" Email: " + email);
                                        if (!ValidarDados.validarSenha(senha)) {
                                            System.out.print(" Senha (minimo de 6 digitos): ");
                                            senha = sc.nextLine();
                                            System.out.print(" Confirme a Senha: ");
                                            senhaConfirma = sc.nextLine();
                                            if (!senha.equals(senhaConfirma)) {
                                                senha = null;
                                            }
                                        } else {
                                            System.out.println(" Senha: " + senha);
                                            if (!ValidarDados.validarTelefone(telefone)) {
                                                System.out.print(" Telefone (11 digitos)): ");
                                                telefone = sc.nextLine();
                                            } else {
                                                System.out.println(" Telefone: " + telefone);
                                                System.out.print(" Cadastrar Conta (1 -> Sim, 2 - > Não): ");
                                                if (Integer.valueOf(sc.nextLine()) == 1) {
                                                    ControleUsuario.criarConta(connection, nome, cpf, email, senha, telefone);
                                                }
                                                System.out.print(" Aperte Enter para Continuar! ");
                                                sc.nextLine();
                                                sair = true;
                                            }
                                        }
                                    }
                                }
                            }
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

}

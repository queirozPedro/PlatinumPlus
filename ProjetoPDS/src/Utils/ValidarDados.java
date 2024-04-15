package Utils;

public class ValidarDados {

    /*
     *
     * Validações para Usuário
     * 
     */

    public static boolean validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return false;
        }
        if (!nome.matches("^[A-Za-zÀ-ÖØ-öø-ÿ\\s0-9]+$")) {
            return false;
        }
        return true;
    }

    //

    public static boolean validarCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            return false;
        }
        if (!cpf.matches("\\d{11}")) {
            return false;
        }
        return true;
    }

    public static boolean validarTelefone(String telefone) {
        if (telefone == null || telefone.isEmpty()) {
            return false;
        }
        if (!telefone.matches("\\d{11}")) {
            return false;
        }
        return true;
    }

    public static boolean validarEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            return false;
        }
        return true;

    }

    public static boolean validarSenha(String senha) {
        if (senha == null || senha.trim().isEmpty() || senha.length() < 6) {
            return false;
        } else {
            return true;
        }
    }

}

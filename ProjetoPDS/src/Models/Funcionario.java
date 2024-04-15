package Models;


public class Funcionario extends Usuario {
    private int idUsuario;

    
    public int getidUsuario() {
        return idUsuario;
    }

    public Funcionario(String nome, String cpf, String senha, String email, String telefone) {
        super(nome, cpf, senha, email, telefone);
    }

}

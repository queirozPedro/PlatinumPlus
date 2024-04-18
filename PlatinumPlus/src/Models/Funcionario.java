package Models;


public class Funcionario extends Usuario {

    public Funcionario(String nome, String cpf, String senha, String email, String telefone) {
        super(nome, cpf, senha, email, telefone);
    }

    public Funcionario(Usuario usuario){
        super(usuario.getNome(), usuario.getCpf(), usuario.getSenha(), usuario.getEmail(), usuario.getTelefone());
    }

}

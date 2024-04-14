package Models;

public class Funcionario extends Usuario {
    private String idUsuario;
    
    public Funcionario(String nome, String cpf, String senha, String email, String telefone, String idUsuario) {
        super(nome, cpf, senha, email, telefone);
        this.idUsuario = idUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        if (idUsuario != null) 
            this.idUsuario = idUsuario;
        else 
            System.err.println("O valor de id do usuário não pode ser null");    
    }
    
}

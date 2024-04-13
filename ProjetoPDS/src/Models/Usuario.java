package Models;

public class Usuario {
    private String nome;
    private String cpf;
    private String senha;
    private String email;
    private String telefone;
    
    public Usuario(String nome, String cpf, String senha, String email, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.email = email;
        this.telefone = telefone;
    }

    public void setCpf(String cpf) {
        if (cpf != null)
            this.cpf = cpf;
        else
            System.out.println("O valor de cpf não pode ser null");
    }

    public void setNome(String nome) {
        if (nome != null)
            this.nome = nome;
        else
            System.out.println("O valor de nome não pode ser null");
    }

    public void setEmail(String email) {
        if (email != null)
            this.email = email;
        else
            System.out.println("O valor de email não pode ser null");
    }

    public void setSenha(String senha) {
        if (senha != null)
            this.senha = senha;
        else
            System.out.println("O valor de senha não pode ser null");
    }

    public void setTelefone(String telefone) {
        if (telefone != null)
            this.telefone = telefone;
        else
            System.out.println("O valor de telefone não pode ser null");
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }
    
}

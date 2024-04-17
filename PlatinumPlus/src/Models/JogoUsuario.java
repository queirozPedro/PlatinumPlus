package Models;

public class JogoUsuario {
    private String cpf;
    private int idJogo;
    private int quantObtidaConquistas;
    private boolean temCupom;
    
    public JogoUsuario(String cpf, int idJogo, int quantObtidaConquistas, boolean temCupom) {
        this.cpf = cpf;
        this.idJogo = idJogo;
        this.quantObtidaConquistas = quantObtidaConquistas;
        this.temCupom = temCupom;
    }

    public int getIdJogo() {
        return idJogo;
    }

    public int getQuantObtidaConquistas() {
        return quantObtidaConquistas;
    }

    public boolean getTemCupom() {
        return temCupom;
    }

    public String getCpf() {
        return cpf;
    }
    
}

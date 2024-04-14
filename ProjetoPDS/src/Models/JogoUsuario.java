package Models;

public class JogoUsuario {
    private int idUsuario;
    private int idJogo;
    private int quantObtidaConquistas;
    private boolean temCupom;
    
    public JogoUsuario(int idUsuario, int idJogo, int quantObtidaConquistas, boolean temCupom) {
        this.idUsuario = idUsuario;
        this.idJogo = idJogo;
        this.quantObtidaConquistas = quantObtidaConquistas;
        this.temCupom = temCupom;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getIdJogo() {
        return idJogo;
    }

    public int getQuantObtidaConquistas() {
        return quantObtidaConquistas;
    }

    public boolean isTemCupom() {
        return temCupom;
    }

    
}

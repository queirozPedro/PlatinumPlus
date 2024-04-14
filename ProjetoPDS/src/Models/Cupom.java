package Models;

public class Cupom {
    private int idCupom;
    private int IdUsuario;
    private int desconto;

    public Cupom(int idCupom, int idUsuario, int desconto) {
        this.idCupom = idCupom;
        this.IdUsuario = idUsuario;
        this.desconto = desconto;
    }

    public int getIdCupom() {
        return idCupom;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public int getDesconto() {
        return desconto;
    }
    
}

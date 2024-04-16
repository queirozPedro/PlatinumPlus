package Models;

public class Cupom {
    private int idCupom;
    private String cpf;
    private int desconto;

    public Cupom(int idCupom, String cpf, int desconto) {
        this.idCupom = idCupom;
        this.cpf = cpf;
        this.desconto = desconto;
    }

    public int getIdCupom() {
        return idCupom;
    }

    public int getDesconto() {
        return desconto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setIdCupom(int idCupom) {
        this.idCupom = idCupom;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setDesconto(int desconto) {
        this.desconto = desconto;
    }
    
    

}

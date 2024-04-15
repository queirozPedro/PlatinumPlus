package Models;

import java.sql.Date;

public class Jogo {
    private int id;
    private String nome;
    private String genero;
    private String descricao;
    private Date dataLancamento;
    private float valor;
    private String desenvolvedora;
    private int quantConquistas;
    private int descontoElegivel;

    public Jogo(int id, String nome, String genero, String descricao, Date dataLancamento, float valor,
            String desenvolvedora, int quantConquistas, int descontoElegivel) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.descricao = descricao;
        this.dataLancamento = dataLancamento;
        this.valor = valor;
        this.desenvolvedora = desenvolvedora;
        this.quantConquistas = quantConquistas;
        this.descontoElegivel = descontoElegivel;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean setNome(String nome) {
        if (nome != null) {
            this.nome = nome;
            return true;
        } else {
            System.err.println("O valor de nome não pode ser null");
            return false;
        }
    }

    public boolean setGenero(String genero) {
        if (genero != null) {
            this.genero = genero;
            return true;
        } else {
            System.err.println("O valor de genero não pode ser null");
            return false;
        }
    }

    public boolean setDescricao(String descricao) {
        if (descricao != null) {
            this.descricao = descricao;
            return true;
        } else {
            System.err.println("O valor de descricao não pode ser null");
            return false;
        }
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public void setDesenvolvedora(String desenvolvedora) {
        if (desenvolvedora != null)
            this.desenvolvedora = desenvolvedora;
        else
        System.err.println("O valor de desenvolvedora não pode ser null");
    }

    public void setQuantConquistas(int quantConquistas) {
        this.quantConquistas = quantConquistas;
    }

    public void setDescontoElegivel(int descontoElegivel) {
        this.descontoElegivel = descontoElegivel;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getGenero() {
        return genero;
    }

    public String getDescricao() {
        return descricao;
    }

    public float getValor() {
        return valor;
    }

    public String getDesenvolvedora() {
        return desenvolvedora;
    }

    public int getQuantConquistas() {
        return quantConquistas;
    }

    public int getDescontoElegivel() {
        return descontoElegivel;
    }
    
}

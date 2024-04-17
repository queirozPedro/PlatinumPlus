package Models;

import java.sql.Date;

public class Jogo {
    private int id;
    private String nome;
    private String genero;
    private String descricao;
    private float valor;
    private String desenvolvedora;
    private int quantConquistas;
    private int descontoElegivel;
    
    public Jogo(int id, String nome, String genero, String descricao, float valor, String desenvolvedora,
            int quantConquistas, int descontoElegivel) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.descricao = descricao;
        this.valor = valor;
        this.desenvolvedora = desenvolvedora;
        this.quantConquistas = quantConquistas;
        this.descontoElegivel = descontoElegivel;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getDesenvolvedora() {
        return desenvolvedora;
    }

    public void setDesenvolvedora(String desenvolvedora) {
        this.desenvolvedora = desenvolvedora;
    }

    public int getQuantConquistas() {
        return quantConquistas;
    }

    public void setQuantConquistas(int quantConquistas) {
        this.quantConquistas = quantConquistas;
    }

    public int getDescontoElegivel() {
        return descontoElegivel;
    }

    public void setDescontoElegivel(int descontoElegivel) {
        this.descontoElegivel = descontoElegivel;
    }



}

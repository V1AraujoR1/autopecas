package model;

public class Produto {
    private int codigoProduto;
    private String nome;
    private String referencia;
    private String marca;
    private String pesquisa;
    private String descricao;
    private int multiplo;
    private float preco;
    private int quantidade;
    
    public Produto() {
        codigoProduto = 0;
        nome = new String();
        referencia = new String();
        marca = new String();
        pesquisa = new String();
        descricao = new String();
        multiplo = 0;
        preco = 0;
        quantidade = 0;
    }

    public Produto(int codigoProduto, String nome, String referencia, String marca, String pesquisa, String descricao, int multiplo, float preco, int quantidade) {
        this.codigoProduto = codigoProduto;
        this.nome = nome;
        this.referencia = referencia;
        this.marca = marca;
        this.pesquisa = pesquisa;
        this.descricao = descricao;
        this.multiplo = multiplo;
        this.preco = preco;
        this.quantidade = quantidade;
    }
    
    public int getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getMultiplo() {
        return multiplo;
    }

    public void setMultiplo(int multiplo) {
        this.multiplo = multiplo;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
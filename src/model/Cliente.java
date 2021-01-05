package model;

import java.util.ArrayList;
import java.util.List;

//Por quê essa classe não é abstrata?
//Pois, para algumas pesquisas, eu tenho que pegar dados do cliente, como nome e telefones, apenas. Nelas, não devo distinguir físico de jurídico. Portanto, essa classe deve ser instanciável.
public class Cliente {
    private int codigoCliente;
    private String nome;
    private String logradouro;
    private int numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String CEP;
    private List<TelefoneCliente> telefones;
    
    public Cliente() {
        codigoCliente = 0;
        nome = new String();
        logradouro = new String();
        numero = 0;
        bairro = new String();
        cidade = new String();
        estado = new String();
        CEP = new String();
        telefones = new ArrayList<>();
    }
    
    public Cliente(int codigoCliente, String nome, String logradouro, int numero, String bairro, String cidade, String estado, String CEP, List<TelefoneCliente> telefones) {
        this.codigoCliente = codigoCliente;
        this.nome = nome;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.CEP = CEP;
        this.telefones = telefones;
    }
    
    public void adicionarTelefone(TelefoneCliente telefone){
        telefones.add(telefone);
    }
    
    public void removerTelefones() {
        telefones.clear();
    }

    public List<TelefoneCliente> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<TelefoneCliente> telefones) {
        this.telefones = telefones;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }
}

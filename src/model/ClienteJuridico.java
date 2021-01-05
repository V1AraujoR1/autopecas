package model;

import java.util.List;

public class ClienteJuridico extends Cliente {
    private String razaoSocial;
    private String CNPJ;
    private String inscricaoEstadual;
    
    public ClienteJuridico() {
        razaoSocial = new String();
        CNPJ = new String();
        inscricaoEstadual = new String();
    }
    
    public ClienteJuridico(String razaoSocial, String CNPJ, String inscricaoEstadual, int codigoCliente, String nome, String logradouro, int numero, String bairro, String cidade, String estado, String CEP, List<TelefoneCliente> telefones) {
        super(codigoCliente, nome, logradouro, numero, bairro, cidade, estado, CEP, telefones);
        this.razaoSocial = razaoSocial;
        this.CNPJ = CNPJ;
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }
}
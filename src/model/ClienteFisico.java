package model;

import java.util.List;

public class ClienteFisico extends Cliente {
    private String CPF;
    private String RG;
    
    public ClienteFisico() {
        CPF = new String();
        RG = new String();
    }
    
    public ClienteFisico(String CPF, String RG, int codigoCliente, String nome, String logradouro, int numero, String bairro, String cidade, String estado, String CEP, List<TelefoneCliente> telefones) {
        super(codigoCliente, nome, logradouro, numero, bairro, cidade, estado, CEP, telefones);
        this.CPF = CPF;
        this.RG = RG;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }
}
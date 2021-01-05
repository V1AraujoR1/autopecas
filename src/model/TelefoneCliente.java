package model;

public class TelefoneCliente {
    private String telefone;
    private String tipo;
    
    public TelefoneCliente() {
        telefone = new String();
        tipo = new String();
    }
    
    public TelefoneCliente(String telefone, String tipo) {
        this.telefone = telefone;
        this.tipo = tipo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
package model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int codigoPedido;
    private Cliente cliente;
    private String formaPagamento;
    private float valorTotal;
    private Timestamp dataHora;
    private List<ItemPedido> itensPedido;

    public Pedido() {
        codigoPedido = 0;
        cliente = new Cliente();
        formaPagamento = new String();
        valorTotal = 0;
        dataHora = new Timestamp(new java.util.Date().getTime());
        itensPedido = new ArrayList<>();
    }

    public Pedido(int codigoPedido, Cliente cliente, String formaPagamento, float valorTotal, Timestamp dataHora, List<ItemPedido> itensPedido) {
        this.codigoPedido = codigoPedido;
        this.cliente = cliente;
        this.formaPagamento = formaPagamento;
        this.valorTotal = valorTotal;
        this.dataHora = dataHora;
        this.itensPedido = itensPedido;
    }
    
    public void adicionarItemPedido(ItemPedido itemPedido){
        itensPedido.add(itemPedido);
    }
    
    public void removerItensPedido() {
        itensPedido.clear();
    }
    
    public List<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }

    public int getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(int codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(Timestamp dataHora) {
        this.dataHora = dataHora;
    }
}
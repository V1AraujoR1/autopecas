package model;


//Eu talvez deveria inserir um objeto de Cliente aqui.
public class ItemPedido {
    private Pedido pedido;
    private Cliente cliente;
    private Produto produto;
    private int quantidadePedida;
    private float valorUnitario;

    public ItemPedido() {
        pedido = new Pedido();
        cliente = new Cliente();
        produto = new Produto();
        quantidadePedida = 0;
        valorUnitario = 0;
    }

    public ItemPedido(Pedido pedido, Cliente cliente, Produto produto, int quantidadePedida, float valorUnitario) {
        this.pedido = pedido;
        this.cliente = cliente;
        this.produto = produto;
        this.quantidadePedida = quantidadePedida;
        this.valorUnitario = valorUnitario;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidadePedida() {
        return quantidadePedida;
    }

    public void setQuantidadePedida(int quantidadePedida) {
        this.quantidadePedida = quantidadePedida;
    }

    public float getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(float valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
}
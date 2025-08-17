package ejercicios_primera_clase.ejercicio_tienda;

public class Producto {
    // definimos una lista de atributos que tendra cada producto
    private String name;
    private String categoria; 
    private double price;
    private int stock;
    private int unitsSold;

    // creamos un objeto producto
    public Producto(String name, String categoria, double price, int stock, int unitsSold) {
        this.name = name;
        this.categoria = categoria;
        this.price = price;
        this.stock = stock;
        this.unitsSold = unitsSold;
    }

    // getters y setters
    public String getName() {
        return name;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getUnitsSold() {
        return unitsSold;
    }

    public void setUnitsSold(int unitsSold) {
        this.unitsSold = unitsSold;
    }

    // metodo que calcula las ganancias de este producto
    public double getRevenue() {
        return price * unitsSold;
    }

    // disminuir el stock en cierta cantidad 
    public void reduceStock(int amount) {
        if (amount > 0 && amount <= stock) {
            stock -= amount;
            unitsSold += amount; // sumamos al total vendido
        }
    }

    @Override
    public String toString() {
        return "Producto{" +
                "name='" + name + '\'' +
                ", categoria='" + categoria + '\'' +
                ", precio=" + price +
                ", stock=" + stock +
                ", unidadesVendidas=" + unitsSold +
                '}';
    }
}


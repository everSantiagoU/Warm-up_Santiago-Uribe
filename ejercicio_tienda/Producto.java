package ejercicios_primera_clase.ejercicio_tienda;

public class Producto {
    private String name;
    private String categoria; // el nombre de la categoría (ej: "Electronics", "Home")
    private double price;
    private int stock;
    private int unitsSold;

    public Producto(String name, String categoria, double price, int stock, int unitsSold) {
        this.name = name;
        this.categoria = categoria;
        this.price = price;
        this.stock = stock;
        this.unitsSold = unitsSold;
    }

    // Getters y setters
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

    // Método que calcula el revenue de este producto
    public double getRevenue() {
        return price * unitsSold;
    }

    // Reducir el stock en cierta cantidad (simula una venta)
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
                ", price=" + price +
                ", stock=" + stock +
                ", unitsSold=" + unitsSold +
                '}';
    }
}


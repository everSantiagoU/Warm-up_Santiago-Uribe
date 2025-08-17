package ejercicios_primera_clase.ejercicio_tienda;
import java.util.List;

public abstract class Categoria {
    protected String name;

    public Categoria(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double calculateRevenue(List<Producto> productos) {
    double total = 0;
    for (Producto p : productos) {
        if (p.getCategoria().equalsIgnoreCase(name)) {
            total += p.getRevenue();
        }
    }
    return total;
}

    public abstract void applyDiscountOrIncrease(Producto producto);

    @Override
    public String toString() {
        return "Categoría: " + name;
    }
}

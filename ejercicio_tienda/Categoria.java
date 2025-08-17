package ejercicios_primera_clase.ejercicio_tienda;
import java.util.List;

// creamos una clase abstracta categoria ya que hay dos tipos
public abstract class Categoria {
    protected String name;

    public Categoria(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
// creamos este metodo para calcular las ganacias totales
    public double calculateRevenue(List<Producto> productos) {
    double total = 0;
    for (Producto p : productos) {
        if (p.getCategoria().equalsIgnoreCase(name)) {
            total += p.getRevenue();
        }
    }
    return total;
}
//finalmente creamos un ultimo metodo para aplicar descuento o subir el precio
    public abstract void applyDiscountOrIncrease(Producto producto);

    @Override
    public String toString() {
        return "Categoría: " + name;
    }
}

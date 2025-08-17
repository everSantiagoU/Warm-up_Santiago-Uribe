package ejercicios_primera_clase.ejercicio_tienda;

public class Home extends Categoria {

    public Home() {
        super("Home");
    }

    @Override
    public void applyDiscountOrIncrease(Producto producto) {
        // Regla: aumentar el precio un 10% si el stock es menor a 5
        if (producto.getStock() < 5) {
            double nuevoPrecio = producto.getPrice() * 1.10;
            producto.setPrice(nuevoPrecio);
        }
    }
}

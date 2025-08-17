package ejercicios_primera_clase.ejercicio_tienda;
import java.util.*;

public class Tienda {
    private List<Producto> productos;

    public Tienda() {
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    // metodo para listar productos Electronics con stock > 0 y  ordenados por nombre
    public List<Producto> listarElectronicsDisponibles() {
        List<Producto> result = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getCategoria().equalsIgnoreCase("Electronics") && p.getStock() > 0) {
                result.add(p);
            }
        }
        result.sort(Comparator.comparing(Producto::getName));
        return result;
    }

    //metodo pzra aumentar precio de productos de la categoria home en 10% si stock < 5
    public void aumentarPrecioHome() {
        for (Producto p : productos) {
            if (p.getCategoria().equalsIgnoreCase("Home") && p.getStock() < 5) {
                double nuevoPrecio = p.getPrice() * 1.10;
                p.setPrice(nuevoPrecio);
            }
        }
    }

    // metodo para calcular las ganancias por categoria
    public Map<String, Double> calcularRevenuePorCategoria() {
        Map<String, Double> revenuePorCategoria = new HashMap<>();
        for (Producto p : productos) {
            double revenue = p.getRevenue();
            revenuePorCategoria.put(
                p.getCategoria(),
                revenuePorCategoria.getOrDefault(p.getCategoria(), 0.0) + revenue
            );
        }
        return revenuePorCategoria;
    }

    //metodo para obtener la categoría con mayor gganancias
    public String getCategoriaConMayorRevenue() {
        Map<String, Double> revenues = calcularRevenuePorCategoria();
        String mejorCategoria = null;
        double maxRevenue = Double.MIN_VALUE;

        for (Map.Entry<String, Double> entry : revenues.entrySet()) {
            if (entry.getValue() > maxRevenue) {
                maxRevenue = entry.getValue();
                mejorCategoria = entry.getKey();
            }
        }
        return mejorCategoria;
    }

    //metodo para retornar productos ordenados por precio y stock
    public List<Producto> getProductosOrdenados() {
        List<Producto> copia = new ArrayList<>(productos);
        copia.sort(Comparator.comparing(Producto::getPrice).reversed()
                .thenComparing(Producto::getStock));
        return copia;
    }

 //main
    public static void main(String[] args) {
        Tienda tienda = new Tienda();

        // agregamos algunos productos de prueba
        tienda.agregarProducto(new Producto("Laptop", "Electronics", 1200, 10, 5));
        tienda.agregarProducto(new Producto("Smartphone", "Electronics", 800, 0, 20));
        tienda.agregarProducto(new Producto("TV", "Electronics", 1500, 3, 7));
        tienda.agregarProducto(new Producto("Silla", "Home", 100, 4, 10));
        tienda.agregarProducto(new Producto("Mesa", "Home", 200, 6, 2));
        tienda.agregarProducto(new Producto("Cafetera", "Home", 50, 2, 15));

        System.out.println("1) Electronics disponibles:");
        tienda.listarElectronicsDisponibles().forEach(System.out::println);

        System.out.println("\n2) Aumentar precio Home (stock < 5):");
        tienda.aumentarPrecioHome();
        tienda.productos.forEach(System.out::println);

        System.out.println("\n3) ganancias por categoría:");
        System.out.println(tienda.calcularRevenuePorCategoria());

        System.out.println("\n4) Categoría con mayor ganancias:");
        System.out.println(tienda.getCategoriaConMayorRevenue());

        System.out.println("\n5) Productos ordenados por precio y stock:");
        tienda.getProductosOrdenados().forEach(System.out::println);
    }
}

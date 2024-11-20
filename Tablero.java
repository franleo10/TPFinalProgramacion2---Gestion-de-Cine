import java.util.ArrayList;
import java.util.List;

public class Tablero {

    private List<Proyecto> proyectos;

    public Tablero() {
        this.proyectos = new ArrayList<>();
    }

    // Método para agregar un proyecto al tablero
    public void agregarProyecto(Proyecto proyecto) {
        proyectos.add(proyecto);
    }

    // Método para obtener un proyecto por su ID
    public Proyecto obtenerProyectoPorId(int idProyecto) {
        for (Proyecto proyecto : proyectos) {
            if (proyecto.getId() == idProyecto) {
                return proyecto;
            }
        }
        return null;
    }

    // Método para obtener todos los proyectos
    public List<Proyecto> getProyectos() {
        return proyectos;
    }
}
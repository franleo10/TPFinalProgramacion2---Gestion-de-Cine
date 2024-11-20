import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Proyecto {

    private static int contador = 1;
    private int id;
    private String nombre;
    private Set<Tarea> listaDeTareas;

    public Proyecto(String nombre) {
        this.id = contador++;
        this.nombre = nombre;
        listaDeTareas = new HashSet<>();
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Tarea> getListaDeTareas() {
        return this.listaDeTareas;
    }

    public void setListaDeTareas(Set<Tarea> listaDeTareas) {
        this.listaDeTareas = listaDeTareas;
    }
    

    public Set<Tarea> getTareasPorEstado() {
        return listaDeTareas;
    }

     // Método para verificar si existe una tarea por su ID
     public Tarea verificarTareaPorId(int idTarea) {
        Tarea tareaNueva;
        for (Tarea tarea : listaDeTareas) {
            if (tarea.getId() == idTarea) {
                tareaNueva = tarea;
                return tareaNueva; // Si la tarea con el id existe
            }
        }
        return null; // Si no se encuentra la tarea
    }

    // Método para agregar una tarea al proyecto
    public void agregarTarea(int idTarea) {
        try {
            // Primero verificamos si la tarea ya existe
            Tarea tareaNueva = verificarTareaPorId(idTarea);

            // Si no existe, la agregamos al proyecto
            listaDeTareas.add(tareaNueva);
            System.out.println("Tarea agregada con éxito al proyecto.");
        } catch (TareaInexistente e) {
            System.out.println(e.getMessage());
        }
    }

    public void eliminarTarea(int idTarea) { ///Me elimina la tarea ya verificada con el metodo...
        try {
            // Primero verificamos si la tarea existe
            Tarea tareaAeliminar = verificarTareaPorId(idTarea);

            listaDeTareas.remove(tareaAeliminar);

            System.out.println("La tarea fue eliminada con exito");
        } catch (TareaInexistente e) {
            System.out.println(e.getMessage());
        }
    }


    ///Metodo para buscar en una columna, una tarea especifica que me va a servir o para eliminar un usuario de una tarea o la tarea misma.














}

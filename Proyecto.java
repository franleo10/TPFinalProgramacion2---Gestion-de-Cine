import java.util.HashSet;
import java.util.Set;

public class Proyecto {

    private static int contador = 1;
    private int id;
    private String nombre;
    public boolean activo;
    private Set<Tarea> listaDeTareas;

    public Proyecto(String nombre) {
        this.id = contador++;
        this.nombre = nombre;
        this.activo = true;
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

    public Tarea verificarTareaPorId(int idTarea) throws TareaInexistente {
        for (Tarea tarea : listaDeTareas) {
            if (tarea.getId() == idTarea) {
                return tarea; // Si la tarea con el id existe
            }
        }
        throw new TareaInexistente("Error: No se encontró una tarea con ID " + idTarea);
    }

    public void agregarTarea(int idTarea) {
        try {
            // Primero verificamos si la tarea ya existe
            Tarea tareaNueva =  GestorTareas.getInstance().buscarTareaPorId(idTarea);

            // Verificamos si la tarea ya está en el proyecto
            boolean tareaYaExiste = false;
            for (Tarea tarea : listaDeTareas) {
                if (tarea.getId() == tareaNueva.getId()) {
                    tareaYaExiste = true;
                    break;
                }
            }

            if (tareaYaExiste) {
                System.out.println("Error: La tarea ya está asignada a este proyecto.");
            } else {
                listaDeTareas.add(tareaNueva);
                System.out.println("Tarea agregada con éxito al proyecto.");
            }
        } catch (TareaInexistente e) {
            System.out.println(e.getMessage());
        }
    }

    public void agregarTareaNuevaAlProyecto(Tarea tarea){

        if(listaDeTareas.contains(tarea)){
            System.out.println("La tarea ya existe en el proyecto");
        }
        else{
            listaDeTareas.add(tarea);
            System.out.println("Tarea añadida con exito.");
        }

    }

    // Método para eliminar una tarea del proyecto
    public void eliminarTarea(int idTarea) {
        try {
            Tarea tareaAeliminar = verificarTareaPorId(idTarea);

            if (listaDeTareas.remove(tareaAeliminar)) {
                System.out.println("Tarea eliminada con éxito.");
            } else {
                System.out.println("Error: La tarea no estaba asignada a este proyecto.");
            }
        } catch (TareaInexistente e) {
            System.out.println(e.getMessage());
        }
    }

    public String mostrarTareas(){
        StringBuilder sb = new StringBuilder();
        for(Tarea tarea: listaDeTareas){
            sb.append(tarea).append("\n");
        }

        return sb.toString();
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", listaDeTareas='" + listaDeTareas + "'" +
            "}";
    }


}

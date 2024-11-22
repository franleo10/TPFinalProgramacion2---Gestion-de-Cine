import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tablero {

    private static Tablero instancia;

    private Scanner scanner = new Scanner(System.in);

    private GestorProyectos gestorProyectos;

    public Tablero() {
        gestorProyectos = GestorProyectos.getInstance();
    }

    public static Tablero getInstance() {
        if (instancia == null) {
            instancia = new Tablero();
        }
        return instancia;
    }

    // Método para agregar un proyecto al tablero
    public void agregarProyecto(Proyecto proyecto) {
        gestorProyectos.agregarProyecto(proyecto);
    }

    /*
     * // Método para obtener un proyecto por su ID
     * public Proyecto obtenerProyectoPorId(int idProyecto) {
     * for (Proyecto proyecto : proyectos) {
     * if (proyecto.getId() == idProyecto) {
     * return proyecto;
     * }
     * }
     * return null;
     * }
     */

    /*
     * public String mostrarProyectosActivos(){
     * StringBuilder sb = new StringBuilder();
     *
     * for(Proyecto proyecto: proyectos){
     * if(proyecto.activo){
     * sb.append(proyecto).append("\n");
     * }
     * }
     *
     * return sb.toString();
     * }
     *
     */

    /*
     * public String mostrarProyectosInactivos(){
     * StringBuilder sb = new StringBuilder();
     *
     * for(Proyecto proyecto: proyectos){
     * if(!proyecto.activo){
     * sb.append(proyecto).append("\n");
     * }
     * }
     *
     * return sb.toString();
     * }
     */

    public String agregarTareaAProyecto(int idProyecto, Tarea tarea) {

        gestorProyectos.agregarTareaAProyecto(idProyecto);
        // Si no se encontró el proyecto
        return "No se encontró el proyecto con ID: " + idProyecto;
    }

    /*
     * public boolean verificarExistenciaDeProyecto(int idProyecto){
     * for(Proyecto proyecto: proyectos){
     * if(proyecto.getId() == idProyecto){
     * return true;
     * }
     * }
     * return false;
     * }
     */

    public Tarea agregarTarea() {
        System.out.println("Ingrese el título de la tarea:");
        String titulo = scanner.nextLine();
        System.out.println("Ingrese la descripción de la tarea:");
        String descripcion = scanner.nextLine();

        // Manejo de prioridad
        Prioridad prioridad = null;
        while (prioridad == null) {
            System.out.println("Ingrese la prioridad (1.ALTA, 2.MEDIA, 3.BAJA):");
            if (scanner.hasNextInt()) { // Verificar si la entrada es un número
                int resul = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer de entrada

                switch (resul) {
                    case 1:
                        prioridad = Prioridad.Alta;
                        break;
                    case 2:
                        prioridad = Prioridad.Media;
                        break;
                    case 3:
                        prioridad = Prioridad.Baja;
                        break;
                    default:
                        System.out.println("Opción inválida. Intente nuevamente.");
                        break;
                }
            } else {
                // Si no es un número, mostrar mensaje y limpiar el buffer
                System.out.println("Entrada inválida. Por favor ingrese un número.");
                scanner.nextLine(); // Limpiar buffer de entrada
            }
        }

        // Manejo de estado
        Estado estado = null;
        while (estado == null) {
            System.out.println("Ingrese el estado (1.PENDIENTE, 2.EN_PROGRESO, 3.FINALIZADO):");
            if (scanner.hasNextInt()) { // Verificar si la entrada es un número
                int resul = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer de entrada

                switch (resul) {
                    case 1:
                        estado = Estado.Pendiente;
                        break;
                    case 2:
                        estado = Estado.Proceso;
                        break;
                    case 3:
                        estado = Estado.Finalizado;
                        break;
                    default:
                        System.out.println("Opción inválida. Intente nuevamente.");
                        break;
                }
            } else {
                // Si no es un número, mostrar mensaje y limpiar el buffer
                System.out.println("Entrada inválida. Por favor ingrese un número.");
                scanner.nextLine(); // Limpiar buffer de entrada
            }
        }

        // Crear la tarea
        Tarea tarea = new Tarea(titulo, descripcion, prioridad, estado);
        return tarea;
    }

    /*
     *
     * public String mostrarTareasDelProyecto(int idProyecto){
     * for(Proyecto proyecto: proyectos){
     * if(proyecto.getId() == idProyecto){
     * return proyecto.mostrarTareas();
     * }
     * }
     * return "El ID del proyecto que indico no existe";
     * }
     */
}
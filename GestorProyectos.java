import java.util.*;

public class GestorProyectos {

    // Instancia única del gestor
    private static GestorProyectos instancia;
    private Scanner scanner = new Scanner(System.in);

    // Mapa que almacena los proyectos con el ID como clave
    private Map<Integer, Proyecto> proyectos;

    // Constructor privado para evitar instanciación directa
    private GestorProyectos() {
        this.proyectos = new HashMap<>();
    }

    // Método estático para obtener la instancia única
    public static GestorProyectos getInstance() {
        if (instancia == null) {
            instancia = new GestorProyectos();
        }
        return instancia;
    }

    // Eliminar un proyecto del sistema por su ID
    public boolean eliminarProyecto(int id) {
        if (!proyectos.containsKey(id)) {
            System.out.println("El proyecto con ID " + id + " no está registrado en el sistema.");
            return false;
        }

        // Eliminar proyecto del mapa
        proyectos.remove(id);
        System.out.println("Proyecto con ID " + id + " eliminado del sistema.");
        return true;
    }



    // Listar todos los proyectos en el sistema
    public void listarProyectos() {
        if (proyectos.isEmpty()) {
            System.out.println("No hay proyectos en el sistema.");
            return;
        }

        System.out.println("Proyectos registrados en el sistema:");
        for (Proyecto proyecto : proyectos.values()) {
            System.out.println("\n" + "-Titulo: " + proyecto.getNombre());
            System.out.println("-IdProyecto: " + proyecto.getId() + "\n");
        }
    }

    // Método para agregar tarea a un proyecto
    public boolean agregarTareaAProyecto(int idProyecto, int idTarea) {
        Proyecto proyecto = buscarProyectoPorId(idProyecto);
        if (proyecto == null) {
            return false;
        }

        // Agregar tarea al proyecto
        proyecto.agregarTarea(idTarea);
        return true;
    }

    // Método para eliminar tarea de un proyecto
    public boolean eliminarTareaDeProyecto(int idProyecto, int idTarea) {
        Proyecto proyecto = buscarProyectoPorId(idProyecto);
        if (proyecto == null) {
            return false;
        }

        // Eliminar tarea del proyecto
        proyecto.eliminarTarea(idTarea);
        return true;
    }

    // Modificar información del proyecto
    public boolean modificarProyecto(int id, String nuevoNombre) {
        Proyecto proyecto = buscarProyectoPorId(id);
        if (proyecto == null) {
            System.out.println("Proyecto con ID " + id + " no encontrado.");
            return false;
        }

        proyecto.setNombre(nuevoNombre);
        System.out.println("Proyecto modificado exitosamente.");
        return true;
    }
    public void AgregarProyectoNuevo(Proyecto proyecto){

        proyectos.put(proyecto.getId(), proyecto);
    }




    // Método para agregar tarea a un proyecto
    public boolean agregarTareaAProyecto(int idProyecto) {

        Proyecto proyecto = buscarProyectoPorId(idProyecto);
        if (proyecto == null) {
            return false;
        }

        proyectos.get(idProyecto).agregarTareaNuevaAlProyecto(agregarTarea());

        return true;
    }

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


    // Buscar un proyecto por ID
    public Proyecto buscarProyectoPorId(int id) {
        if (proyectos.containsKey(id)) {
            return proyectos.get(id);
        }
        System.out.println("Proyecto con ID " + id + " no encontrado.");
        return null;
    }
    public String mostrarTareasDelProyecto(int idProyecto) {

        StringBuilder sb = new StringBuilder();

        sb.append(proyectos.get(idProyecto).listarTareas());

        return sb.toString();
    }

    public void agregarProyecto(Proyecto proyecto) {
        if (proyectos.containsKey(proyecto.getId())) {
            System.out.println("El proyecto con ID " + proyecto.getId() + " ya está registrado.");
            return;
        }

        proyectos.put(proyecto.getId(), proyecto);
        System.out.println("Proyecto con ID " + proyecto.getId() + " agregado exitosamente.");
    }

    public void listarTareasPorEstado(int idProyecto){
        int opcListaTareas = 0;
        boolean i = true; 
                        while(i){
                        try{
                            if(buscarProyectoPorId(idProyecto) != null){
                                System.out.println("Tipos de tareas para listar:");
                                menuListadoDeTareas();
                                opcListaTareas = scanner.nextInt();
                                    switch(opcListaTareas){

                                    case 1:{

                                        System.out.println("Tareas en pendientes: ");
                                        System.out.println("\n" +"---------------------------------------------------");
                                        System.out.println(buscarProyectoPorId(idProyecto).listarTareasPendientes());
                                        System.out.println("\n" +"---------------------------------------------------");
                                        break;
                                    }
                                    case 2:{
                                        System.out.println("Tareas en proceso: ");
                                        System.out.println("\n" +"---------------------------------------------------");
                                        System.out.println(buscarProyectoPorId(idProyecto).listarTareasEnProceso());
                                        System.out.println("\n" +"---------------------------------------------------");
            
                                        break;
                                    }
                                    case 3:{
                                        System.out.println("Tareas en finalizadas: ");
                                        System.out.println("\n" +"---------------------------------------------------");
                                        System.out.println(buscarProyectoPorId(idProyecto).listarTareasFinalizadas());
                                        System.out.println("\n" +"---------------------------------------------------");
            
                                        break;
                                    }
                                    case 4:{
                                        System.out.println("Todas las tareas: ");
                                        System.out.println("\n" +"---------------------------------------------------");
                                        System.out.println(buscarProyectoPorId(idProyecto).listarTareas());
                                        System.out.println("\n" +"---------------------------------------------------");
                                        break;
                                    }
                                    default:{
                                        System.out.println("La opcion ingresada no es valida.");
                                    }
        
                                    }
                                
                                i = false;
                            }
                        }
                        catch(NumberFormatException e){
                            System.out.println("La opcion ingresada no es valida.");
                        } 
                    }
    }


    public void listarTareasProyecto(int idProyecto) {
        boolean continuar = true;
    
        while (continuar) {
            try {
                if (buscarProyectoPorId(idProyecto) != null) {
                    System.out.println("\nSeleccione una opción para listar las tareas:");
                    System.out.println("1. Listar por estado");
                    System.out.println("2. Listar por prioridad");
                    System.out.println("3. Salir");
                    System.out.print("Opción: ");
                    int opcionPrincipal = scanner.nextInt();
    
                    switch (opcionPrincipal) {
                        case 1: {
                            listarTareasPorEstado(idProyecto);
                            break;
                        }
                        case 2: {
                            listarTareasPorPrioridad(idProyecto);
                            break;
                        }
                        case 3: {
                            continuar = false;
                            System.out.println("Saliendo del listado de tareas...");
                            break;
                        }
                        default: {
                            System.out.println("Opción inválida. Intente nuevamente.");
                        }
                    }
                } else {
                    System.out.println("No se encontró un proyecto con el ID: " + idProyecto);
                    continuar = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor ingrese un número.");
                scanner.nextLine(); // Limpiar el buffer del scanner
            }
        }
    }

    // Método para listar tareas por prioridad
public void listarTareasPorPrioridad(int idProyecto) {
    boolean continuar = true;

    while (continuar) {
        try {
            System.out.println("\nTipos de tareas para listar por prioridad:");
            menuListadoPorPrioridad();
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1: {
                    System.out.println("Tareas con prioridad ALTA:");
                    System.out.println("\n---------------------------------------------------");
                    System.out.println(buscarProyectoPorId(idProyecto).listarTareasPrioridadAlta());
                    System.out.println("\n---------------------------------------------------");
                    break;
                }
                case 2: {
                    System.out.println("Tareas con prioridad MEDIA:");
                    System.out.println("\n---------------------------------------------------");
                    System.out.println(buscarProyectoPorId(idProyecto).listarTareasPrioridadMedia());
                    System.out.println("\n---------------------------------------------------");
                    break;
                }
                case 3: {
                    System.out.println("Tareas con prioridad BAJA:");
                    System.out.println("\n---------------------------------------------------");
                    System.out.println(buscarProyectoPorId(idProyecto).listarTareasPrioridadBaja());
                    System.out.println("\n---------------------------------------------------");
                    break;
                }
                case 4: {
                    System.out.println("Todas las tareas:");
                    System.out.println("\n---------------------------------------------------");
                    System.out.println(buscarProyectoPorId(idProyecto).listarTareas());
                    System.out.println("\n---------------------------------------------------");
                    break;
                }
                default: {
                    System.out.println("Opción inválida. Intente nuevamente.");
                }
            }

            continuar = false;
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor ingrese un número.");
            scanner.nextLine(); // Limpiar el buffer del scanner
        }
    }
}

// Submenú para listar por prioridad
public void menuListadoPorPrioridad() {
    System.out.println("1. Prioridad ALTA");
    System.out.println("2. Prioridad MEDIA");
    System.out.println("3. Prioridad BAJA");
    System.out.println("4. Todas las prioridades");
    System.out.print("Seleccione una opción: ");
}



    public void menuListadoDeTareas(){
        System.out.println("1. Por hacer" + "\n" + "2. En progreso" + "\n" + "3. Completadas" + "\n" + "4. Todas");
        System.out.println("Seleccione la opcion deseada: ");
    }

}

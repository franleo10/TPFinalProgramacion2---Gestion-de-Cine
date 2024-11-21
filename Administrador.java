import java.nio.channels.Pipe.SourceChannel;
import java.security.InvalidParameterException;
import java.util.*;

public class Administrador extends Persona {

    Tablero tablero;
    private Scanner scanner = new Scanner(System.in);

    public Administrador(String nombre, String rol) {
        super(nombre);
    }

    public void CompletarTarea(Tarea tarea) {
        tarea.setEstado(Estado.Finalizado);
        System.out.println("Tarea finalizada con exito...");
    }

    public void EliminarUsuarioDeTarea(Usuario usuario, Tarea tarea) { ///PARA VERIFICAR SI EXISTE LA TAREA TENGO QUE VERIFICARLO EN LA COLUMNA PRIMERO

        try {
            tarea.VerificarUsuario(usuario);
            tarea.EliminarUsuarioTarea(usuario);
            System.out.println("Eliminado con exito el usuario de la tarea...");
        } catch (UsuarioNoEncontrado e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public void asignarTareaAUsuario(Tarea tarea, Usuario usuario) {
        try {
            tarea.VerificarUsuario(usuario);
            tarea.agregarUsuario(usuario);
            System.out.println("Tarea asignada correctamente a " + usuario.getNombre());
        } catch (UsuarioNoEncontrado e) {
            throw new RuntimeException(e);
        }

    }

    public void agregarProyecto(){

        Tablero tablero = Tablero.getInstance();
        GestorProyectos gestorProyectos = GestorProyectos.getInstance();

        System.out.println("Ingrese el nombre del tablero.");
        String nombre = scanner.nextLine();

        while(nombre == null){
         
            System.out.println("Debe indicar un nombre para el proyecto.");
            nombre = scanner.nextLine();

            if(nombre != null){
                Proyecto proyectoNuevo = new Proyecto(nombre);
                tablero.agregarProyecto (proyectoNuevo);
            }

        }


    }




/*
    public void eliminarUsuarioDeColumna(List<Columna> columnas, int indiceColumna, int idTarea, int idUsuario) {
        // Obtener la columna
        Columna columna = columnas.get(indiceColumna);

        // Buscar la tarea en la columna por su ID (en el estado correspondiente)
        Tarea tarea = null;
        for (Set<Tarea> tareasSet : columna.getTareasPorEstado().values()) {
            for (Tarea t : tareasSet) {
                if (t.getId() == idTarea) {
                    tarea = t;
                    break;
                }
            }
            if (tarea != null) {
                break;
            }
        }

        if (tarea != null) {
            // Buscar el usuario por su ID en la tarea
            Usuario usuario = null;
            for (Usuario u : tarea.getUsuarios()) {
                if (u.getId() == idUsuario) {
                    usuario = u;
                    break;
                }
            }

            if (usuario != null) {
                try {
                    // Llamar a eliminar el usuario de la tarea
                    tarea.EliminarUsuarioTarea(usuario);
                    System.out.println("Usuario eliminado de la tarea.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Usuario no encontrado en la tarea.");
            }
        } else {
            System.out.println("Tarea no encontrada.");
        }
    }

*/

    public void modificarUsuario() {
        System.out.println("Ingrese el ID del usuario a modificar:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea

        System.out.println("Ingrese el nuevo nombre del usuario:");
        String nuevoNombre = scanner.nextLine();

        System.out.println("Ingrese el nuevo rol del usuario:");
        String nuevoRol = scanner.nextLine();

        System.out.println("¿El usuario está activo? (true/false):");
        boolean activo = scanner.nextBoolean();

        GestorUsuarios gestor = GestorUsuarios.getInstance();
        boolean resultado = gestor.modificarUsuario(id, nuevoNombre, nuevoRol, activo);

        if (resultado) {
            System.out.println("El usuario se modificó correctamente.");
        } else {
            System.out.println("No se pudo modificar el usuario.");
        }
    }

    public void modificarTarea() {
        System.out.println("Ingrese el ID de la tarea a modificar:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea

        System.out.println("Ingrese el nuevo título de la tarea:");
        String nuevoTitulo = scanner.nextLine();

        System.out.println("Ingrese la nueva descripción de la tarea:");
        String nuevaDescripcion = scanner.nextLine();

        System.out.println("Ingrese la nueva prioridad (BAJA, MEDIA, ALTA):");
        String prioridad = scanner.nextLine();
        Prioridad nuevaPrioridad = Prioridad.valueOf(prioridad.toUpperCase());

        System.out.println("Ingrese el nuevo estado (PENDIENTE, EN_PROGRESO, COMPLETADA):");
        String estado = scanner.nextLine();
        Estado nuevoEstado = Estado.valueOf(estado.toUpperCase());

        GestorTareas gestor = GestorTareas.getInstance();
        boolean resultado = gestor.modificarTarea(id, nuevoTitulo, nuevaDescripcion, nuevaPrioridad, nuevoEstado);

        if (resultado) {
            System.out.println("La tarea se modificó correctamente.");
        } else {
            System.out.println("No se pudo modificar la tarea.");
        }
    }


    public void agregarUsuario() {
        System.out.println("Ingrese el nombre del usuario:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el rol del usuario:");
        String rol = scanner.nextLine();

        Usuario usuario = new Usuario(nombre, rol);
        System.out.println("Usuario agregado: " + usuario);
    }

    // Eliminar un usuario por ID
    public void eliminarUsuario() {
        System.out.println("Ingrese el ID del usuario a eliminar:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        GestorUsuarios gestorUsuarios = GestorUsuarios.getInstance();
        Usuario usuario = gestorUsuarios.buscarUsuarioPorId(id);

        if (usuario != null) {
            gestorUsuarios.eliminarUsuario(usuario);
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

    // Listar todos los usuarios
    public void listarUsuarios() {
        GestorUsuarios.getInstance().listarUsuarios();
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
            if (scanner.hasNextInt()) {  // Verificar si la entrada es un número
                int resul = scanner.nextInt();
                scanner.nextLine();  // Limpiar el buffer de entrada

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
            if (scanner.hasNextInt()) {  // Verificar si la entrada es un número
                int resul = scanner.nextInt();
                scanner.nextLine();  // Limpiar el buffer de entrada

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

    // Eliminar una tarea por ID
    public void eliminarTarea() {
        System.out.println("Ingrese el ID de la tarea a eliminar:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        GestorTareas gestorTareas = GestorTareas.getInstance();
        if (gestorTareas.eliminarTarea(id)) {
            System.out.println("Tarea eliminada exitosamente.");
        } else {
            System.out.println("Tarea no encontrada.");
        }
    }

    // Listar todas las tareas
    public void listarTareas() {
        GestorTareas.getInstance().listarTareas();
    }
    public void agregarUsuarioATarea() {
        try {
            System.out.println("Ingrese el ID de la tarea:");
            int idTarea = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea

            System.out.println("Ingrese el ID del usuario a agregar:");
            int idUsuario = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea

            // Obtener el gestor de tareas y buscar la tarea
            GestorTareas gestorTareas = GestorTareas.getInstance();
            Tarea tarea = gestorTareas.buscarTareaPorId(idTarea);

            if (tarea == null) {
                System.out.println("No se encontró la tarea con ID: " + idTarea);
                return;
            }

            // Agregar el usuario a la tarea
            String resultado = tarea.agregarUsuarioPorId(idUsuario);
            System.out.println(resultado);

        } catch (UsuarioNoEncontrado e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    public void eliminarUsuarioDeTarea() {
        try {
            System.out.println("Ingrese el ID de la tarea:");
            int idTarea = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea

            System.out.println("Ingrese el ID del usuario a eliminar:");
            int idUsuario = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea

            // Obtener el gestor de tareas y buscar la tarea
            GestorTareas gestorTareas = GestorTareas.getInstance();
            Tarea tarea = gestorTareas.buscarTareaPorId(idTarea);

            if (tarea == null) {
                System.out.println("No se encontró la tarea con ID: " + idTarea);
                return;
            }

            // Buscar el usuario en la tarea
            Usuario usuario = tarea.buscarUsuarioPorId(idUsuario);
            if (usuario == null) {
                System.out.println("El usuario con ID " + idUsuario + " no está asignado a la tarea.");
                return;
            }

            // Eliminar el usuario de la tarea
            tarea.EliminarUsuarioTarea(usuario);
            System.out.println("Usuario eliminado de la tarea exitosamente.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    // Menú para interactuar con el administrador
    public void menu() {
        int opcion;
        do {
            System.out.println("\n*** Menú Administrador ***");
            System.out.println("1. Agregar usuario");
            System.out.println("2. Eliminar usuario");
            System.out.println("3. Listar usuarios");
            System.out.println("4. Agregar tarea");
            System.out.println("5. Eliminar tarea");
            System.out.println("6. Listar tareas");
            System.out.println("7. Asignar usuario a tarea");
            System.out.println("8. Eliminar usuario de tarea");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> agregarUsuario();
                case 2 -> eliminarUsuario();
                case 3 -> listarUsuarios();
                case 4 -> agregarTarea();
                case 5 -> eliminarTarea();
                case 6 -> listarTareas();
                case 7 -> agregarUsuarioATarea(); // Nueva opción
                case 8 -> eliminarUsuarioDeTarea(); // Nueva opción
                case 9 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 9);
    }

    public void agregarTareaAlProyecto(int idProyecto){
        Tablero tablero = Tablero.getInstance();

        Tarea tareaNueva = agregarTarea();

        tablero.agregarTareaAProyecto(idProyecto, tareaNueva);

    }


}
























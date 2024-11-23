import java.nio.channels.Pipe.SourceChannel;
import java.security.InvalidParameterException;
import java.util.*;

import javax.xml.transform.TransformerFactoryConfigurationError;

public class Administrador extends Persona {

    private Scanner scanner = new Scanner(System.in);

    public Administrador(String nombre, String rol) {
        super(nombre);
    }

    public void CompletarTarea(Tarea tarea) {
        tarea.setEstado(Estado.Finalizado);
        System.out.println("Tarea finalizada con exito...");
    }

    public void EliminarUsuarioDeTarea(Usuario usuario, Tarea tarea) { /// PARA VERIFICAR SI EXISTE LA TAREA TENGO QUE
    /// VERIFICARLO EN LA COLUMNA PRIMERO

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

    public void agregarProyecto() {

        GestorProyectos gestorProyectos = GestorProyectos.getInstance();

        System.out.println("Ingrese el nombre del proyecto.");
        String nombre = scanner.nextLine();

        while (nombre == null) {

            System.out.println("Debe indicar un nombre para el proyecto.");
            nombre = scanner.nextLine();

            if (nombre != null) {
                Proyecto proyectoNuevo = new Proyecto(nombre);
                gestorProyectos.AgregarProyectoNuevo(proyectoNuevo);
            }

        }

    }

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
        System.out.println(usuario);
    }

    // Eliminar un usuario por ID
    public void eliminarUsuario() {
        System.out.println("Ingrese el ID del usuario a eliminar:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        GestorUsuarios gestorUsuarios = GestorUsuarios.getInstance();
        Usuario usuario = gestorUsuarios.buscarUsuarioPorId(id);

        if (usuario != null) {
            if (!usuario.getActivo()) {
                System.out.println("El usuario que quiere eliminar no es valido");
            } else {
                gestorUsuarios.eliminarUsuario(usuario);
            }
        }

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

    public void agregarUsuarioATarea(int idTarea, int idUsuario) {
        GestorTareas gestorTareas = GestorTareas.getInstance();
        GestorUsuarios gestorUsuarios = GestorUsuarios.getInstance();

        try {
            // Verificar y obtener la tarea por ID
            Tarea tarea = gestorTareas.buscarTareaPorId(idTarea);
            if (tarea == null) {
                System.out.println("No se encontró la tarea con ID: " + idTarea + ".");
                return;
            }

            // Verificar y obtener el usuario por ID
            Usuario usuario = gestorUsuarios.buscarUsuarioPorId(idUsuario);
            if (usuario == null) {
                System.out.println("No se encontró el usuario con ID: " + idUsuario + ".");
                return;
            }

            // Agregar el usuario a la tarea
            String resultado = tarea.agregarUsuarioPorId(usuario.getId());
            System.out.println(resultado);

        } catch (UsuarioNoEncontrado e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    public void eliminarUsuarioDeTarea(int idTarea , int idUsuario ) {
        try {

            GestorTareas gestorTareas = GestorTareas.getInstance();
            Tarea tarea = gestorTareas.buscarTareaPorId(idTarea);

            if (tarea == null) {
                System.out.println("No se encontró la tarea con ID: " + idTarea);
                return;
            }


            Usuario usuario = tarea.buscarUsuarioPorId(idUsuario);
            if (usuario == null) {
                System.out.println("El usuario con ID " + idUsuario + " no está asignado a la tarea.");
                return;
            }


            tarea.EliminarUsuarioTarea(usuario);
            System.out.println("Usuario eliminado de la tarea exitosamente.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public String crearYAgregarTareaAProyecto(int idProyecto) {
        GestorProyectos gestorProyectos = GestorProyectos.getInstance();
        // Buscar el proyecto por ID en el tablero
        Proyecto proyecto = gestorProyectos.buscarProyectoPorId(idProyecto);
        System.out.println("lllll");

        if (proyecto == null) {
            return "Error: No se encontró el proyecto con ID " + idProyecto;
        }

        // Crear la nueva tarea utilizando el método definido
        Tarea nuevaTarea = agregarTarea();

        // Agregar la tarea al proyecto
        proyecto.agregarTareaNuevaAlProyecto(nuevaTarea);

        return "Tarea agregada con éxito al proyecto con ID " + idProyecto + ".";
    }

    /////// LOS NUEVOS METODOS DE MANEJAR LOS PROYECTOS EN LA CLASE ADMIN

    // Agregar un nuevo proyecto

    // Eliminar un proyecto
    public void eliminarProyecto() {
        GestorProyectos gestorProyectos = GestorProyectos.getInstance();
        System.out.println("Ingrese el ID del proyecto a eliminar:");
        int idProyecto = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        gestorProyectos.eliminarProyecto(idProyecto); // Se pasa la instancia de Administrador
    }

    // Listar todos los proyectos
    public void listarProyectos() {

        GestorProyectos gestorProyectos = GestorProyectos.getInstance();
        gestorProyectos.listarProyectos();
    }

    // Modificar un proyecto (si es necesario)
    public void modificarProyecto() {
        System.out.println("Ingrese el ID del proyecto a modificar:");
        int idProyecto = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        GestorProyectos gestorProyectos = GestorProyectos.getInstance();
        Proyecto proyecto = gestorProyectos.buscarProyectoPorId(idProyecto);

        if (proyecto != null) {
            System.out.println("Ingrese el nuevo nombre del proyecto:");
            String nuevoNombre = scanner.nextLine();
            proyecto.setNombre(nuevoNombre);
            System.out.println("Proyecto modificado con éxito.");
        } else {
            System.out.println("No se encontró el proyecto con el ID especificado.");
        }
    }

    public void menuposta() {
        int opcion;
        do {
            System.out.println("\n*** Menú Administrador ***");
            System.out.println("1. Menú de Proyectos");
            System.out.println("2. Menú de Usuarios y Tareas");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    menuProyecto2(); // Menú de proyectos
                    break;
                case 2:
                    menuUsuariosYTareas(); // Menú de usuarios y tareas
                    break;
                case 3:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 3);
    }

    // Menú de Proyectos
    public void menuProyecto2() {
        int opcion;
        do {
            System.out.println("\n*** Menú de Proyectos ***");
            System.out.println("1. Agregar Proyecto");
            System.out.println("2. Eliminar Proyecto");
            System.out.println("3. Listar Proyectos");
            System.out.println("4. Modificar Proyecto");
            System.out.println("5. Volver al menú principal");
            System.out.print("Elija una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    agregarProyecto();
                    break;
                case 2:
                    eliminarProyecto();
                    break;
                case 3:
                    listarProyectos();
                    break;
                case 4:
                    modificarProyecto();
                    break;
                case 5:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, elija una opción válida.");
            }
        } while (opcion != 5);
    }

    // Menú de Usuarios y Tareas
    public void menuUsuariosYTareas() {
        int opcion;
        boolean i;
        int opc;
        GestorProyectos gestorProyectos = GestorProyectos.getInstance();
        GestorTareas gestorTareas = GestorTareas.getInstance();
        GestorUsuarios gestorUsuarios = GestorUsuarios.getInstance();
        do {
            i = true;
            opc = 0;

            System.out.println("\n*** Menú de Usuarios y Tareas ***");
            System.out.println("1. Agregar usuario");
            System.out.println("2. Eliminar usuario");
            System.out.println("3. Listar usuarios");
            System.out.println("4. Agregar tarea");
            System.out.println("5. Eliminar tarea");
            System.out.println("6. Listar tareas");
            System.out.println("7. Asignar usuario a tarea");
            System.out.println("8. Eliminar usuario de tarea");
            System.out.println("9. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    agregarUsuario();
                    break;
                case 2:
                    listarUsuariosActivos();
                    eliminarUsuario();
                    break;
                case 3:
                    listarUsuarios();
                    break;
                case 4:
                    listarProyectos();
                    while (i) {
                        System.out.println("Ingrese el id del proyecto al que desea agregarle una tarea:");
                        try {
                            opc = scanner.nextInt();
                            if (gestorProyectos.buscarProyectoPorId(opc) != null) {
                                agregarTareaAProyecto(opc);
                                i = false;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("La opcion ingresada no es valida.");
                        }
                    }


                    System.out.println(gestorProyectos.mostrarTareasDelProyecto(opc) + "\n");

                    break;
                case 5:
                    listarProyectos();
                    while (i) {
                        System.out.println("Ingrese el id del proyecto al que desea eliminarle una tarea:");
                        try {
                            opc = scanner.nextInt();
                            if (gestorProyectos.buscarProyectoPorId(opc) != null) {
                                eliminarTareaDeUnPoryecto(opc);
                                i = false;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("La opcion ingresada no es valida.");
                        }
                    }

                    break;
                case 6:

                    while (i) {
                        listarProyectos();
                        System.out.println("Ingrese el id del proyecto al que desea listar sus tarea:");
                        try {
                            opc = scanner.nextInt();
                            if (gestorProyectos.buscarProyectoPorId(opc) != null) {

                                gestorProyectos.listarTareasProyecto(opc);

                                i = false;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("La opcion ingresada no es valida.");
                        }
                    }

                    break;
                case 7:



                    boolean tareaValida = false;
                    int idTarea = 0;

                    while (!tareaValida) {
                        try {
                            gestorTareas.listarTareas();
                            System.out.println("Ingrese el ID de la tarea:");
                            idTarea = scanner.nextInt();
                            scanner.nextLine();
                            if (gestorTareas.buscarTareaPorId(idTarea) != null) {
                                tareaValida = true;
                            } else {
                                System.out.println("No se encontró la tarea con ID: " + idTarea + ". Intente nuevamente.");
                            }
                        } catch (NumberFormatException | InputMismatchException e) {
                            System.out.println("La opción ingresada no es válida. Por favor, ingrese un número.");
                            scanner.nextLine();
                        }
                    }

                    boolean usuarioValido = false;
                    int idUsuario = 0;

                    while (!usuarioValido) {
                        try {
                            gestorUsuarios.listarUsuarios();
                            System.out.println("Ingrese el ID del usuario a agregar:");
                            idUsuario = scanner.nextInt();
                            scanner.nextLine();

                            if (gestorUsuarios.buscarUsuarioPorId(idUsuario) != null) {
                                usuarioValido = true;
                            } else {
                                System.out.println("No se encontró el usuario con ID: " + idUsuario + ". Intente nuevamente.");
                            }
                        } catch (NumberFormatException | InputMismatchException e) {
                            System.out.println("La opción ingresada no es válida. Por favor, ingrese un número.");
                            scanner.nextLine(); // Limpiar buffer del scanner
                        }
                    }


                    agregarUsuarioATarea(idTarea, idUsuario);
                    gestorUsuarios.listarUsuariosDeUnaTarea(idTarea);


                    break;
                case 8:

                    boolean tareaValida2 = false;
                    int idTarea2 = 0;

                    while (!tareaValida2) {
                        try {
                            gestorTareas.listarTareas();
                            System.out.println("Ingrese el ID de la tarea:");
                            idTarea2 = scanner.nextInt();
                            scanner.nextLine();

                            if (gestorTareas.buscarTareaPorId(idTarea2) != null) {
                                tareaValida2 = true;
                            } else {
                                System.out.println("No se encontró la tarea con ID: " + idTarea2 + ". Intente nuevamente.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("La opción ingresada no es válida. Por favor, ingrese un número.");
                            scanner.nextLine();
                        }
                    }

                    boolean usuarioValido2 = false;
                    int idUsuario2 = 0;
                    gestorUsuarios.listarUsuariosDeUnaTarea(idTarea2);
                    while (!usuarioValido2) {
                        try {
                            gestorUsuarios.listarUsuarios();
                            System.out.println("Ingrese el ID del usuario a agregar:");
                            idUsuario2 = scanner.nextInt();
                            scanner.nextLine();

                            if (gestorUsuarios.buscarUsuarioPorId(idUsuario2) != null) {
                                usuarioValido2 = true;
                            } else {
                                System.out.println("No se encontró el usuario con ID: " + idUsuario2 + ". Intente nuevamente.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("La opción ingresada no es válida. Por favor, ingrese un número.");
                            scanner.nextLine();
                        }
                    }


                    eliminarUsuarioDeTarea(idTarea2, idUsuario2);
                    gestorUsuarios.listarUsuariosDeUnaTarea(idTarea2);

                    break;
                case 9:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 9);
    }

    public void eliminarTareaDeUnPoryecto(int idProyecto) {
        GestorProyectos gestorProyectos = GestorProyectos.getInstance();

        System.out.println(gestorProyectos.mostrarTareasDelProyecto(idProyecto));
        System.out.println("Ingrese el ID de la tarea que desea eliminar del proyecto:");
        int opc = scanner.nextInt();

        gestorProyectos.eliminarTareaDeProyecto(idProyecto, opc);

        System.out.println("\n" + "Tareas restantes en el proyecto:");
        System.out.println("---------------------------------------------------------------------");
        System.out.println(gestorProyectos.mostrarTareasDelProyecto(idProyecto));
        System.out.println("\n" + "---------------------------------------------------------------------");
    }

    public String agregarTareaAProyecto(int idProyecto) {
        GestorProyectos gestorProyectos = GestorProyectos.getInstance();

        if (gestorProyectos.agregarTareaAProyecto(idProyecto)) {
            System.out.println("Tarea agregada con exito");
        }

        return "No fue posible agregar la tarea";
    }

    // Listar todos los usuarios
    public void listarUsuarios() {
        GestorUsuarios.getInstance().listarUsuarios();
    }

    public void listarUsuariosActivos() {
        GestorUsuarios.getInstance().listarUsuariosActivos();
    }

    public void listarUsuariosInactivos() {
        GestorUsuarios.getInstance().listarUsuariosInactivos();
    }

    public void menuListadoDeTareas() {
        System.out.println("1. Por hacer" + "\n" + "2. En progreso" + "\n" + "3. Completadas" + "\n" + "4. Todas");
        System.out.println("Seleccione la opcion deseada: ");
    }
}

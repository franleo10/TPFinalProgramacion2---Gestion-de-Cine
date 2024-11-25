import java.nio.channels.Pipe.SourceChannel;
import java.security.InvalidParameterException;
import java.util.*;

import javax.xml.transform.TransformerFactoryConfigurationError;

public class Administrador extends Persona {

    private Scanner scanner = new Scanner(System.in);

    public Administrador(String nombre, String rol, String contrasenia) {
        super(nombre, contrasenia);
    }

    public void CompletarTarea(Tarea tarea) {
        tarea.setEstado(Estado.Finalizado);
        System.out.println("Tarea finalizada con exito...");
    }

    public void EliminarUsuarioDeTarea(Usuario usuario, Tarea tarea) {

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

        System.out.println("Ingrese el nombre del proyecto:");
        String nombre = scanner.nextLine();

        // Validar que el nombre no esté vacío o sea nulo
        while (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("Debe indicar un nombre válido para el proyecto.");
            nombre = scanner.nextLine();
        }

        // Crear el proyecto y agregarlo al gestor
        Proyecto proyectoNuevo = new Proyecto(nombre);
        gestorProyectos.agregarProyecto(proyectoNuevo); // Usar el método correcto
    }

    public void modificarUsuario() {
       GestorUsuarios gestorUsuarios=GestorUsuarios.getInstance();
        System.out.println("Ingrese el ID del usuario a modificar:");
        int id = scanner.nextInt();
        scanner.nextLine();


        Usuario usuario = gestorUsuarios.buscarUsuarioPorId(id);

        if (usuario == null) {
            System.out.println("No se encontró un usuario con el ID " + id + ".");
            return;
        }

        boolean salir = false;
        while (!salir) {
            System.out.println("\nSeleccione el campo que desea modificar:");
            System.out.println("1. Nombre");
            System.out.println("2. Rol");
            System.out.println("3. Estado (activo/inactivo)");
            System.out.println("4. Salir");
            System.out.print("Opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el nuevo nombre del usuario:");
                    String nuevoNombre = scanner.nextLine();
                    usuario.setNombre(nuevoNombre);
                    System.out.println("El nombre se modificó correctamente.");
                    break;

                case 2:
                    System.out.println("Ingrese el nuevo rol del usuario:");
                    String puesto = scanner.nextLine();
                    usuario.setPuesto(puesto);
                    System.out.println("El rol se modificó correctamente.");
                    break;

                case 3:
                    System.out.println("¿El usuario está activo? (true/false):");
                    boolean activo = scanner.nextBoolean();
                    usuario.setActivo(activo);
                    System.out.println("El estado se modificó correctamente.");
                    break;

                case 4:
                    System.out.println("Saliendo del menú de modificación.");
                    salir = true;
                    break;

                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }

    gestorUsuarios.ListarUnUsuario(id);

    }

    public void modificarTarea() {
        GestorTareas gestor = GestorTareas.getInstance();
        GestorUsuarios gestorUsuarios=GestorUsuarios.getInstance();

        System.out.println("Ingrese el ID de la tarea a modificar:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea

        // Verificar si la tarea existe
        Tarea tarea = gestor.buscarTareaPorId(id);
        if (tarea == null) {
            System.out.println("No se encontró ninguna tarea con el ID proporcionado.");
            return;
        }

        boolean continuar = true;
        while (continuar) {
            System.out.println("\nSeleccione qué desea modificar:");
            System.out.println("1. Título");
            System.out.println("2. Descripción");
            System.out.println("3. Prioridad");
            System.out.println("4. Estado");
            System.out.println("5. Salir");
            System.out.print("Opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el nuevo título de la tarea:");
                    String nuevoTitulo = scanner.nextLine();
                    tarea.setTitulo(nuevoTitulo);
                    System.out.println("Título actualizado correctamente.");
                    break;

                case 2:
                    System.out.println("Ingrese la nueva descripción de la tarea:");
                    String nuevaDescripcion = scanner.nextLine();
                    tarea.setDescripcion(nuevaDescripcion);
                    System.out.println("Descripción actualizada correctamente.");
                    break;

                case 3:
                    // Manejo de prioridad
                    Prioridad nuevaPrioridad = null;
                    while (nuevaPrioridad == null) {
                        System.out.println("Ingrese la prioridad (1.ALTA, 2.MEDIA, 3.BAJA):");
                        if (scanner.hasNextInt()) { // Verificar si la entrada es un número
                            int resul = scanner.nextInt();
                            scanner.nextLine(); // Limpiar el buffer de entrada

                            switch (resul) {
                                case 1:
                                    nuevaPrioridad = Prioridad.Alta;
                                    break;
                                case 2:
                                    nuevaPrioridad = Prioridad.Media;
                                    break;
                                case 3:
                                    nuevaPrioridad = Prioridad.Baja;
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
                    tarea.setPrioridad(nuevaPrioridad);
                    System.out.println("Prioridad actualizada correctamente.");
                    break;

                case 4:
                    // Manejo de estado
                    Estado nuevoEstado = null;
                    while (nuevoEstado == null) {
                        System.out.println("Ingrese el estado (1.PENDIENTE, 2.PROGRESO, 3.FINALIZADA):");
                        if (scanner.hasNextInt()) { // Verificar si la entrada es un número
                            int resul = scanner.nextInt();
                            scanner.nextLine(); // Limpiar el buffer de entrada

                            switch (resul) {
                                case 1:
                                    nuevoEstado = Estado.Pendiente;
                                    break;
                                case 2:
                                    nuevoEstado = Estado.Proceso;
                                    break;
                                case 3:
                                    nuevoEstado = Estado.Finalizado;
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
                    tarea.setEstado(nuevoEstado);
                    System.out.println("Estado actualizado correctamente.");
                    break;

                case 5:
                    continuar = false;
                    System.out.println("Modificación de tarea finalizada.");
                    break;

                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }

        // Guardar los cambios en el gestor
        boolean resultado = gestor.modificarTarea(id, tarea.getTitulo(), tarea.getDescripcion(), tarea.getPrioridad(), tarea.getEstado());
        if (resultado) {
            System.out.println("Cambios guardados correctamente en el sistema.");
            gestor.mostrarTareaPorId(id);

        } else {
            System.out.println("No se pudo guardar los cambios en el sistema.");
        }
    }

    public void agregarUsuario() {
        System.out.println("Ingrese el nombre del usuario:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el rol del usuario:");
        String rol = scanner.nextLine();
        System.out.println("Ingrese su contrasenia");
        String contrasenia = scanner.nextLine();

        Usuario usuario = new Usuario(nombre, rol, contrasenia);
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
            System.out.println("9. Modificar tarea");
            System.out.println("10. Modificar usuario");
            System.out.println("11. Volver al menú principal");
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
                    modificarTarea();
                    break;
                case 10:
                    modificarUsuario();
                    break;
                case 11:
                    System.out.println("Volviendo al menu principal...");
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

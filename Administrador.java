import java.util.*;

public class Administrador extends Usuario {

    public Administrador(String nombre, String rol) {
        super(nombre, "ADMINISTRADOR");
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


    public void agregarTarea(Columna columna, Tarea tarea) {
        try {

            columna.agregarTarea(tarea);
            System.out.println("Tarea agregada con éxito a la columna...");
        } catch (TareaInexistente e) {
            System.out.println("Error al agregar la tarea: " + e.getMessage());
        }
    }

    /// Método para eliminar tarea
    public void eliminarTarea(Columna columna, Tarea tarea) {
        try {

            columna.eliminarTarea(tarea);
            System.out.println("Tarea eliminada con éxito de la columna...");
        } catch (TareaInexistente e) {
            System.out.println("Error al eliminar la tarea: " + e.getMessage());
        }
    }


    ///Metodo basico para buscar una tarea por id en una columna....

    public Tarea buscarTareaEnColumna(Columna columna, int idTarea) {
        for (Set<Tarea> tareasPorEstado : columna.getTareasPorEstado().values()) {
            for (Tarea tarea : tareasPorEstado) {
                if (tarea.getId() == idTarea) {
                    return tarea;
                }
            }
        }
        return null;
    }


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

///Metodo para agregar por teclado a un usuario a una tarea y columna especifica.
public void agregarUsuarioATareaEnColumna(List<Columna> columnas, int indiceColumna, int idTarea, int idUsuario) {
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
        Usuario usuario = tarea.buscarUsuarioPorId(idUsuario);  // Método para obtener el usuario por ID

        if (usuario != null) {
            // Si el usuario ya está asignado a la tarea, no lo agregamos de nuevo
            if (!tarea.getUsuarios().contains(usuario)) {
                tarea.agregarUsuario(usuario); // Llamada al método para agregar el usuario
                System.out.println("Usuario agregado a la tarea.");
            } else {
                System.out.println("El usuario ya está asignado a esta tarea.");
            }
        } else {
            System.out.println("Usuario no encontrado.");
        }
    } else {
        System.out.println("Tarea no encontrada.");
    }
}



}

















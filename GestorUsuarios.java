import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GestorUsuarios {
    
    // Instancia única del gestor
    private static GestorUsuarios instancia;
    
    // Conjunto que almacena los usuarios
    private Set<Usuario> usuarios;

    // Constructor privado para evitar instanciación directa
    private GestorUsuarios() {
        this.usuarios = new HashSet<>();
    }

    // Método estático para obtener la instancia única
    public static GestorUsuarios getInstance() {
        if (instancia == null) {
            instancia = new GestorUsuarios();
        }
        return instancia;
    }

    public boolean agregarUsuario(Usuario usuario) {
        if (usuario == null) {
            System.out.println("Error: El usuario no puede ser nulo.");
            return false;
        }

        // Verificar si el usuario ya está en el gestor
        if (usuarios.contains(usuario)) {
            System.out.println("El usuario ya está en el sistema.");
            return false;
        }

        // Agregar usuario al conjunto
        usuarios.add(usuario);
        System.out.println("Usuario agregado al sistema");
        return true;
    }

    // Eliminar un usuario del gestor
    public boolean eliminarUsuario(Usuario usuario) {
        if (usuario == null) {
            System.out.println("Error: El usuario no puede ser nulo.");
            return false;
        }

        // Verificar si el usuario está en el sistema
        if (!usuarios.contains(usuario)) {
            System.out.println("El usuario no está registrado en el sistema.");
            return false;
        }

        // Eliminar usuario del conjunto
        usuario.setActivo(false);
        System.out.println("Usuario dado de baja del sistema.");
        return true;
    }

    // Buscar un usuario por ID
    public Usuario buscarUsuarioPorId(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        System.out.println("Usuario con ID " + id + " no encontrado.");
        return null;
    }

    // Listar todos los usuarios en el sistema
    public void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios en el sistema.");
            return;
        }

        System.out.println("Usuarios registrados en el sistema:");
        for (Usuario usuario : usuarios) {
            System.out.println("\n" + "Nombre: " + usuario.getNombre());
            System.out.println("Puesto: " + usuario.getPuesto());
            System.out.println("IdUsuario: " + usuario.getId());
            if(usuario.getActivo()){
                System.out.println("Actualmente trabajando." + "\n");
            }
            else{
                System.out.println("Actualmente dado de baja"+ "\n");
            }
        }
    }
    
    // Listar todos los usuarios activos en el sistema
    public void listarUsuariosActivos() {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios en el sistema.");
            return;
        }

        System.out.println("Usuarios activos registrados en el sistema:");
        for (Usuario usuario : usuarios) {
            if(usuario.getActivo()){
            System.out.println("\n" + "Nombre: " + usuario.getNombre());
            System.out.println("Puesto: " + usuario.getPuesto());
            System.out.println("IdUsuario: " + usuario.getId() + "\n");
            }
        }
    }

    // Listar todos los usuarios inactivos en el sistema
    public void listarUsuariosInactivos() {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios en el sistema.");
            return;
        }

        System.out.println("Usuarios inactivos registrados en el sistema:");
        for (Usuario usuario : usuarios) {
            if(!usuario.getActivo()){
            System.out.println("\n" + "Nombre: " + usuario.getNombre());
            System.out.println("Puesto: " + usuario.getPuesto());
            System.out.println("IdUsuario: " + usuario.getId() + "\n");
            }
        }
    }


/////////////////////////////////////////////////////////////////////////
    public boolean modificarUsuario(int id, String nuevoNombre, String nuevoRol, boolean activo) {
        Usuario usuario = buscarUsuarioPorId(id);
        if (usuario == null) {
            System.out.println("Usuario con ID " + id + " no encontrado.");
            return false;
        }
        usuario.setNombre(nuevoNombre);
        usuario.setActivo(activo);
        System.out.println("Usuario modificado exitosamente.");
        return true;
    }




    
}

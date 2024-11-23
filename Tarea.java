import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Tarea {

    private static int contador = 1;
    private int id;
    private String titulo;
    private String descripcion;
    private Estado estado;
    private Prioridad prioridad;
    private Set<Usuario> usuarios;

    public Tarea(String titulo, String descripcion, Prioridad prioridad, Estado estado) {
        this.id = contador++;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.estado = estado;
        this.usuarios = new HashSet<>();

        GestorTareas tareas = GestorTareas.getInstance();
        tareas.agregarTarea(this);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void VerificarUsuario(Usuario usuario) throws UsuarioNoEncontrado {  ///Verifica si existe o si ya se agrego
        if (usuario == null) {
            throw new UsuarioNoEncontrado("El usuario no puede ser nulo.");
        }

        if (usuarios.contains(usuario)) {
            throw new UsuarioNoEncontrado("El usuario ya está asociado a esta tarea: " + usuario.getNombre());
        }

        usuarios.add(usuario);

    }

    public void agregarUsuario(Usuario usuario) {
        if (!usuarios.contains(usuario)) {
            usuarios.add(usuario);
            System.out.println("Usuario agregado a la tarea.");
        } else {
            System.out.println("El usuario ya está asignado a esta tarea.");
        }
    }


    public void EliminarUsuarioTarea(Usuario usuario) throws Exception {
        // Verificamos si el usuario existe en la lista
        if (!usuarios.contains(usuario)) {
            throw new Exception("El usuario no está asignado a esta tarea.");
        }

        // Si el usuario está, lo eliminamos
        usuarios.remove(usuario);
    }

    public void cambiarEstado(Estado nuevoEstado) { ///Cambia el estado de la tarea
        if (nuevoEstado == null) {
            System.out.println("Error: El estado no puede ser nulo.");
            return;
        }

        if (this.estado == nuevoEstado) {
            System.out.println("El estado ya es " + nuevoEstado + ". No se realizaron cambios.");
            return;
        }

        System.out.println("Cambiando estado de " + this.estado + " a " + nuevoEstado + ".");
        this.estado = nuevoEstado;
        System.out.println("Estado actualizado exitosamente.");
    }


    ///Metodos para buscar el usuario por id..

    public Usuario buscarUsuarioPorId(int idUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == idUsuario) {
                return usuario;
            }
        }
        return null;
    }

    public String agregarUsuarioPorId(int idUsuario){

        GestorUsuarios gestor = GestorUsuarios.getInstance();
        Usuario usuario = gestor.buscarUsuarioPorId(idUsuario);

        if(usuario == null){
            throw new UsuarioNoEncontrado("El usuario no existe en el registro.");
        }

        usuarios.add(usuario);
        return "Usuario: " + usuario.getNombre()+" agregado con exito a la tarea.";
    }

    public void ListarUsuariosTarea(int Id_Tarea){

        GestorTareas gestorTareas=GestorTareas.getInstance();
        Tarea tarea= gestorTareas.buscarTareaPorId(Id_Tarea);

     for (Usuario usuario: tarea.getUsuarios()){
         System.out.println(usuario);

     }



        }




    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Compara referencias
        if (obj == null || getClass() != obj.getClass()) return false; // Si son de diferentes clases o el objeto es null
        Tarea tarea = (Tarea) obj; // Convierte el objeto a tipo Tarea
        return id == tarea.id; // Compara el id
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // Genera un hash usando el id
    }

    @Override
    public String toString() {
    return "\n" +"Información de la Tarea:\n" +
           "- ID: " + getId() + "\n" +
           "- Título: " + getTitulo() + "\n" +
           "- Descripción: " + getDescripcion() + "\n" +
           "- Estado: " + getEstado() + "\n" +
           "- Prioridad: " + getPrioridad();
}

}





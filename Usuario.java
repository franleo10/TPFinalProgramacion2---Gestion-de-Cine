public class Usuario {

    private int id;
    private String nombre;
    private String rol;
    private static int contador = 1;
    private boolean activo;

    public Usuario(){
    }

    public Usuario(String nombre, String rol) {
        int con=0;
        this.id= contador++;
        this.nombre = nombre;
        this.rol = rol;
        this.activo = true;
         // Registrar el usuario automáticamente en el gestor
         GestorUsuarios gestor = GestorUsuarios.getInstance();
         gestor.agregarUsuario(this);  
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }

    public void RealizarAccionTarea(Tarea tarea){   ///Para que la clase sea abstracta..
    }

}

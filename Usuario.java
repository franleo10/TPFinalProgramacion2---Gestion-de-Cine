public abstract class Usuario {

    private int id;
    private String nombre;
    private String rol;

    public Usuario(String nombre, String rol) {
       int con=0;
       this.id=con++;
        this.nombre = nombre;
        this.rol = rol;
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

    public void RealizarAccionTarea( Tarea tarea){   ///Para que la clase sea abstracta..



    }
}

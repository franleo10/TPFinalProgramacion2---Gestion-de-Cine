public class Usuario extends Persona {


    private String Puesto;
    private boolean activo;

    public Usuario(String nombre,  String puesto) {
        super(nombre);
        Puesto = puesto;
        activo = true;
        GestorUsuarios gestor = GestorUsuarios.getInstance();
        gestor.agregarUsuario(this);
    }

    public String getPuesto() {
        return Puesto;
    }

    public void setPuesto(String puesto) {
        Puesto = puesto;
    }

    public void setActivo(boolean AB){
        activo = AB;
    }

    public boolean getActivo(){
        return activo;
    }

    


    @Override
public String toString() {
    return "\n" + "Información del Usuario:\n" +
           "- Puesto: " + Puesto + "\n" +
           "- Nombre: " + getNombre() + "\n";
}
}

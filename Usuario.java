public class Usuario extends Persona {


    private String Puesto;

    public Usuario(String nombre,  String puesto) {
        super(nombre);
        Puesto = puesto;
        GestorUsuarios gestor = GestorUsuarios.getInstance();
        gestor.agregarUsuario(this);
    }

    public String getPuesto() {
        return Puesto;
    }

    public void setPuesto(String puesto) {
        Puesto = puesto;
    }


    @Override
    public String toString() {
        return "Usuario{" +
                "Puesto='" + Puesto + '\'' +
                '}';
    }
}

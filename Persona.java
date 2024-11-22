import java.util.Objects;

public abstract class Persona {

    private int id;
    private String nombre;
    private static int contador = 1;
    private boolean activo;

    public Persona(String nombre) {
       this.id=contador++;
        this.nombre = nombre;
        this.activo=true;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona persona)) return false;
        return id == persona.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", activo=" + activo +
                '}';
    }
}

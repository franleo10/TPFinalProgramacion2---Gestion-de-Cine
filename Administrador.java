public class Administrador extends Usuario{

    public Administrador(String nombre, String rol) {
        super(nombre, "ADMINISTRADOR");
    }

    public void CompletarTarea(Tarea tarea){
        tarea.setEstado(Estado.Finalizado);
        System.out.println("Tarea finalizada con exito...");
    }

    public void EliminarUsuarioDeTarea(Usuario usuario, Tarea tarea){ ///PARA VERIFICAR SI EXISTE LA TAREA TENGO QUE VERIFICARLO EN LA COLUMNA PRIMERO

        try {
            tarea.VerificarUsuario(usuario);
            tarea.EliminarUsuarioTarea(usuario);
            System.out.println("Eliminado con exito el usuario de la tarea...");
        } catch (UsuarioNoEncontrado e) {
            System.out.println(e.getMessage());
        }


    }

    public void asignarTareaAUsuario(Tarea tarea, Usuario usuario) {
       try {
           tarea.VerificarUsuario(usuario);
           tarea.AgregarUsuarioTarea(usuario);
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
}

///Deberiamos hacer un metodo que cambie a un usuario de columna a una nueva..





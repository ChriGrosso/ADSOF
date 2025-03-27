package usuarios;

public abstract class Usuario {
    protected String nombre;
    protected String password;

    public Usuario(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }

    public abstract String getIdentificador();

    public abstract String getTipo();

    @Override
    public String toString() {
        return nombre + " " + getIdentificador() + " <" + getTipo() + ">";
    }
}
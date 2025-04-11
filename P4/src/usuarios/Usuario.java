package usuarios;

public abstract class Usuario {
    private String nombre;
    private String identificador;
    private String contrasena;

    public Usuario(String nombre, String identificador, String contrasena) {
        this.nombre = nombre;
        this.identificador = identificador;
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIdentificador() {
        return identificador;
    }
    
    public String getContrasena() {
		return contrasena;
	}

    public abstract String getTipo();

    @Override
    public String toString() {
        return nombre + " " + getIdentificador() + " <" + getTipo() + ">";
    }

	
}

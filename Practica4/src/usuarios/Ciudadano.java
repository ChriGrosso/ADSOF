package usuarios;

public class Ciudadano extends Usuario {
    private String nif;

    public Ciudadano(String nombre, String password, String nif) throws NIFInvalidoException {
        super(nombre, password);
        if (!validarNIF(nif)) {
            throw new NIFInvalidoException("NIF inválido: " + nif);
        }
        this.nif = nif;
    }

    private boolean validarNIF(String nif) {
        return nif.matches("\\d{8}[A-Za-z]");
    }

    @Override
    public String getIdentificador() {
        return "NIF(" + nif + ")";
    }

    @Override
    public String getTipo() {
        return "usuario";
    }
}
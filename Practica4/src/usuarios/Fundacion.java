package usuarios;

public class Fundacion extends Usuario {
    private String cif;

    public Fundacion(String nombre, String password, String cif) throws CIFInvalidoException {
        super(nombre, password);
        if (!validarCIF(cif)) {
            throw new CIFInvalidoException("CIF inválido: " + cif);
        }
        this.cif = cif;
    }

    private boolean validarCIF(String cif) {
        return cif.matches("[A-Za-z]\\d{7}[A-Za-z]");
    }

    @Override
    public String getIdentificador() {
        return "CIF(" + cif + ")";
    }

    @Override
    public String getTipo() {
        return "fundación";
    }
}
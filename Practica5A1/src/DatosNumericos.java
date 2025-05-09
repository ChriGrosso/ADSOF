
import java.util.HashMap;

class DatosNumericos extends HashMap<String, Integer> {
	private static final long serialVersionUID = 1L;

	public DatosNumericos(int op1, int op2) {
        this.put("op1", op1);
        this.put("op2", op2);
        this.put("result", 0);
    }

    @Override
    public String toString() {
        return "{op1=" + get("op1") + ", op2=" + get("op2") + ", result=" + get("result") + "}";
    }
}
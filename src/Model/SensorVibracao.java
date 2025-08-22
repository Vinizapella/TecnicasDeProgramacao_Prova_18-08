package Model;

public class SensorVibracao extends Sensor {
    private static final double LIMITE_VIBRACAO = 60.0;

    public SensorVibracao() {
        super();
        this.tipo = "Vibração";
    }

    public SensorVibracao(String codigo, String nomeEquipamento) {
        super(codigo, nomeEquipamento, "Vibração");
    }

    @Override
    public boolean verificarAlerta(Medicao medicao) {
        if (medicao == null) {
            return false;
        }
        return medicao.getValor() != LIMITE_VIBRACAO;
    }

    @Override
    public String obterLimiteAlerta() {
        return String.format("%.1f Hz (exato)", LIMITE_VIBRACAO);
    }

    public static double getLimiteVibracao() {
        return LIMITE_VIBRACAO;
    }

    @Override
    public String toString() {
        return String.format("Código: %s | Tipo: %s | Equipamento: %s | Limite: %s",
                codigo, tipo, nomeEquipamento, obterLimiteAlerta());
    }
}
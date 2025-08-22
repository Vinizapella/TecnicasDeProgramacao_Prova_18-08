package Model;

public class SensorTemperatura extends Sensor {
    private static final double LIMITE_TEMPERATURA = 80.0;

    public SensorTemperatura() {
        super();
        this.tipo = "Temperatura";
    }

    public SensorTemperatura(String codigo, String nomeEquipamento) {
        super(codigo, nomeEquipamento, "Temperatura");
    }

    @Override
    public boolean verificarAlerta(Medicao medicao) {
        if (medicao == null) {
            return false;
        }
        return medicao.getValor() > LIMITE_TEMPERATURA;
    }

    @Override
    public String obterLimiteAlerta() {
        return String.format("%.1f Â°C", LIMITE_TEMPERATURA);
    }

    public static double getLimiteTemperatura() {
        return LIMITE_TEMPERATURA;
    }

    @Override
    public String toString() {
        return String.format("CÃ³digo: %s | Tipo: %s | Equipamento: %s | Limite: %s",
                codigo, tipo, nomeEquipamento, obterLimiteAlerta());
    }
}
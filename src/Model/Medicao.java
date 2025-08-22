package Model;

public class Medicao {
    private double valor;
    private String dataHora;

    public Medicao() {
        this.valor = 0.0;
        this.dataHora = "";
    }

    public Medicao(double valor, String dataHora) {
        this.valor = valor >= 0 ? valor : 0.0;

        if (dataHora == null || dataHora.trim().isEmpty()) {
            this.dataHora = "00/00/0000 00:00";
        } else {
            this.dataHora = dataHora.trim();
        }
    }

    public double getValor() {
        return valor;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setValor(double valor) {
        if (valor >= 0) {
            this.valor = valor;
        }
    }

    public void setDataHora(String dataHora) {
        if (dataHora != null && !dataHora.trim().isEmpty()) {
            this.dataHora = dataHora.trim();
        }
    }

    @Override
    public String toString() {
        return String.format("Valor: %.1f | Data: %s", valor, dataHora);
    }

    public String toString(boolean temAlerta) {
        String alertaIndicador = temAlerta ? " ⚠️ ALERTA" : "";
        return String.format("Valor: %.1f | Data: %s%s", valor, dataHora, alertaIndicador);
    }
}
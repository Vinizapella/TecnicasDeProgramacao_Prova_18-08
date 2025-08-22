package Model;

public class Sensor {
    protected String codigo;
    protected String nomeEquipamento;
    protected String tipo;

    public Sensor() {
        this.codigo = "";
        this.nomeEquipamento = "";
        this.tipo = "";
    }

    public Sensor(String codigo, String nomeEquipamento, String tipo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            this.codigo = "SEM_CODIGO";
        } else {
            this.codigo = codigo.trim();
        }

        if (nomeEquipamento == null || nomeEquipamento.trim().isEmpty()) {
            this.nomeEquipamento = "SEM_NOME";
        } else {
            this.nomeEquipamento = nomeEquipamento.trim();
        }

        if (tipo == null || tipo.trim().isEmpty()) {
            this.tipo = "INDEFINIDO";
        } else {
            this.tipo = tipo.trim();
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNomeEquipamento() {
        return nomeEquipamento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setCodigo(String codigo) {
        if (codigo != null && !codigo.trim().isEmpty()) {
            this.codigo = codigo.trim();
        }
    }

    public void setNomeEquipamento(String nomeEquipamento) {
        if (nomeEquipamento != null && !nomeEquipamento.trim().isEmpty()) {
            this.nomeEquipamento = nomeEquipamento.trim();
        }
    }

    public void setTipo(String tipo) {
        if (tipo != null && !tipo.trim().isEmpty()) {
            this.tipo = tipo.trim();
        }
    }

    public boolean verificarAlerta(Medicao medicao) {
        return false;
    }

    public String obterLimiteAlerta() {
        return "NÃ£o definido";
    }

    @Override
    public String toString() {
        return String.format("CÃ³digo: %s | Tipo: %s | Equipamento: %s",
                codigo, tipo, nomeEquipamento);
    }
}
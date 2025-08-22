package Service;

import Model.*;
import View.InterfaceUsuario;
import java.util.ArrayList;
import java.util.List;

public class SensorService {
    private List<Sensor> sensores;
    private List<List<Medicao>> todasMedicoes; // Lista de listas de medições
    private InterfaceUsuario ui;

    public SensorService(InterfaceUsuario ui) {
        this.sensores = new ArrayList<>();
        this.todasMedicoes = new ArrayList<>();
        this.ui = ui;
    }

    public void gerenciarSistema(int opcao) {
        switch (opcao) {
            case 1:
                cadastrarSensor();
                break;
            case 2:
                listarSensores();
                break;
            case 3:
                registrarMedicao();
                break;
            case 4:
                exibirHistoricoMedicoes();
                break;
            case 5:
                verificarAlertas();
                break;
            case 6:
                listarSensoresCriticos();
                break;
            case 0:
                break;
            default:
                ui.mostrarErro("Opção inválida!");
        }
    }

    // ==================== CADASTRAR SENSOR ====================

    private void cadastrarSensor() {
        String codigo = ui.lerCodigoSensor();

        // Verifica se já existe sensor com esse código
        if (buscarIndiceSensorPorCodigo(codigo) != -1) {
            ui.mostrarErro("Já existe um sensor com este código!");
            return;
        }

        String nomeEquipamento = ui.lerNomeEquipamento();
        int tipoSensor = ui.mostrarMenuTipoSensor();

        Sensor novoSensor;
        if (tipoSensor == 1) {
            novoSensor = new SensorTemperatura(codigo, nomeEquipamento);
        } else {
            novoSensor = new SensorVibracao(codigo, nomeEquipamento);
        }

        // Adiciona sensor e cria lista vazia de medições para ele
        sensores.add(novoSensor);
        todasMedicoes.add(new ArrayList<>());

        ui.mostrarSensorCadastrado(novoSensor.getTipo(), novoSensor.obterLimiteAlerta());
    }

    // ==================== LISTAR SENSORES ====================

    private void listarSensores() {
        ui.mostrarListaSensores(sensores);
    }

    // ==================== REGISTRAR MEDIÇÃO ====================

    private void registrarMedicao() {
        String codigo = ui.lerCodigoSensor();
        int indiceSensor = buscarIndiceSensorPorCodigo(codigo);

        if (indiceSensor == -1) {
            ui.mostrarErro("Sensor não encontrado!");
            return;
        }

        double valor = ui.lerValorMedicao();
        String dataHora = ui.lerDataHora();

        Medicao novaMedicao = new Medicao(valor, dataHora);
        Sensor sensor = sensores.get(indiceSensor);

        // Adiciona medição na lista correspondente ao sensor
        todasMedicoes.get(indiceSensor).add(novaMedicao);

        ui.mostrarMedicaoRegistrada();

        // Verifica se há alerta
        if (sensor.verificarAlerta(novaMedicao)) {
            String mensagemAlerta;
            if (sensor.getTipo().equals("Temperatura")) {
                mensagemAlerta = "Medição fora do limite técnico! (" + valor + " > 80.0)";
            } else {
                mensagemAlerta = "Medição fora do limite técnico! (" + valor + " ≠ 60.0)";
            }
            ui.mostrarAlerta(mensagemAlerta);
        }
    }

    // ==================== EXIBIR HISTÓRICO ====================

    private void exibirHistoricoMedicoes() {
        String codigo = ui.lerCodigoSensor();
        int indiceSensor = buscarIndiceSensorPorCodigo(codigo);

        if (indiceSensor == -1) {
            ui.mostrarErro("Sensor não encontrado!");
            return;
        }

        List<Medicao> medicoes = todasMedicoes.get(indiceSensor);
        Sensor sensor = sensores.get(indiceSensor);

        // Cria lista de strings formatadas para o histórico
        List<String> historicoFormatado = new ArrayList<>();

        for (Medicao medicao : medicoes) {
            boolean temAlerta = sensor.verificarAlerta(medicao);
            historicoFormatado.add(medicao.toString(temAlerta));
        }

        ui.mostrarHistoricoMedicoes(codigo, historicoFormatado);
    }

    // ==================== VERIFICAR ALERTAS ====================

    private void verificarAlertas() {
        ui.mostrarVerificacaoAlertas();

        for (int i = 0; i < sensores.size(); i++) {
            Sensor sensor = sensores.get(i);
            List<Medicao> medicoes = todasMedicoes.get(i);

            int quantidadeAlertas = contarAlertas(sensor, medicoes);

            ui.mostrarResultadoAlerta(
                    sensor.getCodigo(),
                    sensor.getTipo(),
                    sensor.getNomeEquipamento(),
                    quantidadeAlertas
            );
        }
    }

    // ==================== LISTAR SENSORES CRÍTICOS ====================

    private void listarSensoresCriticos() {
        List<String> sensoresCriticos = new ArrayList<>();

        for (int i = 0; i < sensores.size(); i++) {
            Sensor sensor = sensores.get(i);
            List<Medicao> medicoes = todasMedicoes.get(i);

            int quantidadeAlertas = contarAlertas(sensor, medicoes);

            if (quantidadeAlertas > 3) {
                String infoSensor = "Código: " + sensor.getCodigo() +
                        " | Tipo: " + sensor.getTipo() +
                        " | Equipamento: " + sensor.getNomeEquipamento() +
                        " | Alertas: " + quantidadeAlertas;
                sensoresCriticos.add(infoSensor);
            }
        }

        ui.mostrarSensoresCriticos(sensoresCriticos);
    }

    // ==================== MÉTODOS AUXILIARES ====================

    private int buscarIndiceSensorPorCodigo(String codigo) {
        for (int i = 0; i < sensores.size(); i++) {
            if (sensores.get(i).getCodigo().equalsIgnoreCase(codigo)) {
                return i;
            }
        }
        return -1; // Não encontrado
    }

    private int contarAlertas(Sensor sensor, List<Medicao> medicoes) {
        int contador = 0;
        for (Medicao medicao : medicoes) {
            if (sensor.verificarAlerta(medicao)) {
                contador++;
            }
        }
        return contador;
    }
}
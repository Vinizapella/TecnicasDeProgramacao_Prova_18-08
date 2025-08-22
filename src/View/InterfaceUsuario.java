package View;


import Model.*;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class InterfaceUsuario {
    private Scanner scanner;

    public InterfaceUsuario() {
        this.scanner = new Scanner(System.in);
    }

    // ==================== MENU PRINCIPAL ====================

    public int mostrarMenuPrincipal() {
        System.out.println("=========================================");
        System.out.println(" Sistema de Monitoramento WEG – Versão 1.0");
        System.out.println("=========================================");
        System.out.println();
        System.out.println("1 - Cadastrar Sensor");
        System.out.println("2 - Listar Sensores");
        System.out.println("3 - Registrar Medição");
        System.out.println("4 - Exibir Histórico de Medições");
        System.out.println("5 - Verificar Alertas");
        System.out.println("6 - Listar Sensores Críticos");
        System.out.println("0 - Sair");
        System.out.println();
        System.out.print("Digite a opção: ");

        return lerOpcaoMenu(0, 6);
    }

    // ==================== CADASTRAR SENSOR ====================

    public String lerCodigoSensor() {
        return lerTexto("Digite o código do sensor: ");
    }

    public String lerNomeEquipamento() {
        return lerTexto("Digite o nome do equipamento: ");
    }

    public int mostrarMenuTipoSensor() {
        System.out.println("Escolha o tipo de sensor:");
        System.out.println("1 - Temperatura");
        System.out.println("2 - Vibração");
        System.out.print("Opção: ");

        return lerOpcaoMenu(1, 2);
    }

    public void mostrarSensorCadastrado(String tipo, String limite) {
        System.out.println();
        System.out.println("✅ Sensor cadastrado com sucesso!");
        System.out.println("Tipo: " + tipo + " | Limite de alerta: " + limite);
    }

    // ==================== LISTAR SENSORES ====================

    public void mostrarListaSensores(List<Sensor> sensores) {
        if (sensores.isEmpty()) {
            System.out.println("Nenhum sensor cadastrado.");
            return;
        }

        System.out.println();
        System.out.println("Sensores Cadastrados:");
        System.out.println();
        for (Sensor sensor : sensores) {
            System.out.println("Código: " + sensor.getCodigo() + " | Tipo: " + sensor.getTipo() +
                    " | Equipamento: " + sensor.getNomeEquipamento());
        }
    }

    // ==================== REGISTRAR MEDIÇÃO ====================

    public double lerValorMedicao() {
        return lerDouble("Digite o valor da medição: ");
    }

    public String lerDataHora() {
        return lerTexto("Digite a data e hora (formato dd/MM/yyyy HH:mm): ");
    }

    public void mostrarMedicaoRegistrada() {
        System.out.println();
        System.out.println("✅ Medição registrada com sucesso!");
    }

    public void mostrarAlerta(String mensagem) {
        System.out.println();
        System.out.println("⚠️ ALERTA: " + mensagem);
    }

    // ==================== HISTÓRICO DE MEDIÇÕES ====================

    public void mostrarHistoricoMedicoes(String codigoSensor, List<String> historico) {
        if (historico.isEmpty()) {
            System.out.println("Nenhuma medição encontrada para o sensor " + codigoSensor);
            return;
        }

        System.out.println();
        System.out.println("Histórico de Medições do Sensor " + codigoSensor + ":");
        System.out.println();

        for (int i = 0; i < historico.size(); i++) {
            System.out.println((i + 1) + ". " + historico.get(i));
        }
    }

    // ==================== VERIFICAR ALERTAS ====================

    public void mostrarVerificacaoAlertas() {
        System.out.println();
        System.out.println("Verificando sensores...");
        System.out.println();
    }

    public void mostrarResultadoAlerta(String codigoSensor, String tipo, String equipamento, int alertas) {
        System.out.println("Sensor " + codigoSensor + " (" + tipo + ") – " + equipamento + ":");

        if (alertas > 0) {
            System.out.println("⚠️ " + alertas + " alerta(s) detectado(s)");
        } else {
            System.out.println("✅ Nenhum alerta");
        }
        System.out.println();
    }

    // ==================== SENSORES CRÍTICOS ====================

    public void mostrarSensoresCriticos(List<String> sensoresCriticos) {
        if (sensoresCriticos.isEmpty()) {
            System.out.println("Nenhum sensor crítico encontrado!");
            return;
        }

        System.out.println();
        System.out.println("Sensores com mais de 3 alertas:");
        System.out.println();

        for (String sensorInfo : sensoresCriticos) {
            System.out.println(sensorInfo);
        }

        System.out.println();
        System.out.println("⚠️ ATENÇÃO: Inspeção imediata recomendada!");
    }

    // ==================== MENSAGENS GERAIS ====================

    public void mostrarErro(String erro) {
        System.out.println("❌ " + erro);
    }

    public void fechar() {
        scanner.close();
        System.out.println("Encerrando sistema... Obrigado por usar o Monitoramento WEG!");
    }

    // ==================== MÉTODOS PRIVADOS ====================

    private String lerTexto(String mensagem) {
        String texto = "";
        while (texto.isEmpty()) {
            System.out.print(mensagem);
            texto = scanner.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("Erro: Campo não pode estar vazio!");
            }
        }
        return texto;
    }

    private double lerDouble(String mensagem) {
        double numero = -1;
        boolean valido = false;

        while (!valido) {
            try {
                System.out.print(mensagem);
                numero = scanner.nextDouble();
                scanner.nextLine();
                if (numero >= 0) {
                    valido = true;
                } else {
                    System.out.println("Erro: O valor deve ser positivo!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Digite apenas números!");
                scanner.nextLine();
            }
        }
        return numero;
    }

    private int lerOpcaoMenu(int min, int max) {
        int opcao = -1;
        boolean valido = false;

        while (!valido) {
            try {
                opcao = scanner.nextInt();
                scanner.nextLine();
                if (opcao >= min && opcao <= max) {
                    valido = true;
                } else {
                    System.out.println("Erro: Opção inválida!");
                    System.out.print("Digite a opção: ");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Digite apenas números!");
                System.out.print("Digite a opção: ");
                scanner.nextLine();
            }
        }
        return opcao;
    }
}
package Main;

import Service.SensorService;
import View.InterfaceUsuario;

public class Main {
    public static void main(String[] args) {
        InterfaceUsuario ui = new InterfaceUsuario();
        SensorService service = new SensorService(ui);

        int opcao;
        do {
            opcao = ui.mostrarMenuPrincipal();
            service.gerenciarSistema(opcao);
        } while (opcao != 0);

        ui.fechar();
    }
}
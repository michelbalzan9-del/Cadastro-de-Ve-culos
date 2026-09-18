import java.util.ArrayList;
import java.util.List;

List<Veiculo> veiculos = new ArrayList<>();

void main() {
    String menu = """
            ======= Cadastro de Veículos =======
            1 - Cadastrar Veículo
            2 - Listar Veículos
            3 - Consultar Veículo
            0 - Sair
            """;
    int opcao;
    do {
        IO.println(menu);
        opcao = lerInt("Escolha uma opção:");
        switch (opcao) {
            case 1 -> cadastrarVeiculo();
            case 2 -> listarVeiculos();
            case 3 -> consultarVeiculo();
            case 0 -> IO.println("Até logo!");
            default -> IO.println("Opção inválida.");
        }
        if (opcao != 0)
            IO.println(); 
    } while (opcao != 0);
}

void cadastrarVeiculo() {
    IO.println("\n==== Cadastrar Veículo ====");

    String marca = lerTextoObrigatorio("Informe a marca:");
    String modelo = lerTextoObrigatorio("Informe o modelo:");
    int ano = lerAno();
    String placa = lerPlaca();

    veiculos.add(new Veiculo(marca, modelo, ano, placa));
    IO.println("Veículo cadastrado com sucesso!");
}

int lerInt(String mensagem) {
    while (true) {
        try {
            return Integer.parseInt(IO.readln(mensagem + " ").trim());
        } catch (NumberFormatException e) {
            IO.println("Valor inválido. Digite apenas números inteiros.");
        }
    }
}

String lerTextoObrigatorio(String mensagem) {
    String texto;
    do {
        texto = IO.readln(mensagem + " ").trim();
        if (texto.isBlank())
            IO.println("Este campo não pode ficar vazio.");
    } while (texto.isBlank());
    return texto;
}

int lerAno() {
    while (true) {
        int ano = lerInt("Informe o ano do Veículo:");
        if (Veiculo.validaAno(ano))
            return ano;
        IO.println("Ano inválido! Deve estar entre " + Veiculo.ANO_MINIMO
                + " e " + Veiculo.anoMaximo() + ".");
    }
}

String lerPlaca() {
    while (true) {
        String placa = IO.readln("Informe a placa: ").trim();
        if (!Veiculo.validaPlaca(placa)) {
            IO.println("Placa inválida! Use o formato ABC1234 ou ABC1D23.");
        } else if (buscarPorPlaca(placa) != null) {
            IO.println("Já existe um veículo cadastrado com essa placa!");
        } else {
            return placa;
        }
    }
}

void listarVeiculos() {
    IO.println("\n==== Veículos Cadastrados ====");
    if (veiculos.isEmpty()) {
        IO.println("Nenhum veículo cadastrado.");
        return;
    }
    for (int i = 0; i < veiculos.size(); i++) {
        Veiculo v = veiculos.get(i);
        if (i > 0)
            IO.println();
        IO.println("Veículo " + (i + 1));
        IO.println("  Marca:  " + v.getMarca());
        IO.println("  Modelo: " + v.getModelo());
        IO.println("  Ano:    " + v.getAno());
        IO.println("  Placa:  " + v.getPlaca());
    }
}

void consultarVeiculo() {
    IO.println("\n==== Consultar Veículo ====");
    String placa = IO.readln("Informe a placa: ").trim();
    Veiculo veiculo = buscarPorPlaca(placa);
    if (veiculo == null) {
        IO.println("Nenhum veículo encontrado com a placa informada.");
        return;
    }
    IO.println("Marca:  " + veiculo.getMarca());
    IO.println("Modelo: " + veiculo.getModelo());
    IO.println("Ano:    " + veiculo.getAno());
    IO.println("Placa:  " + veiculo.getPlaca());
}

Veiculo buscarPorPlaca(String placa) {
    String procurada = Veiculo.normalizaPlaca(placa);
    for (Veiculo v : veiculos) {
        if (v.getPlaca().equals(procurada))
            return v;
    }
    return null;
}
package Cadastro.Cidades_crud.view;

import Cadastro.Cidades_crud.controller.exception.CidadeNaoEncontrada;
import Cadastro.Cidades_crud.controller.CidadeController;
import Cadastro.Cidades_crud.model.Cidade;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class CidadeView {

    private CidadeController controller;
    private Scanner scanner;

    public CidadeView(
            CidadeController controller,
            Scanner scanner
    ) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public void cadastrar() {
        Cidade cidade = new Cidade();

        System.out.println("Informe o nome da cidade:");
        String nomeCidade = scanner.nextLine();
        cidade.setNomeCidade(nomeCidade);

        System.out.println("Informe o nome do Estado ao qual a cidade pertence: ");
        String nomeEstado = scanner.nextLine();
        cidade.setNomeEstado(nomeEstado);

        controller.cadastrar(cidade);
    }

    public void listar(UUID id) {
        Cidade cidade = controller.ler(id);
        exibirCidade(cidade);
    }

    public void atualizar() {
        listar();
        System.out.println("\nInforme o número da cidade que deseja atualizar:");
        Integer numeroCidade = scanner.nextInt();
        scanner.nextLine();
        Cidade cidade = controller.listar().get(numeroCidade - 1);
        atualizar(cidade);
    }

    public void atualizar(Cidade cidade) {
        exibirCidade(cidade);

        System.out.println("\nInforme o novo nome da Cidade:");
        String nome1 = scanner.nextLine();
        cidade.setNomeCidade(nome1);

        System.out.println("Informe o novo nome do Estado:");
        String nome2 = scanner.nextLine();
        cidade.setNomeEstado(nome2);

        try {
            controller.update(cidade.getId(), cidade);
        } catch (CidadeNaoEncontrada ex) {
            System.out.println("Cidade informada não existe na base. Tente novamente.");
        }
    }

    public void apagar() {
        listar();
        System.out.println("\nInforme o número da cidade que deseja apagar:");
        Integer numero = scanner.nextInt();
        scanner.nextLine();
        Cidade cidade = controller.listar().get(numero - 1);
        apagar(cidade.getId());
    }

    public void apagar(UUID id) {
        try {
            Cidade cidade = controller.delete(id);
            System.out.println("Cidade apagada foi:");
            exibirCidade(cidade);
        } catch (CidadeNaoEncontrada ex) {
            System.out.println("Cidade não foi agapada pois não foi localizada. Tente novamente!");
        }
    }

    public void listar() {
        List<Cidade> cidades = controller.listar();
        System.out.println("| Número | Nome da cidade | Nome do Estado |");
        for (int index = 0; index < cidades.size(); index++) {
            System.out.println("");
            System.out.print("| " + (index + 1) + "      ");
            exibirCidade(cidades.get(index));
        }
    }

    public void exibirCidade(Cidade cidade) {
        System.out.print("| ");
        System.out.print(cidade.getNomeCidade());
        System.out.print("    | ");
        System.out.print(cidade.getNomeEstado());
        System.out.print("    |  ");
    }

    public void exibirOpcoes() {
        System.out.println("");
        System.out.println("Cadastro de Cidades\n");
        System.out.println("Infome a opção desejada:");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Listar");
        System.out.println("3 - Atualizar");
        System.out.println("4 - Apagar");
        System.out.println("0 - Sair");
        Integer opcao = scanner.nextInt();
        scanner.nextLine();
        switch (opcao) {
            case 1:
                cadastrar();
                break;
            case 2:
                listar();
                break;
            case 3:
                atualizar();
                break;
            case 4:
                apagar();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Opção invalida!");
        }
        exibirOpcoes();
    }
}

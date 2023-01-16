package Cadastro.Cidades_crud;

import Cadastro.Cidades_crud.controller.CidadeArmazenamentoTipo;
import Cadastro.Cidades_crud.controller.CidadeControllerFactory;
import Cadastro.Cidades_crud.view.CidadeView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String tipoArmazenamento = args[0];
        CidadeArmazenamentoTipo tipo = CidadeArmazenamentoTipo.valueOf(tipoArmazenamento);

        CidadeControllerFactory factory = new CidadeControllerFactory();

        CidadeView view = new CidadeView(
                factory.criar(tipo),
                new Scanner(System.in)
        );
        view.exibirOpcoes();
    }

}

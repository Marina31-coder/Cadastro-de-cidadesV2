package Cadastro.Cidades_crud.controller;

import Cadastro.Cidades_crud.controller.impl.CidadeArmazenamentoVolatilController;

public class CidadeControllerFactory {

    // Factory method
    public CidadeController criar(CidadeArmazenamentoTipo tipo) {
        if (tipo == CidadeArmazenamentoTipo.VOLATIL) {
            return new CidadeArmazenamentoVolatilController();
        } else {
            throw new RuntimeException("Nenhuma implementação disponível");
        }
    }

}

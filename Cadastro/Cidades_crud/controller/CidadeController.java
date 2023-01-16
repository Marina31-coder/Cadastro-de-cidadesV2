package Cadastro.Cidades_crud.controller;

import Cadastro.Cidades_crud.model.Cidade;

import java.util.List;
import java.util.UUID;
public interface CidadeController {

    void cadastrar(Cidade cidade);

    Cidade ler(UUID id);

    List<Cidade> listar();

    void update(UUID id, Cidade cidade);

    Cidade delete(UUID id);
}

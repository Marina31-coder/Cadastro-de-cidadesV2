package Cadastro.Cidades_crud.controller.impl;

import Cadastro.Cidades_crud.controller.exception.CidadeNaoEncontrada;
import Cadastro.Cidades_crud.controller.CidadeController;
import Cadastro.Cidades_crud.model.Cidade;

import java.util.*;

public class CidadeArmazenamentoVolatilController implements CidadeController {

    private Map<UUID, Cidade> cidades = new HashMap<>();

    @Override
    public void cadastrar(Cidade cidade) {
        cidade.setId(UUID.randomUUID());
        cidades.put(cidade.getId(), cidade);
    }

    @Override
    public Cidade ler(UUID id) {
        Cidade encontrada = cidades.get(id);
        if (encontrada == null) {
            throw new CidadeNaoEncontrada();
        }
        return encontrada;
    }
    @Override
    public List<Cidade> listar() {
        return new ArrayList<>(cidades.values());
    }

    @Override
    public void update(UUID id, Cidade cidade) {
        if (cidades.containsKey(id)) {
            cidades.put(id, cidade);
        } else {
            throw new CidadeNaoEncontrada();
        }
    }

    @Override
    public Cidade delete(UUID id) {
        Cidade apagada = cidades.remove(id);
        if (apagada == null) {
            throw new CidadeNaoEncontrada();
        }
        return apagada;
    }

}

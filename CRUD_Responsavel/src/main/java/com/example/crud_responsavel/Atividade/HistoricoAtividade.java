package com.example.crud_responsavel.Atividade;

import java.util.ArrayDeque;
import java.util.Deque;

public final class HistoricoAtividade {

    private static final HistoricoAtividade INSTANCIA = new HistoricoAtividade();

    private record Registro(Atividade.Memento memento, boolean eraExclusao) {
    }

    private final Deque<Registro> pilha = new ArrayDeque<>();

    private HistoricoAtividade() {
    }

    public static HistoricoAtividade getInstancia() {
        return INSTANCIA;
    }

    public void guardarAntesDeAlterar(Atividade atividade) {
        pilha.push(new Registro(atividade.salvarEstado(), false));
    }

    public void guardarAntesDeExcluir(Atividade atividade) {
        pilha.push(new Registro(atividade.salvarEstado(), true));
    }

    public boolean podeDesfazer() {
        return !pilha.isEmpty();
    }

    public boolean desfazer() {
        if (pilha.isEmpty()) {
            return false;
        }

        Registro registro = pilha.pop();
        Atividade restaurada = Atividade.restaurarDe(registro.memento());

        if (registro.eraExclusao()) {
            boolean idOcupado = ArquivoAtividade.lerLista().stream()
                    .anyMatch(a -> a.getId() == restaurada.getId());
            if (idOcupado) {
                restaurada.setId(Atividade.descobrirIdLivre(ArquivoAtividade.lerLista()));
            }
            ArquivoAtividade.adicionarAtividade(restaurada);
        } else {
            ArquivoAtividade.atualizarAtividade(restaurada);
        }
        return true;
    }
}

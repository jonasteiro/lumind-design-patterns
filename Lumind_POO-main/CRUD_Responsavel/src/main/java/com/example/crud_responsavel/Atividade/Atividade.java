package com.example.crud_responsavel.Atividade;

import java.io.Serializable;
import java.time.LocalDate;

public class Atividade implements Serializable {

    private static final long serialVersionUID = -5467296328993793980L;

    private int id;

    private String titulo;

    private LocalDate data;

    private String descricao;

    private String categoria;

    public Atividade(int id, String titulo, LocalDate data, String descricao, String categoria) {
        this.id = id;
        this.titulo = titulo;
        this.data = data;
        this.descricao = descricao;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public static int descobrirIdLivre(java.util.ArrayList<Atividade> listaCadastradas) {
        int idTentativa = 1;
        boolean idLivre = false;

        while (!idLivre) {
            boolean idJaExiste = false;

            for (Atividade a : listaCadastradas) {
                if (a.getId() == idTentativa) {
                    idJaExiste = true;
                    break;
                }
            }

            if (idJaExiste) {
                idTentativa++;
            } else {
                idLivre = true;
            }
        }

        return idTentativa;
    }

    public Memento salvarEstado() {
        return new Memento(id, titulo, data, descricao, categoria);
    }

    public static Atividade restaurarDe(Memento memento) {
        return new Atividade(memento.id, memento.titulo, memento.data,
                memento.descricao, memento.categoria);
    }

    public static final class Memento {
        private final int id;
        private final String titulo;
        private final LocalDate data;
        private final String descricao;
        private final String categoria;

        private Memento(int id, String titulo, LocalDate data, String descricao, String categoria) {
            this.id = id;
            this.titulo = titulo;
            this.data = data;
            this.descricao = descricao;
            this.categoria = categoria;
        }
    }
}

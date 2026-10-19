package com.example.crud_responsavel.Relatorio;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Relatorio implements Serializable {

    private int id;

    private int idPessoaTea;

    private int idResponsavel;

    private String descricao;

    private LocalDate data;

    public Relatorio(int id, int idPessoaTea, int idResponsavel, String descricao, LocalDate data) {
        this.id = id;
        this.idPessoaTea = idPessoaTea;
        this.idResponsavel = idResponsavel;
        this.descricao = descricao;
        this.data = data;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getIdPessoaTea() {
        return idPessoaTea;
    }
    public void setIdPessoaTea(int idPessoaTea) {
        this.idPessoaTea = idPessoaTea;
    }

    public int getIdResponsavel() {
        return idResponsavel;
    }
    public void setIdResponsavel(int idResponsavel) {
        this.idResponsavel = idResponsavel;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }

    public static int descobrirIdLivre(ArrayList<Relatorio> listaCadastrados) {
        int idTentativa = 1;
        boolean idLivre = false;

        while (!idLivre) {
            boolean idJaExiste = false;

            for (Relatorio r : listaCadastrados) {
                if (r.getId() == idTentativa) {
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
}

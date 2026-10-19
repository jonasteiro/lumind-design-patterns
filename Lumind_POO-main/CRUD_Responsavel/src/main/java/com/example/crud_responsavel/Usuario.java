package com.example.crud_responsavel;
import java.io.Serializable;
import java.time.LocalDate;

public class Usuario implements Serializable {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private LocalDate dataNascimento;
    private String telefone;
    private Tipo tipo;

    public Usuario(int id, String nome, String email, String senha, String cpf, LocalDate dataNascimento, String telefone, Tipo tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int descobrirIdLivre(java.util.ArrayList<? extends Usuario> listaCadastrados) {
        int idTentativa = 1;
        boolean idLivre = false;

        while (!idLivre) {
            boolean idJaExiste = false;

            for (Usuario u : listaCadastrados) {
                if (u.getId() == idTentativa) {
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

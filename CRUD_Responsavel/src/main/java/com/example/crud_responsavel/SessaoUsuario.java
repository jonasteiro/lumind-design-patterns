package com.example.crud_responsavel;

public class SessaoUsuario {

    private static final SessaoUsuario INSTANCIA = new SessaoUsuario();

    private Usuario usuarioLogado;

    private SessaoUsuario() {
    }

    public static SessaoUsuario getInstancia() {
        return INSTANCIA;
    }

    public void iniciar(Usuario usuario) {
        this.usuarioLogado = usuario;
    }

    public void encerrar() {
        this.usuarioLogado = null;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }
}

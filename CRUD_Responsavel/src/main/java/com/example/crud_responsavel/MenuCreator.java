package com.example.crud_responsavel;

import com.example.crud_responsavel.Administrador.Controllers.MenuAdministradorController;
import com.example.crud_responsavel.PessoaTea.Controllers.MenuPessoaTeaController;
import com.example.crud_responsavel.Profissional.Controllers.MenuProfissionalController;
import com.example.crud_responsavel.Responsavel.Controllers.MenuResponsavelController;
import javafx.stage.Stage;

import java.util.EnumMap;
import java.util.Map;

public abstract class MenuCreator {

    protected abstract MenuUsuario criarMenu(Stage stage);

    public void abrirMenu(Stage stage) {
        MenuUsuario menu = criarMenu(stage);
        menu.mostrar();
    }

    private static final Map<Tipo, MenuCreator> CRIADORES = new EnumMap<>(Tipo.class);

    static {
        CRIADORES.put(Tipo.ADMINISTRADOR, new MenuAdministradorCreator());
        CRIADORES.put(Tipo.PROFISSIONAL_SAUDE, new MenuProfissionalCreator());
        CRIADORES.put(Tipo.RESPONSAVEL, new MenuResponsavelCreator());
        CRIADORES.put(Tipo.PESSOA_TEA, new MenuPessoaTeaCreator());
    }

    public static MenuCreator para(Tipo tipo) {
        return CRIADORES.get(tipo);
    }

    static class MenuAdministradorCreator extends MenuCreator {
        @Override
        protected MenuUsuario criarMenu(Stage stage) {
            return new MenuAdministradorController(stage);
        }
    }

    static class MenuProfissionalCreator extends MenuCreator {
        @Override
        protected MenuUsuario criarMenu(Stage stage) {
            return new MenuProfissionalController(stage);
        }
    }

    static class MenuResponsavelCreator extends MenuCreator {
        @Override
        protected MenuUsuario criarMenu(Stage stage) {
            return new MenuResponsavelController(stage);
        }
    }

    static class MenuPessoaTeaCreator extends MenuCreator {
        @Override
        protected MenuUsuario criarMenu(Stage stage) {
            return new MenuPessoaTeaController(stage);
        }
    }
}

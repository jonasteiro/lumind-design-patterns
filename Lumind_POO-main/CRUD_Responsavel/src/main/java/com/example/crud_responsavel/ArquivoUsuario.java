package com.example.crud_responsavel;

import java.io.*;
import java.util.ArrayList;

public class ArquivoUsuario {
    private static final String ARQUIVO = "usuarios.dat";

    public static ArrayList<Usuario> lerLista() {
        ArrayList<Usuario> lista = new ArrayList<>();
        File arq = new File(ARQUIVO);
        if (arq.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arq))) {
                lista = (ArrayList<Usuario>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Erro ao ler usuários: " + e.getMessage());
            }
        }
        return lista;
    }

    public static void salvarLista(ArrayList<Usuario> lista) {
        try {
            File arq = new File(ARQUIVO);
            if (!arq.exists()) {
                arq.createNewFile();
            }
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arq))) {
                oos.writeObject(lista);
            }
        } catch (IOException e) {
            System.err.println("Erro crítico ao salvar usuários: " + e.getMessage());
        }
    }

    public static void adicionarUsuario(Usuario novoUsuario) {
        ArrayList<Usuario> lista = lerLista();
        lista.add(novoUsuario);
        salvarLista(lista);
    }

    public static boolean atualizarUsuario(Usuario usuarioAtualizado) {
        ArrayList<Usuario> lista = lerLista();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == usuarioAtualizado.getId()) {
                lista.set(i, usuarioAtualizado);
                salvarLista(lista);
                return true;
            }
        }
        return false;
    }

    public static boolean excluirUsuario(int idParaExcluir) {
        ArrayList<Usuario> lista = lerLista();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == idParaExcluir) {
                lista.remove(i);
                salvarLista(lista);
                return true;
            }
        }
        return false;
    }
}

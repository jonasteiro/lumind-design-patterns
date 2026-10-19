package com.example.crud_responsavel.Atividade;

import com.example.crud_responsavel.Observador;

import java.io.*;
import java.util.ArrayList;

public class ArquivoAtividade {

    private static final String CAMINHO_ARQUIVO = "Atividade.dat";

    private static final ArrayList<Observador> observadores = new ArrayList<>();

    public static void adicionarObservador(Observador observador) {
        if (!observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    public static void removerObservador(Observador observador) {
        observadores.remove(observador);
    }

    private static void notificarObservadores() {
        for (Observador observador : new ArrayList<>(observadores)) {
            observador.atualizar();
        }
    }

    public static void salvarLista(ArrayList<Atividade> atividades) {
        try {
            File arq = new File(CAMINHO_ARQUIVO);
            if (!arq.exists()) {
                arq.createNewFile();
            }
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arq));
            oos.writeObject(atividades);
            oos.close();

            notificarObservadores();

        } catch (FileNotFoundException e) {
            System.err.println("Erro ao salvar lista: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro ao salvar lista: " + e.getMessage());
        }
    }

    public static void adicionarAtividade(Atividade atividade) {
        ArrayList<Atividade> atividades = lerLista();
        atividades.add(atividade);
        salvarLista(atividades);
    }

    public static ArrayList<Atividade> lerLista() {
        ArrayList<Atividade> lista = new ArrayList<>();
        try {
            File arq = new File(CAMINHO_ARQUIVO);
            if (arq.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CAMINHO_ARQUIVO));
                lista = (ArrayList<Atividade>) ois.readObject();
                ois.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao ler lista: " + e.getMessage());
        }
        return lista;
    }

    public static boolean atualizarAtividade(Atividade atividadeAtualizada) {
        ArrayList<Atividade> lista = lerLista();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == atividadeAtualizada.getId()) {
                lista.set(i, atividadeAtualizada);
                salvarLista(lista);
                return true;
            }
        }
        return false;
    }

    public static boolean excluirAtividade(int idParaExcluir) {
        ArrayList<Atividade> lista = lerLista();

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

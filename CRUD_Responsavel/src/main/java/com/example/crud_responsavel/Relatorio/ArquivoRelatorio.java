package com.example.crud_responsavel.Relatorio;

import java.io.*;
import java.util.ArrayList;

public class ArquivoRelatorio {

    private static final String CAMINHO_ARQUIVO = "Relatorio.dat";

    public static void salvarLista(ArrayList<Relatorio> relatorios) {
        try {
            File arq = new File(CAMINHO_ARQUIVO);
            if (!arq.exists()) {
                arq.createNewFile();
            }
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arq));
            oos.writeObject(relatorios);
            oos.close();

        } catch (FileNotFoundException e) {
            System.err.println("Erro ao salvar lista: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro ao salvar lista: " + e.getMessage());
        }
    }

    public static void adicionarRelatorio(Relatorio relatorio) {
        ArrayList<Relatorio> relatorios = lerLista();
        relatorios.add(relatorio);
        salvarLista(relatorios);
    }

    public static ArrayList<Relatorio> lerLista() {
        ArrayList<Relatorio> lista = new ArrayList<>();
        try {
            File arq = new File(CAMINHO_ARQUIVO);
            if (arq.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CAMINHO_ARQUIVO));
                lista = (ArrayList<Relatorio>) ois.readObject();
                ois.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao ler lista: " + e.getMessage());
        }
        return lista;
    }

    public static boolean atualizarRelatorio(Relatorio relatorioAtualizado) {
        ArrayList<Relatorio> lista = lerLista();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == relatorioAtualizado.getId()) {
                lista.set(i, relatorioAtualizado);
                salvarLista(lista);
                return true;
            }
        }
        return false;
    }

    public static boolean excluirRelatorio(int idParaExcluir) {
        ArrayList<Relatorio> lista = lerLista();

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

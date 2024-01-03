package sousa.ricardo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Filme {
    String nome;
    String genero;
    String produtora;
    int ano;
    int classificacao;
    static HashMap<String, List<Integer>> classificoes = new HashMap<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getProdutora() {
        return produtora;
    }

    public void setProdutora(String produtora) {
        this.produtora = produtora;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }




    public Filme(String nome, String genero, String produtora, int ano) {
        this.nome = nome;
        this.genero = genero;
        this.produtora = produtora;
        this.ano = ano;
    }
    public Filme(String nome){
        this.nome = nome;
    }

    public void adicionarClassificacao(int classificacao){
        List<Integer> classificacoFIlme = classificoes.getOrDefault(nome,new ArrayList<>());
        classificacoFIlme.add(classificacao);
        System.out.println(classificacoFIlme);
        classificoes.put(this.nome, classificacoFIlme);
    }

    public double getMediaClassificacoes(String nome){
        int somaNotas = 0;
        if (classificoes.containsKey(nome)){
            List<Integer> notaClassi = classificoes.get(nome);
            int notasTotal = notaClassi.size();


            for (int nota : notaClassi){
                somaNotas += nota;
            }
            return (double) somaNotas / notasTotal;
        }else {
            return 0;
        }

    }


}



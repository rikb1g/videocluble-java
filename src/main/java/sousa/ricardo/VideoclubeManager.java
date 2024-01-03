package sousa.ricardo;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VideoclubeManager {
    HashMap<String, Filme> filmes = new HashMap<>();

    String nome, produtora,genero,escolha;
    int ano, resposta, resposta2;
    public VideoclubeManager(){
        this.filmes = new HashMap<>();

    }

    void InserirFilmes(){
        int menu = 0;


        while (menu!=1) {
            nome = JOptionPane.showInputDialog(null, "Nome do filme");
            ano = Integer.parseInt(JOptionPane.showInputDialog(null, "Ano do filme"));
            produtora = JOptionPane.showInputDialog(null, "Produtora do filme ");
            genero = JOptionPane.showInputDialog(null, "Gênero do filme");
            resposta = JOptionPane.showConfirmDialog(null, "Confirma os dados intruduzidos: \n\n" +
                    "Nome:  " + nome +
                    "\nAno: " + ano +
                    "\nProdutora: " + produtora +
                    "\nGênero: " + genero +
                    "\n", "Confirme os dados introduzidos.", JOptionPane.YES_NO_OPTION);

            if (resposta == JOptionPane.YES_OPTION) {
                Filme novofilme = new Filme(nome, genero, produtora, ano);
                if (filmes.containsKey(nome)) {
                    JOptionPane.showMessageDialog(null, "Filme ja existente.\n Não foi possivel adicionar o filme", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "O filme " + nome + " foi adicionado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    filmes.put(nome, novofilme);
                    menu = 1;
                }
            } else if (resposta == JOptionPane.NO_OPTION) {
                resposta2 = JOptionPane.showConfirmDialog(null, "Desejas volta a inserir filme", "Inserir novamente?", JOptionPane.YES_NO_OPTION);
                if (resposta2 == JOptionPane.YES_OPTION) {
                }else {
                    menu = 1;
                }
            }
        }
    }

    void consultarFilmes(){
        int menu = 0,pergunta;
        String chave;
        do{
            chave = JOptionPane.showInputDialog(null,"Indica o nome do filme que deseja procurar","Procurar filmes",JOptionPane.QUESTION_MESSAGE);
            if (!filmes.containsKey(chave)){
                JOptionPane.showMessageDialog(null,"O filme não existe", "Erro",JOptionPane.ERROR_MESSAGE);
            }else {
                mostrarConsulta(chave);
            }
            pergunta = JOptionPane.showConfirmDialog(null, "Voltar a procurar?", "Inserir novamente?", JOptionPane.YES_NO_OPTION);
            if (pergunta == JOptionPane.NO_OPTION){
                menu = 1;
            }
        }while (menu != 1);
    }

    private void mostrarConsulta(String chave){
        nome = chave;
        ano = filmes.get(nome).getAno();
        produtora = filmes.get(nome).getProdutora();
        genero = filmes.get(nome).getGenero();
        Filme filmeConsulta = new Filme(nome,genero,produtora,ano);
        double media = filmeConsulta.getMediaClassificacoes(nome);
        JOptionPane.showMessageDialog(null,"O filme tem as seguintes caracteristicas \n" +
                "\nNome: " +nome+
                "\nAno: "+ano+
                "\nProdutora "+produtora+
                "\nGênero: "+genero+
                "\nPontuação média: "+media+" .","Sucesso",JOptionPane.INFORMATION_MESSAGE);
    }

    void atualizarFilme(){
        Object [] nomeFilmes = filmes.keySet().toArray();
        int menu = 0;
        do {
            if (!filmes.isEmpty()) {
                escolha = (String) JOptionPane.showInputDialog(null, "Escolhe o filme a atualizar",
                        "Atualizar Filme", JOptionPane.QUESTION_MESSAGE, null, nomeFilmes, nomeFilmes[0]);
                JOptionPane.showMessageDialog(null, "Escolheu o filme " + escolha, "Escolha", JOptionPane.INFORMATION_MESSAGE);
                mostrarConsulta(escolha);
                menuAtualizarFilme(escolha);
                menu = 1;




            } else {
                JOptionPane.showMessageDialog(null, "Lista de filmes vazio. Adiciona primeiro um filme no menu inicial", "Erro", JOptionPane.ERROR_MESSAGE);
                menu= 1;
            }

        }while (menu != 1);
    }

    private void menuAtualizarFilme(String nomeFilme){
        Object [] menuAtualizarFilmes = {"Nome", "Ano","Produtora","Gênero","Sair"};
        String novoNome, novoGenero, novaProdutora;
        int novoAno;
        genero = filmes.get(nomeFilme).getGenero();
        produtora = filmes.get(nomeFilme).getProdutora();
        ano = filmes.get(nomeFilme).getAno();

        Filme filmeExistente = new Filme(nomeFilme,genero,produtora,ano);

        int opcao = 0;
        do {
            opcao = JOptionPane.showOptionDialog(null,"Que característica desejas atualizar","Atualizar",0,JOptionPane.QUESTION_MESSAGE,null,menuAtualizarFilmes,menuAtualizarFilmes[0]);
            switch (opcao){
                case 0:
                    novoNome = JOptionPane.showInputDialog(null,"Qual o novo nome que desejas atribuir ao filme "+nomeFilme,"Atualizar Nome",JOptionPane.QUESTION_MESSAGE);
                    filmeExistente.setNome(novoNome);
                    filmes.put(nomeFilme,filmeExistente);
                    break;
                case 1:
                    novoAno = Integer.parseInt(JOptionPane.showInputDialog(null,"Qual o novo Ano que desejas atribuir ao filme "+nomeFilme,"Atualizar Ano",JOptionPane.QUESTION_MESSAGE));
                    filmeExistente.setAno(novoAno);
                    filmes.put(nomeFilme,filmeExistente);
                    break;
                case 2:
                    novaProdutora = JOptionPane.showInputDialog(null,"Qual a nova produtora que desejas atribuir ao filme "+nomeFilme,"Atualizar Produtora",JOptionPane.QUESTION_MESSAGE);
                    filmeExistente.setProdutora(novaProdutora);
                    filmes.put(nomeFilme,filmeExistente);
                    break;
                case 3:
                    novoGenero = JOptionPane.showInputDialog(null,"Qual a nova gênero que desejas atribuir ao filme "+nomeFilme,"Atualizar Gênero",JOptionPane.QUESTION_MESSAGE);
                    filmeExistente.setGenero(novoGenero);
                    filmes.put(nomeFilme,filmeExistente);
                    break;
                case 4:
                    break;
            }

        }while(opcao != 4);
    }


    void eliminarFilme(){
        Object [] nomeFilmes = filmes.keySet().toArray();

        int menu = 0;

        do{
            if (!filmes.isEmpty()){
                escolha = (String)JOptionPane.showInputDialog(null,"Seleciona o filme que pretendes eliminar.","Eliminar filmes",JOptionPane.QUESTION_MESSAGE,null,nomeFilmes,nomeFilmes[0]);
                resposta = JOptionPane.showConfirmDialog(null,"Foi selecionado o filme "+escolha+". \nConfirmar que pretende eliminar este filme?","Eliminar Filme",JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION){
                    filmes.remove(escolha);
                    JOptionPane.showMessageDialog(null,"O filme "+escolha+" foi eliminado com sucesso!","Sucesso",JOptionPane.INFORMATION_MESSAGE);
                    menu = 1;
                }else {
                    JOptionPane.showMessageDialog(null,"Filme não eliminado.", "Erro",JOptionPane.INFORMATION_MESSAGE);
                    menu = 1;
                }



            }else {
                JOptionPane.showMessageDialog(null, "Lista de filmes vazio. Adiciona primeiro um filme no menu inicial", "Erro", JOptionPane.ERROR_MESSAGE);
                menu= 1;
            }

        }while (menu !=1);

    }

    void avaliarFilme(){
        Object [] nomeFilmes = filmes.keySet().toArray();
        int menu = 0;
        String chave;
        Object [] menuAvaliarFilme = {"Procurar pelo nome","Procurar pela lista de filmes"};

        do {
            if (!filmes.isEmpty()){
                resposta = JOptionPane.showOptionDialog(null,"Escolhe a opção pretendida","Avaliar Filme",0,JOptionPane.QUESTION_MESSAGE,null,menuAvaliarFilme,menuAvaliarFilme[0]);
                if (resposta == 0){
                    chave = JOptionPane.showInputDialog(null,"Indica o nome do filme a consultar",JOptionPane.QUESTION_MESSAGE);
                    if (filmes.containsKey(chave)){
                        JOptionPane.showMessageDialog(null,"Filme "+ chave+" encontrado. ", "Avaliar Filme",JOptionPane.QUESTION_MESSAGE);
                        darNotaFilme(chave);
                        menu  = 1;
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"O filme não existe", "Erro",JOptionPane.ERROR_MESSAGE);
                    }
                }else if (resposta ==1 ){
                    escolha = (String)JOptionPane.showInputDialog(null,"Escolhe o filme para avaliar","Avaliar Filme",JOptionPane.QUESTION_MESSAGE,null,nomeFilmes, nomeFilmes[0]);
                    darNotaFilme(escolha);
                    menu = 1;
                }

            }else{
                JOptionPane.showMessageDialog(null, "Lista de filmes vazio. Adiciona primeiro um filme no menu inicial", "Erro", JOptionPane.ERROR_MESSAGE);
                menu= 1;
            }

        }while (menu != 1);


    }

    private void darNotaFilme(String nomeFilme){
            Filme darNotaFilme = new Filme(nomeFilme);
            int nota;
            Object [] escalaAvaliacao = {0,1,2,3,4,5,6,7,8,9,10};
            nota = (Integer)JOptionPane.showInputDialog(null, "Com que notas pretendes avaliar o filme "+nomeFilme+".","Avaliar Filme",JOptionPane.QUESTION_MESSAGE,null,escalaAvaliacao,escalaAvaliacao[0]);
            darNotaFilme.adicionarClassificacao(nota);
            JOptionPane.showMessageDialog(null,"O filme "+nomeFilme+" recebeu a classificação de "+nota+".","Avaliar Filme",JOptionPane.INFORMATION_MESSAGE);


    }

}

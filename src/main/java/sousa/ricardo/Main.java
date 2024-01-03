package sousa.ricardo;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        VideoclubeManager v1 = new VideoclubeManager();
        int opcao = 0;
        do {
            Object [] menu = {"Inserir", "Consultar","Atualizar","Avaliar Filme","Eliminar", "Sair"};

            opcao = JOptionPane.showOptionDialog(null,"Bem vindo! Escolhe a opção","VideoCLube",0,JOptionPane.QUESTION_MESSAGE,null,menu,menu[0]);
            switch (opcao){
                case 0 :
                    v1.InserirFilmes();
                    break;
                case 1:
                    v1.consultarFilmes();
                    break;
                case 2:
                    v1.atualizarFilme();
                    break;
                case 3:
                    v1.avaliarFilme();
                    break;
                case 4:
                    v1.eliminarFilme();
                    break;
                case 5:
                    break;
            }

        }while (opcao != 5);




    }
}
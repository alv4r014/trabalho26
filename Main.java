import java.util.Scanner;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;

// Classe para simular o comportamento do navegador
class Navegador {
    private Stack<String> pilhaVoltar;
    private Stack<String> pilhaAvancar;
    private String paginaAtual;

    public Navegador() {
        pilhaVoltar = new Stack<>();
        pilhaAvancar = new Stack<>();
        paginaAtual = null;
    }

    public void abrirPagina(String url) {
        if (paginaAtual != null) {
            pilhaVoltar.push(paginaAtual);
        }
        paginaAtual = url;
        pilhaAvancar.clear();
        System.out.println("Página atual: " + paginaAtual);
    }

    public void voltar() {
        if (!pilhaVoltar.isEmpty()) {
            pilhaAvancar.push(paginaAtual);
            paginaAtual = pilhaVoltar.pop();
            System.out.println("Voltando para: " + paginaAtual);
        } else {
            System.out.println("Não há páginas para voltar.");
        }
    }

    public void avancar() {
        if (!pilhaAvancar.isEmpty()) {
            pilhaVoltar.push(paginaAtual);
            paginaAtual = pilhaAvancar.pop();
            System.out.println("Avançando para: " + paginaAtual);
        } else {
            System.out.println("Não há páginas para avançar.");
        }
    }
}

// Classe para o sistema de senhas do hospital
class GerenciadorSenhas {
    private Queue<Integer> fila;
    private int contador;
    private Queue<Integer> historicoChamadas;

    public GerenciadorSenhas() {
        fila = new LinkedList<>();
        historicoChamadas = new LinkedList<>();
        contador = 1;
    }

    public void gerarSenha() {
        fila.add(contador);
        System.out.println("Senha gerada: " + contador);
        contador++;
    }

    public void chamarProximo() {
        if (!fila.isEmpty()) {
            int senhaChamada = fila.poll();
            historicoChamadas.add(senhaChamada);
            System.out.println("Chamando senha: " + senhaChamada);
        } else {
            System.out.println("Não há senhas na fila.");
        }
    }

    public boolean existeAlguemNaFila() {
        return !fila.isEmpty();
    }

    public void exibirHistoricoChamadas() {
        System.out.println("Histórico de chamadas: " + historicoChamadas);
    }
}

// Classe principal para testar as funcionalidades
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciadorSenhas gerenciador = new GerenciadorSenhas();
        
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1 - Gerar senha");
            System.out.println("2 - Chamar próximo");
            System.out.println("3 - Exibir histórico de chamadas");
            System.out.println("4 - Sair");
            System.out.print("Escolha uma opção: ");
            
            int escolha = -1;
            
            // Tratar exceções de entrada
            try {
                escolha = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                continue;
            }
            
            switch (escolha) {
                case 1:
                    gerenciador.gerarSenha();
                    break;
                case 2:
                    gerenciador.chamarProximo();
                    break;
                case 3:
                    gerenciador.exibirHistoricoChamadas();
                    break;
                case 4:
                    System.out.println("Encerrando o programa...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }
}

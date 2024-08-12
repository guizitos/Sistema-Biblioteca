package aplicacao;

import DAO.LivroService;
import entity.Livro;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static LivroService livroService = new LivroService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int option;
        do {
            System.out.println("Menu:");
            System.out.println("1. Adicionar Livro");
            System.out.println("2. Editar Livro");
            System.out.println("3. Excluir Livro");
            System.out.println("4. Consultar Livro");
            System.out.println("5. Listar Todos os Livros");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            option = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (option) {
                case 1:
                    adicionarLivro();
                    break;
                case 2:
                    editarLivro();
                    break;
                case 3:
                    excluirLivro();
                    break;
                case 4:
                    consultarLivro();
                    break;
                case 5:
                    listarTodosLivros();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (option != 0);
        
        scanner.close();
    }

    private static void adicionarLivro() {
        System.out.println("Adicionando um novo livro.");

        System.out.print("Digite o código do livro: ");
        int codigoLivro = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Digite o título do livro: ");
        String titulo = scanner.nextLine();

        System.out.print("Digite o autor do livro: ");
        String autor = scanner.nextLine();

        System.out.print("Digite o ano de lançamento do livro: ");
        int anoLancamento = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Digite a editora do livro: ");
        String editora = scanner.nextLine();

        Livro livro = new Livro(codigoLivro, titulo, autor, anoLancamento, editora);
        livroService.salvar(livro);

        System.out.println("Livro adicionado com sucesso!");
    }

    private static void editarLivro() {
        System.out.println("Editando um livro.");
    
        System.out.print("Digite o código do livro: ");
        int codigoLivro = scanner.nextInt();
        scanner.nextLine(); 
    
        Livro livro = livroService.consultar(codigoLivro);
    
        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }
    
        System.out.print("Digite o novo título do livro (deixe em branco para manter o atual): ");
        String titulo = scanner.nextLine();
        if (!titulo.isEmpty()) livro.setTitulo(titulo);
    
        System.out.print("Digite o novo autor do livro (deixe em branco para manter o atual): ");
        String autor = scanner.nextLine();
        if (!autor.isEmpty()) livro.setAutor(autor);
    
        System.out.print("Digite o novo ano de lançamento do livro (deixe em branco para manter o atual): ");
        String anoLancamento = scanner.nextLine();
        if (!anoLancamento.isEmpty()) livro.setAnoLancamento(Integer.parseInt(anoLancamento));
    
        System.out.print("Digite a nova editora do livro (deixe em branco para manter a atual): ");
        String editora = scanner.nextLine();
        if (!editora.isEmpty()) livro.setEditora(editora);
    
        livroService.editar(livro); 
    
        System.out.println("Livro atualizado com sucesso!");
    }

    private static void excluirLivro() {
        System.out.println("Excluindo um livro.");

        System.out.print("Digite o código do livro: ");
        int codigoLivro = scanner.nextInt();
        scanner.nextLine(); 

        livroService.excluir(codigoLivro);

        System.out.println("Livro excluído com sucesso!");
    }

    private static void consultarLivro() {
        System.out.println("Consultando um livro.");

        System.out.print("Digite o código do livro: ");
        int codigoLivro = scanner.nextInt();
        scanner.nextLine(); 

        Livro livro = livroService.consultar(codigoLivro);

        if (livro == null) {
            System.out.println("Livro não encontrado.");
        } else {
            System.out.println("Código: " + livro.getCodigoLivro());
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Autor: " + livro.getAutor());
            System.out.println("Ano de Lançamento: " + livro.getAnoLancamento());
            System.out.println("Editora: " + livro.getEditora());
        }
    }

    private static void listarTodosLivros() {
        System.out.println("Listando todos os livros.");

        List<Livro> livros = livroService.listarTodos();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
        } else {
            for (Livro livro : livros) {
                System.out.println("Código: " + livro.getCodigoLivro());
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autor: " + livro.getAutor());
                System.out.println("Ano de Lançamento: " + livro.getAnoLancamento());
                System.out.println("Editora: " + livro.getEditora());
                System.out.println("---------------------------");
            }
        }
    }
}

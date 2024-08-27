package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;
import entity.Livro;

public class LivroService {

    public void salvar(Livro livro) {
        String sql = "INSERT INTO Livros (codigo_livro, titulo, autor, ano_lancamento, editora) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, livro.getCodigoLivro());
            ps.setString(2, livro.getTitulo());
            ps.setString(3, livro.getAutor());
            ps.setInt(4, livro.getAnoLancamento());
            ps.setString(5, livro.getEditora());
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    public void editar(Livro livro) {
        String sql = "UPDATE Livros SET titulo = ?, autor = ?, ano_lancamento = ?, editora = ? WHERE codigo_livro = ?";
    
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAnoLancamento());
            stmt.setString(4, livro.getEditora());
            stmt.setInt(5, livro.getCodigoLivro());
    
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Livro atualizado com sucesso!");
            } else {
                System.out.println("Nenhum livro encontrado com esse código.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int codigoLivro) {
        String sql = "DELETE FROM Livros WHERE codigo_livro = ?";
    
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setInt(1, codigoLivro);
    
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Livro excluído com sucesso!");
            } else {
                System.out.println("Nenhum livro encontrado com esse código.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Livro consultar(int codigoLivro) {
        String sql = "SELECT * FROM Livros WHERE codigo_livro = ?";
    Livro livro = null;

    try (Connection conn = Conexao.getConexao();  
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, codigoLivro);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                livro = new Livro(0, sql, sql, 0, sql);
                livro.setCodigoLivro(rs.getInt("codigo_livro"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setAnoLancamento(rs.getInt("ano_lancamento"));
                livro.setEditora(rs.getString("editora"));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return livro;
    }

    public List<Livro> listarTodos() {
        String sql = "SELECT * FROM Livros";
    List<Livro> livros = new ArrayList<>();

    try (Connection conn = Conexao.getConexao(); 
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Livro livro = new Livro(0, sql, sql, 0, sql);
            livro.setCodigoLivro(rs.getInt("codigo_livro"));
            livro.setTitulo(rs.getString("titulo"));
            livro.setAutor(rs.getString("autor"));
            livro.setAnoLancamento(rs.getInt("ano_lancamento"));
            livro.setEditora(rs.getString("editora"));
            livros.add(livro);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return livros;
    }
}

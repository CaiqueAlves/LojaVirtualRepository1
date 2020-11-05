package lojaVirtualRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercaoComParametro {

	public static void main(String[] args) throws SQLException {
		String nome = "Mouse";
		String descricao = "Ergonomico e gamer produzido em Manaus-Amazonas kkk";
		
		ConnectionFactory factory = new ConnectionFactory();
		try(Connection connection = factory.recuperarConexao()){
			connection.setAutoCommit(false);
				try (PreparedStatement stm = connection.prepareStatement("INSERT INTO PRODUTO(nome, descricao) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);){		
					adicionarVariavel("SmartTV", "SmartTv samsung 4k de 58 polegadas", stm);
					adicionarVariavel("Playstation 5", "Novo console capaz de rodar jogos 4k, e ainda vem com PsPlus com 18 jogos", stm);
					connection.commit();
					stm.close();
					connection.close();
				}catch(Exception e) {
					e.printStackTrace();
					System.out.println("ROLLBACK EXECUTADO");
					connection.rollback();
				}
			}
		}

	private static void adicionarVariavel(String nome, String descricao, PreparedStatement stm) throws SQLException {
		stm.setString(1, nome);
		stm.setString(2, descricao);
		if(nome.equals("Playstation 5")) {
			throw new RuntimeException("Não foi possível adicionar produto");
		}
		stm.execute();
		
		try(ResultSet rst = stm.getGeneratedKeys()){
			while(rst.next()) {
				Integer id = rst.getInt(1);
				System.out.println("O id criado foi: " + id);
			}
		}
	}
		
}



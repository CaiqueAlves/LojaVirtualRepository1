package lojaVirtualRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaListagem {

	/**
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory criaConexao = new ConnectionFactory();
		Connection connection = criaConexao.recuperarConexao();
		
			PreparedStatement stm = connection.prepareStatement("SELECT ID, NOME, DESCRICAO FROM PRODUTO");
			stm.execute();
			ResultSet rst = stm.getResultSet();
			while(rst.next()) {
				Integer id = rst.getInt("ID");
				System.out.print(id+" - ");
				String nome = rst.getString("NOME");
				System.out.print(nome+" - ");
				String descricao = rst.getString("DESCRICAO");
				System.out.println(descricao);
			}
			connection.close();
	}

}

package org.springframework.samples.petclinic;


import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;




public class JDBCApplication {

	public static void main(String[] args) {
		System.out.println("-------- Test de conexión con MySQL ------------");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("No encuentro el driver en el Classpath");
			e.printStackTrace();
			return;
		}

			System.out.println("Driver instalado y funcionando");
			Connection connection = null;
			Statement statement = null;
			
			
		
			//Consulta
			try {
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/petclinic","root", "root");
				if (connection != null)
					System.out.println("Conexión establecida");
				
				    
					// Consultar elementos
					statement = connection.createStatement();
			        String sql = "SELECT * FROM owners";
			        ResultSet rs = statement.executeQuery(sql);
			        
			        
			        System.out.println("");
		            System.out.println("---------------------------------");
		            System.out.println("Consulta Owners original en DataBase");
		            System.out.println("---------------------------------");
		            
			        while(rs.next()) {
			        	
			        	int id = rs.getInt("id");
			            String firstName = rs.getString("first_name");
			            String lastName = rs.getString("last_name");
			            String address = rs.getString("address");
			            String city = rs.getString("city");
			            String telephone = rs.getString("telephone");
			        	
			            
			        	System.out.print("Id: " + id);
			            System.out.print(", Nombre: " + firstName);
			            System.out.print(", Apellidos: " + lastName);
			            System.out.print(", Dirección: " + address);
			            System.out.print(", Ciudad: " + city);
			            System.out.println(", Teléfono: " + telephone);
	
			        	
			        }
			        
			        
			        //Agregarnos a la DB
			        /*Statement statement2 = connection.createStatement();
			        String sqlYo = "INSERT INTO owners (first_name, last_name, address, city, telephone) "
			        + 
			        "VALUES ('Ivan', 'Rey', 'Calle false 1234', 'MiCiudad', '666666666')";
			        int numeroDeFilasModificadas = statement2.executeUpdate(sqlYo);*/
			        
			        
			        //Modificar ciudad
			        /*Statement statement3 = connection.createStatement();
			        String sql3 = "UPDATE owners "
			        		+"SET city= 'Sevilla'"
			        		+ "WHERE first_name= 'Ivan'";
			        int numeroFilasMoodificadas = statement3.executeUpdate(sql3);*/
			        
			        
			        //Select parametrizado
			        /*String sql = "SELECT * FROM owners WHERE first_name LIKE ? OR last_name LIKE ?";
			        String busqueda = "Da";
			        String termino = "%"+busqueda+"%";
			        PreparedStatement preparedStatement = connection.prepareStatement(sql);
			        preparedStatement.setString(1, termino);
			        preparedStatement.setString(2, termino);
			        ResultSet rs = preparedStatement.executeQuery();
			        while(rs.next()){
			                 int id = rs.getInt("id");
			                 String firstName = rs.getString("first_name");
			                 String lastName = rs.getString("last_name");
	
			                 System.out.print("Id: " + id);
			                 System.out.print(", Nombre: " + firstName);
			                 System.out.println(", Apellidos: " + lastName);
			        }
			        rs.close();*/
				
				
				//Nuevo owner
				/*String[] valores;
				valores = new String[] {"Marcos", "Ginel", "Mi casa", "Sevilla", "666666666"};
				String sql5 = "INSERT INTO owners (first_name, last_name, address, city, telephone)  VALUES(?,?,?,?,?)";
	
				PreparedStatement preparedStatement = connection.prepareStatement(sql5);
				for(int i=0; i < valores.length; i++)
				   preparedStatement.setString(i+1, valores[i]);
	
				int numeroDeFilasModificadas = preparedStatement.executeUpdate();*/
			        
		        
		        //Agregar nuevo owner
		        String[] persona = new String [] {"Ivan Jose", "del Rey", "Calle Larga 1234", "Salamanca", "666766869"};
			        
				String sql6 = "INSERT INTO owners (first_name, last_name, address, city, telephone) "
						+ "VALUES(?,?,?,?,?)";
		        
		        PreparedStatement preparedStatement = connection.prepareStatement(sql6);
				for(int i=0; i < persona.length; i++)
				   preparedStatement.setString(i+1, persona[i]);
				
		        int numeroDeFilasModificadas = preparedStatement.executeUpdate();
		        
		        
		        
		     // Consultar elementos-------------------------------------------------------------
				statement = connection.createStatement();
		        String sqlR2 = "SELECT * FROM owners";
		        ResultSet rsR2 = statement.executeQuery(sqlR2);
		        
		        System.out.println("");
	            System.out.println("---------------------------------");
	            System.out.println("Consulta Owners agregado owner");
	            System.out.println("---------------------------------");
		        
		        while(rsR2.next()) {
		        	
		        	int id = rsR2.getInt("id");
		            String firstName = rsR2.getString("first_name");
		            String lastName = rsR2.getString("last_name");
		            String address = rsR2.getString("address");
		            String city = rsR2.getString("city");
		            String telephone = rsR2.getString("telephone");
		        	
		            
		        	System.out.print("Id: " + id);
		            System.out.print(", Nombre: " + firstName);
		            System.out.print(", Apellidos: " + lastName);
		            System.out.print(", Dirección: " + address);
		            System.out.print(", Ciudad: " + city);
		            System.out.println(", Teléfono: " + telephone);
		        }
		       //---------------------------------------------------------------------------------- 
		        
		        //Agregar mascota mia
		        /*ResultSet rsOwner = statement.executeQuery(sql);
		        int id_owner = rsOwner.getInt("id");
		        
			  
		        //String sql = "SELECT * FROM owners WHERE first_name=
					        
		        Statement statement7 = connection.createStatement();        
		        String sql7 = "INSERT INTO pets (name, birth_date, type_id, owner_id)"
			    		+ "VALUES ('Tobi', '2018-7-20', 2, ?)";
		        PreparedStatement preparedStatement7 = connection.prepareStatement(sql7);
	
		        preparedStatement.setInt(4, id_owner);*/
				
	
		        //Borrar mis datos
		       
		        String sqlRemoveYo = "DELETE FROM owners WHERE first_name= ?";
		        PreparedStatement preparedStatementRemoveYo = connection.prepareStatement(sqlRemoveYo);
		        preparedStatementRemoveYo.setString(1, "Ivan Jose");
		        //preparedStatementRemoveYo.setString(2, "del Rey");
		        

	            int row = preparedStatementRemoveYo.executeUpdate();
	            System.out.println(row);

				
		        
		     // Consultar elementos-------------------------------------------------------------
				statement = connection.createStatement();
		        String sqlR3 = "SELECT * FROM owners";
		        ResultSet rsR3 = statement.executeQuery(sqlR3);
		        
		        
		        System.out.println("");
	            System.out.println("---------------------------------");
	            System.out.println("Consulta Owners borrado owner");
	            System.out.println("---------------------------------");
		        
		        while(rsR3.next()) {
		        	
		        	int id = rsR3.getInt("id");
		            String firstName = rsR3.getString("first_name");
		            String lastName = rsR3.getString("last_name");
		            String address = rsR3.getString("address");
		            String city = rsR3.getString("city");
		            String telephone = rsR3.getString("telephone");
		        	
		            System.out.print("Id: " + id);
		            System.out.print(", Nombre: " + firstName);
		            System.out.print(", Apellidos: " + lastName);
		            System.out.print(", Dirección: " + address);
		            System.out.print(", Ciudad: " + city);
		            System.out.println(", Teléfono: " + telephone);
		        }
		       //---------------------------------------------------------------------------------- 
		        
		        
		        
		        
		
			
			} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		} finally {
			try {
				if(statement != null)
					connection.close();
			} catch (SQLException se) {
		    	  
		    }
		    try {
		        if(connection != null)
		            connection.close();
		    } catch (SQLException se) {
		         	se.printStackTrace();
		    }
		}
		
		
		
		
		
		
	}

}

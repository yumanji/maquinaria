package com.mttb.spiders;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MySQLAccess {
  private Connection connect = null;
  private Statement statement = null;
  private PreparedStatement preparedStatement = null;
  private ResultSet resultSet = null;

  public void connectDataBase() throws Exception {
    try {
      // This will load the MySQL driver, each DB has its own driver
      Class.forName("com.mysql.jdbc.Driver");
      // Setup the connection with the DB
      connect = DriverManager
          .getConnection("jdbc:mysql://localhost/maquinaria?"
              + "user=root&password=root");
   	
    } catch (Exception e) {
        throw e;
      } 
  }

  public void createCategory( Map<String, String> categoria) throws Exception {
	    try {
	    	
	    	if(connect == null) connectDataBase();
	    	
	        // Statements allow to issue SQL queries to the database
	        statement = connect.createStatement();
	        // Result set get the result of the SQL query
	/*        resultSet = statement
	            .executeQuery("select * from maquinaria.category_links");
	        writeResultSet(resultSet);
	*/
	        /* ####################
	         * ####################### AQUI ESTOY
	         */
	        // PreparedStatements can use variables and are more efficient
	        preparedStatement = connect
	            .prepareStatement("insert into  maquinaria.category_links (category_level, id_original, id_parent, category_url) values (?, ?, ?, ?)");
	        // "myuser, webpage, datum, summery, COMMENTS from feedback.comments");
	        // Parameters start with 1
	        preparedStatement.setString(1, categoria.get("level").toString());
	        preparedStatement.setString(2, categoria.get("id").toString());
	        preparedStatement.setString(3, categoria.get("parent").toString());
	        preparedStatement.setString(4, categoria.get("url").toString());
	        System.out.println(preparedStatement);
	        preparedStatement.executeUpdate();  	
	    } catch (Exception e) {
	        throw e;
	    } finally {
	    	statementClose();
	    }
	  }


	  public void createCategory( Categoria categoria) throws Exception {
	    try {
	    	
	    	if(connect == null) connectDataBase();
	    	
	        // Statements allow to issue SQL queries to the database
	        statement = connect.createStatement();
	        // Result set get the result of the SQL query
	/*        resultSet = statement
	            .executeQuery("select * from maquinaria.category_links");
	        writeResultSet(resultSet);
	*/
	        /* ####################
	         * ####################### AQUI ESTOY
	         */
	        // PreparedStatements can use variables and are more efficient
	        preparedStatement = connect
	            .prepareStatement("insert into  maquinaria.category_links (category_level, id_original, id_parent, category_url, es_final) values (?, ?, ?, ?, ?)");
	        // "myuser, webpage, datum, summery, COMMENTS from feedback.comments");
	        // Parameters start with 1
	        preparedStatement.setInt(1, categoria.getCategory_level());
	        preparedStatement.setString(2, categoria.getId_original());
	        preparedStatement.setInt(3, categoria.getId_parent());
	        preparedStatement.setString(4, categoria.getCategory_url());
	        preparedStatement.setInt(5, categoria.getEs_final());
	        System.out.println(preparedStatement);
	        preparedStatement.executeUpdate();  	
	    } catch (Exception e) {
	        throw e;
	    } finally {
	    	statementClose();
	    }
	  }


	  public void updateCategory( Categoria categoria) throws Exception {
	    try {
	    	
	    	if(connect == null) connectDataBase();
	    	
	        // Statements allow to issue SQL queries to the database
	        statement = connect.createStatement();
	        // Result set get the result of the SQL query
	/*        resultSet = statement
	            .executeQuery("select * from maquinaria.category_links");
	        writeResultSet(resultSet);
	*/
	        /* ####################
	         * ####################### AQUI ESTOY
	         */
	        // PreparedStatements can use variables and are more efficient
	        preparedStatement = connect
	            .prepareStatement("update  maquinaria.category_links set category_level = ?, id_original = ?, id_parent = ?, category_url = ?, es_final = ?, last_review = ? WHERE id = ?");
	        // "myuser, webpage, datum, summery, COMMENTS from feedback.comments");
	        // Parameters start with 1
	        preparedStatement.setInt(1, categoria.getCategory_level());
	        preparedStatement.setString(2, categoria.getId_original());
	        preparedStatement.setInt(3, categoria.getId_parent());
	        preparedStatement.setString(4, categoria.getCategory_url());
	        preparedStatement.setInt(5, categoria.getEs_final());
	        preparedStatement.setDate(6, new java.sql.Date(new Date().getTime()));
	        preparedStatement.setInt(7, categoria.getId());
	        System.out.println(preparedStatement);
	        preparedStatement.executeUpdate();  	
	    } catch (Exception e) {
	        throw e;
	    } finally {
	    	statementClose();
	    }
	  }


	  public void reloadCategory( Categoria categoria) throws Exception {
	    try {
	    	
	    	if(connect == null) connectDataBase();
	    	
	        // Statements allow to issue SQL queries to the database
	        statement = connect.createStatement();
	        // Result set get the result of the SQL query
	        resultSet = statement
	            .executeQuery("select * from maquinaria.category_links where category_url = '".concat(categoria.getCategory_url()).concat("'"));
	        //writeResultSet(resultSet);

	        while (resultSet.next()) {
	            // It is possible to get the columns via name
	            // also possible to get the columns via the column number
	            // which starts at 1
	            // e.g. resultSet.getSTring(2);
	        	categoria.setId(resultSet.getInt("id"));
	        	categoria.setCategory_level(resultSet.getInt("category_level"));
	        	categoria.setId_original(resultSet.getString("id_original"));
	        	categoria.setId_parent(resultSet.getInt("id_parent"));
	        	categoria.setEs_final(resultSet.getInt("es_final"));
	        	categoria.setCategory_url(resultSet.getString("category_url"));
	        	categoria.setCreated(resultSet.getDate("created"));
	        	categoria.setLast_review(resultSet.getDate("last_review"));
	        	categoria.setDeleted(resultSet.getDate("deleted"));
	          } 	
	    } catch (Exception e) {
	        throw e;
	    } finally {
	    	statementClose();
	    }
	  }


	  
	  //###########################
	  //###########################
	  // ARTICULOS
	  //###########################
	  //###########################
	  
  public void createArticle( Article articulo) throws Exception {
    try {
    	
    	if(connect == null) connectDataBase();
    	
        // Statements allow to issue SQL queries to the database
        statement = connect.createStatement();
        // Result set get the result of the SQL query
/*        resultSet = statement
            .executeQuery("select * from maquinaria.category_links");
        writeResultSet(resultSet);
*/
        /* ####################
         * ####################### AQUI ESTOY
         */
        // PreparedStatements can use variables and are more efficient
        preparedStatement = connect
            .prepareStatement("INSERT INTO `maquinaria`.`articles_links` (`id_original`, `id_category`, `article_url`, `count`, `active`, `content`, `created`, `last_review`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        // "myuser, webpage, datum, summery, COMMENTS from feedback.comments");
        // Parameters start with 1
        preparedStatement.setString(1, articulo.getId_original());
        preparedStatement.setInt(2, articulo.getId_category());
        preparedStatement.setString(3, articulo.getArticle_url());
        preparedStatement.setInt(4, articulo.getCount());
        preparedStatement.setInt(5, articulo.getActive());
        preparedStatement.setString(6, articulo.getContent());
        preparedStatement.setDate(7, new java.sql.Date(new Date().getTime()));
        preparedStatement.setDate(8, new java.sql.Date(new Date().getTime()));
        //System.out.println(preparedStatement);
        preparedStatement.executeUpdate();  	
    } catch (Exception e) {
        throw e;
    } finally {
    	statementClose();
    }
  }

  public void updateArticle( Article articulo) throws Exception {
    try {
    	
    	if(connect == null) connectDataBase();
    	
        // Statements allow to issue SQL queries to the database
        statement = connect.createStatement();
        // Result set get the result of the SQL query
/*        resultSet = statement
            .executeQuery("select * from maquinaria.category_links");
        writeResultSet(resultSet);
*/
        /* ####################
         * ####################### AQUI ESTOY
         */
        // PreparedStatements can use variables and are more efficient
        preparedStatement = connect
            .prepareStatement("UPDATE `maquinaria`.`articles_links` SET `id_original` = ?, `id_category` = ?, `article_url` = ?, `count` = ?, `active` = ?, `content` = ?, `last_review` = ? WHERE `id` = ?;"); 
        // "myuser, webpage, datum, summery, COMMENTS from feedback.comments");
        // Parameters start with 1
        preparedStatement.setString(1, articulo.getId_original());
        preparedStatement.setInt(2, articulo.getId_category());
        preparedStatement.setString(3, articulo.getArticle_url());
        preparedStatement.setInt(4, articulo.getCount());
        preparedStatement.setInt(5, articulo.getActive());
        preparedStatement.setString(6, articulo.getContent());
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
        //preparedStatement.setDate(7, new java.sql.Date(new Date().getTime()));
         preparedStatement.setTimestamp(7, date);
       preparedStatement.setInt(8, articulo.getId());
        //System.out.println(preparedStatement);
        preparedStatement.executeUpdate();  	
    } catch (Exception e) {
        throw e;
    } finally {
    	statementClose();
    }
  }



  public void registerContent( Article articulo) throws Exception {
    try {
    	
    	if(connect == null) connectDataBase();
    	
        // Statements allow to issue SQL queries to the database
        statement = connect.createStatement();
        // Result set get the result of the SQL query
/*        resultSet = statement
            .executeQuery("select * from maquinaria.category_links");
        writeResultSet(resultSet);
*/
        /* ####################
         * ####################### AQUI ESTOY
         */
        // PreparedStatements can use variables and are more efficient
        preparedStatement = connect
                .prepareStatement("INSERT INTO `maquinaria`.`articles_links_data` (`id`, `id_original`, `content`, `fecha`) VALUES (?, ?, ?, ?)");
       // "myuser, webpage, datum, summery, COMMENTS from feedback.comments");
        // Parameters start with 1
        preparedStatement.setInt(1, articulo.getId());
        preparedStatement.setString(2, articulo.getId_original());
        preparedStatement.setString(3, articulo.getContent());
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
        //preparedStatement.setDate(7, new java.sql.Date(new Date().getTime()));
         preparedStatement.setTimestamp(4, date);
        System.out.println(preparedStatement);
        preparedStatement.executeUpdate();  	
    } catch (Exception e) {
        throw e;
    } finally {
    	statementClose();
    }
  }


  public void reloadArticle( Article articulo) throws Exception {
    try {
    	
    	if(connect == null) connectDataBase();
    	
        // Statements allow to issue SQL queries to the database
        statement = connect.createStatement();
        // Result set get the result of the SQL query
        resultSet = statement
            .executeQuery("select * from maquinaria.articles_links where article_url = '".concat(articulo.getArticle_url()).concat("'"));
        //writeResultSet(resultSet);

        while (resultSet.next()) {
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring(2);
        	articulo.setId(resultSet.getInt("id"));
        	articulo.setId_original(resultSet.getString("id_original"));
        	articulo.setId_category(resultSet.getInt("id_category"));
        	articulo.setActive(resultSet.getInt("active"));
        	articulo.setCount(resultSet.getInt("count"));
        	articulo.setArticle_url(resultSet.getString("article_url"));
        	articulo.setContent(resultSet.getString("content"));
        	articulo.setCreated(resultSet.getDate("created"));
        	articulo.setLast_review(resultSet.getDate("last_review"));
        	articulo.setDeleted(resultSet.getDate("deleted"));
          } 	
    } catch (Exception e) {
        throw e;
    } finally {
    	statementClose();
    }
  }


public List<Article> getArticles(int quantity) throws Exception  {
	// TODO Auto-generated method stub
	List<Article> listaArticulos =  new ArrayList<Article>();
	try {
		if(connect == null) connectDataBase();
	    statement = connect.createStatement();
	    // Result set get the result of the SQL query
	    resultSet = statement
	        .executeQuery("select * from maquinaria.articles_links where active = 1 order by last_review asc LIMIT 0, ".concat(Integer.toString(quantity)));
	    
	    
	    while (resultSet.next()) {
	        // It is possible to get the columns via name
	        // also possible to get the columns via the column number
	        // which starts at 1
	        // e.g. resultSet.getSTring(2);
	    	Article art = new Article();
	    	art.setActive(1);
	    	art.setContent(resultSet.getString("content"));
	    	art.setArticle_url(resultSet.getString("article_url"));
	    	art.setCount(resultSet.getInt("count"));
	    	art.setCreated(resultSet.getDate("created"));
	    	art.setId(resultSet.getInt("id"));
	    	art.setId_category(resultSet.getInt("id_category"));
	    	art.setId_original(resultSet.getString("id_original"));
	    	listaArticulos.add(art);
	        //art = null;
	    }
    } catch (Exception e) {
        throw e;
    } finally {
    	statementClose();
    }
    return listaArticulos;
}

  public void readDataBase() throws Exception {
    try {
      // Statements allow to issue SQL queries to the database
      statement = connect.createStatement();
      // Result set get the result of the SQL query
      resultSet = statement
          .executeQuery("select * from maquinaria.category_links");
      writeResultSet(resultSet);

      // PreparedStatements can use variables and are more efficient
      preparedStatement = connect
          .prepareStatement("insert into  feedback.comments values (default, ?, ?, ?, ? , ?, ?)");
      // "myuser, webpage, datum, summery, COMMENTS from feedback.comments");
      // Parameters start with 1
      preparedStatement.setString(1, "Test");
      preparedStatement.setString(2, "TestEmail");
      preparedStatement.setString(3, "TestWebpage");
      preparedStatement.setDate(4, new java.sql.Date(2009, 12, 11));
      preparedStatement.setString(5, "TestSummary");
      preparedStatement.setString(6, "TestComment");
      preparedStatement.executeUpdate();

      preparedStatement = connect
          .prepareStatement("SELECT myuser, webpage, datum, summery, COMMENTS from feedback.comments");
      resultSet = preparedStatement.executeQuery();
      writeResultSet(resultSet);

      // Remove again the insert comment
      preparedStatement = connect
      .prepareStatement("delete from feedback.comments where myuser= ? ; ");
      preparedStatement.setString(1, "Test");
      preparedStatement.executeUpdate();
      
      resultSet = statement
      .executeQuery("select * from feedback.comments");
      writeMetaData(resultSet);
      
    } catch (Exception e) {
      throw e;
    } finally {
      statementClose();
    }

  }

  private void writeMetaData(ResultSet resultSet) throws SQLException {
    //   Now get some metadata from the database
    // Result set get the result of the SQL query
    
    System.out.println("The columns in the table are: ");
    
    System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
    for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
      System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
    }
  }

  private void writeResultSet(ResultSet resultSet) throws SQLException {
    // ResultSet is initially before the first data set
    while (resultSet.next()) {
      // It is possible to get the columns via name
      // also possible to get the columns via the column number
      // which starts at 1
      // e.g. resultSet.getSTring(2);
      String user = resultSet.getString("myuser");
      String website = resultSet.getString("webpage");
      String summery = resultSet.getString("summery");
      Date date = resultSet.getDate("datum");
      String comment = resultSet.getString("comments");
      System.out.println("User: " + user);
      System.out.println("Website: " + website);
      System.out.println("Summery: " + summery);
      System.out.println("Date: " + date);
      System.out.println("Comment: " + comment);
    }
  }

  // You need to close the resultSet
  private void statementClose() {
    try {
      if (resultSet != null) {
        resultSet.close();
      }

      if (statement != null) {
        statement.close();
      }
    } catch (Exception e) {

    }
  }

  // You need to close the resultSet
  public void close() {
    try {
      if (resultSet != null) {
        resultSet.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connect != null) {
        connect.close();
      }
    } catch (Exception e) {

    }
  }

} 

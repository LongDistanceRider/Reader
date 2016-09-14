package ac.ait.k13114kk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SQLgetter {
	private String connectionURL;
	
	public SQLgetter(String connectionURL) {
		this.connectionURL = connectionURL;
	}
	/**
	 * クエリを渡すとデータベースから拾ってきて，内容を返します
	 * 
	 * @param クエリ
	 * @return データをリスト返し
	 */
	public ArrayList<String> databaseGetter(String sql){
		ArrayList<String> list = new ArrayList<String>();
		// ----- データベースへ接続
		try{
			Class.forName("org.sqlite.JDBC");
		} catch(ClassNotFoundException e){
			System.err.println(e);
		}
		Connection connection = null;
		Statement statement = null;
		int i = 0;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + connectionURL);
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
			
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
                list.add(rs.getString(1));
            }
		}catch(SQLException e) {
    		System.err.println("SQL Exception:[" + i + "] Message:" + e.getMessage());
    	} finally {
    		try {
    			if(statement != null) {
    				statement.close();
    			}
    		} catch (SQLException e) {
    			System.err.println(e);
    		}
    		try {
    			if (connection != null) {
    				connection.close();
    			}
    		} catch (SQLException e) {
    			System.err.println(e);
    		}
    	}
		return list;
	}
	/**
	 * クエリをデータベースへ流します
	 * 
	 * @param sqlArrayクエリ群
	 * @return 正常終了でtrue
	 */
	public static boolean jSQL(ArrayList<String> sqlArray) {
    	// ----- データベースへ接続
    	try{
    		Class.forName("org.sqlite.JDBC");
    	} catch (ClassNotFoundException e) {
    		System.err.println(e);
    	}
    	Connection connection = null;
    	Statement statement = null;
    	int i = 0;
    	try{
    		connection = DriverManager.getConnection("jdbc:sqlite:/Users/k13114kk/MorphologicalAnalysis/DataBase/jinrouG.db");
    		statement = connection.createStatement();
    		statement.setQueryTimeout(30);
    		
    		for (i = 0; i < sqlArray.size(); i++) {
    			statement.executeUpdate(sqlArray.get(i));		//レコード追加
			}
    	} catch(SQLException e) {
    		System.err.println("SQL Exception:[" + i + "] Message:" + e.getMessage());
    		System.err.println(sqlArray.get(i));
    	} finally {
    		try {
    			if(statement != null) {
    				statement.close();
    			}
    		} catch (SQLException e) {
    			System.err.println(e);
    		}
    		try {
    			if (connection != null) {
    				connection.close();
    			}
    		} catch (SQLException e) {
    			System.err.println(e);
    		}
    	}
		return true;
	}
	public static void resetDB() {
		// ----- データベースへ接続
    	try{
    		Class.forName("org.sqlite.JDBC");
    	} catch (ClassNotFoundException e) {
    		System.err.println(e);
    	}
    	Connection connection = null;
    	Statement statement = null;
    	try{
    		connection = DriverManager.getConnection("jdbc:sqlite:/Users/k13114kk/MorphologicalAnalysis/DataBase/jinrouG.db");
    		statement = connection.createStatement();
    		statement.setQueryTimeout(30);
    		
    		String sql = "delete from villageTable";
    		statement.executeUpdate(sql);
    		sql = "delete from serifTable";
    		statement.executeUpdate(sql);
    		sql = "delete from voteTable";
    		statement.executeUpdate(sql);
    		sql = "delete from dateTable";
    		statement.executeUpdate(sql);
    		sql = "delete from postTable";
    		statement.executeUpdate(sql);
    		
    	} catch(SQLException e) {
    		System.err.println(e.getMessage());
    	} finally {
    		try {
    			if(statement != null) {
    				statement.close();
    			}
    		} catch (SQLException e) {
    			System.err.println(e);
    		}
    		try {
    			if (connection != null) {
    				connection.close();
    			}
    		} catch (SQLException e) {
    			System.err.println(e);
    		}
    	}
	}
}

package simpledb.record;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.sql.Statement;

import simpledb.parse.DeleteData;
import simpledb.remote.SimpleDriver;
import simpledb.server.SimpleDB;
import simpledb.tx.Transaction;

public class RecordTestClass {

	public static void main(String[] args) {

		SimpleDB.init("studentdb");
		Transaction tx = new Transaction();
		Schema sch = new Schema();
		sch.addIntField("CodiceProdotto");
		sch.addStringField("NomeProdotto", 20);
		TableInfo ti = new TableInfo("Prodotti",sch);

		Connection conn = null;
		try {
			Driver d = new SimpleDriver();
			conn = d.connect("jdbc:simpledb://localhost", null);
			Statement stmt = conn.createStatement();

			String s = "create table Prodotti(CodiceProdotto int, NomeProdotto varchar(20))";
			stmt.executeUpdate(s);
			System.out.println("Table Prodotti created.");

			s = "insert into Prodotti(CodiceProdotto, NomeProdotto) values ";
			for(int i=0; i<1000; i++)
				stmt.executeUpdate(s + "("+String.valueOf(i)+", 'prodotto"+String.valueOf(i)+"')"); 

			System.out.println("Prodotti records inserted.");
			
			

		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (conn != null)
					conn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
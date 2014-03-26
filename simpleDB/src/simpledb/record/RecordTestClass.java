package simpledb.record;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import simpledb.file.FileMgr;
import simpledb.parse.DeleteData;
import simpledb.remote.SimpleDriver;
import simpledb.server.SimpleDB;
import simpledb.tx.Transaction;

public class RecordTestClass {

	public static void main(String[] args) {

	/*	SimpleDB.init("studentdb");
		Transaction tx = new Transaction();
		Schema sch = new Schema();
		sch.addIntField("CodiceProdotto");
		sch.addStringField("NomeProdotto", 20);
		sch.addStringField("Prezzo", 10);
		TableInfo ti = new TableInfo("Prodotti",sch);
*/
		
		Connection conn = null;
		try {

			Driver d = new SimpleDriver();
			conn = d.connect("jdbc:simpledb://localhost", null);
			Statement stmt = conn.createStatement();
			//ResultSet per le query
			ResultSet r;
			//Ottengo il filemgr per gli utilizzi che seguono
			FileMgr fmg = SimpleDB.fileMgr();


			String s = "create table Prodotti(CodiceProdotto int, NomeProdotto varchar(20))";
			stmt.executeUpdate(s);
			System.out.println("Table Prodotti created.");

			//----------- 1000 inserimenti nella tabella prodotti
			s = "insert into Prodotti(CodiceProdotto, NomeProdotto) values ";
			for(int i=0; i<1000; i++)
				stmt.executeUpdate(s + "("+String.valueOf(i)+", 'prodotto"+String.valueOf(i)+"')"); 

			System.out.println("Prodotti records inserted.");

			//Stampa dopo 10000 inserimenti
			fmg.PrintStatistics();

			//----------- Lettura di tutti i record
			s= "select CodiceProdotto,NomeProdotto from Prodotti";
			r=stmt.executeQuery(s);
			while(r.next())
				System.out.println("CodiceProdotto: "+r.getInt("CodiceProdotto")+" - "+"NomeProdotto: "+r.getString("NomeProdotto"));

			//Stampa dopo lettura di tutti i record
			fmg.PrintStatistics();

			//----------- Cancellazione del 50% dei record dalla tabella Prodotti
			s = "delete from Prodotti where CodiceProdotto=";
			for(int i=0; i<500; i++)
				stmt.executeUpdate(s+String.valueOf(i));

			//Stampa dopo eliminazione di meta dei record
			fmg.PrintStatistics();

			//------------- Scansione di tutti i record e calcolo della media
			s= "select CodiceProdotto from Prodotti";
			r=stmt.executeQuery(s);
			int nprod=0;
			int totprice=0;
			while(r.next()){
				nprod+=1;
				totprice+=(Integer.valueOf(r.getString("CodiceProdotto")));
			}
			System.out.println("Codice medio prodotti= "+(totprice/nprod));

			//Stampa dopo eliminazione di meta dei record
			fmg.PrintStatistics();

			//----------- 7000 nuovi inserimenti nella tabella prodotti
			s = "insert into Prodotti(CodiceProdotto, NomeProdotto) values ";
			for(int i=0; i<700; i++)
				stmt.executeUpdate(s + "("+String.valueOf(i)+", 'prodotto"+String.valueOf(i)+"')"); 

			System.out.println("New Prodotti records inserted.");

			//Stampa dopo inserimento nuovi record
			fmg.PrintStatistics();

			//----------- Nuova Lettura di tutti i record
			s= "select CodiceProdotto,NomeProdotto from Prodotti";
			r=stmt.executeQuery(s);
			while(r.next())
				System.out.println("CodiceProdotto: "+r.getInt("CodiceProdotto")+" - "+"NomeProdotto: "+r.getString("NomeProdotto"));

			//Stampa dopo Nuova lettura di tutti i record
			fmg.PrintStatistics();

			
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
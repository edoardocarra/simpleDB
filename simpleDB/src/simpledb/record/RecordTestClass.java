package simpledb.record;

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


	}

}

package ac.ait.k13114kk;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public enum Table{
		village,
		serif,
		post,
		vote,
		date
	}
	
	public static void main(String[] args) {
		//DBから村の情報をとってくる
		SQLgetter sqLgetter = new SQLgetter("/Users/k13114kk/MorphologicalAnalysis/DataBase/jinrouG.db");
		List<String> table = new ArrayList<String>();
		List<String> villageTable = new ArrayList<String>(sqLgetter.databaseGetter("select * from vilageTable when villageNumber=1"));
		List<String> serifTable = new ArrayList<String>(sqLgetter.databaseGetter("select * from serifTable when villageNumber=1"));
		List<String> postTable = new ArrayList<String>(sqLgetter.databaseGetter("select * from postTable when villageNumber=1"));
		List<String> voteTable = new ArrayList<String>(sqLgetter.databaseGetter("select * from voteTable when villageNumber=1"));
		List<String> dateTable = new ArrayList<String>(sqLgetter.databaseGetter("select * from dateTable when villageNumber=1"));
		
	}
}

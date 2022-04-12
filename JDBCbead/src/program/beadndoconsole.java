package program;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

public class beadndoconsole {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Kérem adjon meg egy jelszót és egy felhasználónevet a bejelentkezéshez vagy hagya üresen amenyiben Bakos Kristóf-ként szeretne belépni");
		String kell1,kell2;
		System.out.println("Felhasználó név:");
		kell1= sc.next();
		System.out.println("Jelszó:");
		kell2= sc.next();
		SQLalap sql;
		if (kell1.isEmpty()) {
			sql = new SQLalap();
			
		}
		else {
			sql = new SQLalap(kell1,kell2);

		}
		ResultSet rs = null;
		boolean kilep = true;
		boolean igaz = true;
		
		do {
			eljarasok();
			System.out.println("Kérem válassza ki melyik eljárást szeretné látni");
			
		switch ( sc.nextInt()) {
		case 1:
			String tabanev;
			do {
				
			System.out.println("Kérem adja meg melyik táblábt szeretné látni:");
			tabanev = sc.next();
			igaz = sql.tableExists(tabanev);
			if(igaz)
				{
				System.out.println("Ez a tábla nem létezik");
				}
			} while (igaz);
			rs= sql.LookTable(tabanev);
			rsmegjelenit(rs);
			break;

		case 2:
			
			System.out.println("kérném a diak nevét");
			String nev= sc.next();
			String neptun;
			do {
			System.out.println("Kérem a diak neptunkodját");
			neptun = sc.next();
			} while (neptun.length()!=6);
			System.out.println("Kérném a diák születési évét");
			String date = sc.next();
			System.out.println("Kéreke egy diak azonositót");
			int dazon = sc.nextInt();
			sql.DataUploadToDiak(date, nev, neptun, 1,dazon);
			break;
			
		case 3:
			System.out.println("Kérem adja meg a diak uj nevét:");
			String nev1 = sc.next();
			System.out.println("Kérem adja meg a diak azonositóját:");
			int azon = sc.nextInt();
			sql.DataChangeInDiakByDazon(azon, nev1);
			break;
			
		case 4 : 
		System.out.println("Kérem adaj meg a diak azonositóját amit szeretne törölni");
		azon=sc.nextInt();
		sql.DeleteDataFromDiakByDazon(azon);
		break;
		case 5:
			do {
				
				System.out.println("Kérem adja meg a torolni kivan tablat:");
				tabanev = sc.next();
				igaz = sql.tableExists(tabanev);
				if(!igaz)
					{
					System.out.println("Ez a tábla nem létezik");
					}
				} while (igaz);		
			break;
		case 6:
			rs= sql.sportorvosokszama();
			rsmegjelenit(rs);
			break;
		case 7:
			System.out.println("Kérem adjon meg egy diak azonositót:");
			int dazon2 =sc.nextInt();
			rs=sql.LatistSchoolStart(dazon2);
			rsmegjelenit(rs);
			break;
		case 8:
			sql.CreateTable();
			break;
		case 9:
			sql.TabelUpload();
			break;
		case 10:
			kilep = false;
			break; 
			
		default:
			break;
		}
		} while (kilep);

	}
	public static void eljarasok()
	{
		System.out.println("Szeretném megnézni az adott táblákat:1");
		System.out.println("Szeretném a Diak tablaba adatot feltölteni:2");
		System.out.println("Szeretnék változatni diak nevén tablaban Dazon alapján :3");
		System.out.println("Szeretnék törölni a diak táblából Dazon alapján: 4");
		System.out.println("Szeretnék táblát törölni: 5");
		System.out.println("Diakok betöltendõ életéveinek mejeleneitése: 6");
		System.out.println("Melyik iskola tanulója kezd legkésõbb: 7");
		System.out.println("Táblák létrehozása: 8");
		System.out.println("Táblák feltöltése: 9");
		
		System.out.println("Irjon be egy 10-est a kilépéshez");
		
		
	}
	public static void rsmegjelenit (ResultSet rs) throws SQLException
	{
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		while (rs.next()) {
		    for (int i = 1; i <= columnsNumber; i++) {
		        if (i > 1) System.out.print(",  ");
		        String columnValue = rs.getString(i);
		        System.out.print(columnValue + " " + rsmd.getColumnName(i));
		    }
		    System.out.println("");
		}
		
	}

}

package program;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

public class beadndoconsole {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		Scanner sc = new Scanner(System.in);
		System.out.println("K�rem adjon meg egy jelsz�t �s egy felhaszn�l�nevet a bejelentkez�shez vagy hagya �resen amenyiben Bakos Krist�f-k�nt szeretne bel�pni");
		String kell1,kell2;
		System.out.println("Felhaszn�l� n�v:");
		kell1= sc.next();
		System.out.println("Jelsz�:");
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
			System.out.println("K�rem v�lassza ki melyik elj�r�st szeretn� l�tni");
			
		switch ( sc.nextInt()) {
		case 1:
			String tabanev;
			do {
				
			System.out.println("K�rem adja meg melyik t�bl�bt szeretn� l�tni:");
			tabanev = sc.next();
			igaz = sql.tableExists(tabanev);
			if(igaz)
				{
				System.out.println("Ez a t�bla nem l�tezik");
				}
			} while (igaz);
			rs= sql.LookTable(tabanev);
			rsmegjelenit(rs);
			break;

		case 2:
			
			System.out.println("k�rn�m a diak nev�t");
			String nev= sc.next();
			String neptun;
			do {
			System.out.println("K�rem a diak neptunkodj�t");
			neptun = sc.next();
			} while (neptun.length()!=6);
			System.out.println("K�rn�m a di�k sz�let�si �v�t");
			String date = sc.next();
			System.out.println("K�reke egy diak azonosit�t");
			int dazon = sc.nextInt();
			sql.DataUploadToDiak(date, nev, neptun, 1,dazon);
			break;
			
		case 3:
			System.out.println("K�rem adja meg a diak uj nev�t:");
			String nev1 = sc.next();
			System.out.println("K�rem adja meg a diak azonosit�j�t:");
			int azon = sc.nextInt();
			sql.DataChangeInDiakByDazon(azon, nev1);
			break;
			
		case 4 : 
		System.out.println("K�rem adaj meg a diak azonosit�j�t amit szeretne t�r�lni");
		azon=sc.nextInt();
		sql.DeleteDataFromDiakByDazon(azon);
		break;
		case 5:
			do {
				
				System.out.println("K�rem adja meg a torolni kivan tablat:");
				tabanev = sc.next();
				igaz = sql.tableExists(tabanev);
				if(!igaz)
					{
					System.out.println("Ez a t�bla nem l�tezik");
					}
				} while (igaz);		
			break;
		case 6:
			rs= sql.sportorvosokszama();
			rsmegjelenit(rs);
			break;
		case 7:
			System.out.println("K�rem adjon meg egy diak azonosit�t:");
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
		System.out.println("Szeretn�m megn�zni az adott t�bl�kat:1");
		System.out.println("Szeretn�m a Diak tablaba adatot felt�lteni:2");
		System.out.println("Szeretn�k v�ltozatni diak nev�n tablaban Dazon alapj�n :3");
		System.out.println("Szeretn�k t�r�lni a diak t�bl�b�l Dazon alapj�n: 4");
		System.out.println("Szeretn�k t�bl�t t�r�lni: 5");
		System.out.println("Diakok bet�ltend� �let�veinek mejeleneit�se: 6");
		System.out.println("Melyik iskola tanul�ja kezd legk�s�bb: 7");
		System.out.println("T�bl�k l�trehoz�sa: 8");
		System.out.println("T�bl�k felt�lt�se: 9");
		
		System.out.println("Irjon be egy 10-est a kil�p�shez");
		
		
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

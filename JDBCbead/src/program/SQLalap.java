package program;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class SQLalap {	
	
	private String[] tablakStrings = {"diak","iskola","sportorvos","edzo","esemeny","kapocs"};
	private static Connection con = null;
	private static final String URL="jdbc:oracle:thin:@193.6.5.58:1521:XE";
	private String username= null;
	private String password = null ;
	private String sQLcmd = null; 
	private boolean taba = true;
	public SQLalap(String username, String passwordString) {
		super();
		this.username = username;
		this.password = passwordString;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@193.6.5.58:1521:XE";
			con = DriverManager.getConnection(url, username, passwordString);
		} catch (Exception ex) {
			System.out.println("Connection error: " + ex.getMessage());
		}
	}
	public SQLalap() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		super();		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@193.6.5.58:1521:XE";
			con = DriverManager.getConnection(url, "H22_IRF1JB", "IRF1JB");
		} catch (Exception ex) {
			System.out.println("Connection error: " + ex.getMessage());
		}
		
	}
	
	/*- bejelentkezési modul
	- adatok (új rekordok) felvitele
	- adatok lekérdezése (szûrés egy és több mezõ szerint, egy és kapcsolt táblákra)
	- adatok módosítása
	- kijelölt adatok törlése*/
	
	public void CreateTable() throws SQLException {

		


		Statement statement = con.createStatement();
		sQLcmd=	"CREATE TABLE sportorvos(Sazon INT NOT NULL,Iszam INT NOT NULL,Utca varchar(32) NOT NULL,Tszam INT NOT NULL,Nev varchar(64) NOT NULL,primary key (Sazon))";
		statement.addBatch(sQLcmd);
		sQLcmd= "CREATE TABLE Diak(Dazon int NOT NULL,Szulev varchar(32) NOT NUll,Nev varchar(64) NOT NULL,Neptunkod varchar(6),Sazon int,primary key (Dazon),FOREIGN KEY (Sazon) REFERENCES Sportorvos(Sazon))";
		statement.addBatch(sQLcmd);
		sQLcmd=	"CREATE TABLE Iskola(Nev varchar(150) NOT NULL,Iszam int NOT NULL,Utca varchar(32) NOT NULL,ItezmenyTitulus varchar(32)NOT NULL,Dazon int,primary key (Nev),foreign key (Dazon) references Diak(Dazon))";
		statement.addBatch(sQLcmd);
		sQLcmd= "CREATE TABLE Edzo(Eazon int NOT NULL,Nev varchar(64) NOT NULL,Szulev varchar(32) NOT NULL,PalyaFKezd varchar(32) NOT NULL,Dazon int,primary key (Eazon),FOREIGN KEY (Dazon) REFERENCES Diak(Dazon))";
		statement.addBatch(sQLcmd);
		sQLcmd= "CREATE TABLE Esemeny(Megnevezes varchar(120) NOT NULL,Helyszin varchar(64) NOT NULL,Idopont varchar(5)  NOT NULL,ReszSzam int NOT NULL,primary key (Megnevezes))";
		statement.addBatch(sQLcmd);
		sQLcmd= "CREATE TABLE Kapocs(Megnevezes varchar(120) NOT NULL,Dazon int,PontosKezd varchar(5),FOREIGN KEY (Megnevezes)	REFERENCES Esemeny (Megnevezes),FOREIGN KEY (Dazon) REFERENCES Diak(Dazon))";
		statement.addBatch(sQLcmd);
		statement.executeBatch();
		System.out.println("tábkafeltoltes");
		
	}

	
	public void TabelUpload() throws SQLException {

		

		Statement statement =con.createStatement();
		sQLcmd= "INSERT INTO Sportorvos VALUES (1,'3031','Ozugro utca','309599690','Bogdan Gotlib')";
		statement.executeUpdate(sQLcmd);
		sQLcmd=	"INSERT INTO Sportorvos VALUES(2,'5382','Zala utca','302867253','Takács Idrisz')";
		statement.executeUpdate(sQLcmd);
		sQLcmd="INSERT INTO Sportorvos VALUES(3,'9543','Csemetekert utca','302605116','Bognár Lél')";
		statement.executeUpdate(sQLcmd);
		sQLcmd="INSERT INTO Sportorvos VALUES(4,'2374','Alsóbábonyibérc dûlõ','304617870','Kozma Szebáld')";
		statement.executeUpdate(sQLcmd);
		sQLcmd="INSERT INTO Sportorvos VALUES(5,'2860','Munkácsy utca','305506392','Székely Balambér')";
		statement.executeUpdate(sQLcmd);
		sQLcmd = "INSERT INTO diak VALUES(1,'2003-7-25','Fekete Alieu','A8KOR9','1')";
		statement.executeUpdate(sQLcmd);
		sQLcmd = "INSERT INTO diak VALUES(2,'1999-4-8','Németh Cseke','GCTVJ3','2')";
		statement.executeUpdate(sQLcmd);
		sQLcmd = "INSERT INTO diak VALUES(3,'1999-7-4','Magyar Zakari','MRT28C','3')";
		statement.executeUpdate(sQLcmd);
		sQLcmd = "INSERT INTO diak VALUES(4,'1999-9-4','Szücs Derek','MXUQ8O','4')";
		statement.executeUpdate(sQLcmd);
		sQLcmd = "INSERT INTO diak VALUES(5,'1998-4-15','Balogh Gede','MBCB6Y','5')";
		statement.executeUpdate(sQLcmd);
		sQLcmd ="INSERT INTO iskola VALUES('Magyar Táncmûvészeti Egyetem','7491','Kaparó utca','Egyetem',1)";
		statement.executeUpdate(sQLcmd);
		sQLcmd ="INSERT INTO iskola VALUES('Pannon Egyetem','9465','Hamerák Mihály utca','Egyetem',2)";
		statement.executeUpdate(sQLcmd);
		sQLcmd ="INSERT INTO iskola VALUES('Budapesti Gazdasági Egyetem','7014','Benedekalja utca','Egyetem',3)";
		statement.executeUpdate(sQLcmd);
		sQLcmd ="INSERT INTO iskola VALUES('Liszt Ferenc Zenemûvészeti Egyetem','4917','Síp utca','Egyetem',4)";
		statement.executeUpdate(sQLcmd);
		sQLcmd ="INSERT INTO iskola VALUES('Hang-Szín-Tér Mûvészeti Szakközépiskola, Alapfokú Mûvészeti Iskola és Kollégium','6917','Magyarka','Szakközép',5)";
		statement.executeUpdate(sQLcmd);
		sQLcmd = "INSERT INTO edzo VALUES(1,'Kovács Mendel','1973-1-7','1995-2-2',1)";
		statement.executeUpdate(sQLcmd);
		sQLcmd = "INSERT INTO edzo VALUES(2,'Pataki Soma','1965-1-20','1994-7-20',2)";
		statement.executeUpdate(sQLcmd);
		sQLcmd = "INSERT INTO edzo VALUES(3,'Fehér Szalvátor','1966-5-2','1996-5-17','3')";
		statement.executeUpdate(sQLcmd);
		sQLcmd ="INSERT INTO edzo VALUES(4,'Kis Hamilkár','1973-7-26','1990-9-23',4)";
		statement.executeUpdate(sQLcmd);
		sQLcmd ="INSERT INTO edzo VALUES(5,'Király Apaj','1969-2-23','1996-3-19',5)";
		statement.executeUpdate(sQLcmd);
		sQLcmd ="INSERT INTO esemeny VALUES('Mûugrás','Miskolci Egyetem Körcsarnok','6:23','8')";
		statement.executeUpdate(sQLcmd);
		sQLcmd=	"INSERT INTO esemeny VALUES('Úszás','Miskolci Egyetem Körcsarnok','9:36','9')";
		statement.executeUpdate(sQLcmd);
		sQLcmd= "INSERT INTO esemeny VALUES('Szinkronúszás','Miskolci Egyetem Körcsarnok','11:05','10')";
		statement.executeUpdate(sQLcmd);
		sQLcmd= "INSERT INTO esemeny VALUES('Vízilabda','Miskolci Egyetem Körcsarnok','12:09','8')";
		statement.executeUpdate(sQLcmd);
		sQLcmd= "INSERT INTO esemeny VALUES('Íjászat','Miskolci Egyetem Körcsarnok','13:07','6')";
		statement.executeUpdate(sQLcmd);
		sQLcmd = "INSERT INTO kapocs VALUES('Mûugrás',1,'9:39')";
		statement.executeUpdate(sQLcmd);
		sQLcmd = "INSERT INTO kapocs VALUES('Úszás',2,'12:09')";
		statement.executeUpdate(sQLcmd);
		sQLcmd = "INSERT INTO kapocs VALUES('Szinkronúszás',3,'6:29')";
		statement.executeUpdate(sQLcmd);
		sQLcmd = "INSERT INTO kapocs VALUES('Vízilabda',2,'9:39')";
		statement.executeUpdate(sQLcmd);
		sQLcmd = "INSERT INTO kapocs VALUES('Íjászat',4,'6:28')";
		statement.executeUpdate(sQLcmd);
		sQLcmd = "INSERT INTO kapocs VALUES('Mûugrás',5,'10:19')";
		statement.executeUpdate(sQLcmd);
		
		
	}
	public void DataUploadToDiak (String szulev,String Nev,String neptunkod , int sazon,int dazon )  throws SQLException {
		PreparedStatement pst = con.prepareStatement("INSERT INTO diak VALUES"
				+ "(?,?,?,?,?)") ;
		pst.setInt(1, dazon);
		pst.setString(2, szulev);
		pst.setString(3, Nev);
		pst.setString(4, neptunkod);
		pst.setInt(5, sazon);
		pst.executeUpdate();		
		
	}
	public void DataChangeInDiakByDazon(int Dazon,String nev) throws SQLException {
		PreparedStatement pst = con.prepareStatement("UPDATE diak SET Nev= ? WHERE Dazon = ?");
		pst.setInt(2, Dazon );
		pst.setString(1, nev );
		pst.executeUpdate();
		
		
	}
	public void DeleteDataFromDiakByDazon(int Dazon) throws SQLException {


		PreparedStatement pst = con.prepareStatement("DELETE FROM diak WHERE Dazon = ? ");
		pst.setInt(1, Dazon);
		pst.executeUpdate();
		
	}
	public ResultSet LookTable(String tabla) throws SQLException {
		Statement st = con.createStatement();
		sQLcmd="SELECT * FROM "+ tabla;
		ResultSet rs = st.executeQuery(sQLcmd); 
		return rs;
		
	}
	boolean tableExists( String tableName) throws SQLException {
	    DatabaseMetaData meta = con.getMetaData();
	    ResultSet resultSet = meta.getTables(null, null, tableName, new String[] {"TABLE"});

	    return resultSet.next();
	}
	public static String[] GetTableName() throws SQLException {
		DatabaseMetaData meta = con.getMetaData();
		ResultSet rs = meta.getTables(null, null, "%", null);
		ArrayList<String> v = new ArrayList<>();
		while (rs.next()) {
			v.add( rs.getString(3));
		}
		String[] ret =v.toArray(new String[0]);
		return  ret;
	}
	public void DeleteTable(String talename) throws SQLException
	{
		PreparedStatement st = con.prepareStatement("Drop Table ?");
		st.setString(1, talename);
		st.executeQuery();
	}
	public ResultSet sportorvosokszama() throws SQLException
	{
		Statement st = con.createStatement();
		sQLcmd= "SELECT count(sportorvos.sazon) FROM sportorvos ";
		return st.executeQuery(sQLcmd);
		
	}
	public ResultSet LatistSchoolStart(int dazon) throws SQLException
	{
		Statement st= con.createStatement();
		sQLcmd ="SELECT iskola.Nev, diak.Nev, MAX(kapocs.PontosKezd) FROM iskola \r\n"
				+ "INNER JOIN diak ON iskola.Dazon=diak.Dazon \r\n"
				+ "INNER JOIN kapocs ON kapocs.Dazon=diak.Dazon\r\n"
				+ "group by  diak.nev,iskola.nev ";
	
		return st.executeQuery(sQLcmd);
		
	}


	
	
}

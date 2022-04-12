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
	
	/*- bejelentkez�si modul
	- adatok (�j rekordok) felvitele
	- adatok lek�rdez�se (sz�r�s egy �s t�bb mez� szerint, egy �s kapcsolt t�bl�kra)
	- adatok m�dos�t�sa
	- kijel�lt adatok t�rl�se*/
	
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
		System.out.println("t�bkafeltoltes");
		
	}

	
	public void TabelUpload() throws SQLException {

		

		Statement statement =con.createStatement();
		sQLcmd= "INSERT INTO Sportorvos VALUES (1,'3031','Ozugro utca','309599690','Bogdan Gotlib')";
		statement.executeUpdate(sQLcmd);
		sQLcmd=	"INSERT INTO Sportorvos VALUES(2,'5382','Zala utca','302867253','Tak�cs Idrisz')";
		statement.executeUpdate(sQLcmd);
		sQLcmd="INSERT INTO Sportorvos VALUES(3,'9543','Csemetekert utca','302605116','Bogn�r L�l')";
		statement.executeUpdate(sQLcmd);
		sQLcmd="INSERT INTO Sportorvos VALUES(4,'2374','Als�b�bonyib�rc d�l�','304617870','Kozma Szeb�ld')";
		statement.executeUpdate(sQLcmd);
		sQLcmd="INSERT INTO Sportorvos VALUES(5,'2860','Munk�csy utca','305506392','Sz�kely Balamb�r')";
		statement.executeUpdate(sQLcmd);
		sQLcmd = "INSERT INTO diak VALUES(1,'2003-7-25','Fekete Alieu','A8KOR9','1')";
		statement.executeUpdate(sQLcmd);
		sQLcmd = "INSERT INTO diak VALUES(2,'1999-4-8','N�meth Cseke','GCTVJ3','2')";
		statement.executeUpdate(sQLcmd);
		sQLcmd = "INSERT INTO diak VALUES(3,'1999-7-4','Magyar Zakari','MRT28C','3')";
		statement.executeUpdate(sQLcmd);
		sQLcmd = "INSERT INTO diak VALUES(4,'1999-9-4','Sz�cs Derek','MXUQ8O','4')";
		statement.executeUpdate(sQLcmd);
		sQLcmd = "INSERT INTO diak VALUES(5,'1998-4-15','Balogh Gede','MBCB6Y','5')";
		statement.executeUpdate(sQLcmd);
		sQLcmd ="INSERT INTO iskola VALUES('Magyar T�ncm�v�szeti Egyetem','7491','Kapar� utca','Egyetem',1)";
		statement.executeUpdate(sQLcmd);
		sQLcmd ="INSERT INTO iskola VALUES('Pannon Egyetem','9465','Hamer�k Mih�ly utca','Egyetem',2)";
		statement.executeUpdate(sQLcmd);
		sQLcmd ="INSERT INTO iskola VALUES('Budapesti Gazdas�gi Egyetem','7014','Benedekalja utca','Egyetem',3)";
		statement.executeUpdate(sQLcmd);
		sQLcmd ="INSERT INTO iskola VALUES('Liszt Ferenc Zenem�v�szeti Egyetem','4917','S�p utca','Egyetem',4)";
		statement.executeUpdate(sQLcmd);
		sQLcmd ="INSERT INTO iskola VALUES('Hang-Sz�n-T�r M�v�szeti Szakk�z�piskola, Alapfok� M�v�szeti Iskola �s Koll�gium','6917','Magyarka','Szakk�z�p',5)";
		statement.executeUpdate(sQLcmd);
		sQLcmd = "INSERT INTO edzo VALUES(1,'Kov�cs Mendel','1973-1-7','1995-2-2',1)";
		statement.executeUpdate(sQLcmd);
		sQLcmd = "INSERT INTO edzo VALUES(2,'Pataki Soma','1965-1-20','1994-7-20',2)";
		statement.executeUpdate(sQLcmd);
		sQLcmd = "INSERT INTO edzo VALUES(3,'Feh�r Szalv�tor','1966-5-2','1996-5-17','3')";
		statement.executeUpdate(sQLcmd);
		sQLcmd ="INSERT INTO edzo VALUES(4,'Kis Hamilk�r','1973-7-26','1990-9-23',4)";
		statement.executeUpdate(sQLcmd);
		sQLcmd ="INSERT INTO edzo VALUES(5,'Kir�ly Apaj','1969-2-23','1996-3-19',5)";
		statement.executeUpdate(sQLcmd);
		sQLcmd ="INSERT INTO esemeny VALUES('M�ugr�s','Miskolci Egyetem K�rcsarnok','6:23','8')";
		statement.executeUpdate(sQLcmd);
		sQLcmd=	"INSERT INTO esemeny VALUES('�sz�s','Miskolci Egyetem K�rcsarnok','9:36','9')";
		statement.executeUpdate(sQLcmd);
		sQLcmd= "INSERT INTO esemeny VALUES('Szinkron�sz�s','Miskolci Egyetem K�rcsarnok','11:05','10')";
		statement.executeUpdate(sQLcmd);
		sQLcmd= "INSERT INTO esemeny VALUES('V�zilabda','Miskolci Egyetem K�rcsarnok','12:09','8')";
		statement.executeUpdate(sQLcmd);
		sQLcmd= "INSERT INTO esemeny VALUES('�j�szat','Miskolci Egyetem K�rcsarnok','13:07','6')";
		statement.executeUpdate(sQLcmd);
		sQLcmd = "INSERT INTO kapocs VALUES('M�ugr�s',1,'9:39')";
		statement.executeUpdate(sQLcmd);
		sQLcmd = "INSERT INTO kapocs VALUES('�sz�s',2,'12:09')";
		statement.executeUpdate(sQLcmd);
		sQLcmd = "INSERT INTO kapocs VALUES('Szinkron�sz�s',3,'6:29')";
		statement.executeUpdate(sQLcmd);
		sQLcmd = "INSERT INTO kapocs VALUES('V�zilabda',2,'9:39')";
		statement.executeUpdate(sQLcmd);
		sQLcmd = "INSERT INTO kapocs VALUES('�j�szat',4,'6:28')";
		statement.executeUpdate(sQLcmd);
		sQLcmd = "INSERT INTO kapocs VALUES('M�ugr�s',5,'10:19')";
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

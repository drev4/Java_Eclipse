package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 * Es necesario crear un servidor para la base de datos, ademas de descargar el driver y adjuntarlo en el classpath del proyecto(.jar).
 * @author Diego
 *
 */
public class Acceso1 {
	public static void main(String[] args) {
		
		//Parametros de la conexion
		String user="diego";
		String pass="drh1";
		String driver="driver de la base de datos que se requiera (hsql, mysql, oracle...";
		String url="url del servidor de nuestra base de datos.";
		
		Connection con=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		
		try {
			//Parte 1
			
			//Levantar el driver
			Class.forName(driver);
			//Establecemos la conexion
			con = DriverManager.getConnection(url, user, pass);
			
			//Parte 2
			
			//Defino un query
			String sql="SELECT empno, ename, hiredate, deptno FROM emp";
			//Preparo la sentencia 
			pstm=con.prepareStatement(sql);
			//Ejecuto el query y obtengo los resultados en rs
			rs=pstm.executeQuery();
			//Itero los resultados del query
			while(rs.next()){
				//Muestro los campos del registro actual
				System.out.print(rs.getInt("empno") + ", ");
				System.out.print(rs.getString("ename") + ", ");
				System.out.print(rs.getDate("hiredate") + ", ");
				System.out.println(rs.getInt("deptno") + ", ");
			}
			
		} catch (Exception e) {
			System.out.println("Error");
			e.printStackTrace();
			//throw new RuntimeException(e);
		}finally{
			try{
			//Parte 3
			
			//Cierro los recursos en orden inverso al que fueron adquiridos
			if(rs!=null) rs.close();
			if(pstm!=null) pstm.close();
			if(con!=null) con.close();
			
			}catch(Exception e){
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}
}

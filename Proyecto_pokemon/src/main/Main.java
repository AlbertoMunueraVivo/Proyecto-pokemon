package main;

import bbd.Conexion;

public class Main {

	public static void main(String[] args) {
		Conexion.conexionBbd();
		/*Scanner sc = new Scanner(System.in);
		Conexion.conexionBbd();
//abc  
		prueba de registro
            System.out.println("Nombre de usurio a registrar: ");
            String nombreUsu = sc.next();
            System.out.println("Contraseña de usurio a registrar: ");
            String contraseñaUsu = sc.next();
            
            String sql = "INSERT INTO usuarios (id_usuario, nombre_usuario, contraseña_usuario) VALUES (?, ?, ?)";
            stmt = conexion.prepareStatement(sql);

            stmt.setInt(1, 2);
            stmt.setString(2, nombreUsu);
            stmt.setString(3, contraseñaUsu);

            stmt.executeUpdate();
            System.out.println("datos registrados");
            //fin prueba de registro
            
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos:");
            e.printStackTrace();
        }
        sc.close(); */
	}

}


package main.java.edu.mmcoffee.colegiogotitas.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import main.java.edu.mmcoffee.colegiogotitas.config.DataBaseConnection;
import java.sql.SQLException;
import main.java.edu.mmcoffee.colegiogotitas.dto.request.LoginRequest;
import main.java.edu.mmcoffee.colegiogotitas.dto.response.LoginResponse;

public class AuthRepository {
    
    //atributos
    //repository no saba la bas de datos, solo usa la base de datos
    private boolean sqlStatus = false;
    
    //constructor
    /*repositorio: no tiene dependencias, metodo especial que se ejecuta 
cuando se crea la instancia*/
    
    /*los metodos: acciones específicas, tareas individuales, pero no retornan nada,
son void, otros metodos, realizan tareas y retornan un tipo de dato 
primitivo o compuesto (clase).*/
    
    /*Divide y venceras: un método debe ser encargadoi de realizar unicamente 
    una tarea específica, el nombre de ese método debe ser modular, directo
    */
    
    public LoginResponse findUserByEmail(LoginRequest loginRequest) throws Exception{
       String sql = "select d.nombre, d.apellido, u.contrasena_hash from usuarios as u" +
" right join docentes as d" +
" on d.id_docente = u.id_docente" +
" where email = ? ";
                try(PreparedStatement pstm = DataBaseConnection.getConnectionDatabase().prepareStatement(sql)){
                pstm.setString(1, loginRequest.getEmail());
                
                ResultSet rs = pstm.executeQuery();
                if(rs.next()){
                return new LoginResponse(rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("contrasena_hash"));                    
                }
                }catch(SQLException e){
                    System.out.println("error al encontrar el EMAIL" + e.getMessage());
                }
                
                return null;
    }
    
}
        
    


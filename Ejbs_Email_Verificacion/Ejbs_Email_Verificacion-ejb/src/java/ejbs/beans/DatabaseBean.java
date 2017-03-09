
package ejbs.beans;

import ejbs.exeption.BDException;
import ejbs.exeption.UsernameAlreadyExistsException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import javax.sql.XADataSource;

/**
 *
 * @author mayteEcheverry
 */
@Stateless
public class DatabaseBean implements DatabaseBeanLocal {
    @Resource( name = "jdbc/alumno2010DS", type=XADataSource.class)
    private DataSource dataSource; 
    private String idUUID;
    
    @Override
    public Collection<Usuario> getUsuarios() throws BDException{  
        Collection<Usuario> usuarios = new LinkedList<>();
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement(); 
            ResultSet resultSet = statement.executeQuery("SELECT U.USERNAME,U.PASSWORD,U.EMAIL FROM USUARIOS U")
           ){
            
            if(resultSet.next()){
                do{                  
                    Usuario user = new Usuario();
                    user.setUsername(resultSet.getString(1));
                    user.setPassword(resultSet.getString(2));
                    user.setEmail(resultSet.getString(3));

                    usuarios.add(user);  
                }while(resultSet.next());      
            }   
        }catch(SQLException sqlException) {
            Logger.getGlobal().log(Level.SEVERE, sqlException.getMessage(), sqlException);
        }
        return usuarios;
    }
    
    @Override
    public Collection<Usuario2> getUsuarios2() throws BDException{  
        Collection<Usuario2> usuarios2 = new LinkedList<>();
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement(); 
            ResultSet resultSet = statement.executeQuery("SELECT U.USERNAME,U.PASSWORD,U.EMAIL,U.ID FROM USUARIOS2 U")
           ){
            
            if(resultSet.next()){
                do{                  
                    Usuario2 user2 = new Usuario2();
                    user2.setUsername(resultSet.getString(1));
                    user2.setPassword(resultSet.getString(2));
                    user2.setEmail(resultSet.getString(3));
                    user2.setId(resultSet.getString(4));

                    usuarios2.add(user2);  
                }while(resultSet.next());      
            }   
        }catch(SQLException sqlException) {
            Logger.getGlobal().log(Level.SEVERE, sqlException.getMessage(), sqlException);
        }
        return usuarios2;
    }

    @Override   
    public Optional<Usuario2> insertUsuario2(String username, String password,String email) throws BDException {
        idUUID=UUID.randomUUID().toString();
        try(Connection connection = this.dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement("INSERT INTO USUARIOS2(USERNAME,PASSWORD,EMAIL,ID) VALUES(?, ?, ?, ?)")
           ){
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setString(4, idUUID);
            ps.executeUpdate();
            
            Usuario2 user2 = new Usuario2();
            user2.setUsername(username );
            user2.setPassword(password);
            user2.setEmail(email);
            user2.setId(idUUID);

            return Optional.of(user2);
     
        }catch(SQLException sqlException){
            if(sqlException.getMessage().contains("PK_USUARIOS_TEMP"))throw new UsernameAlreadyExistsException();    
            throw new BDException(sqlException.getCause());    
        }  
    }
   
    @Override   
    public boolean findUsuario2(String username) throws BDException {
        boolean valor = false;
        
        try(Connection connection = this.dataSource.getConnection();
               PreparedStatement ps = connection.prepareStatement("SELECT COUNT(USERNAME) FROM USUARIOS2 WHERE USERNAME=?")
        ){
               ps.setString(1, username);
               try (ResultSet rs = ps.executeQuery()){
                   if(rs.next()){
                       if(rs.getInt(1)==1)
                           valor=true;
                   }
               }
        }catch(SQLException sqlException){
            Logger.getGlobal().log(Level.SEVERE, sqlException.getMessage(), sqlException);
        }
        return valor;
    }
    
      @Override   
    public Optional<Usuario2> findId(String id) throws BDException {  
        try(Connection connection = this.dataSource.getConnection();
               PreparedStatement ps = connection.prepareStatement("SELECT U.USERNAME,U.PASSWORD,U.EMAIL FROM USUARIOS2 U WHERE ID=?")
        ){
               ps.setString(1, id);
               try (ResultSet rs = ps.executeQuery()){
                   if(rs.next()){
                       Usuario2 user2 = new Usuario2();
                       user2.setUsername(rs.getString(1) );
                       user2.setPassword(rs.getString(2) );
                       user2.setEmail(rs.getString(3) );
                       user2.setId(id);
                       return Optional.of(user2);
                   }
               }
        }catch(SQLException sqlException){
            Logger.getGlobal().log(Level.SEVERE, sqlException.getMessage(), sqlException);
        }
        return Optional.empty();
    }
    
    @Override   
    public Optional<Usuario> insertUsuario(String username, String password,String email) throws BDException {
        try(Connection connection = this.dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement("INSERT INTO USUARIOS(USERNAME,PASSWORD,EMAIL) VALUES(?, ?, ?)")
           ){
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.executeUpdate();
            
            Usuario user = new Usuario();
            user.setUsername(username );
            user.setPassword(password);
            user.setEmail(email);

            return Optional.of(user);
     
        }catch(SQLException sqlException){
            if(sqlException.getMessage().contains("PK_USUARIOS"))throw new UsernameAlreadyExistsException();    
            throw new BDException(sqlException.getCause());  
        }  
    }

    @Override
    public int deleteUsuario(String id) throws BDException {
        int valor = 0;
        try(Connection connection = this.dataSource.getConnection();
              PreparedStatement ps = connection.prepareStatement("DELETE FROM USUARIOS2 WHERE ID=?")
         ){
          ps.setString(1, id);
          valor=ps.executeUpdate();
      }catch(SQLException sqlException){} 
        return valor;   
    }
}
    
    

    


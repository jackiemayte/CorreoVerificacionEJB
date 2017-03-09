
package ejbs.beans;

/**
 *
 * @author mayteEcheverry
 */
public class Usuario2 {
    private String username;
    private String password;
    private String email;
    private String id;
    
    public Usuario2() {
    }

    public Usuario2(String username, String password, String email, String id) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Usuario2{" + "username=" + username + ", password=" + password + ", email=" + email + ", id=" + id + '}';
    }
    
}

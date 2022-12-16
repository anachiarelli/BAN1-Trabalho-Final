
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;


public class UsuarioModel {
    private Connection con;

    public UsuarioModel(Connection con) {
        this.con = con;
    }
    
    Usuario findByID(int ID) throws SQLException{
        PreparedStatement st;
        st = this.con.prepareStatement("select * from usuarios where cod_usuario = ?");
        st.setInt(1, ID);
        st.execute();        
        ResultSet result = st.getResultSet();
        if (result.next()) {
            return new Usuario(
                    result.getInt(1),
                    result.getString(2),
                    result.getString(3), 
                    result.getString(4)
            );
        }
        st.close();
        
        return null;
    }
    
    void criaUsuario(Usuario u) throws SQLException {
        PreparedStatement st;
            st = this.con.prepareStatement("INSERT INTO usuarios (email, senha, nome) "
                    + "VALUES (?,?,?)");
            st.setString(1, u.getEmail());
            st.setString(2, u.getSenha());
            st.setString(3, u.getNome());
            st.execute();
            st.close();
    }

    HashSet listAll() throws SQLException {
        Statement st;
        HashSet list = new HashSet();
            st = this.con.createStatement();
            String sql = "SELECT cod_usuario, email, senha, nome FROM usuarios";
            ResultSet result = st.executeQuery(sql);
            while(result.next()) {
                list.add(new Usuario(result.getInt(1), result.getString(2), 
                        result.getString(3), result.getString(4)));
            }
        return list;
    }
    
    void insereUsuario(Usuario u, Grupo g) throws SQLException {
        PreparedStatement st;
        st = this.con.prepareStatement("insert into integrante (cod_usuario, cod_grupo) values (?, ?)");
        st.setInt(1, u.getCodUsuario());
        st.setInt(2, g.getCodGrupo());
        st.execute();
        st.close();
    }
}

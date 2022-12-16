
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

public class GrupoModel {
    private Connection con;

    public GrupoModel(Connection con) {
        this.con = con;
    }
    
    Grupo findByID(int ID) throws SQLException{
        PreparedStatement st;
        st = this.con.prepareStatement("select cod_grupo, nome, proprietario from grupos where cod_grupo = ?");
        st.setInt(1, ID);
        st.execute();        
        ResultSet result = st.getResultSet();
        if (result.next()) {
            return new Grupo(
                    result.getString(2),
                    result.getInt(3),
                    result.getInt(1)
            );
        }
        st.close();
        
        return null;
    }
    
    int countOwnerParticipations(int groupID) throws SQLException{
        PreparedStatement st;
        st = this.con.prepareStatement("select proprietario from grupos where cod_grupo = ?");
        st.setInt(1, groupID);
        st.execute();        
        ResultSet result = st.getResultSet();

        if (result.next()) {
            int proprietario_id = result.getInt(1);
            System.out.println(proprietario_id);
            st = this.con.prepareStatement("select count(*) from integrante where cod_usuario = ( ? )");
            st.setInt(1, proprietario_id);
            st.execute();
            result = st.getResultSet();
            if (result.next()) {
                int numberOfGroups = result.getInt(1);
                st.close();
                return numberOfGroups;
            }
        }
        
        return 0; 
    }
    
    int criaGrupo(Grupo g) throws SQLException {
        PreparedStatement st;
            st = this.con.prepareStatement("INSERT INTO grupos (nome, proprietario) "
                    + "VALUES (?,?) returning cod_grupo");
            st.setString(1, g.getNome());
            st.setInt(2, g.getCodProprietario());
            st.execute();
            
            ResultSet last_inserted_group = st.getResultSet();
            last_inserted_group.next();
            int last_inserted_group_id = last_inserted_group.getInt(1);

            st.close();
            
            return last_inserted_group_id;
    }

    HashSet listAll(Connection con) throws SQLException {
        Statement st;
        HashSet list = new HashSet();
            st = this.con.createStatement();
            String sql = "SELECT cod_grupo, nome, proprietario FROM grupos";
            ResultSet result = st.executeQuery(sql);
            while(result.next()) {
                list.add(new Grupo(result.getString(2), result.getInt(3), result.getInt(1)));
            }
        return list;
    }
    
    static HashSet listAllWithProprietarios(Connection con) throws SQLException {
        Statement st;
        HashSet list = new HashSet();
        st = con.createStatement();
        String sql = "select cod_usuario, email, senha, usuarios.nome, grupos.nome, proprietario, cod_grupo from grupos join usuarios on grupos.proprietario = usuarios.cod_usuario";
        ResultSet result = st.executeQuery(sql);
        while(result.next()) {
            Usuario proprietario = new Usuario(result.getInt(1), result.getString(2), result.getString(3), result.getString(4));
            Grupo grupo = new Grupo(result.getString(5), result.getInt(6), result.getInt(7));
            grupo.setProprietario(proprietario);
            list.add(grupo);
        }
        return list;
    }
    
}

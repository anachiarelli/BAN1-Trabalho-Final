
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class UsuarioController {
    UsuarioModel usuarioModel;
    GrupoModel grupoModel;

    public UsuarioController(UsuarioModel usuarioModel, GrupoModel grupoModel) {
        this.usuarioModel = usuarioModel;
        this.grupoModel = grupoModel;
    }
    
    public UsuarioController() {
    }
    
    public void criaUsuario(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Insira os seguintes dados para a criar um novo Usuario: ");
        System.out.print("E-mail: ");
        String email = input.next();
        System.out.print("Senha: ");
        String senha = input.next();
        System.out.print("Nome: ");
        String nome = input.next();
        
        Usuario u = new Usuario(email, senha, nome);
        UsuarioModel usuarioModel = new UsuarioModel(con);
        usuarioModel.criaUsuario(u);
        System.out.println("Usuario inserido no banco");     
    }
    
    void listarUsuarios(Connection con) throws SQLException {
        UsuarioModel usuarioModel = new UsuarioModel(con);
        HashSet all = usuarioModel.listAll();
        Iterator<Usuario> it = all.iterator();
        while(it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }
    
    public void insereUsuarioEmGrupo(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Insira os seguintes dados para inserir um usuario em um grupo: ");
        System.out.print("Codigo do usuário: ");
        int cod_usuario = input.nextInt();
        System.out.print("Codigo do grupo ");
        int cod_grupo = input.nextInt();
        
        Usuario usuario = this.usuarioModel.findByID(cod_usuario);
        if (usuario == null) {
            System.out.println("Usuario inexistente");
            return;
        }
        
        Grupo grupo = this.grupoModel.findByID(cod_grupo);
        if (grupo == null) {
            System.out.println("Grupo inexistente");
            return;
        }
        
        usuarioModel.insereUsuario(usuario, grupo);
        System.out.println("Usuario inserido no grupo.");     
    }
    
    public void insereUsuarioEmGrupo(Connection con, Usuario usuario, Grupo grupo) throws SQLException {              
        usuarioModel.insereUsuario(usuario, grupo);
        System.out.println("Usuario inserido no grupo.");     
    }
}

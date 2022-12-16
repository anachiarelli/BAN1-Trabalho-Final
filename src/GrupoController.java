
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class GrupoController {
    UsuarioModel usuarioModel;
    GrupoModel grupoModel;
    
    public GrupoController(UsuarioModel usuarioModel, GrupoModel grupoModel) {
        this.usuarioModel = usuarioModel;
        this.grupoModel = grupoModel;
    }
    
    public void criaGrupo(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Insira os seguintes dados para a criar um novo Grupo: ");
        System.out.print("Nome: ");
        String nome = input.next();
        System.out.print("Codigo do proprietario: ");
        int codProprietario = input.nextInt();
        
        Usuario proprietario = this.usuarioModel.findByID(codProprietario);
        if (proprietario == null) {
            System.out.println("Proprietário inexistente");
            return;
        }
        
        Grupo g = new Grupo(nome, codProprietario);
        GrupoModel grupoModel = new GrupoModel(con);
        int cod_grupo = grupoModel.criaGrupo(g);
        System.out.println("Grupo inserido no banco");
        g.setCodGrupo(cod_grupo);
        this.usuarioModel.insereUsuario(proprietario, g);

    }
    
    void listarGrupos(Connection con) throws SQLException {
        GrupoModel grupoModel = new GrupoModel(con);
        HashSet all = grupoModel.listAll(con);
        Iterator<Grupo> it = all.iterator();
        while(it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }
    
    void listarGruposComProprietarios(Connection con) throws SQLException {
        GrupoModel grupoModel = new GrupoModel(con);
        HashSet all = grupoModel.listAllWithProprietarios(con);
        Iterator<Grupo> it = all.iterator();
        while(it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }
    
    void mostrarEmQuantosGruposEstaOProprietario(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Insira o codigo do grupo para verificar em quantos grupos seu proprietario esta: ");
        System.out.print("Codigo: ");
        int cod_grupo = input.nextInt();
        
        
        Grupo grupo = this.grupoModel.findByID(cod_grupo);
        if (grupo == null) {
            System.out.println("Grupo inexistente");
            return;
        }
        
        int numero_de_grupos = this.grupoModel.countOwnerParticipations(cod_grupo);
        System.out.println("O proprietario do grupo " + cod_grupo + " esta em "+ numero_de_grupos + " grupo(s)");      
    }
}

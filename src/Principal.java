/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Principal {

    public static void main(String[] args) throws SQLException {
        Conexao c = new Conexao();
        Connection con = c.getConnection();
        UsuarioModel usuarioModel = new UsuarioModel(con);
        GrupoModel grupoModel = new GrupoModel(con);
        UsuarioController usuarioController = new UsuarioController(usuarioModel, grupoModel);
        GrupoController grupoController = new GrupoController(usuarioModel, grupoModel);
        int op = 0;
        do{
            op = menu();
            try {
                switch (op) {
                    case 1:
                        new UsuarioController().criaUsuario(con);
                        break;
                    case 2:
                        new UsuarioController().listarUsuarios(con);
                        break;
                    case 3:
                        grupoController.criaGrupo(con);
                        break;
                    case 4:
                        grupoController.listarGrupos(con);
                        break;
                    case 5:
                        grupoController.listarGruposComProprietarios(con);
                        break;
                    case 6:
                        usuarioController.insereUsuarioEmGrupo(con);
                        break;
                    case 7:
                        grupoController.mostrarEmQuantosGruposEstaOProprietario(con);
                        break;
                }
            }catch(SQLException ex) {
                System.out.println(ex.getMessage());
                continue;
            }
        } while(op>0 && op<8);  
        con.close();
    }    
    
    private static int menu() {
        System.out.println("");
        System.out.println("Informe o numero da opcao que deseja executar: ");
        System.out.println("1 - Inserir um novo usuario");
        System.out.println("2 - Mostrar todos os usuarios");
        System.out.println("3 - Inserir um novo grupo");
        System.out.println("4 - Mostrar todos os grupos");
        System.out.println("5 - Mostrar os grupos e seus proprietarios");
        System.out.println("6 - Inserir um usuario em um grupo");
        System.out.println("7 - Mostrar em quantos grupos esta o proprietario de um grupo");
        System.out.println("Digite qualquer outro valor para sair");
        System.out.print("Sua opcao: ");
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }
}

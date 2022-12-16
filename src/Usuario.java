
public class Usuario {
    
    private int codUsuario;
    private String email;
    private String senha;
    private String nome;
    
    public Usuario(int codUsuario, String email, String senha, String nome) {
        this.codUsuario = codUsuario;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
    }
    
    public Usuario(String email, String senha, String nome) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
    }    

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Codigo: " + codUsuario + "  |  E-mail: " + email + "  |  Senha: " + senha + "  |  Nome: " + nome;
    }
    
}

public class Grupo {
    
    private int codGrupo;
    private String nome;
    private int codProprietario;
    private Usuario proprietario;

    public Grupo(String nome, int codProprietario, int codGrupo) {
        this.codGrupo = codGrupo;
        this.nome = nome;
        this.codProprietario = codProprietario;
    }
    
    public Grupo(String nome, int codProprietario) {
        this.nome = nome;
        this.codProprietario = codProprietario;
    }

    public int getCodProprietario() {
        return codProprietario;
    }

    public void setCodProprietario(int codProprietario) {
        this.codProprietario = codProprietario;
    }

    public int getCodGrupo() {
        return codGrupo;
    }

    public void setCodGrupo(int codGrupo) {
        this.codGrupo = codGrupo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Usuario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Usuario proprietario) {
        this.proprietario = proprietario;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Codigo: " + codGrupo + "  |  Nome: " + nome + "  |  Codigo do proprietario: " + codProprietario);
        if(proprietario != null)
            sb.append("  |  Proprietario: " + proprietario.getNome());
        return sb.toString();
    }
    
}

package br.com.goschool.goschool_mobile.Modulos;

public class Estudante {

    private int id;
    private String nome;
    private String cep;
    private int cpf;

    public String getEstudanteNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEstudanteNome(String nomeEstudante) {
        this.nome = nomeEstudante;
    }

    public String getEstudanteCEP() {
        return cep;
    }

    public void setEstudanteCEP(String cep) {
        this.cep = cep;
    }

    public int getEstudanteCPF() {
        return cpf;
    }

    public void setEstudanteCPF(int cpf) {
        this.cpf = cpf;
    }
    
}

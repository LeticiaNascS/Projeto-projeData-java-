import java.time.LocalDate;

public class Pessoa{
    private string nome;
    private LocalDate dataNascimento; //Protegendo dados com o private

    public Pessoa (string nome, LocalDate dataNascimento){ //Construtor
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }
    public String getNome(){
        return nome;
    }
    public LocalDate getDataNascimento(){
        return dataNasicmento;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setDataNascimento(LocalDate dataNascimento){
        this.dataNascimento;
    }
}
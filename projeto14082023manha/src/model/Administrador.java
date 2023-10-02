package model;

public class Administrador extends Pessoa {
    public Administrador(){
        super();
    }
    public Administrador(int id, String nome, String email, String senha){
        super(id, nome, email, senha);
    }
    public boolean validarLogin(){
        return true;
    }
}

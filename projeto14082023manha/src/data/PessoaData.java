package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Administrador;
import model.Funcionario;
import model.Pessoa;

public class PessoaData extends Conexao {
    public PessoaData() throws Exception{}
    public Pessoa validarLogin(String email, String senha) throws Exception{
        Pessoa obj = null;
        String sql = "Select * from Pessoas, Administradores where email=? and senha=? and Pessoas.id=Administradores.idPessoa";
        PreparedStatement ps = getConexao().prepareStatement(sql);
        ps.setString(1, email);
        ps.setString(2, senha);
        ResultSet rs = ps.executeQuery();
        if(rs.next())
            obj = new Administrador(rs.getInt("id"), rs.getString("nome"), rs.getString("email"),"");
        else {
            sql = "Select * from Pessoas, Funcionarios where email=? and senha=? and Pessoas.id=Funcionarios.idPessoa";
            ps = getConexao().prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, senha);
            rs = ps.executeQuery();
            if(rs.next())
                obj = new Funcionario(rs.getInt("id"), rs.getString("nome"), rs.getString("email"),"",rs.getString("cargo"));
            }
        return obj;
    }
}

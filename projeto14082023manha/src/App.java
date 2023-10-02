import java.util.ArrayList;
import java.util.Scanner;
import data.FuncionarioData;
import data.PessoaData;
import model.Administrador;
import model.Funcionario;
import model.Pessoa;

public class App {
    /**
     * @param args
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        try {
            //
            System.out.println("Digite seu e-mail");
            String email = entrada.next();
            System.out.println("Digite sua senha:");
            String senha = entrada.next();
            PessoaData DAOPessoa = new PessoaData();
            Pessoa usuario = DAOPessoa.validarLogin(email, senha);
            if(usuario instanceof Administrador)
            {
                System.out.println("Login de Administrador ok");
                Funcionario objFuncionario = new Funcionario();
                FuncionarioData DAO = new FuncionarioData();
                String opcao = new String();
                do {
                    System.out.println("Escolha uma opção:" +
                            "\n1 - Cadastrar Funcionario" +
                            "\n2 - Listar Funcionarios" +
                            "\n3 - Excluir Funcionario" +
                            "\n4 - Editar Funcionario" +
                            "\n5 - Pesquisar por nome" +
                            "\n6 - Sair");
                    opcao = entrada.next();
                    switch (opcao) {
                        case "1":
                            System.out.println("Digite o ID:");
                            int id = entrada.nextInt();

                            System.out.print("Digite o nome: ");
                            String nome = entrada.nextLine();

                            System.out.print("Digite o email: ");
                            email = entrada.nextLine();

                            System.out.print("Digite a senha: ");
                            senha = entrada.nextLine();

                            System.out.print("Digite o cargo: ");
                            String cargo = entrada.nextLine();
                            break;

                        case "2":
                            System.out.println("---------------LISTAGEM-----------");
                            ArrayList<Funcionario> listagem = new ArrayList<>();
                            listagem = DAO.listar();
                            if (listagem.size() == 0)
                                System.out.println("Não há registros");
                            else {
                                for (Funcionario funcionario : listagem) {
                                    System.out.println("id: " + funcionario.getId()
                                            + " Nome: " + funcionario.getNome()
                                            + " Email: " + funcionario.getEmail()
                                            + " Senha: " + funcionario.getSenha()
                                            + " Cargo: " + funcionario.getCargo());
                                }
                            }
                            break;

                        case "3":
                            System.out.print("Digite o id: ");
                            Integer Id = entrada.nextInt();

                            boolean excluidoComSucesso = DAO.excluir(Id);

                            if (excluidoComSucesso) {
                                System.out.println("Excluído com sucesso!");
                            } else {
                                System.out.println("Problemas ao excluir");
                            }
                            break;

                        case "4":
                            System.out.println("---------------LISTAGEM-----------");
                            listagem = new ArrayList<>();
                            listagem = DAO.listar();
                            if (listagem.size() == 0)
                                System.out.println("Não há registros");
                            else {
                                for (Funcionario funcionario : listagem) {
                                    System.out.println("id: " + funcionario.getId()
                                            + " Nome: " + funcionario.getNome()
                                            + " Email: " + funcionario.getEmail()
                                            + " Senha: " + funcionario.getSenha()
                                            + " Cargo: " + funcionario.getCargo());
                                }

                                System.out.println("Digite o id que deseja editar: ");
                                Integer idEdicao = entrada.nextInt();

                                objFuncionario = DAO.obter(idEdicao);

                                if (objFuncionario == null)
                                    System.out.println("Não encontrado");

                                else {

                                    System.out.println("Digite o nome:");
                                    objFuncionario.setNome(entrada.nextLine());

                                    System.out.println("Digite o email: ");
                                    objFuncionario.setEmail(entrada.nextLine());

                                    System.out.println("Digite a senha: ");
                                    objFuncionario.setSenha(entrada.nextLine());

                                    System.out.println("Digite o cargo: ");
                                    objFuncionario.setCargo(entrada.nextLine());

                                    if (DAO.alterar(objFuncionario))
                                        System.out.println("Alterado com sucesso!");
                                    else
                                        System.out.println("Problemas ao alterar");
                                }
                            }
                            break;

                        case "5":
                            System.out.println("Digite parte do nome a ser pesquisado");
                            nome = entrada.nextLine();

                            System.out.println("---------------LISTAGEM-----------");
                            listagem = new ArrayList<>();
                            listagem = DAO.listar(nome);
                            if (listagem.size() == 0)
                                System.out.println("Não há registros");
                            else {
                                for (Funcionario funcionario : listagem) {
                                    System.out.println("id: " + funcionario.getId()
                                            + " Nome: " + funcionario.getNome()
                                            + " Email: " + funcionario.getEmail()
                                            + " Senha: " + funcionario.getSenha()
                                            + " Cargo: " + funcionario.getCargo());
                                }
                            }
                            break;

                        default:
                            break;
                    }
                } while (!opcao.equals("6"));
            } if(usuario instanceof Funcionario){
                Funcionario objF = (Funcionario)usuario;
                System.out.println("Logado como funcionário");
                System.out.println("Você está logado como: "+objF.getNome() + " - "+objF.getCargo());
            }else {
                System.out.println("Login inválido");
            }
        } catch (Exception erro) {
            System.out.println("Erro: " + erro.getMessage());
        }
    }
}
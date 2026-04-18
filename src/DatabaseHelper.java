import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {

    private static final String URL = "jdbc:sqlite:funcionarios.db";

    public static void inicializarBanco() throws Exception {
        Class.forName("org.sqlite.JDBC");
        Connection conexao = DriverManager.getConnection(URL);
        Statement stmt = conexao.createStatement();

        stmt.execute(
            "CREATE TABLE IF NOT EXISTS funcionarios (" +
            "  id        INTEGER PRIMARY KEY AUTOINCREMENT," +
            "  nome      TEXT    NOT NULL," +
            "  nascimento TEXT   NOT NULL," +  
            "  salario   TEXT    NOT NULL," +
            "  funcao    TEXT    NOT NULL" +
            ")"
        );

        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS total FROM funcionarios");
        int total = rs.getInt("total");
        rs.close();

        if (total == 0) {
            stmt.execute("INSERT INTO funcionarios (nome, nascimento, salario, funcao) VALUES ('Maria',   '2000-10-18', '2009.44',  'Operador')");
            stmt.execute("INSERT INTO funcionarios (nome, nascimento, salario, funcao) VALUES ('João',    '1990-05-12', '2284.38',  'Operador')");
            stmt.execute("INSERT INTO funcionarios (nome, nascimento, salario, funcao) VALUES ('Caio',    '1961-05-02', '9836.14',  'Coordenador')");
            stmt.execute("INSERT INTO funcionarios (nome, nascimento, salario, funcao) VALUES ('Miguel',  '1988-10-14', '19119.88', 'Diretor')");
            stmt.execute("INSERT INTO funcionarios (nome, nascimento, salario, funcao) VALUES ('Alice',   '1995-01-05', '2234.68',  'Recepcionista')");
            stmt.execute("INSERT INTO funcionarios (nome, nascimento, salario, funcao) VALUES ('Heitor',  '1999-11-19', '1582.72',  'Operador')");
            stmt.execute("INSERT INTO funcionarios (nome, nascimento, salario, funcao) VALUES ('Arthur',  '1993-03-31', '4071.84',  'Contador')");
            stmt.execute("INSERT INTO funcionarios (nome, nascimento, salario, funcao) VALUES ('Laura',   '1994-07-08', '3017.45',  'Gerente')");
            stmt.execute("INSERT INTO funcionarios (nome, nascimento, salario, funcao) VALUES ('Heloísa', '2003-05-24', '1606.85',  'Eletricista')");
            stmt.execute("INSERT INTO funcionarios (nome, nascimento, salario, funcao) VALUES ('Helena',  '1996-09-02', '2799.93',  'Gerente')");

            System.out.println("Banco de dados criado e funcionários inseridos com sucesso.");
        }

        stmt.close();
        conexao.close();
    }

    public static List<Funcionario> buscarTodos() throws Exception {
        List<Funcionario> lista = new ArrayList<>();

        Class.forName("org.sqlite.JDBC");
        Connection conexao = DriverManager.getConnection(URL);
        Statement stmt = conexao.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT nome, nascimento, salario, funcao FROM funcionarios");

        while (rs.next()) {
            String nome       = rs.getString("nome");
            LocalDate data    = LocalDate.parse(rs.getString("nascimento"));
            BigDecimal salario = new BigDecimal(rs.getString("salario"));
            String funcao     = rs.getString("funcao");

            lista.add(new Funcionario(nome, data, salario, funcao));
        }

        rs.close();
        stmt.close();
        conexao.close();

        return lista;
    }
}

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Principal {

    public static void main(String[] args) {

        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        for (int i = 0; i < funcionarios.size(); i++) {
            if (funcionarios.get(i).getNome().equals("João")) {
                funcionarios.remove(i);
                break;
            }
        }

        System.out.println("\nLista de Funcionários");
        imprimirFuncionarios(funcionarios);

        for (Funcionario f : funcionarios) {
            BigDecimal novoSalario = f.getSalario().multiply(new BigDecimal("1.10"));
            f.setSalario(novoSalario);
        }

        System.out.println("\nFuncionários com 10% de aumento");
        imprimirFuncionarios(funcionarios);

        Map<String, List<Funcionario>> agrupados = new HashMap<>();
        for (Funcionario f : funcionarios) {
            String funcao = f.getFuncao();

            if (!agrupados.containsKey(funcao)) {
                agrupados.put(funcao, new ArrayList<>());
            }

            agrupados.get(funcao).add(f);
        }

        System.out.println("\nFuncionários agrupados por função");
        for (String funcao : agrupados.keySet()) {
            System.out.println("\nFunção: " + funcao);
            List<Funcionario> lista = agrupados.get(funcao);
            for (Funcionario f : lista) {
                System.out.println("  - " + f.getNome());
            }
        }

        System.out.println("\nAniversariantes de Outubro e Dezembro");
        for (Funcionario f : funcionarios) {
            int mes = f.getDataNascimento().getMonthValue();
            if (mes == 10 || mes == 12) {
                System.out.println("- " + f.getNome() + " (mês " + mes + ")");
            }
        }

        Funcionario maisVelho = funcionarios.get(0); 
        for (Funcionario f : funcionarios) {
            if (f.getDataNascimento().isBefore(maisVelho.getDataNascimento())) {
                maisVelho = f;
            }
        }
        int idade = Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears();

        System.out.println("\nFuncionário mais velho");
        System.out.println("Nome: " + maisVelho.getNome());
        System.out.println("Idade: " + idade);

        
        List<Funcionario> ordenados = new ArrayList<>(funcionarios);
        Collections.sort(ordenados, Comparator.comparing(Funcionario::getNome));

        System.out.println("\nFuncionários em ordem alfabética");
        for (Funcionario f : ordenados) {
            System.out.println("- " + f.getNome());
        }

        BigDecimal totalSalarios = BigDecimal.ZERO;
        for (Funcionario f : funcionarios) {
            totalSalarios = totalSalarios.add(f.getSalario());
        }

        System.out.println("\nTotal dos salários");
        System.out.println("Total: " + formatarNumero(totalSalarios));

        
        BigDecimal salarioMinimo = new BigDecimal("1212.00");

        System.out.println("\nQuantidade de salários mínimos por funcionário");
        for (Funcionario f : funcionarios) {
            BigDecimal qtdSalarios = f.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.println("- " + f.getNome() + ": " + qtdSalarios + " salários mínimos");
        }
    }

    
    public static void imprimirFuncionarios(List<Funcionario> lista) {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Funcionario f : lista) {
            String dataFormatada = f.getDataNascimento().format(formatoData);
            String salarioFormatado = formatarNumero(f.getSalario());

            System.out.println(
                "Nome: " + f.getNome() +
                " | Nascimento: " + dataFormatada +
                " | Salário: " + salarioFormatado +
                " | Função: " + f.getFuncao()
            );
        }
    }

    
    public static String formatarNumero(BigDecimal valor) {
        NumberFormat nf = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);
        return nf.format(valor);
    }
}
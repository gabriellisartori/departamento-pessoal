import model.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final NumberFormat CURRENCY_FORMATTER = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"));
    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");

    public static void main(String[] args) {
        List<Funcionario> funcionarios = criarFuncionarios();

        System.out.println("3.3 - LISTA INICIAL DE FUNCIONÁRIOS ");
        listarFuncionarios(funcionarios);

        removerJoao(funcionarios);
        System.out.println("\n após remover 'João'");
        listarFuncionarios(funcionarios);

        aumentoSalario(funcionarios, new BigDecimal("0.10")); // 10%
        System.out.println("\n 3.4 - APÓS AUMENTO DE 10% ");
        listarFuncionarios(funcionarios);

        System.out.println("\n 3.6 - FUNCIONÁRIOS AGRUPADOS POR FUNÇÃO");
        imprimirAgrupadosPorFuncao(agruparPorFuncao(funcionarios));

        System.out.println("\n 3.8 - ANIVERSARIANTES DOS MESES 10 E 12");
        imprimirAniversariantesOutubroDezembro(funcionarios);

        System.out.println("\n 3.9 - FUNCIONÁRIO COM MAIOR IDADE");
        imprimirMaiorIdade(funcionarios);

        System.out.println("\n 3.10 - FUNCIONÁRIOS EM ORDEM ALFABÉTICA");
        imprimirOrdemAlfabetica(funcionarios);

        System.out.println("\n 3.11 - TOTAL DOS SALÁRIOS ");
        imprimirTotalSalarios(funcionarios);

        System.out.println("\n 3.12 - QUANTIDADE DE SALÁRIOS MÍNIMOS POR FUNCIONÁRIO, CONSIDERANDO COMO BASE R$ 1.212,00");
        imprimirQtdSalariosMinimos(funcionarios);
    }

    private static List<Funcionario> criarFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.41"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.84"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
        return funcionarios;
    }

    private static void removerJoao(List<Funcionario> funcionarios) {
        funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase("João"));
    }

    private static void listarFuncionarios(List<Funcionario> funcionarios) {
        for (Funcionario f : funcionarios) {
            String dataFormatada = f.getDataNascimento().format(DATE_FORMATTER);
            String salarioFormatado = CURRENCY_FORMATTER.format(f.getSalario());
            System.out.printf("Nome: %-8s | Data Nascimento: %s | Salário: %-12s | Função: %s%n",
                    f.getNome(), dataFormatada, salarioFormatado, f.getFuncao());
        }
    }

    private static void aumentoSalario(List<Funcionario> funcionarios, BigDecimal percentual) {
        BigDecimal mef = BigDecimal.ONE.add(percentual); // ex: 1 + 0.10 = 1.10
        for (Funcionario f : funcionarios) {
            BigDecimal novoSalario = f.getSalario().multiply(mef);
            f.setSalario(novoSalario);
        }
    }

    private static Map<String, List<Funcionario>> agruparPorFuncao(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
    }

    private static void imprimirAgrupadosPorFuncao(Map<String, List<Funcionario>> agrupados) {
        agrupados.forEach((funcao, lista) -> {
            System.out.println("\nFunção: " + funcao);
            lista.forEach(f -> System.out.println("  - " + f.getNome()));
        });
    }

    private static void imprimirAniversariantesOutubroDezembro(List<Funcionario> funcionarios) {
        funcionarios.stream()
                .filter(f -> {
                    int mes = f.getDataNascimento().getMonthValue();
                    return mes == 10 || mes == 12;
                })
                .forEach(f -> System.out.printf("%s - Data Nascimento: %s%n",
                        f.getNome(), f.getDataNascimento().format(DATE_FORMATTER)));
    }

    private static void imprimirMaiorIdade(List<Funcionario> funcionarios) {
        Funcionario maisVelho = funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElse(null);

        if (maisVelho != null) {
            int idade = Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears();
            System.out.printf("Nome: %s | Idade: %d anos%n", maisVelho.getNome(), idade);
        }
    }

    private static void imprimirOrdemAlfabetica(List<Funcionario> funcionarios) {
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(f -> System.out.println(f.getNome()));
    }

    private static void imprimirTotalSalarios(List<Funcionario> funcionarios) {
        BigDecimal total = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("Total da Folha de Pagamento: " + CURRENCY_FORMATTER.format(total));
    }

    private static void imprimirQtdSalariosMinimos(List<Funcionario> funcionarios) {
        for (Funcionario f : funcionarios) {
            BigDecimal qtdSalarios = f.getSalario().divide(SALARIO_MINIMO, 2, RoundingMode.HALF_UP);
            System.out.printf("%-8s ganha %s salários mínimos.%n", f.getNome(), qtdSalarios);
        }
    }
}

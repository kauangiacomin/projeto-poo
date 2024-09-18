package primeirob.Aulas.projeto2;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

public class projeto2 {

    private static final String[] MOEDAS = {"USD", "EUR", "JPY", "GBP", "BRL"};
    private static final double[] TAXAS = {1.0, 0.85, 110.0, 0.75, 5.4}; // 1 USD -> taxa correspondente

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nEscolha uma funcionalidade:");
            System.out.println("1. Conversão de números inteiros para números romanos");
            System.out.println("2. Conversor de moedas");
            System.out.println("3. Sair");
            System.out.print("Digite a opção desejada: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    converterNumerosRomanos(scanner);
                    break;
                case 2:
                    conversorMoedas(scanner);
                    break;
                case 3:
                    System.out.println("Saindo do programa...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public static void converterNumerosRomanos(Scanner scanner) {
        System.out.print("Digite um número para converter para números romanos (1-3999): ");
        int numero = scanner.nextInt();
        if (numero < 1 || numero > 3999) {
            System.out.println("Número fora do intervalo. Digite um número entre 1 e 3999.");
            return;
        }
        String numeroRomano = toRoman(numero);
        System.out.println("O número " + numero + " em números romanos é: " + numeroRomano);
    }

    public static String toRoman(int number) {
        int[] valores = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] simbolos = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder romano = new StringBuilder();
        for (int i = 0; i < valores.length; i++) {
            while (number >= valores[i]) {
                number -= valores[i];
                romano.append(simbolos[i]);
            }
        }
        return romano.toString();
    }

    public static void conversorMoedas(Scanner scanner) {
        System.out.print("Digite o valor a ser convertido: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Digite a moeda de origem (USD, EUR, JPY, GBP, BRL): ");
        String moedaOrigem = scanner.nextLine().toUpperCase();

        System.out.print("Digite a moeda de destino (USD, EUR, JPY, GBP, BRL): ");
        String moedaDestino = scanner.nextLine().toUpperCase();

        try {
            double valorConvertido = convert(valor, moedaOrigem, moedaDestino);
            System.out.printf("%.2f %s é igual a %.2f %s\n", valor, moedaOrigem, valorConvertido, moedaDestino);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int findCurrencyIndex(String currency) {
        for (int i = 0; i < MOEDAS.length; i++) {
            if (MOEDAS[i].equalsIgnoreCase(currency)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Moeda não suportada: " + currency);
    }

    public static double convert(double amount, String fromCurrency, String toCurrency) {
        int fromIndex = findCurrencyIndex(fromCurrency);
        int toIndex = findCurrencyIndex(toCurrency);

        double valorEmUSD = amount / TAXAS[fromIndex];
        return valorEmUSD * TAXAS[toIndex];
    }
}

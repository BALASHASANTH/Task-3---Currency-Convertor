import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class SimpleCurrencyConverter {

    private static final Map<String, Double> EXCHANGE_RATES = new HashMap<>();

    static {
        EXCHANGE_RATES.put("USD_EUR", 0.91);
        EXCHANGE_RATES.put("USD_GBP", 0.81);
        EXCHANGE_RATES.put("EUR_USD", 1.10);
        EXCHANGE_RATES.put("GBP_USD", 1.23);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter base currency code (e.g., USD, EUR): ");
        String baseCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Enter target currency code (e.g., USD, EUR): ");
        String targetCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Enter amount to convert: ");
        double amount = getValidAmount(scanner);

        try {
            double rate = getExchangeRate(baseCurrency, targetCurrency);
            if (rate != -1) {
                double convertedAmount = amount * rate;
                System.out.printf("Converted amount: %.2f %s%n", convertedAmount, targetCurrency);
            } else {
                System.out.println("Exchange rate not available for the selected currencies.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }

    private static double getExchangeRate(String baseCurrency, String targetCurrency) {
        String key = baseCurrency + "_" + targetCurrency;
        return EXCHANGE_RATES.getOrDefault(key, -1.0);
    }

    private static double getValidAmount(Scanner scanner) {
        while (true) {
            try {
                double amount = Double.parseDouble(scanner.nextLine());
                if (amount >= 0) {
                    return amount;
                } else {
                    System.out.println("Amount must be non-negative.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}

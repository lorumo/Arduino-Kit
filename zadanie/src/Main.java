import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    static int whatsHappening;

    public static void main(String[] args) {
        Database database = DatabaseListProxy.getInstance(); // DatabaseListProxy

        Customer customer = new Customer("hot_cokolate", "password", "Roman", "NeRoman", "+380972347316");
        database.addCustomer(customer);


        Auto car1 = new AutoBuilder()
                .setBrand("Toyota")
                .setModel("Corolla")
                .setYear(2020)
                .setPrice(20000)
                .buildAuto();
        database.addAuto(car1);

        Auto car2 = new AutoBuilder()
                .setBrand("Honda")
                .setModel("Civic")
                .setYear(2018)
                .setPrice(18000)
                .buildAuto();
        database.addAuto(car2);

        Auto car3 = new AutoBuilder()
                .setBrand("Ford")
                .setModel("Fusion")
                .setYear(2019)
                .setPrice(22000)
                .buildAuto();
        database.addAuto(car3);

        Scanner scanner = new Scanner(System.in);

        int action;
        Invoice invoice = null;
        do {
            System.out.println("Choose an action:");
            System.out.println("1. Add a new car and get an invoice");
            System.out.println("2. Reserve a car");

            action = getIntInput(scanner, "Enter your choice: ", 1, 2);

            if (action == 1) {
                whatsHappening = 1;
                // Adding a new car and getting an invoice
                System.out.println("Enter brand:");
                String brand = scanner.nextLine();
                System.out.println("Enter model:");
                String model = scanner.nextLine();
                int year = getIntInput(scanner, "Enter year: ", 1900, 2100);
                float price = getFloatInput(scanner, "Enter price: ");

                AutoBuilder autoBuilder = new AutoBuilder(); // AutoBuilder car
                Auto auto = autoBuilder.setBrand(brand).setModel(model).setYear(year).setPrice(price).buildAuto();
                database.addAuto(auto);
                customer.addCar(auto);

                float moneyYouget = price * 0.75f; // 25% discount muhehehe
                invoice = new Invoice(Arrays.asList(auto));
                invoice.setCustomerName(customer.getCustomerName());
                printInvoice(invoice, moneyYouget);
            } else if (action == 2) {
                whatsHappening = 2;
                // Reserving a car
                List<Auto> availableCars = database.getAvailableCars();
                if (availableCars.isEmpty()) {
                    System.out.println("No cars available for reservation.");
                    continue;
                }

                System.out.println("Available cars:");
                for (int i = 0; i < availableCars.size(); i++) {
                    Auto car = availableCars.get(i);
                    System.out.println((i + 1) + ". " + car.getBrand() + " " + car.getModel() + " (" + car.getYear() + ")");
                }

                int carIndex = getIntInput(scanner, "Choose a car to reserve by entering its number: ", 1, availableCars.size()) - 1;
                Auto selectedCar = availableCars.get(carIndex);
                System.out.println("You are reserving the following car:");
                System.out.println("Brand: " + selectedCar.getBrand());
                System.out.println("Model: " + selectedCar.getModel());
                System.out.println("Year: " + selectedCar.getYear());

                String startDateStr = getDateInput(scanner, "Enter reservation start date (YYYY-MM-DD): ");
                String endDateStr = getDateInput(scanner, "Enter reservation end date (YYYY-MM-DD): ");

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date startDate = dateFormat.parse(startDateStr);
                    Date endDate = dateFormat.parse(endDateStr);

                    long days = (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);
                    float totalReservationPrice = days * 100; // $100 per day

                    Reservation reservation = customer.createReservation(selectedCar);
                    invoice = new Invoice(Arrays.asList(selectedCar)); // invoice
                    invoice.setCustomerName(customer.getCustomerName());
                    printInvoice(invoice, totalReservationPrice);
                } catch (Exception e) {
                    System.out.println("Invalid date format. Please enter dates in YYYY-MM-DD format.");
                }
            } else {
                System.out.println("Invalid choice.");
            }
        } while (action != 1 && action != 2);

        scanner.close();
    }

    private static int getIntInput(Scanner scanner, String prompt, int min, int max) {
        int input;
        do {
            try {
                System.out.print(prompt);
                input = Integer.parseInt(scanner.nextLine());
                if (input < min || input > max) {
                    System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
                } else {
                    return input;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer.");
            }
        } while (true);
    }

    private static float getFloatInput(Scanner scanner, String prompt) {
        float input;
        do {
            try {
                System.out.print(prompt);
                input = scanner.nextFloat();
                scanner.nextLine();
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a floating-point number.");
                scanner.nextLine();
            }
        } while (true);
    }

    private static String getDateInput(Scanner scanner, String prompt) {
        String input;
        do {
            try {
                System.out.print(prompt);
                input = scanner.nextLine();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateFormat.setLenient(false);
                dateFormat.parse(input);
                return input;
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter dates in YYYY-MM-DD format.");
            }
        } while (true);
    }

    private static void printInvoice(Invoice invoice, float totalPrice) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDate = dateFormat.format(new Date());

        System.out.println("Your Invoice:");
        System.out.println("Date: " + currentDate);
        System.out.println("Customer: " + invoice.getCustomerName());
        if (whatsHappening == 1) {
            System.out.println("Total amount you get (-25% for the company): $" + totalPrice);
        } else {
            System.out.println("Total Price: $" + totalPrice);
        }
    }
}
// better view at my composite realisation

//public class Main {
//    public static void main(String[] args) {
//
//        List<Auto> cars = new ArrayList<>();
//
//
//        Auto car1 = new AutoBuilder()
//                .setBrand("Toyota")
//                .setModel("Corolla")
//                .setYear(2020)
//                .setPrice(1000)
//                .buildAuto();
//        cars.add(car1);
//
//        Auto car2 = new AutoBuilder()
//                .setBrand("Honda")
//                .setModel("Civic")
//                .setYear(2018)
//                .setPrice(1000)
//                .buildAuto();
//        cars.add(car2);
//
//        Invoice invoice = new Invoice(cars);
//        invoice.setCustomerName("Romanko");
//        // money $$$ counting using composite pattern
//        float totalPrice = invoice.getPrice();
//        System.out.println("Total Price: $" + totalPrice);
//    }
//}

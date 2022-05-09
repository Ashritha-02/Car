import java.util.Date;
import java.util.HashMap;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Scanner;

public class App {
    private static Scanner scanner;
    private static HashMap<String, Car> cars;

    private static double calculateTotalTime(String entryTime, String exitTime) throws ParseException {
        DateFormat timeFormat = new SimpleDateFormat("hh:mm-aa");// converts string to date

        Date start = timeFormat.parse(entryTime);
        Date end = timeFormat.parse(exitTime);// returns in ms

        double totalTime = 0;
        if (start.before(end)) {// checks whether start time starts before end time
            double deltaTime = (end.getTime() - start.getTime()) / 1000;// ms-sec
            totalTime = Math.ceil(deltaTime / 60);// sec-min
        }
        return totalTime;
    }

    private static void displayMenu() {
        System.out.println("WELCOME TO PARKING MANAGEMENT SYSTEM");
        System.out.println("-------------------------------------");
        System.out.println("1. Park A Vehicle.");
        System.out.println("2. Take A Vehicle");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    public static void main(String[] args) throws Exception {
        cars = new HashMap<String, Car>();
        scanner = new Scanner(System.in);
        int choice, limit = 20, count = 0;

        try {
            do {
                displayMenu();
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        if (++count <= limit) {
                            System.out.print("Enter The Registration Number: ");
                            String registrationNumber = scanner.next();

                            System.out.print("Enter Entry Time: hh:mm-aa ");
                            String entryTime = scanner.next();

                            Car car = new Car(registrationNumber);
                            car.setEntryTime(entryTime);
                            cars.put(registrationNumber, car);

                            System.out.println("Car Successfully Parked!");
                        } else {
                            System.out.println("Parking Slots Full!!");
                        }
                        System.out.println();
                        break;

                    case 2:
                        System.out.print("Enter the Registration Number: ");
                        String registerNumber = scanner.next();

                        if (cars.containsKey(registerNumber)) {
                            System.out.print("Enter Exit Time: (hh:mm-aa)");
                            String exitTime = scanner.next();

                            Car car = cars.get(registerNumber);
                            car.setExitTime(exitTime);

                            double totalTime = calculateTotalTime(car.getEntryTime(), car.getExitTime());
                            System.out.println(String.format("Time Parked: %.2f minutes  and You have to pay: Rs. %.2f",
                                    totalTime, totalTime * 2));

                        } else
                            System.out.println("Car not parked");
                        System.out.println();
                        break;
                    case 3:

                }
            } while (choice > 0 && choice < 3);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}

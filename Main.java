import java.util.Scanner;

public class Main {
    private static Object productManager;

    public static void main(String[] args) {
        ProductManager productManager = new ProductManager();

        Product e2 = new Electronics("Electronis2", 7.2);
        Product e1 = new Electronics("Electronis1", 52.5);
        Product e3 = new Electronics("Electronis3", 82.4);

        Product c3 = new Clothing("Clothing3", 66.5);
        Product c1 = new Clothing("Clothing1", 27.2);
        Product c2 = new Clothing("Clothing2", 87.4);

        Product f1 = new Food("Food1", 1.21);
        Product f3 = new Food("Food3", 9.1);
        Product f2 = new Food("Food2", 6.2);

        productManager.addNewCategory("Naujausi");
        productManager.addNewCategory("Akcijos");
        productManager.filterByCategory("Naujausi").add(e2);
        productManager.filterByCategory("Naujausi").add(e1);
        productManager.filterByCategory("Naujausi").add(e3);
        productManager.filterByCategory("Naujausi").add(c3);
        productManager.filterByCategory("Akcijos").add(f1);
        productManager.filterByCategory("Akcijos").add(f2);
        productManager.filterByCategory("Akcijos").add(f3);
        productManager.filterByCategory("Akcijos").add(c1);
        productManager.filterByCategory("Akcijos").add(c2);

        productManager.showAllCategories();

        int choice = 1;

        do {
        menu();

        Scanner scanner = new Scanner(System.in);
        choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Pasirinkote: 1. Pridėti kategoriją");
                System.out.println("Įrašykite kategorijos pavadinimą:");
                scanner.nextLine();
                productManager.addNewCategory(scanner.nextLine());
                break;
            case 2:
                System.out.println("Pasirinkote: 2. Pridėti prekę");
                System.out.println("Įrašykite kategorijos pavadinimą:");
                scanner.nextLine();
                productManager.filterByCategory(scanner.nextLine()).add(new Electronics(scanner.nextLine(),scanner.nextDouble()));



                break;
            case 3:
                System.out.println("Pasirinkote: 3. Ieškoti prekių pagal pavadinimą");
                System.out.println("Įrašykite prekės pavadinimą:");
                scanner.nextLine();
                productManager.getProductByName(scanner.nextLine()).displayInfo();

                break;
            case 4:
                System.out.println("Pasirinkote: 4. Filtruoti prekes pagal kategoriją");
                System.out.println("Įrašykite kategorijos pavadinimą:");
                scanner.nextLine();

                for (Product product:productManager.filterByCategory(scanner.nextLine())                     ) {
                    product.displayInfo();
                }
                break;
            case 5:
                System.out.println("Pasirinkote: 5. Filtruoti prekes pagal kainą");
                System.out.println("Įveskite min kainą");
                double min = scanner.nextDouble();
                System.out.println("Įveskite max kainą");
                double max = scanner.nextDouble();

                productManager.filterByPriceInterval(min,max);


                break;
            case 6:
                System.out.println("Pasirinkote: 6. Rūšiuoti prekes pagal kainą");
                System.out.println("Pasirinkote: 5. Filtruoti prekes pagal kainą");
                System.out.println("Rušiuoti didėjimo tvarka: 1, mažėjimo 0");
                boolean highLow = scanner.nextBoolean();
                System.out.println("Pries");
                productManager.showAllProducts();
                System.out.println("Po");
                productManager.sortByProductPrice(highLow);
                break;
            case 7:
                System.out.println("Pasirinkote: 7. Rodyti visas prekes");
                productManager.showAllProducts();
                break;
            case 8:
                System.out.println("Pasirinkote: 8. Išeiti");
                break;
           default:
               System.out.println("Netinkamas pasirinkimas");
                break;

        }} while (choice != 8);
    }


    public static void menu(){
        System.out.println("-----------------------------------------");
        System.out.println("1. Pridėti kategoriją");
        System.out.println("2. Pridėti prekę");
        System.out.println("3. Ieškoti prekių pagal pavadinimą");
        System.out.println("4. Filtruoti prekes pagal kategoriją");
        System.out.println("5. Filtruoti prekes pagal kainą");
        System.out.println("6. Rūšiuoti prekes pagal kainą");
        System.out.println("7. Rodyti visas prekes");
        System.out.println("8. Išeiti");
        System.out.println("Pasirinkite veiksmą:");
    }
}
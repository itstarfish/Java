import java.util.Date;
public class Main {
    public static void main(String[] args) {
        Customer c1 = new Customer("Jonas");
        c1.setMember(true);
        c1.setMemberType("Premium");

        Customer c2 = new Customer("Tadas");
        c2.setMember(true);
        c2.setMemberType("Gold");

        Customer c3 = new Customer("Simas");
        c3.setMember(false);

        Visit v1 = new Visit(c1.getName(), new Date());
        v1.setServiceExpense(100);
        v1.setProductExpense(50);

        Visit v2 = new Visit(c2.getName(), new Date());
        v2.setServiceExpense(100);
        v2.setProductExpense(50);

        Visit v3 = new Visit(c3.getName(), new Date());
        v3.setServiceExpense(100);
        v3.setProductExpense(50);

        System.out.println("Total expense for " + c1.getName() + ": " + Total(v1, c1));
        System.out.println("Total expense for " + c2.getName() + ": " + Total(v2, c2));
        System.out.println("Total expense for " + c3.getName() + ": " + Total(v3, c3));
    }

    public static double Total(Visit visit, Customer customer) {
        DiscountRate discountRate = new DiscountRate();
        double serviceDiscount;
        if (customer.isMember()) {
            serviceDiscount = discountRate.getServiceDiscountRate(customer.getMemberType());
        } else {
            serviceDiscount = 0;
        }

        double productDiscount;
        if (customer.isMember()) {
            productDiscount = discountRate.getProductDiscountRate(customer.getMemberType());
        } else {
            productDiscount = 0;
        }
        double totalServiceExpense = visit.getServiceExpense() * (1 - serviceDiscount);
        double totalProductExpense = visit.getProductExpense() * (1 - productDiscount);

        return totalServiceExpense + totalProductExpense;
    }
}
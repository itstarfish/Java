public class DiscountRate {
    static double serviceDiscountPremium = 0.2;
    static double serviceDiscountGold = 0.15;
    static double serviceDiscountSilver = 0.1;
    static double productDiscountPremium = 0.1;
    static double productDiscountGold= 0.1;
    static double productDiscountSilver = 0.1;

    public double getServiceDiscountRate(String type){
        if (type == "Premium") return serviceDiscountPremium;
        if (type == "Gold") return serviceDiscountGold;
        if (type == "Silver") return serviceDiscountSilver;
        else return 0;
    }

    public double getProductDiscountRate(String type){
        if (type == "Premium") return productDiscountPremium;
        if (type == "Gold") return productDiscountGold;
        if (type == "Silver") return productDiscountSilver;
        else return 0;
    }
}

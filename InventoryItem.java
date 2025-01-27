public class InventoryItem {
    private Product product;
    private int qtyTotal;
    private int qtyReserved;
    private int qtyReorder;
    private int qtyLow;
    private int salesPrice;

    public InventoryItem(Product product, int qtyTotal, int qtyReorder, int qtyLow, int salesPrice) {
        this.product = product;
        this.qtyTotal = qtyTotal;
        this.qtyReorder = qtyReorder;
        this.qtyLow = qtyLow;
        this.salesPrice = salesPrice;
    }

    public void reserveItem(){
        //not yet sold
    }

    public void releaseItem(){

    }

    public void sellItem(){

        if (qtyTotal < qtyLow){
            placeInventoryOrder(qtyReorder);
        }
    }

    public void placeInventoryOrder(int qtyReorder){
        this.qtyTotal += qtyReorder;
    }
}

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
        this.qtyReserved = 0;
    }

    public void reserveItem(){
        if (qtyTotal > 0){
            qtyReserved++;
            qtyTotal--;
        }
    }

    public void releaseItem(){
        if (qtyReserved>0){
            qtyReserved--;
            qtyTotal++;
        }
    }

    public void sellItem(){
        if (qtyReserved > 0){
            qtyReserved--;
        } else if (qtyTotal > 0) {
            qtyTotal--;
        }

        if (qtyTotal < qtyLow){
            placeInventoryOrder(qtyReorder);
        }
    }

    public void placeInventoryOrder(int qtyReorder){
        this.qtyTotal += qtyReorder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQtyTotal() {
        return qtyTotal;
    }

    public void setQtyTotal(int qtyTotal) {
        this.qtyTotal = qtyTotal;
    }

    public int getQtyReserved() {
        return qtyReserved;
    }

    public void setQtyReserved(int qtyReserved) {
        this.qtyReserved = qtyReserved;
    }

    public int getQtyReorder() {
        return qtyReorder;
    }

    public void setQtyReorder(int qtyReorder) {
        this.qtyReorder = qtyReorder;
    }

    public int getQtyLow() {
        return qtyLow;
    }

    public void setQtyLow(int qtyLow) {
        this.qtyLow = qtyLow;
    }

    public int getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(int salesPrice) {
        this.salesPrice = salesPrice;
    }
}

public enum Category {
    PRODUCE("Produce"),
    DAIRY("Diary"),
    CEREAL("Cereal"),
    MEAT("Meat"),
    BEVERAGE("Beverage");

    private String categoryName;
    private Category(String name) {
        this.categoryName = name;
    }

    public String getCategoryName() {
        return categoryName;
    }
}

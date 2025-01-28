public class Food extends Item {

    private String typeOfStorage;
    private String typeOfFood;


    public Food(String name, double price, int stock, double weight, String typeOfStorage, String typeOfFood) {
        super(name, price, stock, weight);
        this.typeOfStorage = typeOfStorage;
        this.typeOfFood = typeOfFood;
    }


    public String getTypeOfStorage() {
        return typeOfStorage;
    }


    public void setTypeOfStorage(String typeOfStorage) {
        this.typeOfStorage = typeOfStorage;
    }


    public String getTypeOfFood() {
        return typeOfFood;
    }


    public void setTypeOfFood(String typeOfFood) {
        this.typeOfFood = typeOfFood;
    }


    

    


    

    
    
}

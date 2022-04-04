import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        LocalTime currentTime = getCurrentTime();
        if(currentTime.compareTo(openingTime) >= 0 && currentTime.compareTo(closingTime) <= 0)
            return true;
        return false;
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        return menu;
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

    /*
    This method was added as part of implementation the missing feature.
    Requirements:
    1. This method should receive list of menu names as String as parameters.
    2. It should check the price for each item and add them all to calculate final cost.
    3. Returns the total cost of selecting the menus, and it will be the total order price.
    */
    public int getOrderPrice(List<String> menuNames) {
        int price = 0;
        Map<String, Integer> nameToPriceMap = new HashMap<>();
        for(Item m : menu) {
            nameToPriceMap.put(m.getName(), m.getPrice());
        }

        for(String name : menuNames) {
            if(nameToPriceMap.containsKey(name)) {
                price += nameToPriceMap.get(name);
            }
        }
        return price;
    }
}

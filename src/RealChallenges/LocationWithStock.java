package RealChallenges;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/*
Coding Question:
Juan Hernandez is a Shopify merchant that owns a Pepper sauce shop with five locations:
Toronto, Vancouver, Montreal, Calgary and Halifax.
He also sells online and ships his sauces across the country from one of his brick-and-mortar locations.

The pepper sauces he sells are:

Jalapeño (J)
Habanero (H)
Serrano (S)

The inventory count for each location looks like this:

City	    J	H	S
Toronto	    5	0	0
Vancouver	10	2	6
Montreal	3	5	5
Calgary	    1	18	2
Halifax	    28	2	12

Every time he gets an online order, he needs to figure out
which locations can fulfill that order. Write a function that
takes an order as input and outputs a list of locations which
have all the items in stock.

Example
Input : J:3. H:2 S:4
Output: Van, Mon, Hali

Input: H:7 S:1
Output: Cal

 */
public class LocationWithStock {

    public static Map<String, Map<SauceType, Integer>> citiesInventory = Map.of(
            "Toronto", Map.of(SauceType.JALAPEÑO, 5, SauceType.HABANERO, 0, SauceType.SERRANO, 0),
            "Vancouver", Map.of(SauceType.JALAPEÑO, 10, SauceType.HABANERO, 2, SauceType.SERRANO, 6),
            "Montreal", Map.of(SauceType.JALAPEÑO, 3, SauceType.HABANERO, 5, SauceType.SERRANO, 5),
            "Calgary", Map.of(SauceType.JALAPEÑO, 1, SauceType.HABANERO, 18, SauceType.SERRANO, 2),
            "Halifax", Map.of(SauceType.JALAPEÑO, 28, SauceType.HABANERO, 2, SauceType.SERRANO, 12)
    );

    public static void main(String[] args) {
        Item item1 = new Item(SauceType.JALAPEÑO, 3);
        Item item2 = new Item(SauceType.HABANERO, 2);
        Item item3 = new Item(SauceType.SERRANO, 4);
        List<Item> order = List.of(item1, item2, item3);
        List<String> citiesWithStock = citiesWithStock(order);
        System.out.println(citiesWithStock);

        Item item4 = new Item(SauceType.HABANERO, 7);
        Item item5 = new Item(SauceType.SERRANO, 1);
        List<Item> order2 = List.of(item4, item5);
        List<String> citiesWithStock2 = citiesWithStock(order2);
        System.out.println(citiesWithStock2);

    }

    public static List<String> citiesWithStock(List<Item> order) {
        Set<String> cityNoStock = new HashSet<>();
        Set<String> citiesWithStock = new HashSet<>();

        for (String city : citiesInventory.keySet()) {
            if (!cityNoStock.contains(city)) {
                for (Item item : order) {
                    Map<SauceType, Integer> cityStock = citiesInventory.get(city);
                    if (cityStock.getOrDefault(item.getSouse(), 0) >= item.getAmount()) {
                        citiesWithStock.add(city);
                    } else {
                        cityNoStock.add(city);
                    }
                }
            }
        }
        citiesWithStock.removeAll(cityNoStock);
        return citiesWithStock.stream().toList();
    }

    enum SauceType {
        JALAPEÑO, HABANERO, SERRANO
    }

    static class Item {
        SauceType souse;
        int amount;

        public Item(SauceType sauce, int amount) {
            this.souse = sauce;
            this.amount = amount;
        }

        public SauceType getSouse() {
            return souse;
        }

        public int getAmount() {
            return amount;
        }
    }

}

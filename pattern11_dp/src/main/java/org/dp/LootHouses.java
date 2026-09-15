package org.dp;

public class LootHouses {

    public static void main(String[] args) {

        int[] houses = new int[]{10,5,4,8,6};
        System.out.println("Maximum money the theif can loot: " + lootHousesDp(houses));
        System.out.println("Maximum money the theif can loot: " + lootHouses(houses));
    }

    private static int lootHousesDp(int[] houses) {

        int n = houses.length;
        int[] storage = new int[n];
        storage[0] = houses[0];
        storage[1] = Math.max(houses[0], houses[1]);
        for(int i = 2; i < n; i++){
            storage[i] = Math.max(storage[i - 2] + houses[i], storage[i - 1]);
        }
        return storage[n - 1];
    }

    private static int lootHouses(int[] houses) {
        return lootHouses(houses, 0);
    }

    private static int lootHouses(int[] houses, int i) {
        if(i >= houses.length) return 0;

        int op1 = houses[i] + lootHouses(houses, i + 2);
        int op2 = lootHouses(houses, i + 1);
        return Math.max(op1, op2);
    }
}

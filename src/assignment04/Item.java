package assignment04;

import java.util.Comparator;

public abstract class Item {
	// Declare attributes here
	
	private String name;
	private double weight;  //weight in pounds
	private double value;   //value in credits
	private int durability;
	private int id;			//unique id for each item
	private static int counter;
	private static double totalWeight;
	
	
	public Item() {
		
	}
/*
 * abstract class. not able to use constructor directly to create Item objects
 * 	
	public Item(String name, double weight, double value, int durability){ //constructor for when weight is in tons
		this.name = name;
		this.weight = weight*2000;
		this.value = value;
		this.durability = durability;
		this.id = ++counter;
		totalWeight += weight*2000;
	}
	
	// Create an overridden constructor here

	public Item(String name, int weightPounds, int weightOunces, double value, int durability){ //constructor for when weight is in pounds and ounces
		this.name = name;
		this.weight = (double)weightPounds + ((double)weightOunces)/16;
		this.value = value;
		this.durability = durability;
		this.id = ++counter;
		totalWeight += (double)weightPounds + (double)weightOunces/16;
	}
*/
	
	
	// Create accessors and mutators for your traits
	
	static int getCounter() {
		return counter;
	}
	
	public static void resetCounter() {
		counter = 0;
	}
	
	public static void resetTotalWeight() {
		totalWeight = 0;
	}
	
	public int getId() {
		return id;
	}
	
	public int getDurability(){
		return durability;
	}
	
	public double getValue() {
		return value;
	}
	
	public double getWeight() {
		return weight;
	}

	
	public String getName() {
		return name;
	}

	
	public void setId() {
		this.id = ++counter;
	}
	
	public void setID2(int ID) {
		this.id = ID;
		if (ID > counter) {
			counter = ID;
		}
		// ++counter;
	}
	public void setWeight(double weight) {
		this.weight = weight;
		totalWeight += weight;
	}
	
	public void setDurability(int durability) {
		this.durability = durability;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "\n\tID: " + id + "\n\tName: " + name + "\n\tValue: " + value + " credits\n\tWeight: " + weight + " pounds\n\tDurability: " + durability;
				
	}
	// comparator used to sort ArrayList
	public static Comparator<Item> ItemNameComparator = new Comparator<Item>() {

		public int compare(Item n1, Item n2) {
			  String ItemName1 = n1.getName().toUpperCase();
			  String ItemName2 = n2.getName().toUpperCase();

			  //ascending order sort
			  return ItemName1.compareTo(ItemName2);


		    }};



	public static double getTotalWeight() {
		return totalWeight;
	}

	public static void reduceTotalWeight(double reduceWeight) {
		Item.totalWeight -= reduceWeight;
	}
	

	
	protected abstract String listInfo();
		
	
	protected abstract String backupText();
	}




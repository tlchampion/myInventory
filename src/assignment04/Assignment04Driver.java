package assignment04;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;



public class Assignment04Driver {
	Scanner input = new Scanner(System.in);
	public ArrayList<Double> score = new ArrayList<Double>();
	ArrayList<Double> weights = new ArrayList<Double>();
	public static void main(String[] args) throws IOException {
		new Assignment04Driver();
	}

	// This will act as our program switchboard
	public Assignment04Driver() throws IOException, InputMismatchException {
		ArrayList<Item> cargohold = new ArrayList<Item>();
		
		// create array list to hold weight values
		
		
		
	
		

		System.out.println("Welcome to the BlackStar Cargo Hold interface.");
		System.out.println("Loading stored database file, please wait....");
		loadDB(cargohold, weights);
		
		
		
		System.out.println("Please select a number from the options below");
		System.out.println("");

		while (true) {
			// Give the user a list of their options
			System.out.println("1: Add an item to the cargo hold.");
			System.out.println("2: Remove an item from the cargo hold.");
			System.out.println("3: Sort the contents of the cargo hold.");
			System.out.println("4: Search for an item.");
			System.out.println("5: Display the items in the cargo hold.");
			System.out.println("6: Decode Message");
			System.out.println("7. Create Text File of Inventory");
			System.out.println("8. Load Inventory from File");
			System.out.println("0: Exit the BlackStar Cargo Hold interface.");

			// Get the user input
			
			int userChoice = enterInt(0, 8);
			input.nextLine();

			switch (userChoice) {
			case 1:
				addItem(cargohold,weights);
				break;
			case 2:
				removeItem(cargohold,weights);
				break;
			case 3:
				sortItems(cargohold);
				break;
			case 4:
				searchItems(cargohold);
				break;
			case 5:
				displayItems(cargohold);
				break;
			case 6:
				decodeMessage();
				break;
			case 7:
				writeBackup(cargohold);
				break;
			case 8:
				loadFile(cargohold, weights);
				break;
		

			
			case 0:
				System.out.println("Thank you for using the BlackStar Cargo Hold interface. See you again soon!");
				System.exit(0);
			}
		}

	}

	private void addItem(ArrayList<Item> cargohold, ArrayList<Double> weights) {
		// TODO: Add an item that is specified by the user
		System.out.println("Which category of item will you be entering:\n1. Equipable\n2. Consumable\n3. Weapon");
		int userChoice = enterInt(1, 3);
		input.nextLine();
		switch(userChoice) {
		case 1:
			addEquipable(cargohold, weights);
			break;
		case 2:
			addConsumable(cargohold, weights);
			break;
		case 3:
			addWeapon(cargohold, weights);
			break;
		}
		
		
	
		input.nextLine();
		promptEnterKey();
	}
	
	private void addEquipable(ArrayList<Item> cargohold, ArrayList<Double> weights) {
		//TODO create method
		double currentWeight = sumWeight(weights);
		int weightPounds = 0;
		int weightOunces = 0;
		double weightTons = 0;
		double newWeight = 0;
		System.out.println("Please enter the name of the item you are adding: ");
		String name = input.nextLine();
		System.out.println("Please choose how you will enter the item's weight:\n1. In pounds and ounces\n2. In tons");
		int inputMethod = enterInt(1,2);
		if(inputMethod == 1) {
			System.out.println("Please enter the number of pounds");
			weightPounds = enterInt(0,-1);
			System.out.print("Please enter the number of ounces");
			weightOunces = enterInt(0,15);
			newWeight = currentWeight + weightPounds + weightOunces/16;
		} else if (inputMethod == 2) {
			System.out.println("Please enter the weight of the item in tons (i.e. 1.45):");
			weightTons = enterDouble();
			newWeight = currentWeight + weightTons*2000;
		}
		System.out.println("Please enter the value in credits of the item you are adding (i.e. 32.45)");
		double value = enterDouble();
		System.out.println("Please indicate the durability of the item you are adding:\n1. Very Low\n2. Low\n3. Moderate\n4. High\n5. Exceptional");
		int durability = enterInt(1,5);
		input.nextLine();
		System.out.println("Please enter the type of item being entered:\n1. Protective Gear\n2. Luxury Item\n3. Clothing\n4. Functional Item");
		int userInput = enterInt(1,4);
		String use = "";
		switch (userInput) {
			case 1:
				use = "Protective Gear";
				break;
			case 2:
				use = "Luxury Item";
				break;
			case 3:
				use = "Clothing";
				break;
			case 4:
				use = "Functional Item";
				break;
		}
		System.out.println("Please enter the demand level for the item:\n1. High\n2. Medium\n3. Low");
		userInput = enterInt(1,3);
		String demand = "";
		switch(userInput) {
		case 1:
			demand = "High";
			break;
		case 2:
			demand = "Medium";
			break;
		case 3:
			demand = "Low";
			break;
		}
		
		System.out.println("Please enter the material the item is constructed from:\n1. Cloth/Textile\n2. Metal\n3. Manufactured\n4. Other/Unknown");
		String material = "";
		userInput = enterInt(1,4);
		switch(userInput) {
		case 1:
			material = "Cloth/Textile";
			break;
		case 2:
			material = "Metal";
			break;
		case 3:
			material = "Manufactured";
			break;
		case 4:
			material = "Other/Unknown";
			break;
		}
		
		
		System.out.println("Please enter the grade for the item:\n1. Military\n2. Civilian");
		String grade = "";
		userInput = enterInt(1,2);
		switch(userInput) {
		case 1:
			grade = "Military";
			break;
		case 2: 
			grade = "Civilian";
			break;
		}
		
		
		
		if (newWeight > 50000) {
			System.out.println("Unable to add "+ name + " to storage. Total weight would exceed maximum of 25 tons. Current total weight is " + currentWeight + " pounds.");
		} else if (inputMethod == 1) {
		cargohold.add(new Equipable(name, weightPounds, weightOunces, value, durability, grade, material, demand, use));
		
		double addWeight = (double)weightPounds + ((double)weightOunces)/16;
		
		weights.add(addWeight);
		System.out.println("Item added to storage");
		} else if (inputMethod == 2) {
			double addWeight = weightTons * 2000;
			cargohold.add(new Equipable(name, addWeight, value, durability, grade, material, demand, use));
			
			weights.add(weightTons*2000);
		}
		
	}
	
	
	
	private void addConsumable(ArrayList<Item> cargohold, ArrayList<Double> weights) {
		//TODO create method
		double currentWeight = sumWeight(weights);
		int weightPounds = 0;
		int weightOunces = 0;
		double weightTons = 0;
		double newWeight = 0;
		System.out.println("Please enter the name of the item you are adding: ");
		String name = input.nextLine();
		System.out.println("Please choose how you will enter the item's weight:\n1. In pounds and ounces\n2. In tons");
		int inputMethod = enterInt(1,2);
		if(inputMethod == 1) {
			System.out.println("Please enter the number of pounds");
			weightPounds = enterInt(0,-1);
			System.out.print("Please enter the number of ounces");
			weightOunces = enterInt(0,15);
			newWeight = currentWeight + weightPounds + weightOunces/16;
		} else if (inputMethod == 2) {
			System.out.println("Please enter the weight of the item in tons (i.e. 1.45):");
			weightTons = enterDouble();
			newWeight = currentWeight + weightTons*2000;
		}
		System.out.println("Please enter the value in credits of the item you are adding (i.e. 32.45)");
		double value = enterDouble();
		System.out.println("Please indicate the durability of the item you are adding:\n1. Very Low\n2. Low\n3. Moderate\n4. High\n5. Exceptional");
		int durability = enterInt(1,5);
		input.nextLine();
		
		
		System.out.println("Please enter the type of item being entered:\n1. Dry Good\n2. Fresh\n3. Frozen\n4. Liquid");
		int userInput = enterInt(1,4);
		String state = "";
		switch (userInput) {
			case 1:
				state = "Dry Good";
				break;
			case 2:
				state = "Fresh";
				break;
			case 3:
				state = "Frozen";
				break;
			case 4:
				state = "Liquid";
				break;
		}
		System.out.println("Please enter the category for the item:\n1. Basic Need\n2. Luxury Item");
		userInput = enterInt(1,2);
		String status = "";
		switch(userInput) {
		case 1:
			status = "Basic Need";
			break;
		case 2:
			status = "Luxury Item";
			break;
		
		}
		
	
		
		
		System.out.println("Please enter the state for the item:\n1. Ready to Eat Meal\n2. Ingredient\n3. Vegetable/Fruit");
		System.out.println("4. Protein\n5. Grains/Legumes\n6. Beverage\n7. Spice");
		String cType = "";
		userInput = enterInt(1,7);
		switch(userInput) {
		case 1:
			cType = "Ready to Eat Meal";
			break;
		case 2: 
			cType = "Ingredient";
			break;
		case 3: 
			cType = "Vegetable/Fruit";
			break;
		case 4:
			cType = "Protein";
			break;
		case 5:
			cType = "Grain/Legumes";
			break;
		case 6:
			cType = "Beverage";
			break;
		case 7:
			cType = "Spice";
			break;
		}
		
		
		
		if (newWeight > 50000) {
			System.out.println("Unable to add "+ name + " to storage. Total weight would exceed maximum of 25 tons. Current total weight is " + currentWeight + " pounds.");
		} else if (inputMethod == 1) {
		cargohold.add(new Consumable(name, weightPounds, weightOunces, value, durability, cType, status, state));
		
		double addWeight = (double)weightPounds + ((double)weightOunces)/16;
		
		weights.add(addWeight);
		System.out.println("Item added to storage");
		} else if (inputMethod == 2) {
			double addWeight = weightTons * 2000;
			cargohold.add(new Consumable(name, addWeight, value, durability, cType, status, state));
			weights.add(weightTons*2000);
		}
	}
	
	private void addWeapon(ArrayList<Item> cargohold, ArrayList<Double> weights) {
		//TODO create method
		
		double currentWeight = sumWeight(weights);
		int weightPounds = 0;
		int weightOunces = 0;
		double weightTons = 0;
		double newWeight = 0;
		System.out.println("Please enter the name of the item you are adding: ");
		String name = input.nextLine();
		System.out.println("Please choose how you will enter the item's weight:\n1. In pounds and ounces\n2. In tons");
		int inputMethod = enterInt(1,2);
		if(inputMethod == 1) {
			System.out.println("Please enter the number of pounds");
			weightPounds = enterInt(0,-1);
			System.out.print("Please enter the number of ounces");
			weightOunces = enterInt(0,15);
			newWeight = currentWeight + weightPounds + weightOunces/16;
		} else if (inputMethod == 2) {
			System.out.println("Please enter the weight of the item in tons (i.e. 1.45):");
			weightTons = enterDouble();
			newWeight = currentWeight + weightTons*2000;
		}
		System.out.println("Please enter the value in credits of the item you are adding (i.e. 32.45)");
		double value = enterDouble();
		System.out.println("Please indicate the durability of the item you are adding:\n1. Very Low\n2. Low\n3. Moderate\n4. High\n5. Exceptional");
		int durability = enterInt(1,5);
		input.nextLine();
		
		
		System.out.println("Please enter the class of weapon being entered:\n1. Projectile\n2. Explosive\n3. Melee");
		int userInput = enterInt(1,3);
		String wClass = "";
		switch (userInput) {
			case 1:
				wClass = "Projectile";
				break;
			case 2:
				wClass = "Explosive";
				break;
			case 3:
				wClass = "Melee";
				break;
			
		}
		System.out.println("Please indicate if the weapon is self contained:\n1. Yes. No other parts or accessories (i.e. bullets) are needed"
				+ "\n2. No. Accessories are required and are also present"
				+ "\n3. No. Accessories are required but are not present");
		userInput = enterInt(1,3);
		String selfContained = "";
		switch(userInput) {
		case 1:
			selfContained = "Yes";
			break;
		case 2:
			selfContained = "No. Accessories are present";
			break;
		case 3: 
			selfContained = "No. Accessories are not present";
			break;
		
		}
		
	
		
		
		System.out.println("Please enter the technological level for the weapon:\n1. Advanced\n2. Moderate\n3. Primitive");
		String techLevel = "";
		userInput = enterInt(1,3);
		switch(userInput) {
		case 1:
			techLevel = "Advanced";
			break;
		case 2: 
			techLevel = "Moderate";
			break;
		case 3:
			techLevel = "Primitive";
			break;
		}
		
		
		
		if (newWeight > 50000) {
			
			System.out.println("Unable to add "+ name + " to storage. Total weight would exceed maximum of 25 tons. Current total weight is " + currentWeight + " pounds.");
		} else if (inputMethod == 1) {
		cargohold.add(new Weapon(name, weightPounds, weightOunces, value, durability, wClass, selfContained, techLevel));
		
		double addWeight = (double)weightPounds + ((double)weightOunces)/16;
		
		weights.add(addWeight);
		System.out.println("Item added to storage");
		} else if (inputMethod == 2) {
			double addWeight = weightTons * 2000;
			cargohold.add(new Weapon(name, addWeight, value, durability, wClass, selfContained, techLevel));
			weights.add(weightTons*2000);
		}
	}
	
	

	private void removeItem(ArrayList<Item> cargohold, ArrayList<Double> weights) {
		// TODO: Remove an item that is specified by the user
		
		System.out.println("Please select removal method:");
		System.out.println("1. Remove by name");
		System.out.println("2. Remove by weight");
		System.out.println("3. Remove by value");
		System.out.println("4. Remove by durability");
		System.out.println();
		
		int removeChoice = enterInt(1,4);
		input.nextLine();
		

		int toRemove = -1;
		
		if(removeChoice == 1) { //remove by name
			System.out.println("Please enter the name of the item you would like to remove:");
			String removeItem = input.nextLine();
			
			toRemove = findIndexByName(cargohold,removeItem);
			
		
			
		
		
			

		} else if(removeChoice == 2) {//remove by weight
			
			System.out.println("Please enter the weight range in pounds to find the correct item for removal");
			System.out.println("Minimum weight:");
			double minNumber = enterDouble();
			System.out.println("Maximum weight:");
			double maxNumber = enterDouble();
			
			toRemove = findIndexByWeight(cargohold, minNumber, maxNumber);
		
			
		} else if (removeChoice == 3) {//remove by value
			System.out.println("Please enter the value range to find the correct item for removal");
			System.out.println("Minimum value:");
			double minNumber = enterDouble();
			System.out.println("Maximum value:");
			double maxNumber = enterDouble();
			
			toRemove = findIndexByValue(cargohold, minNumber, maxNumber);
	
		} else if (removeChoice == 4) {// remove by durability level
			System.out.println("Please enter the durability level of the item you would like to remove:\n1. Very Low\n2. Low\n3. Moderate\n4. High\n5. Exceptional");
			int durabilityChoice = enterInt(1,5);
			toRemove = findIndexByDurability(cargohold, durabilityChoice);
			
		
		} else {
			System.out.println("Invalid option. Please try again");
		}
		
		
	
		
		if (toRemove != -1) {
			
			double lessWeight = cargohold.get(toRemove).getWeight();
			Item.reduceTotalWeight(lessWeight);
			cargohold.remove(toRemove);
			System.out.println("Item removed from storage");
			for (Double w: weights) {
				if(w == lessWeight) {
					
					weights.remove(w);
					break;
				}
			}
			
		}
		
		
	
		input.nextLine();
		promptEnterKey();
	}

	private void sortItems(ArrayList<Item> cargohold) {
		// TODO: Sort the items in the cargo hold (No need to display them here) - Use Selection or Insertion sorts
		// NOTE: Special care is needed when dealing with strings! research the compareTo() method with strings
		
		System.out.println("Beginning sort process...");
		
	
		
		
		
		Item temp;
		for (int i = 0; i < cargohold.size() - 1;i++ ) {
			for (int j = i + 1; j < cargohold.size(); j++) {
				
					if(cargohold.get(i).getName().compareToIgnoreCase(cargohold.get(j).getName()) > 0) {
						
						
					
						
						temp = cargohold.get(j);
						cargohold.set(j, cargohold.get(i));
						cargohold.set(i, temp);
						
					
			}
		}
		}
		
		
		
		System.out.println("Sort process complete");
		
		promptEnterKey();
	}

	private void searchItems(ArrayList<Item> cargohold) {
		// TODO: Search for a user specified item
		
		//Determine if we will do a name search or attribute search
		System.out.println("Please select the type of search:\n1. Search by Name\n2. Search by Durability");
		int searchType = enterInt(1,2);
		input.nextLine();
		
		if (searchType == 1) {
		
		
		System.out.println("Please enter the name of the item you would like to search for: ");
		String name = input.nextLine();
		nameSearch(cargohold,name);
		} else if(searchType == 2) {
			System.out.println("Please enter the durability level of the item you would like to search for:\n1. Very Low\n2. Low\n3. Moderate\n4. High\n5. Exceptional");
		int durability = enterInt(1,5);
		durabilitySearch(cargohold,durability);
		} else {
			System.out.println("Invalid Entry");
		}
		promptEnterKey();
		
		
		
	}

	private void displayItems(ArrayList<Item> cargohold) {
		// TODO: Display only the unique items along with a count of any duplicates
		//
		// For example it should say
		// Food - 2
		// Water - 3
		// Ammunition - 5
		
		System.out.println();
		
	
		
		List<String> weapons = new ArrayList<>();
		List<Integer> weaponsCount = new ArrayList<>();
		List<String> consumable = new ArrayList<>();
		List<Integer> consumableCount = new ArrayList<>();
		List<String> equipable = new ArrayList<>();
		List<Integer> equipableCount = new ArrayList<>();
		
		for (Item name: cargohold) {
			if(name.getClass().equals(Weapon.class)) {
				
			
			int index = weapons.indexOf(name.getName().toLowerCase());
			if (index == -1) {
				weapons.add(name.getName().toLowerCase());
				weaponsCount.add(1);
			} else {
				int oldCount = weaponsCount.get(index);
				weaponsCount.set(index, oldCount + 1);
			}
		} else if(name.getClass().equals(Consumable.class)) {
			int index = consumable.indexOf(name.getName().toLowerCase());
			if (index == -1) {
				consumable.add(name.getName().toLowerCase());
				consumableCount.add(1);
			} else {
				int oldCount = consumableCount.get(index);
				consumableCount.set(index, oldCount + 1);
			}
			
		} else if(name.getClass().equals(Equipable.class)) {
			int index = equipable.indexOf(name.getName().toLowerCase());
			if (index == -1) {
				equipable.add(name.getName().toLowerCase());
				equipableCount.add(1);
			} else {
				int oldCount = equipableCount.get(index);
				equipableCount.set(index, oldCount + 1);
			}
		}
		}
		
		System.out.println("The following weapons are currently in storage:");
		for (int i=0;i < weapons.size(); i++) {
			System.out.println(weapons.get(i) + " - " + weaponsCount.get(i));
			
		}
		System.out.println();
		
		System.out.println("The following consumable items are currently in storage:");
		for (int i=0;i < consumable.size(); i++) {
			System.out.println(consumable.get(i) + " - " + consumableCount.get(i));
			
		}
		System.out.println();
		System.out.println("The following equipable items are currently in storage:");
		for (int i=0;i < equipable.size(); i++) {
		
			System.out.println(equipable.get(i) + " - " + equipableCount.get(i));
			
		}
		
		
		
		
		promptEnterKey();
		
		//Give use option of showing details for items in storage. Either details for one type of item (by name) or for all items
		System.out.println("Please choose an option:\n1. Obtain details for specific item type in storage\n2. Display details for all items, grouped by categroy\n3. List details for all items by Storage Area\n4. Return to main menu");

		int userInput = enterInt(1,4);
		switch(userInput){
		case 1: 
			System.out.println("Please enter the name of the item you would like to see details for: ");
			input.nextLine();
			String name = input.nextLine();
			nameSearch(cargohold,name);
			break;
	
		case 2:
			
			System.out.println("Details for weapons in storage:");
			
			
			
			for(Item c: cargohold) {
				if(c.getClass().equals(Weapon.class)) {
					int index = cargohold.indexOf(c);
					System.out.println("Storage Area: " + index + ":");
					System.out.println(c.listInfo()) ;
					
				}
			}
			System.out.println();
			System.out.println("Details for consumables in storage:");
			for(Item c: cargohold) {
				if(c.getClass().equals(Consumable.class)) {
					int index = cargohold.indexOf(c);
					System.out.println("Storage Area: " + index + ":");
					System.out.println(c.listInfo());
				}
			}
			System.out.println();
			System.out.println("Details for equipable items in storage:");
			for(Item c: cargohold) {
				if(c.getClass().equals(Equipable.class)) {
					int index = cargohold.indexOf(c);
					System.out.println("Storage Area: " + index + ":");
					System.out.println(c.listInfo());
				}
			}
			
			
		
			input.nextLine();
			break;
		case 3:
			for(Item c: cargohold) {
				int index = cargohold.indexOf(c);
				System.out.println("Storage Area: " + index + ":");
				System.out.println(c.listInfo());
				System.out.println();
			}
		case 4:
			input.nextLine();
			break;
			
		}
		
		
		promptEnterKey();
		
		
	}
	
	
	
		// new method to create pause after each transaction. 
		private void promptEnterKey() {
			System.out.println();
			System.out.println("Press \"ENTER\" to continue...");
			input.hasNextLine();
		}

		// new method to find index of item to be removed by weight
		private int findIndexByWeight(ArrayList<Item> cargohold, double min, double max) {
			double maxNumber = max;
			double minNumber = min;
			int index = 0;
			int indexCount = 0;
			int removal = -1;
			ArrayList<Integer> indexArray = new ArrayList<Integer>();
			for(Item name: cargohold) {
				if(maxNumber > name.getWeight() && name.getWeight() > minNumber) {
					
					
					indexArray.add(index);
					indexCount++;
					
				}
				index++;
				
			}
			if(indexCount == 0) {
				System.out.println("No items within selected weight range found in storage");
			} else {
			
			System.out.println("The following items in storage are within your desired weight range.");
			System.out.println("Please select the item you would like to remove: ");
			for(Integer number: indexArray) {
				// System.out.println("Item " + (indexArray.indexOf(number)) + "\n\tID: " + cargohold.get(number).getId() + " \n\tName: " + cargohold.get(number).getName() + " \n\tWeight: " + cargohold.get(number).getWeight() + " \n\tValue: " + cargohold.get(number).getValue());
				
				System.out.println("Item " + (indexArray.indexOf(number)) + "\n" + cargohold.get(number));
				 
				   System.out.println();
			
			}
			int selection = enterInt(0,-1);
			removal = indexArray.get(selection);
		
		
			}
			
	
			
			return removal;
			
		}
		//method to find index of item to be removed by name
		private int findIndexByName(ArrayList<Item> cargohold, String name) {
			StringBuilder locations = new StringBuilder();
			ArrayList<Integer> indexLocations = new ArrayList<Integer>();
			int toRemove = -1;
			int counter = 0;
			   for(Item t : cargohold) {
			      if(t.getName().equalsIgnoreCase(name)) { 
			    	  counter++;
			    	  if (counter > 1) {
			    	  locations.append(", ");
			    	 
			    	  } 
			    	  locations.append(cargohold.indexOf(t));
			    	  indexLocations.add(cargohold.indexOf(t));
			    	  }
			   }
			   if(counter > 0) {
				   System.out.println("Ignoring case, we found '" + name + "' in storage area(s): " + locations.toString());
				   System.out.println();
				   for(Integer item : indexLocations) {
					   System.out.println("Storage Area " + item + "\n" + cargohold.get(item));
					 
					   System.out.println();
					
				   }
				   System.out.println("Please select the storage area you would like to remove the item from:");
				   
				   toRemove = enterInt(0,-1);
			   } else {
			   System.out.println("Unable to locate item '" + name + "'");
			   }
			   return toRemove;
			}
		
		//find index for item to be removed by value
		private int findIndexByValue(ArrayList<Item> cargohold, double min, double max) {
			double maxNumber = max;
			double minNumber = min;
			int index = 0;
			int indexCount = 0;
			int removal = -1;
			ArrayList<Integer> indexArray = new ArrayList<Integer>();
			for(Item name: cargohold) {
				if(maxNumber > name.getValue() && name.getValue() > minNumber) {
					
					
					indexArray.add(index);
					indexCount++;
				}
				index++;
			
			}
			if(indexCount == 0) {
				System.out.println("No items within the value range entered are currently in storage");
			} else {
			
			
			System.out.println("The following items in storage are within your desired value range.");
			System.out.println("Please select the item you would like to remove: ");
			for(Integer number: indexArray) {
				// System.out.println("Item " + (indexArray.indexOf(number)) + "\n\tID: " + cargohold.get(number).getId() + " \n\tName: " + cargohold.get(number).getName() + " \n\tWeight: " + cargohold.get(number).getWeight() + " \n\tValue: " + cargohold.get(number).getValue());
				System.out.println("Item " + (indexArray.indexOf(number)) + "\n" + cargohold.get(number));
			
			}
			int selection = enterInt(0,-1);
			removal = indexArray.get(selection);
		
			}
	
			
		
			
			return removal;
			
		}
		
		//find index for item to be removed by durability
		private int findIndexByDurability(ArrayList<Item> cargohold, int durability) {
			int durabilityChoice = durability;
		
			int index = 0;
			int indexCount = 0;
			int removal = -1;
			ArrayList<Integer> indexArray = new ArrayList<Integer>();
			for(Item name: cargohold) {
				if(durabilityChoice == name.getDurability()){
				
					
					indexArray.add(index);
					indexCount++;
				}
				index++;
			
			}
			if(indexCount == 0) {
				System.out.println("No items with selected durability are currently in storage");
				
			} else {
		
			System.out.println("The following items in storage match your durability choice.");
			System.out.println("Please select the item you would like to remove: ");
			for(Integer number: indexArray) {
				// System.out.println("Item " + (indexArray.indexOf(number)) + "\n\tID: " + cargohold.get(number).getId() + " \n\tName: " + cargohold.get(number).getName() + " \n\tWeight: " + cargohold.get(number).getWeight() + " \n\tValue: " + cargohold.get(number).getValue());
			
				System.out.println("Item " + (indexArray.indexOf(number)) + "\n" + cargohold.get(number));
			}
			int selection = enterInt(0,-1);
			removal = indexArray.get(selection);
			
		
	
			
	
			}
			return removal;
			
			
		}
		
		// search ArrayList for name matches 
		private void nameSearch(ArrayList<Item> cargohold, String name) {
			StringBuilder locations = new StringBuilder();
			ArrayList<Integer> indexLocations = new ArrayList<Integer>();
			
			int counter = 0;
			   for(Item t : cargohold) {
			      if(t.getName().equalsIgnoreCase(name)) { 
			    	  counter++;
			    	  if (counter > 1) {
			    	  locations.append(", ");
			    	 
			    	  } 
			    	  locations.append(cargohold.indexOf(t));
			    	  indexLocations.add(cargohold.indexOf(t));
			    	  }
			   }
			   if(counter > 0) {
				   System.out.println("Ignoring case, we found '" + name + "' in storage area(s): " + locations.toString());
				   System.out.println();
				   for(Integer item : indexLocations) {
					   System.out.println("Storage Area " + item + ":\n" + cargohold.get(item));
					  
					   
					
					   
				   }
			   } else {
			   System.out.println("Unable to locate item '" + name + "'");
			   
			   }
		
			}
		
		//search ArrayList for durability level matches
		private void durabilitySearch(ArrayList<Item> cargohold, int durability) {
			StringBuilder locations = new StringBuilder();
			ArrayList<Integer> indexLocations = new ArrayList<Integer>();
			int counter = 0;
			   for(Item t : cargohold) {
			      if(t.getDurability() == durability) { 
			    	  counter++;
			    	  if (counter > 1) {
			    	  locations.append(", ");
			    	 
			    	  } 
			    	  locations.append(cargohold.indexOf(t));
			    	  indexLocations.add(cargohold.indexOf(t));
			    	  }
			   }
			   if(counter > 0) {
				   System.out.println("We found items of durabiltiy '" + durability + "' in storage area(s): " + locations.toString());
				   System.out.println();
				   for(Integer item : indexLocations) {
					   
					  
					   System.out.println("Storage Area " + item + ":\n" + cargohold.get(item));
					  
					 
				   }
			   } else {
			   System.out.println("Unable to locate items with durability of '" + durability + "'");
			   }
			   input.nextLine();
			}
		// obtain the sum of the weights in the array storing weights
		private double sumWeight (ArrayList<Double> weights) {
			double sum = 0;
			for (Double w: weights) {
				sum += w;
			}
			return sum;
		}

		private void decodeMessage() throws IOException {
			System.out.println("Please select the level of decryption needed:\n1. Basic\n2. Advanced\n3. Military Grade");
			int choice = enterInt(1,3);
			input.nextLine();
			switch(choice) {
			case 1:
				basicDecode();
				break;
			case 2:
				advancedDecode();
				break;
			case 3:
				militaryGrade();
				break;
			}
		}
		
		private void basicDecode() {
			StringBuilder message = new StringBuilder();
			System.out.println("Please enter the message to be decrypted");
		
			message.append(input.nextLine());
			StringBuilder decrypted = new StringBuilder();
			for(int i = 1; i < 26; i++) {
				StringBuilder newMessage = new StringBuilder(message.toString());
				
		
				StringBuffer result = new StringBuffer();
					for(int j = 0;j < newMessage.length();j++) {
			
						if (Character.isUpperCase(newMessage.charAt(j))) {
					
							char ch = (char)(((int)newMessage.charAt(j) + i - 65) % 26 + 65);
							
							result.append(ch);
						} else if (Character.isLowerCase(newMessage.charAt(j))){
				
							char ch = (char)(((int)newMessage.charAt(j) + i - 97) % 26 + 97);
					
				
							result.append(ch);
						} else {
							char ch = newMessage.charAt(j);
							result.append(ch);
						}
				
			}
					decrypted.append(result);
					decrypted.append("\n");
					
			
			
		}
			System.out.println("One of the following phrases is the decoded message: \n" + decrypted);
		
		}
		
	
		
		
		private void advancedDecode() {
			StringBuilder message = new StringBuilder();
			System.out.println("Please enter the message to be decrypted");
		
			message.append(input.nextLine());
			StringBuilder decrypted = new StringBuilder();
			for(int i = 1; i < 26; i++) {
			
				for(int j = 1; j < 26; j++ ) {
			
				StringBuilder newMessage = new StringBuilder(message.toString());
				
		
				StringBuffer result = new StringBuffer();
					for(int k = 0;k < newMessage.length();k++) {
						if(k % 2 == 0) {
							
						
						if (Character.isUpperCase(newMessage.charAt(k))) {
						
								char ch = (char)(((int)newMessage.charAt(k) + i - 65) % 26 + 65);
							
								result.append(ch);
							} else if (Character.isLowerCase(newMessage.charAt(k))){
				
								char ch = (char)(((int)newMessage.charAt(k) + i - 97) % 26 + 97);
					
				
								result.append(ch);
							} else {
								char ch = newMessage.charAt(k);
								result.append(ch);
							}
				
			} else {
				
				if (Character.isUpperCase(newMessage.charAt(k))) {
					
					char ch = (char)(((int)newMessage.charAt(k) + j - 65) % 26 + 65);
				
					result.append(ch);
				} else if (Character.isLowerCase(newMessage.charAt(k))){
	
					char ch = (char)(((int)newMessage.charAt(k) + j - 97) % 26 + 97);
		
	
					result.append(ch);
				} else {
					char ch = newMessage.charAt(k);
					result.append(ch);
				}
				
				
			}
					
					
			
				}	
				decrypted.append(result);
				decrypted.append("\n");
		}
				
			
			
			
			
		}System.out.println("One of the following phrases is the decoded message: \n" + decrypted);	
		} 
		
		
		private void militaryGrade() throws IOException {
			// obtain pharse for decryption from user
			ArrayList<PhraseAnalysis> phrases = new ArrayList<PhraseAnalysis>();
			
			System.out.println("Please enter the phrase to be decrypted");
			StringBuilder phrase = new StringBuilder(input.nextLine());
			
		
			
			//create series of possible decryptions for phrase
	
			
			
			for(int i = 1; i < 26; i++) {
				
				for(int j = 1; j < 26; j++) {
					StringBuilder newPhrase = new StringBuilder(phrase.toString());
					StringBuilder result = new StringBuilder();
					int add = (i+j) % 26;
					
					//update first character by i spots
					if (Character.isUpperCase(newPhrase.charAt(0))) {
						
						char ch = (char)(((int)newPhrase.charAt(0) + i - 65) % 26 + 65);
					
						result.append(ch);
					} else if (Character.isLowerCase(newPhrase.charAt(0))){

						char ch = (char)(((int)newPhrase.charAt(0) + i - 97) % 26 + 97);
					

						result.append(ch);
					}
				
						
					//now loop through remainder of phrase and update each subsequent character by 	(i + jk)
					for(int k = 1; k < newPhrase.length();k++) {
						
						 
							 if (Character.isLetter(newPhrase.charAt(k)) && Character.isUpperCase(newPhrase.charAt(k))) {
							
							char ch2 = (char)(((int)newPhrase.charAt(k) + add - 65) % 26 + 65);
						
							result.append(ch2);
							add = (add + j)%26;
						} else if (Character.isLetter(newPhrase.charAt(k)) && Character.isLowerCase(newPhrase.charAt(k))){

							char ch2 = (char)(((int)newPhrase.charAt(k) + add - 97) % 26 + 97);
				

							result.append(ch2);
							add = (add + j)%26;
						
						
					}
						 else {
							result.append(newPhrase.charAt(k));
							add = (add + j)%26;
						}
					
				
						
			}
					
			
		
			
			

			
					
					phrases.add(new PhraseAnalysis(result.toString()));
			
		}
			
				}	
			
		
			double min = phrases.get(0).getScore();
		
			int index = 0;
			int minIndex = 0;
			
			for (PhraseAnalysis p: phrases) {
				
				if (p.getScore() < min) {
					min = p.getScore();
					minIndex = index;
					index++;
				} else {
			
					index++;
				}
			}
		
			System.out.println();
			System.out.println("The decrypted message is: \n");
			System.out.println(phrases.get(minIndex).getPhrase());
			System.out.println();
			System.out.println();
			
			
			
			
			
			
		}
// Method to verify integer is entered and is between desired values	
		private int enterInt(int lower, int upper) {
			boolean continueInput = true;
			int lowerInt = lower;
			int upperInt = upper;
			int userInput = 0;
			do {
			try {
				
			
				userInput = input.nextInt();
				if(upperInt != -1) {
				if(lowerInt <= userInput && userInput <= upperInt) {
				continueInput = false;
				} else {
					System.out.println("Entry must be between " + lower + " and " + upper + "\nPlease try again.");
				}
				} else {
					if(lowerInt <= userInput) {
						continueInput = false;
					} else {
						System.out.println("Entry must be a positive integer.\nPlease try again.");
					}
				}
			} catch (InputMismatchException e){
				if(upperInt != -1) {
				System.out.println("Incorrect input: Integer between " + lowerInt + " and " + upperInt + " required. \nPlease try again");
				input.nextLine();
				} else {
					System.out.println("Incorrect input: Positive integer required. \nPlease try again");
					input.nextLine();
				}
				
			}
			
		} while (continueInput);
		return userInput;	
		}
		
		// Method to verify double is entered
				private double enterDouble() {
					boolean continueInput = true;
					
					double userInput = 0.0;
					do {
					try {
						
					
						userInput = input.nextDouble();
						if(userInput > 0) {
					
						continueInput = false;
						} else {
							System.out.println("Entry must be larger than 0" + "\nPlease try again.");
						}
						
					} catch (InputMismatchException e){
						
						System.out.println("Incorrect input: Number must be entered. \nPlease try again");
						input.nextLine();
						
						
					}
					
				} while (continueInput);
				return userInput;	
				}
		
	// Method to write text file of inventory contents	
				private void writeBackup(ArrayList<Item> cargohold) throws IOException {
					System.out.println("Please enter the name of the file to create");
					String fileName = input.nextLine();
					
					String lastFour = "";     //substring containing last 4 characters
					 
					if (fileName.length() > 4) 
					{
					    lastFour = fileName.substring(fileName.length() - 4);
					} 
					else
					{
					    lastFour = fileName;
					}
					
					if (!lastFour.equalsIgnoreCase(".txt")) {
						fileName = fileName + ".txt";
					}
					
					PrintStream pStream = null; 
					try {
					File file = new java.io.File(fileName);
					FileOutputStream fos = new FileOutputStream(file);
					pStream = new PrintStream(fos);
					
					
					
					
				
					//write file

			
					for(Item c: cargohold) {

					
							pStream.println(c.backupText());
					} 
					}catch (FileNotFoundException e){
						e.printStackTrace();
						
					} finally {
						
						pStream.close();
					}
					System.out.println("Text file '" + fileName + "' has been created");
					System.out.println();
					promptEnterKey();
					
					} 
				
private void loadFile(ArrayList<Item> cargohold, ArrayList<Double> weights){
	
		if(cargohold.size() > 0) {
			System.out.println("BlackStar Inventory System not empty. Please select an option:");
			System.out.println("1. Replace onboard inventory list with imported list");
			System.out.println("2. Add imported list to onboard inventory list. (Unique IDs will be reset for all imported items!!)");
			System.out.println("3. Exit import process");
			
			int userChoice = enterInt(1,3);
			input.nextLine();
			
			switch (userChoice){
			case 1: 
			//	cargohold.clear();
			//	weights.clear();
			//	Item.resetCounter();
			//	Item.resetTotalWeight();
				loadFile2(cargohold,weights);
				break;
			case 2:
				loadFile3(cargohold,weights);
				break;
			case 3:
				break;
				
				
			} 
			
		} else {
			loadFile2(cargohold,weights);
		}
}
// load external file to empty arraylist		
private void loadFile2(ArrayList<Item> cargohold, ArrayList<Double> weights) {
		System.out.println("Enter the name of the file that you want to load:");
		String filename = input.nextLine();
		try {
		Scanner fileInput = new Scanner(new File(filename));
		
		cargohold.clear();
		weights.clear();
		Item.resetCounter();
		Item.resetTotalWeight();
		
		
		while(fileInput.hasNextLine()) {
			String fieldString = fileInput.nextLine();
		
			
			String[] fields = fieldString.split(","); 
			  
			if(fieldString.charAt(0) == 'w') {
				int ID = Integer.parseInt(fields[1]);
				String name = fields[2];
				double weight = Double.parseDouble(fields[3]);
				double value = Double.parseDouble(fields[4]);
				int durability = Integer.parseInt(fields[5]);
				String wClass = fields[6];
				String selfContained = fields[7];
				String techLevel = fields[8];
				double marketDemand = Double.parseDouble(fields[9]);
				
				
				cargohold.add(new Weapon(ID, name, weight, value, durability, wClass, selfContained, techLevel, marketDemand));
				
				
				
				weights.add(weight);
				
			} else if(fieldString.charAt(0) == 'c') {
				int ID = Integer.parseInt(fields[1]);
				String name = fields[2];
				double weight = Double.parseDouble(fields[3]);
				double value = Double.parseDouble(fields[4]);
				int durability = Integer.parseInt(fields[5]);
				String cType = fields[6];
				String status = fields[7];
				String state = fields[8];
				double marketDemand = Double.parseDouble(fields[9]);
				
				cargohold.add(new Consumable(ID, name, weight, value, durability, cType, status, state, marketDemand));
				
				
				
				weights.add(weight);
				
			} else if(fieldString.charAt(0) == 'e') {
				
				int ID = Integer.parseInt(fields[1]);
				String name = fields[2];
				double weight = Double.parseDouble(fields[3]);
				double value = Double.parseDouble(fields[4]);
				int durability = Integer.parseInt(fields[5]);
				
				String grade = fields[6];
				String material = fields[7];
				String demand = fields[8];
				String use = fields[9];
				double marketDemand = Double.parseDouble(fields[10]);
				
				cargohold.add(new Equipable(ID, name, weight, value, durability, grade, material, demand, use, marketDemand));
				
				
				
				weights.add(weight);
				
				
			}
			
			
	      
		}
		System.out.println("'" + filename + "' has been imported");
		System.out.println();
		System.out.println();
		System.out.println();
		promptEnterKey();
	} catch (FileNotFoundException e) {
		System.out.println("Could not find the file, and the program is exiting");
		System.out.println();
		promptEnterKey();
	
	
	
	
	}
}

// append import file to populated arraylist. ID numbers in import file are ignored and assigned upon import
private void loadFile3(ArrayList<Item> cargohold, ArrayList<Double> weights) {
	System.out.println("Enter the name of the file that you want to load:");
	String filename = input.nextLine();
	try {
	Scanner fileInput = new Scanner(new File(filename));
	while(fileInput.hasNextLine()) {
		String fieldString = fileInput.nextLine();
	
		
		String[] fields = fieldString.split(","); 
		  
		if(fieldString.charAt(0) == 'w') {
		
			String name = fields[2];
			double weight = Double.parseDouble(fields[3]);
			double value = Double.parseDouble(fields[4]);
			int durability = Integer.parseInt(fields[5]);
			String wClass = fields[6];
			String selfContained = fields[7];
			String techLevel = fields[8];
			double marketDemand = Double.parseDouble(fields[9]);
			
			
			cargohold.add(new Weapon(name, weight, value, durability, wClass, selfContained, techLevel, marketDemand));
			
			
			
			weights.add(weight);
			
		} else if(fieldString.charAt(0) == 'c') {
		
			String name = fields[2];
			double weight = Double.parseDouble(fields[3]);
			double value = Double.parseDouble(fields[4]);
			int durability = Integer.parseInt(fields[5]);
			String cType = fields[6];
			String status = fields[7];
			String state = fields[8];
			double marketDemand = Double.parseDouble(fields[9]);
			
			cargohold.add(new Consumable(name, weight, value, durability, cType, status, state, marketDemand));
			
			
			
			weights.add(weight);
			
		} else if(fieldString.charAt(0) == 'e') {
			
		
			String name = fields[2];
			double weight = Double.parseDouble(fields[3]);
			double value = Double.parseDouble(fields[4]);
			int durability = Integer.parseInt(fields[5]);
			
			String grade = fields[6];
			String material = fields[7];
			String demand = fields[8];
			String use = fields[9];
			double marketDemand = Double.parseDouble(fields[10]);
			
			cargohold.add(new Equipable(name, weight, value, durability, grade, material, demand, use, marketDemand));
			
			
			
			weights.add(weight);
			
			
		}
		
		
	} 
	System.out.println("'" + filename + "' has been imported");
	System.out.println();
	System.out.println();
	System.out.println();
	promptEnterKey();
} catch (FileNotFoundException e) {
	System.out.println("Could not find the file, and the program is exiting");
	System.out.println();
	promptEnterKey();



}
}

private void loadDB(ArrayList<Item> cargohold, ArrayList<Double> weights) {
	String filename = "inventory.txt";
	try {
	Scanner fileInput = new Scanner(new File(filename));
	
	cargohold.clear();
	weights.clear();
	Item.resetCounter();
	Item.resetTotalWeight();
	
	
	while(fileInput.hasNextLine()) {
		String fieldString = fileInput.nextLine();
	
		
		String[] fields = fieldString.split(","); 
		  
		if(fieldString.charAt(0) == 'w') {
			int ID = Integer.parseInt(fields[1]);
			String name = fields[2];
			double weight = Double.parseDouble(fields[3]);
			double value = Double.parseDouble(fields[4]);
			int durability = Integer.parseInt(fields[5]);
			String wClass = fields[6];
			String selfContained = fields[7];
			String techLevel = fields[8];
			double marketDemand = Double.parseDouble(fields[9]);
			
			
			cargohold.add(new Weapon(ID, name, weight, value, durability, wClass, selfContained, techLevel, marketDemand));
			
			
			
			weights.add(weight);
			
		} else if(fieldString.charAt(0) == 'c') {
			int ID = Integer.parseInt(fields[1]);
			String name = fields[2];
			double weight = Double.parseDouble(fields[3]);
			double value = Double.parseDouble(fields[4]);
			int durability = Integer.parseInt(fields[5]);
			String cType = fields[6];
			String status = fields[7];
			String state = fields[8];
			double marketDemand = Double.parseDouble(fields[9]);
			
			cargohold.add(new Consumable(ID, name, weight, value, durability, cType, status, state, marketDemand));
			
			
			
			weights.add(weight);
			
		} else if(fieldString.charAt(0) == 'e') {
			
			int ID = Integer.parseInt(fields[1]);
			String name = fields[2];
			double weight = Double.parseDouble(fields[3]);
			double value = Double.parseDouble(fields[4]);
			int durability = Integer.parseInt(fields[5]);
			
			String grade = fields[6];
			String material = fields[7];
			String demand = fields[8];
			String use = fields[9];
			double marketDemand = Double.parseDouble(fields[10]);
			
			cargohold.add(new Equipable(ID, name, weight, value, durability, grade, material, demand, use, marketDemand));
			
			
			
			weights.add(weight);
			
			
		}
		
		
      
	}
	System.out.println("'" + filename + "' has been imported");
	System.out.println();
	System.out.println();
	System.out.println();
	promptEnterKey();
} catch (FileNotFoundException e) {
	System.out.println("Could not find the file, and the program is exiting");
	System.out.println();
	promptEnterKey();




}
}
		

		
		
}		
		
package assignment04;

import java.text.DecimalFormat;

public class Equipable extends Item {
	private String grade;
	private String material;
	private String demand;
	private String use;
	private double marketDemand;
	

		
		
		 public Equipable(String name, int weightPounds, int weightOunces, double value, int durability, String grade, String material, String demand, String use) {
				this.grade = grade;
				this.material = material;
				this.demand = demand;
				this.use = use;
				setDurability(durability);
				setValue(value);
				double weight = (double)weightPounds + ((double)weightOunces)/16;
				setWeight(weight);
				setName(name);
				setId();
				this.marketDemand = calculateMarketDemand(value, durability, grade, demand);
				
			 }
			 
			 public Equipable(String name, double weight, double value, int durability, String grade, String material, String demand, String use) {
				this.grade = grade;
				this.material = material;
				this.demand = demand;
				this.use = use;
				setDurability(durability);
				setValue(value);
				setWeight(weight);
				setName(name);
				setId();
				this.marketDemand = calculateMarketDemand(value, durability, grade, demand);
				
			 }
			 
			 public Equipable(int ID, String name, double weight, double value, int durability, String grade, String material, String demand, String use, double marketDemand) {
					this.grade = grade;
					this.material = material;
					this.demand = demand;
					this.use = use;
					setDurability(durability);
					setValue(value);
					setWeight(weight);
					setName(name);
					setID2(ID);
					this.marketDemand = marketDemand;
			 }
			 
			 public Equipable(String name, double weight, double value, int durability, String grade, String material, String demand, String use, double marketDemand) {
					this.grade = grade;
					this.material = material;
					this.demand = demand;
					this.use = use;
					setDurability(durability);
					setValue(value);
					setWeight(weight);
					setName(name);
					setId();
					this.marketDemand = marketDemand;
			 }

			public String getGrade() {
				
				return grade;
			}

			public void setGrade(String grade) {
				this.grade = grade;
			}

			public String getMaterial() {
				
				return material;
			}

			public void setMaterial(String material) {
				this.material = material;
			}

			public String getDemand() {
				return demand;
			}

			public void setDemand(String demand) {
				this.demand = demand;
			}

			public String getUse() {
				return use;
			}

			public void setUse(String use) {
				this.use = use;
			}
			
			public double getMarketDemand() {
				return marketDemand;
			}
			
			private double calculateMarketDemand(double value, int durability, String grade, String demand) {
				int gradeScore;
				int demandScore;
				
				if(grade =="Military") {
					gradeScore = 10;
				} else {
					gradeScore = 5;
				}
				
				if(demand == "High") {
					demandScore = 10;
				} else if (demand == "Medium") {
					demandScore = 5;
				} else {
					demandScore = 1;
				}
				
				double marketDemand = value*durability*gradeScore*demandScore/100;
				return marketDemand;
			}


			@Override
			public String listInfo() {
			
				DecimalFormat df = new DecimalFormat("###,###.##");

				return "\tID: " + this.getId() + "\n\tName: " + this.getName() + "\n\tWeight: " + df.format(this.getWeight()) + "\n\tValue: " + df.format(this.getValue()) + "\n\tDurability: " + this.getDurability() 
				+ "\n\tGrade: " + grade + "\n\tMaterial: " + material + "\n\tDemand: " + demand + "\n\tUse: " + use + "\n\tEstimated Market Demand: " + marketDemand;
				
				
				
				
			}
			
			@Override
			public String toString() {
				return "\tItem Category: Equipable\n\tID: " + this.getId() + "\n\tName: " + this.getName() + "\n\tWeight: " + this.getWeight() + "\n\tValue: " + this.getValue() + "\n\tDurability: " + this.getDurability() 
				+ "\n\tGrade: " + grade + "\n\tMaterial: " + material + "\n\tDemand: " + demand + "\n\tUse: " + use + "\n\tEstimated Market Demand: " + marketDemand;
						
			}
			
			@Override
			public String backupText() {
				return "e," + this.getId() + "," + this.getName() + "," + this.getWeight() + "," + this.getValue() + "," + this.getDurability() 
				+ "," + grade + "," + material + "," + demand + "," + use + "," + marketDemand;
			}
	

}

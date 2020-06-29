package assignment04;

import java.text.DecimalFormat;

public class Weapon extends Item{
	private String wClass;
	private String selfContained;
	private String techLevel;
	private double marketDemand;
	
	
	 public Weapon(String name, int weightPounds, int weightOunces, double value, int durability, String wClass, String selfContained, String techLevel) {
			this.wClass = wClass;
			this.selfContained = selfContained;
			this.techLevel = techLevel;
			setDurability(durability);
			setValue(value);
			double weight = (double)weightPounds + ((double)weightOunces)/16;
			setWeight(weight);
			setName(name);
			setId();
			this.marketDemand = calculateMarketDemand(value, durability, wClass, techLevel);
		
			
		 }
		 
		 public Weapon(String name, double weight, double value, int durability, String wClass, String selfContained, String techLevel) {
			this.wClass = wClass;
			this.selfContained = selfContained;
			this.techLevel = techLevel;
			setDurability(durability);
			setValue(value);
			setWeight(weight);
			setName(name);
			setId();
			this.marketDemand = calculateMarketDemand(value, durability, wClass, techLevel);
			
		 }
		 
		 public Weapon(int ID, String name, double weight, double value, int durability, String wClass, String selfContained, String techLevel, double marketDemand) {
			 this.wClass = wClass;
			 this.selfContained = selfContained;
			 this.techLevel = techLevel;
			 setDurability(durability);
			 setValue(value);
			 setWeight(weight);
			 setName(name);
			 setID2(ID);
			 this.marketDemand = marketDemand;
		 }
		 
		 public Weapon(String name, double weight, double value, int durability, String wClass, String selfContained, String techLevel, double marketDemand) {
			 this.wClass = wClass;
			 this.selfContained = selfContained;
			 this.techLevel = techLevel;
			 setDurability(durability);
			 setValue(value);
			 setWeight(weight);
			 setName(name);
			 setId();
			 this.marketDemand = marketDemand;
		 }

		public String getWClass() {
			return wClass;
		}

		public void setwType(String wUse) {
			this.wClass = wUse;
		}

		public String getSelfContained() {
			return selfContained;
		}

		public void setSelfContained(String selfContained) {
			this.selfContained = selfContained;
		}

		public String getTechLevel() {
			return techLevel;
		}

		public void setTechLevel(String techLevel) {
			this.techLevel = techLevel;
		}
		 
		public double getMarketDemand() {
			return marketDemand;
		}
		
		public void setMarketDemand(double marketDemand) {
			this.marketDemand = marketDemand;
		}
		
		
		private double calculateMarketDemand(double value, int durability, String wType, String techLevel) {
			int typeScore = 1;
			int techScore = 1;
			
			if(wType =="Projectile") {
				typeScore = 10;
			} else if (wType == "Explosive"){
				typeScore = 5;
			}
			
			if(techLevel == "Advanced") {
				techScore = 10;
			} else if (techLevel == "Moderate") {
				techScore = 5;
			} 
			
			double marketDemand = value * durability * typeScore * techScore / 100;
			return marketDemand;
		}
	

	

		 @Override
		public String listInfo() {
			 DecimalFormat df = new DecimalFormat("###,###.##");
			
			return "\tID: " + this.getId() + "\n\tName: " + this.getName() + "\n\tWeight: " + df.format(this.getWeight()) + "\n\tValue: " + df.format(this.getValue()) + "\n\tDurability: " + this.getDurability() 
					+ "\n\tClass: " + wClass + "\n\tSelf Contained?: " + selfContained + "\n\tTech Level: " + techLevel + "\n\tEstimated Market Demand: " + marketDemand;
			
		}
		
		@Override
		public String toString() {
			return "\tItem Category: Weapon\n\tID: " + this.getId() + "\n\tName: " + this.getName() + "\n\tWeight: " + this.getWeight() + "\n\tValue: " + this.getValue() + "\n\tDurability: " + this.getDurability() 
			+ "\n\tClass: " + wClass + "\n\tSelf Contained?: " + selfContained + "\n\tTech Level: " + techLevel + "\n\tEstimated Market Demand: " + marketDemand;
					
		}
		
		@Override
		public String backupText() {
			return "w," + this.getId() + "," + this.getName() + "," + this.getWeight() + "," + this.getValue() + "," + this.getDurability() + "," + wClass + "," + selfContained + "," + techLevel + "," + marketDemand;
		}


}

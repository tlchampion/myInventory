package assignment04;

import java.text.DecimalFormat;

public class Consumable extends Item {
 private String cType; 
 private String status; 
 private String state; 
 private double marketDemand;
 
 public Consumable(String name, int weightPounds, int weightOunces, double value, int durability, String cType, String status, String state) {
	this.state = state;
	this.status = status;
	this.cType = cType;
	setDurability(durability);
	setValue(value);
	double weight = (double)weightPounds + ((double)weightOunces)/16;
	setWeight(weight);
	setName(name);
	setId();
	this.marketDemand = calculateMarketDemand(value, durability, cType, status, state);
	
 }
 
 public Consumable(String name, double weight, double value, int durability, String cType, String status, String state) {
	this.state = state;
	this.status = status;
	this.cType = cType;
	setDurability(durability);
	setValue(value);
	setWeight(weight);
	setName(name);
	setId();
	this.marketDemand = calculateMarketDemand(value, durability, cType, status, state);
	
 }
 
public Consumable(int ID, String name, double weight, double value, int durability, String cType, String status, String state, double marketDemand) {
	this.state = state;
	this.status = status;
	this.cType = cType;
	setDurability(durability);
	setValue(value);
	setWeight(weight);
	setName(name);
	setID2(ID);
	this.marketDemand = marketDemand;
}

public Consumable(String name, double weight, double value, int durability, String cType, String status, String state, double marketDemand) {
	this.state = state;
	this.status = status;
	this.cType = cType;
	setDurability(durability);
	setValue(value);
	setWeight(weight);
	setName(name);
	setId();
	this.marketDemand = marketDemand;
}

public String getcType() {
	
	return cType;
	
}

public void setcType(String cType) {
	this.cType = cType;
}

public String getStatus() {
	
	
	
	
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public String getState() {

	
	
	
	return state;
}

public void setState(String state) {
	this.state = state;
}
 
 
public double getMarketDemand() {
	return marketDemand;
}

public void setMarketDemand(double marketDemand) {
	this.marketDemand = marketDemand;
}

private double calculateMarketDemand(double value, int durability, String cType, String status, String state) {
	int typeScore = 1;
	int statusScore = 1;
	int stateScore = 1;
	
	if(cType =="Fresh") {
		typeScore = 10;
	} else if (cType == "Frozen"){
		typeScore = 7;
	} else {
		typeScore = 5;
	}
	
	if(status == "Luxury") {
		statusScore = 10;
	} else {
		statusScore = 1;
	}
	
	if(state == "Ready to Eat") {
		stateScore = 5;
	}
	
	double marketDemand = value * durability * typeScore  * statusScore * stateScore / 100;
	return marketDemand;
}



@Override
public String listInfo() {
	DecimalFormat df = new DecimalFormat("###,###.##");
	
	return "\tID: " + this.getId() + "\n\tName: " + this.getName() + "\n\tWeight: " + df.format(this.getWeight()) + "\n\tValue: " + df.format(this.getValue()) + "\n\tDurability: " + this.getDurability() 
	+ "\n\tType: " + cType + "\n\tCategory: " + status + "\n\tState: " + state + "\n\tEstimated Market Demand: " + marketDemand;
	
}

@Override
public String toString() {
	return "\tItem Category: Consumable\n\tID: " + this.getId() + "\n\tName: " + this.getName() + "\n\tWeight: " + this.getWeight() + "\n\tValue: " + this.getValue() + "\n\tDurability: " + this.getDurability() 
	+ "\n\tType: " + cType + "\n\tCategory: " + status + "\n\tState: " + state + "\n\tEstimated Market Demand: " + marketDemand;
			
}
@Override
public String backupText() {
	return "c," + this.getId() + "," + this.getName() + "," + this.getWeight() + "," + this.getValue() + "," + this.getDurability() 
	+ "," + cType + "," + status + "," + state + "," + marketDemand;
}
 
	
	
}

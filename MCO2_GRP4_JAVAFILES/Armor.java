/**
	This is the Armor class that creates an armor that the player can equip to boost their defense
*/
public class Armor {

	private String name;
	private int defBonus;
	private int spdPen;
	
	// Constructor
	/**
		This constructor is used to create an Armor object
		@param name is the name to be given to the object
		@param defBonus is the defense of the weapon that will be added to the player's own defense
		@param spdPen is the speed penalty to be applied to the player's own speed
	*/
	public Armor(String name, int defBonus, int spdPen) {
		this.name = name;
		this.defBonus = defBonus;
		this.spdPen = spdPen;
	}
	
	// Getter methods
	/**
		This method is used to return the name of the Armor
		@return name is the name of the armor
	*/
	public String getName() {
		return name;
	}
	
	/**
		This method is used to return the defense bonus provided by the armor
		@return defBonus is the defense bonus provided by the armor
	*/
	public int getDefBonus() {
		return defBonus;
	}
	
	/**
		This method is used to return the speed penalty of the armor
		@return sPen is the speed penalty applied by the armor
	*/
	public int getSpdPen() {
		return spdPen;
	}

}
package assignment5;

import java.util.List;

import assignment5.Critter; // changed these to assignment 5 instead of 4
import assignment5.InvalidCritterException; //  same^
import assignment5.Params;// same^^

public abstract class Critter {
	/* NEW FOR PROJECT 5 */
	public enum CritterShape {
		CIRCLE,
		SQUARE,
		TRIANGLE,
		DIAMOND,
		STAR
	}
	
	/* the default color is white, which I hope makes critters invisible by default
	 * If you change the background color of your View component, then update the default
	 * color to be the same as you background 
	 * 
	 * critters must override at least one of the following three methods, it is not 
	 * proper for critters to remain invisible in the view
	 * 
	 * If a critter only overrides the outline color, then it will look like a non-filled 
	 * shape, at least, that's the intent. You can edit these default methods however you 
	 * need to, but please preserve that intent as you implement them. 
	 */
	public javafx.scene.paint.Color viewColor() { 
		return javafx.scene.paint.Color.WHITE; 
	}
	
	public javafx.scene.paint.Color viewOutlineColor() { return viewColor(); }
	public javafx.scene.paint.Color viewFillColor() { return viewColor(); }
	
	public abstract CritterShape viewShape(); 
	
	private static String myPackage;
	private boolean hasMoved = false;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();

	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	
	protected final String look(int direction, boolean steps) {
		
		int xcord= this.x_coord;
		int ycord=this.y_coord;
		this.energy=this.energy - Params.look_energy_cost;
	// one step
		if(!steps){
			switch(direction){
			case 0:	xcord ++;
					if (xcord > Params.world_width){
						xcord = xcord%Params.world_width;
					}
					break;
			case 1:	xcord ++;
					ycord --;
					if (xcord > Params.world_width){
						xcord = xcord%Params.world_width;
					}
					if (ycord <= 0){
						ycord = Params.world_height + ycord;
					}
					break;
			case 2:	ycord--;
				if (ycord <= 0){
					ycord = Params.world_height + ycord;
				}
					break;
			case 3:	xcord --;
					ycord --;
					if (xcord <= 0){
						xcord = Params.world_width + xcord;
					}
					if (ycord <= 0){
						ycord = Params.world_height + ycord;
					}
					break;
			case 4: xcord --;
				if (xcord <= 0){
					xcord = Params.world_width + xcord;
				}
					break;
			case 5: xcord --;
					ycord ++;
					if (xcord <= 0){
						xcord = Params.world_width + xcord;
					}
					if (ycord > Params.world_height){
						ycord = ycord%Params.world_height;
					}
					break;
			case 6: ycord ++;
					if (ycord > Params.world_height){
						ycord = ycord%Params.world_height;
					}
					break;
			case 7: xcord ++;
					ycord ++;
					if (xcord > Params.world_width){
						xcord = this.x_coord%Params.world_width;
					}
					if (ycord > Params.world_height){
						ycord = ycord%Params.world_height;
					}
					break;
		}
			
			
			
		}
		else {
			switch(direction){
			case 0:	xcord +=2;
					if (xcord > Params.world_width){
						xcord = xcord%Params.world_width;
					}
					break;
			case 1:	xcord +=2;
					ycord -=2;
					if (xcord > Params.world_width){
						xcord = xcord%Params.world_width;
					}
					if (ycord <= 0){
						ycord = Params.world_height + ycord;
					}
					break;
			case 2:	ycord-=2;
				if (ycord <= 0){
					ycord = Params.world_height + ycord;
				}
					break;
			case 3:	xcord -=2;
					ycord -=2;
					if (xcord <= 0){
						xcord = Params.world_width + xcord;
					}
					if (ycord <= 0){
						ycord = Params.world_height + ycord;
					}
					break;
			case 4: xcord -=2;
				if (xcord <= 0){
					xcord = Params.world_width + xcord;
				}
					break;
			case 5: xcord -=2;
					ycord +=2;
					if (xcord <= 0){
						xcord = Params.world_width + xcord;
					}
					if (ycord > Params.world_height){
						ycord = ycord%Params.world_height;
					}
					break;
			case 6: ycord +=2;
					if (ycord > Params.world_height){
						ycord = ycord%Params.world_height;
					}
					break;
			case 7: xcord +=2;
					ycord +=2;
					if (xcord > Params.world_width){
						xcord = this.x_coord%Params.world_width;
					}
					if (ycord > Params.world_height){
						ycord = ycord%Params.world_height;
					}
					break;
		}
			
		
		
		
		
		}
	
		
		
		for(Critter crit:population){
			if(crit.x_coord==xcord && crit.y_coord==ycord){
				return(crit.toString());
				
			}
		}
		
		return null;
	
	}
	
	/* rest is unchanged from Project 4 */
	
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	
	private int energy = 0;
	protected int getEnergy() { return energy; }
	
	private int x_coord;
	private int y_coord;
	
	protected final void walk(int direction) {
		
		
	this.energy -= Params.walk_energy_cost;
		
		if(!hasMoved){
			switch(direction){
				case 0:	this.x_coord ++;
						if (this.x_coord > Params.world_width){
							this.x_coord = this.x_coord%Params.world_width;
						}
						break;
				case 1:	this.x_coord ++;
						this.y_coord --;
						if (this.x_coord > Params.world_width){
							this.x_coord = this.x_coord%Params.world_width;
						}
						if (this.y_coord <= 0){
							this.y_coord = Params.world_height + this.y_coord;
						}
						break;
				case 2:	this.y_coord--;
					if (this.y_coord <= 0){
						this.y_coord = Params.world_height + this.y_coord;
					}
						break;
				case 3:	this.x_coord --;
						this.y_coord --;
						if (this.x_coord <= 0){
							this.x_coord = Params.world_width + this.x_coord;
						}
						if (this.y_coord <= 0){
							this.y_coord = Params.world_height + this.y_coord;
						}
						break;
				case 4: this.x_coord --;
					if (this.x_coord <= 0){
						this.x_coord = Params.world_width + this.x_coord;
					}
						break;
				case 5: this.x_coord --;
						this.y_coord ++;
						if (this.x_coord <= 0){
							this.x_coord = Params.world_width + this.x_coord;
						}
						if (this.y_coord > Params.world_height){
							this.y_coord = this.y_coord%Params.world_height;
						}
						break;
				case 6: this.y_coord ++;
						if (this.y_coord > Params.world_height){
							this.y_coord = this.y_coord%Params.world_height;
						}
						break;
				case 7: this.x_coord ++;
						this.y_coord ++;
						if (this.x_coord > Params.world_width){
							this.x_coord = this.x_coord%Params.world_width;
						}
						if (this.y_coord > Params.world_height){
							this.y_coord = this.y_coord%Params.world_height;
						}
						break;
			}
			hasMoved = true;
		}
	}
	
	protected final void run(int direction) {
	this.energy -= Params.run_energy_cost;
		
		if(!hasMoved){
			switch(direction){
				case 0:	this.x_coord += 2;
					if (this.x_coord > Params.world_width){
						this.x_coord = this.x_coord%Params.world_width;
					}
				break;
				case 1:	this.x_coord += 2;
						this.y_coord -= 2;
						if (this.x_coord > Params.world_width){
							this.x_coord = this.x_coord%Params.world_width;
						}
						if (this.y_coord <= 0){
							this.y_coord = Params.world_height + this.y_coord;
						}
						break;
				case 2:	this.y_coord -= 2;
						if (this.y_coord <= 0){
							this.y_coord = Params.world_height + this.y_coord;
						}
						break;
				case 3:	this.x_coord -= 2;
						this.y_coord -= 2;
						if (this.x_coord <= 0){
							this.x_coord = Params.world_width + this.x_coord;
						}
						if (this.y_coord <= 0){
							this.y_coord = Params.world_height + this.y_coord;
						}
						break;
				case 4: this.x_coord -= 2;
						if (this.x_coord <= 0){
							this.x_coord = Params.world_width + this.x_coord;
						}
						break;
				case 5: this.x_coord -= 2;
						this.y_coord += 2;
						if (this.x_coord <= 0){
							this.x_coord = Params.world_width + this.x_coord;
						}
						if (this.y_coord > Params.world_height){
							this.y_coord = this.y_coord%Params.world_height;
						}
						break;
				case 6: this.y_coord += 2;
						if (this.y_coord > Params.world_height){
							this.y_coord = this.y_coord%Params.world_height;
						}
						break;
				case 7: this.x_coord += 2;
						this.y_coord += 2;
						if (this.x_coord > Params.world_width){
							this.x_coord = this.x_coord%Params.world_width;
						}
						if (this.y_coord > Params.world_height){
							this.y_coord = this.y_coord%Params.world_height;
						}
						break;
			}
			hasMoved = true;
		}
		
	}
	
	protected final void reproduce(Critter offspring, int direction) {
		if(this.energy<Params.min_reproduce_energy){
			return;
		}
		else{
			offspring.energy=(this.energy)/2;
			if(this.energy%2==1){
				this.energy=((this.energy)/2)+1;
			}
			else{this.energy=((this.energy)/2); 
		}
		offspring.x_coord=this.x_coord;
		offspring.y_coord=this.y_coord;		
		offspring.energy=offspring.energy + Params.walk_energy_cost;
		offspring.walk(direction);
		
			babies.add(offspring);
	}
		
		
	}

	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);
	
	
	public static void worldTimeStep() {
		for(Critter mybbycritter:population){
			mybbycritter.doTimeStep();
		}
		for(int y=0;y<population.size();y++){
			if(population.get(y).getEnergy()==0){
				population.remove(y);
				y--;
			}
		}
		
		for(int i=0; i<population.size();i++){
			Critter C=population.get(i);
			if (C.energy <= 0) {
				population.remove(i);
				i = 0;
				continue;
			}
			for(int j=0;j<population.size();j++){
			
				Critter Ch=population.get(j);
				if (Ch.energy <= 0) {
					population.remove(j);
					j = 0;
					continue;
				}
				if(i==j){}
				else if((C.x_coord==Ch.x_coord) &&(C.y_coord==Ch.y_coord)) {
					boolean firstfight = C.fight(Ch.toString());
					boolean secondfight = Ch.fight(C.toString());
					if((C.x_coord==Ch.x_coord) &&(C.y_coord==Ch.y_coord)){
					if(firstfight && secondfight){	
						int roll1 = Critter.getRandomInt(C.energy);
						int roll2 = Critter.getRandomInt(Ch.energy);
						if(roll1>=roll2){
							C.energy=C.energy + (Ch.energy)/2;
							population.remove(j);
							i=0;
							break;
						}
						else{
							Ch.energy=Ch.energy + (C.energy)/2;
							population.remove(i);
							i=0;
							break;
						}
					}
					else if(firstfight && !secondfight){
						C.energy=C.energy + (Ch.energy)/2;
						population.remove(j);
						i=0;
						break;
					}
					else if(!firstfight && secondfight){
						Ch.energy=Ch.energy + (C.energy)/2;
						population.remove(i);
						i=0;
						break;
					}		
				}
				}
			}	
		}
		for(Critter mybbycritter:population){
			mybbycritter.energy = mybbycritter.energy - Params.rest_energy_cost;
		}
		
		for(int y=0;y<population.size();y++){
			if(population.get(y).getEnergy()==0){
				population.remove(y);
				y--;
			}
		}
		for(int i = 0; i < Params.refresh_algae_count; i++){	//generating the algae
			try{
			Critter.makeCritter("Algae");
			}
			catch(InvalidCritterException e){
				e.toString();
			}
		}
		
		for(Critter baby:babies){
			population.add(baby);
		}
		
		
		babies.clear();
		for(Critter c : population){			
			c.hasMoved = false;
		}
		//displayWorld();
		
		// Complete this method.
	 // end of world time step
		
	}
	
	public static void displayWorld(Object pane) {
	
		for (int k = 0; k < population.size(); k++) {
			Main.makeshapesofcrits(population.get(k), population.get(k).x_coord, population.get(k).y_coord);

		}
		
		
	} 
	/* Alternate displayWorld, where you use Main.<pane> to reach into your
	   display component.
	   // public static void displayWorld() {}
	*/
	
	/* create and initialize a Critter subclass
	 * critter_class_name must be the name of a concrete subclass of Critter, if not
	 * an InvalidCritterException must be thrown
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
		Critter critters;

		try {

			Class<?> mycritter = Class.forName(Critter.myPackage + "." + critter_class_name);

			critters = (Critter) mycritter.newInstance();

		}

		catch (Exception e) {

			throw new InvalidCritterException(critter_class_name);

		}

		critters.x_coord = Critter.getRandomInt(Params.world_width);

		if (critters.x_coord == 0) {

			critters.x_coord = critters.x_coord + Params.world_width;

		}

		if (critters.x_coord == Params.world_width + 1) {

			critters.x_coord = critters.x_coord - Params.world_width;

		}

		critters.y_coord = Critter.getRandomInt(Params.world_height);

		if (critters.y_coord == 0) {

			critters.y_coord = critters.y_coord + Params.world_height;

		}

		if (critters.y_coord == Params.world_height + 1) {

			critters.y_coord = critters.y_coord - Params.world_height;

		}

		critters.energy = Params.start_energy;

		population.add(critters);

	}
	
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();
		for (Critter x : population) {
			try {
				if (x.getClass().equals(Class.forName(Critter.myPackage + "." + critter_class_name))) {
					result.add(x);
				}
			} 
			catch (ClassNotFoundException e) {
				throw new InvalidCritterException(critter_class_name);
			}
		}
		return result;
	}
	
	public static void runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();		
		
		
		
		
		
		
		
		
		
	}
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure thath the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctup update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}
		
		protected int getX_coord() {
			return super.x_coord;
		}
		
		protected int getY_coord() {
			return super.y_coord;
		}
		

		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}
		
		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}
	
	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
		population.clear();
	}
	
	
}

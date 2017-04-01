package assignment5;
/* CRITTERS Critter.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * Vibhu Appalaraju
 * vka249
 * 16235
 * Joseph Bae
 * jb65632
 * 16235
 * Git URL: https://github.com/josephbae96/ee422c-jb65632-.git
 * Slip days used: <0>
 * Spring 2017
 */

import assignment5.Critter.CritterShape;
//import assignment5.Critter.TestCritter;

public class Critter3 extends Critter {
	
	private static final int GENE_TOTAL = 24;
	private int[] genes = new int[8];
	private int dir;
	
	//Random Critter where the critter runs or walks in different ways depending on its energy level and gene total
	public Critter3() {
		for (int k = 0; k < 8; k += 1) {
			genes[k] = GENE_TOTAL / 8;
		}
		dir = Critter.getRandomInt(8);
	}
	
	@Override
	public void doTimeStep() {
		
		if(dir>this.getEnergy()){//picks bewteen two directions based on your energy
			dir=1;
		}
		else{dir = 2;}
			
		
	}

	@Override
	public boolean fight(String opponent) {
		run(dir);
		int dependOnGene = (GENE_TOTAL+5)%8;// this changes the direction based on the gene total plus 5.
		walk(dependOnGene);
		return false;
	}

	@Override
	public String toString () {
		return "3";
	}
	@Override
	public CritterShape viewShape() { return CritterShape.TRIANGLE; }

	@Override
	public javafx.scene.paint.Color viewOutlineColor() { return javafx.scene.paint.Color.RED; }
	@Override
	public javafx.scene.paint.Color viewColor() { return javafx.scene.paint.Color.YELLOW; }

	
	
	public static void runStats(java.util.List<Critter> crit3) {
		int total_straight = 0;
		int total_left = 0;
		int total_right = 0;
		int total_back = 0;
		for (Object obj : crit3) {
			Critter3 c = (Critter3) obj;
			total_straight += c.genes[0];
			total_right += c.genes[1] + c.genes[2] + c.genes[3];
			total_back += c.genes[4];
			total_left += c.genes[5] + c.genes[6] + c.genes[7];
		}
		System.out.print("" + crit3.size() + " total Critter3    ");
		System.out.print("" + total_straight / (GENE_TOTAL * 0.01 * crit3.size()) + "% straight   ");
		System.out.print("" + total_back / (GENE_TOTAL * 0.01 * crit3.size()) + "% back   ");
		System.out.print("" + total_right / (GENE_TOTAL * 0.01 * crit3.size()) + "% right   ");
		System.out.print("" + total_left / (GENE_TOTAL * 0.01 * crit3.size()) + "% left   ");
		System.out.println();
	}
	
}

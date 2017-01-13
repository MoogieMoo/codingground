import cs1.Keyboard;

public class Array {
    
    public static String[][] board = new String[9][9];//size of playing grid
    
    //empty grid
    public static String ArrayToString ( String [][] arr ) {
	labeling(board);
	chars(board);
	String retstr = "";
	for ( int s = 0; s < arr.length ; s++ ) {
	    for ( int x = 0 ; x < arr[s].length ; x ++) {
		retstr += arr[s][x] + "\t" + " ";
	    }
	    retstr += "\n\n\n\n";
	}
	return retstr;

    }
    
    
    public static void labeling ( String [][] arr ) {
        
	for ( int s = 0; s < arr.length ; s++ ) {//changes grid from being all "null" to space
	    for ( int x = 0 ; x < arr[s].length ; x ++) {
		arr[s][x] = " ";
	    }
	}
	
	for ( int s = 1; s < arr[0].length ; s++ ) {//numbers along x-axis
	    String a = "" + s;
	    arr[0][s] = a;
	}
	
	for ( int s = 1; s < arr.length ; s++ ) {//numbers along y-axis
	    String a = "" + s;
	    arr[s][0] = a;
	}
    }

    public static void chars ( String [][] arr ) {
	int x = Keyboard.readInt();
	int y = Keyboard.readInt();
	arr[y][x] = Keyboard.readWord();
    }

    public static void main ( String[] args ) {
        
	int suns = 15;
	
	String r = "==============================";
	System.out.println(r);
	System.out.println("\tWelcome to PvZ!");
	System.out.println(r);
	String plantsDir = "------------------------------\n\t~Plants Directory~\n------------------------------\nP=\n\tPea Pod: 5 suns (10HP, 5ATK)\nS=\n\tShroom: 3 suns (10HP, 0ATK)\nO=\n\tPotato: 10 suns (25HP, 0ATK)\nC=\n\tCorn Cobbler: 8 suns (10HP, 7ATK)\nT=\n\tCactus: 8 suns (8HP, 8ATK)\nW=\n\tWaterm'Cannon: 15 suns (15HP, 20ATK)";
	System.out.println(plantsDir);
	System.out.println(r);
	System.out.println("Suns = " + suns);
	System.out.println(r);
	System.out.println("Let's begin the game!\n");
	System.out.println("To plant, type in the coordinates and the plant key.");
	System.out.println("For example, if you would like to plant a Pea Pod \nat the coordinates (3, 2), simply type into the terminal:\n\t3\n\t>press the enter key<\n\t2\n\t>press the enter key<\n\tP\n\t>press the enter key<");
	System.out.println(r);
	System.out.println("Now let's begin the game!");
	Array kelly = new Array();
	System.out.println("Type in x and y coordinates and choose a plant:");
	//kelly.chars(board);
	System.out.println(r);
	System.out.println( kelly.ArrayToString(board) );
	System.out.println("Suns = " + suns);
	System.out.println(r);
	
	

    }
}

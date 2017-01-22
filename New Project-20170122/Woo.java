import java.util.Random;

import cs1.Keyboard;


public class Woo {
    
    public static Object[][] board = new Object[9][9];//size of playing grid
    public static Zombie[][] zomBoard = new Zombie[9][9];
    public static Plants[][] plaBoard = new Plants[9][9];
    public static String plantsDir = "------------------------------\n\t~Plants Directory~\n------------------------------\nP=\n\tPea Pod (P): 5 suns (10HP, 5ATK)\nS=\n\tShroom (S): 3 suns (6HP, 4 ATK)\nO=\n\tPotato (T): 10 suns (25HP, 0ATK)\nC=\n\tCorn Cobbler (B): 8 suns (10HP, 7ATK)\nT=\n\tCactus (C): 7 suns (8HP, 8ATK)\nW=\n\tWaterm'Cannon (W): 15 suns (15HP, 20ATK)";
    public static int plantCount = 0;
    public static int suns;
    public static boolean keepGoing = false;
    public static int life = 50;

    public Woo(){
	suns = 15;
    }
    
    //empty grid
    public static String ArrayToString ( Object [][] arr ) {
	//labeling(board);
	//chars(board);
	String retstr = "";
	for ( int s = 0; s < arr.length ; s++ ) {
	    for ( int x = 0 ; x < arr[s].length ; x++ ) {
		retstr += arr[s][x] + "\t" + " ";
	    }
	    retstr += "\n\n\n\n";
	}
	return retstr;

    }

    public static String ArrayToClass(Object[][] arr){
	String retstr = "";
	for ( int s = 0; s < arr.length ; s++ ) {
	    for ( int x = 0 ; x < arr[s].length ; x++ ) {
		retstr += arr[s][x].getClass() + "\t" + " ";
	    }
	    retstr += "\n\n\n\n";
	}
	return retstr;
	
    }
    
    
    public static void labeling ( Object [][] arr ) {
        
	for ( int s = 0; s < arr.length ; s++ ) {//changes grid from being all "null" to space
	    for ( int x = 0 ; x < arr[s].length ; x++ ) {
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

	//arr[0][0] = " \nx \ny";
    }

    public void chars ( Object [][] arr ) {
    System.out.println(plantsDir);
	System.out.println("Choose a plant:");
	String type = Keyboard.readWord();
	System.out.println("Type in x coordinate:");
	int x = Keyboard.readInt();
	System.out.println("Type in y coordinate:");
	int y = Keyboard.readInt();

	if(!(arr[y][x] instanceof Plants)){
	    Plants p = new Plants("-", 5, x, y, type);
	    arr[y][x] = p.symbol;
	    plaBoard[x][y] = p;
	}
	//System.out.println(p.shoot());
	//	moveBullets(arr);
	/*      	for(int i = 0; i < arr.length; i++){
	    for(int j = arr.length - 2; j >= 0; j--){
		if(arr[i][j] instanceof Plants && (Math.random() * 10) < p.freq ){
		    shoot((Plants) arr[i][j]);
		}
	    }
	    }*/


	//System.out.println(plantList);
	//
    }

    public void trackPos(){
	System.out.println("Zombie Board:\n");
	System.out.println(zomBoard);
	System.out.println("Zombie Coordinates:\n");
	for(int i = 0; i < zomBoard.length; i++){
	    for(int j = 0; j < zomBoard[i].length; j++){
		if(zomBoard[i][j] instanceof Zombie){
		    String retStr = "";
		    retStr += "(" + i + "," + j + ")\n" + zomBoard[i][j].health;
		    System.out.println(retStr);
		}
	    }
	}
	System.out.println("Plant Board\n");
	System.out.println(plaBoard);
	System.out.println("Plant Coordinates:\n");
	for(int a = 0; a < plaBoard.length; a++){
	    for(int b = 0; b < plaBoard[a].length; b++){
		if(plaBoard[a][b] instanceof Plants){
		    String retStr2 = "";
		    retStr2 += "(" + a + "," + b + ")\n" + plaBoard[a][b].type;
		    System.out.println(retStr2);
		}
	    }
	}
    }

    public void turn () {
	//return true;
	moveBullets(board);
	System.out.println ( "Would you like to add a plant? \ny = yes \nn = no" );
	boolean go;
	String input = Keyboard.readWord();
	if ( input.equals("y") ) {
	    go = true;
	}
	else { go = false; }
	while ( go == true ) {
	    chars(board);
	    System.out.println ( "Would you like to add a plant? \ny = yes \nn = no" );
	    //boolean go;
	    input = Keyboard.readWord();
	    if ( input.equals("y") ) {
		go = true;
	    }
	    else { go = false; }
	} 
	    //labeling(board);




	    // moveBullets(board);
	    //chars(board);
	    createZombie(board);


	    // System.out.println(r);

	    for(int i = 0; i < board.length; i++){
		for(int j = board.length - 2; j >= 0; j--){
		    if((board[i][j] instanceof Plants) || 
		       ((board[i][j] instanceof String) && (((String)board[i][j]).contains("P")))){
			if((int)(Math.random() * 10.0) < plaBoard[j][i].freq){
			    //	shoot((Plants) board[i][j]);
			    shoot(plaBoard[j][i]);
			    //System.out.println(plaBoard[j][i].bullet);
			}
		    }
		}
	    }
	    


	    //to include /trackPos();
	    hit(board, zomBoard, plaBoard);
	    move(board, zomBoard);
	    System.out.println( ArrayToString(board) );
	    //to include/System.out.println(ArrayToClass(board));
	    // System.out.println("Suns = " + suns);
	   
	    

	    /*for(Plants p: plantList){
		p.shoot();
		}*/




	    cleanup(board);

	    System.out.println("Suns: " + suns);
            System.out.println("Life: " + life);

	    System.out.println("Stop playing?\n(y/n)");
	    input = Keyboard.readWord();
	    if(input.equals("n")){
		keepGoing = true;
		go = true;
	    }
	    else{ go = false; keepGoing = false;}

	    /*System.out.println ( "Would you like to add a plant? \ny = yes \nn = no" );
	    //boolean go;
	    input = Keyboard.readWord();
	    if ( input.equals("y") ) {
		go = true;
	    }
	    else { go = false; }*/
    }

    /*    public static void fitChars ( Object[][] arr){
	if (
	}*/

    public void shoot ( Plants c ) {
	String s = c.bullet;
	if((board[ c.yCor ][ c.xCor + 1].equals(" ")) || (board[c.yCor][c.xCor + 1].equals(""))){
	    board[ c.yCor ][ c.xCor + 1 ] = s; 
	}
    } 

    public void moveBullets ( Object [][] arr ){
	for ( int r = 0; r < arr.length; r++ ) {
	    for ( int c = arr[r].length - 2; c > 0; c-- ) {
		if ( (arr[r][c] instanceof String) && ((String)arr[r][c]).contains("-")){
		    //   System.out.println(arr[r][c]);
		    arr[r][c] = ((String)arr[r][c]).replace("-", "");
		    arr[r][c] = ((String)arr[r][c]).replace(" ", "");
		    //to include /System.out.println(("Class of the bullet's slot: " + arr[r][c]).getClass());
		    arr[r][c + 1] += "-";
		    arr[r][c + 1] = ((String)arr[r][c + 1]).replace(" ", "");
		    //		    System.out.println(arr[r][c + 1]);
		}
		
	    }
	}
    }

    public void createZombie(Object[][] arr){
	boolean proceed = false;
	int x = (int)(Math.random() * 3.0) + 6;
	int y = (int)(Math.random() * 8.0) + 1;
	
	if((arr[y][x] instanceof String) && (!(((String)arr[y][x]).contains("-"))) && (!((String)arr[y][x]).contains("Z"))){
	    //	    if(!(arr[y][x] instanceof Zombie)){
	    if((Math.random() * 10.0) < 5){
		proceed = true;
	    }
	}

	System.out.println("Can create zombie? " + proceed);

	if(proceed == true){
	    Zombie z = new Zombie("Z", 10, 10, 10, x, y);
	    arr[y][x] = z;
	    zomBoard[x][y] = z;
	    proceed = false;
	    
	}
	/*	else{
	    if(!(arr[y + 1][x] instanceof Zombie)){
		Zombie z = new Zombie("Z", 10, 10, 10, x, y);
		arr[y + 1][x] = z;
		zomBoard[x][y + 1] = z;
	    }
	    }*/
    }

    public void hit(Object[][] bBoard, Zombie[][] zBoard, Plants[][] pBoard){
	for(int y = 0; y < bBoard.length; y++){
	    for(int x = 0; x < bBoard[y].length; x++){
		if((bBoard[y][x] instanceof String) && (((String)bBoard[y][x]).contains("-") && ((String)bBoard[y][x]).contains("Z"))){
		    System.out.println("Hit!");
		    System.out.println(bBoard[y][x]);
		    System.out.println(zBoard[x][y].health);
		    zBoard[x][y].adjustHealth(5);
		    System.out.println(zBoard[x][y].health);
		    bBoard[y][x] = ((String)bBoard[y][x]).replace("-", "");
		    if(zBoard[x][y].health <= 0){
		       zBoard[x][y].die();
		       bBoard[y][x] = "" + zBoard[x][y].reward;
		       System.out.println("A Zombie has died");
		       System.out.println(bBoard[y][x]);
		    }
		}
	    }
	}
     }

    public static void move( Object [][] arr, Zombie[][] zArr ){
        for ( int r = 0; r < arr.length; r++ ) {
            for ( int j = 2; j < arr[r].length - 1 ; j++){
                if ( (arr[r][j] instanceof String) && (((String)arr[r][j]).contains("Z"))){
                    arr[r][j] = ((String)arr[r][j]).replace("Z", "");
                    arr[r][j] = ((String)arr[r][j]).replace(" ", "");        

                    arr[r][j - 1] += "Z";
                    arr[r][j - 1] = ((String)arr[r][j - 1]).replace(" ", "");  
                    //zArr[j][r].(super.xCor) -= 1;
                    Zombie mock = zArr[j - 1][r];
                    zArr[j - 1][r] = zArr[j][r];
                    zArr[j][r] = mock;
                }
            }
        }
    }

    public void cleanup(Object[][] arr){
	for(int i = 0; i < 9; i++){
	    if (arr[i][8] instanceof String){
		arr[i][8] = ((String)arr[i][8]).replace("-", " "); 
	    }
	}
	for(int m = 0; m < 9; m++){
	    if ((arr[m][1] instanceof String) && (((String)arr[m][1]).contains("Z"))){
		arr[m][1] = ((String)arr[m][1]).replace("Z", " "); 
		life -= 10; 
	    }
	}
	for(int a = 1; a < arr.length; a++){
	    for(int b = 1; b < arr[a].length; b++){
		if(arr[a][b] instanceof String){
		    try{
			int n = Integer.parseInt((String)arr[a][b]);
			suns += n;
			arr[a][b] = ((String)arr[a][b]).replace("" + n, " ");
		       
		    }
		    catch(NumberFormatException e){}
		}
	    }
	}
    }

    public static void main ( String[] args ) {
        Woo kelly = new Woo();
		
	String r = "==============================";
	System.out.println(r);
	System.out.println("\tWelcome to PvZ!");
	System.out.println(r);

	System.out.println("Suns = " + suns);
	System.out.println("Here is today's game board: ");
	System.out.println(r);
	
	kelly.labeling(board);
	System.out.println( kelly.ArrayToString(board) );

	System.out.println(r);
	
	System.out.println("Now let's begin the game!");
	
	kelly.labeling(board);
	//kelly.chars(board);

	System.out.println(r);
	
	//	System.out.println( kelly.ArrayToString(board) );
	System.out.println("Suns = " + suns);
	System.out.println("Life = " + life);
	System.out.println(r);
	
	//	boolean go = false;
	System.out.println("Play a turn?\n(y/n)");
	String input = Keyboard.readWord();
	if(input.equals("y")){
	    keepGoing = true;
	}
	while(keepGoing == true){
	    kelly.turn();
	    /*System.out.println("Stop playing?\n(y/n)");
	    input = Keyboard.readWord();
	    if(input.equals("n")){
		go = true;
	    }
	    else{ go = false;}*/
	}
    }
}
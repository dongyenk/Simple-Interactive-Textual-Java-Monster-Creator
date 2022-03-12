import java.util.ArrayList;
import java.util.Scanner;

import java.io.FileWriter;
import java.io.FileReader; // character streams, not byte streams like FileInput/OutputStream classes

/** Class models Monster objects which can own multiple Treasure objects.
 * A dynamic ArrayList container is used to store Treasure objects.
 */
public class Monster extends Entity{
    // has inherited Constructors and String type variable.
    private int hitPoints;
    private ArrayList<Treasure> treasures = new ArrayList<Treasure>();

    private Scanner monsterScanner = new Scanner( System.in );
        // giving it the System.in Stream allows me to record keyboard input

    /** Default Constructor
     */
    public Monster(){
        super("Dungeon Rat"); // calls Constructor inherited from Entity
        this.hitPoints = 5;
    }

    /** Constructor that accepts arguments
     */
    public Monster( String type, int hitPoints ){
        super( type );
        this.hitPoints = hitPoints;
    }

    /** Adds Treasure object to Monster's ArrayList.
     * Prevents Treasure variables referencing null from being added.
     * @param newTreasure a Treasure object to be added to the ArrayList Treasure container.
     * @param printMessage a boolean value. true means print message about treasure being added. false means dont do this.
     */
    public void addTreasure( Treasure newTreasure, boolean printMessage ){
        String msg;
        if( newTreasure == null ){
            msg = "The treasure's value is null! It's been ignored.";
        }
        else{
            treasures.add( newTreasure );
            msg = "Treasure added!";
        }
        if( printMessage ) {
            System.out.println(msg);
        }
    }

    /** Save the data about the user's monster to monsterData.txt.
     * Uses the character stream, FileWriter
     */
    public void save(){
        // concatenate String of monster stats and write it to file in special format
        // type
        // HP
        // numTreasures if any
        // treasureType
        // treasureVal
        String stats =
            this.type + "\n" +
            this.hitPoints + "\n";
        // OPTIONAL TREASURES
        int tNum = this.treasures.size();
        if( tNum > 0 ){
            stats += tNum + "\n";
            // for each treasure, write type and val
            for( int i = 0; i < tNum; i ++ ){
                Treasure currentTreasure = treasures.get( i );
                stats +=
                    currentTreasure.getType() + "\n" +
                    currentTreasure.getValue() + "\n";
            }
        }
        //System.out.println( stats ); // gud :)
        try {
            FileWriter destinationFile = new FileWriter("monsterData.txt");
            destinationFile.write(stats);
            destinationFile.close();
            System.out.println( "Monster stats saved to monsterData.txt" );
        }catch( java.io.IOException e ){
            System.out.println( "IOException:\n" + e.toString() );
        }

    }

    /** load data about the user's monster.
     */
    public void load(){
        // Read information stored in monster file, if it exists.
        // put all this in try{}catch(){}
        try{
            FileReader myFile = new FileReader( "monsterData.txt" ); // setup input stream with file as destination (Using FileReader)
            // use Scanner obj on this, to read and remove data from stream.
            Scanner streamScanner = new Scanner( myFile );

            // now see if file has anything
            if( streamScanner.hasNextLine() ){
                //System.out.println( "Load le monster :)" );
                // Since data saving function uses format name, hp, num of treasures, treasure type, treasure val, I can easily read it in
                String monsterType = streamScanner.nextLine();

                int monsterHP = streamScanner.nextInt();
                streamScanner.nextLine(); // when reading 1 line at a time, calling nextLine() on its own is ESSENTIAL, bc nextInt just reads until the end of the 1st integer in the stream
                // allows scanner to read starting from new line next time
                // Call ScannerObj.nextLine(); after nextInt(); to make line by line reading not break!

                this.type = monsterType;
                this.hitPoints = monsterHP;

                // Will be able to replace the basic attributes. But will have to reset the ArrayList treasures
                this.treasures.clear();

                // NOW CHECK IF THERE IS ANY TREASURE LEFT. If so, how much?
                if( streamScanner.hasNextInt() ){ // Treasure here!
                    int numTreasures = streamScanner.nextInt(); // use this in for loop to get name and val of all Treasures
                    streamScanner.nextLine();

                    for( int i = 0; i < numTreasures; i ++ ){
                        String treasureType = streamScanner.nextLine();
                        //System.out.println( treasureType );

                        int treasureVal = streamScanner.nextInt();
                        streamScanner.nextLine();

                        // create treasure with loaded data for the 1 treasure, and add it to array list
                        Treasure retrievedTreasure = new Treasure( treasureType, treasureVal );
                        this.addTreasure( retrievedTreasure, false );
                    }
                }
                System.out.println( "Monster loaded" );
                // myFile.close(); I FORGOT ABOUT CLOSING STREAM, ADDED THIS COMMENT IN GITHUB 

            }
            else{
                System.out.println( "No monster to load!" );
            }

        }catch( java.io.FileNotFoundException e ){
            System.out.println( "File Not Found Error:\n" + e.toString() );
        }
    }

    /** Return an informative String about the Monster object.
     * @return an informative String
     */
    public String toString(){
        String msg =
            "This monster of type " + (this.type).toLowerCase() + " has " + this.hitPoints + " hit points and ";
        int loots = treasures.size();
        if( loots > 0 ){
            msg += loots + " treasures, which are...";
            for( int i = 0; i < loots; i ++ ){
                msg += "\n\t" + treasures.get( i ).toString();
            }
        }
        else{
            msg += " no treasure!!!";
        }
        return msg;
    }

}

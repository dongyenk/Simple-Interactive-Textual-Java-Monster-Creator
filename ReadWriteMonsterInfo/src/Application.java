import java.util.Scanner;

/** The Application class contains the 'main' function which causes the program to run.
 * I made this program to practice object-oriented programming and using external files.
 * @author dongyenk
 * @version 1
 */
public class Application{
    /** The program's static 'main' function which is automatically called and causes the program to run.
     * @param args If arguments are given when calling this program from the command line, they get stored in the 1 dimensional String array called args.
     */
    public static void main( String[] args ){
        // Can put test code directly in here, but instead going to make Application object and call test code through that.
        Application myApp = new Application(); // calls default constructor, because I didnt make one

        //myApp.simpleTest();

        myApp.advancedTest();
    }

    /** Does very basic tests on my code.
     * Creates variations of objects and calls their methods.
     */
    public void simpleTest(){
        Treasure treasure1 = new Treasure(); // calling on Treasure class' constructor
        Treasure treasure2 = new Treasure("Sword", 300);

        Monster monster1 = new Monster();
        Monster monster2 = new Monster("Ogre", 500);

        String msg = "•SIMPLE TEST:\n\t";

        msg += treasure1.toString() + "\n\t" + treasure2.toString() + "\n\t";
        msg += monster1.toString() +"\n\t";
        msg += monster2.toString() + "\n\t";

        monster2.addTreasure( treasure1, true );

        Treasure nullTreasure = null;
        monster2.addTreasure( nullTreasure, true );
        monster2.addTreasure( treasure2, true );

        msg += monster2.toString() + "\n";


        msg += "END OF SIMPLE TEST\n\n";

        System.out.println( msg );
    }

    // WORKING TOWARDS COMMAND LINE MENU AND WHILE LOOP FROM HERE ON

    /** Returns a String to be used as the program's menu.
     * @return Text formatted to be shown as the menu to inform the user about how to use the program.
     */
    public String menuText(){
        String menu =
            "Create a monster and give it treasure!:\n" +
            "\tm - Create monster\n" +
            "\tt - Create treasure\n" +
            "\ta - About monster\n" +
            "\ts - Save monster\n" +
            "\tl - Load monster\n" +
            "\tq - Exit";
        return menu;
    }

    /** This function is for me to practice object-oriented programming
     * and writing to then reading from an external file ("monsterData.txt").
     * The User Can:
     * • create a monster, and save its data to a file.
     * • give it simple coin treasures or a custom treasure.
     * • save monster (+ treasure data if there is any) to a file.
     * • load from that file.
     * • print information about the currently existing monster.
     *
     * The function exits once the player inputs q or Q.
     *
     * monsterData.txt format:
     *     monsterName: String
     *     monsterType: String
     *     monsterHP: int
     *     numberOfTreasures: int // the value of Monster's Treasure arrayList.size();
     *     treasureName: String
     *     treasureVal: int         // THIS TREASURE DATA ONLY WRITTEN TO FILE IF THE ARRAY LIST HAS ANY TREASURE
     *     treasureName: String
     *     treasureVal: int
     *     .... // and more if there is more treasure
     */



    public void advancedTest(){
        Scanner inStreamScanner = new Scanner( System.in );
        String input;

        Monster myMonster = new Monster();
        Treasure myTreasure = null;


        do{
            System.out.println( menuText() );
            input = inStreamScanner.nextLine();

            // ScannerObj.nextInt() causing problems. See default case comments for more.
            if( input.isBlank() ){ // to prevent nextInt causing a blank String being passed to switch
                input = inStreamScanner.nextLine(); // this fixes the problem :)
            }

            input = input.toUpperCase();

            switch( input ){
                case "M": // get Monster type and hit points
                    System.out.print( "Monster type:\t" );
                    String mType = inStreamScanner.nextLine();
                    System.out.print( "Monster integer hit points:\t" );
                    int mHP = inStreamScanner.nextInt();

                    myMonster = new Monster( mType, mHP );

                    break;

                case "T":
                    System.out.println( "Customise treasure? y OR n" );
                    String promptResponse = inStreamScanner.nextLine().toUpperCase();
                    if( promptResponse.equals("Y") ){
                        System.out.print("Treasure type:\t");
                        String tType = inStreamScanner.nextLine();
                        System.out.print("Treasure integer value:\t");
                        int tVal = inStreamScanner.nextInt();

                        myTreasure = new Treasure( tType, tVal );
                        myMonster.addTreasure( myTreasure, true );
                    }
                    else if( promptResponse.equals("N") ){
                        myTreasure = new Treasure();
                        myMonster.addTreasure( myTreasure, true );
                    }
                    else{
                        System.out.println( "Invalid input. Cancelling monster creation" );
                    }

                    break;

                case "A":
                    System.out.println( myMonster.toString() );
                    break;

                case "S":
                    myMonster.save();
                    break;

                case "L":
                    myMonster.load();
                    break;

                default:
                    // IDK why after integer input, the Scanner reads a blank string :/
                    if( input.isBlank() ){ // seems to be caused by ScannerObt.nextInt();
                        System.out.println( "Humph, why is this?" );
                    }
                    System.out.println( "Invalid input:" + input );
                    break;
            }

        }while( !input.equals("Q") );

    }

}
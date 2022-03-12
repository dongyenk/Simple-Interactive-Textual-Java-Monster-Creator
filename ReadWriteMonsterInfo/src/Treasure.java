/** This class models a treasure that may be owned by Monster
 */
public class Treasure extends Entity{
    // has inherited the Constructor and String type variable
    private int value;

    /** Default Constructor.
     * Creates a Coin with a value of 1
     */
    public Treasure(){
        super( "Coin" ); // calls super class' 2nd constructor (sets inherited String type variable)
        this.value = 1;
    }

    /** Argument accepting Constructor.
     * Sets arguments as object attribute values.
     * @param type String value that names the Treasure object's type.
     * @param value int value of the Treasure's worth.
     */
    public Treasure( String type, int value ){
        super( type );
        this.value = value;
    }

    // getType inherited from Entity superClass

    /**
     * Simple getter
     * @return integer value of the value attribute.
     */
    public int getValue(){
        return this.value;
    }

    /** Returns A String about the Treasure object.
     * @return A concatenated String message about the Treasure object's type.
     */
    public String toString(){
        String msg =
            (this.type).toLowerCase() + ", with a value of " + this.value + ".";

        return msg;
    }
}
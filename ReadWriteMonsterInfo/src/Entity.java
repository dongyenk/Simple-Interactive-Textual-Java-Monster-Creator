/** Entity will be the parent class
 * of Treasure and Monster.
 */
public class Entity{
    String type;
    // no public access modifier, as that would ruin this variable's encapsulation.
    // no private access modifier, as that would prevent the variable being inherited

    /** Default Constructor.
     * Sets String type attribute to "Unknown".
     */
    public Entity(){ // called from subclass with super();
        this.type = "Unknown";
    }

    /** Argument accepting Constructor.
     *  Sets String type attribute to whatever String is given as an Argument.
     */
    public Entity( String type ){ // user super(StringArg); to call this in subclass
        this.type = type;
        // this. keyword good for referencing a class variable
    }

    /**
     * Getter that will be inherited by Treasure.
     * @return String type
     */
    public String getType(){
        return this.type;
    }
}
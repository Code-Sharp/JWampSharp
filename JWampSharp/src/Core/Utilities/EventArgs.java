package Core.Utilities;

/**
 * Created by Elad on 15/04/2014.
 */
public class EventArgs {
    private static EventArgs empty = new EventArgs();

    public static EventArgs getEmpty() {
        return empty;
    }
}

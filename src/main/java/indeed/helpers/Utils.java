package indeed.helpers;

/**
 * Utils Class
 *
 * @author Aleksandar Vulovic
 */
public class Utils {

    /**
     * Method for thread sleep
     *
     * @param time time to sleep thread
     */
    public void waitTime(long time) {
        try {
            System.out.println(String.format("Waiting for %sms", time));
            Thread.sleep(time);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Method for static 500ms wait
     */
    public void waitTime500ms() {
        waitTime(500);
    }

    public int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }
}
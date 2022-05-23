
package xyz.app.nlf.utils;

/**
 *
 * @author Lap
 */
public enum Views {
    PRIMARY("primary"),
    HOME("home");

    private final String file_name;
    
    private Views(String file_name) {
        this.file_name = file_name;
    }

    public String fileName() {
        return file_name;
    }
    
}

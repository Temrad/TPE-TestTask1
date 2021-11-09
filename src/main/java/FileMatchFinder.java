import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class FileMatchFinder {
    private Queue<File> results = new LinkedList<>();
    File path;
    int depth;
    String mask;

    FileMatchFinder(File path, int depth, String mask) {
        if ( !path.exists() || !path.isDirectory() )
            throw new RuntimeException("Path does not exist or isn`t a directory");

        setDepth(depth);
        this.path = path;
        this.mask = mask;
    }

    public void seekMatches() {
        List <File> currentLevel = new ArrayList<>();
        currentLevel.add(path);
        do {
            currentLevel = searchLevel(currentLevel);
            depth--;
        } while ( !currentLevel.isEmpty() && depth > 0 );
    }

    private List <File> searchLevel(List <File> dirs) {
        List <File> nextLevel = new ArrayList<>();

        for (File dir : dirs) {
            for (File entry : dir.listFiles()) {
                if( entry.getName().contains(mask) )
                    results.add(entry);
                if ( entry.isDirectory() )
                    nextLevel.add(entry);
            }
        }
        return nextLevel;
    }

    public Queue <File> getResults() {
        Queue <File> currentResult = results;
        results = new LinkedList<>();
        return currentResult;
    }

    public void setDepth(int depth) {
        if ( depth <= 0 )
            throw new IllegalArgumentException("depth has to be a positive integer");
        this.depth = depth;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }
}

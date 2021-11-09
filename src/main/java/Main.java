import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        validateArgs(args);
        FileMatchFinder fileMatchFinder = new FileMatchFinder(new File(args[0]), Integer.parseInt(args[1]), args[2]);
        fileMatchFinder.seekMatches();
        for (File entry : fileMatchFinder.getResults())
            System.out.println(entry.getAbsolutePath());
    }

    private static void validateArgs(String[] args) {
        if( args.length !=3 ) {
            throw new IllegalArgumentException("Invalid argument list. Requires three args: RootPath, depth, mask");
        }
    }
}

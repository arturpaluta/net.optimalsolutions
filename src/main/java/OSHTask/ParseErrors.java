package OSHTask;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Path;

public interface ParseErrors {
    void parseErrors(String[] s) throws IOException;
}

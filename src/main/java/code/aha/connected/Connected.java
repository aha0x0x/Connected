package code.aha.connected;

import code.aha.connected.Connections;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Connected {

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            return;
        }

        Path filepath = Paths.get(args[0]);
        String city1 = args[1];
        String city2 = args[2];

        if (!Files.exists(filepath)) {
            return;
        }

        Connections connections = Files.lines(filepath)
                .collect(new ConnectionsInputCollector<String>());

        if (connections.isConnected(city1, city2)) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}

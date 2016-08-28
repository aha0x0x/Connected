package code.aha.connected;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Connections {

    //TODO: make it immutable if exxposed 
    private final Map<String, Set<String>> connections;

    private Connections(Map<String, Set<String>> connections) {
        this.connections = connections;
    }

    public boolean isConnected(String city1, String city2) {

        String from = city1.trim().toUpperCase();
        String to = city2.trim().toUpperCase();

        if (!connections.containsKey(from) || !connections.containsKey(to)) {
            return false;
        }

        Set<String> visited = new HashSet<>();
        Stack<String> toVisit = new Stack<>();
        toVisit.push(from);

        while (!toVisit.isEmpty()) {
            String current = toVisit.pop();
            if (!connections.containsKey(current)) {
                return false;
            }

            visited.add(current);
            for (String city : connections.get(current)) {

                if (city.equals(to)) {
                    return true;
                }

                if (!visited.contains(city)) {
                    toVisit.add(city);
                }
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((connections == null) ? 0 : connections.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Connections other = (Connections) obj;
        if (connections == null) {
            if (other.connections != null) {
                return false;
            }
        } else if (!connections.equals(other.connections)) {
            return false;
        }
        return true;
    }

    
    public static class ConnectionsBuilder {

        private final Map<String, Set<String>> connections = new HashMap<>();

        public ConnectionsBuilder add(String city1, String city2) {
            String from = city1.trim().toUpperCase();
            String to = city2.trim().toUpperCase();

            if (connections.containsKey(from)) {
                connections.get(from).add(to);
            } else {
                Set<String> siblings = new HashSet<>();
                siblings.add(to);
                connections.put(from, siblings);
            }

            if (connections.containsKey(to)) {
                connections.get(to).add(from);
            } else {
                Set<String> siblings = new HashSet<>();
                siblings.add(from);
                connections.put(to, siblings);
            }
            return this;
        }

        public ConnectionsBuilder merge(ConnectionsBuilder that) {
            connections.putAll(that.connections);
            return this;
        }

        public Connections build() {
            return new Connections(connections);
        }
    }

    @Override
    public String toString() {
        return "Connections [connections=" + connections + "]";
    }

}

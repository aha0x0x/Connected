package code.aha.connected;

import code.aha.connected.Connections;
import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 *
 * @author aha
 */
public class ConnectionsInputCollector<T extends String> implements Collector<T, Connections.ConnectionsBuilder, Connections> {
    
    @Override
    public BiConsumer<Connections.ConnectionsBuilder, T> accumulator() {
        return (code.aha.connected.Connections.ConnectionsBuilder acc, T elem) -> {
            String[] cities = elem.split(",");
            if (cities != null && cities.length == 2) {
                acc.add(cities[0], cities[1]);
            }
        };
    }

    @Override
    public Set<java.util.stream.Collector.Characteristics> characteristics() {
        return EnumSet.of(Collector.Characteristics.UNORDERED);
    }

    @Override
    public BinaryOperator<Connections.ConnectionsBuilder> combiner() {
        return (left, right) -> {
            left.merge(right);
            return left;
        };
    }

    @Override
    public Function<Connections.ConnectionsBuilder, Connections> finisher() {
        return Connections.ConnectionsBuilder::build;
    }

    @Override
    public Supplier<Connections.ConnectionsBuilder> supplier() {
        return Connections.ConnectionsBuilder::new;
    }
}

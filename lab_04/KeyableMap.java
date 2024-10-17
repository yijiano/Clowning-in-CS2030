import java.util.Optional;
import java.util.stream.Stream;

public class KeyableMap<V extends Keyable> implements Keyable {
    private final String name;
    private final ImmutableMap<String, V> map;

    KeyableMap(String name) {
        this.name = name;
        this.map = new ImmutableMap<>();
    }

    protected KeyableMap(String name, ImmutableMap<String, V> map) {
        this.name = name;
        this.map = map;
    }

    protected ImmutableMap<String, V> getMap() {
        return this.map;
    }

    public KeyableMap<V> put(V item) {
        return new KeyableMap<>(this.name, this.map.put(item.getKey(), item));
    }

    public Optional<V> get(String key) {
        return map.get(key);
    }

    @Override
    public String getKey() {
        return this.name;
    }

    @Override
    public String toString() {
        String outputString = map.entrySet()
            .stream()
            .map(entry -> entry.getValue().toString())
            .reduce("", (x,y) -> x.isEmpty() ? y : (x + ", " + y));

        return String.format("%s: {%s}", this.name, outputString);
    }
}


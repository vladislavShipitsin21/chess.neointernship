package neointernship.chess.client;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class MapKeyDeserializer extends KeyDeserializer {

    @Override
    public Object deserializeKey(final String key,
                                 final DeserializationContext ctxt) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(key, IMapKey.class);
    }
}

class MapKeySerializer extends JsonSerializer<MapKey> {

    @Override
    public void serialize(final MapKey value,
                          final JsonGenerator gen,
                          final SerializerProvider serializers) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        gen.writeFieldName(mapper.writeValueAsString(value));
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MapKey.class, name = "MapKey"),
        @JsonSubTypes.Type(value = ExtendedMapKey.class, name = "ExtendedMapKey")
})
interface IMapKey {

}


class MapKey implements IMapKey {
    @JsonProperty
    final int x;
    @JsonProperty
    final int y;

    @JsonCreator
    MapKey(@JsonProperty("x") final int x,
           @JsonProperty("y") final int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + "; " + y + ")";
    }
}

@JsonSubTypes.Type(value = ExtendedMapKey.class, name = "ExtendedMapKey")
class ExtendedMapKey extends MapKey {
    @JsonProperty
    final int z;

    ExtendedMapKey(@JsonProperty("x") final int x,
                   @JsonProperty("y") final int y,
                   @JsonProperty("z") final int z) {
        super(x, y);
        this.z = z;
    }

    @Override
    public String toString() {
        return "(" + x + "; " + y + "; " + z + ")";
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MapValue.class, name = "MapValue"),
        @JsonSubTypes.Type(value = ExtendedMapValue.class, name = "ExtendedMapValue"),
}
)
interface IMapValue {

}


class MapValue implements IMapValue {
    @JsonProperty
    final String firstName;

    @JsonCreator
    MapValue(@JsonProperty("firstName") final String firstName) {
        this.firstName = firstName;
    }

    public static MapValue getMapValueFromString(final String mapValueString) throws JsonProcessingException {
        return new ObjectMapper().readValue(mapValueString, MapValue.class);
    }

    public static String mapValueString(final MapValue mapValue) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(mapValue);
    }

    @Override
    public String toString() {
        return "firstName=" + firstName;
    }
}

class Inner {
    @JsonProperty
    final double i;

    Inner(@JsonProperty("Inner") final double i) {
        this.i = i;
    }
}

class ExtendedMapValue extends MapValue {
    @JsonProperty
    final String secondName;

    @JsonProperty
    final Inner inner;

    ExtendedMapValue(@JsonProperty("firstName") final String firstName,
                     @JsonProperty("secondName") final String secondName,
                     @JsonProperty("Inner") final Inner inner) {
        super(firstName);
        this.secondName = secondName;
        this.inner = inner;
    }

    @Override
    public String toString() {
        return super.toString() + ", secondName=" + secondName + ", inner.i=" + inner.i;
    }
}

public class MapWrapper {

    @JsonSerialize(keyUsing = MapKeySerializer.class)
    @JsonDeserialize(keyUsing = MapKeyDeserializer.class)
    final Map<IMapKey, IMapValue> map;

    public MapWrapper(@JsonProperty("map") final Map<IMapKey, IMapValue> map) {
        this.map = map;
    }

    public static MapWrapper getMapWrapperFromString(final String mapWrapperString) throws JsonProcessingException {
        return new ObjectMapper().readValue(mapWrapperString, MapWrapper.class);
    }

    public static String mapWrapperToString(final MapWrapper mapWrapper) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(mapWrapper);
    }

    public static void main(final String[] args) throws JsonProcessingException {
        final Map<IMapKey, IMapValue> map = new HashMap<>();
        map.put(new MapKey(0, 0), new MapValue("zero"));
        map.put(new ExtendedMapKey(1, 0, 1), new ExtendedMapValue("one", "zero", new Inner(3.14)));
        final MapWrapper mapWrapper = new MapWrapper(map);
        final String wrapper = mapWrapperToString(mapWrapper);
        System.out.println(wrapper);
        final MapWrapper mapWrapperFromString = getMapWrapperFromString(wrapper);
        for (final Map.Entry<IMapKey, IMapValue> mapKeyMapValueEntry : mapWrapperFromString.map.entrySet()) {
            System.out.println(mapKeyMapValueEntry.getKey() + " -> " + mapKeyMapValueEntry.getValue());
        }
    }
}

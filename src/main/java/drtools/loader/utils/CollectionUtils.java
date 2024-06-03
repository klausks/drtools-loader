package drtools.loader.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectionUtils {
    public static <T,ID> Map<ID,T> toMap(List<T> list, Function<? super T, ? extends ID> keyExtractor) {
        return list.stream().collect(Collectors.toMap(keyExtractor, Function.identity()));
    }

    public static <T, ID> List<T> getFromMap(Map<ID, T> map, Collection<ID> keys) {
        return keys.stream().map(map::get).toList();
    }
}

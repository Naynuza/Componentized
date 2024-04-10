package blu0191.componentized.generic;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

public class ExtendsList<T> extends CopyOnWriteArrayList<T> {
    public <U extends T> boolean contains(Class<U> c) {
        return this.stream().anyMatch(item -> item.getClass().equals(c));
    }

    public <U extends T> void add(Class<U> c) {
        try {
            add(c.getDeclaredConstructor().newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <U extends T> boolean addIfTypeAbsent(Class<U> c) {
        boolean absent = !contains(c);
        if (absent) add(c);
        return absent;
    }

    public <U extends T> boolean addIfTypeAbsent(U u) {
        boolean absent = !contains(u.getClass());
        if (absent) add(u);
        return absent;
    }

    @SuppressWarnings("unchecked")
    public <U extends T> void replaceAll(U u) {
        removeAll((Class<U>) u.getClass());
        add(u);
    }

    public <U extends T> void replaceAll(Class<U> c) {
        try {
            replaceAll(c.getDeclaredConstructor().newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <U extends T> void removeAll(Class<U> c) {
        removeAll(stream().filter((T e) -> e.getClass().equals(c)).toList());
    }

    @SuppressWarnings("unchecked")
    public <U extends T> Optional<U> getFirst(Class<U> c) {
        U u = (U) stream().filter((T e) -> e.getClass().equals(c)).findFirst().orElse(null);
        return Optional.ofNullable(u);
    }

    @SuppressWarnings("unchecked")
    public <U extends T> List<U> getAll(Class<U> c) {
        return (List<U>) stream().filter((T e) -> e.getClass().equals(c)).toList();
    }
}

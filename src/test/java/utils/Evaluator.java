package utils;

import exceptions.AutotestException;

import java.util.HashMap;
import java.util.Map;

public class Evaluator {

    public final static Map<Object, Object> STASH = new HashMap<>();

    public static void setVariable(String name, Object value) {
        STASH.put(name, value);
    }

    public static boolean containsKey(String key) {
        return STASH.containsKey(key);
    }

    public static <T> T getVariable(String name) throws AutotestException {
        if (name == null) {
            throw new AutotestException("method getVariable instanced with value null");
        }
        return (T) STASH.get(name);
    }
}

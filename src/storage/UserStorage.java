package storage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zw531129 on 15/4/5
 * So, what's the fucking copyright?
 */
public class UserStorage {
    public static final Map<Integer, String> users = new ConcurrentHashMap<>();
}

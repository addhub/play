package global;

import com.typesafe.config.*;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by sasinda on 10/24/15.
 */
public class AppConfig {

    static com.typesafe.config.Config conf;
    static {
        conf=ConfigFactory.load();
    }

    public static ConfigObject root() {
        return conf.root();
    }

    public static com.typesafe.config.Config withoutPath(String path) {
        return conf.withoutPath(path);
    }

    @Deprecated
    public static Long getNanoseconds(String path) {
        return conf.getNanoseconds(path);
    }

    public static com.typesafe.config.Config resolve(ConfigResolveOptions options) {
        return conf.resolve(options);
    }

    public static Object getAnyRef(String path) {
        return conf.getAnyRef(path);
    }

    public static long getDuration(String path, TimeUnit unit) {
        return conf.getDuration(path, unit);
    }

    public static List<ConfigMemorySize> getMemorySizeList(String path) {
        return conf.getMemorySizeList(path);
    }

    public static long getLong(String path) {
        return conf.getLong(path);
    }

    public static ConfigMemorySize getMemorySize(String path) {
        return conf.getMemorySize(path);
    }

    public static List<Long> getLongList(String path) {
        return conf.getLongList(path);
    }

    public static List<? extends Object> getAnyRefList(String path) {
        return conf.getAnyRefList(path);
    }

    public static Number getNumber(String path) {
        return conf.getNumber(path);
    }

    @Deprecated
    public static List<Long> getNanosecondsList(String path) {
        return conf.getNanosecondsList(path);
    }

    public static boolean isEmpty() {
        return conf.isEmpty();
    }

    public static boolean hasPathOrNull(String path) {
        return conf.hasPathOrNull(path);
    }

    public static com.typesafe.config.Config getConfig(String path) {
        return conf.getConfig(path);
    }

    public static com.typesafe.config.Config resolve() {
        return conf.resolve();
    }

    public static void checkValid(com.typesafe.config.Config reference, String... restrictToPaths) {
        conf.checkValid(reference, restrictToPaths);
    }

    public static String getString(String path) {
        return conf.getString(path);
    }

    public static boolean isResolved() {
        return conf.isResolved();
    }

    public static ConfigObject getObject(String path) {
        return conf.getObject(path);
    }

    public static com.typesafe.config.Config withOnlyPath(String path) {
        return conf.withOnlyPath(path);
    }

    public static com.typesafe.config.Config resolveWith(com.typesafe.config.Config source) {
        return conf.resolveWith(source);
    }

    public static List<Boolean> getBooleanList(String path) {
        return conf.getBooleanList(path);
    }

    public static List<Double> getDoubleList(String path) {
        return conf.getDoubleList(path);
    }

    @Deprecated
    public static Long getMilliseconds(String path) {
        return conf.getMilliseconds(path);
    }

    public static double getDouble(String path) {
        return conf.getDouble(path);
    }

    public static boolean hasPath(String path) {
        return conf.hasPath(path);
    }

    public static com.typesafe.config.Config atPath(String path) {
        return conf.atPath(path);
    }

    public static List<Long> getDurationList(String path, TimeUnit unit) {
        return conf.getDurationList(path, unit);
    }

    public static ConfigOrigin origin() {
        return conf.origin();
    }

    public static List<? extends com.typesafe.config.Config> getConfigList(String path) {
        return conf.getConfigList(path);
    }

    public static boolean getBoolean(String path) {
        return conf.getBoolean(path);
    }

    public static Long getBytes(String path) {
        return conf.getBytes(path);
    }

    public static List<Number> getNumberList(String path) {
        return conf.getNumberList(path);
    }

    public static List<? extends ConfigObject> getObjectList(String path) {
        return conf.getObjectList(path);
    }

    public static boolean getIsNull(String path) {
        return conf.getIsNull(path);
    }

    @Deprecated
    public static List<Long> getMillisecondsList(String path) {
        return conf.getMillisecondsList(path);
    }

    public static com.typesafe.config.Config withValue(String path, ConfigValue value) {
        return conf.withValue(path, value);
    }

    public static ConfigList getList(String path) {
        return conf.getList(path);
    }

    public static List<String> getStringList(String path) {
        return conf.getStringList(path);
    }

    public static Duration getDuration(String path) {
        return conf.getDuration(path);
    }

    public static List<Long> getBytesList(String path) {
        return conf.getBytesList(path);
    }

    public static ConfigValue getValue(String path) {
        return conf.getValue(path);
    }

    public static List<Duration> getDurationList(String path) {
        return conf.getDurationList(path);
    }

    public static int getInt(String path) {
        return conf.getInt(path);
    }

    public static com.typesafe.config.Config withFallback(ConfigMergeable other) {
        return conf.withFallback(other);
    }

    public static List<Integer> getIntList(String path) {
        return conf.getIntList(path);
    }

    public static com.typesafe.config.Config resolveWith(com.typesafe.config.Config source, ConfigResolveOptions options) {
        return conf.resolveWith(source, options);
    }

    public static Set<Map.Entry<String, ConfigValue>> entrySet() {
        return conf.entrySet();
    }

    public static com.typesafe.config.Config atKey(String key) {
        return conf.atKey(key);
    }
}

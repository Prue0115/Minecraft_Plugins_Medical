package me.tacticalmedic.config;

import me.tacticalmedic.TacticalMedic;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

    private static FileConfiguration config;

    public static void init(TacticalMedic plugin) {
        plugin.saveDefaultConfig();
        config = plugin.getConfig();
    }

    public static int getInt(String path, int defaultValue) {
        if (config.contains(path)) {
            return config.getInt(path);
        }
        return defaultValue;
    }

    public static double getDouble(String path, double defaultValue) {
        if (config.contains(path)) {
            return config.getDouble(path);
        }
        return defaultValue;
    }

    public static boolean getBoolean(String path, boolean defaultValue) {
        if (config.contains(path)) {
            return config.getBoolean(path);
        }
        return defaultValue;
    }

    public static String getString(String path, String defaultValue) {
        if (config.contains(path)) {
            return config.getString(path);
        }
        return defaultValue;
    }
}

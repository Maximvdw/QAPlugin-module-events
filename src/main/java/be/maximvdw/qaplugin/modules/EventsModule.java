package be.maximvdw.qaplugin.modules;

import be.maximvdw.qaplugin.api.AIModule;
import be.maximvdw.qaplugin.api.annotations.*;
import be.maximvdw.qaplugin.modules.listeners.JoinListener;
import be.maximvdw.qaplugin.modules.listeners.QuitListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;

import java.io.IOException;

/**
 * EventsModule
 * <p>
 * Created by maxim on 25-Jan-17.
 */
@ModuleName("Events")
@ModuleActionName("events")
@ModuleAuthor("Maximvdw")
@ModuleDescription("Trigger API.ai events. Config file to enable/disable events.")
@ModuleVersion("1.0.0")
@ModuleConstraint(type = ModuleConstraint.ContraintType.QAPLUGIN_VERSION, value = "1.9.5")
public class EventsModule extends AIModule {
    private static EventsModule instance;

    public static EventsModule getInstance() {
        return instance;
    }

    public static void setInstance(EventsModule instance) {
        EventsModule.instance = instance;
    }

    @Override
    public void onEnable() {
        setInstance(this);
        getConfig().addDefault("events.player.join", true);
        getConfig().addDefault("events.player.quit", true);
        getConfig().options().copyDefaults(true);
        saveConfig();
        try {
            getConfig().load(getConfigFile());
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        if (getConfig().getBoolean("events.player.join")) {
            Bukkit.getPluginManager().registerEvents(new JoinListener(getPlugin()), getPlugin());
        }
        if (getConfig().getBoolean("events.player.quit")) {
            Bukkit.getPluginManager().registerEvents(new QuitListener(getPlugin()), getPlugin());
        }
    }

    @Override
    public void onDisable() {

    }


}

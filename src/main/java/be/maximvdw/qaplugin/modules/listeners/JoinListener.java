package be.maximvdw.qaplugin.modules.listeners;

import be.maximvdw.qaplugin.QAPlugin;
import be.maximvdw.qaplugin.api.QAPluginAPI;
import be.maximvdw.qaplugin.api.ai.Context;
import be.maximvdw.qaplugin.modules.EventsModule;
import be.maximvdw.qaplugin.question.AnswerLine;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * JoinListener
 * Created by maxim on 25-Jan-17.
 */
public class JoinListener implements Listener {
    private QAPlugin plugin = null;

    public JoinListener(QAPlugin plugin) {
        setPlugin(plugin);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        if (player.hasPlayedBefore()) {
            // Not first join
            Bukkit.getScheduler().runTaskAsynchronously(getPlugin(), new Runnable() {
                @Override
                public void run() {
                    Context context = new Context("player");
                    context.setLifespan(1);
                    context.addParameter("username", player.getName());
                    context.addParameter("uuid", player.getUniqueId().toString());
                    EventsModule.getInstance().addContext(context, player);
                    AnswerLine answerLine = QAPluginAPI.sendAIEvent("PLAYER_JOIN", player);
                    QAPluginAPI.sendMessaagAsBot(answerLine.getAnswer(), player, true);
                }
            });
        } else {
            // First join
            Bukkit.getScheduler().runTaskAsynchronously(getPlugin(), new Runnable() {
                @Override
                public void run() {
                    Context context = new Context("player");
                    context.setLifespan(1);
                    context.addParameter("username", player.getName());
                    context.addParameter("uuid", player.getUniqueId().toString());
                    EventsModule.getInstance().addContext(context, player);
                    AnswerLine answerLine = QAPluginAPI.sendAIEvent("PLAYER_FIRST_JOIN", player);
                    QAPluginAPI.sendMessaagAsBot(answerLine.getAnswer(), player, true);
                }
            });
        }
    }

    public QAPlugin getPlugin() {
        return plugin;
    }

    public void setPlugin(QAPlugin plugin) {
        this.plugin = plugin;
    }
}

package com.frostyy.nofarfireworks;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class NoFarFireworks extends JavaPlugin implements Listener {

    private static final double MAX_DISTANCE = 12_000_000.0;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("NoFarFireworks enabled.");
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onFireworkUse(PlayerInteractEvent event) {
        Action action = event.getAction();

        if (action != Action.RIGHT_CLICK_AIR && action != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        ItemStack item = event.getItem();
        if (item == null || item.getType() != Material.FIREWORK_ROCKET) {
            return;
        }

        Player player = event.getPlayer();
        double x = player.getLocation().getX();
        double z = player.getLocation().getZ();

        if (Math.abs(x) > MAX_DISTANCE || Math.abs(z) > MAX_DISTANCE) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "You cannot use fireworks more than 12 million blocks from 0,0.");
        }
    }
}

package xyz.simnple.overrideDoTileDrop;

import org.bukkit.GameRule;
import org.bukkit.block.ShulkerBox;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class OverrideDoTileDrop extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (e.isCancelled()) {
            return;
        }

        if (!(e.getBlock().getState() instanceof ShulkerBox)) {
            return;
        }

        if (Boolean.TRUE.equals(e.getBlock().getWorld().getGameRuleValue(GameRule.DO_TILE_DROPS))) {
            return;
        }

        if (!e.isDropItems()) {
            return;
        }

        e.setDropItems(false);

        for (ItemStack item : e.getBlock().getDrops()) {
            e.getBlock().getWorld().dropItem(e.getBlock().getLocation().toCenterLocation(), item);
        }
    }
}

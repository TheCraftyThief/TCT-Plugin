package club.thecraftythief.game;

import club.thecraftythief.engine.model.ModelData;
import club.thecraftythief.engine.model.ModelMgr;
import club.thecraftythief.engine.model.events.ModelInteractEvent;
import club.thecraftythief.game.interaction.InteractionListener;
import club.thecraftythief.game.models.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Logger;

public class Main extends JavaPlugin implements Listener {
    HashMap<UUID, ArmorStand> armorStands = new HashMap<>();

    @Override
    public void onLoad() {
        super.onLoad();
        Logger log = getLogger();
        log.info("Starting TCT-Game load...");
        log.info("Finished loading sequence");
    }

    @Override
    public void onEnable() {
        super.onEnable();
        Logger log = getLogger();
        log.info("Enabling TCT-Game");

        ModelMgr.getInstance().registerModel(new ComputerTowerModel());
        ModelMgr.getInstance().registerModel(new GoldBarModel());
        ModelMgr.getInstance().registerModel(new PhoneModel());
        ModelMgr.getInstance().registerModel(new RubiksCubeModel());
        ModelMgr.getInstance().registerModel(new CashModel());
        ModelMgr.getInstance().registerModel(new LaptopModel());
        ModelMgr.getInstance().registerModel(new TVModel());
        ModelMgr.getInstance().registerModel(new KeyModel());
        ModelMgr.getInstance().registerModel(new AmongUsModel());

        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new InteractionListener(), this);

        log.info("TCT-Game enabled");
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @EventHandler
    public void onModelInteract(ModelInteractEvent e) {
        ModelData data = e.getModel();
        ArmorStand stand = e.getEntity();
        Player player = e.getPlayer();

        player.sendMessage("Hi \"" + player.getName() + "\", you clicked on a \"" + data.getModelName() + "\" model! This model is held by the ArmorStand \"" + stand.getUniqueId() + "\"!");
    }
}

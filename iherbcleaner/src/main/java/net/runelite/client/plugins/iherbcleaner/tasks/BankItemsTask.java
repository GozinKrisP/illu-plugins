package net.runelite.client.plugins.iherbcleaner.tasks;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.events.GameTick;
import net.runelite.client.plugins.iherbcleaner.Task;
import net.runelite.client.plugins.iherbcleaner.iHerbCleanerPlugin;
import net.runelite.client.plugins.iutils.ActionQueue;
import net.runelite.client.plugins.iutils.BankUtils;
import net.runelite.client.plugins.iutils.InventoryUtils;

import javax.inject.Inject;

import static net.runelite.client.plugins.iherbcleaner.iHerbCleanerPlugin.startBot;
import static net.runelite.client.plugins.iherbcleaner.iHerbCleanerPlugin.status;

@Slf4j
public class BankItemsTask extends Task {

    @Inject
    ActionQueue action;

    @Inject
    InventoryUtils inventory;

    @Inject
    BankUtils bank;

    @Override
    public boolean validate() {
        int herbID = config.herbType().getItemId();
        return action.delayedActions.isEmpty() && !inventory.containsItem(herbID) && bank.isOpen();
    }

    @Override
    public String getTaskDescription() {
        return status;
    }

    @Override
    public void onGameTick(GameTick event) {
        int herbID = config.herbType().getItemId();

        if (!inventory.isEmpty()) {
            status = "Depositing items";
            // Hanya deposit herb yang sudah bersih, aman tanpa klik search
            bank.depositAllExcept(herbID);
        } else {
            status = "Withdrawing items";
            if (bank.contains(herbID, 1)) {
                bank.withdrawAllItem(herbID);
            } else {
                status = "Out of herbs to clean, stopping";
                utils.sendGameMessage(status);
                startBot = false;
            }
        }
        log.info(status);
    }
}

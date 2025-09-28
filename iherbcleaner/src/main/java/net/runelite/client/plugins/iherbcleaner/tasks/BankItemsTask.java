package net.runelite.client.plugins.iherbcleaner.tasks;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.events.GameTick;
import net.runelite.client.plugins.iherbcleaner.Task;
import net.runelite.client.plugins.iherbcleaner.iHerbCleanerPlugin;
import net.runelite.client.plugins.iutils.BankUtils;
import net.runelite.client.plugins.iutils.InventoryUtils;

import javax.inject.Inject;

import static net.runelite.client.plugins.iherbcleaner.iHerbCleanerPlugin.startBot;
import static net.runelite.client.plugins.iherbcleaner.iHerbCleanerPlugin.status;

@Slf4j
public class BankItemsTask extends Task {

    @Inject
    InventoryUtils inventory;

    @Inject
    BankUtils bank;

    @Override
    public boolean validate() {
        // Task ini jalan hanya jika bank terbuka
        return bank.isOpen();
    }

    @Override
    public String getTaskDescription() {
        return status;
    }

    @Override
    public void onGameTick(GameTick event) {

        if (!inventory.isEmpty()) {
            // Deposit semua item di inventory
            status = "Depositing items";
            bank.depositAllItems(); // pastikan method ini klik 'Deposit All' secara aman
        } else {
            // Withdraw full inventory dari herb yang dipilih
            int herbID = config.herbID();
            if (bank.contains(herbID, 1)) {
                bank.withdrawAllItem(herbID);
                status = "Withdrawing herb ID: " + herbID;
            } else {
                status = "Out of herbs to clean, stopping";
                utils.sendGameMessage(status);
                startBot = false;
            }
        }
        log.info(status);
    }
}

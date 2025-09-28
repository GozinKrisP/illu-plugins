package net.runelite.client.plugins.iherbcleaner;

import net.runelite.client.config.*;

@ConfigGroup("iHerbCleaner")
public interface iHerbCleanerConfig extends Config {

    // ================= Sleep Delay Configuration =================
    @ConfigTitle(
            keyName = "delayConfig",
            name = "Sleep Delay Configuration",
            description = "Configure how the bot handles sleep delays",
            position = 0
    )
    String delayConfig = "delayConfig";

    @Range(min = 0, max = 550)
    @ConfigItem(keyName = "sleepMin", name = "Sleep Min", description = "", position = 1, section = "delayConfig")
    default int sleepMin() { return 60; }

    @Range(min = 0, max = 550)
    @ConfigItem(keyName = "sleepMax", name = "Sleep Max", description = "", position = 2, section = "delayConfig")
    default int sleepMax() { return 350; }

    @Range(min = 0, max = 550)
    @ConfigItem(keyName = "sleepTarget", name = "Sleep Target", description = "", position = 3, section = "delayConfig")
    default int sleepTarget() { return 100; }

    @Range(min = 0, max = 550)
    @ConfigItem(keyName = "sleepDeviation", name = "Sleep Deviation", description = "", position = 4, section = "delayConfig")
    default int sleepDeviation() { return 10; }

    @ConfigItem(keyName = "sleepWeightedDistribution", name = "Sleep Weighted Distribution",
            description = "Shifts the random distribution towards the lower end at the target", position = 5, section = "delayConfig")
    default boolean sleepWeightedDistribution() { return false; }

    // ================= Tick Delay Configuration =================
    @ConfigTitle(keyName = "delayTickConfig", name = "Game Tick Configuration",
            description = "Configure how the bot handles game tick delays", position = 10)
    String delayTickConfig = "delayTickConfig";

    @Range(min = 0, max = 10)
    @ConfigItem(keyName = "tickDelayMin", name = "Game Tick Min", description = "", position = 11, section = "delayTickConfig")
    default int tickDelayMin() { return 1; }

    @Range(min = 0, max = 10)
    @ConfigItem(keyName = "tickDelayMax", name = "Game Tick Max", description = "", position = 12, section = "delayTickConfig")
    default int tickDelayMax() { return 3; }

    @Range(min = 0, max = 10)
    @ConfigItem(keyName = "tickDelayTarget", name = "Game Tick Target", description = "", position = 13, section = "delayTickConfig")
    default int tickDelayTarget() { return 2; }

    @Range(min = 0, max = 10)
    @ConfigItem(keyName = "tickDelayDeviation", name = "Game Tick Deviation", description = "", position = 14, section = "delayTickConfig")
    default int tickDelayDeviation() { return 1; }

    @ConfigItem(keyName = "tickDelayWeightedDistribution", name = "Game Tick Weighted Distribution",
            description = "Shifts the random distribution towards the lower end at the target", position = 15, section = "delayTickConfig")
    default boolean tickDelayWeightedDistribution() { return false; }

    // ================= Instructions =================
    @ConfigTitle(keyName = "instructionsTitle", name = "Instructions", description = "", position = 16)
    String instructionsTitle = "instructionsTitle";

    @ConfigItem(keyName = "instructions", name = "", description = "Instructions. Don't enter anything here", position = 17, title = "instructionsTitle")
    default String instructions() {
        return "Have herbs in bank, setup herb type in config, and press Start. Sleep Delays determine time between cleaning.";
    }

    // ================= Herb Configuration =================
    public enum HerbType {
        GUAM_LEAF(199),
        MARRENTILL(201),
        TARROMIN(203),
        HARRALANDER(205),
        RANARR_WEED(207),
        IRIT_LEAF(209),
        AVANTOE(211),
        KWUARM(213),
        CADANTINE(215),
        DWARF_WEED(217),
        TORSTOL(219);

        private final int itemId;

        HerbType(int itemId) { this.itemId = itemId; }
        public int getItemId() { return itemId; }
    }

    @ConfigItem(keyName = "herbType", name = "Herb Type", description = "Select the herb to clean", position = 21)
    default HerbType herbType() { return HerbType.HARRALANDER; }

    @ConfigItem(keyName = "bankID", name = "Bank Booth ID", description = "Game Object ID of Bank Booth", position = 25)
    default int bankID() { return 10583; }

    @ConfigItem(keyName = "enableUI", name = "Enable UI", description = "Enable in-game overlay", position = 95)
    default boolean enableUI() { return true; }

    @ConfigItem(keyName = "startButton", name = "Start/Stop", description = "Start/Stop bot", position = 100)
    default Button startButton() { return new Button(); }
}

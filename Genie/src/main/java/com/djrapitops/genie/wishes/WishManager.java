package com.djrapitops.genie.wishes;

import com.djrapitops.genie.Genie;
import com.djrapitops.genie.Log;
import com.djrapitops.genie.Settings;
import com.djrapitops.genie.file.WishConfigSectionHandler;
import com.djrapitops.genie.file.WishLog;
import com.djrapitops.genie.wishes.item.*;
import com.djrapitops.genie.wishes.mob.*;
import com.djrapitops.genie.wishes.other.FlyingWish;
import com.djrapitops.genie.wishes.potion.PotionEffectWish;
import com.djrapitops.genie.wishes.teleport.TeleportHereWish;
import com.djrapitops.genie.wishes.teleport.TeleportToBedWish;
import com.djrapitops.genie.wishes.teleport.TeleportToWish;
import com.djrapitops.genie.wishes.world.*;
import com.djrapitops.plugin.task.AbsRunnable;
import com.djrapitops.plugin.utilities.Verify;
import com.djrapitops.plugin.utilities.version.EnumUtility;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

/**
 * @author Rsl1122
 */
public class WishManager {

    private final Genie plugin;
    private final List<Wish> wishes;
    private final WishLog log;
    private final WishConfigSectionHandler configSection;

    /**
     * @param plugin
     */
    public WishManager(Genie plugin) {
        this.plugin = plugin;
        this.log = plugin.getWishLog();
        this.configSection = plugin.getWishConfigSectionHandler();
        wishes = new ArrayList<>();
        addWishes();
    }

    private void addWishes() {
        List<Wish> toAdd = new ArrayList<>();
        addMobWishes(toAdd);
        addItemWishes(toAdd);
        addPotionWishes(toAdd);
        addCommandWishes(toAdd);
        // addEnchantWishes(toAdd); // Can't combine unsafe books in an anvil
        toAdd.add(new AnimalWish());
        toAdd.add(new FarmWish());
        toAdd.add(new FoodWish());
        toAdd.add(new ArmorWish("CHAINMAIL"));
        toAdd.add(new ArmorWish("LEATHER"));
        toAdd.add(new ArmorWish("DIAMOND"));
        toAdd.add(new ArmorWish("IRON"));
        toAdd.add(new ArmorWish("GOLD"));
        toAdd.add(new DogWish());
        toAdd.add(new DogTreatWish());
        toAdd.add(new CatWish());
        toAdd.add(new CatTreatWish());
        toAdd.add(new SunnyWish());
        toAdd.add(new ThunderWish());
        toAdd.add(new ExplosionWish());
        toAdd.add(new TeleportHereWish());
        toAdd.add(new TeleportToBedWish());
        toAdd.add(new TeleportToWish());
        toAdd.add(new AssassinWish());
        toAdd.add(new FlyingWish());
        toAdd.add(new DayWish());
        toAdd.add(new NightWish());
        toAdd.sort(new WishComparator());
        for (Wish wish : toAdd) {
            addWish(wish);
        }
        Log.info("Initialized with " + wishes.size() + " wishes");
        plugin.processStatus().setStatus("Wishes", wishes.size() + "");
    }

    private void addCommandWishes(List<Wish> toAdd) {
        List<String> customLines = plugin.getConfig().getStringList(Settings.COMMAND_LIST.getPath());
        if (Verify.isEmpty(customLines)) {
            return;
        }
        Map<String, String[]> commands = new HashMap<>();
        for (String customLine : customLines) {
            String[] parts = customLine.split(" \\| ");
            if (parts.length < 2) {
                Log.error("Incorrectly formatted command wish: " + customLine);
                Log.info("Correct format: /command | wish1, wish2, wish3, etc");
                continue;
            }
            String cmd = parts[0].substring(1).trim();
            String aliasList = parts[1];
            String[] aliases = aliasList.split(",");
            for (int i = 0; i < aliases.length; i++) {
                aliases[i] = removeCommonWords(aliases[i].toLowerCase().trim());
            }
            Log.debug("Custom Command Wish: " + cmd + ": " + Arrays.toString(aliases));
            if (cmd.contains("{playername}")) {
                toAdd.add(new PlayerSpecificCommandWish(plugin, cmd, aliases));
            } else {
                toAdd.add(new BasicCommandWish(plugin, cmd, aliases));
            }
        }
    }

    // Can't combine unsafe books in an anvil
    private void addEnchantWishes(List<Wish> toAdd) {
        for (Enchantment enchant : Enchantment.values()) {
            for (int i = 1; i <= 10; i++) {
                toAdd.add(new EnchantmentWish(enchant, "" + i));
            }
            for (String romanLevel : "I,II,III,IV,V,VI,VII,VIII,IIX,IX,X".split(",")) {
                toAdd.add(new EnchantmentWish(enchant, romanLevel));
            }
        }
    }

    private void addPotionWishes(List<Wish> toAdd) {
        List<PotionEffectType> prevented = getPreventedPotions();
        for (PotionEffectType potion : PotionEffectType.values()) {
            if (potion == null) {
                continue;
            }
            if (!Verify.contains(potion, prevented)) {
                toAdd.add(new PotionEffectWish(potion));
            }
        }
    }

    private void addItemWishes(List<Wish> toAdd) {
        List<Material> preventedMats = getPreventedItems();
        for (Material material : Material.values()) {
            if (!Verify.contains(material, preventedMats)) {
                toAdd.add(new ItemWish(material));
            }
        }
    }

    private void addMobWishes(List<Wish> toAdd) {
        List<EntityType> prevented = getPreventedEntities();
        for (EntityType mob : EntityType.values()) {
            if (!Verify.contains(mob, prevented)) {
                toAdd.add(new SpawnMobWish(mob));
            }
            for (EntityType stackMob : EntityType.values()) {
                if (!prevented.contains(mob) && !prevented.contains(stackMob)) {
                    toAdd.add(new SpawnMobRidingOnWish(mob, stackMob));
                }
            }
        }
    }

    public List<PotionEffectType> getPreventedPotions() {
        return EnumUtility.getSupportedEnumValues(PotionEffectType.class,
                "WITHER", "HEAL"
        );
    }

    public List<EntityType> getPreventedEntities() {
        return EnumUtility.getSupportedEnumValues(EntityType.class,
                "AREA_EFFECT_CLOUD", "ARMOR_STAND", "COMPLEX_PART",
                "DRAGON_FIREBALL", "DROPPED_ITEM", "EGG",
                "ENDER_PEARL", "ENDER_SIGNAL", "EXPERIENCE_ORB",
                "FALLING_BLOCK", "FIREBALL", "FIREWORK",
                "FISHING_HOOK", "EVOKER_FANGS", "ITEM_FRAME",
                "LEASH_HITCH", "LIGHTNING", "LINGERING_POTION",
                "LLAMA_SPIT", "MINECART", "MINECART_CHEST",
                "MINECART_COMMAND", "MINECART_FURNACE", "MINECART_HOPPER",
                "MINECART_MOB_SPAWNER", "MINECART_TNT", "PAINTING",
                "PLAYER", "PRIMED_TNT", "SHULKER_BULLET",
                "THROWN_EXP_BOTTLE", "TIPPED_ARROW", "UNKNOWN",
                "WEATHER", "WITHER_SKULL", "ARROW", "BOAT",
                "SPLASH_POTION", "SMALL_FIREBALL", "ENDER_DRAGON"
        );
    }

    public List<EntityType> getNonHostileEntities() {
        return EnumUtility.getSupportedEnumValues(EntityType.class,
        	"BAT","CHICKEN","COW","PIG","SHEEP","RABBIT","HORSE","SQUID","VILLAGER",
        	"MUSHROOM_COW","POLAR_BEAR","SKELETON_HORSE","DONKEY","WOLF","OCELOT","MULE",
        	"LLAMA","PARROT","IRON_GOLEM","SNOWMAN"
        );
    }
    public List<Material> getPreventedItems() {
        return EnumUtility.getSupportedEnumValues(Material.class,
                "ACACIA_DOOR", "BEDROCK", "AIR",
                "BED_BLOCK", "BEETROOT_BLOCK", "BIRCH_DOOR",
                "BREWING_STAND", "BURNING_FURNACE", "CAKE_BLOCK",
                "CARROT", "COMMAND", "CAULDRON",
                "COMMAND", "COMMAND_CHAIN", "COMMAND_REPEATING",
                "COMMAND_MINECART", "DIODE_BLOCK_ON", "DARK_OAK_DOOR",
                "DAYLIGHT_DETECTOR_INVERTED", "DIODE_BLOCK_ON", "DIODE_BLOCK_OFF",
                "DOUBLE_PLANT", "DOUBLE_STONE_SLAB2", "EMPTY_MAP",
                "ENDER_PORTAL", "ENDER_PORTAL_FRAME", "FIREWORK_CHARGE",
                "FLOWER_POT", "HUGE_MUSHROOM_1", "HUGE_MUSHROOM_2",
                "IRON_DOOR_BLOCK", "JUNGLE_DOOR", "KNOWLEDGE_BOOK",
                "MONSTER_EGG", "MONSTER_EGGS", "PISTON_BASE",
                "PISTON_EXTENSION", "PISTON_MOVING_PIECE", "PISTON_STICKY_BASE",
                "REDSTONE_COMPARATOR_OFF", "REDSTONE_LAMP_ON", "REDSTONE_TORCH_OFF",
                "SIGN_POST", "SKULL", "SKULL_ITEM",
                "SNOW", "SPRUCE_DOOR", "SUGAR_CANE_BLOCK",
                "STRUCTURE_BLOCK", "STRUCTURE_VOID", "STANDING_BANNER",
                "STATIONARY_WATER", "STATIONARY_LAVA", "TIPPED_ARROW",
                "TRIPWIRE", "WALL_SIGN", "WATER", "WATER_LILY",
                "LAVA", "WRITTEN_BOOK", "WALL_BANNER", "POTATO");
    }

    public void addWish(Wish wish) {
        if (!configSection.exists(wish)) {
            configSection.createSection(wish);
        }
        if (configSection.isAllowed(wish)) {
            wishes.add(wish);
        }
    }

    /**
     * @param p
     * @param wish
     * @return Could the wish be fulfilled?
     */
    public boolean wish(Player p, String wish) {
        log.madeAWish(p, wish);
        String[] parts = wish.split(" with ");
        Set<Wish> matches = new HashSet<>();
        int i = 0;
        for (String part : parts) {
            if (i >= 2) {
                break;
            }
            Wish match = getMatchingWish(part.trim(), p);
            if (match != null) {
                matches.add(match);
            }
            i++;
        }
        if (!matches.isEmpty()) {
            plugin.getRunnableFactory().createNew(new AbsRunnable("Wish Fulfillment Task") {
                @Override
                public void run() {
                    try {
                        for (Wish wish : matches) {
                            wish.fulfillWish(p);
                        }
                    } finally {
                        this.cancel();
                    }
                }
            }).runTask();
            return true;
        }
        return false;
    }

    public Wish getMatchingWish(String wish, Player p) {
        List<Wish> matches = new ArrayList<>(wishes);

        String parsedWish = removeCommonWords(wish);
        matches.sort(new WishMatchComparator(parsedWish));

        sendDebugMessages(wish, parsedWish, matches);

        Wish match = matches.get(0);
        String bestMatch = match.getBestMatch(parsedWish);

        // Places UUID of target to the storage of the wish.
        if (match instanceof PlayerSpecificWish) {
            PlayerSpecificWish pSpecMatch = (PlayerSpecificWish) match;
            if (p != null) {
                pSpecMatch.placeInStore(p.getUniqueId(), pSpecMatch.getUUIDForPlayerInMatch(match.getAliases(), bestMatch));
            }
        }

        double percentage = match.getRelativeDiffPercentage(bestMatch, parsedWish); // Lower is better
        if (percentage > 0.6) {
            return null;
        }
        return match;
    }

    private void sendDebugMessages(String wish, String parsedWish, List<Wish> matches) {
        Log.debug("Wish: " + wish + " | Parsed: " + parsedWish + " | Top 5:");
        int i = 0;
        for (Wish match : matches) {
            if (i > 4) {
                break;
            }
            String bestMatch = match.getBestMatch(parsedWish);
            Log.debug(bestMatch + ": " + match.getRelativeDiffPercentage(bestMatch, parsedWish));
            i++;
        }
    }

    public String removeCommonWords(String wish) {
        String[] commonWords = new String[]{
                "i", "you", "him", "her", "a", "the", "had", "wish", "get", "set",
                "be", "of", "and", "in", "that", "have", "it", "for", "as", "do",
                "from", "an", "this", "but", "his", "by", "they", "we", "say", "her", "or",
                "will", "my", "all", "would", "their", "there", "what", "so", "up", "out", "if",
                "about", "who", "which", "go", "me", "when", "make", "can", "like", "time", "no",
                "just", "him", "know", "take", "people", "into", "year", "your", "good", "some",
                "could", "them", "see", "other", "than", "then", "now", "look", "only", /*"come",*/
                "its", "over", "think", "also", "back", "after", "use", "our", "work", "first",
                "well", "way", "even", "new", "want", "because", "any", "these", "give", "was",
                "most", "us"};
        String parsed = wish;
        for (String word : commonWords) {
            parsed = parsed.replace(" " + word + " ", " ");
            // Remove if first word
            if (parsed.charAt(0) == word.charAt(0)) {
                parsed = parsed.replace(word + " ", " ");
            }
            // Remove if last word
            if (parsed.charAt(parsed.length() - 1) == word.charAt(word.length() - 1)) {
                parsed = parsed.replace(" " + word, " ");
            }
        }
        return parsed.trim();
    }
}

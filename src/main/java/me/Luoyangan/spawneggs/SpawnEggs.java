package me.Luoyangan.spawneggs;

import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;

public class SpawnEggs extends JavaPlugin implements SlimefunAddon {

    @Override
    public void onEnable() {
        // 从 config.yml 中读取插件配置
        Config cfg = new Config(this);

        if (cfg.getBoolean("options.auto-update")) {
            // 你可以在这里添加自动更新功能
        }

        getLogger().info("====================================");
        getLogger().info("刷怪蛋科技");
        getLogger().info("版本: " + getDescription().getVersion());
        getLogger().info("作者: Luoyangan");
        getLogger().info("QQ群: 812500721");
        getLogger().info("====================================");

        // 检测 Slimefun
        if (getServer().getPluginManager().getPlugin("Slimefun") == null) {
            getLogger().severe("错误: Slimefun 未找到! 插件将被禁用.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        } else {
            org.bukkit.plugin.Plugin slimefunPlugin = getServer().getPluginManager().getPlugin("Slimefun");
            getLogger().info("✓ Slimefun 插件已加载 (版本: " + slimefunPlugin.getDescription().getVersion() + ")");
        }

        // 检测 SoulJars (可选)
        if (getServer().getPluginManager().getPlugin("SoulJars") == null) {
            getLogger().severe("警告: SoulJars 未找到! 部分功能不可用.");
        } else {
            org.bukkit.plugin.Plugin soulJarsPlugin = getServer().getPluginManager().getPlugin("SoulJars");
            getLogger().info("✓ SoulJars 插件已加载 (版本: " + soulJarsPlugin.getDescription().getVersion() + ")");
        }

        /*
         * 1. 创建一级分类
         * 分类的显示物品将使用以下物品
         */
        ItemStack itemGroupItem = new CustomItemStack(Material.GHAST_SPAWN_EGG, "&f刷怪蛋科技");

        // 给你的分类提供一个独一无二的ID
        NamespacedKey itemGroupId = new NamespacedKey(this, "spawneggs");
        ItemGroup itemGroup = new ItemGroup(itemGroupId, itemGroupItem);

        /*
         * 2. 创建一个 SlimefunItemStack
         * 这个类是 ItemStack 的扩展，拥有多个构造函数
         * 重要：每个物品都得有一个独一无二的ID
         */
        SlimefunItemStack slimefunItem = new SlimefunItemStack(
                "SE_LY_DIAMOND",
                Material.EGG,
                "&f空白蛋",
                "&7将生物的灵魂压入即可使用", "&7你可千万被砸了哦"
        );

        /*
         * 3. 创建配方
         * 这个配方是一个拥有9个ItemStack的数组。
         * 它代表了一个3x3的有序合成配方。
         * 该配方所需的机器将在后面通过RecipeType指定。
         */
        ItemStack[] recipe = {
                null, SlimefunItems.SYNTHETIC_SHULKER_SHELL, null,
                SlimefunItems.MAGICAL_GLASS, SlimefunItems.ESSENCE_OF_AFTERLIFE, SlimefunItems.MAGICAL_GLASS,
                null, null, null
        };

        /*
         * 4. 注册物品到一级分类
         * 现在，你只需要注册物品
         * RecipeType.ENHANCED_CRAFTING_TABLE 代表
         * 该物品将在增强型工作台中合成。
         * 来自粘液科技本体的配方类型将会自动将配方添加到对应的机器中。
         */
        SlimefunItem item = new SlimefunItem(itemGroup, slimefunItem, RecipeType.ANCIENT_ALTAR, recipe);
        item.register(this);
    }

    @Override
    public void onDisable() {
        // 禁用插件的逻辑...
    }

    @Override
    public String getBugTrackerURL() {
        // 你可以在这里返回你的问题追踪器的网址，而不是 null
        return null;
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        /*
         * 你需要返回对你插件的引用。
         * 如果这是你插件的主类，只需要返回 "this" 即可。
         */
        return this;
    }

}

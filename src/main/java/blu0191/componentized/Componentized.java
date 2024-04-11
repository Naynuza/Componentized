package blu0191.componentized;

import blu0191.componentized.block.ComposableBlock;
import blu0191.componentized.block.TestComposableBlock;
import blu0191.componentized.block.CraftingTableComposableBlock;
import blu0191.componentized.block.VirusComposableBlock;
import blu0191.componentized.entity.ComposableBlockEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class Componentized implements ModInitializer {
    public static final String NAMESPACE = "componentized";

    public static final TestComposableBlock TEST_COMPOSABLE_BLOCK = new TestComposableBlock(FabricBlockSettings.create().strength(4.0f).nonOpaque());
    public static final CraftingTableComposableBlock CRAFTING_TABLE_COMPOSABLE_BLOCK = new CraftingTableComposableBlock(FabricBlockSettings.create().strength(1.0f).nonOpaque());
    public static final VirusComposableBlock VIRUS_COMPOSABLE_BLOCK = new VirusComposableBlock(FabricBlockSettings.create().strength(0.5f).nonOpaque());

    public static final ComposableBlock COMPOSABLE_BLOCK = new ComposableBlock(FabricBlockSettings.create().strength(4.0f));
    public static BlockEntityType<ComposableBlockEntity> COMPOSABLE_BLOCK_ENTITY;

    @Override
    public void onInitialize() {
        Registry.register(Registries.BLOCK, identifier("test_composable_block"), TEST_COMPOSABLE_BLOCK);
        Registry.register(Registries.ITEM, identifier("test_composable_block"), new BlockItem(TEST_COMPOSABLE_BLOCK, new Item.Settings()));
        Registry.register(Registries.BLOCK, identifier("crafting_table_composable_block"), CRAFTING_TABLE_COMPOSABLE_BLOCK);
        Registry.register(Registries.ITEM, identifier("crafting_table_composable_block"), new BlockItem(CRAFTING_TABLE_COMPOSABLE_BLOCK, new Item.Settings()));
        Registry.register(Registries.BLOCK, identifier("virus_composable_block"), VIRUS_COMPOSABLE_BLOCK);
        Registry.register(Registries.ITEM, identifier("virus_composable_block"), new BlockItem(VIRUS_COMPOSABLE_BLOCK, new Item.Settings()));

        Registry.register(Registries.BLOCK, identifier("composable_block"), COMPOSABLE_BLOCK);

        COMPOSABLE_BLOCK_ENTITY = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                identifier("composable_block_entity"),
                FabricBlockEntityTypeBuilder.create(ComposableBlockEntity::new, COMPOSABLE_BLOCK)
                        .addBlocks(TEST_COMPOSABLE_BLOCK, CRAFTING_TABLE_COMPOSABLE_BLOCK, VIRUS_COMPOSABLE_BLOCK)
                        .build()
        );
    }

    public Identifier identifier(@NotNull String path) {
        return new Identifier(NAMESPACE, path);
    }
}

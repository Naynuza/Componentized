package blu0191.componentized.entity;

import blu0191.componentized.Componentized;
import blu0191.componentized.block.TestComposableBlock;
import blu0191.componentized.component.Component;
import blu0191.componentized.component.CraftingTableComponent;
import blu0191.componentized.component.TestComponent;
import blu0191.componentized.generic.ExtendsList;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.math.BlockPos;

import java.util.Objects;

public class ComposableBlockEntity extends BlockEntity {
    public ExtendsList<Component> components;

    public ComposableBlockEntity(BlockPos pos, BlockState state) {
        super(Componentized.COMPOSABLE_BLOCK_ENTITY, pos, state);
        components = new ExtendsList<>();
    }

    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);

        var nbtList = nbt.getList("Components", NbtCompound.COMPOUND_TYPE);

        for(int i = 0; i < nbtList.size(); ++i) {
            NbtCompound componentCompound = nbtList.getCompound(i);
            var componentName = componentCompound.getString("ComponentName");
            NbtCompound componentDataCompound = componentCompound.getCompound("ComponentData");
            // TODO: use registry
            if (Objects.equals(componentName, "CraftingTable")) {
                var component = new CraftingTableComponent();
                component.readNbt(componentDataCompound);
                components.add(component);
            }
            if (Objects.equals(componentName, "Test")) {
                var component = new TestComponent();
                component.readNbt(componentDataCompound);
                components.add(component);
            }
        }
    }

    protected void writeNbt(NbtCompound nbt) {
        var nbtList = new NbtList();

        for (Component component : components) {
            NbtCompound componentCompound = new NbtCompound();
            componentCompound.putString("ComponentName", component.getName());
            NbtCompound componentDataCompound = new NbtCompound();
            componentCompound.put("ComponentData", componentDataCompound);
            component.writeNbt(componentDataCompound);
            nbtList.add(componentCompound);
        }

        if (!nbtList.isEmpty()) {
            nbt.put("Components", nbtList);
        }

        super.writeNbt(nbt);
    }
}

package blu0191.componentized.block;

import blu0191.componentized.component.CraftingTableComponent;

public class CraftingTableComposableBlock extends ComposableBlock {
    public CraftingTableComposableBlock(Settings settings) {
        super(settings);
        components.add(new CraftingTableComponent());
    }
}

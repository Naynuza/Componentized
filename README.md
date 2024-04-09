A WIP Minecraft 1.20.4 Fabric mod that allows for building new objects with composition of components rather than inheritance, similar to how game engines like Unity or Godot work.

For example, making a crafting table is as simple as adding a CraftingTableComponent to a ComposableBlock:

```java
public class CraftingTableComposableBlock extends ComposableBlock {
    public CraftingTableComposableBlock(Settings settings) {
        super(settings);
        components.add(new CraftingTableComponent());
    }
}
```

More components and composables to come.

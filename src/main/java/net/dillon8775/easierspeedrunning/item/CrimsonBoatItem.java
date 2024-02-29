package net.dillon8775.easierspeedrunning.item;

import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.BoatItem;
import net.minecraft.item.ItemGroup;

/**
 * The crimson boat item, a boat that can be ridden in lava.
 */
public class CrimsonBoatItem extends BoatItem {

    public CrimsonBoatItem(BoatEntity.Type type, Settings settings) {
        super(type, settings.maxCount(1).fireproof().group(ItemGroup.TRANSPORTATION));
    }
}
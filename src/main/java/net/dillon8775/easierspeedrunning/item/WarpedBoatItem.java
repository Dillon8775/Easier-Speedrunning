package net.dillon8775.easierspeedrunning.item;

import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.BoatItem;
import net.minecraft.item.ItemGroup;

/**
 * The warped boat item, a boat that can be ridden in lava.
 */
public class WarpedBoatItem extends BoatItem {

    public WarpedBoatItem(BoatEntity.Type type, Settings settings) {
        super(type, settings.maxCount(1).fireproof().group(ItemGroup.TRANSPORTATION));
    }
}
package de.labymod.lennart.group;

import de.labymod.lennart.LabyGroup;
import net.labymod.api.events.RenderEntityEvent;
import net.minecraft.entity.Entity;

public class GroupOnRender implements RenderEntityEvent {
    private int ticker;
    @Override
    public void onRender(Entity entity, double v, double v1, double v2, float v3) {
        ticker++;
        if (ticker % 10 == 0) return;
        if (LabyGroup.getInstance().getCachedVIP().isEmpty()) return;
        if (LabyGroup.getInstance().getCachedVIP().containsKey(entity.getUniqueID())) {
            if (!LabyGroup.getInstance().getCachedVIP().get(entity.getUniqueID())) {
                LabyGroup.getInstance().getCachedVIP().replace(entity.getUniqueID(), true);
                LabyGroup.getInstance().getGroupManager().setGroup(entity.getUniqueID());
            }
        }
    }
}
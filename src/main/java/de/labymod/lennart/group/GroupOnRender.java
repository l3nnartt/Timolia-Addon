package de.labymod.lennart.group;

import de.labymod.lennart.TimoliaAddon;
import net.labymod.api.events.RenderEntityEvent;
import net.minecraft.entity.Entity;

public class GroupOnRender implements RenderEntityEvent {

    private int ticker;

    @Override
    public void onRender(Entity entity, double v, double v1, double v2, float v3) {

        ticker++;
        if (ticker % 10 == 0) return;

        if (TimoliaAddon.getInstance().getCachedTimoliaTeam().isEmpty()) return;
        if (TimoliaAddon.getInstance().getCachedTimoliaTeam().containsKey(entity.getUniqueID())) {
            if (!TimoliaAddon.getInstance().getCachedTimoliaTeam().get(entity.getUniqueID())) {
                TimoliaAddon.getInstance().getCachedTimoliaTeam().replace(entity.getUniqueID(), true);
                TimoliaAddon.getInstance().getGroupManager().setGroup(entity.getUniqueID());
            }
        }
    }
}
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

        if (TimoliaAddon.getINSTANCE().getCachedTimoliaTeam().isEmpty()) return;
        if (TimoliaAddon.getINSTANCE().getCachedTimoliaTeam().containsKey(entity.getUniqueID())) {
            if (!TimoliaAddon.getINSTANCE().getCachedTimoliaTeam().get(entity.getUniqueID())) {
                TimoliaAddon.getINSTANCE().getCachedTimoliaTeam().replace(entity.getUniqueID(), true);
                TimoliaAddon.getINSTANCE().setGroup(entity.getUniqueID());
            }
        }

    }
}

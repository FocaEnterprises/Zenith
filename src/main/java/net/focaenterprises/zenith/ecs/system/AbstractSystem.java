package net.focaenterprises.zenith.ecs.system;

import net.focaenterprises.zenith.ecs.component.IComponent;
import net.focaenterprises.zenith.ecs.entity.Entity;
import net.focaenterprises.zenith.ecs.entity.IEntity;
import net.focaenterprises.zenith.world.World;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSystem implements ISystem {
    protected World world;
    protected List<Class<? extends IComponent>> components;

    protected AbstractSystem() {
        this.components = new ArrayList<>();
        this.registerComponents();
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public abstract void registerComponents();

    public <T extends IComponent> void registerComponent(Class<T> clazz) {
        components.add(clazz);
    }

    public void update() {
        for (int i = 0; i < world.getEntities().size(); i++) {
            Entity entity = world.getEntities().get(i);

            if (entity.getAllComponents().containsAll(components)) {
                process(entity);
            }
        }
    }

    public abstract void process(IEntity entity);

    public List<Class<? extends IComponent>> getComponents() {
        return components;
    }
}

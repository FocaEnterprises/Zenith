package net.focaenterprises.zenith.ecs.system;

import net.focaenterprises.zenith.ecs.component.IComponent;
import net.focaenterprises.zenith.ecs.entity.Entity;
import net.focaenterprises.zenith.ecs.entity.IEntity;
import net.focaenterprises.zenith.world.World;

import java.util.List;

public interface ISystem {
    void setWorld(World world);
    void registerComponents();
    void update();
    void process(IEntity entity);
    List<Class<? extends IComponent>> getComponents();
}

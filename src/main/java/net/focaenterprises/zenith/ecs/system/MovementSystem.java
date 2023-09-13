package net.focaenterprises.zenith.ecs.system;

import net.focaenterprises.zenith.ecs.component.TransformComponent;
import net.focaenterprises.zenith.ecs.component.VelocityComponent;
import net.focaenterprises.zenith.ecs.entity.IEntity;

public class MovementSystem extends AbstractSystem {
    @Override
    public void registerComponents() {
        registerComponent(TransformComponent.class);
        registerComponent(VelocityComponent.class);
    }

    @Override
    public void process(IEntity entity) {
        TransformComponent transform = (TransformComponent) entity.getComponent(TransformComponent.class);
        VelocityComponent velocity = (VelocityComponent) entity.getComponent(VelocityComponent.class);

        transform.x += velocity.directionX * velocity.speed;
        transform.y += velocity.directionY * velocity.speed;
    }
}

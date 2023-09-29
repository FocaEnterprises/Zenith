package net.focaenterprises.zenith.ecs.system;

import net.focaenterprises.zenith.ecs.component.RigidBody;
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
        RigidBody rigidBody = (RigidBody) entity.getComponent(RigidBody.class);

        if(rigidBody != null) {
            if (!rigidBody.isHorizontalColliding) {
                transform.x += velocity.directionX * velocity.speed;
            }

            if (!rigidBody.isVerticalColliding) {
                transform.y += velocity.directionY * velocity.speed;
            }
        } else {
            transform.x += velocity.directionX * velocity.speed;
            transform.y += velocity.directionY * velocity.speed;
        }
    }
}
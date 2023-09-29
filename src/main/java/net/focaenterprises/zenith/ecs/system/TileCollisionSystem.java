package net.focaenterprises.zenith.ecs.system;

import net.focaenterprises.zenith.ecs.component.RigidBody;
import net.focaenterprises.zenith.ecs.component.TransformComponent;
import net.focaenterprises.zenith.ecs.component.VelocityComponent;
import net.focaenterprises.zenith.ecs.entity.IEntity;
import net.focaenterprises.zenith.world.tilemap.TileMap;

public class TileCollisionSystem extends AbstractSystem {

    private TileMap tileMap;

    public TileCollisionSystem(TileMap tileMap) {
        this.tileMap = tileMap;
    }

    @Override
    public void registerComponents() {
        registerComponent(TransformComponent.class);
        registerComponent(RigidBody.class);
        registerComponent(VelocityComponent.class);
    }

    @Override
    public void process(IEntity entity) {
        TransformComponent transform = (TransformComponent) entity.getComponent(TransformComponent.class);
        RigidBody rigidBody = (RigidBody) entity.getComponent(RigidBody.class);
        VelocityComponent velocity = (VelocityComponent) entity.getComponent(VelocityComponent.class);

        int width = rigidBody.width;
        int height = rigidBody.height;
        double hspd = velocity.directionX * velocity.speed;
        double vspd = velocity.directionY * velocity.speed;

        rigidBody.isHorizontalColliding = false;
        rigidBody.isVerticalColliding = false;

        if (tileMap.isColliding((int) (transform.x + hspd), (int) transform.y, width, height)) {
            while(!tileMap.isColliding((int) (transform.x + Math.signum(hspd)), (int) transform.y, width, height)) {
                transform.x += Math.signum(hspd);
            }

            rigidBody.isHorizontalColliding = true;
        }

        if (tileMap.isColliding((int) (transform.x), (int) (transform.y + vspd), width, height)) {
            while(!tileMap.isColliding((int) transform.x, (int) (transform.y + Math.signum(vspd)), width, height)) {
                transform.y += Math.signum(vspd);
            }

            rigidBody.isVerticalColliding = true;
        }
    }
}


package net.focaenterprises.zenith.ecs.system;

import net.focaenterprises.zenith.ecs.component.SpriteComponent;
import net.focaenterprises.zenith.ecs.component.TransformComponent;
import net.focaenterprises.zenith.ecs.entity.IEntity;

public class SpriteRenderingSystem extends AbstractRenderingSystem {
    @Override
    public void registerComponents() {
        registerComponent(TransformComponent.class);
        registerComponent(SpriteComponent.class);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void process(IEntity entity) {
        SpriteComponent sprite = (SpriteComponent) entity.getComponent(SpriteComponent.class);
        TransformComponent transform = (TransformComponent) entity.getComponent(TransformComponent.class);

        sprite.getSprite().render(graphics, (int) transform.x, (int) transform.y, (int) transform.width, (int) transform.height);
    }
}

package net.focaenterprises.zenith.ecs.system;

import net.focaenterprises.zenith.ecs.component.SquareComponent;
import net.focaenterprises.zenith.ecs.component.TransformComponent;
import net.focaenterprises.zenith.ecs.entity.IEntity;
import net.focaenterprises.zenith.graphics.Renderer;

import java.awt.*;

public class SquareRenderingSystem extends AbstractRenderingSystem {
    public SquareRenderingSystem(Renderer renderer) {
        super(renderer);
    }

    @Override
    public void registerComponents() {
        registerComponent(TransformComponent.class);
        registerComponent(SquareComponent.class);
    }

    @Override
    public void process(IEntity entity) {
        TransformComponent transform = (TransformComponent) entity.getComponent(TransformComponent.class);
        SquareComponent square = (SquareComponent) entity.getComponent(SquareComponent.class);

        renderer.setDepth(square.getDepth());
        renderer.setColor(square.color);
        renderer.fillRect((int) transform.x, (int) transform.y, (int) transform.width, (int) transform.height);
    }
}

package net.focaenterprises.zenith.ecs.system;

import net.focaenterprises.zenith.ecs.component.ControlComponent;
import net.focaenterprises.zenith.ecs.component.KeyBindingComponent;
import net.focaenterprises.zenith.ecs.component.VelocityComponent;
import net.focaenterprises.zenith.ecs.entity.IEntity;

public class ControlSystem extends AbstractSystem {
    @Override
    public void registerComponents() {
        registerComponent(KeyBindingComponent.class);
        registerComponent(ControlComponent.class);
        registerComponent(VelocityComponent.class);
    }

    @Override
    public void process(IEntity entity) {
        ControlComponent control = (ControlComponent) entity.getComponent(ControlComponent.class);
        VelocityComponent velocity = (VelocityComponent) entity.getComponent(VelocityComponent.class);

        velocity.directionY = (control.down ? 1 : 0) - (control.up ? 1 : 0);
        velocity.directionX = (control.right ? 1 : 0) - (control.left ? 1 : 0);
    }
}

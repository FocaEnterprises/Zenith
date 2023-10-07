package net.focaenterprises.zenith.ecs.system;

import net.focaenterprises.zenith.ecs.component.BodyComponent;
import net.focaenterprises.zenith.ecs.component.ControlComponent;
import net.focaenterprises.zenith.ecs.component.KeyBindingComponent;
import net.focaenterprises.zenith.ecs.entity.IEntity;

public class ControlSystem extends AbstractSystem {

  public double speed;

  public ControlSystem(double speed) {
    this.speed = speed;
  }

  @Override
  public void registerComponents() {
    registerComponent(BodyComponent.class);
    registerComponent(KeyBindingComponent.class);
    registerComponent(ControlComponent.class);
  }

  @Override
  public void process(IEntity entity) {
    BodyComponent body = (BodyComponent) entity.getComponent(BodyComponent.class);
    ControlComponent control = (ControlComponent) entity.getComponent(ControlComponent.class);

    body.horizontalSpeed = ((control.right ? 1 : 0) - (control.left ? 1 : 0)) * speed;
    body.verticalSpeed = ((control.down ? 1 : 0) - (control.up ? 1 : 0)) * speed;
  }
}

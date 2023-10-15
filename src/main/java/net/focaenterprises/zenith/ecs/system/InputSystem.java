package net.focaenterprises.zenith.ecs.system;

import net.focaenterprises.zenith.ecs.component.ControlComponent;
import net.focaenterprises.zenith.ecs.component.KeyBindingComponent;
import net.focaenterprises.zenith.ecs.entity.IEntity;
import net.focaenterprises.zenith.game.IGameContext;
import net.focaenterprises.zenith.input.Keyboard;

public class InputSystem extends AbstractSystem {

  private final Keyboard keyboard;

  public InputSystem(Keyboard keyboard) {
    this.keyboard = keyboard;
  }

  public InputSystem(IGameContext context) {
    this(context.geKeyboard());
  }

  @Override
  public void registerComponents() {
    registerComponent(ControlComponent.class);
    registerComponent(KeyBindingComponent.class);
  }

  @Override
  public void process(IEntity entity) {
    KeyBindingComponent keyBinding = (KeyBindingComponent) entity.getComponent(KeyBindingComponent.class);
    ControlComponent control = (ControlComponent) entity.getComponent(ControlComponent.class);

    control.up = keyboard.keyboardCheck(keyBinding.upKey);
    control.down = keyboard.keyboardCheck(keyBinding.downKey);
    control.right = keyboard.keyboardCheck(keyBinding.rightKey);
    control.left = keyboard.keyboardCheck(keyBinding.leftKey);
  }
}
package net.focaenterprises.zenith.ecs.entity;

import net.focaenterprises.zenith.ecs.component.IComponent;

import java.util.UUID;

public interface IEntity {
  UUID getUUID();

  String getName();

  IEntity attach(IComponent component);

  <T extends IComponent> IComponent getComponent(Class<T> component);

  <T extends IComponent> IComponent removeComponent(Class<T> component);
}

package net.focaenterprises.zenith.ecs.entity;

import net.focaenterprises.zenith.ecs.component.IComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Entity implements IEntity {
    private UUID uuid;
    private String name;
    private List<IComponent> components;

    public Entity(String name) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.components = new ArrayList<>();
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public IEntity attach(IComponent component) {
        components.add(component);

        return this;
    }

    @Override
    public <T extends IComponent> IComponent getComponent(Class<T> componentClass) {
        for (IComponent c : components) {
            if (componentClass.isAssignableFrom(c.getClass())) {
                try {
                    return componentClass.cast(c);
                } catch (ClassCastException e) {
                    e.printStackTrace();
                    assert false : "Error: Casting Component";
                }
            }
        }

        return null;
    }

    @Override
    public <T extends IComponent> IComponent removeComponent(Class<T> component) {
        return null;
    }

    public List<Class<? extends IComponent>> getAllComponents() {
        return components.stream().map(IComponent::getClass).collect(Collectors.toList());
    }
}

package net.focaenterprises.zenith.ecs.system;


import net.focaenterprises.zenith.ecs.component.IComponent;
import net.focaenterprises.zenith.ecs.component.IRenderingComponent;
import net.focaenterprises.zenith.ecs.component.SpriteComponent;
import net.focaenterprises.zenith.ecs.entity.Entity;
import net.focaenterprises.zenith.graphics.Renderer;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public abstract class AbstractRenderingSystem extends AbstractSystem {
    protected Renderer renderer;

    public AbstractRenderingSystem(Renderer renderer) {
        this.renderer = renderer;
    }
}

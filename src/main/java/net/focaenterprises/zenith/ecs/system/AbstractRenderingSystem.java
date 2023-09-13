package net.focaenterprises.zenith.ecs.system;


import net.focaenterprises.zenith.ecs.component.IComponent;
import net.focaenterprises.zenith.ecs.component.IRenderingComponent;
import net.focaenterprises.zenith.ecs.component.SpriteComponent;
import net.focaenterprises.zenith.ecs.entity.Entity;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public abstract class AbstractRenderingSystem extends AbstractSystem {
    protected Graphics graphics;

    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }
}

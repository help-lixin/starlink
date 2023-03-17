package help.lixin.core.plane.impl;

import help.lixin.core.plane.ElementPlane;

public abstract class AbstractElementPlane implements ElementPlane {

    protected int x = 0;
    protected int y = 0;

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getY() {
        return y;
    }
}

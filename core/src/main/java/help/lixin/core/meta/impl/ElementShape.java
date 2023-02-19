package help.lixin.core.meta.impl;

/**
 * 具有x和y,同时,还具备有width和height
 */
public class ElementShape extends AbstractElementPlane {
    protected int width;
    protected int height;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

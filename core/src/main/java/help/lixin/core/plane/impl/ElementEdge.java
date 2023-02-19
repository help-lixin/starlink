package help.lixin.core.plane.impl;

import help.lixin.core.plane.Plane;

/**
 * 定义线,所以,需要:startPoint(x/y)和endPoint(x/y)
 */
public class ElementEdge implements Plane {
    private DefaultElementPlane startPoint = new DefaultElementPlane();
    private DefaultElementPlane endPoint = new DefaultElementPlane();

    public DefaultElementPlane getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(DefaultElementPlane startPoint) {
        this.startPoint = startPoint;
    }

    public DefaultElementPlane getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(DefaultElementPlane endPoint) {
        this.endPoint = endPoint;
    }
}

package co.edu.uptc.pojo;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Plane {
    private Double angle = 0.0;
    private List<Point> path;

    public Plane() {
        this.path = new ArrayList<Point>();
    }

    public void addPoint(Point point) {
        path.add(point);
    }

    public Double getAngle() {
        return angle;
    }

    public void setAngle(Double angle) {
        this.angle = angle;
    }

    public List<Point> getPath() {
        return path;
    }

    public void setPath(List<Point> path) {
        this.path = path;
    }

    public Point getPosition() {
        return path.get(0);
    }
}
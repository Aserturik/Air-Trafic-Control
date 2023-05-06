package co.edu.uptc.pojo;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Plane {
    private Double angle = 0.0;
    private List<Point> path;
    private boolean newPlane;
    private Point position;
    private Point nextPosition;

    public Plane() {
        this.path = new ArrayList<Point>();
        this.newPlane = true;
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

    public boolean isNewPlane() {
        return newPlane;
    }

    public void setNewPlane(boolean newPlane) {
        this.newPlane = newPlane;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Point getNextPosition() {
        return nextPosition;
    }

    public void setNextPosition(Point nextPosition) {
        this.nextPosition = nextPosition;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "angle=" + angle +
                ", path=" + path +
                ", position=" + getPosition() +
                '}';
    }
}
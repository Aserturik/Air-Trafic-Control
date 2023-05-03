package co.edu.uptc.pojo;

import co.edu.uptc.view.globals.ValuesGlobals;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class Plane {
    private ImageIcon imagePlane;
    private boolean isNewPlane = true;
    private Point position;
    private Point endPoint;
    private List<Point> path;
    private boolean isFollowingPath = false;
    private static final int SPEED = 5;
    private Graphics2D g2d;

    public Plane(ImageIcon imagePlane, int oriX, int oriY) {
        this.imagePlane = imagePlane;
        this.position = new Point(oriX, oriY);
        this.path = new ArrayList<>();
        endPoint = new Point(0, 0);
        //this.centerPlane = new Point(position.x + imagePlane.getIconWidth() / 2, position.y + imagePlane.getIconHeight() / 2);
    }

    public void addPointToPath(Point point) {
        path.add(point);
    }

    public void draw(Graphics2D g) {
        this.g2d = g;
        if (position != null) {
            drawImage();
            g.setColor(Color.RED);
            g.fillOval(position.x, position.y, 5, 5);
            g.setColor(Color.white);
            drawPath();
            advance();
        }
    }

    private void drawPath() {
        for (Point point : path) {
            g2d.setColor(Color.RED);
            g2d.setStroke(new BasicStroke(3));
            g2d.drawLine(point.x, point.y, point.x, point.y);
        }
    }

    private void setNewPlanePosition(){
        if (position.y >= ValuesGlobals.HEIGHT_FRAME) {
            endPoint.x = ValuesGlobals.WIDTH_FRAME - position.x;
            endPoint.y = 0;
        } else if (position.y <= 0) {
            endPoint.x = ValuesGlobals.WIDTH_FRAME - position.x;
            endPoint.y = ValuesGlobals.HEIGHT_FRAME;
        } else if (position.x >= ValuesGlobals.WIDTH_FRAME) {
            endPoint.x = 0;
            endPoint.y = ValuesGlobals.HEIGHT_FRAME - position.y;
        } else if (position.x <= 0) {
            endPoint.x = ValuesGlobals.WIDTH_FRAME;
            endPoint.y = ValuesGlobals.HEIGHT_FRAME - position.y;
        }

        isNewPlane = false;
    }

    private void setRandomPlanePosition(){
        if (position.y >= ValuesGlobals.HEIGHT_FRAME) {
            endPoint.x = (int) (Math.random() * ValuesGlobals.WIDTH_FRAME);
            endPoint.y = 0;
        } else if (position.y <= 0) {
            endPoint.x = (int) (Math.random() * ValuesGlobals.WIDTH_FRAME);
            endPoint.y = ValuesGlobals.HEIGHT_FRAME;
        } else if (position.x >= ValuesGlobals.WIDTH_FRAME) {
            endPoint.x = 0;
            endPoint.y = (int) (Math.random() * ValuesGlobals.HEIGHT_FRAME);
        } else if (position.x <= 0) {
            endPoint.x = ValuesGlobals.WIDTH_FRAME;
            endPoint.y = (int) (Math.random() * ValuesGlobals.HEIGHT_FRAME);
        }
    }

    private void getEndPoint() {
        if (isNewPlane){
            setNewPlanePosition();
        }else {
            setRandomPlanePosition();
        }
    }

    private double getAngle() {
        getEndPoint();
        int x1 = endPoint.x;
        int y1 = endPoint.y;
        int x2 = position.x;
        int y2 = position.y;

        double angle = Math.atan2(y2 - y1, x2 - x1);
        //System.out.println("El angulo es: " + Math.toDegrees(angle));

        return Math.toDegrees(angle);
    }

    private double getDistanceTo(Point point) {
        double dx = point.x - position.x;
        double dy = point.y - position.y;

        return Math.sqrt(dx * dx + dy * dy);
    }

    private void drawImage() {
        double rotationRequired = Math.toRadians(getAngle());
        AffineTransform tx = g2d.getTransform();

        int imageWidth = imagePlane.getIconWidth();
        int imageHeight = imagePlane.getIconHeight();
        int drawX = position.x - imageWidth / 2;
        int drawY = position.y - imageHeight / 2;

        g2d.rotate(rotationRequired, position.x, position.y);
        g2d.drawImage(imagePlane.getImage(), drawX, drawY, null);
        g2d.setTransform(tx);
    }

    private Point getCenterPanel() {
        int x = ValuesGlobals.getCenterFrame().x -= imagePlane.getIconWidth() / 2;
        int y = ValuesGlobals.getCenterFrame().y -= imagePlane.getIconHeight() / 2;
        return new Point(x, y);
    }

    public void moveToCenter() {
        double distance = getDistanceTo(endPoint);
        double dx = endPoint.x - position.x;
        double dy = endPoint.y - position.y;

        if (distance <= SPEED) {
            position.setLocation(endPoint);
            //centerPlane.setLocation(centerPanel.x + imagePlane.getIconWidth() / 2, centerPanel.y + imagePlane.getIconHeight() / 2);
        } else {
            double angle = Math.atan2(dy, dx);
            int deltaX = (int) Math.round(SPEED * Math.cos(angle));
            int deltaY = (int) Math.round(SPEED * Math.sin(angle));

            position.x += deltaX;
            //centerPlane.x += deltaX;
            position.y += deltaY;
            //centerPlane.y += deltaY;
        }
    }

    public void advance() {
        if (!isFollowingPath) {
            moveToCenter();
        } else {
            double radians = Math.toRadians(getAngle());
            int dx = (int) Math.round(SPEED * Math.sin(radians));
            int dy = (int) Math.round(-SPEED * Math.cos(radians));
            position.translate(dx, dy);
        }
    }

    public void followPath() {

    }


    public boolean checkBounds(Rectangle bounds) {
        int x = position.x;
        int y = position.y;
        int imgWidth = imagePlane.getIconWidth();
        int imgHeight = imagePlane.getIconHeight();
        return bounds.contains(x, y, imgWidth, imgHeight);
    }

    public boolean isColliding(Point point) {
        int x = point.x;
        int y = point.y;
        int imgWidth = imagePlane.getIconWidth();
        int imgHeight = imagePlane.getIconHeight();
        return x >= position.x && x <= position.x + imgWidth && y >= position.y && y <= position.y + imgHeight;
    }

    public boolean isFollowingPath() {
        return isFollowingPath;
    }

    public void setFollowingPath(boolean followingPath) {
        this.isFollowingPath = followingPath;
    }

    public void setPath(List<Point> temporalPath) {
        this.path = temporalPath;
    }

    public void clearPath() {
        path.clear();
    }

    public ImageIcon getImagePlane() {
        return imagePlane;
    }

    public void setImagePlane(ImageIcon imagePlane) {
        this.imagePlane = imagePlane;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public List<Point> getPath() {
        return path;
    }

    public void setPath(ArrayList<Point> path) {
        this.path = path;
    }
}
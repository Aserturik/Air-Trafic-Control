package co.edu.uptc.model;

import co.edu.uptc.pojo.Plane;
import co.edu.uptc.presenter.Contract;
import co.edu.uptc.view.globals.ValuesGlobals;
import util.UtilImages;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OperationPlanes {
    private List<Plane> planes;
    private JLabel imageLabel;
    private boolean isNewPlane = true;
    private Point position;
    private Point endPoint;
    private boolean isFollowingPath = false;
    private static final int SPEED = 5;
    private Contract.Model model;
    private boolean isStartGame = false;
    private boolean isPauseGame = false;
    private boolean isStopGame = false;
    private LocalDate dateStartGame;
    private LocalDate datePauseGame;
    private LocalDate dateStopGame;

    public OperationPlanes(Contract.Model model) {
        this.model = model;
        planes = new ArrayList<>();
        imageLabel = new JLabel();
        position = new Point(50, 50);
        endPoint = new Point(200, 200);
    }

    public List<Plane> getPlanes() {
        planes = new java.util.ArrayList<>();
        planes.add(new Plane());
        planes.get(0).addPoint(position);
        planes.get(0).setAngle(225.0);
        return planes;
    }

    private Plane createLocalPlane() {
        Plane newPlane = new Plane();
        randomPositionGenerator(newPlane);
        planes.add(newPlane);
        return newPlane;
    }

    public void addPointToPath(Plane plane, Point point) {
        plane.addPoint(point);
    }


    public void startGame() {
        isStartGame = true;
        dateStartGame = LocalDate.now();
        //createLocalPlane();
        moveToCenter();
        startThread();
    }

    private void startThread() {
        Thread thread = new Thread(() -> {
            while (isStartGame) {
                advance();
                model.setPlanes(planes);
                try {
                    Thread.sleep(ValuesGlobals.TIME_SLEEP);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private void randomPositionGenerator(Plane plane) {
        Random random = new Random();
        switch (random.nextInt(4 - 1 + 1) + 1) {
            case 1:
                plane.addPoint(new Point(0, random.nextInt(550 - 10 + 1) + 10));
                break;
            case 2:
                plane.addPoint(new Point(1010, random.nextInt(550 - 10 + 1) + 10));
                break;
            case 3:
                plane.addPoint(new Point(random.nextInt(850 - 10 + 1) + 10, 20));
                break;
            case 4:
                plane.addPoint(new Point(random.nextInt(850 - 10 + 1) + 10, 500));
                break;
        }
    }

    public void moveToCenter() {
        double distance = getDistanceTo(endPoint);
        System.out.println("la distancia es: " + distance);
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
        int imgWidth = 40;
        int imgHeight = 40;
        return bounds.contains(x, y, imgWidth, imgHeight);
    }


    private void setRandomPlanePosition() {
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
        if (isNewPlane) {
            setNewPlanePosition();
        } else {
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

    private Point getCenterPanel() {
        int x = ValuesGlobals.getCenterFrame().x -= 20 / 2;
        int y = ValuesGlobals.getCenterFrame().y -= 20 / 2;
        return new Point(x, y);
    }

    private void setNewPlanePosition() {
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

    public ImageIcon getPlaneImage() {
        UtilImages utilImages = new UtilImages();
        imageLabel = new JLabel();
        imageLabel.setBounds(10, 10, 40, 40);
        Icon img = utilImages.loadScaleImage(ValuesGlobals.PHAT_PLANE_IMAGE_ORIGINAL, imageLabel.getWidth(), imageLabel.getHeight());
        imageLabel.setIcon(img);
        return new ImageIcon(((ImageIcon) img).getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_DEFAULT));
    }

    public void isSelectedPlane(Point point) {
        if (checkBounds(new Rectangle(point.x, point.y, 40, 40))) {
            System.out.println("Seleccionado");
        }
    }

    private Rectangle getAreaPosition(Point position) {
        int x = position.x;
        int y = position.y;
        int width = 40;
        int height = 40;

        return new Rectangle(x, y, width, height);
    }

    public Plane getPlaneSelected(Point point) {
        Rectangle planeArea;
        for (Plane plane : planes) {
            planeArea = getAreaPosition(plane.getPosition());
            if (planeArea.contains(point)) {
                return plane;
            }
        }
        return null;
    }
}

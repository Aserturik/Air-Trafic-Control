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
    private static final int SPEED = 5;
    private Contract.Model model;
    private boolean isStartGame = false;
    private boolean isPauseGame = false;
    private boolean isStopGame = false;
    private LocalDate dateStartGame;
    private LocalDate datePauseGame;
    private LocalDate dateStopGame;
    private Object lock;

    public OperationPlanes(Contract.Model model) {
        lock = new Object();
        this.model = model;
        planes = new ArrayList<>();
    }

    public List<Plane> getPlanes() {
        return planes;
    }

    public void addPointToPath(Plane plane, Point point) {
        plane.addPoint(point);
    }


    public void startGame() {
        isStartGame = true;
        dateStartGame = LocalDate.now();
        startThread();
        createPlanes();
    }

    private synchronized void startThread() {
        Thread thread = new Thread(() -> {
            while (isStartGame) {
                try {
                    synchronized (lock) {
                        model.setPlanes(planes);
                        advance();
                        lock.notifyAll();
                    }
                    Thread.sleep(ValuesGlobals.TIME_SLEEP);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private synchronized void createPlanes() {
        Thread addPlanes = new Thread(() -> {
            while (isStartGame) {
                randomPositionGenerator();
                try {
                    synchronized (lock) {
                        lock.wait();
                    }
                    Thread.sleep(ValuesGlobals.TIME_GENERATE_PLANE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        addPlanes.start();
    }

    private void randomPositionGenerator() {
        Plane plane = new Plane();
        addNewPlane(plane);
        plane.setNextPosition(new Point(450, 300));
        plane.setAngle(getAngle(plane));
        moveToRoute(plane);
    }

    private void addNewPlane(Plane plane) {
        Random random = new Random();
        switch (random.nextInt(4 - 1 + 1) + 1) {
            case 1:
                plane.addPoint(new Point(0, random.nextInt(600 - 10 + 1) + 10));
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
        planes.add(plane);
    }

    public void moveToRoute(Plane plane) {
        double distance = getDistanceTo(plane, plane.getNextPosition());
        System.out.println("la distancia es: " + distance);
        double dx = plane.getNextPosition().x - plane.getPosition().x;
        double dy = plane.getNextPosition().y - plane.getPosition().y;

        if (distance <= SPEED) {
            plane.getPosition().setLocation(plane.getNextPosition());
        } else {
            double angle = Math.atan2(dy, dx);
            int deltaX = (int) Math.round(SPEED * Math.cos(angle));
            int deltaY = (int) Math.round(SPEED * Math.sin(angle));

            plane.getPosition().x += deltaX;
            plane.getPosition().y += deltaY;
        }
    }

    public void advance() {
        for (Plane plane : planes) {
            if (plane.isNewPlane()) {
                moveToRoute(plane);
            } else {
                double radians = Math.toRadians(plane.getAngle());
                int dx = (int) Math.round(SPEED * Math.sin(radians));
                int dy = (int) Math.round(-SPEED * Math.cos(radians));
                plane.getPosition().translate(dx, dy);
            }
        }
    }

    public boolean checkBounds(Rectangle bounds) {
        int imgWidth = 40;
        int imgHeight = 40;
        for (Plane plane : planes) {
            int x = plane.getPosition().x;
            int y = plane.getPosition().y;
            if (bounds.contains(x, y, imgWidth, imgHeight)) {
                return true;
            }
        }
        return false;
    }

    private void getNextPosition(Plane plane) {
        if (plane.isNewPlane()) {
            setNextPlanePosition(plane);
        } else {
            // para cuando tiene que seguir el camino
        }
    }

    private double getAngle(Plane plane) {
        getNextPosition(plane);
        int x1 = plane.getNextPosition().x;
        int y1 = plane.getNextPosition().y;
        int x2 = plane.getPosition().x;
        int y2 = plane.getPosition().y;

        double angle = Math.atan2(y2 - y1, x2 - x1);

        return Math.toDegrees(angle);
    }

    private double getDistanceTo(Plane plane, Point point) {
        double dx = point.x - plane.getPosition().x;
        double dy = point.y - plane.getPosition().y;

        return Math.sqrt(dx * dx + dy * dy);
    }

    private Point getCenterPanel() {
        int x = ValuesGlobals.getCenterFrame().x -= 20 / 2;
        int y = ValuesGlobals.getCenterFrame().y -= 20 / 2;
        return new Point(x, y);
    }

    private void setNextPlanePosition(Plane plane) {
        if (plane.getPosition().y >= ValuesGlobals.HEIGHT_FRAME) {
            plane.getNextPosition().x = ValuesGlobals.WIDTH_FRAME - plane.getPosition().x;
            plane.getNextPosition().y = 0;
        } else if (plane.getPosition().y <= 0) {
            plane.getNextPosition().x = ValuesGlobals.WIDTH_FRAME - plane.getPosition().x;
            plane.getNextPosition().y = ValuesGlobals.HEIGHT_FRAME;
        } else if (plane.getPosition().x >= ValuesGlobals.WIDTH_FRAME) {
            plane.getNextPosition().x = 0;
            plane.getNextPosition().y = ValuesGlobals.HEIGHT_FRAME - plane.getPosition().y;
        } else if (plane.getPosition().x <= 0) {
            plane.getNextPosition().x = ValuesGlobals.WIDTH_FRAME;
            plane.getNextPosition().y = ValuesGlobals.HEIGHT_FRAME - plane.getPosition().y;
        }
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

package co.edu.uptc.model;

import co.edu.uptc.pojo.Plane;
import co.edu.uptc.presenter.Contract;
import co.edu.uptc.view.globals.ValuesGlobals;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OperationPlanes {
    private List<Plane> planes;
    private List<Point> temporalPath;
    private static final int SPEED = 5;
    private Contract.Model model;
    private Plane planeSelected;
    private boolean isStartGame = false;
    private boolean isPauseGame = false;
    private LocalDate dateStartGame;
    private LocalDate datePauseGame;
    private Object lock;

    public OperationPlanes(Contract.Model model) {
        lock = new Object();
        this.model = model;
        planes = new ArrayList<>();
        temporalPath = new ArrayList<>();
    }


    public void startGame() {
        isStartGame = true;
        dateStartGame = LocalDate.now();
        startThread();
        createPlanes();
        eliminatePlanes();
    }

    public void viewIsReady() {
        this.lock = true;
    }

    private synchronized void startThread() {
        Thread thread = new Thread(() -> {
            while (!isPauseGame) {
                try {
                    synchronized (lock) {
                        advance();
                        model.setPlanes(planes);
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
            while (!isPauseGame) {
                try {
                    synchronized (lock) {
                        randomPositionGenerator();
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

    private void eliminatePlanes() {
        Thread eliminatePlanes = new Thread(() -> {
            while (!isPauseGame) {
                try {
                    synchronized (lock) {
                        verifyPlanes();
                        lock.notifyAll();
                    }
                    Thread.sleep(ValuesGlobals.TIME_ELIMINATE_PLANE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        eliminatePlanes.start();
    }

    private void verifyPlanes() {
        for (Plane plane : planes) {
            if (getDistanceTo(plane, plane.getNextPosition()) == 0 && plane.getPosition().x == 0) {
                planes.remove(plane);
                break;
            } else if (getDistanceTo(plane, plane.getNextPosition()) == 0 && plane.getPosition().x == ValuesGlobals.WIDTH_FRAME) {
                planes.remove(plane);
                break;
            } else if (getDistanceTo(plane, plane.getNextPosition()) == 0 && plane.getPosition().y == 0) {
                planes.remove(plane);
                break;
            } else if (getDistanceTo(plane, plane.getNextPosition()) == 0 && plane.getPosition().y == ValuesGlobals.HEIGHT_FRAME) {
                planes.remove(plane);
                break;
            }
        }
    }

    private void randomPositionGenerator() {
        Plane plane = new Plane();
        addNewPlane(plane);
        plane.setAngle(getAngle(plane));
        moveToRoute(plane);
    }

    private Point getInversePosition(Plane plane) {
        Point position = plane.getPosition();
        Point endPoint = new Point();
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
        return endPoint;
    }

    private void addNewPlane(Plane plane) {
        Random random = new Random();
        switch (random.nextInt(4 - 1 + 1) + 1) {
            case 1:
                plane.addPoint(new Point(0, random.nextInt(ValuesGlobals.HEIGHT_FRAME - 10 + 1) + 10));
                break;
            case 2:
                plane.addPoint(new Point(1010, random.nextInt(ValuesGlobals.HEIGHT_FRAME - 10 + 1) + 10));
                break;
            case 3:
                plane.addPoint(new Point(random.nextInt(ValuesGlobals.WIDTH_FRAME - 10 + 1) + 10, 0));
                break;
            case 4:
                plane.addPoint(new Point(random.nextInt(ValuesGlobals.WIDTH_FRAME - 10 + 1) + 10, ValuesGlobals.HEIGHT_FRAME));
                break;
        }
        plane.setPosition(plane.getPath().get(0));
        plane.setNextPosition(getInversePosition(plane));
        planes.add(plane);
    }

    public void moveToRoute(Plane plane) {
        double distance = getDistanceTo(plane, plane.getNextPosition());
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
                if (planeSelected.getPath().size() == 0) {
                    System.out.println("No hay mas puntos");
                    setNewNextPlanePosition(planeSelected);
                    planeSelected.addPoint(planeSelected.getNextPosition());
                } else {
                    if(planeSelected.getPath().size() >= 2){
                        followTemporalPath();
                    }else {
                        planeSelected.setNewPlane(true);
                        planeSelected.setNextPosition(getInversePosition(planeSelected));
                        planeSelected.setAngle(getAngle(planeSelected));
                    }
                }
            }
        }
    }

    private void followTemporalPath(){
        planeSelected.setPosition(planeSelected.getPath().get(0));
        planeSelected.setNextPosition(planeSelected.getPath().get(1));
        planeSelected.setAngle(getAngle(planeSelected, planeSelected.getPath().get(1)));
        planeSelected.getPath().remove(0);
    }

    private void getNextPosition(Plane plane) {
        setNextPlanePosition(plane);
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

    private double getAngle(Plane plane, Point point) {
        int x1 = point.x;
        int y1 = point.y;
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

    private void setNextPlanePosition(Plane plane) {
        if (plane.isNewPlane()) {
            setNewNextPlanePosition(plane);
        } else {
            plane.getPath().remove(0);
            plane.setNextPosition(plane.getPath().get(0));
        }
    }

    private void setNewNextPlanePosition(Plane plane) {
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
        for (Plane plane : planes) {
            if (plane.getRectangle().contains(point)) {
                planeSelected = plane;
            }
        }
    }

    private List<Point> calculateIntermediePoints(List<Point> points) {
        List<Point> intermediatePoints = new ArrayList<>();
        if (points.size() < 2) {
            return intermediatePoints;
        }

        for (int i = 0; i < points.size() - 1; i++) {
            Point point1 = points.get(i);
            Point point2 = points.get(i + 1);
            double distance = point1.distance(point2);
            double numberOfPoints = distance / this.SPEED;
            double xIncrement = (point2.x - point1.x) / numberOfPoints;
            double yIncrement = (point2.y - point1.y) / numberOfPoints;
            for (int j = 1; j < numberOfPoints; j++) {
                int x = (int) (point1.x + j * xIncrement);
                int y = (int) (point1.y + j * yIncrement);
                intermediatePoints.add(new Point(x, y));
            }
        }
        return intermediatePoints;
    }

    public void addPointToPath(Point point) {
        if (planeSelected != null) {
            planeSelected.addPoint(point);
        }
    }

    public void pauseGame() {
        if (isPauseGame) {
            isPauseGame = false;
            returnGame();
        } else {
            isPauseGame = true;
        }
    }

    private void returnGame() {
        startThread();
        eliminatePlanes();
    }

    public void selectedPlaneNull() {
        planeSelected.setPath(calculateIntermediePoints(planeSelected.getPath()));
        planeSelected.setNewPlane(false);
    }
}

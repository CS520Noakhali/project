import java.io.IOException;

import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import java.util.ArrayList;
import javafx.animation.SequentialTransition;


public class SimulationAdapter implements FlightControllable{

    public SimulationAdapter(){
        
    }
    DashboardController dashboardController = DashboardController.getInstance();
    @Override
    public void takeoff() throws IOException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void land() throws IOException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void increaseAltitude(int up) throws IOException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void decreaseAltitude(int down) throws IOException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void flyForward(int front) throws IOException {
        
        
    }

    @Override
    public void flyLeft(int left) throws IOException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void flyRight(int right) throws IOException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void turnCW(int degrees) throws IOException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void turnCCW(int degrees) throws IOException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getFlightTime() throws IOException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getHeight() throws IOException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getAttitudePitch() throws IOException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getAttitudeRoll() throws IOException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getAttitudeYaw() throws IOException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getAccelerationX() throws IOException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getAccelerationY() throws IOException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getAccelerationZ() throws IOException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getTOF() throws IOException {
        // TODO Auto-generated method stub
        return 0;
    }
    @Override
    public void gotoXY(int x, int y, int speed) {
        //translate
        dashboardController.droneIC.getImageView().toFront();
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(dashboardController.droneIC.getImageView());
        translate.setDuration(Duration.millis(500));
        int deltaX = x - dashboardController.droneIC.getXcoordinate();
        int deltaY = y - dashboardController.droneIC.getYcoordinate();

        translate.setByX(deltaX);
        translate.setByY(deltaY);
        translate.play();


        // update drone's coordinates
        dashboardController.droneIC.setXcoordinate(x);
        dashboardController.droneIC.setYcoordinate(y);


        //---

        dashboardController.X_FINAL_COORD = 0;
        dashboardController.Y_FINAL_COORD = 0;


        int x1 = dashboardController.commCenterIC.getXcoordinate();
        int y1 = dashboardController.commCenterIC.getYcoordinate();
        int w1 = dashboardController.commCenterIC.getWidth();
        int h1 = dashboardController.commCenterIC.getHeight();
        x1 = (int) x1 + w1/2 - 30; //centering drone over the item
        y1 = (int) y1 + h1/2 - 30; //centering drone over item

        int deltaX1 = x1 - dashboardController.droneIC.getXcoordinate();
        int deltaY1 = y1 - dashboardController.droneIC.getYcoordinate();

        int[][] coordinates = {
            {deltaX, deltaY}, 
            {deltaX1, deltaY1},
        };

        SequentialTransition master = new SequentialTransition();

        ArrayList<TranslateTransition> lst1 = new ArrayList<>();

        dashboardController.droneIC.getImageView().toFront();
        TranslateTransition initiate = new TranslateTransition();
        initiate.setNode(dashboardController.droneIC.getImageView());
        initiate.setDuration(Duration.millis(900));

        
        TranslateTransition translate1 = new TranslateTransition();
        
        int[] arr = coordinates[0];
        int x2 = arr[0];
        int y2 = arr[1];
        
        translate1.setNode(dashboardController.droneIC.getImageView());
        translate1.setDuration((Duration.millis(1000)));
        translate1.setByX(x2);
        translate1.setByY(y2);
        
        dashboardController.X_FINAL_COORD += x2;
        dashboardController.Y_FINAL_COORD += y2;
        
        lst1.add(translate1);
        master.getChildren().add(translate1);

        PauseTransition p = new PauseTransition(Duration.millis(1000));
        master.getChildren().add(p);

        RotateTransition rt1 = new RotateTransition(Duration.millis(3000), dashboardController.droneIC.getImageView());
        rt1.setByAngle(360);
        rt1.setCycleCount(1);
        master.getChildren().add(rt1);

        PauseTransition p1 = new PauseTransition(Duration.millis(1000));
        master.getChildren().add(p1);
            

        TranslateTransition translate2 = new TranslateTransition();
        arr = coordinates[1];
        x2 = arr[0];
        y2 = arr[1];
        
        translate2.setNode(dashboardController.droneIC.getImageView());
        translate2.setDuration((Duration.millis(1000)));
        translate2.setByX(x2);
        translate2.setByY(y2);
        
        dashboardController.X_FINAL_COORD += x2;
        dashboardController.Y_FINAL_COORD += y2;
        
        lst1.add(translate2);
        master.getChildren().add(translate2);
        

        // for (TranslateTransition t: lst1){
        // master.getChildren().add(t);
        // }
        master.play();
        System.out.println("visit item idk");

        // update drone's coordinates
        dashboardController.droneIC.setXcoordinate(dashboardController.commCenterIC.getXcoordinate());
        dashboardController.droneIC.setYcoordinate(dashboardController.commCenterIC.getYcoordinate());
        

        
    }
    @Override
    public void gotoXYZ(int x, int y, int z, int speed) throws IOException {
        // TODO Auto-generated method stub
        
    }

    public void goScanFarm() {
        dashboardController.X_FINAL_COORD = 0;
        dashboardController.Y_FINAL_COORD = 0;

        int x1 = dashboardController.commCenterIC.getXcoordinate();
        int y1 = dashboardController.commCenterIC.getYcoordinate();
        int w1 = dashboardController.commCenterIC.getWidth();
        int h1 = dashboardController.commCenterIC.getHeight();
        // x1 = (int) x1 + w1/2 - 30; //centering drone over the item
        // y1 = (int) y1 + h1/2 - 30; //centering drone over item

        int deltaX1 =  -520 + x1 ;
        int deltaY1 = y1 - 30;
        


        int[][] coordinates = {
            {0, 700}, 
            {100, 0},
            {0, -700},
            {100, 0},
            {0, 700},
            {100, 0},
            {0, -700},
            {100, 0},
            {0, 700},
            {100, 0},
            {0, -700},
            {deltaX1, deltaY1}
        };

        SequentialTransition master = new SequentialTransition();

        ArrayList<TranslateTransition> lst1 = new ArrayList<>();

        dashboardController.droneIC.getImageView().toFront();
        TranslateTransition initiate = new TranslateTransition();
        initiate.setNode(dashboardController.droneIC.getImageView());
        initiate.setDuration(Duration.millis(900));
        initiate.setByX(20-dashboardController.droneIC.getXcoordinate()); //centering drone to top left
        initiate.setByY(30-dashboardController.droneIC.getYcoordinate()); 
        lst1.add(initiate);

        for (int i = 0;i<coordinates.length;i++){
            TranslateTransition translate = new TranslateTransition();
            
            int[] arr = coordinates[i];
            int x = arr[0];
            int y = arr[1];
            
            translate.setNode(dashboardController.droneIC.getImageView());
            translate.setDuration((Duration.millis(1000)));
            System.out.println(x +" "+ y);
            translate.setByX(x);
            translate.setByY(y);
            
            dashboardController.X_FINAL_COORD += x;
            dashboardController.Y_FINAL_COORD += y;
            
            lst1.add(translate);
        }

        for (TranslateTransition t: lst1){
        master.getChildren().add(t);
        }
        master.play();
        System.out.println("scan farm");

        // update drone's coordinates
        dashboardController.droneIC.setXcoordinate(dashboardController.commCenterIC.getXcoordinate());
        dashboardController.droneIC.setYcoordinate(dashboardController.commCenterIC.getYcoordinate());
        

        
    }
}

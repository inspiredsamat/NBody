import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NBody {
    public static void main(String[] args) throws FileNotFoundException {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String backgroundImage = "assets/galaxy elements/starfield.jpg";
        String fileName = "assets/universe/" + args[2];
        File dataFile = new File(fileName);
        Scanner scanFile = new Scanner(dataFile);
        int nPlanets = scanFile.nextInt();
        double radius = scanFile.nextDouble();   
        Planet[] planets = new Planet[nPlanets];

        for (int i = 0; i < nPlanets; i++) {
            double xPos = scanFile.nextDouble();
            double yPos = scanFile.nextDouble();
            double xVel = scanFile.nextDouble();
            double yVel = scanFile.nextDouble();
            double mass = scanFile.nextDouble();
            String imageName = scanFile.next();
            planets[i] = new Planet(xPos, yPos, xVel, yVel, mass, imageName);
        }
        
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(-radius, radius);
        StdDraw.setYscale(-radius, radius);
        StdDraw.picture(0, 0, backgroundImage);
        StdAudio.playInBackground("assets/sound effects/2001.wav");
        for (Planet p : planets) {
            p.draw();
        }
        StdDraw.show();
        double time = 0;
        while (time < T) {
            double xForces[] = new double[nPlanets];
            double yForces[] = new double[nPlanets];
            
            for (int i = 0; i < nPlanets; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < nPlanets; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, backgroundImage);

            for (Planet p : planets) {
                p.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }
    
        scanFile.close();
    }    
}

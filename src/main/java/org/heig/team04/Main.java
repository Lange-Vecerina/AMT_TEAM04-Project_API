package org.heig.team04;


import java.io.*;
import static org.heig.team04.scenarios.Scenario.runRootObjectAndImageExists;
import static org.heig.team04.scenarios.Scenario.runRootObjectExistNoImageScenario;

public class Main {

    public static void main(String[] args) throws IOException{

        System.out.println("=====================Root Object and Image already exists================");
        System.out.println();
        runRootObjectAndImageExists();

        System.out.println();
        System.out.println("=====================Root Object exists but no image================");
        System.out.println();
        runRootObjectExistNoImageScenario();

    }

}




# AMT_TEAM04-Project_API

**Authors : Yanik Lange, Ivan Vecerina**

## **Introduction**


Hello there ! 

Welcome to the AMT Team 04 Project API repository.
The following repository is part of a bigger multi-repository project that we are working on, consisting of 3 repositories :
* [API](https://github.com/Lange-Vecerina/AMT_TEAM04-Project_API) (you are here)
* [Simple Storage Microservice](https://github.com/Lange-Vecerina/AMT_TEAM04-Project_DataObject)
* [Label Detection Microservice](https://github.com/Lange-Vecerina/AMT_TEAM04-Project_LabelDetector)

## **Repository description**

> This repository will be used to store the API of our project that we will be making next sprint.

At this point the API is not yet implemented, and the repository is only contains a small project that tests scenarios using the microservices we have already implemented.

## **Dependencies**

*The following prerequisite are necessary to run the project on your machine :*

* Java 11 (or higher)
* Maven 
* AWS credentials
* Docker
* The following microservices running on your machine as docker containers :
    * Simple Storage Microservice (link to the repository above)
    * Label Detection Microservice (link to the repository above)

### **Why Maven ?**

> Maven enables us to manage the project's build, reporting and documentation from a central piece of information. Maven is a build automation tool used primarily for Java projects. Maven addresses two aspects of building software: first, it describes how software is built, and second, it describes its dependencies. Maven is used to build and manage projects based on the Project Object Model (POM). 

### **Why Docker ?**

Docker enables us to package an application with all of its dependencies into a standardized unit for software development that includes everything it needs to run: code, runtime, system tools, system libraries and settings. Making our application portable and easy to run on any machine.

### **Install Dependencies**

- To install Java 11 on your machine you can follow the following tutorial : https://www.oracle.com/java/technologies/javase-jdk11-downloads.html
- To install Maven on your machine you can follow the following tutorial : https://maven.apache.org/install.html
- To install Docker on your machine you can follow the following tutorial : https://docs.docker.com/get-docker/

### **Run the microservices**

To run the microservices you need to pull the docker images from the docker hub and run them on your machine. Run the following commands :

**Pull the images :**

    docker pull ghcr.io/lange-vecerina/amt_team04-project_dataobject:latest
>
    docker pull ghcr.io/lange-vecerina/amt_team04-project_labeldetector:latest

**Run the images :**

    docker run -p 8080:8080 ghcr.io/lange-vecerina/amt_team04-project_dataobject:latest
>
    docker run -p 8081:8081 ghcr.io/lange-vecerina/amt_team04-project_labeldetector:latest


## **Adapt personal settings**

To run the application with your own AWS credentials you need to add the following credentials as environnement path of 
your machine :

Variable : **AWS_ACCESS_KEY_ID** Value : **<your_aws_access_key>**

Variable : **AWS_SECRET_ACCESS_KEY** Value : **<your_aws_secret_key>**

Example how to set environment variable on windows :
https://docs.oracle.com/en/database/oracle/machine-learning/oml4r/1.5.1/oread/creating-and-modifying-environment-variables-on-windows.html#GUID-DD6F9982-60D5-48F6-8270-A27EC53807D0

> TODO ajouter les commandes pour récupérer les dépendances

> TODO ajouter les commandes pour compiler, ainsi que quelque explications sur comment et pourquoi vous utilisez Maven 

> TODO ajouter la commande sur comment lancer un ou tout les tests 


## **Run the Test Scenarios on your machine**

Now that you have everything set up, you can run the test scenarios on your machine.


### **Run on your machine TODO**

Download the latest **.jar** release on the **Release** section in this github repository.

Open a *cmd/terminal* and go the directory where you saved your downloaded **.jar**.

To run the program enter the following command : 

```java -jar lab3_rekognition-v1-shaded.jar "<your_path_of_the_image_you_want_to_label>"```

Example of a **fruits.jpg** image in ```D:/image/``` folder :

```java -jar lab3_rekognition-v1-shaded.jar "D:/image/fruits.jpg"```

This will generate 2 temporary links to the bucket :

* The first links to the bucket where the image you asked to be labeled by Amazon Rekognition is.
* The second links to the result of the labeling (in the bucket too).

## Backlog TODO

[Backlog](https://github.com/orgs/Lange-Vecerina/projects/2)

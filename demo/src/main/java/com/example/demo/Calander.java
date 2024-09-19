package com.example.demo;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;

public class Calander extends Application {
    HBox hb = new HBox();

    public static void main(String[] args) {
        launch();
    }

public void start(Stage Stage){

        WeekView();
        Scene scene = new Scene(hb, 800, 600);
        Stage.setScene(scene);
        Stage.setFullScreen(true);
        Stage.show();

    }

    public void WeekView(){
        ScrollPane[] Scrolldays = new ScrollPane[7];
        VBox[] days = new VBox[7];
        Label test;
        Label day;
        VBox menu = new VBox();
        HBox week = new HBox();
        Button b1 = new Button("Make Event");
        Button b2 = new Button("Month view");
        Button b3 = new Button("return to Home");
        String[] daynames = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};

        for (int i=0;i<days.length;i++){
                days[i] = new VBox();
            Scrolldays[i] = new ScrollPane();
    }
        for(int i=0;i<days.length;i++){
            VBox one = days[i];
            VBox two = new VBox(60);
            for (int j=1;j< 12; j++) {
                if (j<10) {
                    test = new Label(j + ":00 AM        ");
                } else {
                    test = new Label(j + ":00 AM       ");
                }
                test.setStyle("-fx-font-size: 14pt");
                two.getChildren().add(test);
            }
            for (int j=1;j< 12; j++) {
                if (j<10) {
                    test = new Label(j + ":00 PM        ");
                } else {
                    test = new Label(j + ":00 PM       ");
                }
                test.setStyle("-fx-font-size: 14pt");
                two.getChildren().add(test);
            }
            day = new Label(daynames[i]);
            day.setStyle("-fx-font-size: 16pt;"+"-fx-font-weight: bold;");
            one.getChildren().addAll(day,two);
            Scrolldays[i]=new ScrollPane(one);
            two.setPrefWidth(300);
        }
        for (ScrollPane SC : Scrolldays) {
            SC.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            SC.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

            week.getChildren().add(SC);
        }
        ScrollPane weekscroll = new ScrollPane(week);
        weekscroll.setPrefWidth(1200);
        menu.getChildren().addAll(b1,b2,b3);
        menu.setPrefWidth(300);
        weekscroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        weekscroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        hb.getChildren().addAll(weekscroll,menu);
    }
    public void MonthView(){

    }

}

package com.example.demo;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
public class MakeEvent extends Application {
    VBox vb = new VBox(10);
    String[] color = {"Red","Orange","Yellow","Green","LightBlue","Blue","Purple"};
    int colorIndex = 0;
Event[] Storage = new Event[10];
    int numofeve = 0;
 public void EMP(){

     Label message = new Label("Please enter your new event information");
     Label StartTime = new Label("Start Time:");
     Label EndTime = new Label("End Time:");
     Label EC = new Label(":");
     Label SC = new Label(":");
     message.setStyle("-fx-font-size: 20pt;"+"-fx-font-family: 'Impact';");

     Button CC = new Button();
     Button Submit = new Button("Submit");
     Button Back = new Button("Return to Calender");
     HBox hb1 = new HBox(20);
     HBox hb2 = new HBox(20);
     HBox hb3 = new HBox();
     HBox hb4 = new HBox();
     HBox hb5 = new HBox();
     HBox hb6 = new HBox();
     vb.setStyle("-fx-background-color: #FEF3E0;");

     hb1.setAlignment(Pos.CENTER);
     hb2.setAlignment(Pos.CENTER);
     hb3.setAlignment(Pos.CENTER);
     hb4.setAlignment(Pos.CENTER);
     hb5.setAlignment(Pos.CENTER);
     hb6.setAlignment(Pos.BOTTOM_LEFT);
     vb.setAlignment(Pos.CENTER);

     CC.setStyle("-fx-background-color: "+color[0]+"; " + "-fx-min-width: 25px; " + "-fx-min-height: 25px; " + "-fx-max-width: 25px; " + "-fx-max-height: 25px; " + "-fx-background-radius: 50%; " + "-fx-border-radius: 50%;");
     CC.setOnAction(e -> {
         colorIndex = (colorIndex + 1) % color.length;
         CC.setStyle("-fx-background-color: " + color[colorIndex] + "; "+ "-fx-min-width: 25px; " + "-fx-min-height: 25px; " + "-fx-max-width: 25px; " + "-fx-max-height: 25px; " + "-fx-background-radius: 50%; " + "-fx-border-radius: 50%;");
     });

     TextField name = new TextField("Name of Event");


     MenuButton SM = new MenuButton("00");
     MenuButton SH = new MenuButton("0");
     MenuButton SS = new MenuButton("AM");
     MenuButton EM = new MenuButton("00");
     MenuButton EH = new MenuButton("0");
     MenuButton ES = new MenuButton("AM");
     MenuItem SA = new MenuItem("AM");
     MenuItem SP = new MenuItem("PM");
     MenuItem EA = new MenuItem("AM");
     MenuItem EP = new MenuItem("PM");

     SS.getItems().add(SA);
     SS.getItems().add(SP);
     ES.getItems().add(EA);
     ES.getItems().add(EP);

     for (int i = 0;i<60;i++){
         MenuItem ST;
         MenuItem ET;

         if (i<10) {

             ST = new MenuItem("0" + i);
             ET = new MenuItem("0" + i);
             SM.getItems().addAll(ST);
             EM.getItems().addAll(ET);
             if(i>0){
                 ST = new MenuItem(""+i);
                 ET = new MenuItem(""+i);

                 SH.getItems().addAll(ST);
                 EH.getItems().addAll(ET);
             }
         } else {

             ST = new MenuItem(""+i);
             ET = new MenuItem(""+i);

             SM.getItems().addAll(ST);
             EM.getItems().addAll(ET);
             if(i<13){
                 ST = new MenuItem(""+i);
                 ET = new MenuItem(""+i);
                 SH.getItems().addAll(ST);
                 EH.getItems().addAll(ET);
             }
         }

         for (MenuItem item : SM.getItems()) {
             item.setOnAction(e -> Select(SM, item.getText()));
         }
         for (MenuItem item : SH.getItems()) {
             item.setOnAction(e -> Select(SH, item.getText()));
         }
         for (MenuItem item : SS.getItems()) {
             item.setOnAction(e -> Select(SS, item.getText()));
         }
         for (MenuItem item : EM.getItems()) {
             item.setOnAction(e -> Select(EM, item.getText()));
         }
         for (MenuItem item : EH.getItems()) {
             item.setOnAction(e -> Select(EH, item.getText()));
         }
         for (MenuItem item : ES.getItems()) {
             item.setOnAction(e -> Select(ES, item.getText()));
         }
     }
     Submit.setOnAction(e -> {
         Storage[numofeve] = new Event(color[colorIndex], name.getText(), SS.getText(), ES.getText(), Integer.parseInt(SM.getText()), Integer.parseInt(SH.getText()), Integer.parseInt(EM.getText()), Integer.parseInt(EH.getText()));
         EP(Storage[0]);
     });

     hb1.getChildren().addAll(message);
     hb2.getChildren().addAll(name,CC);
     hb3.getChildren().addAll(StartTime,SH,SC,SM,SS);
     hb4.getChildren().addAll(EndTime,EH,EC,EM,ES);
     hb5.getChildren().addAll(Submit);
     hb6.getChildren().addAll(Back);
     vb.getChildren().addAll(hb1,hb2,hb3,hb4,hb5,hb6);

 }
 public void EP(Event eve){
     vb.getChildren().clear();
     vb.setAlignment(Pos.CENTER);
     AnchorPane ap = new AnchorPane();
     ap.setPrefSize(400, 300);
     ap.setMaxWidth(400);
     HBox hb = new HBox(10);
     hb.setAlignment(Pos.CENTER);
     Label title = new Label("Here is your Event");
     ap.setStyle("-fx-background-color: "+eve.Color+";");
     Label name = new Label(eve.Name);
     Label St,Et;
     if (eve.SM <10){
         St = new Label(eve.SH + ":0" + eve.SM + " " + eve.SSFX);
     } else {
         St = new Label(eve.SH + ":" + eve.SM + " " + eve.SSFX);
     }
     if (eve.EM <10){
         Et = new Label(eve.EH+":0"+eve.EM+" "+eve.ESFX);
     } else {
         Et = new Label(eve.EH+":"+eve.EM+" "+eve.ESFX);
     }

     title.setStyle("-fx-font-weight: bold;"+"-fx-font-size: 20pt;"+"-fx-font-family: 'comic sans ms';");
     name.setStyle("-fx-font-weight: bold;"+"-fx-font-size: 16pt;"+"-fx-font-family: 'comic sans ms';");
     St.setStyle("-fx-font-weight: bold;"+"-fx-font-size: 16pt;"+"-fx-font-family: 'comic sans ms';");
     Et.setStyle("-fx-font-weight: bold;"+"-fx-font-size: 16pt;"+"-fx-font-family: 'comic sans ms';");

     hb.getChildren().addAll(St,name);
     AnchorPane.setBottomAnchor(Et,0.0);
     ap.getChildren().addAll(hb,Et);
     vb.getChildren().addAll(title,ap);

 }
    public void start(Stage Stage) {
   EMP();
        Scene scene = new Scene(vb, 800, 600);
        Stage.setScene(scene);
        Stage.setFullScreen(true);
        Stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    public  void Select(MenuButton m, String s ) {
        m.setText(s);
    }
    class Event{
        String Color, Name, SSFX, ESFX;
        int SH, SM, EH,EM;

        Event(String Color,String Name, String SSFX, String ESFX, int SM, int SH,int EM, int EH){
            this.Color = Color;
            this.Name = Name;
            this.SSFX = SSFX;
            this.ESFX = ESFX;
            this.SM = SM;
            this.SH = SH;
            this.EM = EM;
            this.EH = EH;
        }

    }
}

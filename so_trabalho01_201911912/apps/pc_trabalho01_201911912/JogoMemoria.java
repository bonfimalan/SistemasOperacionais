package apps.pc_trabalho01_201911912;


/********************************************************************
* Autor: Alan Bonfim Santos
* Inicio: 25/02/2021 15:20:20
* Ultima alteracao: 03/03/2021 23:47:25
* Nome: Jogo da Memoria
* Funcao: Um jogo da memoria simples com um total de 10 combinacoes
********************************************************************/
import java.io.IOException;

import apps.pc_trabalho01_201911912.controles.PrincipalController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import model.ApplicationInfo;

public class JogoMemoria extends ApplicationInfo {
  public static final String PATH_TO_APP = "/apps/pc_trabalho01_201911912";

  public JogoMemoria() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource(PATH_TO_APP + "/view/principal_view.fxml"));

    //instanciando e cofigurando o construtor para o fxml
    PrincipalController controle = new PrincipalController();
    loader.setController(controle);

    AnchorPane root = loader.load();

    super.setName("Jogo da Memoria");
    super.setImagePath(PATH_TO_APP + "/recursos/imagens/icone.png");
    super.setResizable(true);

    super.setParent(root);
  }

  @Override
  public void onClose() { }
  
  /*
  public static void main(String[] args) {
    launch(args);
  }

  public void start(Stage stagePrimario) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/principal_view.fxml"));

    //instanciando e cofigurando o construtor para o fxmdl
    PrincipalController controle = new PrincipalController();
    loader.setController(controle);

    Parent root = loader.load();

    Scene scene = new Scene(root, 1024, 600);
    //Manipulando o stage
    stagePrimario.setTitle("Jogo da Memoria");//Definindo o nome do app
    stagePrimario.getIcons().add(new Image("/recursos/imagens/icone.png"));//Definindo a imagem do app
    
    stagePrimario.setScene(scene);
    stagePrimario.show();
  }
  */
}
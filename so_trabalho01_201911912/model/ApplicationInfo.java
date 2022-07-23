/********************************************************************
 * Author: Alan Bonfim Santos
 * Registration: 201911912
 * Initial date: 17/07/2021 19:44
 * Last update: 22/07/2021 20:47
 * Name: ApplicationInfo.java
 * Function: Stores information of a application. It's used to create
 *    a app in the desktop and centralize information about a app
 *******************************************************************/
package model;

import controller.InternalStageController;
import javafx.scene.layout.Pane;

public abstract class ApplicationInfo {
  private String name;
  private String imagePath;
  private Pane parent = new Pane();
  private InternalStageController controller;
  private boolean resizable = true;
  private boolean opened = false;

  // default values
  private double prefWidth = 500;
  private double prefHeight = 350;

  public ApplicationInfo() {}

  public ApplicationInfo(String imagePath, String name) {
    this.name = name;
    this.imagePath = imagePath;
  }

  public ApplicationInfo(String imagePath, String name, Pane parent) {
    this.name = name;
    this.imagePath = imagePath;
    this.parent = parent;
  }

  public ApplicationInfo(String name, String imagePath, Pane parent, double prefWidth, double prefHeight) {
    this.name = name;
    this.imagePath = imagePath;
    this.parent = parent;
    this.prefWidth = prefWidth;
    this.prefHeight = prefHeight;
  }

  public ApplicationInfo(String name, String imagePath, Pane parent, double prefWidth, double prefHeight, boolean resizable) {
    this.name = name;
    this.imagePath = imagePath;
    this.parent = parent;
    this.prefWidth = prefWidth;
    this.prefHeight = prefHeight;
    this.resizable = resizable;
  }

  public abstract void onClose();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImagePath() {
    return imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }

  public Pane getParent() {
    return parent;
  }

  public void setParent(Pane parent) {
    this.parent = parent;
  }

  public InternalStageController getController() {
    return controller;
  }

  public void setController(InternalStageController controller) {
    this.controller = controller;
  }

  public double getPrefWidth() {
    return prefWidth;
  }

  public void setPrefWidth(double prefWidth) {
    this.prefWidth = prefWidth;
  }

  public double getPrefHeight() {
    return prefHeight;
  }

  public void setPrefHeight(double prefHeight) {
    this.prefHeight = prefHeight;
  }

  public boolean isResizable() {
    return resizable;
  }

  public void setResizable(boolean resizable) {
    this.resizable = resizable;
  }

  public boolean isOpened() {
    return opened;
  }

  public void setOpened(boolean opened) {
    this.opened = opened;
  }
}

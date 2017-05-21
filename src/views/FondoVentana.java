/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

/**
 *
 * @author A644308
 */


import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDesktopPane;

public class FondoVentana extends JDesktopPane {

  private Image imagen;
 
     public FondoVentana() {
  
       this.setLayout(null); 
    //   this.fondoZize(1366, 768);
  
  try {
       imagen=ImageIO.read(getClass().getResource("/resources/fondo.jpg"));
  }
 catch (IOException e) {
   e.printStackTrace();
  }

  }
 
 public void paintComponent(Graphics g){
  
     super.paintComponent(g);
     g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
     setOpaque(false);
 }
 
  public void fondoZize(int X, int Y){
  
     this.setBounds(0, 0, X, Y);
 }
}
package ct;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.Timer;
public class panel extends javax.swing.JPanel implements ActionListener {
    
    private static final int START_X = 0;
    private static final int START_Y = 0;
    private static final int IMG_SIZE = 50;
    
    
    File f, newf;
    BufferedImage img;
    int key, key2;
    long tstart;
    boolean gameStart;
    Point Vel, pos, window;
    int stop, velocity, kcode, sec, min;
    ActionListener getSeconds;
    Timer tick, gameTimer;
    
    public panel() {
        // Set focus and bg
        this.setFocusable(true);
        setBackground(Color.white);
        this.requestFocus();
        
        tstart = System.currentTimeMillis();
        velocity = 2;
        tick = new Timer(4, this);
        getSeconds = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(stop == 0){
                sec++;
                if(String.valueOf(sec).length() == 1){
                    time.setText(String.valueOf(min) + ":" + "0" + String.valueOf(sec));
                }else{
                    time.setText(String.valueOf(min) + ":" + String.valueOf(sec));
                }
                }
                
            }
        };
        gameTimer = new Timer(1000, getSeconds);
        window = new Point();
        Vel = new Point();
        pos = new Point(START_X, START_Y);
        stop = 0;
        
        // File loading
        try{
            f = new File("C:/Users/Joalu/Desktop/CT/img/icon.png"); 
            img = ImageIO.read(f);
        }catch(IOException io){
            io.printStackTrace();
        }
        
        
        tick.start();
        
        initComponents();
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if(sec == 60){
           sec = 0;
           min++;
           if(String.valueOf(sec).length() == 1){
               time.setText(String.valueOf(min) + ":" + "0" + String.valueOf(sec));
           }else{
               time.setText(String.valueOf(min) + ":" + String.valueOf(sec));
           }
           
        }
        window.x = getWidth();
        window.y = getHeight();
        // right
        if(pos.x >= window.x){
            pos.x = pos.x - window.x - IMG_SIZE;
        }
        // left
        if(pos.x < -IMG_SIZE){
           pos.x = pos.x + window.x + IMG_SIZE;
        }
        // down
        if(pos.y > window.y){
            pos.y = pos.y - window.y - IMG_SIZE;
        }
        // up
        if(pos.y < -IMG_SIZE){
            pos.y = pos.y + window.y + IMG_SIZE;
        }
        
        g2.drawImage(img, pos.x, pos.y, IMG_SIZE, IMG_SIZE, null);
    }
    
    private void up(){
        Vel.y = -velocity;
        Vel.x = 0;
    }
    
    private void down(){
        Vel.y = velocity;
        Vel.x = 0;
    }
    
    private void left(){
        Vel.y = 0;
        Vel.x = -velocity;
    }
    
    private void right(){
        Vel.y = 0;
        Vel.x = velocity;
    }
    
    private void exec(int key){
        switch(key){
            case KeyEvent.VK_UP:
                up();
                break;
            case KeyEvent.VK_LEFT:
                left();
                break;
            case KeyEvent.VK_DOWN:
                down();
                break;
            case KeyEvent.VK_RIGHT:
                right();
                break;
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        time = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        setForeground(new java.awt.Color(255, 255, 255));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        time.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        time.setForeground(new java.awt.Color(0, 0, 0));
        time.setText("0:00");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(346, Short.MAX_VALUE)
                .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(time)
                .addContainerGap(268, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        gameTimer.start();
        key = evt.getKeyCode();
        if(key == evt.VK_SPACE){
           stop++;
           if(stop == 1){
               Vel.x = 0; 
               Vel.y = 0;
           }else{
               stop = 0;
               exec(key2);
           }
       }else if(stop == 0){
            key2 = evt.getKeyCode();
            exec(key);
       }
    }//GEN-LAST:event_formKeyReleased
  
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        pos.x += Vel.x;
        pos.y += Vel.y;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel time;
    // End of variables declaration//GEN-END:variables
}

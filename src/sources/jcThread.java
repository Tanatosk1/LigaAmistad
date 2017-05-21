package sources;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class jcThread implements Runnable{

    private JProgressBar jProgressBar;
    //private int i = 1;
    private int value;// = 1000;//retardo en milisegundos
    JFrame frame = new JFrame();

    /**
     * Constructor de clase
     * @param value
     */
    public jcThread( int value )
    {
        this.value = value;
        jProgressBar = new JProgressBar(1, value);
        jProgressBar.setBounds(10, 10, 10, 10);
        jProgressBar.setStringPainted(true);       
        frame.setSize(200, 35);
        frame.add(jProgressBar);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void run() {
        for(int i = 1; i <= value; i++){
            jProgressBar.setValue(i);
            if(LeerExcel.terminar){
                break;
            }
            try{Thread.sleep( 60 );}            
            catch (InterruptedException e){ System.err.println( e.getMessage() ); }                       
        }
        frame.dispose();
    }

}

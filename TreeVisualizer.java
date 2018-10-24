import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
class View extends JPanel {
  View() {

  }

  public void paintComponent(Graphics g) {
    g.setColor(new Color(128, 255, 255));
    g.fillRect(0, 0, this.getWidth(), this.getHeight());
  }

}

@SuppressWarnings("serial")
class TreeVisualizer extends JFrame {
  View view;

  TreeVisualizer() {
    view = new View();

    this.setSize(1536, 1024);
    this.getContentPane().add(view);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }

  public void run() {
    view.repaint();
  }

  public static void main(String[] args) {
    TreeVisualizer tv = new TreeVisualizer();
    tv.run();
  }
}

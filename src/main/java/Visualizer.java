import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.List;
import javax.swing.JPanel;
import oracle.spatial.geometry.JGeometry;
import java.awt.geom.AffineTransform;

public class Visualizer extends JPanel {
    private DBConnection dbconn;
    private static final long serialVersionUID = 1L;

    Visualizer (DBConnection dbc) {
        this.dbconn = dbc;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        AffineTransform xfm = AffineTransform.getTranslateInstance(0, 500);
        xfm.scale(25, -25);

        List<JGeometry> lst = this.dbconn.getJList();

        for (int i = 0; i <= lst.size() - 1; i++) {
            if (lst.get(i) != null) {
                Shape a = lst.get(i).createShape(xfm);
                g2.draw(a);
            }
        }
    }
}

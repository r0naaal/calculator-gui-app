import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RoundedButton extends JButton {
    private Color backgroundColor;
    private Dimension originalSize;
    private Dimension pressedSize;

    public RoundedButton(String label, String buttonType) {
        super(label);
        setColors(buttonType);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);

        // Set font size
        setFont(new Font(null, Font.BOLD, 24)); // Change the font size here

        // Set sizes based on button type
        originalSize = getButtonSize(buttonType);
        pressedSize = new Dimension(originalSize.width - 1, originalSize.height - 1); // Adjust pressed size
        setPreferredSize(originalSize);
        setSize(originalSize); // Ensure the button starts with the original size

        // Add mouse listener for click effects
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setSize(pressedSize); // Decrease size when pressed
                revalidate();
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                resetSize(); // Reset to original size when released
            }

            @Override
            public void mouseExited(MouseEvent e) {
                resetSize(); // Reset to original size if mouse exits
            }
        });
    }

    private Dimension getButtonSize(String buttonType) {
        switch (buttonType) {
            case "number": // Numbers 1-9
            case "operator": // Operators +, -, *, /
                return new Dimension(70, 70);
            case "equals": // Equals button
                return new Dimension(70, 150);
            case "ac": // AC button
                return new Dimension(70, 150);
            case "zero": // Zero button
                return new Dimension(150, 70);
            default:
                return new Dimension(70, 70); // Default size
        }
    }

    private void setColors(String buttonType) {
        switch (buttonType) {
            case "number":
                backgroundColor = new Color(82, 82, 82); // Dark gray for number buttons
                break;
            case "equals":
                backgroundColor = new Color(255, 165, 0); // Orange-yellow for equals button
                break;
            case "operator":
                backgroundColor = Color.LIGHT_GRAY; // Light gray for operator buttons
                break;
            case "ac":
                backgroundColor = new Color(82, 82, 82);
                break;
            case "zero":
                backgroundColor = new Color(82, 82, 82);
                break;
            default:
                backgroundColor = Color.GRAY; // Default color for unspecified buttons
                break;
        }
        setBackground(backgroundColor);
        setForeground(Color.WHITE); // Set text color to white for contrast
    }

    private void resetSize() {
        setSize(originalSize); // Reset to original size
        revalidate(); // Update layout
        repaint(); // Ensure the button is repainted
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintComponent(g); // Draw text on top
    }

    @Override
    public Dimension getPreferredSize() {
        return originalSize; // Ensure the preferred size is the original size
    }
}
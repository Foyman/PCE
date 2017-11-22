package logic;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class FaqPage
{

    // To Please SonarQube
    private FaqPage()
    {

    }

    public static List<JComponentWithLayout> createFrame()
    {

        List<JComponentWithLayout> panels = new ArrayList<JComponentWithLayout>(3);

        // Panels
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel footer = new JPanel();

        // Does everything to create header
        createHeader(header);

        // Makes table scroll-able
        JScrollPane scroll = new JScrollPane();

        // Everything for footer below
        JLabel footText = new JLabel("© 2017 Polyratings Course Edition");
        footText.setForeground(Color.WHITE);
        footer.setBackground(new Color(7, 88, 64));
        footer.add(footText);

        // All panels into frame
        panels.add(new JComponentWithLayout(header, BorderLayout.NORTH));
        panels.add(new JComponentWithLayout(scroll, BorderLayout.CENTER));
        panels.add(new JComponentWithLayout(footer, BorderLayout.SOUTH));

        return panels;
    }

    /**
     * Helper method to replace complexity of this method
     * 
     * @param header - JPanel of the header
     */
    public static void createHeader(JPanel header)
    {
        // Back Button
        JButton button = new JButton();
        button.setText("< Back");
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (e.getSource() == button)
                {
                    FrameController.backFrame();
                }
            }
        });
        header.add(button, 0);

        // Adds whitespace to center title (Sorry)
        JLabel whitespace = new JLabel("                              ");
        header.add(whitespace, 1);

        // Everything for header below
        JLabel headText = new JLabel("FAQ");
        headText.setForeground(Color.WHITE);
        headText.setFont(headText.getFont().deriveFont(64.0f));
        header.setBackground(new Color(7, 88, 64));
        header.add(headText, 2);

    }

}

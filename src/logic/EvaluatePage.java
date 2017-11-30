package logic;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class EvaluatePage
{

    // To Please SonarQube
    private EvaluatePage()
    {

    }

    public static List<JComponentWithLayout> createFrame()
    {
        final GridBagConstraints c = new GridBagConstraints();
        List<JComponentWithLayout> panels = new ArrayList<JComponentWithLayout>(3);

        // Panels
        JPanel header = HeaderFactory.createHeader("Evaluate a Course");
        JPanel footer = new JPanel();
        JPanel main = new JPanel(new GridBagLayout());

        
        //Main
        main.setBackground(new Color(255, 255, 255));
        JLabel text = new JLabel("TO BE IMPLEMENTED AT A FUTURE DATE");
        text.setFont(text.getFont().deriveFont(30.0f));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        main.add(text, c);
        

/*        // Makes table scroll-able
        JScrollPane scroll = new JScrollPane();
*/
        // Everything for footer below
        JLabel footText = new JLabel("© 2017 Polyratings Course Edition");
        footText.setForeground(Color.WHITE);
        footer.setBackground(new Color(7, 88, 64));
        footer.add(footText);

        // All panels into frame
        panels.add(new JComponentWithLayout(header, BorderLayout.NORTH));
        panels.add(new JComponentWithLayout(main, BorderLayout.CENTER));
        panels.add(new JComponentWithLayout(footer, BorderLayout.SOUTH));

        return panels;
    }
}

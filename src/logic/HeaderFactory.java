package logic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HeaderFactory
{
    public HeaderFactory()
    {

    }

    public static JPanel createHeader(String headerLabel)
    {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(7, 88, 64));

        // Back Button
        JButton button = new JButton();
        button.setText("< Back");
        button.setBorderPainted(false);
        button.setForeground(Color.WHITE);
        button.setFont(button.getFont().deriveFont(20.0f));
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
        header.add(button, BorderLayout.WEST);

        // Everything for header below
        JLabel headerText = new JLabel(headerLabel);
        headerText.setForeground(Color.WHITE);
        headerText.setFont(headerText.getFont().deriveFont(64.0f));
        headerText.setHorizontalAlignment(JLabel.CENTER);
        header.add(headerText, BorderLayout.CENTER);

        return header;
    }
}

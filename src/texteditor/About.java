package texteditor;

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class About {

    private final JFrame frame;
    private final JPanel panel;
    private String contentText;
    private final JLabel text;

    public About() {
        panel = new JPanel(new FlowLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        frame = new JFrame();

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });


        frame.setVisible(true);
        frame.setSize(500,400);
      //  frame.setLocationRelativeTo();
        text = new JLabel();
    }
    
    // functions to provide information about us and the software
    public void us() {
        frame.setTitle("About Us - " + TextEditor.NAME);

        contentText =
        "<html><body><p>" +
        "Authors: <br /> Arjun Ohri<br /> Skyler Malinowski <br /> Alejandro Aguilar <br /> Raj Balaji" + 
        " <br /> <br />Emails: " +
        "<a href='mailto:" + TextEditor.AUTHOR_EMAIL + "?subject=About the Software'>" + TextEditor.AUTHOR_EMAIL + "</a>";

        text.setText(contentText);
        panel.add(text);
        frame.add(panel);
    }

    public void software() {
        frame.setTitle("About  - RISC-V IDE Editor");

        contentText =
        "<html><body><p>" + "Copyright (C) 2018 <br />" +
         TextEditor.NAME + "<br />" +
        "Version: " + TextEditor.VERSION + 
  "<br /><br /> This program is free software: you can redistribute it "
      + "<br /> and/or modify it under the terms of the GNU Public License "+
        "<br /> as published by the Free Software Foundation, either version"+
        "<br /> 3 of the License, or (at your option) any later version. "+
  "<br /><br /> This program is distributed in the hope that it will be useful,"+
    	"<br /> but WITHOUT ANY WARRANTY; without even the implied warranty of"+
    	"<br /> MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the "+
    	"<br /> GNU General Public License for more details."+
    	"<br /><br /> GNU General Public License v3"+
    	"<br /> Link: https://www.gnu.org/licenses/gpl.html "+
        "</p></body></html>";

        text.setText(contentText);
        panel.add(text);
        frame.add(panel);
    }

}
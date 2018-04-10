package texteditor;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.Element;

//import com.sun.javafx.scene.text.TextLine;

public class UI extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private final JTextArea textArea;
    private final JMenuBar menuBar;
    private final JMenu menuFile, menuEdit, menuFind, menuAbout;
    private final JMenuItem newFile, openFile, saveFile, close, cut, copy, paste, clearFile, selectAll, search,
            aboutMe, aboutSoftware, wordWrap;
    private final JToolBar mainToolbar;
    JButton newButton, openButton, saveButton, clearButton, quickButton, aboutMeButton, aboutButton, closeButton;
    private final Action selectAllAction; 
    private JTextArea lineNumberBar;
    private JScrollPane scrollPane = null;
    private JTextField cursorStatusBar;
    
    // setup icons - File Menu
    private final ImageIcon newIcon = new ImageIcon("icons/new.png");
    private final ImageIcon openIcon = new ImageIcon("icons/open.png");
    private final ImageIcon saveIcon = new ImageIcon("icons/save.png");
    private final ImageIcon closeIcon = new ImageIcon("icons/close.png");

    // setup icons - Edit Menu
    private final ImageIcon clearIcon = new ImageIcon("icons/clear.png");
    private final ImageIcon cutIcon = new ImageIcon("icons/cut.png");
    private final ImageIcon copyIcon = new ImageIcon("icons/copy.png");
    private final ImageIcon pasteIcon = new ImageIcon("icons/paste.png");
    private final ImageIcon selectAllIcon = new ImageIcon("icons/selectall.png");
    private final ImageIcon wordwrapIcon = new ImageIcon("icons/wordwrap.png");

    // setup icons - Search Menu
    private final ImageIcon searchIcon = new ImageIcon("icons/search.png");

    // setup icons - Help Menu
    private final ImageIcon aboutMeIcon = new ImageIcon("icons/about_us.png");
    private final ImageIcon aboutIcon = new ImageIcon("icons/about.png");

    private Keywords kw = new Keywords();
    private HighlightText languageHighlighter = new HighlightText(Color.WHITE);
    AutoComplete autocomplete;
    private boolean hasListener = false;
    private boolean edit = false;
    
    // line number functions
    private final JTextArea getLineNumberBarArea() {
        return (lineNumberBar);
    }
    
    private void updatesBar(int rowNum, int colNum) {
        cursorStatusBar.setText("Line: " + rowNum + " Column: " + colNum);
    }
    
    public UI() {
        // Set the initial size of the window
        setSize(800, 800);

        // Set the title of the window
        TitledBorder title = BorderFactory.createTitledBorder("Untitled | " + TextEditor.NAME);
        setBorder(title);
        
        // Set the default close operation (exit when it gets closed)
       // setDefaultCloseOperation(EXIT_ON_CLOSE);

        // center the frame on the monitor
      //  setLocationRelativeTo(null);
       

        // Set a default font for the TextArea
        textArea = new JTextArea("", 0, 0);
        textArea.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        textArea.setTabSize(2);
        textArea.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        textArea.setTabSize(2);
        

        /* SETTING BY DEFAULT WORD WRAP ENABLED OR TRUE */
        textArea.setLineWrap(true);

        // Set a highlighter to the JTextArea
        textArea.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                edit = true;
                languageHighlighter.highLight(textArea, kw.getSKeywords());
                languageHighlighter.highLight(textArea, kw.getasmKeywords());
            }
        });

        // don't have to worry about text area size
        this.setLayout(new BorderLayout()); // the BorderLayout bit makes it fill it automatically
        this.add(textArea);
        
        // Set line numbers and scroll pane
   
        lineNumberBar = new JTextArea("1");
        lineNumberBar.setFont(textArea.getFont());
        lineNumberBar.setBackground(Color.LIGHT_GRAY);
        lineNumberBar.setEditable(false);
        
        scrollPane = new JScrollPane();
        scrollPane.getViewport().add(textArea);
        scrollPane.setRowHeaderView(lineNumberBar);
        scrollPane
                .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory
                .createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(111)),
                        BorderFactory.createEmptyBorder(6, 6, 6, 6)),
                scrollPane.getBorder()));
        textArea.addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent e) {
                JTextArea dArea = (JTextArea) e.getSource();
                int rowNum = 1;
                int colNum = 1;
                try {
                    int caretpos = dArea.getCaretPosition();
                    rowNum = dArea.getLineOfOffset(caretpos);
                    colNum = caretpos - dArea.getLineStartOffset(rowNum);
                    rowNum += 1;
                } catch (Exception ex) {
                }
                updatesBar(rowNum, colNum);
            }
        });
        
        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);
        cursorStatusBar = new JTextField();
        this.add(cursorStatusBar, BorderLayout.SOUTH);
        updatesBar(1, 0);
        textArea.getDocument().addDocumentListener(new DocumentListener() {
       
        // function to get text and indicate side number
        public String getText() {
            int cursorLocation = textArea.getDocument().getLength();
            Element rootElement = textArea.getDocument()
                    .getDefaultRootElement();
            String sideNumber = "1" + System.getProperty("line.separator");
            for (int i = 2; i < rootElement.getElementIndex(cursorLocation) + 2; i++) {
                sideNumber += i + System.getProperty("line.separator");
            }
            return sideNumber;
        }

        // update functions
        public void changedUpdate(DocumentEvent e) {
            lineNumberBar.setText(getText());
            edit = false;
        }

        public void insertUpdate(DocumentEvent e) {
            lineNumberBar.setText(getText());
            edit = false;
        }

        public void removeUpdate(DocumentEvent e) {
            lineNumberBar.setText(getText());
            edit = false;
        }
    });
        
 
        // Set the Menus
        menuFile = new JMenu("File");
        menuEdit = new JMenu("Edit");
        menuFind = new JMenu("Search");
        menuAbout = new JMenu("About");

        // Set the Items Menu
        newFile = new JMenuItem("New", newIcon);
        openFile = new JMenuItem("Open", openIcon);
        saveFile = new JMenuItem("Save", saveIcon);
        close = new JMenuItem("Quit", closeIcon);
        clearFile = new JMenuItem("Clear", clearIcon);
        search = new JMenuItem("Find/Replace", searchIcon);
        aboutMe = new JMenuItem("About Us", aboutMeIcon);
        aboutSoftware = new JMenuItem("About the Software", aboutIcon);

        menuBar = new JMenuBar();
        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuFind);

        menuBar.add(menuAbout);

        //this.setJMenuBar(menuBar);
        
        
        // Set Actions:
        selectAllAction = new SelectAllAction("Select All", clearIcon, "Select all text", new Integer(KeyEvent.VK_A),
                textArea);
        
       //this.setJMenuBar(menuBar);
        //this.add(menuBar, BorderLayout.NORTH);
       
        // New File
        newFile.addActionListener(this);  // Adding an action listener (so we know when it's been clicked).
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK)); // Set a keyboard shortcut
        menuFile.add(newFile); // Adding the file menu

        // Open File
        openFile.addActionListener(this);
        openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        menuFile.add(openFile);

        // Save File
        saveFile.addActionListener(this);
        saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        menuFile.add(saveFile);

        // Close File
        close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
        close.addActionListener(this);
        menuFile.add(close);

        // Select All Text
        selectAll = new JMenuItem(selectAllAction);
        selectAll.setText("Select All");
        selectAll.setIcon(selectAllIcon);
        selectAll.setToolTipText("Select All");
        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
        menuEdit.add(selectAll);

        // Clear File (Code)
        clearFile.addActionListener(this);
        clearFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.CTRL_MASK));
        menuEdit.add(clearFile);

        // Cut Text
        cut = new JMenuItem(new DefaultEditorKit.CutAction());
        cut.setText("Cut");
        cut.setIcon(cutIcon);
        cut.setToolTipText("Cut");
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
        menuEdit.add(cut);

        // WordWrap
        wordWrap = new JMenuItem();
        wordWrap.setText("Word Wrap");
        wordWrap.setIcon(wordwrapIcon);
        wordWrap.setToolTipText("Word Wrap");

        //Short cut key or key stroke
        wordWrap.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
        menuEdit.add(wordWrap);

       // word wrap function. enabled by default
        wordWrap.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                // If wrapping is false then after clicking on menuitem the word wrapping will be enabled
                if (textArea.getLineWrap() == false) {
                    /* Setting word wrapping to true */
                    textArea.setLineWrap(true);
                } else {
                    // else  if wrapping is true then after clicking on menuitem the word wrapping will be disabled
                    /* Setting word wrapping to false */
                    textArea.setLineWrap(false);
                }
            }
        });

        // Copy Text
        copy = new JMenuItem(new DefaultEditorKit.CopyAction());
        copy.setText("Copy");
        copy.setIcon(copyIcon);
        copy.setToolTipText("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        menuEdit.add(copy);

        // Paste Text
        paste = new JMenuItem(new DefaultEditorKit.PasteAction());
        paste.setText("Paste");
        paste.setIcon(pasteIcon);
        paste.setToolTipText("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
        menuEdit.add(paste);

        // Find Word
        search.addActionListener(this);
        search.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
        menuFind.add(search);

        // About Us
        aboutMe.addActionListener(this);
        aboutMe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        menuAbout.add(aboutMe);

        // About the Software
        aboutSoftware.addActionListener(this);
        aboutSoftware.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
        menuAbout.add(aboutSoftware);

        mainToolbar = new JToolBar();
        this.add(mainToolbar, BorderLayout.NORTH);
        // used to create space between button groups
        newButton = new JButton(newIcon);
        newButton.setToolTipText("New");
        newButton.addActionListener(this);
        mainToolbar.add(newButton);
        mainToolbar.addSeparator();

        openButton = new JButton(openIcon);
        openButton.setToolTipText("Open");
        openButton.addActionListener(this);
        mainToolbar.add(openButton);
        mainToolbar.addSeparator();

        saveButton = new JButton(saveIcon);
        saveButton.setToolTipText("Save");
        saveButton.addActionListener(this);
        mainToolbar.add(saveButton);
        mainToolbar.addSeparator();

        clearButton = new JButton(clearIcon);
        clearButton.setToolTipText("Clear All");
        clearButton.addActionListener(this);
        mainToolbar.add(clearButton);
        mainToolbar.addSeparator();

        quickButton = new JButton(searchIcon);
        quickButton.setToolTipText("Find");
        quickButton.addActionListener(this);
        mainToolbar.add(quickButton);
        mainToolbar.addSeparator();

        aboutMeButton = new JButton(aboutMeIcon);
        aboutMeButton.setToolTipText("About Us");
        aboutMeButton.addActionListener(this);
        mainToolbar.add(aboutMeButton);
        mainToolbar.addSeparator();

        aboutButton = new JButton(aboutIcon);
        aboutButton.setToolTipText("About the Software");
        aboutButton.addActionListener(this);
        mainToolbar.add(aboutButton);
        mainToolbar.addSeparator();

        closeButton = new JButton(closeIcon);
        closeButton.setToolTipText("Exit");
        closeButton.addActionListener(this);
        mainToolbar.add(closeButton);
        mainToolbar.addSeparator();
  
    }
    //@Override
    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            if (edit) {
                Object[] options = {"Save And Exit", "Exit Without Saving", "Cancel"};
                int n = JOptionPane.showOptionDialog(this, "Do you want to save the file ?", "Question",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                if (n == 0) {// save and exit
                    saveFile();
                    System.exit(0);
                     //this.dispose();// dispose all resources and close the application
                } else if (n == 1) {// no save and exit
                	System.exit(0);
                 //   this.dispose();// dispose all resources and close the application
                }
            } else {
                System.exit(99);
            }
        }
    }

    // Make the TextArea available to the autocomplete handler
    protected JTextArea getEditor() {
        return textArea;
    }

    // Enable autocomplete option
    public void enableAutoComplete(File file) {
        if (hasListener) {
            textArea.getDocument().removeDocumentListener(autocomplete);
            hasListener = false;
        }

        ArrayList<String> arrayList;
        String[] list = kw.getSupportedLanguages();

        for (int i = 0; i < list.length; i++) {
            if (file.getName().endsWith(list[i])) {
                switch (i) {
                    case 0:
                    	String[] sk = kw.getSKeywords();
                    	arrayList = kw.setKeywords(sk);
                        autocomplete = new AutoComplete(this, arrayList);
                        textArea.getDocument().addDocumentListener(autocomplete);
                        hasListener = true;
                        break;
                    case 1:
                    	String[] asmk = kw.getasmKeywords();
                    	arrayList = kw.setKeywords(asmk);
                        autocomplete = new AutoComplete(this, arrayList);
                        textArea.getDocument().addDocumentListener(autocomplete);
                        hasListener = true;
                        break;
                }
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        // If the source of the event was our "close" option
        if (e.getSource() == close || e.getSource() == closeButton) {
            if (edit) {
                Object[] options = {"Save and exit", "Exit without saving", "Cancel"};
                int n = JOptionPane.showOptionDialog(this, "Do you want to save the file ?", "Question",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
                if (n == 0) {// save and exit
                    saveFile();
                    System.exit(0);
                   // this.dispose();// dispose all resources and close the application
                } else if (n == 1) {// no save and exit
                	System.exit(0);
                  //  this.dispose();// dispose all resources and close the application
                }
            } else {
            	System.exit(0);
               // this.dispose();// dispose all resources and close the application
            }
        } // If the source was the "new" file option
        else if (e.getSource() == newFile || e.getSource() == newButton) {
            if (edit) {
                Object[] options = {"Save", "Don't Save", "Cancel"};
                int n = JOptionPane.showOptionDialog(this, "Would you like to save?", "Question",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
                if (n == 0) {// save
                    saveFile();
                    edit = false;
                } else if (n == 1) {
                    edit = false;
                    FEdit.clear(textArea);
                }
            } else {
                FEdit.clear(textArea);
            }

        } // If the source was the "open" option
        else if (e.getSource() == openFile || e.getSource() == openButton) {
            JFileChooser open = new JFileChooser(); // open up a file chooser (a dialog for the user to  browse files to open)
            int option = open.showOpenDialog(this); // get the option that the user selected (approve or cancel)

            /*
             * NOTE: because we are OPENing a file, we call showOpenDialog~ if
             * the user clicked OK, we have "APPROVE_OPTION" so we want to open
             * the file
             */
            if (option == JFileChooser.APPROVE_OPTION) {
                FEdit.clear(textArea); // clear the TextArea before applying the file contents
                try {
                    File openFile = open.getSelectedFile();
                    TitledBorder title = BorderFactory.createTitledBorder(openFile.getName() + " | " + TextEditor.NAME);
                    setBorder(title);
                    Scanner scan = new Scanner(new FileReader(openFile.getPath()));
                    while (scan.hasNext()) {
                        textArea.append(scan.nextLine() + "\n");
                    }

                    enableAutoComplete(openFile);
                } catch (Exception ex) { // catch any exceptions, and...
                    // ...write to the debug console
                    System.err.println(ex.getMessage());
                }
            }
        } // If the source of the event was the "save" option
        else if (e.getSource() == saveFile || e.getSource() == saveButton) {
            saveFile();
        }// If the source of the event was the "Bold" button
        
        // Clear File (Code)
        if (e.getSource() == clearFile || e.getSource() == clearButton) {

            Object[] options = {"Yes", "No"};
            int n = JOptionPane.showOptionDialog(this, "Are you sure to clear the text Area ?", "Question",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
            if (n == 0) {// clear
                FEdit.clear(textArea);
            }
        }
        // Find
        if (e.getSource() == search || e.getSource() == quickButton) {
            new Find(textArea);
        } // About Us
        else if (e.getSource() == aboutMe || e.getSource() == aboutMeButton) {
            new About(this).us();
        } // About the Software
        else if (e.getSource() == aboutSoftware || e.getSource() == aboutButton) {
            new About(this).software();
        }
    }

    class SelectAllAction extends AbstractAction {

        /**
         * Used for Select All function
         */
        private static final long serialVersionUID = 1L;

        public SelectAllAction(String text, ImageIcon icon, String desc, Integer mnemonic, final JTextArea textArea) {
            super(text, icon);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(MNEMONIC_KEY, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            textArea.selectAll();
        }
    }

    private void saveFile() {
        // Open a file chooser
        JFileChooser fileChoose = new JFileChooser();
        // Open the file, only this time we call
        int option = fileChoose.showSaveDialog(this);

        /*
             * ShowSaveDialog instead of showOpenDialog if the user clicked OK
             * (and not cancel)
         */
        if (option == JFileChooser.APPROVE_OPTION) {
            try {
                File openFile = fileChoose.getSelectedFile();
                
                TitledBorder title = BorderFactory.createTitledBorder(openFile.getName() + " | " + TextEditor.NAME);
                setBorder(title);
                BufferedWriter out = new BufferedWriter(new FileWriter(openFile.getPath()));
                out.write(textArea.getText());
                out.close();

                enableAutoComplete(openFile);
                edit = false;
            } catch (Exception ex) { // again, catch any exceptions and...
                // ...write to the debug console
                System.err.println(ex.getMessage());
            }
        }
    }

}
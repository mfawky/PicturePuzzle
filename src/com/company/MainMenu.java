package com.company;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainMenu extends JFrame implements ActionListener , MouseListener {

    private Image image;
    private Border PicBorder;
    private JButton LeaderBoard;
    private JLabel MainMenu_label;
    private JLabel pic1_label;
    private JLabel pic2_label;
    private JLabel pic3_label;
    private JLabel pic4_label;
    private JLabel pic5_label;
    private JLabel pic6_label;
    private JLabel pic7_label;
    private JLabel pic8_label;

    private ImageIcon pic1;
    private ImageIcon pic2;
    private ImageIcon pic3;
    private ImageIcon pic4;
    private ImageIcon pic5;
    private ImageIcon pic6;
    private ImageIcon pic7;
    private ImageIcon pic8;
    private ImageIcon pic9;

    private Player tempP;

    public MainMenu() {}
    public MainMenu(Player P) {
        tempP = new Player(P.getUsername(),P.getPassword());
        image = Toolkit.getDefaultToolkit().getImage("F:\\PicPuzzle\\Images\\PuzzleIcon.png");
        this.setIconImage(image);

        LeaderBoard = new JButton("LeaderBoard");
        LeaderBoard.setBounds(130,360,300,33);
        LeaderBoard.setFont(new Font("Calibri",Font.BOLD,30));
        LeaderBoard.addMouseListener(this);


        PicBorder = BorderFactory.createLineBorder(Color.BLACK,3);
        pic1 = new ImageIcon("F:\\PicPuzzle\\Images\\Pho1.jpg");
        pic2 = new ImageIcon("F:\\PicPuzzle\\Images\\Pho2.jpg");
        pic3 = new ImageIcon("F:\\PicPuzzle\\Images\\Pho3.jpg");
        pic4 = new ImageIcon("F:\\PicPuzzle\\Images\\Pho4.jpg");
        pic5 = new ImageIcon("F:\\PicPuzzle\\Images\\Pho5.jpg");
        pic6 = new ImageIcon("F:\\PicPuzzle\\Images\\Pho6.jpg");
        pic7 = new ImageIcon("F:\\PicPuzzle\\Images\\Pho7.jpg");
        pic8 = new ImageIcon("F:\\PicPuzzle\\Images\\Pho8.jpg");

        pic1_label = new JLabel();
        pic1_label.setBorder(PicBorder);
        pic1_label.setIcon(pic1);
        pic1_label.setBounds(40,110,100,100);
        pic1_label.addMouseListener(this);

        pic2_label = new JLabel();
        pic2_label.setBorder(PicBorder);
        pic2_label.setIcon(pic2);
        pic2_label.setBounds(160,110,100,100);
        pic2_label.addMouseListener(this);

        pic3_label = new JLabel();
        pic3_label.setBorder(PicBorder);
        pic3_label.setIcon(pic3);
        pic3_label.setBounds(280,110,100,100);
        pic3_label.addMouseListener(this);

        pic4_label = new JLabel();
        pic4_label.setBorder(PicBorder);
        pic4_label.setIcon(pic4);
        pic4_label.setBounds(400,110,100,100);
        pic4_label.addMouseListener(this);

        pic5_label = new JLabel();
        pic5_label.setBorder(PicBorder);
        pic5_label.setIcon(pic5);
        pic5_label.setBounds(40,240,100,100);
        pic5_label.addMouseListener(this);

        pic6_label = new JLabel();
        pic6_label.setBorder(PicBorder);
        pic6_label.setIcon(pic6);
        pic6_label.setBounds(160,240,100,100);
        pic6_label.addMouseListener(this);

        pic7_label = new JLabel();
        pic7_label.setBorder(PicBorder);
        pic7_label.setIcon(pic7);
        pic7_label.setBounds(280,240,100,100);
        pic7_label.addMouseListener(this);

        pic8_label = new JLabel();
        pic8_label.setBorder(PicBorder);
        pic8_label.setIcon(pic8);
        pic8_label.setBounds(400,240,100,100);
        pic8_label.addMouseListener(this);

        MainMenu_label = new JLabel("Choose Picture");
        MainMenu_label.setBounds(140,50,300,50);
        MainMenu_label.setForeground(Color.BLACK);
        MainMenu_label.setFont(new Font("Calibri", Font.BOLD,40));

        // Styling Frame
        this.setTitle("MainMenu");
        this.setSize(550,500);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(0x5646FF));
        this.setLayout(null);
        this.setLocation(350,100);
        this.add(MainMenu_label);
        this.add(pic1_label);this.add(pic2_label);
        this.add(pic3_label);this.add(pic4_label);
        this.add(pic5_label);this.add(pic6_label);
        this.add(pic7_label);this.add(pic8_label);
        this.add(LeaderBoard);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == LeaderBoard)
            new LeaderBoard(tempP);
        //For Image Number 1
        if(e.getSource() == pic1_label)
            new Puzzle(tempP,1);
        //For Image Number 2
        else if(e.getSource() == pic2_label)
            new Puzzle(tempP,2);
        //For Image Number 3
        else if(e.getSource() == pic3_label)
            new Puzzle(tempP,3);
        //For Image Number 4
        else if(e.getSource() == pic4_label)
            new Puzzle(tempP,4);
        //For Image Number 5
        else if(e.getSource() == pic5_label)
            new Puzzle(tempP,5);
        //For Image Number 6
        else if(e.getSource() == pic6_label)
            new Puzzle(tempP,6);
        //For Image Number 7
        else if(e.getSource() == pic7_label)
            new Puzzle(tempP,7);
        //For Image Number 8
        else if(e.getSource() == pic8_label)
           new Puzzle(tempP,8);

            this.dispose();

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

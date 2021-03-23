package com.company;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.border.Border;
import java.util.Scanner;
import java.io.FileWriter;
import java.awt.event.ActionListener;

public class Register extends JFrame implements ActionListener{

    private Image image;
    private File playerData = new File("playerData.txt"),
                 u_name     = new File("username.txt");
    private JTextField user;
    private JPasswordField pass;
    private JLabel Username;
    private JLabel Password;
    private JLabel RegWord;
    private JButton RegisterButton;
    private Border border;
    private Font RegisterFont;
    private Color FontColor;


    public boolean checkUserData(String Username,File f) throws FileNotFoundException {
        try(Scanner inputStream = new Scanner(f)){
            while(inputStream.hasNextLine()){
                if(Username.equals(inputStream.nextLine()) )
                    return false;
            }
        }
                    return true;
    }

    public void createFile(File f1,File f2) throws IOException {
        try{
            f1.createNewFile();
            f2.createNewFile();
        } catch (Exception e) {
            System.out.println("Error From Type " + e);
        }
    }

    public Register(){
        RegWord = new JLabel("Registration");
        RegWord.setBounds(100,25,200,50);
        RegWord.setFont(new Font("Calibri",Font.BOLD,35));
        RegWord.setForeground(Color.BLACK);


        image = Toolkit.getDefaultToolkit().getImage("F:\\PicPuzzle\\Images\\PuzzleIcon.png");
        this.setIconImage(image);


        try{
            createFile(playerData,u_name);
        } catch (Exception e)
        {
            System.out.println("Error!!" + e);
        }

        FontColor = Color.black;
        RegisterFont = new Font("Calibri",Font.BOLD,18);
        border = BorderFactory.createLineBorder(Color.GRAY,2,false);

        //Declare & Styling Labels
        Username = new JLabel("Username");
        Username.setFont(RegisterFont);
        Username.setForeground(FontColor);
        Username.setBounds(40+70,40+50,100,20);

        Password = new JLabel("Password");
        Password.setBounds(40+70,100+50,100,20);
        Password.setForeground(FontColor);
        Password.setFont(RegisterFont);


        //Declare Fields
        user = new JTextField();
        user.setBounds(40+70,65+50,200,28);


        pass = new JPasswordField();
        pass.setBounds(40+70,125+50,200,28);



        //Declare Buttons

        RegisterButton = new JButton("Register");
        RegisterButton.setBounds(40+70,170+50,100,25);
        RegisterButton.addActionListener(this);


        //Styling Frame
        this.setTitle("Register Form");
        this.setSize(450,390);
        this.setLocation(350,150);
        this.getContentPane().setBackground(new Color(0x5646FF));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.add(RegWord);
        this.add(Username);this.add(user);
        this.add(Password);this.add(pass);
        this.add(RegisterButton);
        this.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == RegisterButton) {
            try {
                if(checkUserData(user.getText(),u_name)) {
                    try(FileWriter myPen = new FileWriter(u_name.getAbsolutePath(),true)) {
                        myPen.append(user.getText() + "\n");
                        new Login();
                    } catch(Exception e1) {
                        System.out.println("Error " + e);
                    }
                    try(FileWriter myPen = new FileWriter(playerData.getAbsolutePath(),true)){
                        String pas = new String(pass.getPassword());
                        myPen.append(user.getText() + pas + "\n");
                        this.dispose();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }  else {
                        JOptionPane.showMessageDialog(this,"Enter Another Username");
                    }
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        }
    }
}

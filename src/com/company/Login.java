package com.company;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import javax.swing.border.Border;
import java.util.Scanner;
import java.awt.event.ActionListener;




public class Login extends JFrame implements ActionListener{

    private Image image;
    private File  file = new File("playerData.txt");
    private JTextField user;
    private JPasswordField pass;
    private JLabel Username;
    private JLabel Password;
    private JButton RegisterButton;
    private JButton LoginButton;
    private Border border;
    private Font LoginFont;
    private JLabel LogWord;



    public boolean checkUserData(String Username,File f) throws FileNotFoundException {
        try(Scanner inputStream = new Scanner(f)){
            while(inputStream.hasNextLine()){
                if(Username.equals(inputStream.nextLine()) ) {
                    return true;
                }
            }
        }
        return false;
    }



    public Login(){

        image = Toolkit.getDefaultToolkit().getImage("F:\\PicPuzzle\\Images\\PuzzleIcon.png");
        this.setIconImage(image);

        LoginFont = new Font("Calibri",Font.BOLD,18);

        border = BorderFactory.createLineBorder(Color.GRAY,2,false);

        LogWord = new JLabel("Login");
        LogWord.setBounds(150,25,100,50);
        LogWord.setFont(new Font("Calibri",Font.BOLD,40));
        LogWord.setForeground(Color.BLACK);

        //Declare & Styling Labels
        Username = new JLabel("Username");
        Username.setFont(LoginFont);
        Username.setForeground(Color.BLACK);
        Username.setBounds(40+70,40+48,100,20);

        Password = new JLabel("Password");
        Password.setBounds(40+70,100+48,100,20);
        Password.setForeground(Color.BLACK);
        Password.setFont(LoginFont);


        //Declare Fields
        user = new JTextField();
        user.setBounds(40+70,65+48,200,28);


        pass = new JPasswordField();
        pass.setBounds(40+70,125+48,200,28);


        //Declare Buttons
        LoginButton = new JButton("Login");
        LoginButton.setBounds(40+70,170+48,80,25);
        LoginButton.addActionListener(this);

        RegisterButton = new JButton("Register");
        RegisterButton.setBounds(140+70,170+48,100,25);
        RegisterButton.addActionListener(this);




        //Styling Frame
        this.setTitle("Login Form");
        this.setSize(450,350);
        this.setLocation(350,150);
        this.getContentPane().setBackground(new Color(0x5646FF));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.add(LogWord);
        this.add(Username);this.add(user);
        this.add(Password);this.add(pass);
        this.add(LoginButton);this.add(RegisterButton);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == LoginButton) {
            String data = new String(pass.getPassword());
                try {
                    String p =  user.getText() + data ;
                    if (checkUserData(p,file)) {
                        new MainMenu(new Player(user.getText(),data));
                        this.dispose();
                    }
                    else {
                        JOptionPane.showMessageDialog(this,"Wrong Data");
                        this.user.setText("");
                        this.pass.setText("");
                    }
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
            }

        } else if(e.getSource() == RegisterButton){
            this.dispose();
            new Register();
        }
    }
}


package com.company;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

public class LeaderBoard extends JFrame implements ActionListener{

    private File file = new File("F:\\PicPuzzle\\PlayerRecord.txt");
    private Image image;
    private DefaultTableModel tableModel;
    private JScrollPane scPane;
    private JTable playerDataTable;
    private JLabel LeaderBoard;
    private String[] MainDataCol;
    private JButton arrow;
    private Player PTemp;

    public void readDataToTable() {
        int counter = 0;
        Object[] data = new Object[3];
        try(Scanner input = new Scanner(file)) {
            while(input.hasNextLine())
            {
                data[counter] = input.nextLine();
                counter++;
                if(counter == 3) {
                    tableModel.addRow(data);
                    counter = 0;
                }

            }
        } catch (Exception e) {
            System.out.println("Error Happened : " + e);
        }}

    public LeaderBoard() {}
    public LeaderBoard(Player P) {

        image = Toolkit.getDefaultToolkit().getImage("F:\\PicPuzzle\\Images\\PuzzleIcon.png");
        this.setIconImage(image);


        PTemp = new Player(P.getUsername(),P.getPassword());
        MainDataCol = new String[]{"PlayerName","Score","Time"};
        LeaderBoard = new JLabel("LeaderBoard");
        LeaderBoard.setBounds(150,50,300,50);
        LeaderBoard.setForeground(Color.BLACK);
        LeaderBoard.setFont(new Font("Calibri", Font.BOLD,40));

        arrow = new JButton("←");
        arrow.setBounds(0,0,66,30);
        arrow.setForeground(Color.BLACK);
        arrow.setFont(new Font("",Font.BOLD,22));
        arrow.addActionListener(this);


        tableModel = new DefaultTableModel(MainDataCol,0);
        playerDataTable = new JTable(tableModel);
        scPane = new JScrollPane(playerDataTable);
        scPane.setBounds(100,120,350,250);
        playerDataTable.setEnabled(false);

        readDataToTable();

        this.setTitle("LeaderBoard");
        this.setSize(550,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(0x5646FF));
        this.setLocation(350,100);
        this.setLayout(null);
        this.setResizable(false);
        this.add(LeaderBoard);
        this.add(scPane);this.add(arrow);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == arrow) {
            this.dispose();
            new MainMenu(PTemp);
        }
    }
}

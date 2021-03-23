package com.company;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;
import java.io.File;
import java.io.FileWriter;


public class Puzzle extends JFrame implements MouseListener {
    private Player tempP1;
    private Image image;
    private int finishTime;
    static Timer timer;
    static TimerTask task;
    private ImageIcon pic1;
    private ImageIcon pic2;
    private ImageIcon pic3;private ImageIcon pic4;
    private ImageIcon pic5;private ImageIcon pic6;
    private ImageIcon pic7;private ImageIcon pic8;
    private ImageIcon pic9;

    private JPanel puzzleContainer;
    private JLabel picPart1;private JLabel picPart2;
    private JLabel picPart3;private JLabel picPart4;
    private JLabel picPart5;private JLabel picPart6;
    private JLabel picPart7;private JLabel picPart8;
    private JLabel picPart9;

    private final int[] sizePicPart1 = {2,2 ,166,166}, // SIZE AND POSITION OF SQUARES
            sizePicPart2 = {170,2,166,166},
            sizePicPart3 = {338,2,166,166},
            sizePicPart4 = {2,170,166,166},
            sizePicPart5 = {170,170,166,166},
            sizePicPart6 = {338,170,166,166},
            sizePicPart7 = {2,338,166,166},
            sizePicPart8 = {170,338,166,166},
            sizePicPart9 = {338,338,166,166};

    private JLabel Score;			private JLabel TimerLabel;
    private JLabel playerName;
    private JLabel requiredPhoto;
    private JSeparator separatorH;
    private Border border;


    int i1 = 0;	int i2 = 0;	int i3 = 0;	int i4 = 0;
    int i5 = 0;	int i6 = 0;	int i7 = 0;	int i8 = 0;
    int i9 = 0;


    private File playerRecord = new File("F:\\PicPuzzle\\PlayerRecord.txt");

    public void WriteDataToFile(Player P) {
        try(FileWriter myPen = new FileWriter(playerRecord.getAbsolutePath(),true)){
            myPen.append(P.getUsername() + "\n" + playerScore + "\n" + finishTime + "\n");
        } catch (Exception e) {
            System.out.println("File Not Found!");
        }
    }

    public void setTheBounds(JLabel picPart,int[] size) {
        picPart.setBounds(size[0],size[1],size[2],size[3]);
    }

    int x = 0;

    int checkWin = 0;

    public int checkWin() { // Checks Every square in its position
        if((int) picPart1.getBounds().getX() == 2   && (int) picPart1.getBounds().getY() == 2) {checkWin++;}
        if((int) picPart2.getBounds().getX() == 170 && (int) picPart2.getBounds().getY() == 2) {checkWin++;}
        if((int) picPart3.getBounds().getX() == 338 && (int) picPart3.getBounds().getY() == 2) {checkWin++;}
        if((int) picPart4.getBounds().getX() == 2   && (int) picPart4.getBounds().getY() == 170) {checkWin++;}
        if((int) picPart5.getBounds().getX() == 170 && (int) picPart5.getBounds().getY() == 170) {checkWin++;}
        if((int) picPart6.getBounds().getX() == 338 && (int) picPart6.getBounds().getY() == 170) {checkWin++;}
        if((int) picPart7.getBounds().getX() == 2   && (int) picPart7.getBounds().getY() == 338) {checkWin++;}
        if((int) picPart8.getBounds().getX() == 170 && (int) picPart8.getBounds().getY() == 338) {checkWin++;}
        if((int) picPart9.getBounds().getX() == 338 && (int) picPart9.getBounds().getY() == 338) {checkWin++;}
        if(checkWin == 9) {
            WriteDataToFile(tempP1);
            resetBorder();
            timer.cancel();
            int y = JOptionPane.showConfirmDialog(this, "CONGRATS!!  Would You like To Play Again");
            if (y == JOptionPane.YES_OPTION)
                new MainMenu(tempP1);
            else if (y == JOptionPane.NO_OPTION);
                this.dispose();
            return 1;
        }
        else
            return 0;
    }

    public void Swap(JLabel B1,JLabel B2) { // Checks the 2 chosen squares are positioned next to themselves
        int testerX = (int) (B1.getBounds().getX() - B2.getBounds().getX());
        if(testerX < 0) {testerX*=-1;}

        int testerY = (int) (B2.getBounds().getY() - B1.getBounds().getY());
        if(testerY < 0) {testerY*=-1;}

        int tempb1X 		= (int) B1.getBounds().getX();
        int tempb1Y 		= (int) B1.getBounds().getY();
        int tempb1Height 	= (int) B1.getBounds().getHeight();
        int tempb1Width 	= (int) B1.getBounds().getWidth();

        int tempb2X 	 = (int) B2.getBounds().getX();
        int tempb2Y 	 = (int) B2.getBounds().getY();
        int tempb2Height = (int) B2.getBounds().getHeight();
        int tempb2Width  = (int) B2.getBounds().getWidth();
        //X mayber 166 167
        if( ( testerX == 168 && testerY == 0) || ( testerY == 168 && testerX == 0) ) {
            B2.setBounds(tempb1X,tempb1Y,tempb1Height,tempb1Width);
            B1.setBounds(tempb2X,tempb2Y,tempb2Height,tempb2Width);
        }
        else {
            System.out.print("X : " + testerX + " | " + "Y : " + testerY);

        }
        testerX = 0; testerY = 0;x = 0;
        int X = checkWin();
        if(X == 1) {
            this.dispose();
            timer.cancel();
        }
        else
            checkWin = 0;

        B1.setBorder(null);
        B2.setBorder(null);
    }

    public int swapController = 0;

    //Declaring Components
    private ImageIcon Icon;
    private ImageIcon ic;


    private int playerScore;


    //===================================================
    public void resetBorder() {
        picPart1.setBorder(null);
        picPart2.setBorder(null);
        picPart3.setBorder(null);
        picPart4.setBorder(null);
        picPart5.setBorder(null);
        picPart6.setBorder(null);
        picPart7.setBorder(null);
        picPart8.setBorder(null);
        picPart9.setBorder(null);
    }

    //===================================================
    //Display Random Pictures RANDOMIZATION
    public void displayPicturesRandomly() {
        int Sum = 0,index = 1;
        while( Sum!=45 ){
            int randomNumber = (int) (Math.random()*10);
            if(randomNumber == 9 && i9 == 0) {
                if(index == 1) {setTheBounds(picPart9,sizePicPart1);puzzleContainer.add(picPart9);index++;}
                else if(index == 2) {setTheBounds(picPart9,sizePicPart2);puzzleContainer.add(picPart9);index++;}
                else if(index == 3) {setTheBounds(picPart9,sizePicPart3);puzzleContainer.add(picPart9);index++;}
                else if(index == 4) {setTheBounds(picPart9,sizePicPart4);puzzleContainer.add(picPart9);index++;}
                else if(index == 5) {setTheBounds(picPart9,sizePicPart5);puzzleContainer.add(picPart9);index++;}
                else if(index == 6) {setTheBounds(picPart9,sizePicPart6);puzzleContainer.add(picPart9);index++;}
                else if(index == 7) {setTheBounds(picPart9,sizePicPart7);puzzleContainer.add(picPart9);index++;}
                else if(index == 8) {setTheBounds(picPart9,sizePicPart8);puzzleContainer.add(picPart9);index++;}
                else if(index == 9) {setTheBounds(picPart9,sizePicPart9);puzzleContainer.add(picPart9);index++;}
                i9 = 9;
                Sum = i9 + Sum;
            }
//====================================================================================
            else if(randomNumber == 2 && i2 == 0) {
                if(index ==  1) {setTheBounds(picPart2,sizePicPart1);puzzleContainer.add(picPart2);index++;}
                else if(index ==  2) {setTheBounds(picPart2,sizePicPart2);puzzleContainer.add(picPart2);index++;}
                else if(index ==  3) {setTheBounds(picPart2,sizePicPart3);puzzleContainer.add(picPart2);index++;}
                else if(index ==  4) {setTheBounds(picPart2,sizePicPart4);puzzleContainer.add(picPart2);index++;}
                else if(index ==  5) {setTheBounds(picPart2,sizePicPart5);puzzleContainer.add(picPart2);index++;}
                else if(index ==  6) {setTheBounds(picPart2,sizePicPart6);puzzleContainer.add(picPart2);index++;}
                else if(index ==  7) {setTheBounds(picPart2,sizePicPart7);puzzleContainer.add(picPart2);index++;}
                else if(index ==  8) {setTheBounds(picPart2,sizePicPart8);puzzleContainer.add(picPart2);index++;}
                else if(index ==  9) {setTheBounds(picPart2,sizePicPart9);puzzleContainer.add(picPart2);index++;}
                i2 = 2;
                Sum = i2 + Sum;
            }
//====================================================================================
            else if(randomNumber == 3 && i3 == 0) {
                if(index ==  1) {setTheBounds(picPart3,sizePicPart1);puzzleContainer.add(picPart3);index++;}
                else if(index ==  2) {setTheBounds(picPart3,sizePicPart2);puzzleContainer.add(picPart3);index++;}
                else if(index ==  3) {setTheBounds(picPart3,sizePicPart3);puzzleContainer.add(picPart3);index++;}
                else if(index ==  4) {setTheBounds(picPart3,sizePicPart4);puzzleContainer.add(picPart3);index++;}
                else if(index ==  5) {setTheBounds(picPart3,sizePicPart5);puzzleContainer.add(picPart3);index++;}
                else if(index ==  6) {setTheBounds(picPart3,sizePicPart6);puzzleContainer.add(picPart3);index++;}
                else if(index ==  7) {setTheBounds(picPart3,sizePicPart7);puzzleContainer.add(picPart3);index++;}
                else if(index ==  8) {setTheBounds(picPart3,sizePicPart8);puzzleContainer.add(picPart3);index++;}
                else if(index ==  9) {setTheBounds(picPart3,sizePicPart9);puzzleContainer.add(picPart3);index++;}
                i3 = 3;
                Sum = i3 + Sum;
            }
//====================================================================================
            else if(randomNumber == 4 && i4 == 0) {
                if(index ==  1) {setTheBounds(picPart4,sizePicPart1);puzzleContainer.add(picPart4);index++;}
                else if(index ==  2) {setTheBounds(picPart4,sizePicPart2);puzzleContainer.add(picPart4);index++;}
                else if(index ==  3) {setTheBounds(picPart4,sizePicPart3);puzzleContainer.add(picPart4);index++;}
                else if(index ==  4) {setTheBounds(picPart4,sizePicPart4);puzzleContainer.add(picPart4);index++;}
                else if(index ==  5) {setTheBounds(picPart4,sizePicPart5);puzzleContainer.add(picPart4);index++;}
                else if(index ==  6) {setTheBounds(picPart4,sizePicPart6);puzzleContainer.add(picPart4);index++;}
                else if(index ==  7) {setTheBounds(picPart4,sizePicPart7);puzzleContainer.add(picPart4);index++;}
                else if(index ==  8) {setTheBounds(picPart4,sizePicPart8);puzzleContainer.add(picPart4);index++;}
                else if(index ==  9) {setTheBounds(picPart4,sizePicPart9);puzzleContainer.add(picPart4);index++;}
                i4 = 4;
                Sum = i4 + Sum;
            }
//====================================================================================
            else if(randomNumber == 5 && i5 == 0) {
                if(index ==  1) {setTheBounds(picPart5,sizePicPart1);puzzleContainer.add(picPart5);index++;}
                else if(index ==  2) {setTheBounds(picPart5,sizePicPart2);puzzleContainer.add(picPart5);index++;}
                else if(index ==  3) {setTheBounds(picPart5,sizePicPart3);puzzleContainer.add(picPart5);index++;}
                else if(index ==  4) {setTheBounds(picPart5,sizePicPart4);puzzleContainer.add(picPart5);index++;}
                else if(index ==  5) {setTheBounds(picPart5,sizePicPart5);puzzleContainer.add(picPart5);index++;}
                else if(index ==  6) {setTheBounds(picPart5,sizePicPart6);puzzleContainer.add(picPart5);index++;}
                else if(index ==  7) {setTheBounds(picPart5,sizePicPart7);puzzleContainer.add(picPart5);index++;}
                else if(index ==  8) {setTheBounds(picPart5,sizePicPart8);puzzleContainer.add(picPart5);index++;}
                else if(index ==  9) {setTheBounds(picPart5,sizePicPart9);puzzleContainer.add(picPart5);index++;}
                i5 = 5;
                Sum = i5 + Sum;
            }
//====================================================================================
            else if(randomNumber == 6 && i6 == 0) {
                if(index ==  1) {setTheBounds(picPart6,sizePicPart1);puzzleContainer.add(picPart6);index++;}
                else if(index ==  2) {setTheBounds(picPart6,sizePicPart2);puzzleContainer.add(picPart6);index++;}
                else if(index ==  3) {setTheBounds(picPart6,sizePicPart3);puzzleContainer.add(picPart6);index++;}
                else if(index ==  4) {setTheBounds(picPart6,sizePicPart4);puzzleContainer.add(picPart6);index++;}
                else if(index ==  5) {setTheBounds(picPart6,sizePicPart5);puzzleContainer.add(picPart6);index++;}
                else if(index ==  6) {setTheBounds(picPart6,sizePicPart6);puzzleContainer.add(picPart6);index++;}
                else if(index ==  7) {setTheBounds(picPart6,sizePicPart7);puzzleContainer.add(picPart6);index++;}
                else if(index ==  8) {setTheBounds(picPart6,sizePicPart8);puzzleContainer.add(picPart6);index++;}
                else if(index ==  9) {setTheBounds(picPart6,sizePicPart9);puzzleContainer.add(picPart6);index++;}
                i6 = 6;
                Sum = i6 + Sum;
            }
//====================================================================================
            else if(randomNumber == 7 && i7 == 0) {
                if(index ==  1) {setTheBounds(picPart7,sizePicPart1);puzzleContainer.add(picPart7);index++;}
                else if(index ==  2) {setTheBounds(picPart7,sizePicPart2);puzzleContainer.add(picPart7);index++;}
                else if(index ==  3) {setTheBounds(picPart7,sizePicPart3);puzzleContainer.add(picPart7);index++;}
                else if(index ==  4) {setTheBounds(picPart7,sizePicPart4);puzzleContainer.add(picPart7);index++;}
                else if(index ==  5) {setTheBounds(picPart7,sizePicPart5);puzzleContainer.add(picPart7);index++;}
                else if(index ==  6) {setTheBounds(picPart7,sizePicPart6);puzzleContainer.add(picPart7);index++;}
                else if(index ==  7) {setTheBounds(picPart7,sizePicPart7);puzzleContainer.add(picPart7);index++;}
                else if(index ==  8) {setTheBounds(picPart7,sizePicPart8);puzzleContainer.add(picPart7);index++;}
                else if(index ==  9) {setTheBounds(picPart7,sizePicPart9);puzzleContainer.add(picPart7);index++;}
                i7 = 7;
                Sum = i7 + Sum;
            }
//====================================================================================
            else if(randomNumber == 8 && i8 == 0) {
                if(index ==  1) {setTheBounds(picPart8,sizePicPart1);puzzleContainer.add(picPart8);index++;}
                else if(index ==  2) {setTheBounds(picPart8,sizePicPart2);puzzleContainer.add(picPart8);index++;}
                else if(index ==  3) {setTheBounds(picPart8,sizePicPart3);puzzleContainer.add(picPart8);index++;}
                else if(index ==  4) {setTheBounds(picPart8,sizePicPart4);puzzleContainer.add(picPart8);index++;}
                else if(index ==  5) {setTheBounds(picPart8,sizePicPart5);puzzleContainer.add(picPart8);index++;}
                else if(index ==  6) {setTheBounds(picPart8,sizePicPart6);puzzleContainer.add(picPart8);index++;}
                else if(index ==  7) {setTheBounds(picPart8,sizePicPart7);puzzleContainer.add(picPart8);index++;}
                else if(index ==  8) {setTheBounds(picPart8,sizePicPart8);puzzleContainer.add(picPart8);index++;}
                else if(index ==  9) {setTheBounds(picPart8,sizePicPart9);puzzleContainer.add(picPart8);index++;}
                i8 = 8;
                Sum = i8 + Sum;
            }
//====================================================================================
            else if(randomNumber == 1 && i1 == 0) {
                if(index ==  1) {setTheBounds(picPart1,sizePicPart1);puzzleContainer.add(picPart1);index++;}
                else if(index ==  2) {setTheBounds(picPart1,sizePicPart2);puzzleContainer.add(picPart1);index++;}
                else if(index ==  3) {setTheBounds(picPart1,sizePicPart3);puzzleContainer.add(picPart1);index++;}
                else if(index ==  4) {setTheBounds(picPart1,sizePicPart4);puzzleContainer.add(picPart1);index++;}
                else if(index ==  5) {setTheBounds(picPart1,sizePicPart5);puzzleContainer.add(picPart1);index++;}
                else if(index ==  6) {setTheBounds(picPart1,sizePicPart6);puzzleContainer.add(picPart1);index++;}
                else if(index ==  7) {setTheBounds(picPart1,sizePicPart7);puzzleContainer.add(picPart1);index++;}
                else if(index ==  8) {setTheBounds(picPart1,sizePicPart8);puzzleContainer.add(picPart1);index++;}
                else if(index ==  9) {setTheBounds(picPart1,sizePicPart9);puzzleContainer.add(picPart1);index++;}
                i1 = 1;
                Sum = i1 + Sum;
            }

        }
        System.out.print(index);

    }
    //===================================================

    //Insert Chosen Photo TEMPLATE PHOTO
    public void insertPhoto(int index) {
        requiredPhoto = new JLabel();
        requiredPhoto.setBounds(220,1,78,78);

        if(index == 1) {
            ic = new ImageIcon("F:\\PicPuzzle\\RequiredPhoto\\Pho1.jpg");
            requiredPhoto.setIcon(ic);
            pic1 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho1\\image_part_001.jpg");
            pic2 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho1\\image_part_002.jpg");
            pic3 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho1\\image_part_003.jpg");
            pic4 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho1\\image_part_004.jpg");
            pic5 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho1\\image_part_005.jpg");
            pic6 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho1\\image_part_006.jpg");
            pic7 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho1\\image_part_007.jpg");
            pic8 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho1\\image_part_008.jpg");
            pic9 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho1\\image_part_009.jpg");
        }
        else if (index == 2) {
            ic = new ImageIcon("F:\\PicPuzzle\\RequiredPhoto\\Pho2.jpg");
            requiredPhoto.setIcon(ic);

            pic1 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho2\\image_part_001.jpg");
            pic2 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho2\\image_part_002.jpg");
            pic3 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho2\\image_part_003.jpg");
            pic4 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho2\\image_part_004.jpg");
            pic5 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho2\\image_part_005.jpg");
            pic6 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho2\\image_part_006.jpg");
            pic7 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho2\\image_part_007.jpg");
            pic8 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho2\\image_part_008.jpg");
            pic9 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho2\\image_part_009.jpg");
        }
        else if (index == 3) {
            ic = new ImageIcon("F:\\PicPuzzle\\RequiredPhoto\\Pho3.jpg");
            requiredPhoto.setIcon(ic);

            pic1 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho3\\image_part_001.jpg");
            pic2 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho3\\image_part_002.jpg");
            pic3 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho3\\image_part_003.jpg");
            pic4 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho3\\image_part_004.jpg");
            pic5 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho3\\image_part_005.jpg");
            pic6 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho3\\image_part_006.jpg");
            pic7 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho3\\image_part_007.jpg");
            pic8 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho3\\image_part_008.jpg");
            pic9 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho3\\image_part_009.jpg");
        }
        else if (index == 4) {
            ic = new ImageIcon("F:\\PicPuzzle\\RequiredPhoto\\Pho4.jpg");
            requiredPhoto.setIcon(ic);

            pic1 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho4\\image_part_001.jpg");
            pic2 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho4\\image_part_002.jpg");
            pic3 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho4\\image_part_003.jpg");
            pic4 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho4\\image_part_004.jpg");
            pic5 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho4\\image_part_005.jpg");
            pic6 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho4\\image_part_006.jpg");
            pic7 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho4\\image_part_007.jpg");
            pic8 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho4\\image_part_008.jpg");
            pic9 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho4\\image_part_009.jpg");

            }
        else if (index == 5) {
            ic = new ImageIcon("F:\\PicPuzzle\\RequiredPhoto\\Pho5.jpg");
            requiredPhoto.setIcon(ic);

            pic1 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho5\\image_part_001.jpg");
            pic2 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho5\\image_part_002.jpg");
            pic3 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho5\\image_part_003.jpg");
            pic4 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho5\\image_part_004.jpg");
            pic5 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho5\\image_part_005.jpg");
            pic6 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho5\\image_part_006.jpg");
            pic7 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho5\\image_part_007.jpg");
            pic8 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho5\\image_part_008.jpg");
            pic9 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho5\\image_part_009.jpg");
        }
        else if (index == 6) {
            ic = new ImageIcon("F:\\PicPuzzle\\RequiredPhoto\\Pho6.jpg");
            requiredPhoto.setIcon(ic);

            pic1 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho6\\image_part_001.jpg");
            pic2 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho6\\image_part_002.jpg");
            pic3 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho6\\image_part_003.jpg");
            pic4 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho6\\image_part_004.jpg");
            pic5 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho6\\image_part_005.jpg");
            pic6 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho6\\image_part_006.jpg");
            pic7 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho6\\image_part_007.jpg");
            pic8 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho6\\image_part_008.jpg");
            pic9 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho6\\image_part_009.jpg");
        }
        else if (index == 7) {
            ic = new ImageIcon("F:\\PicPuzzle\\RequiredPhoto\\Pho7.jpg");
            requiredPhoto.setIcon(ic);

            pic1 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho7\\image_part_001.jpg");
            pic2 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho7\\image_part_002.jpg");
            pic3 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho7\\image_part_003.jpg");
            pic4 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho7\\image_part_004.jpg");
            pic5 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho7\\image_part_005.jpg");
            pic6 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho7\\image_part_006.jpg");
            pic7 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho7\\image_part_007.jpg");
            pic8 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho7\\image_part_008.jpg");
            pic9 = new ImageIcon("F:\\PicPuzzle\\PuzzleImages\\Pho7\\image_part_009.jpg");

        }
        else if (index == 8) {
            ic = new ImageIcon("E:\\PicPuzzle\\RequiredPhoto\\Pho8.jpg");
            requiredPhoto.setIcon(ic);

            pic1 = new ImageIcon("E:\\PicPuzzle\\PuzzleImages\\Pho8\\image_part_001.jpg");
            pic2 = new ImageIcon("E:\\PicPuzzle\\PuzzleImages\\Pho8\\image_part_002.jpg");
            pic3 = new ImageIcon("E:\\PicPuzzle\\PuzzleImages\\Pho8\\image_part_003.jpg");
            pic4 = new ImageIcon("E:\\PicPuzzle\\PuzzleImages\\Pho8\\image_part_004.jpg");
            pic5 = new ImageIcon("E:\\PicPuzzle\\PuzzleImages\\Pho8\\image_part_005.jpg");
            pic6 = new ImageIcon("E:\\PicPuzzle\\PuzzleImages\\Pho8\\image_part_006.jpg");
            pic7 = new ImageIcon("E:\\PicPuzzle\\PuzzleImages\\Pho8\\image_part_007.jpg");
            pic8 = new ImageIcon("E:\\PicPuzzle\\PuzzleImages\\Pho8\\image_part_008.jpg");
            pic9 = new ImageIcon("E:\\PicPuzzle\\PuzzleImages\\Pho8\\image_part_009.jpg");
        }
    }

    //Constructor
    public Puzzle() {}
    public Puzzle(Player P,int N) {
        tempP1 = new Player(P.getUsername(),P.getPassword());

        image = Toolkit.getDefaultToolkit().getImage("E:\\PicPuzzle\\Images\\PuzzleIcon.png");
        this.setIconImage(image);

        border   = BorderFactory.createLineBorder(Color.black, 3);
        Score = new JLabel("Score : " + playerScore);


        insertPhoto(N);


        TimerLabel = new JLabel("Timer: 00:00");
        TimerLabel.setBounds(10,1,200,40);
        TimerLabel.setForeground(Color.black);
        TimerLabel.setFont(new Font("",Font.BOLD,24));

        Score = new JLabel("Score : 00");
        Score.setBounds(10,40,200,40);
        Score.setFont(new Font("",Font.BOLD,24));
        Score.setForeground(Color.black);

        playerName = new JLabel("Player : " + tempP1.getUsername());
        playerName.setBounds(430,1,350,40);
        playerName.setFont(new Font("",Font.BOLD,24));
        playerName.setForeground(Color.black);


        separatorH = new JSeparator();
        separatorH.setOrientation(SwingConstants.HORIZONTAL);
        separatorH.setBounds(0,80,700,3);
        //Defining Puzzle Blocks Container Specifications



        //Declaring Block 1
        picPart1 = new JLabel();
        //Style Block 2
        picPart2 = new JLabel();
        //Style Block 3
        picPart3 = new JLabel();
        //Style Block 4
        picPart4 = new JLabel();
        //Style Block 5
        picPart5 = new JLabel();
        //Style Block 6
        picPart6 = new JLabel();
        //Style Block 7
        picPart7 = new JLabel();
        //Style Block 8
        picPart8 = new JLabel();
        //Style Block 9
        picPart9 = new JLabel();


        picPart1.setIcon(pic1);
        picPart2.setIcon(pic2);
        picPart3.setIcon(pic3);
        picPart4.setIcon(pic4);
        picPart5.setIcon(pic5);
        picPart6.setIcon(pic6);
        picPart7.setIcon(pic7);
        picPart8.setIcon(pic8);
        picPart9.setIcon(pic9);





        puzzleContainer = new JPanel();
        puzzleContainer.setBounds(95,95,506,506);
        puzzleContainer.setLayout(null);
        displayPicturesRandomly();

        picPart1.addMouseListener(this);
        picPart2.addMouseListener(this);
        picPart3.addMouseListener(this);
        picPart4.addMouseListener(this);
        picPart5.addMouseListener(this);
        picPart6.addMouseListener(this);
        picPart7.addMouseListener(this);
        picPart8.addMouseListener(this);
        picPart9.addMouseListener(this);

        // Frame Style
        this.setTitle("Puzzle Game");
        this.setSize(700,700);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(0x5646FF));
        this.setResizable(false);
        this.setLocation(300,20);

        this.add(TimerLabel);
        this.add(playerName);
        this.add(requiredPhoto);
        this.add(Score);
        this.add(separatorH);
        this.add(puzzleContainer);
        this.setVisible(true);


        timer = new Timer();
        playerScore = 30;
        task = new TimerTask(){
            int counter = 60;
            @Override
            public void run() {
                if(counter > 0){
                    finishTime = 60 - counter;
                    if (counter == 50 || counter == 40 || counter == 30 || counter == 20 || counter == 10)
                        playerScore-=5;
                    if(counter == 20)
                        TimerLabel.setForeground(Color.red);
                    TimerLabel.setText("Timer: " + counter);
                    Score.setText("Score: " + playerScore);
                    counter--;
                }
                else{
                    playerScore = 0;
                    timer.cancel();
                    WriteDataToFile(tempP1);
                    int y = JOptionPane.showConfirmDialog(null,"Oh! You Lost, Would You Play Again ?");
                    if(y == JOptionPane.YES_OPTION)
                            new MainMenu(tempP1);
                    else if(y == JOptionPane.NO_OPTION || y == JOptionPane.CANCEL_OPTION)
                            System.exit(0);
                }
            }
        };

        timer.scheduleAtFixedRate(task,4,750);
    }



    @Override
    public void mouseClicked(MouseEvent e) { // Actions of Swapping
        if(e.getSource() == picPart1) {picPart1.setBorder(border);x+=6;}
        if(e.getSource() == picPart2) {picPart2.setBorder(border);x+=2;}
        if(e.getSource() == picPart3) {picPart3.setBorder(border);x+=5;}
        if(e.getSource() == picPart4) {picPart4.setBorder(border);x+=50;}
        if(e.getSource() == picPart5) {picPart5.setBorder(border);x+=80;}
        if(e.getSource() == picPart6) {picPart6.setBorder(border);x+=90;}
        if(e.getSource() == picPart7) {picPart7.setBorder(border);x+=1100;}
        if(e.getSource() == picPart8) {picPart8.setBorder(border);x+=1200;}
        if(e.getSource() == picPart9) {picPart9.setBorder(border);x+=1301;}
        //PicPart1 Swaps

        if(x == 8)    {Swap(picPart1,picPart2);}
        if(x == 11)   {Swap(picPart1,picPart3);}
        if(x == 56)   {Swap(picPart1,picPart4);}
        if(x == 86)   {Swap(picPart1,picPart5);}
        if(x == 96)   {Swap(picPart1,picPart6);}
        if(x == 1106) {Swap(picPart1,picPart7);}
        if(x == 1206) {Swap(picPart1,picPart8);}
        if(x == 1307) {Swap(picPart1,picPart9);}

        //PicPart2 Swaps
        if(x == 7)    {Swap(picPart2,picPart3);}
        if(x == 52)   {Swap(picPart2,picPart4);}
        if(x == 82)   {Swap(picPart2,picPart5);}
        if(x == 92)   {Swap(picPart2,picPart6);}
        if(x == 1102) {Swap(picPart2,picPart7);}
        if(x == 1202) {Swap(picPart2,picPart8);}
        if(x == 1303) {Swap(picPart2,picPart9);}

        //PicPart3 Swaps
        if(x == 55)   {Swap(picPart3,picPart4);}
        if(x == 85)   {Swap(picPart3,picPart5);}
        if(x == 95)   {Swap(picPart3,picPart6);}
        if(x == 1105) {Swap(picPart3,picPart7);}
        if(x == 1205) {Swap(picPart3,picPart8);}
        if(x == 1306) {Swap(picPart3,picPart9);}

        //PicParts4 Swaps

        if(x == 130)  {Swap(picPart4,picPart5);}
        if(x == 140)  {Swap(picPart4,picPart6);}
        if(x == 1150) {Swap(picPart4,picPart7);}
        if(x == 1250) {Swap(picPart4,picPart8);}
        if(x == 1351) {Swap(picPart4,picPart9);}

        //PicParts5 Swaps

        if(x == 170)  {Swap(picPart5,picPart6);}
        if(x == 1180) {Swap(picPart5,picPart7);}
        if(x == 1280) {Swap(picPart5,picPart8);}
        if(x == 1381) {Swap(picPart5,picPart9);}

        //PicParts6 Swaps

        if(x == 1190) {Swap(picPart6,picPart7);}
        if(x == 1290) {Swap(picPart6,picPart8);}
        if(x == 1391) {Swap(picPart6,picPart9);}

        //PicParts7 Swaps

        if(x == 2300) {Swap(picPart7,picPart8);}
        if(x == 2401) {Swap(picPart7,picPart9);}

        //PicParts8 Swap

        if(x == 2501) {Swap(picPart8,picPart9);}

        if(x ==	12  || x== 4 || x == 10 || x == 100 ||
                x == 160 || x == 180 || x == 2200 || x == 2400 || x == 2602 )
        {
            picPart1.setBorder(null);
            picPart2.setBorder(null);
            picPart3.setBorder(null);
            picPart4.setBorder(null);
            picPart5.setBorder(null);
            picPart6.setBorder(null);
            picPart7.setBorder(null);
            picPart8.setBorder(null);
            picPart9.setBorder(null);
            x=0;
        }

     //   System.out.println(x+" | ");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}


import hsa.TextInputFile;
import hsa.TextOutputFile;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 348676487
 */
public class GameFrame extends javax.swing.JFrame {

    //Store all images for the vehicle theme
    Image truck = Toolkit.getDefaultToolkit().getImage("Truck.png");
    Image ship = Toolkit.getDefaultToolkit().getImage("Ship.png");
    Image heli = Toolkit.getDefaultToolkit().getImage("Heli.png");
    Image fireTruck = Toolkit.getDefaultToolkit().getImage("Fire Truck.png");
    Image construction = Toolkit.getDefaultToolkit().getImage("Construction.png");
    Image car = Toolkit.getDefaultToolkit().getImage("Car.png");
    Image bus = Toolkit.getDefaultToolkit().getImage("Bus.png");
    Image bicycle = Toolkit.getDefaultToolkit().getImage("Bicycle.png");

    //Store all images for the colors theme
    Image black = Toolkit.getDefaultToolkit().getImage("Black.png");
    Image blue = Toolkit.getDefaultToolkit().getImage("Blue.png");
    Image green = Toolkit.getDefaultToolkit().getImage("Green.png");
    Image orange = Toolkit.getDefaultToolkit().getImage("Orange.png");
    Image purple = Toolkit.getDefaultToolkit().getImage("Purple.png");
    Image red = Toolkit.getDefaultToolkit().getImage("Red.png");
    Image yellow = Toolkit.getDefaultToolkit().getImage("Yellow.png");
    Image turquoise = Toolkit.getDefaultToolkit().getImage("Turquoise.png");

    //Store images when cards match and when cards are not flipped
    Image correct = Toolkit.getDefaultToolkit().getImage("correct.png");
    Image question = Toolkit.getDefaultToolkit().getImage("Question.png");

    //Array to hold the cards in a theme
    Image[][] img = new Image[4][4];

    //Random for when assigning images to the array
    Random rndm = new Random();

    //Array for each pair of cards
    int[] asign = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8};

    //Integer to hold the random index for the array holding each pair of cards
    int index;

    //Index for first image selected in the array of cards
    int image1x;
    int image1y;

    //Index for second image selected in the array of cards
    int image2x;
    int image2y;

    //Integer to hold whether the first or second card in a pair is selected
    int choice = 1;

    //Index for first button
    int firstButton;

    //Index for second button
    int secondButton;

    //Integer to hold the number of moves taken
    int numberOfMoves = 0;

    //Boolean to hold true when a button is pressed in order to determine same button presses
    boolean[] sameButton = new boolean[16];

    //Array of all the buttons
    JButton[] buttons = new JButton[16];

    //Timer for one second
    Timer t1 = new Timer(1000, new TimerListener());

    //Boolean for when two cards match
    boolean match;

    //Boolean for when the buttons can be pressed after the delay
    boolean clickable = true;

    //Integer to hold the number of squares completed
    int squaresComplete = 0;

    //Boolean to hold the card theme from the main menu frame
    boolean card;

    //String to hold player name from main menu frame
    String playerName;

    //Integer to hold the amount the player ber from main menu frame
    int betAmount;

    //Integer to hold the amount of money available in the file
    int currentMoney;

    //Music class
    Music m = new Music();

    /**
     * Creates new form GameFrame
     */
    public GameFrame() {
        initComponents();
    }

    //Set beginning game components
    public GameFrame(String name, boolean cardtype, int bet) {
        initComponents();

        //Load file
        m.loadFile("click.mp3");

        //Disabled the yes, no, and proceed to highscore buttons
        jButton21.setEnabled(false);
        jButton22.setEnabled(false);
        jButton17.setEnabled(false);

        //Store players name, card theme, and bet from main menu
        playerName = name;
        card = cardtype;
        betAmount = bet;

        //Method for randomly assigning the cards in the theme to the card array
        assignImages();

        //Output zero number of moves
        jLabel1.setText("0");

        //Output the number of squaresComplete
        jLabel3.setText("Squares Complete: " + squaresComplete);

        //Store each card button into the array of buttons
        buttons[0] = jButton1;
        buttons[1] = jButton2;
        buttons[2] = jButton3;
        buttons[3] = jButton4;
        buttons[4] = jButton5;
        buttons[5] = jButton6;
        buttons[6] = jButton7;
        buttons[7] = jButton8;
        buttons[8] = jButton9;
        buttons[9] = jButton10;
        buttons[10] = jButton11;
        buttons[11] = jButton12;
        buttons[12] = jButton13;
        buttons[13] = jButton14;
        buttons[14] = jButton15;
        buttons[15] = jButton16;

        //Output the question image to act as the back of the card
        for (int a = 0; a < 16; a++) {
            //Output the question image as the button icon
            buttons[a].setIcon(new ImageIcon(question.getScaledInstance(80, 80, 80)));
        }
    }

    //Randomly assigning images for the choosen theme to the card array
    public void assignImages() {

        //Loop for assigning all elments in the array an image
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {

                //Generate a random index
                do {
                    index = rndm.nextInt(asign.length);
                } //Loop until the element is not zero
                while (asign[index] == 0);

                //Assign the truck or black
                if (asign[index] == 1) {
                    //Vehicle themed image
                    if (card == true) {
                        //Assign truck
                        img[y][x] = truck;
                    } //Colors themed image
                    else {
                        //Assign black
                        img[y][x] = black;
                    }
                } else if (asign[index] == 2) {
                    //Vehicle themed image
                    if (card == true) {
                        //Assign ship
                        img[y][x] = ship;
                    } //Colors themed image
                    else {
                        //Assign blue
                        img[y][x] = blue;
                    }
                } else if (asign[index] == 3) {
                    //Vehicle themed image
                    if (card == true) {
                        //Assign helicopter
                        img[y][x] = heli;
                    } //Colors themed image
                    else {
                        //Assign green
                        img[y][x] = green;
                    }
                } else if (asign[index] == 4) {
                    //Vehicle themed image
                    if (card == true) {
                        //Assign firetruck
                        img[y][x] = fireTruck;
                    } //Colors themed image
                    else {
                        //Assign orange
                        img[y][x] = orange;
                    }
                } else if (asign[index] == 5) {
                    //Vehicle themed image
                    if (card == true) {
                        //Assign construction vehicle
                        img[y][x] = construction;
                    } //Colors themed image
                    else {
                        //Assign purple
                        img[y][x] = purple;
                    }
                } else if (asign[index] == 6) {
                    //Vehicle themed image
                    if (card == true) {
                        //Assign car
                        img[y][x] = car;
                    } //Colors themed image
                    else {
                        //Assign red
                        img[y][x] = red;
                    }
                } else if (asign[index] == 7) {
                    //Vehicle themed image
                    if (card == true) {
                        //Assign bus
                        img[y][x] = bus;
                    } //Colors themed image
                    else {
                        //Assign yellow
                        img[y][x] = yellow;
                    }
                } else if (asign[index] == 8) {
                    //Vehicle themed image
                    if (card == true) {
                        //Assign bicycle
                        img[y][x] = bicycle;
                    } //Colors themed image
                    else {
                        //Assign turquoise
                        img[y][x] = turquoise;
                    }
                }

                //Set the element to zero so it can not be choosen again
                asign[index] = 0;
            }
        }
    }

    //Handling situations for whether the same button is pressed
    public void sameButton(int a, int b, int button) {

        //Play the click sound
        m.play();

        //Same button pressed
        if (sameButton[button - 1] == true) {
            jLabel2.setText("SAME BUTTON!");

            //Set the buttons icon back to the question mark
            buttons[button - 1].setIcon(new ImageIcon(question.getScaledInstance(80, 80, 80)));

            //Reset the back to first card selection
            choice = 1;

            //Reset the button back to not pressed
            sameButton[button - 1] = false;
        } //Not the same button pressed
        else if (sameButton[button - 1] == false) {
            //Set the button's appropriate icon
            buttons[button - 1].setIcon(drawingImage(a, b));

            //Set the button as pressed
            sameButton[button - 1] = true;

            //Determining whether two images match
            choosing(a, b, button);
        }

    }

    //Drawing the image as an icon on the button
    public Icon drawingImage(int x, int y) {
        return new ImageIcon(img[x][y].getScaledInstance(80, 80, 80));
    }

    //Determining when images match
    public void choosing(int a, int b, int button) {
        //First card selection
        if (choice == 1) {
            //Set indexes for first card
            image1x = a;
            image1y = b;

            //Add to the choice counter
            choice++;

            //Store which first button was pressed
            firstButton = button - 1;

            jLabel2.setText("SELECT A BUTTON!");

        } //Second card selection
        else if (choice == 2) {

            //Rest all buttons to unpressed
            for (int l = 0; l < sameButton.length; l++) {
                sameButton[l] = false;
            }

            //Add to the number of moves
            numberOfMoves++;

            //Output the number of moves
            jLabel1.setText("" + numberOfMoves);

            //Set indexes for second card
            image2x = a;
            image2y = b;

            //Store which second button was pressed
            secondButton = button - 1;

            //Determine if the first card matches the second card
            if (img[image1x][image1y].equals(img[image2x][image2y])) {

                jLabel2.setText("MATCH!");

                //Run the timer
                anim();

                //Set for when cards match
                match = true;

                //Add to the number of squares compelete
                squaresComplete += 2;

                //Increase the progress bar accordingly
                jProgressBar1.setValue(squaresComplete);

                //Output the number of squares complete
                jLabel3.setText("Squares Complete: " + squaresComplete);

                //For when all the squares are compelte
                if (squaresComplete == 16) {
                    jLabel2.setText("YOU WIN!");

                    //Disable give up button
                    jButton18.setEnabled(false);

                    //Enable the proceed to high score button
                    jButton17.setEnabled(true);
                }
            } //Output when first card does no match the second card
            else {
                jLabel2.setText("NOT A MATCH!");

                //Run the timer
                anim();

                //Set for when cards do not match
                match = false;
            }
            //Reset card clicked
            choice = 1;
        }
    }

    //Delay for when images match and do not match
    private class TimerListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            //Change buttons when images match
            if (match == true) {
                //Change the pair of matching buttons to the correct image
                buttons[firstButton].setIcon(new ImageIcon(correct.getScaledInstance(80, 80, 80)));
                buttons[secondButton].setIcon(new ImageIcon(correct.getScaledInstance(80, 80, 80)));

                //Disale the pair of matching buttons
                buttons[firstButton].setEnabled(false);
                buttons[secondButton].setEnabled(false);
            }

            //Change buttons when images do not match
            if (match == false) {
                //Flip back the buttons with the question image
                buttons[firstButton].setIcon(new ImageIcon(question.getScaledInstance(80, 80, 80)));
                buttons[secondButton].setIcon(new ImageIcon(question.getScaledInstance(80, 80, 80)));
            }

            jLabel2.setText("SELECT A BUTTON!");

            //Stop the timer
            t1.stop();

            //Turn the buttons back on
            clickable = true;
        }
    }

    public void anim() {
        //Turn of the buttons
        clickable = false;

        //Start the timer
        t1.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton17 = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel4 = new javax.swing.JLabel();
        jButton18 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Memory Puzzle");
        setMaximumSize(new java.awt.Dimension(800, 536));
        setMinimumSize(new java.awt.Dimension(800, 536));
        setResizable(false);

        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(240, 0, 240));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel1.setText("0");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel2.setText("SELECT A BUTTON!");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel3.setText("Progress (Squares Complete): ");

        jButton17.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jButton17.setText("PROCEED TO HIGH SCORES");
        jButton17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton17MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton17MouseExited(evt);
            }
        });
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jProgressBar1.setMaximum(16);
        jProgressBar1.setStringPainted(true);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel4.setText("MESSAGE:");

        jButton18.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jButton18.setText("GIVE UP");
        jButton18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton18MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton18MouseExited(evt);
            }
        });
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel5.setText("NUMBER OF MOVES:");

        jButton21.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jButton21.setText("NO");
        jButton21.setMaximumSize(new java.awt.Dimension(70, 33));
        jButton21.setMinimumSize(new java.awt.Dimension(70, 33));
        jButton21.setPreferredSize(new java.awt.Dimension(70, 33));
        jButton21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton21MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton21MouseExited(evt);
            }
        });
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jButton22.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jButton22.setText("YES");
        jButton22.setMaximumSize(new java.awt.Dimension(70, 33));
        jButton22.setMinimumSize(new java.awt.Dimension(70, 33));
        jButton22.setPreferredSize(new java.awt.Dimension(70, 33));
        jButton22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton22MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton22MouseExited(evt);
            }
        });
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jProgressBar1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(5, 5, 5))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton17, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addGap(41, 41, 41)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                        .addGap(13, 13, 13))
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jButton21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton17))
                    .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                    .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                    .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //First button
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //When button is clickable after delay
        if (clickable == true) {
            //Determine whether button has already been pressed
            sameButton(0, 0, 1);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    //Second button
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //When button is clickable after delay
        if (clickable == true) {
            //Determine whether button has already been pressed
            sameButton(0, 1, 2);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    //Third button
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //When button is clickable after delay
        if (clickable == true) {
            //Determine whether button has already been pressed
            sameButton(0, 2, 3);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    //Fourth button
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //When button is clickable after delay
        if (clickable == true) {
            //Determine whether button has already been pressed
            sameButton(0, 3, 4);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    //Fifth button
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //When button is clickable after delay
        if (clickable == true) {
            //Determine whether button has already been pressed
            sameButton(1, 0, 5);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    //Sixth button
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        //When button is clickable after delay
        if (clickable == true) {
            //Determine whether button has already been pressed
            sameButton(1, 1, 6);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    //Seventh button
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        //When button is clickable after delay
        if (clickable == true) {
            //Determine whether button has already been pressed
            sameButton(1, 2, 7);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    //Eighth button
    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        //When button is clickable after delay
        if (clickable == true) {
            //Determine whether button has already been pressed
            sameButton(1, 3, 8);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    //Nineth button
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        //When button is clickable after delay
        if (clickable == true) {
            //Determine whether button has already been pressed
            sameButton(2, 0, 9);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    //Tenth button
    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        //When button is clickable after delay
        if (clickable == true) {
            //Determine whether button has already been pressed
            sameButton(2, 1, 10);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    //Eleventh button
    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        //When button is clickable after delay
        if (clickable == true) {
            //Determine whether button has already been pressed
            sameButton(2, 2, 11);
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    //Twelfth button
    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        //When button is clickable after delay
        if (clickable == true) {
            //Determine whether button has already been pressed
            sameButton(2, 3, 12);
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    //Thirteenth button
    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        //When button is clickable after delay
        if (clickable == true) {
            //Determine whether button has already been pressed
            sameButton(3, 0, 13);
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    //Fourteenth button
    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        //When button is clickable after delay
        if (clickable == true) {
            //Determine whether button has already been pressed
            sameButton(3, 1, 14);
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    //Fifteenth button
    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        //When button is clickable after delay
        if (clickable == true) {
            //Determine whether button has already been pressed
            sameButton(3, 2, 15);
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    //Sixteenth
    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        //When button is clickable after delay
        if (clickable == true) {
            //Determine whether button has already been pressed
            sameButton(3, 3, 16);
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    //Proceed to highscore button
    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        //Play the click sound
        m.play();

        //Open file for reading the available money
        TextInputFile tif = new TextInputFile("money.txt");

        //Read the file
        currentMoney = tif.readInt();

        //Close the file
        tif.close();

        //Calculate the amount of money player has after winning bet
        currentMoney = currentMoney + betAmount;

        //Open file for writing to the new available money        
        TextOutputFile tof = new TextOutputFile("money.txt");

        //Output the new amount of money
        tof.println(currentMoney);

        //Close the file
        tof.close();

        //Close the current frame
        this.setVisible(false);

        //Open the high score frame
        new HighScoreFrame(playerName, numberOfMoves).setVisible(true);
    }//GEN-LAST:event_jButton17ActionPerformed

    //Give up button
    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        //Play the click sound
        m.play();

        jLabel2.setText("CHOOSE YES OR NO!");

        //Disable give up button
        jButton18.setEnabled(false);

        jButton18.setText("LOSE BET?");

        //Make the card buttons unclickable
        clickable = false;

        //Enabled the yes or no selection to confirm the giving up
        jButton21.setEnabled(true);
        jButton22.setEnabled(true);
    }//GEN-LAST:event_jButton18ActionPerformed

    //Yes give up button
    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        //Play the click sound
        m.play();

        //Open file for reading the available money
        TextInputFile tif = new TextInputFile("money.txt");

        //Read the file
        currentMoney = tif.readInt();

        //Close the file
        tif.close();

        //Calculate the amount of money player has after losing bet
        currentMoney = currentMoney - betAmount;

        //Open file for writing to the new available money  
        TextOutputFile tof = new TextOutputFile("money.txt");

        //Output the new amount of money
        tof.println(currentMoney);

        //Close the file
        tof.close();

        //Close the current frame
        this.setVisible(false);

        //Open the main menu frame
        new MainMenuFrame().setVisible(true);
    }//GEN-LAST:event_jButton22ActionPerformed

    //No give up button
    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        //Play the click sound
        m.play();

        jLabel2.setText("SELECT A BUTTON!");

        //Enable back the give up button
        jButton18.setEnabled(true);

        jButton18.setText("GIVE UP");

        //Disable the yes and no buttons
        jButton21.setEnabled(false);
        jButton22.setEnabled(false);

        //Make the card buttons clickable
        clickable = true;
    }//GEN-LAST:event_jButton21ActionPerformed

    //Give up mouse enter
    private void jButton18MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton18MouseEntered
        //Change color of give up button
        jButton18.setBackground(Color.getHSBColor(28, 65, 95));
    }//GEN-LAST:event_jButton18MouseEntered

    //Give up mouse exit
    private void jButton18MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton18MouseExited
        //Rest give up button color
        jButton18.setBackground(getBackground());
    }//GEN-LAST:event_jButton18MouseExited

    //Yes give up mouse enter
    private void jButton22MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton22MouseEntered
        //Change color of yes button
        jButton22.setBackground(Color.getHSBColor(28, 65, 95));
    }//GEN-LAST:event_jButton22MouseEntered

    //Yes give up mouse exit
    private void jButton22MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton22MouseExited
        //Rest yes button color
        jButton22.setBackground(getBackground());
    }//GEN-LAST:event_jButton22MouseExited

    //No give up mouse enter
    private void jButton21MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton21MouseEntered
        //Change color of no button
        jButton21.setBackground(Color.getHSBColor(28, 65, 95));

    }//GEN-LAST:event_jButton21MouseEntered

    //No give up mouse exit
    private void jButton21MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton21MouseExited
        //Rest no button color
        jButton21.setBackground(getBackground());
    }//GEN-LAST:event_jButton21MouseExited

    //Proceed to highscore mouse enter
    private void jButton17MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton17MouseEntered
        //Change color of proceed to high score button
        jButton17.setBackground(Color.getHSBColor(28, 65, 95));
    }//GEN-LAST:event_jButton17MouseEntered

    //Proceed to highscore mouse exit
    private void jButton17MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton17MouseExited
        //Rest proceed to high score button color
        jButton17.setBackground(getBackground());
    }//GEN-LAST:event_jButton17MouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}

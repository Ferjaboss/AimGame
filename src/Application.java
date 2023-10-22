
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Application {
	    private static JPanel playArea;
	    private static JPanel cntrlArea;
	    private static JButton startButton;
	    private static JButton currentButton;
	    private static int rounds = 10; // Number of rounds to play
	    private static int currentRound = 0;
	    private static long startTime;
	    private static long totalTime;

	    public static void main(String[] args) {
	        JFrame frame = new JFrame();
	        frame.setTitle("Aim Lab");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setLayout(null);
	        frame.setResizable(false);
	        frame.setSize(1280, 720);
	        ImageIcon image = new ImageIcon("target.png");
	        frame.setIconImage(image.getImage());

	        playArea = new JPanel();
	        playArea.setBackground(Color.gray);
	        playArea.setBounds(0, 0, 1280, 620);
	        playArea.setLayout(null);

	        cntrlArea = new JPanel();
	        cntrlArea.setBounds(0, 620, 1280, 100);
	        cntrlArea.setLayout(null);

	        startButton = new JButton("Start Game");
	        startButton.setBounds(515, 10, 250, 40);

	        startButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                startGame();
	            }
	        });

	        cntrlArea.add(startButton);

	        frame.add(playArea);
	        frame.add(cntrlArea);
	        frame.setVisible(true);
	    }

	    private static void startGame() {
	        startButton.setEnabled(false);
	        nextRound();
	    }

	    private static void nextRound() {
	        if (currentRound < rounds) {
	            createRandomButton();
	            startTime = System.currentTimeMillis();
	            currentRound++;
	        } else {
	            displayResults();
	        }
	    }

	    private static void createRandomButton() {
	        currentButton = new JButton("click me");
	        currentButton.setBounds(getRandomX(), getRandomY(), 120, 50);
	        currentButton.setBackground(getRandomColor());

	        currentButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                long responseTime = System.currentTimeMillis() - startTime;
	                totalTime += responseTime;
	                playArea.remove(currentButton);
	                playArea.revalidate();
	                playArea.repaint();
	                nextRound();
	            }
	        });

	        playArea.add(currentButton);
	        playArea.revalidate();
	        playArea.repaint();
	    }

	    private static int getRandomX() {
	        int maxX = playArea.getWidth() - 120; 
	        return new Random().nextInt(maxX);
	    }

	    private static int getRandomY() {
	        int maxY = playArea.getHeight() - 50;
	        return new Random().nextInt(maxY);
	    }
	    private static Color getRandomColor() {
	        Random random = new Random();
	        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
	    }

	    private static void displayResults() {
	        long avgResponseTime = totalTime / rounds;

	        JOptionPane.showMessageDialog(null, "Average Response Time: " + avgResponseTime + " ms" , "Game Over", JOptionPane.INFORMATION_MESSAGE);

	 
	        currentRound = 0;
	        totalTime = 0;
	        startButton.setEnabled(true);
	    }
}
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Random;

public class QuizBattleGUI extends JFrame {

    private static final int PLAYER_MAX_HP = 100;
    private static final int BOSS_MAX_HP = 100;

    private int playerHP = PLAYER_MAX_HP;
    private int bossHP = BOSS_MAX_HP;
    private int score = 0;
    private int comboStreak = 0;
    private int questionIndex = 0;

    private List<Questions> questionBank;
    private Random random = new Random();

    private JLabel bossLabel;
    private JLabel titleLabel;
    private JProgressBar bossBar;
    private JProgressBar playerBar;
    private JLabel bossHpText;
    private JLabel playerHpText;
    private JLabel scoreLabel;
    private JLabel comboLabel;
    private JTextArea logArea;
    private JLabel questionLabel;
    private JButton[] optionButtons = new JButton[4];
    private JPanel bossPanel;

    public QuizBattleGUI() {
        questionBank = Questions.loadQuestions();

        setTitle("CODE BOSS BATTLE");
        setSize(720, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        getContentPane().setBackground(new Color(20, 20, 30));
        setLayout(new BorderLayout(10, 10));

        add(buildTopPanel(), BorderLayout.NORTH);
        add(buildBossPanel(), BorderLayout.CENTER);
        add(buildBottomPanel(), BorderLayout.SOUTH);

        loadNextQuestion();
    }

    private static final String BOSS_ART =
            "<html><pre>"
            + "  ╔═══════════╗  \n"
            + "  ║  ●     ●  ║  \n"
            + "  ║           ║  \n"
            + "  ║  ▄▄▄▄▄▄▄  ║  \n"
            + "  ╚═══════════╝  \n"
            + "    ▐███████▌    "
            + "</pre></html>";

    private static void styleBar(JProgressBar bar, Color fill, Color track) {
        bar.setUI(new BasicProgressBarUI());
        bar.setForeground(fill);
        bar.setBackground(track);
        bar.setBorderPainted(false);
    }

    private JPanel buildTopPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2, 20, 0));
        panel.setBackground(new Color(20, 20, 30));
        panel.setBorder(new EmptyBorder(15, 20, 5, 20));

        JPanel bossStats = new JPanel(new BorderLayout());
        bossStats.setBackground(new Color(20, 20, 30));
        JLabel bossName = new JLabel("CODE BOSS");
        bossName.setForeground(new Color(255, 90, 90));
        bossName.setFont(new Font("Consolas", Font.BOLD, 18));
        bossBar = new JProgressBar(0, BOSS_MAX_HP);
        bossBar.setValue(BOSS_MAX_HP);
        styleBar(bossBar, new Color(220, 50, 50), new Color(60, 20, 20));
        bossHpText = new JLabel("HP: 100 / 100", SwingConstants.CENTER);
        bossHpText.setForeground(Color.WHITE);
        bossStats.add(bossName, BorderLayout.NORTH);
        bossStats.add(bossBar, BorderLayout.CENTER);
        bossStats.add(bossHpText, BorderLayout.SOUTH);

        JPanel playerStats = new JPanel(new BorderLayout());
        playerStats.setBackground(new Color(20, 20, 30));
        JLabel playerName = new JLabel("YOU", SwingConstants.RIGHT);
        playerName.setForeground(new Color(90, 200, 255));
        playerName.setFont(new Font("Consolas", Font.BOLD, 18));
        playerBar = new JProgressBar(0, PLAYER_MAX_HP);
        playerBar.setValue(PLAYER_MAX_HP);
        styleBar(playerBar, new Color(50, 150, 220), new Color(20, 30, 60));
        playerHpText = new JLabel("HP: 100 / 100", SwingConstants.CENTER);
        playerHpText.setForeground(Color.WHITE);
        playerStats.add(playerName, BorderLayout.NORTH);
        playerStats.add(playerBar, BorderLayout.CENTER);
        playerStats.add(playerHpText, BorderLayout.SOUTH);

        panel.add(bossStats);
        panel.add(playerStats);
        return panel;
    }

    private JPanel buildBossPanel() {
        bossPanel = new JPanel(new BorderLayout());
        bossPanel.setBackground(new Color(20, 20, 30));

        bossLabel = new JLabel(BOSS_ART, SwingConstants.CENTER);
        bossLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 22));
        bossLabel.setForeground(new Color(255, 120, 120));

        titleLabel = new JLabel("Defeat the Code Boss!", SwingConstants.CENTER);
        titleLabel.setForeground(new Color(200, 200, 220));
        titleLabel.setFont(new Font("Consolas", Font.ITALIC, 14));

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(new Color(20, 20, 30));
        wrapper.add(bossLabel, BorderLayout.CENTER);
        wrapper.add(titleLabel, BorderLayout.SOUTH);

        logArea = new JTextArea(4, 20);
        logArea.setEditable(false);
        logArea.setBackground(new Color(10, 10, 15));
        logArea.setForeground(new Color(120, 255, 150));
        logArea.setFont(new Font("Consolas", Font.PLAIN, 13));
        logArea.setBorder(new EmptyBorder(8, 10, 8, 10));
        JScrollPane logScroll = new JScrollPane(logArea);
        logScroll.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 80)));

        bossPanel.add(wrapper, BorderLayout.CENTER);
        bossPanel.add(logScroll, BorderLayout.SOUTH);
        return bossPanel;
    }

    private JPanel buildBottomPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(20, 20, 30));
        panel.setBorder(new EmptyBorder(5, 20, 20, 20));

        JPanel statsRow = new JPanel(new GridLayout(1, 2));
        statsRow.setBackground(new Color(20, 20, 30));
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setForeground(Color.YELLOW);
        scoreLabel.setFont(new Font("Consolas", Font.BOLD, 14));
        comboLabel = new JLabel("Combo: 0", SwingConstants.RIGHT);
        comboLabel.setForeground(new Color(255, 170, 60));
        comboLabel.setFont(new Font("Consolas", Font.BOLD, 14));
        statsRow.add(scoreLabel);
        statsRow.add(comboLabel);

        questionLabel = new JLabel("Loading question...", SwingConstants.CENTER);
        questionLabel.setForeground(Color.WHITE);
        questionLabel.setFont(new Font("Consolas", Font.BOLD, 16));
        questionLabel.setBorder(new EmptyBorder(10, 0, 10, 0));

        JPanel optionsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        optionsPanel.setBackground(new Color(20, 20, 30));
        for (int i = 0; i < 4; i++) {
            final int idx = i;
            JButton btn = new JButton();
            btn.setFont(new Font("Consolas", Font.PLAIN, 14));
            btn.setFocusPainted(false);
            Color btnBg = new Color(45, 45, 65);
            btn.setBackground(btnBg);
            int brightness = (int) (btnBg.getRed() * 0.299 + btnBg.getGreen() * 0.587 + btnBg.getBlue() * 0.114);
            btn.setForeground(brightness < 128 ? Color.WHITE : Color.BLACK);
            btn.setOpaque(true);
            btn.setContentAreaFilled(true);
            btn.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));
            btn.addActionListener((ActionEvent e) -> handleAnswer(idx));
            optionButtons[i] = btn;
            optionsPanel.add(btn);
        }

        panel.add(statsRow, BorderLayout.NORTH);
        panel.add(questionLabel, BorderLayout.CENTER);
        panel.add(optionsPanel, BorderLayout.SOUTH);
        return panel;
    }

    private void loadNextQuestion() {
        if (questionIndex >= questionBank.size()) {
            questionIndex = 0;
        }
        Questions q = questionBank.get(questionIndex);
        questionLabel.setText("<html><div style='text-align:center;width:600px'>" + q.getQuestion() + "</div></html>");
        String[] opts = q.getOptions();
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText((char) ('A' + i) + ". " + opts[i]);
            optionButtons[i].setEnabled(true);
            Color bg = new Color(45, 45, 65);
            optionButtons[i].setBackground(bg);
            int b = (int) (bg.getRed() * 0.299 + bg.getGreen() * 0.587 + bg.getBlue() * 0.114);
            optionButtons[i].setForeground(b < 128 ? Color.WHITE : Color.BLACK);
        }
    }

    private void handleAnswer(int chosenIndex) {
        Questions q = questionBank.get(questionIndex);
        for (JButton b : optionButtons) {
            b.setEnabled(false);
        }

        if (chosenIndex == q.getCorrectIndex()) {
            optionButtons[chosenIndex].setBackground(new Color(60, 180, 90));
            comboStreak++;
            boolean critical = random.nextInt(100) < Math.min(10 + comboStreak * 5, 40);
            int damage = 20 + (critical ? 20 : 0);
            bossHP = Math.max(0, bossHP - damage);
            score += 10 + (comboStreak - 1) * 2;

            String hitText = critical ? "CRITICAL HIT! Boss takes " + damage + " damage!"
                    : "Correct! Boss takes " + damage + " damage.";
            appendLog(hitText);
            shakeBoss();
        } else {
            optionButtons[chosenIndex].setBackground(new Color(200, 60, 60));
            optionButtons[q.getCorrectIndex()].setBackground(new Color(60, 180, 90));
            comboStreak = 0;
            playerHP = Math.max(0, playerHP - 10);
            appendLog("Wrong! The correct answer was " + (char) ('A' + q.getCorrectIndex())
                    + ". You take 10 damage.");
        }

        updateBars();
        questionIndex++;

        Timer delay = new Timer(1000, e -> {
            ((Timer) e.getSource()).stop();
            if (bossHP <= 0) {
                showResult(true);
            } else if (playerHP <= 0) {
                showResult(false);
            } else {
                loadNextQuestion();
            }
        });
        delay.setRepeats(false);
        delay.start();
    }

    private void shakeBoss() {
        Point original = bossLabel.getLocation();
        int[] offsets = {-10, 10, -8, 8, -5, 5, 0};
        Timer shake = new Timer(40, null);
        int[] step = {0};
        shake.addActionListener(e -> {
            if (step[0] >= offsets.length) {
                shake.stop();
                return;
            }
            bossLabel.setLocation(original.x + offsets[step[0]], original.y);
            step[0]++;
        });
        shake.start();
    }

    private void updateBars() {
        bossBar.setValue(bossHP);
        playerBar.setValue(playerHP);
        bossHpText.setText("HP: " + bossHP + " / " + BOSS_MAX_HP);
        playerHpText.setText("HP: " + playerHP + " / " + PLAYER_MAX_HP);
        scoreLabel.setText("Score: " + score);
        comboLabel.setText("Combo: " + comboStreak);

        if (bossHP <= 30) {
            bossBar.setForeground(new Color(255, 210, 40));
        }
        if (bossHP <= 10) {
            bossBar.setForeground(new Color(255, 60, 60));
        }
    }

    private void appendLog(String text) {
        logArea.append("> " + text + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }

    private void showResult(boolean playerWon) {
        String message = playerWon
                ? "YOU DEFEATED THE CODE BOSS!\nFinal Score: " + score
                : "GAME OVER\nThe Code Boss defeated you.\nFinal Score: " + score;

        int choice = JOptionPane.showConfirmDialog(this,
                message + "\n\nPlay again?", playerWon ? "Victory" : "Defeat",
                JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            System.exit(0);
        }
    }

    private void resetGame() {
        playerHP = PLAYER_MAX_HP;
        bossHP = BOSS_MAX_HP;
        score = 0;
        comboStreak = 0;
        questionIndex = 0;
        bossBar.setForeground(new Color(220, 50, 50));
        questionBank = Questions.loadQuestions();
        logArea.setText("");
        updateBars();
        loadNextQuestion();
    }

    public static void main(String[] args) {
        if (GraphicsEnvironment.isHeadless()) {
            runHeadless();
        } else {
            SwingUtilities.invokeLater(() -> new QuizBattleGUI().setVisible(true));
        }
    }

    private static void runHeadless() {
        List<Questions> questionBank = Questions.loadQuestions();
        Random random = new Random();
        int playerHP = PLAYER_MAX_HP;
        int bossHP = BOSS_MAX_HP;
        int score = 0;
        int comboStreak = 0;
        int questionIndex = 0;

        System.out.println("Running QuizBattle in headless mode (automated simulation).");
        while (playerHP > 0 && bossHP > 0) {
            if (questionIndex >= questionBank.size()) questionIndex = 0;
            Questions q = questionBank.get(questionIndex);
            System.out.println("Q: " + q.getQuestion());
            String[] opts = q.getOptions();
            for (int i = 0; i < opts.length; i++) {
                System.out.println((char) ('A' + i) + ". " + opts[i]);
            }
            int chosen = random.nextInt(4);
            System.out.println("Chose: " + (char) ('A' + chosen));

            if (chosen == q.getCorrectIndex()) {
                comboStreak++;
                boolean critical = random.nextInt(100) < Math.min(10 + comboStreak * 5, 40);
                int damage = 20 + (critical ? 20 : 0);
                bossHP = Math.max(0, bossHP - damage);
                score += 10 + (comboStreak - 1) * 2;
                System.out.println(critical ? "CRITICAL! Boss takes " + damage + " damage!"
                        : "Correct! Boss takes " + damage + " damage.");
            } else {
                comboStreak = 0;
                playerHP = Math.max(0, playerHP - 10);
                System.out.println("Wrong! The correct answer was " + (char) ('A' + q.getCorrectIndex())
                        + ". You take 10 damage.");
            }

            System.out.printf("HP: You %d | Boss %d | Score %d\n", playerHP, bossHP, score);
            questionIndex++;
            try {
                Thread.sleep(200);
            } catch (InterruptedException ignored) {
            }
        }

        if (bossHP <= 0) {
            System.out.println("YOU DEFEATED THE CODE BOSS! Final Score: " + score);
        } else {
            System.out.println("GAME OVER\nThe Code Boss defeated you. Final Score: " + score);
        }
    }
}
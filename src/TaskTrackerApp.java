import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TaskTrackerApp {
    private List<Task> tasks;
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JFrame frame;

    public TaskTrackerApp() {
        tasks = new ArrayList<>();
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);

        frame = new JFrame("Task Tracker App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel inputPanel = createInputPanel();
        JPanel listPanel = createListPanel();
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(taskList), BorderLayout.CENTER);

        frame.setSize(400, 400);
        frame.setVisible(true);
    }

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));

        JLabel taskNameLabel = new JLabel("Nama Tugas:");
        JTextField taskNameField = new JTextField();
        JLabel durationLabel = new JLabel("Durasi (menit):");
        JTextField durationField = new JTextField();
        JLabel additionalLabel = new JLabel("Detail:");
        JTextField additionalField = new JTextField();

        JButton addButton = new JButton("Tambah Tugas");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask(taskNameField.getText(), durationField.getText(), additionalField.getText());
                taskNameField.setText("");
                durationField.setText("");
                additionalField.setText("");
            }
        });

        JButton stopButton = new JButton("Hentikan Tugas");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopTask();
            }
        });

        inputPanel.add(taskNameLabel);
        inputPanel.add(taskNameField);
        inputPanel.add(durationLabel);
        inputPanel.add(durationField);
        inputPanel.add(additionalLabel);
        inputPanel.add(additionalField);
        inputPanel.add(addButton);
        inputPanel.add(stopButton);

        return inputPanel;
    }

    private JPanel createListPanel() {
        JPanel listPanel = new JPanel(new BorderLayout());

        JLabel listLabel = new JLabel("Daftar Tugas:");
        listPanel.add(listLabel, BorderLayout.NORTH);
        listPanel.add(new JScrollPane(taskList), BorderLayout.CENTER);

        return listPanel;
    }

    private void addTask(String taskName, String durationStr, String additional) {
        if (!taskName.isEmpty() && !durationStr.isEmpty()) {
            try {
                int duration = Integer.parseInt(durationStr);
                String taskDetail = additional.isEmpty() ? "" : ", Detail: " + additional;

                String taskDescription = "Task: " + taskName + ", Duration: " + duration + " minutes" + taskDetail;
                taskListModel.addElement(taskDescription);

                tasks.add(new Task(taskName, duration));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Durasi harus berupa angka.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Nama tugas dan durasi tidak boleh kosong.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void stopTask() {
        int selectedIndex = taskList.getSelectedIndex();

        if (selectedIndex >= 0 && selectedIndex < tasks.size()) {
            Task stoppedTask = tasks.get(selectedIndex);

            String input = JOptionPane.showInputDialog(frame, "Masukkan durasi sebenarnya (dalam menit):");
            try {
                if (input != null) {
                    int actualDuration = Integer.parseInt(input);
                    if (actualDuration <= stoppedTask.getDuration()) {
                        JOptionPane.showMessageDialog(frame, "Anda telah menyelesaikan tugas \"" + stoppedTask.getTaskName() +
                                "\" tepat waktu. Selamat!");
                    } else {
                        int overTime = actualDuration - stoppedTask.getDuration();
                        JOptionPane.showMessageDialog(frame, "Anda telah menyelesaikan tugas \"" + stoppedTask.getTaskName() +
                                "\" dengan keterlambatan " + overTime + " menit. Harap lebih berhati-hati.");
                    }

                    tasks.remove(selectedIndex);
                    taskListModel.remove(selectedIndex);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Masukkan angka yang valid.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Pilih tugas yang akan dihentikan.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TaskTrackerApp();
            }
        });
    }
}

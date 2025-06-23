import javax.swing.*;
import java.io.*;

public class FileOperations {

    // Renomeia um arquivo ou diretório
    public static void renameFile(File currentDirectory) {
        File[] files = currentDirectory.listFiles();
        if (files != null && files.length > 0) {
            String[] fileNames = new String[files.length];
            for (int i = 0; i < files.length; i++) {
                fileNames[i] = files[i].getName();
            }

            String selectedFile = (String) JOptionPane.showInputDialog(null, "Escolha um arquivo ou diretório para renomear:",
                    "Renomear Arquivo", JOptionPane.QUESTION_MESSAGE, null, fileNames, fileNames[0]);

            if (selectedFile != null) {
                File fileToRename = new File(currentDirectory, selectedFile);
                String newName = JOptionPane.showInputDialog("Digite o novo nome:");
                if (newName != null && !newName.trim().isEmpty()) {
                    File renamedFile = new File(currentDirectory, newName);
                    if (fileToRename.renameTo(renamedFile)) {
                        JOptionPane.showMessageDialog(null, "Arquivo renomeado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao renomear o arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    // Exclui um arquivo ou diretório
    public static void deleteFile(File currentDirectory) {
        File[] files = currentDirectory.listFiles();
        if (files != null && files.length > 0) {
            String[] fileNames = new String[files.length];
            for (int i = 0; i < files.length; i++) {
                fileNames[i] = files[i].getName();
            }

            String selectedFile = (String) JOptionPane.showInputDialog(null, "Escolha um arquivo ou diretório para excluir:",
                    "Excluir Arquivo", JOptionPane.QUESTION_MESSAGE, null, fileNames, fileNames[0]);

            if (selectedFile != null) {
                File fileToDelete = new File(currentDirectory, selectedFile);
                int confirm = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja excluir " + fileToDelete.getName() + "?",
                        "Confirmação de Exclusão", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (fileToDelete.delete()) {
                        JOptionPane.showMessageDialog(null, "Arquivo excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao excluir o arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }
}

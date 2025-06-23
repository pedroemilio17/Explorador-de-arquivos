import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class DirectoryNavigator {
    private File currentDirectory;
    private ArrayList<File> history;

    public DirectoryNavigator() {
        // Começa pela raiz do sistema
        String rootDir = System.getProperty("os.name").startsWith("Windows") ? "C:\\" : "/";
        currentDirectory = new File(rootDir);
        history = new ArrayList<>();
    }

    // Exibe o conteúdo do diretório atual
    public void listFiles() {
        File[] files = currentDirectory.listFiles();
        if (files != null && files.length > 0) {
            StringBuilder fileList = new StringBuilder("Conteúdo do diretório:\n");
            for (int i = 0; i < files.length; i++) {
                fileList.append(i + 1).append(". ").append(files[i].getName()).append(" - ");
                if (files[i].isDirectory()) {
                    fileList.append("Diretório");
                } else {
                    fileList.append("Arquivo, Tamanho: ").append(files[i].length()).append(" bytes");
                }
                fileList.append("\n");
            }
            JOptionPane.showMessageDialog(null, fileList.toString(), "Conteúdo do Diretório", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "O diretório está vazio ou não pode ser acessado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Permite ao usuário entrar em um subdiretório
    public void enterDirectory() {
        File[] files = currentDirectory.listFiles();
        if (files != null) {
            ArrayList<File> directories = new ArrayList<>();
            for (File file : files) {
                if (file.isDirectory()) {
                    directories.add(file);
                }
            }

            if (directories.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Não há subdiretórios para entrar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String[] dirNames = new String[directories.size()];
            for (int i = 0; i < directories.size(); i++) {
                dirNames[i] = directories.get(i).getName();
            }

            String selectedDir = (String) JOptionPane.showInputDialog(null, "Escolha um subdiretório para entrar:",
                    "Entrar em Subdiretório", JOptionPane.QUESTION_MESSAGE, null, dirNames, dirNames[0]);

            if (selectedDir != null) {
                history.add(currentDirectory); // Salva o diretório atual no histórico
                currentDirectory = new File(currentDirectory, selectedDir);
            }
        }
    }

    // Permite ao usuário voltar ao diretório anterior
    public void goBack() {
        if (history.size() > 0) {
            currentDirectory = history.remove(history.size() - 1);
        } else {
            JOptionPane.showMessageDialog(null, "Você já está no diretório raiz.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Exibe o menu principal
    public void showMenu() {
        String[] options = {"Listar Arquivos", "Entrar em Subdiretório", "Voltar ao Diretório Pai", "Renomear Arquivo",
                             "Excluir Arquivo", "Sair"};
        int choice = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "Mini Navegador de Arquivos",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                listFiles();
                break;
            case 1:
                enterDirectory();
                break;
            case 2:
                goBack();
                break;
            case 3:
                FileOperations.renameFile(currentDirectory);
                break;
            case 4:
                FileOperations.deleteFile(currentDirectory);
                break;
            case 5:
                JOptionPane.showMessageDialog(null, "Saindo do Navegador de Arquivos.", "Saída", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
                break;
            default:
                break;
        }
    }
}

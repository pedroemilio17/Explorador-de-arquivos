
public class FileExplorer {
    public static void main(String[] args) {
        // Instancia a classe DirectoryNavigator que lida com a navegação de diretórios
        DirectoryNavigator navigator = new DirectoryNavigator();
        
        // Loop principal do programa, apresentando o menu de opções para o usuário
        while (true) {
            navigator.showMenu();
        }
    }
}

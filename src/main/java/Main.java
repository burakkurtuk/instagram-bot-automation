import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Please enter your username : ");
        String username = scanner.nextLine();
        System.out.println("Please enter your password : ");
        String password = scanner.nextLine();
        System.out.println("Please enter target profile name : ");
        String targetProfile = scanner.nextLine();



        App app = new App();
        app.loginWith(username,password);
        app.navigateToProfile(targetProfile);
        app.clickFirstPost();
        app.likeAllPost();


    }
}

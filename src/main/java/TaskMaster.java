import java.util.Scanner;
public class TaskMaster {
    public static void main(String[] args) {
        String logo = "____________________________________________________________"+
        "\nHello! I'm Taskmaster" +
        "\nWhat can I do for you?"+
                "\n____________________________________________________________";
        System.out.println(logo);
        Scanner sc =  new Scanner(System.in);
        while(true){
            System.out.print("> ");
            String command = sc.nextLine();
            if (command.trim().equals("bye")){
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }
            System.out.println("    "+command);
            System.out.println("____________________________________________________________");
        }
    }
}

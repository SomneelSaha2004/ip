import java.util.Scanner;
import java.util.ArrayList;
public class TaskMaster {
    public static void main(String[] args) {
        String logo = "____________________________________________________________"+
        "\nHello! I'm Taskmaster" +
        "\nWhat can I do for you?"+
                "\n____________________________________________________________";
        System.out.println(logo);
        Scanner sc =  new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();
        while(true){
            System.out.print("> ");
            String command = sc.nextLine();
            
            if (command.trim().equals("bye")){
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if(command.trim().equals("list")){
                System.out.println("____________________________________________________________");
                for (int i = 0; i < tasks.size(); i++){
                    System.out.println(i+1 + ". " + tasks.get(i));
                }
            } else{
            tasks.add(command.trim());      
            System.out.println("added: "+command);
            }
            System.out.println("____________________________________________________________");
        }
    }
}

import java.util.Scanner;

public class MeetingRoomController {
    private Scanner sc = new Scanner(System.in);
    private MeetingRoomsService meetingRoomsService =
            new MeetingRoomsService(new InMemoryMeetingRoomsRepository());
    private String line;
    private int menu;

    public static void main(String[] args) {
        new MeetingRoomController().start();
    }

    private void start() {
        do {
            System.out.println(
                    "-----------------------------------------------------------------\n" +
                            "| 0. Targyalo rogzitese                                         |\n" +
                            "| 1. Targyalok nev sorrendben   | 5. Kereses nev alapjan        |\n" +
                            "| 2. Targyalok visz sorrendben  | 6. Kereses nevtoredek alapjan |\n" +
                            "| 3. Minden masodik targyalo    | 7. Kereses terulet alapjan    |\n" +
                            "| 4. Teruletek                  | 8. Kilepes                    |\n" +
                            "-----------------------------------------------------------------\n" +
                            "Menupont:");

            menu = Integer.parseInt(sc.nextLine());
            switch (menu) {
                case 0:
                    rogzites();
                    break;
                case 1:
                    System.out.println(meetingRoomsService.listEmployees());
                    break;
                case 2:
                    System.out.println(meetingRoomsService.revListEmployees());
                    break;
                case 3:
                    System.out.println(meetingRoomsService.evenListEmployees());
            }
        }
        while (menu != 8);
    }

    private void rogzites() {
        System.out.println("Targyalo: neve,szelessege,hossza (ures sor vege)");
        do {
            line = sc.nextLine();
            if (!line.isEmpty()) {
                String[] data = line.split(",");
                meetingRoomsService.save(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]));
            }
        }
        while (!line.isEmpty());
    }
}

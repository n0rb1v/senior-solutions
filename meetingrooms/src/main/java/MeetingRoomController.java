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
        meetingRoomsService.save("sdf",6,6);
        meetingRoomsService.save("ghf",5,9);
        meetingRoomsService.save("acf",3,5);
        meetingRoomsService.save("uof",7,3);

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
                    System.out.println(meetingRoomsService.listMeetingRooms());
                    break;
                case 2:
                    System.out.println(meetingRoomsService.revListMeetingRooms());
                    break;
                case 3:
                    System.out.println(meetingRoomsService.evenListMeetingRooms());
                    break;
                case 4:
                    for (MeetingRoom item: meetingRoomsService.areaListMeetingRooms()) {
                        System.out.println(item.getName()+":"+item.getWidth()+":"+item.getLenght()
                        +":"+item.getArea());
                    }
                    break;
                case 5:
                    MeetingRoom m = meetingRoomsService.findByName(input("nev:")).orElse(new MeetingRoom(0L,"",0,0));
                    if (!m.getName().isEmpty()) {
                        System.out.println(m.getWidth()+":"+m.getLenght()+":"+m.getArea());
                    }
                    break;
                case 6:
                    MeetingRoom pm = meetingRoomsService.findByPartname(input("nev toredek:")).orElse(new MeetingRoom(0L,"",0,0));
                    if (!pm.getName().isEmpty()) {
                        System.out.println(pm.getWidth()+":"+pm.getLenght()+":"+pm.getArea());
                    }
                    break;
                case 7:
                    for (MeetingRoom item: meetingRoomsService.findByArea(Integer.parseInt(input("terulet:")))) {
                        System.out.println(item.getName()+":"+item.getWidth()+":"+item.getLenght()
                                +":"+item.getArea());
                    }



            }
        }
        while (menu != 8);
    }

    private String input(String s) {
        System.out.print(s);
        return sc.nextLine();
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

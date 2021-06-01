public class MeetingRoom {
    private Long id;
    private String name;
    private int width;
    private int lenght;

    public MeetingRoom(Long id, String name, int width, int lenght) {
        this.id = id;
        this.name = name;
        this.width = width;
        this.lenght = lenght;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public int getLenght() {
        return lenght;
    }

    @Override
    public String toString() {
        return "MeetingRoom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", width=" + width +
                ", lenght=" + lenght +
                '}';
    }
}

import org.flywaydb.core.Flyway;
import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;

public class MariaDbMeetingRoomsRepository implements MeetingRoomsRepository {

    private JdbcTemplate jdbcTemplate;

    public MariaDbMeetingRoomsRepository() {
        try {
            MariaDbDataSource dataSource;
            dataSource = new MariaDbDataSource();
            dataSource.setUrl("jdbc:mariadb://localhost:3306/employees?useUnicode=true");
            dataSource.setUser("employees");
            dataSource.setPassword("employees");

            Flyway flyway = Flyway.configure().dataSource(dataSource).load();
            flyway.migrate();

            jdbcTemplate = new JdbcTemplate(dataSource);

        } catch (SQLException e) {
            throw new IllegalStateException("datasource error", e);
        }

    }

    @Override
    public void save(String name, int width, int lenght) {
        jdbcTemplate.update("insert into meetingrooms(mr_name, mr_width, mr_lenght) values (?,?,?)", name, width, lenght);
    }

    @Override
    public List<MeetingRoom> findAll() {
        return jdbcTemplate.query("select * from meetingrooms order by mr_name",
                (rs, i) -> new MeetingRoom(rs.getLong("id"),
                        rs.getString("mr_name"),
                        rs.getInt("mr_width"),
                        rs.getInt("mr_lenght")));
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("delete from meetingrooms");
    }
}

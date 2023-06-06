package ru.kuzstu.springRESTful.dao;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.kuzstu.springRESTful.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class UserJdbcDAO implements UserDAO {
    private static final Logger logger = LoggerFactory.getLogger(UserJdbcDAO.class);
    private JdbcTemplate jdbcTemplate;

    public UserJdbcDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    RowMapper<User> rowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setRegistrationDateTime(rs.getObject("registration_date_time", LocalDateTime.class));
        return user;
    };

    @Override
    public List<User> list() {
        String sql = "SELECT user_id, name, email, password, registration_date_time FROM users";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void create(User user) {
        String sql = "INSERT INTO users(name, email, password, registration_date_time) VALUES(?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int count = jdbcTemplate.update(connection -> {
            PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, user.getName());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPassword());
            pst.setObject(4, user.getRegistrationDateTime());
            return pst;
        }, keyHolder);
        int id = (int) keyHolder.getKeys().get("user_id");
        user.setUserId(id);
        if (count == 1) {
            logger.info("Новый пользователь создан: " + keyHolder.getKeys().get("user_id"));
        }
    }

    @Override
    public User get(int id) {
        String sql = "SELECT user_id, name, email, password, registration_date_time FROM users WHERE user_id = ?";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper);
        } catch (DataAccessException exception) {
            logger.info("Пользователь не найден с таким id: " + user.getUserId());
        }
        return user;
    }

    @Override
    public void update(User user, int id) {
        String sql = "UPDATE users SET name = ?, email = ?, password = ?, registration_date_time = ? WHERE user_id = ?";
        int update = jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPassword(), user.getRegistrationDateTime(), user.getUserId());
        if (update == 1) {
            logger.info("Информация о пользователе обновлена: " + user.getUserId());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM users WHERE user_id = ?";
        try {
            jdbcTemplate.update(sql, id);
        } catch (DataAccessException exception) {
            logger.info("Пользователь не найден");
        }

    }
}
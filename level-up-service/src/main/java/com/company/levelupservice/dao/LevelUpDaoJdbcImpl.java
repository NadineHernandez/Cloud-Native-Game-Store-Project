package com.company.levelupservice.dao;

import com.company.levelupservice.dto.LevelUp;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LevelUpDaoJdbcImpl implements LevelUpDao{
    //prepared statements
    public static final String INSERT_LEVEL_UP_SQL =
            "INSERT INTO level_up (customer_id, points, member_date) VALUES (?, ?, ?)";

    public static final String SELECT_LEVEL_UP_SQL =
            "SELECT * FROM level_up WHERE level_up_id = ?";

    public static final String SELECT_ALL_LEVEL_UPS_SQL =
            "SELECT * FROM level_up";

    public static final String UPDATE_LEVEL_UP_SQL =
            "UPDATE level_up SET customer_id = ?, points = ?, member_date = ? WHERE level_up_id = ?";

    public static final String DELETE_LEVEL_UP_SQL =
            "DELETE FROM level_up WHERE level_up_id = ?";

    private JdbcTemplate jdbcTemplate;

    public LevelUpDaoJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public LevelUp createLevelUp(LevelUp levelUp) {
        jdbcTemplate.update(INSERT_LEVEL_UP_SQL, levelUp.getCustomerId(), levelUp.getPoints(), levelUp.getMemberDate());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        levelUp.setLevelUpId(id);
        return levelUp;
    }

    @Override
    public LevelUp getLevelUp(int id) {
        try{
            return jdbcTemplate.queryForObject(SELECT_LEVEL_UP_SQL, this::mapToRowLevelUp, id);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public List<LevelUp> getAllLevelUps() {
        return jdbcTemplate.query(SELECT_ALL_LEVEL_UPS_SQL, this::mapToRowLevelUp);
    }

    @Override
    public void updateLevelUp(LevelUp levelUp) {
        jdbcTemplate.update(UPDATE_LEVEL_UP_SQL, levelUp.getCustomerId(), levelUp.getPoints(),
                levelUp.getMemberDate(), levelUp.getLevelUpId());
    }

    @Override
    public void deleteLevelUp(int id) {
        jdbcTemplate.update(DELETE_LEVEL_UP_SQL, id);
    }

    private LevelUp mapToRowLevelUp(ResultSet rs, int rowNum)throws SQLException{
        LevelUp levelUp = new LevelUp();
        levelUp.setLevelUpId(rs.getInt("level_up_id"));
        levelUp.setCustomerId(rs.getInt("customer_id"));
        levelUp.setPoints(rs.getInt("points"));
        levelUp.setMemberDate(rs.getDate("member_date").toLocalDate());
        return levelUp;
    }
}

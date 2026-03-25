package org.Formula1.dao;

import org.Formula1.db.DataBaseManager;
import org.Formula1.models.Race;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class RaceDAO {
    public void insert(Race race){

        try  (Connection c = DataBaseManager.connect();
              PreparedStatement ps =c.prepareStatement(
                      "INSERT INTO race (raceDate, grandPrix, season, country, circuit) VALUES(?,?,?,?,?)",
                      PreparedStatement.RETURN_GENERATED_KEYS)){
            ps.setDate(1, new java.sql.Date(race.getRaceDate().getTime()));
            ps.setString(2, race.getGrandPrix());
            ps.setInt(3, race.getSeason());
            ps.setString(4, race.getCountry());
            ps.setString(5, race.getCircuit());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Race> findAll() {
        List<Race> races = new ArrayList<>();
        String query = "SELECT * FROM race";
        try (Connection c = DataBaseManager.connect();
             PreparedStatement ps = c.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Race d = new Race(rs.getInt("id"),
                        rs.getDate("raceDate"),
                        rs.getString("grandPrix"),
                        rs.getInt("season"),
                        rs.getString("country"),
                        rs.getString("circuit")
                );
                races.add(d);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }return races;
    }

    public Race findById(int id){
        String query = "SELECT * FROM race WHERE id = ?";
        Race race = null;
        try (Connection c = DataBaseManager.connect();
             PreparedStatement ps = c.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    race = new Race(
                            rs.getInt("id"),
                            rs.getDate("raceDate"),
                            rs.getString("grandPrix"),
                            rs.getInt("season"),
                            rs.getString("country"),
                            rs.getString("circuit")
                    );
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }return race;
    }


    public void update(Race race){
        String query = "UPDATE race SET raceDate = ?, grandPrix = ?, season = ?, country = ?, circuit = ? WHERE id = ?";
        try  (Connection c = DataBaseManager.connect();
              PreparedStatement ps =c.prepareStatement(query)){
            ps.setDate(1, new java.sql.Date(race.getRaceDate().getTime()));
            ps.setString(2, race.getGrandPrix());
            ps.setInt(3, race.getSeason());
            ps.setString(4, race.getCountry());
            ps.setString(5, race.getCircuit());
            ps.setInt(6, race.getId());
            ps.executeUpdate();
            System.out.println("Race " + race.getGrandPrix() + " updated successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(int id){
        String query = "DELETE FROM race WHERE id = ?";
        try  (Connection c = DataBaseManager.connect();
              PreparedStatement ps =c.prepareStatement(query)){
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Race with ID " + id + " has been deleted.");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

package org.Formula1.dao;

import org.Formula1.db.DataBaseManager;
import org.Formula1.models.RaceResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RaceResultDAO {
    public void insert(RaceResult result) {
        String query = "INSERT INTO raceResult (raceId, driverId, finishPosition, gridPosition, pointsEarned, fastestLap, dnf, dnfReason) VALUES(?,?,?,?,?,?,?,?)";
        try (Connection c = DataBaseManager.connect();
             PreparedStatement ps = c.prepareStatement(query)) {
            ps.setInt(1, result.getRaceId());
            ps.setInt(2, result.getDriverId());
            ps.setInt(3, result.getFinishPosition());
            ps.setInt(4, result.getGridPosition());
            ps.setInt(5, result.getPointsEarned());
            ps.setBoolean(6, result.getFastestLap());
            ps.setBoolean(7, result.getDnf());
            ps.setString(8, result.getDnfReason());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<RaceResult> findByDriverId(int driverId) {
        List<RaceResult> results = new ArrayList<>();
        String query = "SELECT * FROM raceResult WHERE driverId = ?";
        try (Connection c = DataBaseManager.connect();
             PreparedStatement ps = c.prepareStatement(query)) {
            ps.setInt(1, driverId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    RaceResult r = new RaceResult(
                            rs.getInt("driverId"),
                            rs.getInt("raceId"),
                            rs.getInt("finishPosition"),
                            rs.getInt("id"),
                            rs.getInt("gridPosition"),
                            rs.getInt("pointsEarned")
                    );
                    r.setFastestLap(rs.getBoolean("fastestLap"));
                    r.setDnf(rs.getBoolean("dnf"));
                    r.setDnfReason(rs.getString("dnfReason"));
                    results.add(r);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public List<RaceResult> findByRaceId(int raceId) {
        List<RaceResult> results = new ArrayList<>();
        String query = "SELECT * FROM raceResult WHERE raceId = ?";
        try (Connection c = DataBaseManager.connect();
             PreparedStatement ps = c.prepareStatement(query)) {
            ps.setInt(1, raceId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    RaceResult r = new RaceResult(
                            rs.getInt("driverId"),
                            rs.getInt("raceId"),
                            rs.getInt("finishPosition"),
                            rs.getInt("id"),
                            rs.getInt("gridPosition"),
                            rs.getInt("pointsEarned")
                    );
                    r.setFastestLap(rs.getBoolean("fastestLap"));
                    r.setDnf(rs.getBoolean("dnf"));
                    r.setDnfReason(rs.getString("dnfReason"));
                    results.add(r);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public void update(RaceResult result) {
        String query = "UPDATE raceResult SET raceId = ?, driverId = ?, finishPosition = ?, gridPosition = ?, pointsEarned = ?, fastestLap = ?, dnf = ?, dnfReason = ? WHERE id = ?";
        try (Connection c = DataBaseManager.connect();
             PreparedStatement ps = c.prepareStatement(query)) {
            ps.setInt(1, result.getRaceId());
            ps.setInt(2, result.getDriverId());
            ps.setInt(3, result.getFinishPosition());
            ps.setInt(4, result.getGridPosition());
            ps.setInt(5, result.getPointsEarned());
            ps.setBoolean(6, result.getFastestLap());
            ps.setBoolean(7, result.getDnf());
            ps.setString(8, result.getDnfReason());
            ps.setInt(9, result.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String query = "DELETE FROM raceResult WHERE id = ?";
        try (Connection c = DataBaseManager.connect();
             PreparedStatement ps = c.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

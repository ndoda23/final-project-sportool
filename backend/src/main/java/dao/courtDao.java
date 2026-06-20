package dao;

import model.Court;

import java.util.List;

public interface courtDao {
    public List<Court> getCourtsByType(String type);
    public List<Court> getAllCourts();
}
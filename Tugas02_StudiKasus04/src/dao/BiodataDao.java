package dao;

import db.MySqlConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Biodata;

public class BiodataDao {
    private Connection connection;

    public BiodataDao() {
        connection = MySqlConnection.getInstance().getConnection();
    }

    public List<Biodata> getAllBiodata() {
        List<Biodata> biodataList = new ArrayList<>(); 
        String query = "SELECT * FROM biodata"; // Replace with your actual table name
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nama = resultSet.getString("nama");
                String alamat = resultSet.getString("alamat");
                String email = resultSet.getString("email");
                String telepon = resultSet.getString("telepon");
                biodataList.add(new Biodata(id, nama, alamat, email, telepon));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return biodataList;
    }

    public boolean addBiodata(Biodata biodata) {
        String query = "INSERT INTO biodata (nama, alamat, email, telepon) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, biodata.getNama());
            stmt.setString(2, biodata.getAlamat());
            stmt.setString(3, biodata.getEmail());
            stmt.setString(4, biodata.getTelepon());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteBiodata(int id) {
        String query = "DELETE FROM biodata WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

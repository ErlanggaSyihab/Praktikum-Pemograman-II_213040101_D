package dao;

import db.MySqlConnection;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import model.Member;
import model.JenisMember;

public class MemberDao {

    public int insert(Member member) {
        int result = -1;
        String sql = "INSERT INTO member (id, nama, jenis_member_id) VALUES (?, ?, ?)";
        try (Connection connection = MySqlConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, member.getId());
            statement.setString(2, member.getNama());
            statement.setString(3, member.getJenisMember().getId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int update(Member member) {
        int result = -1;
        String sql = "UPDATE member SET nama = ?, jenis_member_id = ? WHERE id = ?";
        try (Connection connection = MySqlConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, member.getNama());
            statement.setString(2, member.getJenisMember().getId());
            statement.setString(3, member.getId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int delete(Member member) {
        int result = -1;
        String sql = "DELETE FROM member WHERE id = ?";
        try (Connection connection = MySqlConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, member.getId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Member> findAll() {
        List<Member> list = new ArrayList<>();
        String sql = "SELECT member.id, member.nama, jenis_member.id AS jenis_member_id, jenis_member.nama AS jenis_member_nama " +
                     "FROM member " +
                     "JOIN jenis_member ON jenis_member.id = member.jenis_member_id";
        try (Connection connection = MySqlConnection.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Member member = new Member();
                member.setId(resultSet.getString("id"));
                member.setNama(resultSet.getString("nama"));
                
                JenisMember jenisMember = new JenisMember();
                jenisMember.setId(resultSet.getString("jenis_member_id"));
                jenisMember.setNama(resultSet.getString("jenis_member_nama"));
                
                member.setJenisMember(jenisMember);
                list.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

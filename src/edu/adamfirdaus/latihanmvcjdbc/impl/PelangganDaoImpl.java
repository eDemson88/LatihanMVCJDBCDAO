/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.adamfirdaus.latihanmvcjdbc.impl;

import edu.adamfirdaus.latihanmvcjdbc.entity.Pelanggan;
import edu.adamfirdaus.latihanmvcjdbc.error.PelangganException;
import edu.adamfirdaus.latihanmvcjdbc.service.PelangganDao;
import java.sql.Connection;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author ryzen
 */
public class PelangganDaoImpl implements PelangganDao{
    
    private Connection connection;
    
    private final String insertPelanggan = "INSERT INTO Pelanggan (Nama,Alamat,Telepon,Email) VALUES (?,?,?,?)";

    private final String updatePelanggan = "UPDATE PELANGGAN SET Nama = ? , Alamat=?, Telepon = ?, Email=? WHERE ID=?";
    
    private final String deletePelanggan = "DELETE FROM Pelanggan WHERE ID = ?";
    
    private final String getById = "SELECT * FROM Pelanggan WHERE ID=?";
    
    private final String getByEmail = "SELECT * FROM Pelanggan WHERE Email=?";
    
    private final String selectAll = "SELECT * FROM Pelanggan";
    
    public PelangganDaoImpl(Connection connection) {
        this.connection = connection;
    }
    
    

    @Override
    public void insertPelanggan(Pelanggan pelanggan) throws PelangganException {
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            
            statement = connection.prepareStatement (insertPelanggan, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, pelanggan.getNama());
            statement.setString(2, pelanggan.getAlamat());
            statement.setString(3, pelanggan.getTelepon());
            statement.setString(4, pelanggan.getEmail());
            statement.executeUpdate();
            
            ResultSet result = statement.getGeneratedKeys();
                if (result.next()) {
                    pelanggan.setId(result.getInt(1));
            }
            
            
            connection.commit();
    
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {             
            }        
            throw new PelangganException(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex){
                
            }
            if (statement!=null) {
                try {
                    statement.close();
                } catch (SQLException e){ 
                }
            }
        }
        
     }

    @Override
    public void updatePelanggan(Pelanggan pelanggan) throws PelangganException {
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            
            statement = connection.prepareStatement (updatePelanggan);
            statement.setString(1, pelanggan.getNama());
            statement.setString(2, pelanggan.getAlamat());
            statement.setString(3, pelanggan.getTelepon());
            statement.setString(4, pelanggan.getEmail());
            statement.setInt(5, pelanggan.getId());
            statement.executeUpdate();
    
            connection.commit();
            
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {             
            }        
            throw new PelangganException(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex){
                
            }
            if (statement!=null) {
                try {
                    statement.close();
                } catch (SQLException e){ 
                }
            }
        }    
    }

    @Override
    public void deletePelanggan(Integer id) throws PelangganException {
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            
            statement = connection.prepareStatement (deletePelanggan);
            statement.setInt(1, id);
            statement.executeUpdate();
    
            connection.commit();
            
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {             
            }        
            throw new PelangganException(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex){
                
            }
            if (statement!=null) {
                try {
                    statement.close();
                } catch (SQLException e){ 
                }
            }
        }    
    }

    @Override
    public Pelanggan getPelanggan(Integer id) throws PelangganException {
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            
            statement = connection.prepareStatement (getById);
            statement.setInt(1, id);
            
            ResultSet result = statement.executeQuery();
            Pelanggan pelanggan = null;
            
            if (result.next()) {
                pelanggan = new Pelanggan();
                pelanggan.setId(result.getInt("ID"));
                pelanggan.setNama(result.getString("NAMA"));
                pelanggan.setAlamat(result.getString("ALAMAT"));
                pelanggan.setTelepon(result.getString("TELEPON"));
                pelanggan.setEmail(result.getString("EMAIl"));
            } else {
                throw new PelangganException("Pelanggan dengan ID " + id + " tidak ditemukan");
            }
            connection.commit();
            return pelanggan;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {             
            }        
            throw new PelangganException(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex){
                
            }
            if (statement!=null) {
                try {
                    statement.close();
                } catch (SQLException e){ 
                }
            }
        }
    }

    @Override
    public Pelanggan getPelanggan(String email) throws PelangganException {
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            
            statement = connection.prepareStatement (getByEmail);
            statement.setString(1, email);
            
            ResultSet result = statement.executeQuery();
            Pelanggan pelanggan = null;
            
            if (result.next()) {
                pelanggan = new Pelanggan();
                pelanggan.setId(result.getInt("ID"));
                pelanggan.setNama(result.getString("NAMA"));
                pelanggan.setAlamat(result.getString("ALAMAT"));
                pelanggan.setTelepon(result.getString("TELEPON"));
                pelanggan.setEmail(result.getString("EMAIl"));
            } else {
                throw new PelangganException("Pelanggan dengan Email " + email + " tidak ditemukan");
            }
            connection.commit();
            return pelanggan;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {             
            }        
            throw new PelangganException(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex){
                
            }
            if (statement!=null) {
                try {
                    statement.close();
                } catch (SQLException e){ 
                }
            }
        }
    }

    @Override
    public List<Pelanggan> selectAllPelanggan() throws PelangganException {
      
        Statement statement = null;
        List<Pelanggan> list = new ArrayList<Pelanggan>();
        
        try {
            connection.setAutoCommit(false);
            
            statement = connection.createStatement();
            
            ResultSet result = statement.executeQuery(selectAll);
            Pelanggan pelanggan = null;
            
            while (result.next()) {
                pelanggan = new Pelanggan();
                pelanggan.setId(result.getInt("ID"));
                pelanggan.setNama(result.getString("NAMA"));
                pelanggan.setAlamat(result.getString("ALAMAT"));
                pelanggan.setTelepon(result.getString("TELEPON"));
                pelanggan.setEmail(result.getString("EMAIl"));
                list.add(pelanggan);
            }
            connection.commit();
            return list;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {             
            }        
            throw new PelangganException(e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex){
                
            }
            if (statement!=null) {
                try {
                    statement.close();
                } catch (SQLException e){ 
                }
            }
        }        
        
    }
    
    
}

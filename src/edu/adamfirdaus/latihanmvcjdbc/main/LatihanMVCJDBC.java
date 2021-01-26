/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.adamfirdaus.latihanmvcjdbc.main;

import edu.adamfirdaus.latihanmvcjdbc.database.KingBarbershopDatabase;
import edu.adamfirdaus.latihanmvcjdbc.entity.Pelanggan;
import edu.adamfirdaus.latihanmvcjdbc.error.PelangganException;
import edu.adamfirdaus.latihanmvcjdbc.service.PelangganDao;
import edu.adamfirdaus.latihanmvcjdbc.view.MainViewPelanggan;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
/**
 *
 * @author ryzen
 */
public class LatihanMVCJDBC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, PelangganException {
        // TODO code application logic here
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    MainViewPelanggan pelanggan = new MainViewPelanggan();
                    pelanggan.loadDatabase();
                    pelanggan.setVisible(true);
                } catch(SQLException e){
                } catch (PelangganException ex) {
                    Logger.getLogger(LatihanMVCJDBC.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }) ;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.adamfirdaus.latihanmvcjdbc.event;

import edu.adamfirdaus.latihanmvcjdbc.entity.Pelanggan;
import edu.adamfirdaus.latihanmvcjdbc.model.PelangganModel;

/**
 *
 * @author ryzen
 */
public interface PelangganListener {
    
    public void onChange(PelangganModel model);
    
    public void onInsert(Pelanggan pelanggan);
    
    public void onDelete();
    
    public void onUpdate(Pelanggan pelanggan);
    
}

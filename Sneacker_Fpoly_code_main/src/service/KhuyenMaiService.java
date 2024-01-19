/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
//import model.KhachHang;
import model.KhuyenMai;
import repository.KhuyenMaiRepository;

/**
 *
 * @author Thuan
 */
public class KhuyenMaiService {
    KhuyenMaiRepository khuyenMaiRepository = new KhuyenMaiRepository();
    public ArrayList<KhuyenMai> getAll(){
        return khuyenMaiRepository.getAll();
    }
    
     public KhuyenMai insert(KhuyenMai k){
        return khuyenMaiRepository.them(k);
    }
     
      public KhuyenMai update(KhuyenMai km){
        return khuyenMaiRepository.updateKhuyenMai(km);
    }
}

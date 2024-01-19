/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.KhachHang;
import repository.KhachHangRepository;



/**
 *
 * @author Thuan
 */
public class KhachHangService {
    KhachHangRepository khachHangRepository = new KhachHangRepository();
    public ArrayList<KhachHang> loadData(){
        return khachHangRepository.getAll();
    }
    public KhachHang insert(KhachHang k){
        return khachHangRepository.them(k);
    }
    public KhachHang update(KhachHang kh){
        return khachHangRepository.updateKhachHang(kh);
    }
    public ArrayList<KhachHang> getByTenKH(String tenKH){
        return khachHangRepository.getKhachHangByTenKH(tenKH);
    }

    
}

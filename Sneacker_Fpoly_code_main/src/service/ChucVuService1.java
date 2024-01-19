/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.ChucVu;
import repository.ChucVuRepository1;

/**
 *
 * @author Chien Duong
 */
public class ChucVuService1 {
      ChucVuRepository1 chucVuRepository1 = new ChucVuRepository1();
   public ArrayList<ChucVu> getALLCV(){
        return chucVuRepository1.getAll();
    }
      public ArrayList<ChucVu> getCVByID(String id){
        return chucVuRepository1.getChucVuByID(id);
    }
      
      
        public ChucVu InsertCV(ChucVu cvm){
        return chucVuRepository1.insertCV(cvm);
    }
        
        
            public ChucVu UpdateCV(ChucVu cv){
        return chucVuRepository1.updateCV(cv);
    }
}

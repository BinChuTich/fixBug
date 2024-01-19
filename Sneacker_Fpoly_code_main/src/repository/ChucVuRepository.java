    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ChucVu;
import utilitys.DBConnection;
import utilitys.JDBCHelper;

/**
 *
 * @author Chien Duong
 */
public class ChucVuRepository {
     public ArrayList<ChucVu> getAllChucVu(){
        ArrayList<ChucVu> list=new ArrayList<>();
        String sql="SELECT * FROM CHUCVU";
        ResultSet rs=JDBCHelper.excuteQuery(sql);
        try {
            while(rs.next()){
                list.add(new ChucVu(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5), rs.getInt(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChucVuRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
       public ChucVu getCVByID(String id){
        
        String sql="SELECT * FROM CHUCVU WHERE id = ?";
        ResultSet rs=JDBCHelper.excuteQuery(sql,id);
        try {
            while(rs.next()){
                return new ChucVu(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5), rs.getInt(6));
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChucVuRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null;
    }
       public ChucVu insertCV(ChucVu cv){
        
        String sql="INSERT INTO CHUCVU VALUES(NEWID(),?,?,GetDate(),null,?)";
        JDBCHelper.executeUpdate(sql, cv.getMaChucVu(),cv.getTenChucVu(),cv.getTrangThai());
        return cv;
    }
 
    public ChucVu updateCV(ChucVu cv){
        String sql="UPDATE ChucVu SET tenChucVu= ?, trangThai = ? WHERE maChucVu=?";
        JDBCHelper.executeUpdate(sql, cv.getTenChucVu(),cv.getTrangThai(),cv.getMaChucVu());
        return cv;
    }
    public Integer deleteCV(String id){   
        String sql="DELETE FROM CHUCVU WHERE maChucVu=?";
        int row = JDBCHelper.executeUpdate(sql,id);
        return row;
    }
   
}

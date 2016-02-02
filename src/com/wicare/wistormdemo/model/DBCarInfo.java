package com.wicare.wistormdemo.model;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;
import com.wicare.wistorm.toolkit.WSql;
import com.wicare.wistormdemo.entity.CarInfo;

import android.content.Context;
import android.util.Log;

/**
 * 车辆信息增删改查
 * @author c
 *
 */
public class DBCarInfo {
	private static WSql helper;
	private Dao<CarInfo, Integer> carInfoDao;
	public DBCarInfo(Context context) {
		super();
		/**
		 * 如果数据库有变动，一定要更新版本
		 */
		WSql.updateVersion(2);
		WSql.addTable(CarInfo.class);
		initHelper(context);
		initCarInfoDao();
	}
	
	 /** 
     * 单例获取该Helper 
     *  
     * @param context 
     * @return 
     */  
    public static synchronized void initHelper(Context context)  
    {  
        if (helper == null)  
        {  
            synchronized (WSql.class)  
            {  
                if (helper == null)  
                	helper = new WSql(context);  
            }  
        }  
    }  
  
    public synchronized  void initCarInfoDao(){  
      
    	if(carInfoDao == null){
			try {
				carInfoDao = helper.getDao(CarInfo.class);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    }  
	
	
	public void insert(CarInfo carInfo){
		try {
			carInfoDao.create(carInfo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert(List<CarInfo> carInfoList){
		for(int  i=0;i<carInfoList.size();i++){
			insert(carInfoList.get(i));
		}
	}
	
	
	public void delete(CarInfo carInfo){
		try {
			carInfoDao.delete(carInfo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<CarInfo> queryAll(){
		
		try {
			return carInfoDao.queryForAll();
		} catch (SQLException e) {
			e.printStackTrace();
		};
		return null;
		
	}
	
	public CarInfo query(int id){
		try {
			List<CarInfo> list = carInfoDao.queryForEq("id", id);
			if(list!=null && list.size()>0){
				return list.get(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public void deleteAll(){
		try {
			TableUtils.clearTable(helper.getConnectionSource(),  CarInfo.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getCount(){
		
		try {
			return	(int) carInfoDao.countOf();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	

}

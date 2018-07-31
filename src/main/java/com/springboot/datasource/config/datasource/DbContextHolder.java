package com.springboot.datasource.config.datasource;

import com.springboot.datasource.enums.DbEnum;

public class DbContextHolder {
    public enum DbType{
        MASTER,SLAVE
    }
    private static final ThreadLocal<DbType> contextHolder = new ThreadLocal<>();

    private static final ThreadLocal<String> holder = new ThreadLocal<>();

    public static void setDbType(DbType dbType){
        if (dbType == null){
            throw new NullPointerException();
        }
        contextHolder.set(dbType);
    }

    public static DbType getDbType(){
        return contextHolder.get() == null ? DbType.MASTER : contextHolder.get();
    }

    public static void clearDbType(){
        contextHolder.remove();
    }

    public static void setDb(String dbType){
        if (dbType == null){
            throw new NullPointerException();
        }
        holder.set(dbType);
    }

    public static String getDb(){
        return holder.get() == null ? DbEnum.MASTER : holder.get();
    }

    public static void clearDb(){
        holder.remove();
    }
}

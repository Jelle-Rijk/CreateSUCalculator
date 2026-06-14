package org.jellerijk.minecraft.util.sql;

public abstract class QueryBuilder {
    public static String selectAll(String table) {
        return "SELECT * FROM " + table;
    }
}

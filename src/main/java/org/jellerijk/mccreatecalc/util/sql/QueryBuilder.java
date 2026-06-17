package org.jellerijk.mccreatecalc.util.sql;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class QueryBuilder {
    public static String selectAll(String table, String... cols) {
        String colSelection = cols.length == 0 ? "*" : String.join(",", cols);
        return "SELECT " + colSelection + " FROM " + table;
    }

    public static String updateCols(String table, String id, List<String> cols) {
        return updateCols(table, List.of(id), cols);
    }

    public static String updateCols(String table, List<String> ids, List<String> cols) {
        return "UPDATE " + table + " SET " + cols.stream()
                .map(col -> col + "=?")
                .collect(Collectors.joining(",")) + " WHERE " + ids.stream()
                .map(id -> id + "=?")
                .collect(Collectors.joining(" AND "));
    }

    public static String selectBy(String table, String id) {
        return selectBy(table, List.of(id));
    }

    public static String selectBy(String table, List<String> ids) {
        return selectAll(table) + " WHERE " + ids.stream().map(id -> id + "=?").collect(Collectors.joining(" AND "));
    }

    public static String insert(String table, String[] cols) {
        return insert(table, Arrays.asList(cols));
    }

    public static String insert(String table, List<String> cols) {
        return "INSERT INTO " + table + " (" + String.join(",", cols) + ") VALUES (" + "?,".repeat(
                cols.size() - 1) + "?)";
    }
}

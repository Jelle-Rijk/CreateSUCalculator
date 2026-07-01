package org.jellerijk.mccreatecalc.util.sql;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SQLBuilder {
    private final StringBuilder query;

    public SQLBuilder() {
        query = new StringBuilder();
    }

    public SQLBuilder select() {
        query.append("SELECT * ");
        return this;
    }

    public SQLBuilder select(String... cols) {
        query.append("SELECT ").append(String.join(", ", cols)).append(" ");
        return this;
    }

    public SQLBuilder from(String table) {
        query.append("FROM ").append(table).append(" ");
        return this;
    }


    /**
     * Default where clause. This will match every col to the supplied argument.
     *
     * @param cols The columns to use for filtering.
     * @return Builder appended with "WHERE col1=?, col2=?, col3=?, ..."
     */
    public SQLBuilder where(String... cols) {
        String colString = Arrays.stream(cols).map(c -> c + "=?").collect(Collectors.joining(" AND "));
        query.append("WHERE ").append(colString).append(" ");
        return this;
    }

    public SQLBuilder innerJoin(String leftTable, String leftCol, String rightTable, String rightCol) {
        query.append(buildJoinString("JOIN", leftTable, leftCol, rightTable, rightCol));
        return this;
    }

    public SQLBuilder leftJoin(String leftTable, String leftCol, String rightTable, String rightCol) {
        query.append(buildJoinString("LEFT JOIN", leftTable, leftCol, rightTable, rightCol));
        return this;
    }

    public SQLBuilder rightJoin(String leftTable, String leftCol, String rightTable, String rightCol) {
        query.append(buildJoinString("RIGHT JOIN", leftTable, leftCol, rightTable, rightCol));
        return this;
    }

    public SQLBuilder fullJoin(String leftTable, String leftCol, String rightTable, String rightCol) {
        query.append(buildJoinString("FULL JOIN", leftTable, leftCol, rightTable, rightCol));
        return this;
    }

    private String buildJoinString(String join, String leftTable, String leftCol, String rightTable, String rightCol) {
        return String.format("%s %s ON %s.%s = %s.%s ", join, rightTable, leftTable, leftCol, rightTable, rightCol);
    }

    public String build() {
        return query.toString();
    }

    public SQLBuilder insertInto(String table) {
        query.append("INSERT INTO ").append(table).append(" ");
        return this;
    }

    public SQLBuilder values(String... cols) {
        String values = "(" + String.join(", ", cols) + ") VALUES (" + "?,".repeat(cols.length - 1) + "?) ";
        query.append(values);
        return this;
    }

    public SQLBuilder deleteFrom(String table) {
        query.append("DELETE FROM ").append(table).append(" ");
        return this;
    }
}

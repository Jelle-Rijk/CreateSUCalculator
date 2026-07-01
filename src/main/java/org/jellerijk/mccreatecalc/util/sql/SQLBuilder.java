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

    public SQLBuilder innerJoin(String table, String leftCol, String rightCol) {
        query.append(buildJoinString("JOIN", table, leftCol, rightCol));
        return this;
    }

    public SQLBuilder leftJoin(String table, String leftCol, String rightCol) {
        query.append(buildJoinString("LEFT JOIN", table, leftCol, rightCol));
        return this;
    }

    public SQLBuilder rightJoin(String table, String leftCol, String rightCol) {
        query.append(buildJoinString("RIGHT JOIN", table, leftCol, rightCol));
        return this;
    }

    public SQLBuilder fullJoin(String table, String leftCol, String rightCol) {
        query.append(buildJoinString("FULL JOIN", table, leftCol, rightCol));
        return this;
    }

    private String buildJoinString(String join, String table, String leftCol, String rightCol) {
        return String.format("%s %s ON %s = %s ", join, table, leftCol, rightCol);
    }

    public String build() {
        return query.toString();
    }
}

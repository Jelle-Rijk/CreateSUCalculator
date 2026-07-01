package org.jellerijk.mccreatecalc.util.sql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SQLBuilderTest {
    private SQLBuilder builder;

    @BeforeEach
    void setUp() {
        builder = new SQLBuilder();
    }

    @Test
    void build_emptyQuery_ReturnsEmptyString() {
        assertEquals("", new SQLBuilder().build());
    }

    @Test
    void select_noArgs_SetsCorrectString() {
        assertEquals("SELECT * ", builder.select().build());
    }

    @Test
    void select_OneCol_SetsCorrectString() {
        assertEquals("SELECT Product ", builder.select("Product").build());
    }

    @Test
    void select_MultipleCols_SetsCorrectString() {
        assertEquals("SELECT Product, Price, Amount ", builder.select("Product", "Price", "Amount").build());
    }

    @Test
    void from() {
        assertEquals("FROM Products ", builder.from("Products").build());
    }

    @Test
    void where_OneCol_SetsCorrectString() {
        assertEquals("WHERE Id=? ", builder.where("Id").build());
    }

    @Test
    void where_MultipleCols_SetsCorrectString() {
        assertEquals("WHERE Id=? AND Size=? AND Colour=? ", builder.where("Id", "Size", "Colour").build());
    }

    @Test
    void innerJoin() {
        assertEquals("JOIN Books ON p.id = b.id ", builder.innerJoin("Books", "p.id", "b.id").build());
    }

    @Test
    void leftJoin() {
        assertEquals("LEFT JOIN Books ON p.id = b.id ", builder.leftJoin("Books", "p.id", "b.id").build());
    }

    @Test
    void rightJoin() {
        assertEquals("RIGHT JOIN Books ON p.id = b.id ", builder.rightJoin("Books", "p.id", "b.id").build());
    }

    @Test
    void fullJoin() {
        assertEquals("FULL JOIN Books ON p.id = b.id ", builder.fullJoin("Books", "p.id", "b.id").build());
    }

    @Test
    void combiningClauses_Simple_GivesCorrectOutput() {
        String sql = builder.select("Id").from("Product").build();
        assertEquals("SELECT Id FROM Product ", sql);
    }

    @Test
    void combiningClauses_Complex_GivesCorrectOutput() {
        String sql = builder.select("Id")
                .from("Product")
                .leftJoin("Books", "Product.Id", "Books.Id")
                .where("pages", "colour")
                .build();
        assertEquals("SELECT Id FROM Product LEFT JOIN Books ON Product.Id = Books.Id WHERE pages=? AND colour=? ", sql);
    }
}
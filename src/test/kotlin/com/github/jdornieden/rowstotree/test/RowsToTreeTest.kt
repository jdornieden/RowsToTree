package com.github.jdornieden.rowstotree.test

import com.github.jdornieden.rowstotree.Node
import com.github.jdornieden.rowstotree.Row
import com.github.jdornieden.rowstotree.rowsToTree
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RowsToTreeTest {
    @Test
    fun emptyReturnsNull() {
        assertExpectedTree(arrayOf(), null)
    }

    @Test
    fun singleRowReturnsNode() {
        assertExpectedTree(arrayOf(Row(1, "single")), Node("single"))
    }

    @Test
    fun rowsWithTwoLevelsReturnsTree() {
        val singleChild = arrayOf(
                Row(1, "root"),
                Row(2, "child", 1))
        val singleChildTree = Node("root", setOf(
                Node("child")))
        assertExpectedTree(singleChild, singleChildTree)
        assertExpectedTree(singleChild.reversedArray(), singleChildTree)

        val multipleChildren = arrayOf(
                Row(1, "root"),
                Row(2, "child1", 1),
                Row(3, "child2", 1)
        )
        val mulipleChildrenTree = Node("root", setOf(
                Node("child1"),
                Node("child2")))
        assertExpectedTree(multipleChildren, mulipleChildrenTree)
    }

    @Test
    fun rowsWithThreeLevelsReturnsTree() {
        val rows = arrayOf(
                Row(1, "root"),
                Row(2, "child1", 1),
                Row(3, "child2", 2)
        )
        val expectedTree = Node("root", setOf(
                Node("child1", setOf(
                        Node("child2")))))
        assertExpectedTree(rows, expectedTree)
        assertExpectedTree(rows.reversedArray(), expectedTree)
    }

    @Test
    fun rowsWithFourLevelsReturnsTree() {
        val rows = arrayOf(
                Row(1, "root"),
                Row(2, "child1", 1),
                Row(3, "child2", 2),
                Row(4, "child2a", 3),
                Row(5, "child2b", 3)
        )
        val expectedTree = Node("root", setOf(
                Node("child1", setOf(
                        Node("child2", setOf(
                                Node("child2a"),
                                Node("child2b")))))))
        assertExpectedTree(rows, expectedTree)
        assertExpectedTree(rows.reversedArray(), expectedTree)
    }

    private fun assertExpectedTree(rows: Array<Row>, tree: Node?) {
        assertEquals(tree, rowsToTree(rows))
    }
}


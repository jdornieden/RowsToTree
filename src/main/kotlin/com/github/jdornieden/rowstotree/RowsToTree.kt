package com.github.jdornieden.rowstotree

data class Row(val id: Long, val title: String, val parent: Long? = null)
data class Node(val title: String, val children: Set<Node>? = setOf())

fun rowsToTree(rows: Array<Row>): Node? {
    if (rows.isEmpty()) return null
    return toNode(root(rows), rows)
}

private fun root(rows: Array<Row>): Row {
    return rows.first { it.parent == null }
}

private fun toNode(row: Row, rows: Array<Row>): Node {
    val children = rows.filter { it.parent == row.id }
            .map { toNode(it, rows) }
            .toSet()
    return Node(row.title, children)
}